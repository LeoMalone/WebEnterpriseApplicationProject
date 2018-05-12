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
import javax.servlet.http.HttpSession;

import beans.LeagueBean;
import beans.UserBean;
import dao.League;
import dao.Login;
import dao.Team;

/**
 * The LoginServlet class handles the POST from /login to login a user
 * @author Liam Maloney, Kevin Villemaire
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
		String language = null;

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		if (request.getSession().getAttribute("signedIn") != null) {
			response.sendRedirect("./index");
		} else {		
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("language"))
						language = cookie.getValue();
				}
			}
			if(language == null) {
				Cookie cookieLanguage = new Cookie("language", "en");
				cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
				response.addCookie(cookieLanguage);
			}
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

				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
				rd.forward(request, response);	
			}
		}
	}

	/**
	 * doPost method mapped to /login
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		// Set content type and get form data from login.jsp
		response.setContentType("text/html");

		String loginEmail = request.getParameter("loginEmail");  
		String loginPass = request.getParameter("loginPass");
		String language = null;

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		// Create new userBean
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		UserBean user = new UserBean(loginEmail, loginPass, timestamp);

		// If login with given userBean is successful
		if(Login.validateUserLogin(user)) {

			if(user.getAdminActivated() != 1) {
				response.sendRedirect("./adminActivationNeeded");
			}
			else {
				// Start session and create use cookie
				HttpSession session = request.getSession(false); //false means: don't create if it doesn't exist
				if(session!=null) {
					//Set up user cookie
					Cookie cookie = new Cookie("username", user.getUsername());
					session.setMaxInactiveInterval(30*60);
					cookie.setMaxAge(30*60);
					response.addCookie(cookie);

					// Get user home page
					String url = null;
					if(user.getUserType().equals("Administrator")) {
						session.setAttribute("signedIn", "Administrator");
						url = "./admin";
					} else if(user.getUserType().equals("Referee")) {
						session.setAttribute("signedIn", "Referee");
						url = "./referee";
					} else if(user.getUserType().equals("Team Owner")) {
						session.setAttribute("signedIn", "Team Owner");
						boolean hasTeam = Team.hasTeamByEmail(loginEmail);
						if (!hasTeam){
							url = "./teamCreateTeam";
						}
						else {
							url = "./teamowner";
						}
					}

					// redirect to correct login page
					session.setAttribute("userType", url);
					response.sendRedirect(url);
				}     
			}
		} else {

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("language"))
						language = cookie.getValue();
				}
			}
			if(language == null) {
				Cookie cookieLanguage = new Cookie("language", "en");
				cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
				response.addCookie(cookieLanguage);
			}
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

				response.sendRedirect("./login");
			}
		}  
	}
}
