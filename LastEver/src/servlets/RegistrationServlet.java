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

import beans.LeagueBean;
import dao.League;

/**
<<<<<<< HEAD
 * RegistrationServlet class extends HttpServlet for GET/POST requests for the registration page
 * to get navbar and session info
 * @author Kevin Read and edited by Kevin Villemaire
=======
 * RegistrationServlet class
 * @author Kevin Read, Kevin Villemaire
>>>>>>> branch 'master' of https://github.com/LeoMalone/WebEnterpriseApplicationProject.git
 */
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /registration
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		//cookie variables
		String userName = null;
		String language = null;		
		
		//get cookies
		//get the cookie list
		Cookie[] cookies = request.getCookies();
		
		//if there are cookies then set userName and language to the cookie values if they exist
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username"))
					userName = cookie.getValue();
				else if (cookie.getName().equals("language"))
					language = cookie.getValue();
			}
		}
		//if there is no cookies and the language has not been set then create the cookie with English as default language
		if(language == null) {
			Cookie cookieLanguage = new Cookie("language", "en");
			cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
			response.addCookie(cookieLanguage);
		}
		else {

			//get the current website language
			language = request.getParameter("language");
			//get all the cookies on the website
			Cookie[] theCookies = request.getCookies();

			//loop through the cookies and look for the language cookie
			for (Cookie tempCookie : theCookies) {
				if ("language".equals(tempCookie.getName())) {
					//if the language cookie exists then update the language with the websites language if it exists
					if (language != null)
						tempCookie.setValue(language);
					//add the cookie back to the response headers
					response.addCookie(tempCookie);
					break;
				}
			}		
				
			// Set leagues for navbar
			List<LeagueBean> llb = new ArrayList<LeagueBean>();
			League.getAllLeagues(llb);
			request.setAttribute("league", llb);
			
			request.setAttribute("userName", userName);
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");  
	        rd.forward(request, response);	
		}
	}
	
	/**
	 * doPost method mapped to /registration
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}
