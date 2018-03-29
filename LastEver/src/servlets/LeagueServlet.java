package servlets;

/**
 * The LeagueServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the league page to get the news related to the league
 * @author Kevin Villemaire
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LeagueBean;
import beans.NewsBean;
import dao.Division;
import dao.League;

public class LeagueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /league
	 * @param request - request parameters
	 * @param response - response parameters
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//cookie variables
		String userName = null;
		String language = null;
		String newLang = null;
		int page = 1;
		int newsArticles = 5;

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
			//get the current website language and store it in a new variable so that the date doesnt change language
			newLang = request.getParameter("language");
			//get all the cookies on the website
			Cookie[] theCookies = request.getCookies();

			//loop through the cookies and look for the language cookie
			for (Cookie tempCookie : theCookies) {
				if ("language".equals(tempCookie.getName())) {
					//if the language cookie exists then update the language with the websites language if it exists
					if (newLang != null)
						tempCookie.setValue(newLang);
					//if the sites language has not changed
					else
						tempCookie.setValue(language);
				}
				//add the cookie to the response headers
				response.addCookie(tempCookie);
				break;
			}
		}

		//get the id from the query string
		String id = request.getParameter("id");
		response.setContentType("text/html");

		//determines what page the site is on and displays the news from the range
		if(request.getParameter("page") != null) {
			Pattern p = Pattern.compile("^[1-9][0-9]*$");
			Matcher m1;

			m1 = p.matcher((String)request.getParameter("page"));

			if(m1.matches()) {
				page = Integer.parseInt((String)request.getParameter("page"));
			}
		}

		//bean list variables used to set data on the page
		List<NewsBean> nlb = new ArrayList<NewsBean>();
		List<LeagueBean> llb = new ArrayList<LeagueBean>();

		//if the language has changed then get the news with the new language otherwise use the old language
		if(newLang != null)
			League.getNews(id, nlb, newLang, (page-1)*newsArticles, newsArticles);
		else
			League.getNews(id, nlb, language, (page-1)*newsArticles, newsArticles);	

		//gets the divisions for the nav bar
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		//get the division that the page corresponds to
		llb = new ArrayList<LeagueBean>();	
		League.getCurrentLeague(id, llb);

		//set request attributes
		request.setAttribute("currLeague", llb);
		request.setAttribute("news", nlb);	
		request.setAttribute("userName", userName);
		request.setAttribute("currPage", page);
		request.setAttribute("totalPages", (int) Math.ceil(Division.numberOfArticles(id) * 1.0 / newsArticles));
		
		//forward to division page
		RequestDispatcher rd = request.getRequestDispatcher("/league.jsp?id=" + id);  
		rd.forward(request, response);		
	}

	/**
	 * doPost mapped to /division
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