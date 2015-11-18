package coolcar.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import coolcar.bd.BD;

@SuppressWarnings("serial")
public class InstalacaoServlet extends HttpServlet {
  public void init() throws ServletException {
    try {
      BD bd = new BD();
      bd.setup(getServletContext().getRealPath("/WEB-INF/setup.sql"));
    } catch (ClassNotFoundException e) {
      System.out.println("Erro ao carregar driver para Postgres.");
      e.printStackTrace();
    }

  }
}
