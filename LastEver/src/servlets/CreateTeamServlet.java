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
import beans.LeagueBean;
import beans.TeamBean;
import dao.CreateTeam;
import dao.Division;
import dao.League;

/**
 * The CreateTeamServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option Create team.
 * @author Liam Maloney and edited by Kevin Villemaire
 */
public class CreateTeamServlet extends HttpServlet {

	private static final long serialVersionUID = 7270142539946516086L;
	
	/**
	 * doGet method mapped to /teamCreate	
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tracked Cookie variables
		String userName = null;
		String language = null;
		
		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);
		
		// If User is not signed In redirect to sign in page
		// TODO: distinguish between user types
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("./login");
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
				
				List<DivisionBean> dbl = new ArrayList<DivisionBean>();				
				if(Division.getAllDivisions(dbl)) {
					// Set content type and username and dispatch to jsp
					request.setAttribute("allDiv", dbl);
					request.setAttribute("userName", userName);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("admin_create_team.jsp");  
					rd.forward(request, response);
				}					
			}
		}
	}
	
	/**
	 * doPost method mapped to /teamCreate	
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get all team parameters from jsp inputs
		String newTeamName = request.getParameter("newTeamName");
		String newTeamAbbr = request.getParameter("newTeamAbbr");
		String newTeamAbout = request.getParameter("newTeamAbout");
		String newDiv = request.getParameter("divRadio");
		
		// If any parameter is null
		if(newTeamName == "" || newTeamAbbr == "" || newDiv == null) {
			response.sendRedirect("./teamCreate");
			
		} else {
			//Create Team bean and set parameters
			TeamBean team = new TeamBean();
			team.setTeamName(newTeamName);
			team.setTeamAbbreviation(newTeamAbbr);
			team.setTeamAbout(newTeamAbout);
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
