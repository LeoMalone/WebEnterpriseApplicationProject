package servlets;

import java.io.IOException;
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
import dao.League;

/**
 * The AdminDivisionsServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option view Divisions.
 * @author Liam Maloney, Kevin Villemaire
 */
public class AdminDivisionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /adminDivisions
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
				
				//get league list for an admin to edit divisions
				List<LeagueBean> lbl = new ArrayList<LeagueBean>();
				if(League.getAllLeagues(lbl)) {
					request.setAttribute("leagues", lbl);
				}
				
				if(request.getQueryString() != null) {
					StringBuilder sb = new StringBuilder(request.getQueryString());
					sb.deleteCharAt(0);
					request.setAttribute("currentId", sb.toString());
					
					//get division list for an admin to edit
					List<DivisionBean> dlb = new ArrayList<DivisionBean>();
					if(League.getDivisionsByLeague(dlb, sb.toString())) {
						request.setAttribute("allDiv", dlb);
					}				
				} else {
					List<DivisionBean> dlb = new ArrayList<DivisionBean>();
					if(League.getDivisionsWithNoLeague(dlb)) {
						request.setAttribute("allDiv", dlb);
					}
				}
				
				// Set content type and username and dispatch to jsp
				request.setAttribute("userName", userName);
				response.setContentType("text/html");
				RequestDispatcher rd = request.getRequestDispatcher("admin_divisions.jsp");  
		        rd.forward(request, response);
				
			}
		}
	}
}
