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

import beans.PlayerBean;
import beans.StatisticsBean;
import dao.Division;
import dao.Player;
import dao.Statistics;
import dao.Team;

public class PlayerPageServlet extends HttpServlet {

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

			String id = request.getParameter("id");
			String div = Division.getPlayerDivision(id);
			String team = Team.getPlayerTeam(id);
			
			response.setContentType("text/html");

			List<StatisticsBean> stlb = new ArrayList<StatisticsBean>();
			List<PlayerBean> plb = new ArrayList<PlayerBean>();
			
			Player.getPlayerInfo(id, plb);
			Statistics.getStatisticsWithPlayer(Player.getPlayerName(id), stlb);
			
			request.setAttribute("division", div);
			request.setAttribute("player", plb);
			request.setAttribute("team", team);
			request.setAttribute("statistics", stlb);
			request.setAttribute("userName", userName);
			RequestDispatcher rd = request.getRequestDispatcher("/player.jsp?id=" + id);  
			rd.forward(request, response);		
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		doGet(request, response);
	}
}