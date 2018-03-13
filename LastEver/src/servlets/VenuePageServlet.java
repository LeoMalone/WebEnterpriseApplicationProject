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
import beans.VenueBean;
import dao.Division;
import dao.ScheduleResults;
import dao.Venue;

public class VenuePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
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
			//get the division id from the query string
			String div = request.getParameter("div");
			response.setContentType("text/html");

			//if the query string is not included then default to division id 1
			if(div == null)
				div = "1";

			//bean list variables used to set data on the page
			List<DivisionBean> dlb = new ArrayList<DivisionBean>();
			List<VenueBean> vlb = new ArrayList<VenueBean>();
			List<ScheduleResultsBean> slb = new ArrayList<ScheduleResultsBean>();
						
			//gets all divisions for the nav bar
			Division.getAllDivisions(dlb);
			//get the venue info and schedule
			ScheduleResults.getScheduleWithVenue(id, div, slb);	
			Venue.getVenue(id, vlb);

			//sets request parameters
			request.setAttribute("allDiv", dlb);
			request.setAttribute("venID", id);
			request.setAttribute("divID", div);
			request.setAttribute("venue", vlb);
			request.setAttribute("schedule", slb);
			request.setAttribute("userName", userName);
			
			//forwards to venue page
			RequestDispatcher rd = request.getRequestDispatcher("venue.jsp?id=" + id);  
			rd.forward(request, response);	
		}
	}
	
	/**
	 * doPost mapped to /venue
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
