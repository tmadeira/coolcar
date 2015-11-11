<%@page import="java.util.*"%>
<%@page import="coolcar.ModelosManager,coolcar.Modelo,coolcar.Moto,coolcar.Carro"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Resultados da busca" />
</jsp:include>

<div class="container">

 <h2>Resultados</h2>
 <table class="table table-striped table-bordered table-hover table-condensed">
 <%-- <tr>
<th> <% 
		String bla = request.getParameter("local-devolucao"); 
		out.println(bla);%> </th>
<td> Modelo </td>
<td> Preco </td> 
<td> Numero de assentos </td>
</tr>--%>
  <%
    ModelosManager manager = new ModelosManager();
    ArrayList<Modelo> modelos = manager.buscaModelos();
   	StringBuilder sb = new StringBuilder();

	for (Modelo modelo : modelos) {
   		sb.append("<tr><table class=\"table table-striped table-bordered table-hover table-condensed\">");
	   	if(modelo.getTipoCar()) {
	   		sb.append("<tr><th colspan=\"2\"> " + modelo.getNome() + " </th></tr>");
	   	    sb.append("<tr><td><img src=\"https://www.omegarentalcars.com/assets/vehicles/_resampled/thumb-BAD323245-1230.jpg\" alt=\"...\" class=\"img-responsive img-circle\" width=\"180\" height=\"180\"></td>");
	   	 	sb.append("<td>");
	   	    sb.append("<table><tr><td> Preço: " + modelo.getPreco() + " </td></tr>");
	   	   	
	   	   	//	sb.append("<td> Carro </td>");
	   	   	sb.append("<tr><td> Num Assentos: " + modelo.getNumeroAssentos() + " </td></tr>");
	   	   	sb.append("</tr></table></td>");
	   	   	}
	   	   	else {
	   	   	sb.append("<td><img src=\"http://www.casaraomotos.com.br/slices/img-Moto01.png\" alt=\"...\" class=\"img-responsive img-circle\" width=\"180\" height=\"180\" ></td>");
	   	    sb.append("<td> " + modelo.getNome() + " </td>");
	   	    sb.append("<td> " + modelo.getPreco() + " </td>");
	   	   	sb.append("<td> Moto </td>");
	   	}
   	    sb.append("</table></tr>");
   	}
   	out.println(sb.toString());
  %>
  </table>
</div>

<jsp:include page="footer.jsp" />