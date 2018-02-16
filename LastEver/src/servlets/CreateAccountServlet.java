package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dao.CreateAccount;

public class CreateAccountServlet extends HttpServlet {
	
	// User Types
	private static final String ADMIN = "Administrator";
	private static final String REF = "Referee";
	private static final String TEAM_OW = "Team Owner";
	
private static final long serialVersionUID = 1L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();        
        String newUsername = request.getParameter("newUsername");  
        String newEmail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPass");        
        String userType = request.getParameter("createRadio");
        
        if(newUsername == null || newEmail == null || newPassword == null || userType == null) {
        	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
        
        String ut = null;
        if(userType.equals(ADMIN))
        	ut = ADMIN;
        if(userType.equals(REF))
        	ut = REF;
        if(userType.equals(TEAM_OW))
        	ut = TEAM_OW;        
        
        UserBean user = new UserBean(newUsername, newEmail, newPassword, ut);
        HttpSession session = request.getSession();
        
        if(CreateAccount.createNewUser(user)) {
        	
        	session.setAttribute("isCreated", 1);
        	session.setAttribute("username", user.getUsername());
        	
        	String jsp = null;
            if(user.getUserType().equals("Administrator")) {
            	jsp = "admin.jsp";
            } else if(user.getUserType().equals("Referee")) {
            	jsp = "referee.jsp";
            } else if(user.getUserType().equals("Team Owner")) {
            	jsp = "teamowner.jsp";
            }
        	
        	RequestDispatcher rd = request.getRequestDispatcher(jsp);
            rd.forward(request, response);
        }
        out.close();  
    }

}
