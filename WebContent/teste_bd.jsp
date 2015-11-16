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
  
  Veiculo veiculo_teste = new Veiculo();
  veiculo_teste.setTipoDoVeiculo("Carro");
  veiculo_teste.setFilialAlojada(1);


  ClientePFManager cpf_manager = new ClientePFManager();
  CarroManager carro_manager = new CarroManager();	
  MotoManager moto_manager = new MotoManager();
  VeiculosManager veiculos_manager = new VeiculosManager();

  ArrayList<ClientePF> resultados = cpf_manager.consulta(cpf_teste);
  ArrayList<Carro> carros = carro_manager.consulta(carro_teste);
  ArrayList<Moto> motos = moto_manager.consulta(moto_teste);
  ArrayList<Veiculo> veiculos = veiculos_manager.consulta(veiculo_teste);
  
  // ResultSet resultados = bd.executaConsulta("SELECT nome FROM Usuario WHERE endereco_logradouro LIKE '%'");

  out.println("<ul>");
  Iterator<Veiculo> i = veiculos.iterator();
  while (i.hasNext()) {
	Veiculo j = i.next();
    String nome = j.getCarro().getNome();
    int fil = j.getFilialAlojada();
    out.println("<li>" + nome  + fil +  "</li>");
  }
  out.println("</ul>");

%>