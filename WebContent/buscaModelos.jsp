<%@page import="java.util.*"%>
<%@page import="coolcar.ModelosManager,coolcar.Modelo,coolcar.Moto,coolcar.Carro"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
<jsp:param name="pageTitle" value="Resultados da busca" />
</jsp:include>

<div class="container">

 <h2>Resultados</h2>
 <table class="table table-striped table-bordered table-hover table-condensed">
 <tr>
<th> <% 
		String param = new String(request.getParameter("local-devolucao").getBytes("iso-8859-1"), "UTF-8");		
		out.println("Modelos disponíveis em " + param);%> </th>
<td> Modelo </td>
<td> Preo </td>
<td> Numero de assentoss </td>
</tr>
  <%
    ModelosManager manager = new ModelosManager();
    ArrayList<Modelo> modelos = manager.buscaModelos();
   	StringBuilder sb = new StringBuilder();

	for (Modelo modelo : modelos) {
   		sb.append("<tr>");
	   	if(modelo.getTipoCar()) {
	   	    sb.append("<td><img src=\"https://www.omegarentalcars.com/assets/vehicles/_resampled/thumb-BAD323245-1230.jpg\" alt=\"...\" class=\"img-responsive img-circle\"></td>");
	   	 
	   		sb.append("<td> " + modelo.getNome() + " </td>");
		    sb.append("<td> " + modelo.getPreco() + " </td>");
	   	   	
	   	   		sb.append("<td> Carro </td>");
	   	   	//sb.append("<td> " + modelo.getNumeroAssentos() + " </td>");
	   	   	}
	   	   	else {
	   	   	sb.append("<td><img src=\"http://www.casaraomotos.com.br/slices/img-Moto01.png\" alt=\"...\" class=\"img-responsive img-circle\"></td>");
	      	 
	   	    sb.append("<td> " + modelo.getNome() + " </td>");
	   	    sb.append("<td> " + modelo.getPreco() + " </td>");
	   	   		sb.append("<td> Moto </td>");
	   	}
   	    sb.append("</tr>");
   	}
   	out.println(sb.toString());
  %>
</table>
</div>

<jsp:include page="footer.jsp" />