package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import beans.ScheduleBean;
import dao.AdminSchedule;

/**
 * The CalendarJsonServlet class extends the HttpServlet class to handle the GET requests for
 * the calendar in the admin_achedule.jsp.
 * @author Liam Maloney
 */
public class CalendarJsonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/*
	 * doGet method mapped to /calendarJson
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// if user does not have Admin privileges redirect
		if (!(request.getSession().getAttribute("signedIn").equals("Administrator"))) {
			response.sendRedirect("./index");
		}
		
		// List of Schedule beans to pass to FullCallendar
		List<ScheduleBean> sbl = new ArrayList<ScheduleBean>();
		
		// If query is successful
		if(AdminSchedule.getSchedule(sbl)) {
			// Set content type to json and convert the list using Gson
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(new Gson().toJson(sbl));
		}		
	}
}
