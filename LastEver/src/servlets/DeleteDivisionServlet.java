package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Division;

public class DeleteDivisionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		response.setContentType("text/html");
		
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		if(Division.deleteDivision(sb.toString())) {
			response.sendRedirect("./adminDivisions?=1");
		} else {
			response.sendRedirect("./editDivision?=" + sb.toString());
		}
	}
}
