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
public class CadastroReservaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	  private int verificaMes(int substr) {
		  switch (substr) {
		  case 1:
			  return Calendar.JANUARY;
		  case 2:
			  return Calendar.FEBRUARY;
		  case 3:
			  return Calendar.MARCH;
		  case 4:
			  return Calendar.APRIL;
		  case 5:
			  return Calendar.MAY;
		  case 6:
			  return Calendar.JUNE;
		  case 7:
			  return Calendar.JULY;
		  case 8:
			  return Calendar.AUGUST;
		  case 9:
			  return Calendar.SEPTEMBER;
		  case 10:
			  return Calendar.OCTOBER;
		  case 11:
			  return Calendar.NOVEMBER;
		  case 12:
			  return Calendar.DECEMBER;
		  }
		  return 0;
	  }
	
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    Sessao s = Sessao.getInstance();

	    if (s.isLogged()) {
	      String dataFimStr = request.getParameter("reservaDataDevolucao");
	      String dataIniStr = request.getParameter("reservaDataRetirada");
	      
	      System.out.println(request.getParameter("reservaModelo"));
	    
    	  Calendar c = Calendar.getInstance();
    	  
    	  c.set(Integer.parseInt(dataFimStr.substring(0, 4)), verificaMes(Integer.parseInt(dataFimStr.substring(5, 7))), Integer.parseInt(dataFimStr.substring(8, 10)));
    	  Date dataFim = c.getTime();
    	  c.set(Integer.parseInt(dataIniStr.substring(0, 4)), verificaMes(Integer.parseInt(dataIniStr.substring(5, 7))), Integer.parseInt(dataIniStr.substring(8, 10)));
    	  Date dataIni = c.getTime();
	    	
	      ReservasManager reserva_manager = new ReservasManager();
	      Reserva reserva = new Reserva(s.getId(), //id_cliente
	    		  						0, //id_reserva -- não importa para o INSERT
	    		  						1, //id_modelo
	    		  						1, //id_filial_retirada,
	    		  						1, //id_acessorios -- importa para o INSERT mas não demos importância agora
	    		  						1, //id_filial_devolucao,
	    		  						1, //valor,
	    		  						dataFim, //dt_fim
	    		  						dataIni); //dt_inicio
	      
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
