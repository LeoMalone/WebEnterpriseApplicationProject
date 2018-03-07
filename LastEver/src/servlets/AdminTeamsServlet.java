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
import beans.TeamBean;
import dao.AdminTeams;
import dao.Division;

public class AdminTeamsServlet extends HttpServlet {

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
				
				List<TeamBean> tbl = new ArrayList<TeamBean>();
				List<DivisionBean> dbl = new ArrayList<DivisionBean>();
				if(request.getQueryString() == null) {
					AdminTeams.getAllTeams(null, dbl, tbl);
				} else {
					StringBuilder sb = new StringBuilder(request.getQueryString());
					sb.deleteCharAt(0);
					AdminTeams.getAllTeams(sb.toString(), dbl, tbl);
					request.setAttribute("currentId", sb.toString());
				}				
				
				request.setAttribute("divList", dbl);
				request.setAttribute("teamList", tbl);
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher("admin_teams.jsp");  
		        rd.forward(request, response);
			}
		}
	}
}
