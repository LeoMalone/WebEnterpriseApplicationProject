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
import beans.TeamBean;
import dao.TeamCreateTeam;
import dao.Team;
import dao.Division;

public class TeamCreateTeamServlet extends HttpServlet {

	private static final long serialVersionUID = 7270142539946516086L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");


		String language = null;
		String userName = null;
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);

		List<TeamBean> tb = new ArrayList<TeamBean>();
		Team.getAllTeams(tb);
		request.setAttribute("allTeam", tb);

		if (!(request.getSession().getAttribute("signedIn").equals("Team Owner"))) {
			response.sendRedirect("./index");
		} else {

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

				request.setAttribute("userName", userName);
				if (Team.hasTeam(userName)) {
					response.sendRedirect("teamowner");
				}
				else {
				RequestDispatcher rd = request.getRequestDispatcher("team_create_team.jsp");  
				rd.forward(request, response);
				}
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// set response type and get post data from jsp form
		response.setContentType("text/html");

		String userName = request.getParameter("userName");
		String newTeamName	 = request.getParameter("newTeamName");
		String newTeamAbbr = request.getParameter("newTeamAbbr");
		String newDiv = request.getParameter("divRadio");
		String language = null;
		String teamName = request.getParameter("selectTeam");
		
		if (Team.hasTeam(userName)) {
			response.sendRedirect("login");
		}
		
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("teamCreateTeam");
		} else {

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
						response.sendRedirect("teamowner");

					} else {
						response.sendRedirect("teamCreateTeam");
					}
				}
			}
		}
	}
}
