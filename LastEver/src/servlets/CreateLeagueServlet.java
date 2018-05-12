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
 * CreateLeagueServlet class handels HTTP GET/POST requests which allows the admins to create leagues
 * @author Liam Maloney
 */
public class CreateLeagueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /createLeague	
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
				
				// Set content type and username and dispatch to jsp 
				request.setAttribute("userName", userName);
				response.setContentType("text/html");
				RequestDispatcher rd = request.getRequestDispatcher("admin_create_league.jsp");  
				rd.forward(request, response);					
			}
		}
	}
	
	/**
	 * doPost method mapped to /createLeague	
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  Get new division name from jsp input
		String newLeagueName = request.getParameter("newLeagueName");
		String newLeaguePlayoffs = request.getParameter("newLeaguePlayoffs");
		String leaguePlayoffTeams = request.getParameter("leaguePlayoffTeams");
		String newLeagueStatus = request.getParameter("newLeagueStatus");
		
		// If any parameter is null
		if(newLeagueName == null || newLeagueName == "" || newLeaguePlayoffs == null || newLeaguePlayoffs == "" || 
				leaguePlayoffTeams == null || leaguePlayoffTeams == "" || newLeagueStatus == null || newLeagueStatus == "") {
			response.sendRedirect("./createLeague");
			
		} else {
			// If division is created
			LeagueBean league = new LeagueBean();
			league.setLeaguePlayoffs(newLeaguePlayoffs);
			league.setLeagueName(newLeagueName);
			league.setLeaguePlayoffTeams(leaguePlayoffTeams);
			league.setStatus(newLeagueStatus);
			
			if (League.createNewLeague(league)) {
				response.sendRedirect("./adminLeagues");				
			} else {
				response.sendRedirect("./createLeague");
			}
		}
	}
}
