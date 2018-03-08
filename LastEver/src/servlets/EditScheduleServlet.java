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

public class EditScheduleServlet extends HttpServlet {
	
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
				ScheduleBean schedule = new ScheduleBean();
				schedule.setTitle(sb.toString());
				
				if(AdminSchedule.getScheduleById(schedule)) {
					List<TeamBean> teamList = new ArrayList<TeamBean>();
					if(AdminTeams.teamsForEditSchedule(teamList)) {						
						request.setAttribute("teamList", teamList);
						request.setAttribute("userName", userName);
						request.setAttribute("schedule", schedule);
						RequestDispatcher rd = request.getRequestDispatcher("edit_schedule.jsp");  
				        rd.forward(request, response);	
					}				
				}
			}
		}
	}
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		response.setContentType("text/html");
		
		ScheduleBean schedule = new ScheduleBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		String newDate = request.getParameter("editGameDate");
		String newTime = request.getParameter("editGameTime");		
		String newHomeTeam = request.getParameter("editHomeTeam");
		String newAwayTeam = request.getParameter("editAwayTeam");
		String newHomeScore = request.getParameter("editHomeScore");
		String newAwayScore = request.getParameter("editAwayScore");
		String newGameStatus = request.getParameter("editGameStatus");
		
		if(newDate == null || newTime == null || newHomeTeam == null || newHomeScore == "" || newAwayTeam == null || newAwayScore == "" || newGameStatus == null) {
			response.sendRedirect("./editSchedule?=" + sb.toString());
		} else {
			schedule.setTitle(sb.toString());
			schedule.setStart(newDate);
			schedule.setGameTime(newTime);
			schedule.setHomeTeam(newHomeTeam);
			schedule.setAwayTeam(newAwayTeam);
			schedule.setHomeScore(newHomeScore);
			schedule.setAwayScore(newAwayScore);
			schedule.setGameStatus(newGameStatus);
			
			if(AdminSchedule.updateSchedule(schedule)) {
				response.sendRedirect("./adminSchedule");
			}
		}
	}	
}
