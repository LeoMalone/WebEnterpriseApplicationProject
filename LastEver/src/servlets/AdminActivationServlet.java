package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import beans.LeagueBean;
import beans.UserBean;
import dao.League;

/**
 * AdminActivation Servlet class extends the HttpServlet class to handle the GET/POST requests for
 * the page that shows the user that their account need to be admin activated before use
 * @author Kevin Villemaire
 */
public class AdminActivationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /contact
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");

		// Tracked Cookie variables
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

			//get league list
			List<LeagueBean> llb = new ArrayList<LeagueBean>();
			League.getAllLeagues(llb);
			
			//set request attributes
			request.setAttribute("league", llb);
			request.setAttribute("userName", userName);
			request.setAttribute("user", (UserBean) request.getSession().getAttribute("user"));

			//forward to contact page
			RequestDispatcher rd = request.getRequestDispatcher("activation_needed.jsp");  
			rd.forward(request, response);	
		}
	}

	/**
	 * doGet method mapped to /contact
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}
