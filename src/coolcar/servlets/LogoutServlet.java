package coolcar.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coolcar.Sessao;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private void matarCookie(Cookie cookie, HttpServletResponse response) {
    if (cookie != null) {
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    Sessao s = new Sessao(request.getCookies());
    Cookie[] cookies = s.logOut();

    for (Cookie c : cookies)
      matarCookie(c, response);
    
    response.sendRedirect("index.jsp");
  }

}