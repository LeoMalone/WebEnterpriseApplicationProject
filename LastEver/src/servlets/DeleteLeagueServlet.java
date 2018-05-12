package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.League;

/**
 * DeleteLeagueServlet class handles HTTP POST requests to allow an admin to delete a league
 * @author Liam Maloney
 */
public class DeleteLeagueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * doPost method mapped to /deleteLeague
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
			if (League.deleteLeague(sb.toString())) {
				response.sendRedirect("./adminLeagues");
			} else {
				response.sendRedirect("./editLeague?=" + sb.toString());
			}
		}
	}
}
