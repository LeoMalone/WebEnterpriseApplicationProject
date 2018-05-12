package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * @author Kevin Villemaire
 */
public class ActivateUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /editUser
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tracked Cookie variables
		@SuppressWarnings("unused")
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
				String userID = request.getParameter("id");
				
				UserBean user = new UserBean();
				user.setId(userID);
				// If query is successful
				if(EditUser.activateUser(user)) {
					response.sendRedirect("./adminUsers");
				}
			}
		}
	}
}