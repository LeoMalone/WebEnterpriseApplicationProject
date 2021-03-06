package servlets;

import beans.DivisionBean;
import beans.LeagueBean;
import dao.Division;
import dao.League;

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
 * The EditDivisionServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option edit division.
 * @author Liam Maloney, Kevin Villemaire
 */
public class EditDivisionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /editDivision	
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

				//get id from url and set DivisionBean id
				StringBuilder sb = new StringBuilder(request.getQueryString());
				sb.deleteCharAt(0);
				DivisionBean div = new DivisionBean();
				div.setDivisionId(sb.toString());

				// If query is successful
				if(Division.getDivisionForEdit(div)) {
					//get league list for an admin to edit divisions
					List<LeagueBean> lbl = new ArrayList<LeagueBean>();
					if(League.getAllLeagues(lbl)) {
						if(div.getLeageId() == null) {
							request.setAttribute("leagueId", 0);
						} else {
							request.setAttribute("leagueId", div.getDivisionId());
						}
						request.setAttribute("leagues", lbl);
						request.setAttribute("userName", userName);
						request.setAttribute("division", div);						
						response.setContentType("text/html");
						RequestDispatcher rd = request.getRequestDispatcher("edit_division.jsp");  
						rd.forward(request, response);
					}					
				}
			}
		}
	}

	/**
	 * doPost method mapped to /editDivision	
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get id from url and add it to divisionBean
		DivisionBean division = new DivisionBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		division.setDivisionId(sb.toString());

		// Get all schedule parameters from jsp inputs
		String newDivName = request.getParameter("editDivisionName");
		String newLeague = request.getParameter("divRadio");

		// If any parameter is null
		if(newDivName == null || newDivName.equals("") || newLeague.equals("") || newLeague == null) {
			response.sendRedirect("./editDivision?=" + sb.toString());			
		} else {
			// Set DivisionBean parameters
			division.setDivisionName(newDivName);
			division.setLeagueId(newLeague);
			// If query is successful
			if(Division.saveChanges(division)) {
				response.sendRedirect("./adminDivisions");
			} else {
				response.sendRedirect("./editDivision?=" + sb.toString());
			}
		}
	}
}
