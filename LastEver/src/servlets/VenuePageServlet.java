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
import beans.ScheduleResultsBean;
import beans.VenueBean;
import dao.Division;
import dao.ScheduleResults;
import dao.Venue;

public class VenuePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
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
			
			String id = request.getParameter("id");
			String div = request.getParameter("div");
			
			if(div == null)
				div = "1";
			
			List<DivisionBean> dlb = new ArrayList<DivisionBean>();
			Division.getAllDivisions(dlb);
			request.setAttribute("allDiv", dlb);
			
			List<ScheduleResultsBean> slb = new ArrayList<ScheduleResultsBean>();
			ScheduleResults.getScheduleWithVenue(id, div, slb);	
			
			List<VenueBean> vlb = new ArrayList<VenueBean>();
			Venue.getVenue(id, vlb);
			
			request.setAttribute("venID", id);
			request.setAttribute("divID", div);
			request.setAttribute("venue", vlb);
			request.setAttribute("schedule", slb);
			request.setAttribute("map", vlb.get(0).getVenueAddress());
			
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("venue.jsp?id=" + id);  
	        rd.forward(request, response);	
		}
	}
}
