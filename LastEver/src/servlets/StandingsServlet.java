package servlets;

/**
 * The StandingsServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the standings page to get the divisions standings
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

import beans.DivisionBean;
import beans.StandingsBean;
import dao.Division;
import dao.Standings;

public class StandingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /standings
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
			List<StandingsBean> slb = new ArrayList<StandingsBean>();
			List<DivisionBean> dlb = new ArrayList<DivisionBean>();
			
			//get the current standings in the division
			Standings.getStandings(id, slb);	
			
			//gets all divisions for the navbar
			Division.getAllDivisions(dlb);
			request.setAttribute("allDiv", dlb);

			//get the division that the page corresponds to
			dlb = new ArrayList<DivisionBean>();	
			Division.getSpecificDivision(dlb, id);
			
			//set request attributes
			request.setAttribute("currDiv", dlb);
			request.setAttribute("standings", slb);	
			request.setAttribute("userName", userName);
			
			//forward to the standings page
			RequestDispatcher rd = request.getRequestDispatcher("/standings.jsp?id=" + id);  
			rd.forward(request, response);		
		}
	}

	/**
	 * doPost mapped to /standings
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