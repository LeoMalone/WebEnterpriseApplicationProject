package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.CreateAccount;

/**
 * 
 */
public class AdminCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// User Types
	private static final String ADMIN = "Administrator";
	private static final String REF = "Referee";
	private static final String TEAM_OW = "Team Owner";
	
	
	/**
	 * goGet method mapped to /adminCreate
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String userName = null;
		String language = null;
		
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("./login");
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username"))
						userName = cookie.getValue();
					else if (cookie.getName().equals("language"))
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
				
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher("admin_create_user.jsp"); 
		        rd.forward(request, response);
			}
		}
	}
	
	/**
	 * doPost method mapped to /adminCreate
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// set response type and get post data from jsp form
		response.setContentType("text/html");
		String newUsername = request.getParameter("newUsername");
		String newEmail = request.getParameter("newEmail");
		String newPassword = request.getParameter("newPass");
		String userType = request.getParameter("createRadio");

		// If any parameter is null
		if (newUsername == null || newEmail == null || newPassword == null || userType == null) {
			response.sendRedirect("./adminCreate");
			
		} else {	
			// Get user type
			String ut = null;
			if (userType.equals(ADMIN))
				ut = ADMIN;
			if (userType.equals(REF))
				ut = REF;
			if (userType.equals(TEAM_OW))
				ut = TEAM_OW;
	
			// Create new userBean
			UserBean user = new UserBean(newUsername, newEmail, newPassword, ut);
	
			// If createNewUser method returns true
			if(CreateAccount.createNewUser(user)) {
				response.sendRedirect("./adminUsers");
			}
		}
	}
}
