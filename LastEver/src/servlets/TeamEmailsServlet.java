package servlets;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LeagueBean;
import beans.UserBean;
import dao.League;
import dao.TeamEmails;

/**
 * The TeamEmailsServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the team control panel option send emails.
 * @author Liam Maloney and edited by Kevin Villemaire, moved to Team by Kevin Read
 */
public class TeamEmailsServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /adminUsers	
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Tracked Cookie variables
		String userName = null;
		String language = null;
		
		// Set leagues for navbar
		List<LeagueBean> llb = new ArrayList<LeagueBean>();
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);
		
		// If User is not signed In redirect to sign in page
		if (!(request.getSession().getAttribute("signedIn").equals("Team Owner"))) {
			response.sendRedirect("./index");
		} else {
			// If user is signed in, get language and username
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username"))
						userName = cookie.getValue();
					else if (cookie.getName().equals("language"))
						language = cookie.getValue();
				}
			}
			// If Language is null, set default language to en
			if(language == null) {
				Cookie cookieLanguage = new Cookie("language", "en");
				cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
				response.addCookie(cookieLanguage);
			}
			// Set cookie language for users
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
			
				// User list for display on page
				List<UserBean> admins = new ArrayList<UserBean>();
				List<UserBean> tos = new ArrayList<UserBean>();
				List<UserBean> player = new ArrayList<UserBean>();
				
				// If query is successful
				if(TeamEmails.getAllEmails(admins, tos)) {
					// Set content type, username, userList and dispatch to jsp
					request.setAttribute("admins", admins);
					request.setAttribute("tos", tos);
					request.setAttribute("userName", userName);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("team_emails.jsp");  
				    rd.forward(request, response);
				}
			}
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fromURL = null;
		String[] emails = null;
		
		if(request.getQueryString() != null) {
			StringBuilder sb = new StringBuilder(request.getQueryString());
			sb.deleteCharAt(0);
			fromURL = sb.toString();
		}
		
		if(fromURL == null) {
			List<String> allEmails = new ArrayList<String>();
			if(TeamEmails.getAllEmailsForPost(allEmails)) {
				emails = new String[allEmails.size()];
				emails = allEmails.toArray(emails);
			}
		}
		else if(fromURL.equals("1")) {
			emails = request.getParameterValues("admins");
		}
		else if(fromURL.equals("2")) {
			emails = request.getParameterValues("tos");
		}
		
		if(emails == null || emails.length == 0) {
			response.sendRedirect("./teamEmails");
		} else {
			try {
				Desktop.getDesktop().mail(new URI("mailto", String.join(",", emails), null));
			} catch (IOException e) {
				e.printStackTrace();			
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect("./teamEmails");
		}
	}
}
