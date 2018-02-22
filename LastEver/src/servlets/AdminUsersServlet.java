package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.AdminUsers;

public class AdminUsersServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		
		List<UserBean> ulb = new ArrayList<UserBean>();
		AdminUsers.getAllUsers(ulb);		
		
		request.setAttribute("userList", ulb);		
		RequestDispatcher rd = request.getRequestDispatcher("admin_users.jsp");  
        rd.forward(request, response);		
	}
}
