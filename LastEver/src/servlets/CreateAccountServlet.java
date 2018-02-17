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
import dao.CreateAccount;

/**
 * The CreateAccountServlet class handles the POST from /createAccount to
 * creating a new account
 */
public class CreateAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// User Types
	private static final String ADMIN = "Administrator";
	private static final String REF = "Referee";
	private static final String TEAM_OW = "Team Owner";

	/**
	 * doPost method mapped to /createAccount
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
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

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
		if (CreateAccount.createNewUser(user)) {
			// Get session and login newly created user
			HttpSession session = request.getSession();
			if (session != null) {
				// Set up user cookie
				Cookie cookie = new Cookie("username", user.getUsername());
				session.setMaxInactiveInterval(30 * 60);
				cookie.setMaxAge(30 * 60);
				response.addCookie(cookie);
				session.setAttribute("signedIn", true);

				// Get userType homepage
				String jsp = null;
				if (user.getUserType().equals("Administrator")) {
					jsp = "admin.jsp";
				} else if (user.getUserType().equals("Referee")) {
					jsp = "referee.jsp";
				} else if (user.getUserType().equals("Team Owner")) {
					jsp = "teamowner.jsp";
				}
				
				// redirect to home page
				session.setAttribute("userType", jsp);
                response.sendRedirect(jsp);
                
			} else {
	        	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
	            rd.forward(request, response);  
	        }
		}
		out.close();
	}
}
