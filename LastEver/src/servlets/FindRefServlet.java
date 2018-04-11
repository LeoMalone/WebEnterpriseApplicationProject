package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.RefBean;
import dao.FindCreateRef;

/**
 * FindRefServlet classextends the HttpServlet class to handle the POST requests for a referee user to try and
 * find a referee with their account information
 * @author Kevin Villemaire
 *
 */
public class FindRefServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doPost method mapped to /findReferee
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		
		//get the "hidden" refereeID from the form
		String id = request.getParameter("refID");
		RefBean user = new RefBean();

		//get the referee details
		if(FindCreateRef.getReferee(user, id)) {
			//try and to find a referee using their account information
			FindCreateRef.findReferee(user);
		}
		
		response.sendRedirect("./referee");
	}
}
