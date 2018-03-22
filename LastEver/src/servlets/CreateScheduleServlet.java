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
import beans.ScheduleBean;
import beans.TeamBean;
import dao.AdminSchedule;
import dao.AdminTeams;
import dao.Division;

/**
 * The CreateScheduleServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option Create Schedule.
 * @author Liam Maloney
 */
public class CreateScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /scheduleCreate	
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
				
				// Team list drop drop down picked in jsp.
				List<TeamBean> teamList = new ArrayList<TeamBean>();
				// If query is successful
				if(AdminTeams.teamsForEditSchedule(teamList)) {						
					request.setAttribute("teamList", teamList);
					request.setAttribute("userName", userName);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("admin_create_sched.jsp");  
			        rd.forward(request, response);	
				}				
				
			}
		}
	}
	
	/**
	 * doPost method mapped to /scheduleCreate	
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		// Get all schedule parameters from jsp inputs		
		String newDate = request.getParameter("newGameDate");
		String newTime = request.getParameter("newGameTime");		
		String newHomeTeam = request.getParameter("newHomeTeam");
		String newAwayTeam = request.getParameter("newAwayTeam");
		String newHomeScore = request.getParameter("newHomeScore");
		String newAwayScore = request.getParameter("newAwayScore");
		String newGameStatus = request.getParameter("newGameStatus");
		
		// If any parameter is null
		if(newDate == null || newTime == null || newHomeTeam == null || newHomeScore == "" || newAwayTeam == null || newAwayScore == "" || newGameStatus == null) {
			response.sendRedirect("./scheduleCreate");
		} else {
			//Create Schedule bean and set parameters
			ScheduleBean schedule = new ScheduleBean();	
			schedule.setGameDate(newDate);
			schedule.setGameTime(newTime);
			schedule.setHomeTeam(newHomeTeam);
			schedule.setAwayTeam(newAwayTeam);
			schedule.setHomeScore(newHomeScore);
			schedule.setAwayScore(newAwayScore);
			schedule.setGameStatus(newGameStatus);
			
			// If query is successful
			if(AdminSchedule.createNewGame(schedule)) {
				response.sendRedirect("./adminSchedule");
			}
		}
	}
}
