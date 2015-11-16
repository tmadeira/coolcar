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
	    sb.append("<tr><th> " + veiculo.getCarro().getNome() + "");
	    sb.append("<img src=\"" + veiculo.getCarro().getLink() + "\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"180\" height=\"180\"></th>");
	    sb.append("<td>");
	    sb.append("<table><tr><td> Chassi: " + veiculo.getChassi() + " </td></tr>");
	    sb.append("<tr><td> Num Assentos: " + veiculo.getCarro().getNumAssentos() + " </td></tr>");
	    sb.append("</tr></table></td>");
	  }
  } else {
	  VeiculosManager veiculos_manager = new VeiculosManager();
	  ArrayList<Veiculo> veiculos = veiculos_manager.consulta(veiculo_search);
	
	  for (Veiculo veiculo : veiculos) {       
	    sb.append("<tr><th> " + veiculo.getCarro().getNome() + "");
	    sb.append("<img src=\"" + veiculo.getCarro().getLink() + "\" alt=\"...\" class=\"img-responsive img-thumbnail\" width=\"180\" height=\"180\"></th>");
	    sb.append("<td>");
	    sb.append("<table><tr><td> Chassi: " + veiculo.getChassi() + " </td></tr>");
	    sb.append("<tr><td> Num Assentos: " + veiculo.getCarro().getNumAssentos() + " </td></tr>");
	    sb.append("</tr></table></td>");
	  }
  }
  out.println(sb.toString());
%>
</table>
</div>

<jsp:include page="footer.jsp" />