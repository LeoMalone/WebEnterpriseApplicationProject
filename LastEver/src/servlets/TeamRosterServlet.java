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
import beans.PlayerBean;
import beans.TeamBean;
import dao.Division;
import dao.EditTeamUser;
import dao.TeamScheduleResults;

public class TeamRosterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		String userName = null;
		String language = null;
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
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

				
				String teamId = EditTeamUser.getTeamForEdit(userName);
				List<PlayerBean> pb = new ArrayList<PlayerBean>();
				EditTeamUser.getPlayersFromRoster(pb, teamId);
				
				TeamBean tb = new TeamBean();
				String teamName = TeamScheduleResults.getTeamName(tb, userName);
				
				request.setAttribute("teamName", teamName);
				request.setAttribute("teamId", teamId);
				request.setAttribute("userName", userName);
				request.setAttribute("divList", dlb);
				request.setAttribute("playerList", pb);

				RequestDispatcher rd = request.getRequestDispatcher("team_roster.jsp");  
		        rd.forward(request, response);	
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}
