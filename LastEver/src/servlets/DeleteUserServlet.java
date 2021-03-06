package servlets;


import dao.EditUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The DeleteUserServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option delete user.
 * @author Liam Maloney, Kevin Villemaire
 */
public class DeleteUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * doPost method mapped to /deleteUser
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
			if(EditUser.deleteUser(sb.toString())) {
				response.sendRedirect("./adminUsers");
			} else {
				response.sendRedirect("./index");
			}
		}
	}	
}
