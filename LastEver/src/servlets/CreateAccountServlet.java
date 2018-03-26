package servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.mindrot.jbcrypt.BCrypt;

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
	private static final String SECRET = "LOOK FOR INFO ON DISCORD";

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
		String passConfirm = request.getParameter("newPassConfirm");
		String userType = request.getParameter("createRadio");
		String hashedpw = null;
		String captcha = request.getParameter("g-recaptcha-response");


		// If any parameter is null
		if(newFirstName == "" || newLastName == "" || newUsername == "" || newEmail == "" || newPassword == "" || passConfirm == "" || userType == null) {
			response.sendRedirect("./login");			
		} else if(!newPassword.equals(passConfirm)) {
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

			//Using BCrypt hash the users password with a new generated salt
			hashedpw = BCrypt.hashpw(newPassword, BCrypt.gensalt(13));

			// Create new userBean
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			UserBean user = new UserBean(newFirstName, newLastName, newUsername, newEmail, hashedpw, ut, timestamp);

			String postData = "https://www.google.com/recaptcha/api/siteverify?secret=" + SECRET + "&response="
					+ captcha;
			URL capURL = new URL(postData);
			
			HttpURLConnection conn = (HttpURLConnection) capURL.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty(
					"Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty(
					"charset", StandardCharsets.UTF_8.displayName());
			conn.setRequestProperty(
					"Content-Length", Integer.toString(postData.length()));
			conn.setUseCaches(false);
			conn.getOutputStream()
			.write(postData.getBytes(StandardCharsets.UTF_8));

			JSONObject json = new JSONObject(new JSONTokener(conn.getInputStream()));

			// If createNewUser method returns true
			if (CreateAccount.createNewUser(user) && json.getBoolean("success") == true) {
				// Get session and login newly created user
				HttpSession session = request.getSession();
				if (session != null) {
					// Set up user cookie
					Cookie cookie = new Cookie("username", user.getUsername());
					session.setMaxInactiveInterval(30 * 60);
					cookie.setMaxAge(30 * 60);
					response.addCookie(cookie);

					// Get userType homepage
					String url = null;
					if (user.getUserType().equals("Administrator")) {
						session.setAttribute("signedIn", "Administrator");
						url = "./admin";
					} else if (user.getUserType().equals("Referee")) {
						session.setAttribute("signedIn", "Referee");
						url = "./referee";
					} else if (user.getUserType().equals("Team Owner")) {
						session.setAttribute("signedIn", "Team Owner");
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
