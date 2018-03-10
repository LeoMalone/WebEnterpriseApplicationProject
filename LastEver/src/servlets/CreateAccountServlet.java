package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DivisionBean;
import beans.UserBean;
import dao.CreateAccount;
import dao.Division;
import dao.Team;

/**
 * The CreateAccountServlet class handles the POST from /createAccount for
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

		String newFirstName = request.getParameter("newFirstName");
		String newLastName = request.getParameter("newLastName");
		String newUsername = request.getParameter("newUsername");
		String newEmail = request.getParameter("newEmail");
		String newPassword = request.getParameter("newPass");
		String userType = request.getParameter("createRadio");

		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);

		// If any parameter is null
		if(newFirstName == null || newLastName== null || newUsername == null || newEmail == null || newPassword == null || userType == null) {
			response.sendRedirect("./login");

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
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			UserBean user = new UserBean(newFirstName, newLastName, newUsername, newEmail, newPassword, ut, timestamp);

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
					String url = null;
					if (user.getUserType().equals("Administrator")) {
						url = "./admin";
					} else if (user.getUserType().equals("Referee")) {
						url = "./referee";
					} else if (user.getUserType().equals("Team Owner")) {
						boolean hasTeam = Team.hasTeam(newUsername);
						if (!hasTeam){
							url = "./teamCreateTeam";
						}
						else {
							url = "./teamowner";
						}
					}

					// redirect to home page
					session.setAttribute("userType", url);
					response.sendRedirect(url);
				}
			} else {
				response.sendRedirect("./login");
			}
		}
	}
}
