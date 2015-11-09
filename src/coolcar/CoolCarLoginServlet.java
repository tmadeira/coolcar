package coolcar;
 
import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/*
 * Author: Crunchify.com
 * 
 */
 
/**
 * Servlet implementation class LoginServlet
 */
 
@WebServlet("/CoolCarLoginServlet")
public class CoolCarLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        // get request parameters for email and password
    	String paramUserEmail = request.getParameter("userEmail");
        String paramUserPwd = request.getParameter("userPwd");
        
        Session s = Session.getInstance();
        
        if (s.logIn(paramUserEmail, paramUserPwd)) {
        	response.addCookie(s.getCookie());
            response.sendRedirect("logado.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Por favor, preencha os campos de usuário e senha!</font>\n");
            rd.include(request, response);
        }
  
    }
}