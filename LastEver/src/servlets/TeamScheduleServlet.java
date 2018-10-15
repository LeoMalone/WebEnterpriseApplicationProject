package servlets;

import beans.*;
import dao.*;

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
 * TeamScheduleServlet class extends HttpServlet for GET/POST requests for the team schedule page
 * to get navbar and session info, and team and division info for statistics, results, and schedules
 * @author Kevin Read, Kevin Villemaire
 */
public class TeamScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /teamSchedule
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//cookie attributes
		String userName = null;
		String language = null;

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


		TeamBean tb = new TeamBean();
		String teamName = TeamScheduleResults.getTeamName(tb, userName);

		//get the name of the team's division
		String id = TeamScheduleResults.getTeamId(tb, teamName);
		String div = TeamPage.getTeamDivision(id);
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();	
		Division.getSpecificDivision(dlb, div);
		request.setAttribute("currDiv", dlb);
		response.setContentType("text/html");

		//bean list variables used to set data on the page
		List<StandingsBean> slb = new ArrayList<StandingsBean>();
		List<ScheduleResultsBean> srlb = new ArrayList<ScheduleResultsBean>();	//Bean used for team schedule
		List<ScheduleResultsBean> rlb = new ArrayList<ScheduleResultsBean>();	//Bean used for team results
		List<TeamBean> tlb = new ArrayList<TeamBean>();
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		List<StatisticsBean> stlb = new ArrayList<StatisticsBean>();
		List<UserBean> ulb = new ArrayList<UserBean>();
		
		/*
		 * Gets ths teams standings, schedule, results, statistics, the team info, the team owner, and the 
		 * current league of the team to be displayed on the team page
		 */
		Standings.getStandings(div, slb);
		ScheduleResults.getScheduleWithTeam(id, div, srlb);
		ScheduleResults.getResultsWithTeam(id, div, rlb);
		Statistics.getStatisticsWithTeam(id, div, stlb);
		TeamPage.getTeamInfo(id, tlb);
		League.getAllLeagues(llb);
		TeamPage.getTeamOwner(id, ulb);

		
		

		//set content type and attributes, and dispatch to jsp
		response.setContentType("text/html");
		request.setAttribute("teamName", teamName);
		request.setAttribute("league", llb);
		request.setAttribute("team", tlb);
		request.setAttribute("standings", slb);
		request.setAttribute("schedule", srlb);
		request.setAttribute("statistics", stlb);
		request.setAttribute("results", rlb);
		request.setAttribute("teamowner", ulb);
		request.setAttribute("userName", userName);
		
		RequestDispatcher rd = request.getRequestDispatcher("team_schedule.jsp");  
		rd.forward(request, response);		

	}

	/**
	 * doPost method mapped to /teamSchedule
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}