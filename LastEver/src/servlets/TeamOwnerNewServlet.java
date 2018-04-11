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

import beans.LeagueBean;
import beans.TeamBean;
import dao.League;
import dao.Team;

/**
 * TeamOwnerNewServlet class
 * @author Kevin Read and edited by Kevin Villemaire
 */
public class TeamOwnerNewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /teamOwnerNew
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//cookie attributes		
		String teamNameFromParam = null;
		String userName = null;
		String language = null;

		teamNameFromParam = (String) request.getAttribute("selectTeam");
		if (teamNameFromParam == null) {
			teamNameFromParam = "coolTeam";
		}

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		//if not signed in as team owner redirect to index
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
	 * doPost method mapped to /teamOwnerNew
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		//cookie attributes
		String userName = null;
		String language = null;

		String teamName = request.getParameter("newTeamName");
		String teamAbv = request.getParameter("newTeamAbbr");
		String divRadio = request.getParameter("divRadio");
		String aboutTeam = request.getParameter("aboutTeam");

		//if team not complete then redirect to do again
		if (aboutTeam == null || aboutTeam == "" || teamName == "" || teamName == null || teamAbv == "" || teamAbv == "" || divRadio == null || divRadio == "") {
			response.sendRedirect("teamCreateTeam");
		}
		//if not signed in then do again
		else if (request.getSession().getAttribute("signedIn") == null) {
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
			//get url to either go back to create a team again , or success and go to team owner home page
			String url = null;
			if (teamName == null || teamName == "" || userName == null || userName == "") {
				url = "teamCreateTeam";
			}
			else {
				url = "teamowner";
			}

			//get team info into bean
			TeamBean tb = new TeamBean();
			tb.setTeamName(teamName);
			tb.setTeamAbbreviation(teamAbv);
			tb.setDivisionId(divRadio);

			Team.insertNewTeam(tb, userName, aboutTeam);

			//set content type and attributes and redirect
			response.setContentType("text/html");
			request.setAttribute("teamName", teamName);
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher(url);  
			rd.forward(request, response);	
		}
	}
}
