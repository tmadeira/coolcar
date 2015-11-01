<%@page import="coolcar.bd.BD,java.sql.ResultSet"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%
  BD bd = new BD();
  bd.executaAtualizacao("DROP TABLE IF EXISTS teste");
  bd.executaAtualizacao("CREATE TABLE teste (id INTEGER PRIMARY KEY)");
  bd.executaAtualizacao("INSERT INTO teste VALUES (5), (23), (42), (7)");
  ResultSet resultados = bd.executaConsulta("SELECT id FROM teste");

  out.println("<ul>");
  while (resultados.next()) {
    int id = resultados.getInt("id");
    out.println("<li>" + id + "</li>");
  }
  out.println("</ul>");

  bd.executaAtualizacao("DROP TABLE IF EXISTS teste");
%>