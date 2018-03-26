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

/**
 * The EditTeamServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option edit Team.
 * @author Liam Maloney
 */
public class EditTeamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /editTeam	
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tracked Cookie variables
		String userName = null;
		String language = null;
		
		// Set divisions for navbar
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		// If User is not signed In redirect to sign in page
		if (!(request.getSession().getAttribute("signedIn").equals("Administrator"))) {
			response.sendRedirect("./index");
		} else {
			// If user is signed in, get language and username
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username"))
						userName = cookie.getValue();
					else if (cookie.getName().equals("language"))
						language = cookie.getValue();
				}
			}
			// If Language is null, set default language to en
			if(language == null) {
				Cookie cookieLanguage = new Cookie("language", "en");
				cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
				response.addCookie(cookieLanguage);
			}
			// Set cookie language for users
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
				
				// If query is successful
				if(EditTeam.getTeamForEdit(team)) {
					request.setAttribute("userName", userName);
					request.setAttribute("team", team);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("edit_team.jsp");  
			        rd.forward(request, response);					
				}
			}
		}
	}
	
	/**
	 * doPost method mapped to /editTeam	
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get id from url and add it to TeamBean
		TeamBean team = new TeamBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		// Get all schedule parameters from jsp inputs
		String newName = request.getParameter("editTeamName");
		String newAbbr = request.getParameter("editTeamAbbr");
		String newDiv = request.getParameter("divRadio");
		
		// If any parameter is null
		if(newName == null || newAbbr == null || newDiv == null) {
			response.sendRedirect("./adminTeams?=" + sb.toString());
		} else {
			// Set TeamBean parameters
			team.setTeamId(sb.toString());
			team.setTeamName(newName);
			team.setTeamAbbreviation(newAbbr);
			team.setDivisionId(newDiv);
			
			// If query is successful
			if(EditTeam.saveChanges(team)) {
				response.sendRedirect("./adminTeams");
			}
		}
	}	
}
