package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminSchedule;

/**
 * The DeleteScheduleServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option delete Schedule.
 * @author Liam Maloney
 */
public class DeleteScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doPost method mapped to /deleteSchedule	
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// If Post is not from logged in Admin
		if (!(request.getSession().getAttribute("signedIn").equals("Administrator"))) {
			response.sendRedirect("./index");
		} else {			
			// Get id from url
			StringBuilder sb = new StringBuilder(request.getQueryString());
			sb.deleteCharAt(0);
			
			// if query is successful redirect to proper page
			if(AdminSchedule.deleteSchedule(sb.toString())) {
				response.sendRedirect("./adminSchedule");
			} else {
				response.sendRedirect("./editSchedule?=" + sb.toString());
			}			
		}
	}
}
