package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dao.Login;

/**
 * The LoginServlet class handles the POST from /login to login a user
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
	 * doGet method mapped to /login
	 */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {    	
    	response.setContentType("text/html");	
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
        rd.forward(request, response);
    }
    
    /**
	 * doPost method mapped to /login
	 */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	// Set content type and get form data from login.jsp
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();        
        String loginEmail = request.getParameter("loginEmail");  
        String loginPass = request.getParameter("loginPass");
        
        // Create new userBean
        UserBean user = new UserBean(loginEmail, loginPass);
        
        // If login with given userBean is successful
        if(Login.validateUserLogin(user)) {
        	
        	// Start session and create use cookie
        	HttpSession session = request.getSession(false);
            if(session!=null) {
            	//Set up user cookie
    			Cookie cookie = new Cookie("username", user.getUsername());
    			session.setMaxInactiveInterval(30*60);
    			cookie.setMaxAge(30*60);
    			response.addCookie(cookie);
            	session.setAttribute("signedIn", true);
            	
            	// Get user home page
            	String jsp = null;
                if(user.getUserType().equals("Administrator")) {
                	jsp = "admin.jsp";
                } else if(user.getUserType().equals("Referee")) {
                	jsp = "referee.jsp";
                } else if(user.getUserType().equals("Team Owner")) {
                	jsp = "teamowner.jsp";
                }
                
                // redirect to correct login page
                session.setAttribute("userType", jsp);
                response.sendRedirect(jsp);
            }            
        } else {
        	 response.sendRedirect("./login");
        }
        out.close();  
    }  
}