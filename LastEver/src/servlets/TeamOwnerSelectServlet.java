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
import dao.Division;
import dao.Team;

public class TeamOwnerSelectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		String teamNameFromParam = null;
		String userName = null;
		String language = null;
		
		teamNameFromParam = (String) request.getAttribute("selectTeam");
		if (teamNameFromParam == null) {
			teamNameFromParam = "coolTeam";
		}
		
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

				
				request.setAttribute("teamName", teamNameFromParam);
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher("teamowner");  
		        rd.forward(request, response);	
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		response.setContentType("text/html");
		
		String userName = null;
		String language = null;
		
		String teamName = request.getParameter("selectTeam");
		
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

				String url = null;
				if (teamName == null || teamName == "" || userName == null || userName == "") {
					url = "teamCreateTeam";
				}
				else {
					url = "teamowner";
				}
				
				TeamBean tb = new TeamBean();
				Team.insertTeamByUser(tb, teamName, userName);
				request.setAttribute("teamName", teamName);
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher(url);  
		        rd.forward(request, response);	
			}
		}
	}
}
