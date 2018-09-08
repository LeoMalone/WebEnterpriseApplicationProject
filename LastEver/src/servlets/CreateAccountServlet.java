package servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.mindrot.jbcrypt.BCrypt;

import beans.UserBean;
import dao.CreateAccount;
import dao.EmailActivation;

/**
 * The CreateAccountServlet class handles the POST from /createAccount for
 * creating a new account
 * @author Liam Maloney, Kevin Villemaire
 */
public class CreateAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// User Types
	private static final String ADMIN = "Administrator";
	private static final String REF = "Referee";
	private static final String TEAM_OW = "Team Owner";
	private static final String SECRET = "6LcxBE8UAAAAAARna7UdEV99zCQzyrCHVWNb8D6n";

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

			// post the response to the Google reCAPTCHA API
			String postData = "https://www.google.com/recaptcha/api/siteverify?secret=" + SECRET + "&response="
					+ captcha;
			// create a new URL with the post data
			URL capURL = new URL(postData);

			// open a url connection with the specified url
			HttpURLConnection conn = (HttpURLConnection) capURL.openConnection();

			// set the connection to do output
			conn.setDoOutput(true);
			// set request method to post
			conn.setRequestMethod("POST");

			// set the content-type, charset, and content-length of the connection
			conn.setRequestProperty(
					"Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty(
					"charset", StandardCharsets.UTF_8.displayName());
			conn.setRequestProperty(
					"Content-Length", Integer.toString(postData.length()));
			conn.setUseCaches(false);
			conn.getOutputStream()
			.write(postData.getBytes(StandardCharsets.UTF_8));

			// create a JSON object from the response
			JSONObject json = new JSONObject(new JSONTokener(conn.getInputStream()));

			// If createNewUser method returns true and the user passed the CAPTCHA
			if (CreateAccount.createNewUser(user) && json.getBoolean("success") == true) {
				EmailActivation.generateToken(user);
				EmailActivation.sendEmail(user);
				response.sendRedirect("./login");
			} else {
				response.sendRedirect("./login");
			}
		}
	}
}
