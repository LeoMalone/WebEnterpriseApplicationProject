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
import beans.ScheduleResultsBean;
import beans.TeamBean;
import dao.Division;
import dao.League;
import dao.TeamScheduleResults;

/**
 * TeamScheduleServlet class
 * @author Kevin Read and edited by Kevin Villemaire
 */
public class TeamScheduleServlet extends HttpServlet {

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

			
			response.setContentType("text/html");

			List<ScheduleResultsBean> slb = new ArrayList<ScheduleResultsBean>();
			String divID = TeamScheduleResults.getSchedule(slb, userName);	

			// Set leagues for navbar
			List<LeagueBean> llb = new ArrayList<LeagueBean>();
			League.getAllLeagues(llb);
			request.setAttribute("league", llb);;
			
			List<DivisionBean> dlb = new ArrayList<DivisionBean>();	
			Division.getSpecificDivision(dlb, divID);
			request.setAttribute("currDiv", dlb);
			
			TeamBean tb = new TeamBean();
			String teamName = TeamScheduleResults.getTeamName(tb, userName);
			
			request.setAttribute("teamName", teamName);
			request.setAttribute("schedule", slb);	
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("team_schedule.jsp");  
			rd.forward(request, response);		
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}