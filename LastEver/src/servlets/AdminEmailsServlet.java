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
import dao.AdminEmails;
import dao.League;

/**
 * The AdminUsersServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option View Users.
 * @author Liam Maloney and edited by Kevin Villemaire
 */
public class AdminEmailsServlet extends HttpServlet{
	
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
		if (!(request.getSession().getAttribute("signedIn").equals("Administrator"))) {
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
				List<String> admins = new ArrayList<String>();
				List<String> refs = new ArrayList<String>();
				List<String> tos = new ArrayList<String>();
				
				// If query is successful
				if(AdminEmails.getAllEmails(admins, refs, tos)) {
					// Set content type, username, userList and dispatch to jsp
					request.setAttribute("admins", admins);
					request.setAttribute("refs", refs);
					request.setAttribute("tos", tos);
					request.setAttribute("userName", userName);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("admin_emails.jsp");  
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
			if(AdminEmails.getAllEmailsForPost(allEmails)) {
				emails = new String[allEmails.size()];
				emails = allEmails.toArray(emails);
			}
		}
		else if(fromURL.equals("1")) {
			emails = request.getParameterValues("admins");
		}
		else if(fromURL.equals("2")) {
			emails = request.getParameterValues("refs");
		}
		else if(fromURL.equals("3")) {
			emails = request.getParameterValues("tos");
		}
		
		if(emails == null || emails.length == 0) {
			response.sendRedirect("./adminEmails");
		} else {
			try {
				Desktop.getDesktop().mail(new URI("mailto", String.join(",", emails), null));
			} catch (IOException e) {
				e.printStackTrace();			
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect("./adminEmails");
		}
	}
}