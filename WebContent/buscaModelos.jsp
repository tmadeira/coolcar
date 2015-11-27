<%@page
 import="java.util.ArrayList, coolcar.modelos.Filial, coolcar.modelos.Veiculo"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
 <jsp:param name="pageTitle" value="Resultados da busca" />
</jsp:include>

<%
  Filial filial = (Filial) request.getAttribute("filial");
  @SuppressWarnings("unchecked")
  ArrayList<Veiculo> veiculos = (ArrayList<Veiculo>) request.getAttribute("veiculos");
%>

<div class="container">
 <h2>
  Modelos disponíveis em
  <%=filial.getNome()%>
 </h2>

 <table
  class="table table-striped table-bordered table-hover table-condensed">
  <%
    if (request.getParameter("tipo-veiculo").equals("Carro")) {
      for (Veiculo veiculo : veiculos) {
  %>
  <tr>
   <th width="30%">
    <h4>
     <%=veiculo.getCarro().getNome()%>
    </h4> <img src="<%=veiculo.getCarro().getLink()%>"
    class="img-responsive img-thumbnail" width="300" height="300">
   </th>
   <td>
    <form name="listaVeiculos" id="listaVeiculos"
     action="RealizaReservaServlet" method="post">
     <input type="hidden" name="idModelo"
      value="<%=veiculo.getCarro().getIdModelo()%>" /> <input
      type="hidden" name="local-retirada"
      value="<%=request.getParameter("local-retirada")%>" /> <input
      type="hidden" name="local-devolucao"
      value="<%=request.getParameter("local-devolucao")%>" /> <input
      type="hidden" name="dtRetirada"
      value="<%=request.getParameter("dtRetirada")%>" /> <input
      type="hidden" name="dtDevolucao"
      value="<%=request.getParameter("dtDevolucao")%>" />
     <button type="submit" class="btn btn-warning">Reserve este
      modelo agora</button>
    </form>

    <table>
     <tr>
      <td><h4>
        Preço da Diária: R$
        <%=String.format("%.2f", veiculo.getCarro().getDiaria())%>
       </h4></td>
     </tr>
     <tr>
      <td><b>Ano:</b> <%=veiculo.getAno()%></td>
     </tr>
     <tr>
      <td><b>Fabricante:</b> <%=veiculo.getCarro().getFabricante()%></td>
     </tr>
     <tr>
      <td><b>Tipo:</b> <%=veiculo.getCarro().getTipo()%></td>
     </tr>
     <tr>
      <td><b>Número de Portas:</b> <%=veiculo.getCarro().getNumPortas()%>
      </td>
     </tr>
     <tr>
      <td><b>Número de Assentos:</b> <%=veiculo.getCarro().getNumAssentos()%></td>
     </tr>
     <tr>
      <td><b>Tamanho do Porta-Malas:</b> <%=veiculo.getCarro().getTamanhoPortaMalas()%>L
      </td>
     </tr>
     <tr>
      <td><b>Combustível:</b> <%=veiculo.getCarro().getCombustivel()%></td>
     </tr>
     <%
       if (veiculo.getCarro().getCarac().getArCond()) {
     %>
     <tr>
      <td>Ar Condicionado</td>
     </tr>
     <%
       }
     %>
     <tr>
      <td>Câmbio <%=veiculo.getCarro().getCarac().getCambAuto() ? "Automático" : "Manual"%></td>
     </tr>
     <%
       if (veiculo.getCarro().getCarac().getDirHid()) {
     %>
     <tr>
      <td>Direção Hidráulica</td>
     </tr>
     <%
       }
     %>
    </table>
   </td>
  </tr>
  <%
    }
    } else {
      for (Veiculo veiculo : veiculos) {
  %>
  <tr>
   <th width="30%">
    <h4>
     <%=veiculo.getMoto().getNome()%>
    </h4> <img src="<%=veiculo.getMoto().getLink()%>"
    class="img-responsive img-thumbnail" width="300" height="300">
   </th>
   <td>
    <form name="listaVeiculos" id="listaVeiculos"
     action="RealizaReservaServlet" method="post">
     <input type="hidden" name="idModelo"
      value="<%=veiculo.getMoto().getIdModelo()%>" /> <input
      type="hidden" name="local-retirada"
      value="<%=request.getParameter("local-retirada")%>" /> <input
      type="hidden" name="local-devolucao"
      value="<%=request.getParameter("filialDevolucao")%>" /> <input
      type="hidden" name="dtRetirada"
      value="<%=request.getParameter("dtRetirada")%>" /> <input
      type="hidden" name="dtDevolucao"
      value="<%=request.getParameter("dtDevolucao")%>" />
     <button type="submit" class="btn btn-warning">Reserve este
      modelo agora</button>
    </form>

    <table>
     <tr>
      <td><h4>
        Preço da Diária: R$
        <%=String.format("%.2f", veiculo.getMoto().getDiaria())%>
       </h4></td>
     </tr>
     <tr>
      <td><b>Ano:</b> <%=veiculo.getAno()%></td>
     </tr>
     <tr>
      <td><b>Fabricante:</b> <%=veiculo.getMoto().getFabricante()%></td>
     </tr>
     <tr>
      <td><b>Tipo:</b> <%=veiculo.getMoto().getTipo()%></td>
     </tr>
     <tr>
      <td><b>Combustível:</b> <%=veiculo.getMoto().getCombustivel()%></td>
     </tr>
     <tr>
      <td><b>Tamanho do Tanque:</b> <%=veiculo.getMoto().getTamanhoTanque()%>L</td>
     </tr>
     <tr>
      <td><b>Cilindradas:</b> <%=veiculo.getMoto().getCilindradas()%></td>
     </tr>
    </table>
   </td>
   <%
     }
     }
   %>
  
 </table>
</div>

<jsp:include page="footer.jsp" />
