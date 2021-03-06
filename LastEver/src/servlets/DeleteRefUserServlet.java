package servlets;

import beans.DivisionBean;
import dao.Division;
import dao.EditRefUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DeleteRefUserServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the deleting referee users.
 * @author Kevin Read
 */
public class DeleteRefUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doPost method mapped to /deleteRefUser
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		// get query string
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		// set content type and attributes and redirect to right page
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		response.setContentType("text/html");
		if(EditRefUser.deleteRefUser(Integer.parseInt(sb.toString()))) {
			response.sendRedirect("./refUsers");
		}
		else
			response.sendRedirect("./referee");
	}	
}
