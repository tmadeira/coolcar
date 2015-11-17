<%@page import="coolcar.bd.BD, coolcar.modelos.*, coolcar.managers.* ,java.util.ArrayList, java.util.Iterator"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%
  BD bd = new BD();

  ClientePF cpf_teste = new ClientePF();
  cpf_teste.setEmail("adm@");
  cpf_teste.setSenha("senha123");
  
  Carro carro_teste = new Carro();
  carro_teste.setIdModelo(1);
  
  Moto moto_teste = new Moto();
  moto_teste.setCilindradas(150);

  ClientePFManager cpf_manager = new ClientePFManager();
  CarroManager carro_manager = new CarroManager();	
  MotoManager moto_manager = new MotoManager();

  ArrayList<ClientePF> resultados = cpf_manager.consulta(cpf_teste);
  ArrayList<Carro> carros = carro_manager.consulta(carro_teste);
  ArrayList<Moto> motos = moto_manager.consulta(moto_teste);
  
  // ResultSet resultados = bd.executaConsulta("SELECT nome FROM Usuario WHERE endereco_logradouro LIKE '%'");

  out.println("<ul>");
  Iterator<Moto> i = motos.iterator();
  while (i.hasNext()) {
	Moto j = i.next();
    String nome = j.getNome();
    out.println("<li>" + nome  + "</li>");
  }
  out.println("</ul>");

%>