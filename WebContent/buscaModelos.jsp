<%@page import="java.util.*, coolcar.bd.*, coolcar.modelos.*, coolcar.managers.*"%>
<%@page import="coolcar.managers.ModelosManager,coolcar.modelos.Modelo,coolcar.modelos.Moto,coolcar.modelos.Carro"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
<jsp:param name="pageTitle" value="Resultados da busca" />
</jsp:include>

<div class="container">

<h2>
<% 
    
	BD bd = new BD();
	
	Filial filiais = new Filial();
	FilialManager filial_manager = new FilialManager();
	
	ArrayList<Filial> resultados = filial_manager.consulta(filiais);
	Filial filial = resultados.get(Integer.parseInt(request.getParameter("local-retirada")) - 1);
  out.println("Modelos disponíveis em " + filial.getNome());
%>
</h2>
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
    Carro carro_teste = new Carro();
    carro_teste.setIdModelo(1);

    Moto moto_teste = new Moto();
    moto_teste.setCilindradas(150);

    CarroManager carro_manager = new CarroManager();	
    MotoManager moto_manager = new MotoManager();

    ArrayList<Carro> carros = carro_manager.consulta(carro_teste);
    ArrayList<Moto> motos = moto_manager.consulta(moto_teste);

    StringBuilder sb = new StringBuilder();

    if (true) { // TODO
      for (Carro carro : carros) {       
        sb.append("<tr><th> " + carro.getNome() + "");
        sb.append(
            "<img src=\"https://www.omegarentalcars.com/assets/vehicles/_resampled/thumb-BAD323245-1230.jpg\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"180\" height=\"180\"></th>");
        sb.append("<td>");
        sb.append("<table><tr><td> Preço: " + carro.getDiaria() + " </td></tr>");

        //	sb.append("<td> Carro </td>");
        sb.append("<tr><td> Num Assentos: " + carro.getNumAssentos() + " </td></tr>");
        sb.append("</tr></table></td>");
      }
    } else {
      for (Moto moto : motos) {       
        sb.append(
            "<tr><th>"+moto.getNome()+"<br><img src=\"http://www.casaraomotos.com.br/slices/img-Moto01.png\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"180\" height=\"180\" ></th>");
        sb.append("<td><table>");
        sb.append("<tr><td> Preço: " + moto.getDiaria() + " </td></tr>");
        sb.append("<tr><td> Moto </td></tr>");
        sb.append("</tr></table></td>");
      }
    }
    out.println(sb.toString());
  %>
 </table>
</div>

<jsp:include page="footer.jsp" />