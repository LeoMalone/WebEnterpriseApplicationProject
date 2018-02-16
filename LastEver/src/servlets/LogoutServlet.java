package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		// invalidate the session if exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
	}
}
