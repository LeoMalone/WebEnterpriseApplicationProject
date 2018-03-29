package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LeagueBean;
import beans.UserBean;
import dao.EditUser;
import dao.League;


/**
 * The EditUserServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option edit User.
 * @author Liam Maloney, Kevin Villemaire
 */
public class EditUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /editUser
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
			
				//get id from url and set userBean id
				StringBuilder sb = new StringBuilder(URLDecoder.decode(request.getQueryString(), "UTF-8"));
				sb.deleteCharAt(0);
				
				UserBean user = new UserBean();
				user.setId(sb.toString());
				// If query is successful
				if(EditUser.getUserForEdit(user)) {
					request.setAttribute("user", user);
					request.setAttribute("userName", userName);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("edit_user.jsp");  
			        rd.forward(request, response);
				}
			}
		}
	}
	
	/**
	 * doPost method mapped to /editUser
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get id from url and add it to UserBean
		UserBean user = new UserBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		// Get all schedule parameters from jsp inputs
		String newFirstName = request.getParameter("editFirstName");
		String newLastName = request.getParameter("editLastName");
		String newUsername = request.getParameter("editUsername");
		String newEmail = request.getParameter("editEmail");
		String newPassword = request.getParameter("editPass");
		String userType = request.getParameter("editRadio");
		
		// If any parameter is null
		if(newFirstName == "" || newLastName == "" || newUsername == "" || newEmail == "" || userType == null) {
			response.sendRedirect("./adminUsers");
		} else {
			// Set UserBean parameters
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user.setAccountUpdated(timestamp);
			user.setFirstName(newFirstName);
			user.setLastName(newLastName);
			user.setId(sb.toString());
			user.setUsername(newUsername);
			user.setEmail(newEmail);
			user.setPassword(newPassword);
			user.setUserType(userType);
			
			// If query is successful
			if(EditUser.saveChanges(user)) {
				response.sendRedirect("./adminUsers");
			}
		}
	}	
}
