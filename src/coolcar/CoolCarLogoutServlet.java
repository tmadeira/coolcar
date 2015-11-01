package coolcar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/*
 * Author: Crunchify.com
 * 
 */
 
/**
 * Servlet implementation class LogoutServlet
 */
 
@WebServlet("/CoolCarLogoutServlet")
public class CoolCarLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Session s = Session.getInstance();
        Cookie loginCookie = s.logOut(request.getCookies());
        
        if (loginCookie != null) {
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        
        response.sendRedirect("index.jsp");
    }
 
}