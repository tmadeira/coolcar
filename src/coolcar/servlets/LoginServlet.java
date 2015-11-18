package coolcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coolcar.Sessao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String paramUserEmail = request.getParameter("userEmail");
    String paramUserPwd = request.getParameter("userPwd");

    Sessao s = Sessao.getInstance();

    if (s.logIn(paramUserEmail, paramUserPwd)) {
      response.addCookie(s.getCookie());
      response.sendRedirect("index.jsp");
    } else {
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
      response.setCharacterEncoding("utf-8");
      PrintWriter out = response.getWriter();
      out.println("<font color=red>Por favor, preencha os campos de usuário e senha!</font>\n");
      rd.include(request, response);
    }

  }
}