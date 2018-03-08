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
import beans.ScheduleResultsBean;
import beans.StandingsBean;
import beans.StatisticsBean;
import beans.TeamBean;
import beans.UserBean;
import dao.Division;
import dao.ScheduleResults;
import dao.Standings;
import dao.Statistics;
import dao.TeamPage;

public class TeamPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String userName = null;
		String language = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username"))
					userName = cookie.getValue();
				else if (cookie.getName().equals("language"))
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

			String id = request.getParameter("id");
			String div = TeamPage.getTeamDivision(id);
			
			response.setContentType("text/html");

			List<StandingsBean> slb = new ArrayList<StandingsBean>();
			List<ScheduleResultsBean> srlb = new ArrayList<ScheduleResultsBean>();
			List<ScheduleResultsBean> rlb = new ArrayList<ScheduleResultsBean>();
			List<TeamBean> tlb = new ArrayList<TeamBean>();
			List<DivisionBean> dlb = new ArrayList<DivisionBean>();
			List<StatisticsBean> stlb = new ArrayList<StatisticsBean>();
			List<UserBean> ulb = new ArrayList<UserBean>();
			
			Standings.getStandings(div, slb);
			ScheduleResults.getScheduleWithTeam(id, div, srlb);
			ScheduleResults.getResultsWithTeam(id, div, rlb);
			Statistics.getStatisticsWithTeam(id, div, stlb);
			TeamPage.getTeamInfo(id, tlb);
			Division.getAllDivisions(dlb);
			TeamPage.getTeamOwner(id, ulb);
			
			request.setAttribute("allDiv", dlb);
			request.setAttribute("team", tlb);
			request.setAttribute("standings", slb);
			request.setAttribute("schedule", srlb);
			request.setAttribute("statistics", stlb);
			request.setAttribute("results", rlb);
			request.setAttribute("teamowner", ulb);
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("/team.jsp?id=" + id);  
			rd.forward(request, response);		
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}