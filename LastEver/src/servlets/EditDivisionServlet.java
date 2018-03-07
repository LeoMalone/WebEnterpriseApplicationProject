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
import dao.Division;

public class EditDivisionServlet extends HttpServlet {
	
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
				DivisionBean div = new DivisionBean();
				div.setDivisionId(sb.toString());
				
				if(Division.getDivisionForEdit(div)) {
					request.setAttribute("userName", userName);
					request.setAttribute("division", div);
					RequestDispatcher rd = request.getRequestDispatcher("edit_division.jsp");  
			        rd.forward(request, response);					
				}
			}
		}
	}
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//
		response.setContentType("text/html");
		
		DivisionBean division = new DivisionBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		division.setDivisionId(sb.toString());
		
		String newDivName = request.getParameter("editDivisionName");		
		if(newDivName == null || newDivName.equals("")) {
			response.sendRedirect("./editDivision?=" + sb.toString());
			
		} else {
			division.setDivisionName(newDivName);
			if(Division.saveChanges(division)) {
				response.sendRedirect("./adminDivisions");
			} else {
				response.sendRedirect("./editDivision?=" + sb.toString());
			}
		}
	}
}