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
 
<%
  Veiculo veiculo_search = new Veiculo();
  veiculo_search.setTipoDoVeiculo(request.getParameter("tipo-veiculo"));
  veiculo_search.setFilialAlojada(Integer.parseInt(request.getParameter("local-retirada")));

  StringBuilder sb = new StringBuilder();
  out.println();
  
  // TODO: se for carro, seta arcond, dirhid e cambio
  if(request.getParameter("tipo-veiculo").equals("Carro")){
	  
	  if(request.getParameter("arcond") != null) 
		  veiculo_search.getCarro().getCarac().setArCond(true);
	  else 
		  veiculo_search.getCarro().getCarac().setArCond(false);
	  if(request.getParameter("dirhidri") != null) 
		  veiculo_search.getCarro().getCarac().setDirHid(true);
	  else 
		  veiculo_search.getCarro().getCarac().setDirHid(false);
	  if(request.getParameter("cambauto") != null) 
		  veiculo_search.getCarro().getCarac().setCambAuto(true);
	  else 
		  veiculo_search.getCarro().getCarac().setCambAuto(false);
	  
	  VeiculosManager veiculos_manager = new VeiculosManager();
	  ArrayList<Veiculo> veiculos = veiculos_manager.consulta(veiculo_search);
	  
	  for (Veiculo veiculo : veiculos) {       
	    sb.append("<tr><th> <h4>" + veiculo.getCarro().getNome() + "<br></h4>");
	    sb.append("<img src=\"" + veiculo.getCarro().getLink() + "\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"300\" height=\"300\"></th>");
	    sb.append("<td>");
	    
	    sb.append("<table><tr><td> <u>Preço da Diária:</u> R$" + String.format("%.2f",veiculo.getCarro().getDiaria()) + " </td></tr>");
	    sb.append("<tr><td><b> Ano:</b> " + veiculo.getAno() + " </td></tr>");
	    sb.append("<tr><td><b> Fabricante:</b> " + veiculo.getCarro().getFabricante() + " </td></tr>");
	    sb.append("<tr><td><b> Tipo:</b> " + veiculo.getCarro().getTipo() + " </td></tr>");
	    sb.append("<tr><td><b> Número de Portas:</b> " + veiculo.getCarro().getNumPortas() + " </td></tr>");
	    sb.append("<tr><td><b> Número de Assentos:</b> " + veiculo.getCarro().getNumAssentos() + " </td></tr>");
	    sb.append("<tr><td><b> Tamanho do Porta-Malas:</b> " + veiculo.getCarro().getTamanhoPortaMalas() + "L </td></tr>");
	    sb.append("<tr><td><b> Combustível:</b> " + veiculo.getCarro().getCombustivel() + " </td></tr>");
	    sb.append("<tr><td><b> Num Assentos:</b> " + veiculo.getCarro().getNumAssentos() + " </td></tr>");
	    if(veiculo.getCarro().getCarac().getArCond())
	    	sb.append("<tr><td> Ar Condicionado </td></tr>");
	    if(veiculo.getCarro().getCarac().getCambAuto())
	    	sb.append("<tr><td> Câmbio Automático </td></tr>");
	    else
	    	sb.append("<tr><td> Câmbio Manual </td></tr>");
	    if(veiculo.getCarro().getCarac().getDirHid())
	    	sb.append("<tr><td> Direção Hidráulica </td></tr>");
	    
	    
	    sb.append("</table></td></tr>");
	  }
  } else {
	  VeiculosManager veiculos_manager = new VeiculosManager();
	  ArrayList<Veiculo> veiculos = veiculos_manager.consulta(veiculo_search);
	
	  for (Veiculo veiculo : veiculos) {       
	    sb.append("<tr><th> " + veiculo.getMoto().getNome() + "<br>");
	    sb.append("<img src=\"" + veiculo.getMoto().getLink() + "\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"300\" height=\"300\"></th>");
	    sb.append("<td>");
	    
	    sb.append("<table><tr><td> <u>Preço da Diária:</u> R$" + String.format("%.2f",veiculo.getMoto().getDiaria()) + " </td></tr>");
	    sb.append("<tr><td> Ano: " + veiculo.getAno() + " </td></tr>");
	    sb.append("<tr><td> Fabricante: " + veiculo.getMoto().getFabricante() + " </td></tr>");
	    sb.append("<tr><td> Tipo: " + veiculo.getMoto().getTipo() + " </td></tr>");
	    sb.append("<tr><td> Combustível: " + veiculo.getMoto().getCombustivel() + " </td></tr>");
	    sb.append("<tr><td> Tamanho do Tanque: " + veiculo.getMoto().getTamanhoTanque() + "L</td></tr>");
	    sb.append("<tr><td> Cilindradas: " + veiculo.getMoto().getCilindradas() + " </td></tr>");
	    sb.append("</tr></table></td>");
	  }
  }
  out.println(sb.toString());
%>
</table>
</div>

<jsp:include page="footer.jsp" />