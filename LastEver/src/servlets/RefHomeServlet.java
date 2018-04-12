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
import beans.RefBean;
import dao.EditRefUser;
import dao.League;

/**
 * RefHomeServlet classextends the HttpServlet class to handle the GET/POST requests for
 * the referee homepage
 * @author Kevin Read, Kevin Villemaire, Liam Maloney, Neal Sen
 *
 */
public class RefHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /referee
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

				request.setAttribute("userName", userName);

				//get id from url and set userBean id
				RefBean user = new RefBean();

				if(EditRefUser.getUserForEdit(user, userName)) {
					request.setAttribute("ref", user);
				}

				RequestDispatcher rd = request.getRequestDispatcher("referee.jsp");  
				rd.forward(request, response);	
			}
		}
	}

	/**
	 * doGet method mapped to /referee
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}
