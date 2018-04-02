package servlets;

/**
 * The IndexServlet class extends the HttpServlet class to handle the GET/POST requests for
 * the index page to get the 5 most recent news stories on the website and gets the current weather
 * from the OpenWeatherMap API
 * @author Kevin Villemaire
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.json.JSONObject;
import org.json.JSONTokener;

import beans.LeagueBean;
import beans.NewsBean;
import beans.WeatherBean;
import dao.Index;
import dao.League;
import dao.Weather;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet mapped to /index
	 * @param request - request parameters
	 * @param response - response parameters
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//cookie variables
		String userName = null;
		String language = null;
		String newLang = null;

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
			//get the current website language and store it in a new variable so that the date doesnt change language
			newLang = request.getParameter("language");
			//get all the cookies on the website
			Cookie[] theCookies = request.getCookies();

			//loop through the cookies and look for the language cookie
			for (Cookie tempCookie : theCookies) {
				if ("language".equals(tempCookie.getName())) {
					//if the language cookie exists then update the language with the websites language if it exists
					if (newLang != null)
						tempCookie.setValue(newLang);
					//if the sites language has not changed
					else
						tempCookie.setValue(language);
					//add the cookie to the response headers
					response.addCookie(tempCookie);
					break;
				}
			}
		}

		//set the response type
		response.setContentType("text/html");

		//gets the current time to be displayed for the weather
		Timestamp currTime = new Timestamp(System.currentTimeMillis());
		
		//check to see if the weather from the database needs to be updated
		if(Weather.checkForUpdate(DateTime.now())) {

			// OpenWeatherMap API url to get the weather from: English/French
			String postData = "http://api.openweathermap.org/data/2.5/weather?id=6094817&type=accurate&"
					+ "units=metric&APPID=a4e18466ea056cf88f0ca54293678bfc";
			String postData_fr = "http://api.openweathermap.org/data/2.5/weather?id=6094817&type=accurate&"
					+ "units=metric&lang=fr&APPID=a4e18466ea056cf88f0ca54293678bfc";
			
			// create a new URL with the post data
			URL capURL = new URL(postData);
			URL weather_fr = new URL(postData_fr);

			// open a url connection with the specified url
			HttpURLConnection conn = (HttpURLConnection) capURL.openConnection();

			// set the connection to do output
			conn.setDoOutput(true);
			// set request method to post
			conn.setRequestMethod("POST");

			// set the content-type, charset, and content-length of the connection
			conn.setRequestProperty(
					"Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty(
					"charset", StandardCharsets.UTF_8.displayName());
			conn.setRequestProperty(
					"Content-Length", Integer.toString(postData.length()));
			conn.setUseCaches(false);
			conn.getOutputStream()
			.write(postData.getBytes(StandardCharsets.UTF_8));

			// create a JSON object from the response
			JSONObject json = new JSONObject(new JSONTokener(conn.getInputStream()));
			
			//connect to the OpenWeatherMap API again to get French description
			conn = (HttpURLConnection) weather_fr.openConnection();
			JSONObject jsonfr = new JSONObject(new JSONTokener(conn.getInputStream()));
			
			//update weather with data from the API and gets the french description of the weather
			Weather.updateWeather(json, jsonfr.getJSONArray("weather").getJSONObject(0).getString("description"));
		}
		
		//WeatherBean to store the weather data
		WeatherBean wb = new WeatherBean();
		
		//bean list variables used to set data on the page
		List<NewsBean> nlb = new ArrayList<NewsBean>();
		List<LeagueBean> llb = new ArrayList<LeagueBean>();

		//if the language has changed then get the news with the new language otherwise use the old language
		if(newLang != null)
			Index.getNews(nlb, newLang, 0, 5);
		else
			Index.getNews(nlb, language, 0, 5);

		//get weather data and then store it as an attribute
		Weather.getWeather(wb, "Ottawa");
		request.setAttribute("weather", wb);
		request.setAttribute("currtime", currTime);
		
		// Set leagues for navbar
		League.getAllLeagues(llb);
		request.setAttribute("league", llb);

		//sets request attributes
		request.setAttribute("news", nlb);
		request.setAttribute("league", llb);	
		request.setAttribute("userName", userName);

		//forwards to the index page
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");  
		rd.forward(request, response);		
	}

	/**
	 * doPost mapped to /index
	 * TODO: Find a better way to do this since doGet should not be called
	 * @param request - request parameters
	 * @param response - response parameters
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//TODO: Do not call doGet from doPost to set cookies
		//calls doGet to handle cookie logic
		doGet(request, response);
	}
}