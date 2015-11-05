<%@page import="java.util.ArrayList"%>
<%@page import="coolcar.ModelosManager,coolcar.Modelo"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Resultados da busca" />
</jsp:include>

<div class="container">
 <h2>Resultados</h2>
 <ul>
  <%
    ModelosManager manager = new ModelosManager();
    ArrayList<Modelo> modelos = manager.buscaModelos();
    for (Modelo modelo : modelos) {
      out.println("<li>" + modelo.getNome() + "</li>");
    }
  %>
 </ul>
</div>

<jsp:include page="footer.jsp" />