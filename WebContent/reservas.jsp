<%@page import="java.util.ArrayList"%>
<%@page
 import="coolcar.managers.ReservasManager,coolcar.modelos.Reserva"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
 <jsp:param name="pageTitle" value="Histórico de Locações" />
</jsp:include>

<div class="container">
 <h2>Histórico de Locações de ~USER~</h2>
 <br>
 <table
  class="table table-striped table-bordered table-hover table-condensed">
  <tr>
   <th>Id da Locação</th>
   <th>Data de Retirada</th>
   <th>Data de Devolução</th>
   <th>Modelo</th>
  </tr>
  <%
    ReservasManager manager = new ReservasManager();
    ArrayList<Reserva> reservas = manager.consulta();
    StringBuilder sb = new StringBuilder();

    for (Reserva reserva : reservas) {
      sb.append("<tr>");
      sb.append("<td>" + reserva.getId() + "</td>");
      sb.append("<td>" + reserva.getData() + "</td>");
      sb.append("<td>" + reserva.getDevolucao() + "</td>");
      sb.append("<td>" + reserva.getNome() + "</td>");
      out.println(sb.toString());
    }
  %>
 </table>
</div>

<jsp:include page="footer.jsp" />