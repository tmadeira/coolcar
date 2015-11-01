<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="coolcar.UsuariosManager"%>
<jsp:include page="header.jsp" />

<%

String nome, sobrenome, id, email, password;
nome = sobrenome = id = email = password = ""; 

	if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
		nome = request.getParameter("nome");
		}
	
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

<div class="container">
 <h2>Crie sua conta</h2>
 
 	<br />
 	<h3> Dados pessoais </h3>
	 <form role="form" method="post" action="criaConta.jsp">
	 	<div class="form-group">
	      <label for="Tratamento">Tratamento</label> <select
	       class="form-control" id="tratamento">
	       <option>Selecione</option>
	       <option>Sr.</option>
	       <option>Sra.</option>
	      </select>
	      
	    </div>
		
		<div class="form-group">
		   <label for="Nome">*Nome:</label>
	    <input type="text" class="form-control" name="nome" id="nome" required>
	    </div>
	      
		<div class="form-group">
		   <label for="Sobrenome">*Sobrenome:</label>
	    <input type="text" class="form-control" name="sobrenome" id="sobrenome" required>
	    </div>
	    
		<div class="form-group">
		   <label for="data-de-nascimento">Data de Nascimento:</label>
	    <input type="date" class="form-control" id="data-de-nascimento">
	    </div>
	    
		<div class="form-group">
		   <label for="cpf-passaporte">*CPF/Passaporte:</label>
		<input type="text" class="form-control" name="id" id="cpf-passaporte" required>   
	    </div>
	    
		<div class="form-group">
		   <label for="telefone">Telefone:</label>
	    <input type="tel" class="form-control" id="telefone">
	    </div>
	    
		<div class="form-group">
		   <label for="celular">*Celular:</label>
	    <input type="tel" class="form-control" id="celular" required>
	    </div>
	 
	 <br />
	 <h3> Dados de acesso</h3>
	 
	      
		<div class="form-group">
		   <label for="email">*Email:</label>
	    <input type="email" class="form-control" name="email" id="email" required>
	    </div>
	     
	    <div class="form-group">
		   <label for="email">*Confirme seu email:</label>
	    <input type="email" class="form-control" id="confirmeEmail" required>
	    </div>
	     
		 <div class="form-group">
		    <label for="pwd">*Senha de acesso:</label>
		    <input type="password" class="form-control" name="pwd" id="pwd" required>
		  </div>
		  
		 <div class="form-group">
		    <label for="pwd">*Confirme sua senha:</label>
		    <input type="password" class="form-control" id="confirmePwd" required>
		 </div>
		  
		 
		  <button type="submit" class="btn btn-default">Criar Conta</button>
	</form>
</div>

<jsp:include page="footer.jsp" />