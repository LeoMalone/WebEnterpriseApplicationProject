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
import dao.Division;
import dao.League;

/**
 * The CreateDivisionServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option Create Division.
 * @author Liam Maloney, Kevin Villemaire
 */
public class CreateDivisionServlet extends HttpServlet {
	
private static final long serialVersionUID = 7270142539946516086L;
	
	/**
	 * doGet method mapped to /divisionCreate	
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
				
				//get league list for an admin to edit divisions
				List<LeagueBean> lbl = new ArrayList<LeagueBean>();
				if(League.getAllLeagues(lbl)) {
					request.setAttribute("leagues", lbl);
				}
				
				// Set content type and username and dispatch to jsp 
				request.setAttribute("userName", userName);
				response.setContentType("text/html");
				RequestDispatcher rd = request.getRequestDispatcher("admin_create_div.jsp");  
				rd.forward(request, response);					
			}
		}
	}
	
	/**
	 * doPost method mapped to /divisionCreate	
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  Get new division name from jsp input
		String newDivName = request.getParameter("newDivisionName");
		String newDivLeague = request.getParameter("divRadio");
		
		// If any parameter is null
		if(newDivName == null || newDivName == "" || newDivLeague == null) {
			response.sendRedirect("./divisionCreate");
			
		} else {
			// If division is created
			DivisionBean div = new DivisionBean();
			div.setDivisionName(newDivName);
			div.setLeagueId(newDivLeague);
			if (Division.createNewDiv(div)) {
				response.sendRedirect("./adminDivisions");				
			} else {
				response.sendRedirect("./divisionCreate");
			}
		}
	}
}
