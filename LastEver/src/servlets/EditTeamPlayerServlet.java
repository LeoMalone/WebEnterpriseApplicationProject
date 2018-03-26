package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DivisionBean;
import beans.PlayerBean;
import dao.Division;
import dao.EditTeamPlayer;


/**
 * The EditTeamPlayerServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the team owner control panel option edit Player.
 * @author Kevin Read
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
		
		// Set divisions for navbar
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
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
		
		// Get id from url and add it to UserBean
		PlayerBean player = new PlayerBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		// Get all schedule parameters from jsp inputs
		/*
		String newFirstName = request.getParameter("editFirstName");
		String newLastName = request.getParameter("editLastName");
		String newUsername = request.getParameter("editUsername");
		String newEmail = request.getParameter("editEmail");
		String newPassword = request.getParameter("editPass");
		String userType = request.getParameter("editRadio");
		*/
		// If any parameter is null
		/*
		if(newFirstName == null || newLastName == null || newUsername == null || newEmail == null || newPassword == null || userType == null) {
			response.sendRedirect("./teamRoster");
		} else {
			// Set UserBean parameters
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			player.setAccountUpdated(timestamp);
			player.setFirstName(newFirstName);
			player.setLastName(newLastName);
			player.setId(sb.toString());
			player.setUsername(newUsername);
			player.setEmail(newEmail);
			player.setPassword(newPassword);
			player.setUserType(userType);
			*/
		
		String newPlayerFirstName = request.getParameter("editPlayerFirstName");
		String newPlayerLastName = request.getParameter("editPlayerLastName");
		String newPlayerNumber = request.getParameter("editPlayerNumber");
		String newPlayerPosition = request.getParameter("editPlayerPosition");
		
		// If any parameter is null
		if(newPlayerFirstName == null || newPlayerLastName == null || newPlayerNumber == null || newPlayerPosition == null) {
			response.sendRedirect("./teamRoster");
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
