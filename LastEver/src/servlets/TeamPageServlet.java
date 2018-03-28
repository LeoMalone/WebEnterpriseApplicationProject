package servlets;

/**
 * The TeamPageServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the team page to show everything that is associated with a team
 * Standings, results, schedule, statistics, team info, team owner, and team division are shown
 * @author Kevin Villemaire
 */
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
import beans.ScheduleResultsBean;
import beans.StandingsBean;
import beans.StatisticsBean;
import beans.TeamBean;
import beans.UserBean;
import dao.League;
import dao.ScheduleResults;
import dao.Standings;
import dao.Statistics;
import dao.TeamPage;

public class TeamPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /team
	 * @param request - request parameters
	 * @param response - response parameters
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//cookie variables
		String userName = null;
		String language = null;

		/****************** COOKIE LOGIC ****************/

		//get the cookie list
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

			//get the current website language
			language = request.getParameter("language");
			//get all the cookies on the website
			Cookie[] theCookies = request.getCookies();

			//loop through the cookies and look for the language cookie
			for (Cookie tempCookie : theCookies) {
				if ("language".equals(tempCookie.getName())) {
					//if the language cookie exists then update the language with the websites language if it exists
					if (language != null)
						tempCookie.setValue(language);
					//add the cookie back to the response headers
					response.addCookie(tempCookie);
					break;
				}
			}

			//get the id from the query string
			String id = request.getParameter("id");
			//get the name of the teams division
			String div = TeamPage.getTeamDivision(id);
			
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
			 * current division of the team to be displayed on the team page
			 */
			Standings.getStandings(div, slb);
			ScheduleResults.getScheduleWithTeam(id, div, srlb);
			ScheduleResults.getResultsWithTeam(id, div, rlb);
			Statistics.getStatisticsWithTeam(id, div, stlb);
			TeamPage.getTeamInfo(id, tlb);
			League.getAllLeagues(llb);
			TeamPage.getTeamOwner(id, ulb);
			
			//sets request attributes
			request.setAttribute("league", llb);
			request.setAttribute("team", tlb);
			request.setAttribute("standings", slb);
			request.setAttribute("schedule", srlb);
			request.setAttribute("statistics", stlb);
			request.setAttribute("results", rlb);
			request.setAttribute("teamowner", ulb);
			request.setAttribute("userName", userName);
			
			//forwards to the team page
			RequestDispatcher rd = request.getRequestDispatcher("/team.jsp?id=" + id);  
			rd.forward(request, response);		
		}
	}

	/**
	 * doPost mapped to /team
	 * TODO: Find a better way to do this since doGet should not be called
	 * @param request - request parameters
	 * @param response - response parameters
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//TODO: Do not call doGet from doPost to set cookies
		//calls doGet to handle cookie logic
		doGet(request, response);
	}
}