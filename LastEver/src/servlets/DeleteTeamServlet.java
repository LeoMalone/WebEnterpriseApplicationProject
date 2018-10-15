package servlets;

import dao.EditTeam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The DeleteTeamServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option delete team.
 * @author Liam Maloney
 */
public class DeleteTeamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * doPost method mapped to /deleteTeam	
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// If Post is not from logged in Admin
		if (!(request.getSession().getAttribute("signedIn").equals("Administrator"))) {
			response.sendRedirect("./index");
		} else {
			StringBuilder sb = new StringBuilder(request.getQueryString());
			sb.deleteCharAt(0);
			
			// if query is successful redirect to proper page
			if(EditTeam.deleteTeam(sb.toString())) {
				response.sendRedirect("./adminTeams?=1");
			} else {
				response.sendRedirect("./editTeam?=" + sb.toString());
			}
		}
	}
}
