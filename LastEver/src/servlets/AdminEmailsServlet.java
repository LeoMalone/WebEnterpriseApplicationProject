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
import dao.AdminEmails;
import dao.League;

/**
 * The AdminEmailsServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option Email Users.
 * @author Liam Maloney, Kevin Villemaire
 */
public class AdminEmailsServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet method mapped to /adminEmails	
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
			
				// Lists of User Beans for emails by user type 
				List<UserBean> admins = new ArrayList<UserBean>();
				List<UserBean> refs = new ArrayList<UserBean>();
				List<UserBean> tos = new ArrayList<UserBean>();
				
				// If query is successful
				if(AdminEmails.getAllEmails(admins, refs, tos)) {
					// Add lists to jsp
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
	
	/**
	 * doPost method mapped to /adminEmails	
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fromURL = null;		//data from string, can stay null
		String[] emails = null;		//Array of string for emails
		
		// If the url contains data set it to fromURL
		if(request.getQueryString() != null) {
			StringBuilder sb = new StringBuilder(request.getQueryString());
			sb.deleteCharAt(0);
			fromURL = sb.toString();
		}
		
		// if the url does not contain data send to all available emails
		if(fromURL == null) {
			List<String> allEmails = new ArrayList<String>();
			if(AdminEmails.getAllEmailsForPost(allEmails)) {
				emails = new String[allEmails.size()];
				emails = allEmails.toArray(emails);
			}
		}
		// if the email is to admins
		else if(fromURL.equals("1")) {
			emails = request.getParameterValues("admins");
		}
		// if the email is to Referees
		else if(fromURL.equals("2")) {
			emails = request.getParameterValues("refs");
		}
		//if the email is to Team Owners
		else if(fromURL.equals("3")) {
			emails = request.getParameterValues("tos");
		}
		
		// if the email array is null or has a length of 0, redirect to /adminEmails
		if(emails == null || emails.length == 0) {
			response.sendRedirect("./adminEmails");
		} else {
			// Get default mail service and add emails to mailto
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
