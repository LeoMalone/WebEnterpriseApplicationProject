package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Division;

/**
 * The DeleteDivisionServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option delete division.
 * @author Liam Maloney
 */
public class DeleteDivisionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * doPost method mapped to /deleteDivision	
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		// Get id from url
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		// if query is successful redirect to proper page
		if(Division.deleteDivision(sb.toString())) {
			response.sendRedirect("./adminDivisions?=1");
		} else {
			response.sendRedirect("./editDivision?=" + sb.toString());
		}
	}
}
