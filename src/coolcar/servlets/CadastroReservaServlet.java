package coolcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coolcar.Sessao;
import coolcar.modelos.Reserva;
import coolcar.managers.ReservasManager;

@WebServlet("/CadastroReservaServlet")
public class CadastroReservaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private int verificaMes(int substr) {
    substr--;
    return 0 <= substr && substr < 12 ? substr : 0;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Sessao s = new Sessao(request.getCookies());

    if (s.isLogged()) {
      String dataFimStr = request.getParameter("reservaDataDevolucao");
      String dataIniStr = request.getParameter("reservaDataRetirada");

      Calendar c = Calendar.getInstance();

      c.set(Integer.parseInt(dataFimStr.substring(0, 4)), verificaMes(Integer.parseInt(dataFimStr.substring(5, 7))),
          Integer.parseInt(dataFimStr.substring(8)));
      Date dataFim = c.getTime();
      c.set(Integer.parseInt(dataIniStr.substring(0, 4)), verificaMes(Integer.parseInt(dataIniStr.substring(5, 7))),
          Integer.parseInt(dataIniStr.substring(8)));
      Date dataIni = c.getTime();

      ReservasManager reserva_manager = new ReservasManager();
      Reserva reserva = new Reserva(s.getId(), // id_cliente
          0, // id_reserva -- não importa para o INSERT
          1,//Integer.parseInt(request.getParameter("reservaModelo")), // id_modelo
          1,// Integer.parseInt(request.getParameter("reservaFilialRetirada")), // id_filial_retirada,
          1, // id_acessorios -- importa para o INSERT mas não demos importância agora
          1,//Integer.parseInt(request.getParameter("reservaFilialDevolucao")), // id_filial_devolucao,
          1,//Float.parseFloat(request.getParameter("reservaValor")), // valor,
          dataFim, // dt_fim
          dataIni); // dt_inicio

      reserva_manager.insere(reserva);
      response.sendRedirect("index.jsp");

    } else {
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
      response.setCharacterEncoding("utf-8");
      PrintWriter out = response.getWriter();
      out.println("<font color=red>Por favor, faça o login antes de reservar!</font>\n");
      rd.include(request, response);
    }

  }
}
