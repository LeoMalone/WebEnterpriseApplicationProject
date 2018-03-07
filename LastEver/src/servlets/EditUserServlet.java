package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DivisionBean;
import beans.UserBean;
import dao.Division;
import dao.EditUser;


public class EditUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		String userName = null;
		String language = null;
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
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
			
				//get id from url and set userBean id
				StringBuilder sb = new StringBuilder(request.getQueryString());
				sb.deleteCharAt(0);
				UserBean user = new UserBean();
				user.setId(sb.toString());
				
				if(EditUser.getUserForEdit(user)) {
					request.setAttribute("user", user);
				}
				
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher("edit_user.jsp");  
		        rd.forward(request, response);
			}
		}
	}
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		response.setContentType("text/html");
		
		UserBean user = new UserBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		String newFirstName = request.getParameter("editFirstName");
		String newLastName = request.getParameter("editLastName");
		String newUsername = request.getParameter("editUsername");
		String newEmail = request.getParameter("editEmail");
		String newPassword = request.getParameter("editPass");
		String userType = request.getParameter("editRadio");
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		if(newFirstName == null || newLastName == null || newUsername == null || newEmail == null || newPassword == null || userType == null) {
			response.sendRedirect("./adminUsers");
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user.setAccountUpdated(timestamp);
			user.setFirstName(newFirstName);
			user.setLastName(newLastName);
			user.setId(sb.toString());
			user.setUsername(newUsername);
			user.setEmail(newEmail);
			user.setPassword(newPassword);
			user.setUserType(userType);
			
			if(EditUser.saveChanges(user)) {
				response.sendRedirect("./adminUsers");
			}
		}
	}	
}
