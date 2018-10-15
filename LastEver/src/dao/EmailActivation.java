package dao;

import beans.UserBean;
import db.ConnectionManager;
import org.joda.time.DateTime;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.Properties;

/**
 * The EmailActivation creates a token for the user to click in an email
 * sent to their email address to activate their email address
 * @author Kevin Villemaire
 */
public class EmailActivation {

	/**
	 * The generateToken method generates a new token to use to activate a users account
	 * @param user - UserBean object to get create account credentials
	 * @return status 
	 */
	public static boolean generateToken(UserBean user) { 

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement insertToken = null;	// # of executed queries
		Timestamp currTime = new Timestamp(System.currentTimeMillis());
		DateTime time = new DateTime(currTime).plusDays(1);
		int result = 0;	  

		//Generates bytes that will be used for the token
		SecureRandom rand = new SecureRandom();
		byte bytes[] = new byte[45];
		rand.nextBytes(bytes);

		//Encodes the bytes to create the new token
		byte[] token = Base64.getEncoder().encode(bytes);

		// Connect to Database and execute INSERT query with the Users ID, token, and validity date
		try {
			conn = ConnectionManager.getConnection();
			insertToken = conn.prepareStatement("INSERT INTO activation (userID, activationCode, activationFrom, activationTo)"
					+ " VALUES (?, ?, ?, ?);");
			insertToken.setString(1, user.getId());
			insertToken.setString(2, new String(token));
			insertToken.setTimestamp(3, currTime);
			insertToken.setTimestamp(4, new Timestamp(time.getMillis()));
			result = insertToken.executeUpdate();

			// Return true if 1 query executes
			if(result == 1) {
				status = true;
			}

			// Catch all possible Exceptions
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertToken != null) {
				try {
					insertToken.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return status;
	}

	/**
	 * The deleteToken method deletes the users token in order to remove their entry from the database
	 * in order to activate their account or send them a new token if it has expired
	 * @param user - The information of the current user
	 */
	public static boolean deleteToken(UserBean user) { 

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement deleteActivation = null;	// # of executed queries
		int result = 0;	  

		// Connect to Database and execute DELETE query with UserBean data
		try {
			conn = ConnectionManager.getConnection();        
			deleteActivation = conn.prepareStatement("delete from activation using activation where "
					+ " userID=?");
			deleteActivation.setString(1, user.getId());
			result = deleteActivation.executeUpdate();

			// Return true if 1 query executes
			if(result == 1) {
				status = true;
			}

			// Catch all possible Exceptions
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (deleteActivation != null) {
				try {
					deleteActivation.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return status;
	}

	/**
	 * The sendEmail method sends the activation email to the user
	 * @param user - UserBean object to get the users email address if provided
	 * @return status - If the email was successfully sent to the users email
	 */
	public static boolean sendEmail(UserBean user) { 

		boolean status = false;					// Status of sendEmail
		Connection conn = null;					// DB Connection
		PreparedStatement getToken = null;	// # of executed queries
		PreparedStatement getEmail = null;	// # of executed queries
		ResultSet token = null;				// result of query
		String userToken = null;			// the activationCode of the user

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();        
			getToken = conn.prepareStatement("select activationCode from activation where userID=?");
			getToken.setString(1, user.getId());
			token = getToken.executeQuery();

			// Return true if 1 query executes
			if(token.next()) {
				userToken = token.getString(1);

				//gets a users email and username in case the bean does not contain that information
				if(user.getEmailAddress() == null || user.getUsername() == null) {
					getEmail = conn.prepareStatement("select emailAddress, username from users where userID=?");
					getEmail.setString(1, user.getId());
					token = getEmail.executeQuery();

					//sets the email in the UserBean to be the users email address
					if(token.next()) {
						user.setEmail(token.getString(1));
						user.setUsername(token.getString(2));
					}
					else {
						//if the query fails then return and do not attempt to send the email
						return false;
					}
				}

				//Connection information to the email address to send emails from
				final String email = "lasteversoccer@outlook.com";
				final String password = "";

				//Create a new properties file which has the information about connecting to the email
				Properties properties = new Properties();

				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp-mail.outlook.com");
				properties.put("mail.smtp.port", "587");

				//Creates a new session with the email and password using PasswordAuthentication
				Session session = Session.getInstance(properties,
						new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email, password);
					}
				});

				try {
					//Creates a new message and sets from and to email addresses
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("lasteversoccer@outlook.com"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmailAddress()));
					//The subject of the email sent to the user
					message.setSubject("Welcome to LastEver / Bienvenue chez LastEver");
					//The message of the email sent to the user
					message.setText("Hello " + user.getUsername() + ",\n\nWelcome to LastEver Soccer Management Systems!"
							+ " Before you can get started with your account you need to first activate your email address"
							+ " which you can do by clicking the following link below.\n\nhttp://lastever.azurewebsites.net/activate?id="
							+ user.getId() + "&token=" + userToken + "\nThe activation link will expire in 24 hours. \n\n"
							+ "LastEver Administration Team \n\n --\nThis email account is not monitored and your "
							+ "replies will be ignored.\n\n---------\nBienvenue " + user.getUsername() + ",\n\nBienvenue sur LastEver Soccer Management Systems!"
							+ " Avant de commencer à utiliser votre compte, vous devez d'abord activer votre adresse e-mail."
							+ "que vous pouvez faire en cliquant sur le lien ci-dessous.\n\nhttp://lastever.azurewebsites.net/activate?id="
							+ user.getId() + "&token=" + userToken + "\nLe lien d'activation expirera dans 24 heures. \n\n"
							+ "LastEver Administration Team \n\n --\nCe compte de messagerie n'est pas surveillé et votre"
							+ "les réponses seront ignorées.");
					//Sens the message
					Transport.send(message);

				//Catch any exceptions
				} catch (MessagingException e) {
					e.printStackTrace();
				}

				status = true;
			}

		// Catch all possible Exceptions

		}catch (Exception e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getToken != null) {
				try {
					getToken.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return status;
	}
}
