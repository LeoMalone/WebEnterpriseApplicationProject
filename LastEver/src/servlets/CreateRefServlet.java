package servlets;

import beans.RefBean;
import dao.FindCreateRef;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RefHomeServlet classextends the HttpServlet class to handle the POST requests so that a referee user can create
 * a referee with their information
 * @author Kevin Villemaire
 */
public class CreateRefServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doPost method mapped to /createReferee
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		
		//get the "hidden" refereeID from the form
		String id = request.getParameter("refID");
		RefBean user = new RefBean();

		//get the referee details
		if(FindCreateRef.getReferee(user, id)) {
			//try and to create a referee using their account information
			FindCreateRef.createReferee(user);
		}
		
		response.sendRedirect("./referee");
	}
}
