package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.ActivateUser;
import dao.EmailActivation;


/**
 * The ActivateEmailServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option to activate a user
 * @author Kevin Villemaire
 */
public class ActivateEmailServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /activate
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get id from url and set userBean id
		String userID = request.getParameter("id");
		String token = request.getParameter("token");

		UserBean user = new UserBean();
		user.setId(userID);
		// Try to activate user, if unsuccessful keep the activation code in database
		ActivateUser.activate(user, token);
		
		response.sendRedirect("./login");
	}
}