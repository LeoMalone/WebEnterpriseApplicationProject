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
import dao.Division;
import dao.EditRefUser;

public class RefHomeServlet extends HttpServlet {

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
					else if (cookie.getName().equals("userType"))
					{
						if (cookie.getValue() != "referee") //if they aren't a referee, don't let them access
							{
								response.sendRedirect("./login");
							}
					}
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
				
				
				
				List<DivisionBean> dbl = new ArrayList<DivisionBean>();
				
				request.setAttribute("userName", userName);
				request.setAttribute("divList", dbl);

				//get id from url and set userBean id
				RefBean user = new RefBean();
				
				if(EditRefUser.getUserForEdit(user)) {
					request.setAttribute("firstName", user.getFirstName());
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("referee.jsp");  
		        rd.forward(request, response);	
			}
		}
	}
}
