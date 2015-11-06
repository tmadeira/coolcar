<%@page import="java.util.ArrayList"%>
<%@page import="coolcar.LocacoesManager,coolcar.Locacao"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Histórico de Locações" />
</jsp:include>

<div class="container">
	<h2>Histórico de Locações de ~USER~</h2>
	<br>
	<table class="table table-striped table-bordered table-hover table-condensed">
		<tr>
		<th> Id da Locação </th>
		<th> Data de Retirada </th>
		<th> Data de Devolução </th>
		<th> Modelo </th>
		</tr>
	  <%
	    LocacoesManager manager = new LocacoesManager();
	    ArrayList<Locacao> locacoes = manager.buscaLocacoes();
	    StringBuilder sb = new StringBuilder();
	    
	    for (Locacao locacao : locacoes) {
	    	sb.append("<tr>");
	    	sb.append("<td>"+ locacao.getId() + "</td>");
	    	sb.append("<td>"+ locacao.getData() + "</td>");
	    	sb.append("<td>"+ locacao.getDevolucao() + "</td>");
	    	sb.append("<td>"+ locacao.getNome() + "</td>");
	      	out.println(sb.toString());
	    }
	  %>
	  </table>
</div>

<jsp:include page="footer.jsp" />