<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="coolcar.managers.UsuariosManager"%>
<jsp:include page="header.jsp" />

<%

%>

<div class="container">
 <h2>Crie sua conta</h2>
 
 	<br />
 	<h3> Dados pessoais </h3>
	 <form role="form" method="post" action="CadastroServlet">
		
		<div class="form-group">
		   <label for="Nome">*Nome:</label>
	    <input type="text" class="form-control" name="nome" placeholder="Maria" id="nome" required>
	    </div>
	      
		<div class="form-group">
		   <label for="Sobrenome">*Sobrenome:</label>
	    <input type="text" class="form-control" name="sobrenome" placeholder="Silveira" id="sobrenome" required>
	    </div>
	    
		<div class="form-group">
		   <label for="data-de-nascimento">Data de Nascimento:</label>
	    <input type="date" class="form-control" id="data-de-nascimento">
	    </div>
	    
		<div class="form-group">
		   <label for="cpf">*CPF:</label>
		<input type="text" class="form-control" name="cpf" placeholder="11122233344" id="cpf" required>   
	    </div>
	    
		<div class="form-group">
		   <label for="telefone">Telefone:</label>
	    <input type="tel" class="form-control" name="telefone" placeholder="88887777" id="telefone">
	    </div>
	    
		<div class="form-group">
		   <label for="celular">*Celular:</label>
	    <input type="tel" class="form-control" name="celular" placeholder="988887777" id="celular" required>
	    </div>
	 
	 <br />
	 <h3> Dados de acesso</h3>
	 
	      
		<div class="form-group">
		   <label for="email">*Email:</label>
	    <input type="email" class="form-control" name="email" placeholder="maria@coolcar.com" id="email" required>
	    </div>
	     
	    <div class="form-group">
		   <label for="email">*Confirme seu email:</label>
	    <input type="email" class="form-control" name="confirmacaoDeEmail" placeholder="maria@coolcar.com" id="confirmacaoDeEmail" required>
	    </div>
	     
		 <div class="form-group">
		    <label for="pwd">*Senha de acesso:</label>
		    <input type="password" class="form-control" placeholder="*****" name="pwd" id="pwd" required>
		  </div>
		  
		 <div class="form-group">
		    <label for="pwd">*Confirme sua senha:</label>
		    <input type="password" class="form-control" placeholder="*****" name="confirmacaoDePassword" id="confirmacaoDePassword" required>
		 </div>
		  
		 
		  <button type="submit" class="btn btn-default">Criar Conta</button>
	</form>
</div>

<jsp:include page="footer.jsp" />