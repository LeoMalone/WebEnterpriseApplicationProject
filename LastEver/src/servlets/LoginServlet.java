package servlets;

import beans.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Login;

public class LoginServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();        
        String loginEmail = request.getParameter("loginEmail");  
        String loginPass = request.getParameter("loginPass");
        
        UserBean user = new UserBean(loginEmail, loginPass);
        HttpSession session = request.getSession(false);
        

        if(Login.validateUserLogin(user)) {
        	
            if(session!=null)
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
            
        } else {
        	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);  
        }

        out.close();  
    }  
}