<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="coolcar.UsuariosManager"%>
<jsp:include page="header.jsp" />

<div class="container">
 <h2>Conta criada com sucesso!</h2>
 
  <%
 
 String nome, sobrenome, id, email, password;
 nome = sobrenome = id = email = password = "";
 
 
	if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
		nome = request.getParameter("nome");
		}
	
	out.println("<h2>Nome "+ nome +"</h2>");
	
	if (request.getParameter("sobrenome") != null && !request.getParameter("sobrenome").isEmpty()) {
		sobrenome = request.getParameter("sobrenome");
		}
	
	if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
		id = request.getParameter("id");
		}
	
	if (request.getParameter("email") != null && !request.getParameter("email").isEmpty()) {
		email = request.getParameter("email");
		}
	
	if (request.getParameter("pwd") != null && !request.getParameter("pwd").isEmpty()) {
		password = request.getParameter("pwd");
		}
	UsuariosManager manager = new UsuariosManager();
	manager.cadastraUsuario(nome, sobrenome, id, email, password);
	
%>
 
</div>

<jsp:include page="footer.jsp" />