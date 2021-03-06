package servlets;

import beans.DivisionBean;
import beans.LeagueBean;
import beans.RefBean;
import beans.TeamBean;
import dao.AdminTeams;
import dao.EditRefUser;
import dao.League;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * RefUsersServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the referee to get a list of other referee users
 * @author Kevin Read, Kevin Villemaire, Liam Maloney, Neal Sen
 */
public class RefUsersServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /refUsers
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		String userName = null;
		String language = null;
		
		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);
		
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
				
				//get id from url and set RefBean id
				StringBuilder sbref = new StringBuilder(URLDecoder.decode(request.getQueryString(), "UTF-8"));
				sbref.deleteCharAt(0);
				RefBean user = new RefBean();
				user.setId(sbref.toString());
				
				if(EditRefUser.getUserForEdit(user, userName)) {
					request.setAttribute("refUser", user);
				}
				
				request.setAttribute("userName", userName);
				RequestDispatcher rd = request.getRequestDispatcher("edit_ref_user.jsp");  
		        rd.forward(request, response);
		        
			}
		}
	}
	
	/**
	 * doPost method mapped to /refUsers
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}
