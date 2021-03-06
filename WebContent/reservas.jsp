<%@page import="java.util.ArrayList, coolcar.Sessao"%>
<%@page
 import="coolcar.managers.ReservasManager,coolcar.modelos.Reserva, coolcar.managers.ModelosManager, coolcar.modelos.Modelo, java.text.SimpleDateFormat, java.text.DateFormat"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
 <jsp:param name="pageTitle" value="Histórico de Reservas" />
</jsp:include>

<div class="container">


 <%
   Sessao s = new Sessao(request.getCookies());
   if (s.isLogged()) {
     ReservasManager manager = new ReservasManager();
     ModelosManager mod_manager = new ModelosManager();
     ArrayList<Reserva> reservas = manager.consulta(s.getId());
     //if (reservas.isEmpty())
     //	System.out.println("VAZIO");
     DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
 %>
 <h2>
  Histórico de Reservas de
  <%=s.getNomeUsuario()%></h2>

 <table
  class="table table-striped table-bordered table-hover table-condensed">
  <tr>
   <th>ID da Reserva</th>
   <th>Data de Retirada</th>
   <th>Data de Devolução</th>
   <th>Modelo</th>
  </tr>
  <%
    for (Reserva reserva : reservas) {
        ArrayList<Modelo> modelos = mod_manager.consulta(reserva.getId_modelo());
  %>
  <tr>
   <td><%=reserva.getId_reserva()%></td>
   <td><%=df.format(reserva.getDt_inicio_reserva())%></td>
   <td><%=df.format(reserva.getDt_fim_reserva())%></td>
   <td><%=modelos.get(0).getNome()%></td>
  </tr>
  <%
    }
    }
  %>
 </table>
</div>

<jsp:include page="footer.jsp" />