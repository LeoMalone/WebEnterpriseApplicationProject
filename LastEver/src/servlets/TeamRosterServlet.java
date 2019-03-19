package servlets;

import beans.LeagueBean;
import beans.PlayerBean;
import beans.TeamBean;
import dao.EditTeamUser;
import dao.League;
import dao.TeamScheduleResults;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TeamRosterServlet class extends HttpServlet for GET/POST requests for the team roster page
 * to get navbar and session info, and team roster info
 * @author Kevin Read, Kevin Villemaire
 */
public class TeamRosterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /teamRoster
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		

		String userName = null;
		String language = null;

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		// if not right signin type redirect to index
		if (!(request.getSession().getAttribute("signedIn").equals("Team Owner"))) {
			response.sendRedirect("./index");
		} else {
			//get cookies
			Cookie[] cookies = request.getCookies();
			//if there are cookies then set userName and language to the cookie values if they exist
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username"))
						userName = cookie.getValue();
					else if (cookie.getName().equals("language"))
						language = cookie.getValue();
				}
			}
			//if there is no cookies and the language has not been set then create the cookie with English as default language
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

			}
			
			
			String teamId = EditTeamUser.getTeamForEdit(userName);
			List<PlayerBean> pb = new ArrayList<PlayerBean>();
			EditTeamUser.getPlayersFromRoster(pb, teamId);

			TeamBean tb = new TeamBean();
			String teamName = TeamScheduleResults.getTeamName(tb, userName);

			//set attributes and content type and dispatch to jsp
			request.setAttribute("teamName", teamName);
			request.setAttribute("teamId", teamId);
			request.setAttribute("userName", userName);
			request.setAttribute("playerList", pb);
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("team_roster.jsp");  
			rd.forward(request, response);	

		}
	}

	/**
	 * doPost method mapped to /teamRoster
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}
