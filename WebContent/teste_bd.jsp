<%@page import="coolcar.bd.BD, coolcar.modelos.ClientePF, coolcar.managers.ClientePFManager,java.util.ArrayList, java.util.Iterator"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%
  BD bd = new BD();

  ClientePF cpf_teste = new ClientePF();
  cpf_teste.setEmail("adm@");
  cpf_teste.setSenha("senha123");
  
  ClientePFManager cpf_manager = new ClientePFManager();
	
  ArrayList<ClientePF> resultados = cpf_manager.consulta(cpf_teste);

  // ResultSet resultados = bd.executaConsulta("SELECT nome FROM Usuario WHERE endereco_logradouro LIKE '%'");

  out.println("<ul>");
  Iterator<ClientePF> i = resultados.iterator();
  while (i.hasNext()) {
	out.println("Ronaldo");
	ClientePF j = i.next();
    String nome = j.getNome();
    int id = j.getId();
    out.println("<li>" + nome  + " -- " + id + "</li>");
  }
  out.println("</ul>");

%>