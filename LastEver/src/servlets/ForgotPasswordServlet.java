package servlets;

import beans.LeagueBean;
import beans.UserBean;
import dao.League;
import dao.Login;
import dao.Team;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The LoginServlet class handles the POST from /login to login a user
 * @author Liam Maloney, Kevin Villemaire
 */
public class ForgotPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /login
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {    	
		response.setContentType("text/html");
		String language = null;

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		if (request.getSession().getAttribute("signedIn") != null) {
			response.sendRedirect("./index");
		} else {		
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("language"))
						language = cookie.getValue();
				}
			}
			if(language == null) {
				Cookie cookieLanguage = new Cookie("language", "en");
				cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
				response.addCookie(cookieLanguage);
			}
			else {

				language = request.getParameter("language");
				Cookie[] theCookies = request.getCookies();

				for (Cookie tempCookie : theCookies) {
					if ("language".equals(tempCookie.getName())) {
						if (language != null)
							tempCookie.setValue(language);
						response.addCookie(tempCookie);
						break;
					}
				}			

				RequestDispatcher rd = request.getRequestDispatcher("forgot_password.jsp");
				rd.forward(request, response);	
			}
		}
	}

	/**
	 * doPost method mapped to /login
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		// Set content type and get form data from login.jsp
		response.setContentType("text/html");

		String loginEmail = request.getParameter("loginEmail");
		String language = null;

		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		// Create new userBean
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		UserBean user = new UserBean();
		user.setEmail(loginEmail);

		response.sendRedirect("./login");

		}  
	}
