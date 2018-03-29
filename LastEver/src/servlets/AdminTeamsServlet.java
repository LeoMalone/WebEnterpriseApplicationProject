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
import beans.TeamBean;
import dao.AdminTeams;
import dao.League;

/**
 * The AdminTeamsServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option View Teams.
 * @author Liam Maloney and edited by Kevin Villemaire
 */
public class AdminTeamsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /adminTeams
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
				
				// Team and Divisions lists for display on page
				List<TeamBean> tbl = new ArrayList<TeamBean>();
				List<DivisionBean> dbl = new ArrayList<DivisionBean>();
				
				// If id is null, get teams with no div
				if(request.getQueryString() == null) {
					AdminTeams.getAllTeams(null, dbl, tbl);
				} 
				// Else get teams in division from url
				else {
					StringBuilder sb = new StringBuilder(request.getQueryString());
					sb.deleteCharAt(0);
					AdminTeams.getAllTeams(sb.toString(), dbl, tbl);
					request.setAttribute("currentId", sb.toString());
				}
				
				// Set content type, username, teamList, divisionList and dispatch to jsp
				request.setAttribute("divList", dbl);
				request.setAttribute("teamList", tbl);
				request.setAttribute("userName", userName);
				response.setContentType("text/html");
				RequestDispatcher rd = request.getRequestDispatcher("admin_teams.jsp");  
		        rd.forward(request, response);
			}
		}
	}
}
