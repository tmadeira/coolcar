<%@page import="java.util.*, coolcar.bd.*, coolcar.modelos.*, coolcar.managers.*, java.text.SimpleDateFormat"%>
<%@page import="coolcar.managers.ModelosManager,coolcar.modelos.Modelo,coolcar.modelos.Moto,coolcar.modelos.Carro"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
<jsp:param name="pageTitle" value="Realiza Reserva" />
</jsp:include>

<div class="container">
 <h3>Informações da sua reserva:</h3>
 
 	<center>
 		<img src="<% out.println(request.getParameter("reservaLinkImagem")); %>" class="img-responsive img-thumbnail" width="300" height="300">
 	</center>
 
	<table class="table table-striped">
 		<tr>
 			<td><b>Preço da Diária:</b></td>
 			<td>R$ <% out.println(String.format("%.2f", Float.parseFloat(request.getParameter("reservaDiaria")))); %></td>
 		</tr>
 		<tr>
 			<%
 				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
 			    long startDateTime = formatter.parse(request.getParameter("reservaDataRetirada")).getTime();
 			    long endDateTime = formatter.parse(request.getParameter("reservaDataDevolucao")).getTime();
 			    long milPerDay = 1000*60*60*24;
 			    
 			    float numOfDays = (float) ((endDateTime - startDateTime) / milPerDay);
 			%>
 			<td><b>Quantidade de dias:</b></td>
 			<td><% out.println((int) numOfDays); %></td>
 		</tr>
 		<tr>
 			<td><b>Preço total:</b></td>
 			<td>R$ <% out.println(String.format("%.2f", Float.parseFloat(request.getParameter("reservaDiaria")) * numOfDays )); %></td>
 		</tr>
 		<tr>
 			<td><b>Data retirada:</b></td>
 			<td><% out.println(request.getParameter("reservaDataRetirada")); %></td>
 		</tr>
 		<tr>
 			<%
	 			BD bd = new BD();
	 			
	 			Filial filial = new Filial();
	 			filial.setId(Integer.parseInt(request.getParameter("reservaFilialRetirada")));
	 			FilialManager filial_manager = new FilialManager();
	 			
	 			ArrayList<Filial> resultados = filial_manager.consulta(filial);
 			%>
 			<td><b>Local da retirada:</b></td>
 			<td><% out.println(resultados.get(0).getNome()); %></td>
 		</tr>
 		<tr>
 			<td><b>Data devolução:</b></td>
 			<td><% out.println(request.getParameter("reservaDataDevolucao")); %></td>
 		</tr>
 		<tr>
 			 <%
	 			filial.setId(Integer.parseInt(request.getParameter("reservaFilialDevolucao")));
	 			
	 			resultados = filial_manager.consulta(filial);
 			%>
 			<td><b>Local da devolução:</b></td>
 			<td><% out.println(resultados.get(0).getNome()); %></td>
 		</tr>
 	</table>
 <center>
	<form name="listaVeiculos" id="listaVeiculos" action="CadastroReservaServlet" method="post">
		<input type="hidden" id="reservaModelo" name="reservaModelo" value="<% out.println(request.getParameter("reservaModelo")); %>" />
		<input type="hidden" id="reservaValor" name="reservaValor" value="<% out.println(String.format("%.2f", Float.parseFloat(request.getParameter("reservaDiaria")) * numOfDays )); %>" />
		<input type="hidden" id="reservaFilialRetirada" name="reservaFilialRetirada" value="<% out.println(request.getParameter("reservaFilialRetirada")); %>" />
		<input type="hidden" id="reservaFilialDevolucao" name="reservaFilialDevolucao" value="<% out.println(request.getParameter("reservaFilialDevolucao")); %>" />
		<input type="hidden" id="reservaDataRetirada" name="reservaDataRetirada" value="<% out.println(request.getParameter("reservaDataRetirada")); %>" />
		<input type="hidden" id="reservaDataDevolucao" name="reservaDataDevolucao" value="<% out.println(request.getParameter("reservaDataDevolucao")); %>" />
		<button type="submit" class="btn btn-warning"><b>Confirmar reserva!</b></button>
	</form>
</center>
 
</div>

<jsp:include page="footer.jsp" />