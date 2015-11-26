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

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    Sessao s = Sessao.getInstance();
    Cookie loginCookie = s.logOut(request.getCookies());

    if (loginCookie != null) {
      loginCookie.setMaxAge(0);
      response.addCookie(loginCookie);
    }

    response.sendRedirect("index.jsp");
  }

}