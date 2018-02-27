package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.EditUser;


public class EditUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		//get id from url and set userBean id
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		UserBean user = new UserBean();
		user.setId(sb.toString());
		
		if(EditUser.getUserForEdit(user)) {
			request.setAttribute("user", user);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("edit_user.jsp");  
        rd.forward(request, response);		
	}
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		response.setContentType("text/html");
		
		UserBean user = new UserBean();
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		String newUsername = request.getParameter("editUsername");
		String newEmail = request.getParameter("editEmail");
		String newPassword = request.getParameter("editPass");
		String userType = request.getParameter("editRadio");
		
		user.setId(sb.toString());
		user.setUsername(newUsername);
		user.setEmail(newEmail);
		user.setPassword(newPassword);
		user.setUserType(userType);
		
		if(EditUser.saveChanges(user)) {
			response.sendRedirect("./adminUsers");
		}
	}	
}
