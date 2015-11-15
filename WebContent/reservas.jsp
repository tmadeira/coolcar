<%@page import="java.util.ArrayList, coolcar.Sessao"%>
<%@page
 import="coolcar.managers.ReservasManager,coolcar.modelos.Reserva, coolcar.managers.ModelosManager, coolcar.modelos.Modelo, java.text.SimpleDateFormat, java.text.DateFormat"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
 <jsp:param name="pageTitle" value="Histórico de Locações" />
</jsp:include>

<div class="container">


  <%
  
  	Sessao s = Sessao.getInstance();
	if (s.isLogged()) {
	    ReservasManager manager = new ReservasManager();
	    ModelosManager mod_manager = new ModelosManager();
	    ArrayList<Reserva> reservas = manager.consulta(s.getId());
	    //if (reservas.isEmpty())
	    //	System.out.println("VAZIO");
	    Modelo modelo;
	    ArrayList<Modelo> modelos = new ArrayList<Modelo>();
	    StringBuilder sb = new StringBuilder();
	    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    
	    out.println("<h2> Histórico de Locações de "+ s.getNomeUsuario() +"</h2>");
	%>
	    <br><table class="table table-striped table-bordered table-hover table-condensed">
	    <tr>
	    <th> Id da Locação</th>
	    <th>Data de Retirada</th>
	    <th>Data de Devolução</th>
	    <th>Modelo</th>
	    </tr>
	<%
	    for (Reserva reserva : reservas) {
	    	
	      modelos = mod_manager.consulta(reserva.getId_modelo());
	      
	      if (modelos.size() != 1)
	    	  out.println("ERRO NA CONSULTA");
	      modelo = modelos.get(0);
	      
	      sb.append("<tr>");
	      sb.append("<td>" + reserva.getId_reserva() + "</td>");
	      sb.append("<td>" + reserva.getDt_inicio_reserva() + "</td>");
	      sb.append("<td>" + reserva.getDt_fim_reserva() + "</td>");
	      sb.append("<td>" + modelo.getNome()  + "</td>");
	      out.println(sb.toString());
	    }
	}
  %>
 </table>
</div>

<jsp:include page="footer.jsp" />