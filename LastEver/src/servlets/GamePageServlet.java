package servlets;

/**
 * The GamePageServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the gamePage to show more detailed game statistics
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
import dao.GameResult;
import dao.League;

public class GamePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /gamePage
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

			//set the response type
			response.setContentType("text/html");
			
			//get current gameID
			String id = request.getParameter("id");

			//bean list variables used to set data on the page
			List<LeagueBean> llb = new ArrayList<LeagueBean>();
			List<ScheduleResultsBean> srb = new ArrayList<ScheduleResultsBean>();

			GameResult.getResult(id, srb);
			
			// Set leagues for navbar
			League.getAllLeagues(llb);
			request.setAttribute("league", llb);

			//sets request attributes
			request.setAttribute("league", llb);	
			request.setAttribute("userName", userName);
			request.setAttribute("result", srb);

			//forwards to the index page
			RequestDispatcher rd = request.getRequestDispatcher("/gamePage.jsp");  
			rd.forward(request, response);		

		}
	}

	/**
	 * doPost mapped to /gamePage
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