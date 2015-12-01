<%@page
 import="coolcar.modelos.*, coolcar.managers.* ,java.util.ArrayList, java.util.Iterator, java.util.Calendar, java.util.Date"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%
  ClientePF cpf_teste = new ClientePF();
//  cpf_teste.setEmail("adm@");
//  cpf_teste.setSenha("senha123");
  cpf_teste.setNome("Ronaldo %");
  
  Carro carro_teste = new Carro();
  carro_teste.setIdModelo(1);
  
  Moto moto_teste = new Moto();
  moto_teste.setCilindradas(150);
  
  Veiculo veiculo_teste = new Veiculo();
  veiculo_teste.setTipoDoVeiculo("Carro");
  veiculo_teste.setFilialAlojada(1);
  
  Calendar c = Calendar.getInstance();
  c.set(2015, Calendar.NOVEMBER, 15);
  Date data = c.getTime();
  Reserva reserva = new Reserva(7, 1, 2, 1, 1, 1, 150, data, data);  

  ClientesPFManager cpf_manager = new ClientesPFManager();
  CarrosManager carro_manager = new CarrosManager();	
  MotosManager moto_manager = new MotosManager();
  VeiculosManager veiculos_manager = new VeiculosManager();
  ReservasManager reserva_manager = new ReservasManager();

  ArrayList<ClientePF> resultados = cpf_manager.consulta(cpf_teste);
  ArrayList<Carro> carros = carro_manager.consulta(carro_teste);
  ArrayList<Moto> motos = moto_manager.consulta(moto_teste);
  ArrayList<Veiculo> veiculos = veiculos_manager.consulta(veiculo_teste);
  ArrayList<Reserva> reservas = reserva_manager.consulta(7);
  
  reserva_manager.insere(reserva);

  out.println("<ul>");
  Iterator<ClientePF> i = resultados.iterator();
  while (i.hasNext()) {
	ClientePF j = i.next();
    String nome = j.getNome();
    out.println("<li>" + nome + "</li>");
  }
  out.println("</ul>");
  
  

%>
