package servlets;

import beans.LeagueBean;
import beans.TeamBean;
import dao.League;
import dao.Team;

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
 * TeamOwnerSelectServlet class extends HttpServlet for GET/POST requests for the teamowner select page
 * to get navbar and session info, and to insert a team into the usersxteam table
 * @author Kevin Read, Kevin Villemaire
 */
public class TeamOwnerSelectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /teamOwnerSelect
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		//cookie attributes
		String userName = null;
		String language = null;

		String teamNameFromParam = null;
		teamNameFromParam = (String) request.getAttribute("selectTeam");

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		//if not signed in as a team owner, redirect to index
		if (!(request.getSession().getAttribute("signedIn").equals("Team Owner"))) {
			response.sendRedirect("./index");
		} else {
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
			//set content type and attributes and dispatch
			response.setContentType("text/html");
			request.setAttribute("teamName", teamNameFromParam);
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("teamowner");  
			rd.forward(request, response);	

		}
	}

	/**
	 * doPost method mapped to /teamOwnerSelect
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		//cookie attributes
		String userName = null;
		String language = null;

		String teamName = request.getParameter("selectTeam");

		//if not signed in redirect
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("teamCreateTeam");
		} else {
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
			//get url to go to based on team name
			String url = null;
			if (teamName == null || teamName == "" || userName == null || userName == "") {
				url = "teamCreateTeam";
			}
			else {
				url = "teamowner";
				request.getSession().setAttribute("userType", "./teamowner");
			}

			//insert team based on the user
			TeamBean tb = new TeamBean();
			Team.insertTeamByUser(tb, teamName, userName);
			//set attributes and content type and dispatch
			request.setAttribute("teamName", teamName);
			request.setAttribute("userName", userName);
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher(url);  
			rd.forward(request, response);	
		}
	}
}
