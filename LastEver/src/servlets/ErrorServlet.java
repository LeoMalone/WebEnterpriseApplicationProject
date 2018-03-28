package servlets;

import java.io.*;
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
 * The ErrorServlet class extends HttpServlet for GET/POST requests to a generic error page
 * whenever an Http error occurs
 * @author Kevin Villemaire
 */
public class ErrorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /error
	 * @param request - The request parameters
	 * @param response - The response parameters
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//cookie variables
		String userName = null;
		String language = null;

		/****************** COOKIE LOGIC ****************/

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

			response.setContentType("text/html");

			//gets any servlet error exceptions
			Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
			//gets the http status code to be displayed on the website
			Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
			//gets the servlet name where the error occurred
			String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

			//if servlet name is null set it to say Not Available
			if(servletName == null)
				servletName = "Not Available";

			//gets the page where the request happened (for 404 errors)
			String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

			//if 404 error (page not found) occurs then say page not found
			if(statusCode == 404) {
				request.setAttribute("error", "The specified page: " + requestUri + " could not be found.");
			}
			//if 405 error (method not supported) occurs then say the page does not support request method
			else if(statusCode == 405) {
				request.setAttribute("error", requestUri + " does not support this request method.");
			}
			//if 500 (internal server error) then display the class and the message of the error
			//This will be changed when the website goes live to a generic error message
			else {
				request.setAttribute("error", servletName + " has encountered error: " + throwable.getClass().getName()
						+ " with error message: " + throwable.getMessage());
			}

			// Set leagues for navbar
			List<LeagueBean> llb = new ArrayList<LeagueBean>();
			League.getAllLeagues(llb);
			request.setAttribute("league", llb);

			//set request attributes
			request.setAttribute("errorcode", statusCode);
			request.setAttribute("userName", userName);
			//redirect to the error page
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");  
			rd.forward(request, response);		
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}