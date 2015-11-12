<%@page import="java.util.*"%>
<%@page import="coolcar.managers.ModelosManager,coolcar.modelos.Modelo,coolcar.modelos.Moto,coolcar.modelos.Carro"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
<jsp:param name="pageTitle" value="Resultados da busca" />
</jsp:include>

<div class="container">

 <h2>Resultados</h2>
 <table class="table table-striped table-bordered table-hover table-condensed">
 <%-- <tr>
<th> <% 
		String param = new String(request.getParameter("local-devolucao").getBytes("iso-8859-1"), "UTF-8");		
		out.println("Modelos disponíveis em " + param);%> </th>
<td> Modelo </td>
<td> Preco </td> 
<td> Numero de assentos </td>
</tr>--%>
  <%
    ModelosManager manager = new ModelosManager();
    ArrayList<Modelo> modelos = manager.buscaModelos();
    StringBuilder sb = new StringBuilder();

    for (Modelo modelo : modelos) {
      //sb.append("<tr><table class=\"table table-striped table-bordered table-hover table-condensed\">");
      if (modelo.getTipo().equals("carro")) {
        sb.append("<tr><th> " + modelo.getNome() + "");
        sb.append(
            "<img src=\"https://www.omegarentalcars.com/assets/vehicles/_resampled/thumb-BAD323245-1230.jpg\" alt=\"...\" class=\"img-responsive img-circle\" width=\"180\" height=\"180\"></th>");
        sb.append("<td>");
        sb.append("<table><tr><td> Preço: " + modelo.getPreco() + " </td></tr>");

        //	sb.append("<td> Carro </td>");
        sb.append("<tr><td> Num Assentos: " + modelo.getNumeroAssentos() + " </td></tr>");
        sb.append("</tr></table></td>");
      } else {
    	//sb.append("<tr><th colspan=\"2\"> " + modelo.getNome() + " </th></tr>");
        sb.append(
            "<tr><th>"+modelo.getNome()+"<br><img src=\"http://www.casaraomotos.com.br/slices/img-Moto01.png\" alt=\"...\" class=\"img-responsive img-circle\" width=\"180\" height=\"180\" ></th>");
        sb.append("<td><table>");
        sb.append("<tr><td> Preço: " + modelo.getPreco() + " </td></tr>");
        sb.append("<tr><td> Moto </td></tr>");
        sb.append("</tr></table></td>");
      }
      //sb.append("</table></tr>");
    }
    out.println(sb.toString());
  %>
 </table>
</div>

<jsp:include page="footer.jsp" />