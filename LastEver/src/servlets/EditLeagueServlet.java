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

import beans.LeagueBean;
import dao.League;

/**
 * EditLeagueServlet class
 * @author Liam Maloney
 */
public class EditLeagueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /editLeague
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
			if (language == null) {
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

				// get id from url and set DivisionBean id
				StringBuilder sb = new StringBuilder(request.getQueryString());
				sb.deleteCharAt(0);
				LeagueBean league = new LeagueBean();
				league.setLeagueId(sb.toString());

				// If query is successful
				if (League.getLeagueForEdit(league)) {
					// get league list for an admin to edit divisions
					request.setAttribute("userName", userName);
					request.setAttribute("editLeague", league);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("edit_league.jsp");
					rd.forward(request, response);
				}
			}
		}
	}

	/**
	 * doPost method mapped to /editLeague
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get id from url and add it to divisionBean
		LeagueBean league = new LeagueBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		

		// Get all schedule parameters from jsp inputs
		String newLeagueName = request.getParameter("newLeagueName");
		String newLeaguePlayoffs = request.getParameter("newLeaguePlayoffs");
		String leaguePlayoffTeams = request.getParameter("leaguePlayoffTeams");
		String newLeagueStatus = request.getParameter("newLeagueStatus");

		// If any parameter is null
		if(newLeagueName == null || newLeagueName == "" || newLeaguePlayoffs == null || newLeaguePlayoffs == "" || 
				leaguePlayoffTeams == null || leaguePlayoffTeams == "" || newLeagueStatus == null || newLeagueStatus == "") {
			response.sendRedirect("./editLeague?="+sb.toString());
			
		} else {
			// If division is created
			league.setLeagueId(sb.toString());
			league.setLeaguePlayoffs(newLeaguePlayoffs);
			league.setLeagueName(newLeagueName);
			league.setLeaguePlayoffTeams(leaguePlayoffTeams);
			league.setStatus(newLeagueStatus);
			
			if (League.saveChanges(league)) {
				response.sendRedirect("./adminLeagues");				
			} else {
				response.sendRedirect("./editLeague?="+sb.toString());
			}
		}
	}
}
