package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LeagueBean;
import beans.PlayerBean;
import dao.EditTeamPlayer;
import dao.League;


/**
 * The EditTeamPlayerServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the team owner control panel option edit Player.
 * @author Kevin Read and edited by Kevin Villemaire
 */
public class EditTeamPlayerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * doGet method mapped to /editUser
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
		// TODO: distinguish between user types
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("./login");
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

				//get id from url and set userBean id
				StringBuilder sb = new StringBuilder(URLDecoder.decode(request.getQueryString(), "UTF-8"));
				sb.deleteCharAt(0);

				PlayerBean player = new PlayerBean();
				player.setId(sb.toString());
				// If query is successful
				if(EditTeamPlayer.getPlayerForEdit(player, player.getId())) {
					request.setAttribute("player", player);
					request.setAttribute("userName", userName);
					response.setContentType("text/html");
					RequestDispatcher rd = request.getRequestDispatcher("edit_team_player.jsp");  
					rd.forward(request, response);
				}
			}
		}
	}



	/**
	 * doPost method mapped to /editUser
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Tracked Cookie variables
		String userName = null;
		String language = null;

		// Get id from url and add it to UserBean
		PlayerBean player = new PlayerBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);

		// If User is not signed In redirect to sign in page
		// TODO: distinguish between user types
		if (request.getSession().getAttribute("signedIn") == null) {
			response.sendRedirect("./login");
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

				String newPlayerFirstName = request.getParameter("editPlayerFirstName");
				String newPlayerLastName = request.getParameter("editPlayerLastName");
				String newPlayerNumber = request.getParameter("editPlayerNumber");
				String newPlayerPosition = request.getParameter("editPlayerPosition");

				// If any parameter is null
				if(newPlayerFirstName == null || newPlayerLastName == null || newPlayerNumber == null || newPlayerPosition == null) {
					response.sendRedirect("./editTeamPlayer?="+sb.toString());
				} else {
					// Set PlayerBean parameters
					player.setPlayerFirstName(newPlayerFirstName);
					player.setPlayerLastName(newPlayerLastName);
					player.setId(sb.toString());
					player.setPlayerNumber(newPlayerNumber);
					player.setPlayerPosition(newPlayerPosition);

					// If query is successful
					if(EditTeamPlayer.saveChanges(player, player.getId())) {
						response.sendRedirect("./teamRoster");
					}
				}
			}	
		}
	}
}