package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DivisionBean;
import dao.Division;

/**
 * The LogoutServlet class handles the POST data from /logout to logout a user
 */
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doPost method mapped to /logout
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		// invalidate current session if it exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		RequestDispatcher rd = request.getRequestDispatcher("./index");
        rd.forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		// invalidate current session if it exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		RequestDispatcher rd = request.getRequestDispatcher("./index");
        rd.forward(request, response);
	}
}
