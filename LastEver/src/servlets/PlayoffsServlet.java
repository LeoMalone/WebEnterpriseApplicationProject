package servlets;

/**
 * The PlayoffsServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the schedule page to show the status of the leagues playoffs
 * @author Kevin Villemaire
 */

import beans.LeagueBean;
import beans.ScheduleResultsBean;
import beans.StatisticsBean;
import dao.League;
import dao.ScheduleResults;
import dao.Statistics;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayoffsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /playoffs
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
			response.setContentType("text/html");

			//bean list variables used to set data on the page
			List<ScheduleResultsBean> slb = new ArrayList<ScheduleResultsBean>();
			List<LeagueBean> llb = new ArrayList<LeagueBean>();
			List<StatisticsBean> stlb = new ArrayList<StatisticsBean>();
			
			// Set leagues for navbar
			League.getAllLeagues(llb);
			request.setAttribute("league", llb);
			
			//get the schedule from the division
			ScheduleResults.getPlayoffSchedule(id, slb);	
			Statistics.getPlayoffStatistics(id, stlb);

			//make a new LeagueBean to get the current league
			llb = new ArrayList<LeagueBean>();	
			
			//get the league that the page corresponds to
			League.getCurrentLeague(id, llb);
			request.setAttribute("currLeague", llb);

			//set request attributes
			request.setAttribute("schedule", slb);	
			request.setAttribute("userName", userName);
			request.setAttribute("statistics", stlb);	
			
			//forward to playoffs page
			RequestDispatcher rd = request.getRequestDispatcher("/playoffs.jsp?id=" + id);  
			rd.forward(request, response);		
		}
	}

	/**
	 * doPost mapped to /playoffs
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