package servlets;

/**
 * The ResultsServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the results page to show the leagues results
 * @author Kevin Villemaire
 */

import beans.LeagueBean;
import beans.ScheduleResultsBean;
import dao.League;
import dao.ScheduleResults;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /results
	 * @param request - request parameters
	 * @param response - response parameters
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//cookie variables
		String userName = null;
		String language = null;
		int page = 1;
		int numResults = 7;

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

			//get the current page that the user is on and validate for a number greater than 0
			if(request.getParameter("page") != null) {
				Pattern p = Pattern.compile("^[1-9][0-9]*$");
				Matcher m1;
				
				m1 = p.matcher(request.getParameter("page"));
				
				//if the query string matches the format then set the page to be the current page of the user
				if(m1.matches()) {
					page = Integer.parseInt(request.getParameter("page"));
				}
			}
			
			//bean list variables used to set data on the page
			List<ScheduleResultsBean> rlb = new ArrayList<ScheduleResultsBean>();
			List<LeagueBean> llb = new ArrayList<LeagueBean>();
			
			// Set leagues for navbar
			League.getAllLeagues(llb);
			request.setAttribute("league", llb);
			
			//get the results from the division
			ScheduleResults.getResults(id, rlb, (page-1)*numResults, numResults);	

			//make a new DivisionBean to get the current league
			llb = new ArrayList<LeagueBean>();	
			
			//get the division that the page corresponds to
			League.getCurrentLeague(id, llb);
			request.setAttribute("currLeague", llb);

			//set request attributes
			request.setAttribute("results", rlb);	
			request.setAttribute("userName", userName);
			request.setAttribute("currPage", page);
			request.setAttribute("totalPages", (int) Math.ceil(ScheduleResults.numberOfResults(id) * 1.0 / numResults));
			
			//forward to results page
			RequestDispatcher rd = request.getRequestDispatcher("/results.jsp?id=" + id);  
			rd.forward(request, response);		
		}
	}

	/**
	 * doPost mapped to /results
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