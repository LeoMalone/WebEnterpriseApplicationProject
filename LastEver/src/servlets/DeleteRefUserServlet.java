package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DivisionBean;
import dao.Division;
import dao.EditRefUser;

public class DeleteRefUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		response.setContentType("text/html");
		
		StringBuilder sb = new StringBuilder(request.getQueryString());
		sb.deleteCharAt(0);
		
		List<DivisionBean> dlb = new ArrayList<DivisionBean>();
		Division.getAllDivisions(dlb);
		request.setAttribute("allDiv", dlb);
		
		if(EditRefUser.deleteRefUser(Integer.parseInt(sb.toString()))) {
			response.sendRedirect("./refUsers");
		}
	}	
}
