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
  veiculo_search.setStatus("Disponível");
  
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
	    sb.append("<tr><th width=\"30%\"> <h4><b>" + veiculo.getCarro().getNome() + "</b><br></h4>");
	    sb.append("<img src=\"" + veiculo.getCarro().getLink() + "\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"300\" height=\"300\"></th>");
	    sb.append("<td>");
	    
	    sb.append("<br>");
	    sb.append("<form name=\"listaVeiculos\" id=\"listaVeiculos\" action=\"realizaReserva.jsp\" method=\"post\">");
	    sb.append("<input type=\"hidden\" id=\"reservaModelo\" name=\"reservaModelo\" value=\"" + veiculo.getCarro().getIdModelo() + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaDiaria\" name=\"reservaDiaria\" value=\"" + veiculo.getCarro().getDiaria() + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaFilialRetirada\" name=\"reservaFilialRetirada\" value=\"" + request.getParameter("local-retirada") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaFilialDevolucao\" name=\"reservaFilialDevolucao\" value=\"" + request.getParameter("local-devolucao") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaDataRetirada\" name=\"reservaDataRetirada\" value=\"" + request.getParameter("dtRetirada") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaDataDevolucao\" name=\"reservaDataDevolucao\" value=\"" + request.getParameter("dtEntrega") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaLinkImagem\" name=\"reservaLinkImagem\" value=\"" + veiculo.getCarro().getLink() + "\" />");
	    sb.append("<button type=\"submit\" class=\"btn btn-warning\"><b>Reserve este modelo agora</b></button>");
	    sb.append("</form>");
	    
	    sb.append("<table><tr><td><h4><b>Preço da Diária: R$" + String.format("%.2f",veiculo.getCarro().getDiaria()) + " </b></h4></td></tr>");
	    sb.append("<tr><td><b> Ano:</b> " + veiculo.getAno() + " </td></tr>");
	    sb.append("<tr><td><b> Fabricante:</b> " + veiculo.getCarro().getFabricante() + " </td></tr>");
	    sb.append("<tr><td><b> Tipo:</b> " + veiculo.getCarro().getTipo() + " </td></tr>");
	    sb.append("<tr><td><b> Número de Portas:</b> " + veiculo.getCarro().getNumPortas() + " </td></tr>");
	    sb.append("<tr><td><b> Número de Assentos:</b> " + veiculo.getCarro().getNumAssentos() + " </td></tr>");
	    sb.append("<tr><td><b> Tamanho do Porta-Malas:</b> " + veiculo.getCarro().getTamanhoPortaMalas() + "L </td></tr>");
	    sb.append("<tr><td><b> Combustível:</b> " + veiculo.getCarro().getCombustivel() + " </td></tr>");
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
	    sb.append("<tr><th><h4><b>" + veiculo.getMoto().getNome() + "</b><br></h4>");
	    sb.append("<img src=\"" + veiculo.getMoto().getLink() + "\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"300\" height=\"300\"></th>");
	    sb.append("<td>");
	    
	    sb.append("<br>");
	    sb.append("<form name=\"listaVeiculos\" id=\"listaVeiculos\" action=\"realizaReserva.jsp\" method=\"post\">");
	    sb.append("<input type=\"hidden\" id=\"reservaModelo\" name=\"reservaModelo\" value=\"" + veiculo.getMoto().getIdModelo() + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaDiaria\" name=\"reservaDiaria\" value=\"" + veiculo.getMoto().getDiaria() + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaFilialRetirada\" name=\"reservaFilialRetirada\" value=\"" + request.getParameter("local-retirada") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaFilialDevolucao\" name=\"reservaFilialDevolucao\" value=\"" + request.getParameter("local-devolucao") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaDataRetirada\" name=\"reservaDataRetirada\" value=\"" + request.getParameter("dtRetirada") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaDataDevolucao\" name=\"reservaDataDevolucao\" value=\"" + request.getParameter("dtEntrega") + "\" />");
	    sb.append("<input type=\"hidden\" id=\"reservaLinkImagem\" name=\"reservaLinkImagem\" value=\"" + veiculo.getMoto().getLink() + "\" />");
	    sb.append("<button type=\"submit\" class=\"btn btn-warning\"><b>Reserve este modelo agora</b></button>");
	    sb.append("</form>");
	    
	    sb.append("<table><tr><td><h4><b>Preço da Diária: R$" + String.format("%.2f",veiculo.getMoto().getDiaria()) + " </b></h4></td></tr>");
	    sb.append("<tr><td><b> Ano: " + veiculo.getAno() + " </b></td></tr>");
	    sb.append("<tr><td><b> Fabricante: " + veiculo.getMoto().getFabricante() + " </b></td></tr>");
	    sb.append("<tr><td><b> Tipo: " + veiculo.getMoto().getTipo() + " </b></td></tr>");
	    sb.append("<tr><td><b> Combustível: " + veiculo.getMoto().getCombustivel() + " </b></td></tr>");
	    sb.append("<tr><td><b> Tamanho do Tanque: " + veiculo.getMoto().getTamanhoTanque() + "L </b></td></tr>");
	    sb.append("<tr><td><b> Cilindradas: " + veiculo.getMoto().getCilindradas() + " </b></td></tr>");
	    sb.append("</tr></table></td>");
	  }
  }
  out.println(sb.toString());
%>
</table>
</div>

<jsp:include page="footer.jsp" />