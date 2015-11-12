<%@page import="coolcar.bd.BD,java.sql.ResultSet"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%
  BD bd = new BD();

  ResultSet resultados = bd.executaConsulta("SELECT nome FROM Filial");

  out.println("<ul>");
  while (resultados.next()) {
    String nome = resultados.getString("nome");
    out.println("<li>" + nome + "</li>");
  }
  out.println("</ul>");

  bd.executaAtualizacao("DROP TABLE IF EXISTS teste");
%>