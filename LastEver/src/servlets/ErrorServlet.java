package servlets;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ErrorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String userName = null;
		String language = null;

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

			response.setContentType("text/html");


			Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
			Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
			String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
			
			if(servletName == null)
				servletName = "Not Available";
			
			String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
			
			if(statusCode == 404) {
				request.setAttribute("error", "The specified page: " + requestUri + " could not be found.");
			}
			else {
				request.setAttribute("error", servletName + " has encountered error: " + throwable.getClass().getName()
						+ " with error message: " + throwable.getMessage());
			}
			
			request.setAttribute("errorcode", statusCode);
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");  
			rd.forward(request, response);		
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}