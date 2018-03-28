package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DivisionBean;
import beans.LeagueBean;
import beans.UserBean;
import dao.CreateAccount;
import dao.Division;
import dao.League;

/**
 * The AdminCreateServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option Create User.
 * @author Liam Maloney and edited by Kevin Villemaire
 */
public class AdminCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// User Type Strings
	private static final String ADMIN = "Administrator";
	private static final String REF = "Referee";
	private static final String TEAM_OW = "Team Owner";
	
	
	/**
	 * doGet method mapped to /adminCreate
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tracked Cookie variables
		String userName = null;
		String language = null;
		
		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);
		
		//get division list for a team to join
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		// If User is not signed In redirect to sign in page
		if (!(request.getSession().getAttribute("signedIn").equals("Administrator"))) {
			response.sendRedirect("./index");
		} else {
			// If user is signed in, get language and username
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username"))
						userName = cookie.getValue();
					else if (cookie.getName().equals("language"))
						language = cookie.getValue();
				}
			}
			// If Language is null, set default language to en 
			if(language == null) {
				Cookie cookieLanguage = new Cookie("language", "en");
				cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
				response.addCookie(cookieLanguage);
			}
			// Set cookie language for users
			else {	
				language = request.getParameter("language");
				Cookie[] theCookies = request.getCookies();
	
				for (Cookie tempCookie : theCookies) {
					if ("language".equals(tempCookie.getName())) {
						if (language != null)
							tempCookie.setValue(language);
						response.addCookie(tempCookie);
						break;
					}
				}
				
				// Set content type and username and dispatch to jsp
				request.setAttribute("userName", userName);
				response.setContentType("text/html");
				RequestDispatcher rd = request.getRequestDispatcher("admin_create_user.jsp"); 
		        rd.forward(request, response);
			}
		}
	}
	
	/**
	 * doPost method mapped to /adminCreate
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

		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);
		
		// If any parameter is null
		if (newFirstName == null || newLastName == null || newUsername == null || newEmail == null || newPassword == null || userType == null) {
			response.sendRedirect("./adminCreate");
			
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
			if(CreateAccount.createNewUser(user)) {
				response.sendRedirect("./adminUsers");
			}
		}
	}
}
