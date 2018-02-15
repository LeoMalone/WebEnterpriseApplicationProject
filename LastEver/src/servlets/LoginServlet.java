package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Login;

public class LoginServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();        
        String loginEmail = request.getParameter("loginEmail");  
        String loginPass = request.getParameter("loginPass");

        if(Login.validateLogin(loginEmail, loginPass)){  
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");  
            rd.forward(request,response);  
        } else {
        	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
            rd.forward(request,response);  
        }

        out.close();  
    }  
}