package servlets;

import beans.*;
import dao.*;

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
 * The EditScheduleServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option edit Schedule.
 * @author Liam Maloney, Kevin Villemaire
 */
public class EditScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /editSchedule	
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
			
				//get id from url and set userBean id
				StringBuilder sb = new StringBuilder(request.getQueryString());
				sb.deleteCharAt(0);
				ScheduleBean schedule = new ScheduleBean();
				schedule.setTitle(sb.toString());
				
				// If query is successful
				if(AdminSchedule.getScheduleById(schedule)) {
					List<TeamBean> teamList = new ArrayList<TeamBean>();
					List<RefBean> refList = new ArrayList<RefBean>();
					List<VenueBean> venueList = new ArrayList<VenueBean>();
					
					// If query is successful
					if(AdminTeams.teamsForEditSchedule(teamList) && AdminReferees.getAllReferees(refList)
							&& AdminVenues.getAllVenues(venueList)) {						
						request.setAttribute("teamList", teamList);
						request.setAttribute("userName", userName);
						request.setAttribute("schedule", schedule);
						request.setAttribute("referee", refList);
						request.setAttribute("venue", venueList);
						response.setContentType("text/html");
						RequestDispatcher rd = request.getRequestDispatcher("edit_schedule.jsp");  
				        rd.forward(request, response);	
					}				
				}
			}
		}
	}
	
	/**
	 * doPost method mapped to /editSchedule	
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get id from url and add it to ScheduleBean
		ScheduleBean schedule = new ScheduleBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		// Get all schedule parameters from jsp inputs
		String newDate = request.getParameter("editGameDate");
		String newTime = request.getParameter("editGameTime");		
		String newHomeTeam = request.getParameter("editHomeTeam");
		String newAwayTeam = request.getParameter("editAwayTeam");
		String newHomeScore = request.getParameter("editHomeScore");
		String newAwayScore = request.getParameter("editAwayScore");
		String newGameStatus = request.getParameter("editGameStatus");
		String newReferee = request.getParameter("newReferee");
		String newVenue = request.getParameter("newVenue");
		
		// If any parameter is null
		if(newDate == null || newTime == null || newHomeTeam == null || newHomeScore == "" || newAwayTeam == null 
				|| newAwayScore == "" || newGameStatus == null || newHomeTeam == newAwayTeam || newReferee == null
				|| newVenue == null || newReferee.equals("0") || newVenue.equals("0")) {
			response.sendRedirect("./editSchedule?=" + sb.toString());
		} else {
			// Set ScheduleBean parameters
			schedule.setTitle(sb.toString());
			schedule.setGameDate(newDate);
			schedule.setGameTime(newTime);
			schedule.setHomeTeam(newHomeTeam);
			schedule.setAwayTeam(newAwayTeam);
			schedule.setHomeScore(newHomeScore);
			schedule.setAwayScore(newAwayScore);
			schedule.setGameStatus(newGameStatus);
			schedule.setReferee(newReferee);
			schedule.setVenue(newVenue);
			
			// If query is successful
			if(AdminSchedule.updateSchedule(schedule)) {
				response.sendRedirect("./adminSchedule");
			}
			else {
				response.sendRedirect("./editSchedule?=" + sb.toString());
			}
		}
	}	
}
