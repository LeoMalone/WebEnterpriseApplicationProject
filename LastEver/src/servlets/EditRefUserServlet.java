package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DivisionBean;
import beans.RefBean;
import beans.TeamBean;
import dao.AdminTeams;
import dao.Division;
import dao.EditRefUser;


public class EditRefUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		String userName = null;
		String language = null;
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		if (!(request.getSession().getAttribute("signedIn").equals("Referee"))) {
			response.sendRedirect("./index");
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
			
				List<TeamBean> tbl = new ArrayList<TeamBean>();
				List<DivisionBean> dbl = new ArrayList<DivisionBean>();
				StringBuilder sb = new StringBuilder(request.getQueryString());
				sb.deleteCharAt(0);				
				AdminTeams.getAllTeams(sb.toString(), dbl, tbl);
				
				request.setAttribute("currentId", sb.toString());
				request.setAttribute("userName", userName);
				request.setAttribute("divList", dbl);
				request.setAttribute("teamList", tbl);
				
				
				//get id from url and set userBean id
				StringBuilder sbref = new StringBuilder(request.getQueryString());
				sbref.deleteCharAt(0);
				RefBean user = new RefBean();
				user.setId(sbref.toString());
				
				if(EditRefUser.getUserForEdit(user)) {
					request.setAttribute("firstName", user.getFirstName());
				}
				
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher("edit_ref_user.jsp");  
		        rd.forward(request, response);
			}
		}
	}
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		response.setContentType("text/html");
		
		RefBean user = new RefBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		String newUsername = request.getParameter("editUsername");
		String newEmail = request.getParameter("editEmail");
		String newPassword = request.getParameter("editPass");
		String userType = request.getParameter("editRadio");
		
		user.setId(sb.toString());
		user.setUsername(newUsername);
		user.setEmail(newEmail);
		user.setPassword(newPassword);
		user.setUserType(userType);
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		if(EditRefUser.saveChanges(user)) {
			response.sendRedirect("./refUsers");
		}
	}	
}
