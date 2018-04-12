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
import javax.servlet.http.HttpSession;

import beans.DivisionBean;
import beans.LeagueBean;
import beans.TeamBean;
import dao.Division;
import dao.League;
import dao.Team;
import dao.TeamCreateTeam;

/**
 * TeamCreateTeamServlet class extends HttpServlet for GET/POST requests for the team create team page
 * to get navbar and session info, and to determine if the signed in user is already associated with
 * a team
 * @author Kevin Read and edited by Kevin Villemaire
 */
public class TeamCreateTeamServlet extends HttpServlet {

	private static final long serialVersionUID = 7270142539946516086L;

	/**
	 * doGet method mapped to /teamCreateTeam
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		// tracked cookie attributes
		String language = null;
		String userName = null;

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		//get division list for a team to join
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);

		List<TeamBean> tb = new ArrayList<TeamBean>();
		Team.getAllTeams(tb);
		request.setAttribute("allTeam", tb);

		// If User is not signed In redirect to sign in page
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
			//set content type and attribute
			response.setContentType("text/html");
			request.setAttribute("userName", userName);
			//redirect or dispatch based on if user is already associated with a team
			if (Team.hasTeam(userName)) {

				response.sendRedirect("teamowner");
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("team_create_team.jsp");  
				rd.forward(request, response);
			}
		}
	}

	/**
	 * doPost method mapped to /teamCreateTeam
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// set response type and get post data from jsp form
		response.setContentType("text/html");

		String userName = request.getParameter("userName");
		String newTeamName	 = request.getParameter("newTeamName");
		String newTeamAbbr = request.getParameter("newTeamAbbr");
		String newDiv = request.getParameter("divRadio");
		String language = null;
		//String teamName = request.getParameter("selectTeam");

		//if they have a team already redirect to their homepage
		if (Team.hasTeam(userName)) {
			response.sendRedirect("login");
		}
		// If User is not signed In redirect to sign in page
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

				// If any parameter is null
				if(newTeamName == null || newTeamAbbr == null || newDiv == null || newTeamName == "" || newTeamAbbr == "" || newDiv == "") {
					response.sendRedirect("teamCreateTeam");

				} else {
					TeamBean team = new TeamBean();
					team.setTeamName(newTeamName);
					team.setTeamAbbreviation(newTeamAbbr);
					team.setDivisionId(newDiv);

					// If createNewUser method returns true
					if (TeamCreateTeam.createNewTeam(team, userName)) {
						HttpSession session = request.getSession(false); //false means: don't create if it doesn't exist
						session.setAttribute("userType", "./teamowner");
						response.sendRedirect("teamowner");
					} else {
						response.sendRedirect("teamCreateTeam");
					}
				}
			}
		}
	}
}
