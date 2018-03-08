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
import beans.NewsBean;
import dao.Division;

public class DivisionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String userName = null;
		String language = null;
		String newLang = null;

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
			newLang = request.getParameter("language");
			Cookie[] theCookies = request.getCookies();

			for (Cookie tempCookie : theCookies) {
				if ("language".equals(tempCookie.getName())) {
					if (newLang != null)
						tempCookie.setValue(newLang);
					else
						tempCookie.setValue(language);
					response.addCookie(tempCookie);
					break;
				}
			}

			String id = request.getParameter("id");
			response.setContentType("text/html");

			List<NewsBean> nlb = new ArrayList<NewsBean>();
			List<DivisionBean> dlb = new ArrayList<DivisionBean>();
			if(newLang != null)
				Division.getNews(id, nlb, newLang);
			else
				Division.getNews(id, nlb, language);	
			Division.getAllDivisions(dlb);

			request.setAttribute("allDiv", dlb);

			dlb = new ArrayList<DivisionBean>();	
			Division.getSpecificDivision(dlb, id);

			
			request.setAttribute("currDiv", dlb);
			request.setAttribute("news", nlb);	
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("/division.jsp?id=" + id);  
			rd.forward(request, response);		
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}