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
import dao.EditTeam;

public class EditTeamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		String userName = null;
		String language = null;
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("./login");
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
			
				//get id from url and set userBean id
				StringBuilder sb = new StringBuilder(request.getQueryString());
				sb.deleteCharAt(0);
				TeamBean team = new TeamBean();
				team.setTeamId(sb.toString());
				
				if(EditTeam.getTeamForEdit(team)) {
					request.setAttribute("userName", userName);
					request.setAttribute("team", team);
					RequestDispatcher rd = request.getRequestDispatcher("edit_team.jsp");  
			        rd.forward(request, response);					
				}
			}
		}
	}
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		response.setContentType("text/html");
		
		TeamBean team = new TeamBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		String newName = request.getParameter("editTeamName");
		String newAbbr = request.getParameter("editTeamAbbr");
		String newDiv = request.getParameter("divRadio");
		
		if(newName == null || newAbbr == null || newDiv == null) {
			response.sendRedirect("./adminTeams?=" + sb.toString());
		} else {
			team.setTeamId(sb.toString());
			team.setTeamName(newName);
			team.setTeamAbbreviation(newAbbr);
			team.setDivisionId(newDiv);
			
			List<DivisionBean> dlb = new ArrayList<DivisionBean>();
			Division.getAllDivisions(dlb);
			request.setAttribute("allDiv", dlb);
			
			if(EditTeam.saveChanges(team)) {
				response.sendRedirect("./adminTeams");
			}
		}
	}	
}
