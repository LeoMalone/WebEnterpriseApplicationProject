package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LeagueBean;
import beans.PlayerBean;
import dao.CreateAccount;
import dao.League;

/**
 * The CreatePlayerServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the team owner control panel option Create User.
 * @author Kevin Read and edited by Kevin Villemaire
 */
public class CreatePlayerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /createPlayer
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

		//get id from url and set userBean id
		StringBuilder sb = new StringBuilder(URLDecoder.decode(request.getQueryString(), "UTF-8"));
		sb.deleteCharAt(0);
		
		// If User is not signed In redirect to sign in page
		// TODO: distinguish between user types
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("./login");
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
				request.setAttribute("teamId", sb.toString());
				response.setContentType("text/html");
				RequestDispatcher rd = request.getRequestDispatcher("team_create_player.jsp"); 
				rd.forward(request, response);
			}
		}
	}

	/**
	 * doPost method mapped to /createPlayer
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Tracked Cookie variables
		String userName = null;
		String language = null;

		// set response type and get post data from jsp form
		response.setContentType("text/html");
		String newFirstName = request.getParameter("newFirstName");
		String newLastName = request.getParameter("newLastName");
		String newNumber = request.getParameter("newNumber");
		String newPosition = request.getParameter("newPosition");


		//Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);


		// If User is not signed In redirect to sign in page
		// TODO: distinguish between user types
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("./login");
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




				// If any parameter is null
				if (newFirstName == null || newLastName == null || newNumber == null || newPosition == null) {
					//get id from url and set userBean id
					StringBuilder sb = new StringBuilder(URLDecoder.decode(request.getQueryString(), "UTF-8"));
					sb.deleteCharAt(0);

					response.sendRedirect("./createPlayer?="+sb.toString());

				} else {	
					// Create new playerBean
					PlayerBean player = new PlayerBean(newFirstName, newLastName, newNumber, newPosition);

					StringBuilder sb = new StringBuilder(URLDecoder.decode(request.getQueryString(), "UTF-8"));
					sb.deleteCharAt(0);

					// If createNewUser method returns true
					if(CreateAccount.createNewPlayer(player, sb.toString())) {
						response.sendRedirect("./teamRoster");
					}
				}
			}
		}
	}
}