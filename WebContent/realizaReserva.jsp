<%@page
 import="java.math.BigDecimal, coolcar.modelos.Modelo, coolcar.modelos.Filial"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
 <jsp:param name="pageTitle" value="Realiza Reserva" />
</jsp:include>

<%
  Modelo modelo = (Modelo) request.getAttribute("modelo");
  Filial filialRetirada = (Filial) request.getAttribute("filialRetirada");
  Filial filialDevolucao = (Filial) request.getAttribute("filialDevolucao");
  String dataRetirada = (String) request.getAttribute("dataRetirada");
  String dataDevolucao = (String) request.getAttribute("dataDevolucao");
  long numDias = (long) request.getAttribute("numDias");
  BigDecimal precoTotal = (BigDecimal) request.getAttribute("precoTotal");
%>

<div class="container">
 <h3>Informações da sua reserva:</h3>

 <p class="text-center">
  <img src="<%=modelo.getLink()%>" class="img-responsive img-thumbnail"
   width="300" height="300">
 </p>

 <table class="table table-striped">
  <tr>
   <td><b>Preço da Diária:</b></td>
   <td>R$ <%=modelo.getDiaria()%></td>
  </tr>
  <tr>
   <td><b>Quantidade de dias:</b></td>
   <td><%=numDias%></td>
  </tr>
  <tr>
   <td><b>Preço total:</b></td>
   <td>R$ <%=precoTotal%></td>
  </tr>
  <tr>
   <td><b>Data retirada:</b></td>
   <td><%=dataRetirada%></td>
  </tr>
  <tr>
   <td><b>Local da retirada:</b></td>
   <td><%=filialRetirada.getNome()%></td>
  </tr>
  <tr>
   <td><b>Data devolução:</b></td>
   <td><%=dataDevolucao%></td>
  </tr>
  <tr>
   <td><b>Local da devolução:</b></td>
   <td><%=filialDevolucao.getNome()%></td>
  </tr>
 </table>

 <form name="listaVeiculos" id="listaVeiculos"
  action="CadastroReservaServlet" method="post" class="align-center">
  <input type="hidden" id="reservaModelo" name="reservaModelo"
   value="<%=modelo.getIdModelo()%>" /> <input type="hidden"
   id="reservaValor" name="reservaValor" value="<%=precoTotal%>" /> <input
   type="hidden" id="reservaFilialRetirada" name="reservaFilialRetirada"
   value="<%=filialRetirada.getId()%>" /> <input type="hidden"
   id="reservaFilialDevolucao" name="reservaFilialDevolucao"
   value="<%=filialDevolucao.getId()%>" /> <input type="hidden"
   id="reservaDataRetirada" name="reservaDataRetirada"
   value="<%=dataRetirada%>" /> <input type="hidden"
   id="reservaDataDevolucao" name="reservaDataDevolucao"
   value="<%=dataDevolucao%>" />
  <button type="submit" class="btn btn-warning">
   <b>Confirmar reserva!</b>
  </button>
 </form>
</div>

<jsp:include page="footer.jsp" />
