package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.CreateAccount;

/**
 * 
 */
public class AdminCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// User Types
	private static final String ADMIN = "Administrator";
	private static final String REF = "Referee";
	private static final String TEAM_OW = "Team Owner";
	
	
	/**
	 * goGet method mapped to /adminCreate
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
		RequestDispatcher rd = request.getRequestDispatcher("admin_create_user.jsp"); 
        rd.forward(request, response);
	}
	
	/**
	 * doPost method mapped to /adminCreate
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// set response type and get post data from jsp form
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String newUsername = request.getParameter("newUsername");
		String newEmail = request.getParameter("newEmail");
		String newPassword = request.getParameter("newPass");
		String userType = request.getParameter("createRadio");

		// If any parameter is null
		if (newUsername == null || newEmail == null || newPassword == null || userType == null) {
			RequestDispatcher rd = request.getRequestDispatcher("./createAccount");
			rd.forward(request, response);
			
		} else {	
			// Get user type
			String ut = null;
			if (userType.equals(ADMIN))
				ut = ADMIN;
			if (userType.equals(REF))
				ut = REF;
			if (userType.equals(TEAM_OW))
				ut = TEAM_OW;
	
			// Create new userBean
			UserBean user = new UserBean(newUsername, newEmail, newPassword, ut);
	
			// If createNewUser method returns true
			if(CreateAccount.createNewUser(user)) {
				response.sendRedirect("./adminUsers");
			}
		}
		
		out.close();
	}
}
