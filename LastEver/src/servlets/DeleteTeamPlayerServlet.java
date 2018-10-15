package servlets;


import dao.EditTeamPlayer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The DeleteTeamPlayerServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the team control panel option delete user.
 * @author Kevin Read
 */
public class DeleteTeamPlayerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * doPost method mapped to /deleteTeamUser
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		// Get id from url
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		// if query is successful redirect to proper page
		if(EditTeamPlayer.deletePlayer(sb.toString())) {
			response.sendRedirect("./teamRoster");
		} else {
			response.sendRedirect("./editTeamPlayer?="+sb.toString());
		}
	}	
}
