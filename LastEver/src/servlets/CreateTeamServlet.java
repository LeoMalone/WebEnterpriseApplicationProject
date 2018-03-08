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
import dao.CreateTeam;
import dao.Division;

public class CreateTeamServlet extends HttpServlet {

	private static final long serialVersionUID = 7270142539946516086L;
	
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
			
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher("admin_create_team.jsp");  
				rd.forward(request, response);					
			}
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// set response type and get post data from jsp form
		response.setContentType("text/html");
		
		String newTeamName	 = request.getParameter("newTeamName");
		String newTeamAbbr = request.getParameter("newTeamAbbr");
		String newDiv = request.getParameter("divRadio");
		
		// If any parameter is null
		if(newTeamName == null || newTeamAbbr == null || newDiv == null) {
			response.sendRedirect("./teamCreate");
			
		} else {
			TeamBean team = new TeamBean();
			team.setTeamName(newTeamName);
			team.setTeamAbbreviation(newTeamAbbr);
			team.setDivisionId(newDiv);

			// If createNewUser method returns true
			if (CreateTeam.createNewTeam(team)) {
				response.sendRedirect("./adminTeams?=" + team.getDivisionId());
				
			} else {
				response.sendRedirect("./teamCreate");
			}
		}
	}
}
