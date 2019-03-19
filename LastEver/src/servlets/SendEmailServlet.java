package servlets;

import beans.UserBean;
import dao.EmailActivation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The ActivateUser class extends the HttpServlet class to handle the GET/POST requests for
 * the administrator control panel option to activate a user
 * @author Kevin Villemaire
 */
public class SendEmailServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    /**
     * doGet method mapped to /resendEmail
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //get id from url and set userBean id
        String userID = request.getParameter("id");

        //gets the id of the current user
        UserBean user = new UserBean();
        user.setId(userID);

        //deletes current token and creates a new one to send to the user
        EmailActivation.deleteToken(user);
        EmailActivation.generateToken(user);
        EmailActivation.sendEmail(user);


        response.sendRedirect("./login");
    }
}