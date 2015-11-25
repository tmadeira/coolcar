<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="coolcar.managers.UsuariosManager"%>
<jsp:include page="header.jsp" />

<%
 
%>

<script src="vendor/jquery-1.11.3.min.js"></script>
<script src="vendor/jquery.maskedinput.js" type="text/javascript"></script>
<script src="maskCpf.js"></script>

<script>
jQuery(function($){
	$("#telefone").mask("(99) 9999-9999");
	$("#celular").mask("(99) 99999-9999");
	$("#cep").mask("99999-999")
});
</script>
<script> 
$(document).ready(function(){
	
	$("#cnpj").hide();
    $('#selectbasic').on('change', function() {
      if (this.value == "1")
      {
        $("#sobrenome").show();
        $("#dtNasc").show();
        $("#cpf").show();
        $("#sexo").show();
        $("#cnpj").hide();
        
        $("#sobrenome").prop('required', true);
        $("#dtNasc").prop('required', true);
        $("#cpf").prop('required', true);
        $("#sexo").prop('required', true);
        $("#cnpj").prop('required', false);
        $("data-de-nascimento").prop('required', true);
      }
      else
      {
        $("#sobrenome").hide();
        $("#dtNasc").hide();
        $("#cpf").hide();
        $("#sexo").hide();
        $("#cnpj").show();
        
        $("#sobrenome").prop('required', false);
        $("#dtNasc").prop('required', false);
        $("#cpf").prop('required', false);
        $("#sexo").prop('required', false);
        $("#cnpj").prop('required', true);
        $("data-de-nascimento").prop('required', false);
      }
    });
});
</script>

<div class="container">
 <h2>Crie sua conta</h2>

 <br />
 <h3>Dados pessoais</h3>
 <form class="form-horizontal" role="form" method="post"
  action="CadastroServlet">

  <div class="form-group">
   <label class="col-md-4 control-label" for="nome">*Tipo de
    pessoa:</label>
   <div class="col-md-5">
    <select id="selectbasic" class="form-control" name="selectbasic"
     required>
     <option value="1">Pessoa Física</option>
     <option value="2">Pessoa Jurídica</option>
    </select>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="nome">*Nome:</label>
   <div class="col-md-5">
    <input type="text" class="form-control" name="nome"
     placeholder="Maria" id="nome" required>
   </div>
  </div>

  <div class="form-group" id="sobrenome">
   <label class="col-md-4 control-label" for="sobrenome">*Sobrenome:</label>
   <div class="col-md-5">
    <input type="text" class="form-control" name="sobrenome"
     placeholder="Silveira" id="sobrenome" required>
   </div>
  </div>

  <div class="form-group" id="sexo">
   <label class="col-md-4 control-label" for="nome">*Sexo:</label>
   <div class="col-md-5">
    <select id="sexo" class="form-control" name="sexo" required>
     <option value="1">M</option>
     <option value="2">F</option>
    </select>
   </div>
  </div>

  <div class="form-group" id="dtNasc">
   <label class="col-md-4 control-label" for="data-de-nascimento">*Data
    de Nascimento:</label>
   <div class="col-md-2">
    <input type="date" class="form-control" name="dtNascimento"
     id="data-de-nascimento" required>
   </div>
  </div>

  <div class="form-group" id="cpf">
   <label class="col-md-4 control-label" for="cpf">*CPF:</label>
   <div class="col-md-2">
    <input type="text" class="form-control" name="cpf"
     onblur="javascript: validarCPF(this.value);"
     onkeypress="javascript: mascara(this, cpf_mask);" maxlength="14"
     placeholder="11122233344" id="cpf" required>
   </div>
  </div>

  <div class="form-group" id="cnpj">
   <label class="col-md-4 control-label" for="cnpj">*CNPJ:</label>
   <div class="col-md-2">
    <input type="text" class="form-control" name="cnpj"
     placeholder="11122233344" id="cnpj">
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="telefone">Telefone:</label>
   <div class="col-md-2">
    <input type="text" class="form-control" name="telefone"
     placeholder="1144448888" id="telefone">
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="celular">*Celular:</label>
   <div class="col-md-2">
    <input type="text" class="form-control" name="celular"
     placeholder="11333335555" id="celular" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="logradouro">*Logradouro:</label>
   <div class="col-md-5">
    <input type="text" class="form-control" name="logradouro"
     placeholder="Rua" id="logradouro" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="numero">*Número:</label>
   <div class="col-md-1">
    <input type="text" class="form-control" name="numero"
     placeholder="55" id="numero" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="complemento">Complemento:</label>
   <div class="col-md-2">
    <input type="text" class="form-control" name="complemento"
     placeholder="Bloco 1 Ap 2" id="complemento">
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="cep">*CEP:</label>
   <div class="col-md-2">
    <input type="text" class="form-control" name="cep"
     placeholder="55555333" id="cep" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="cidade">*Cidade:</label>
   <div class="col-md-5">
    <input type="text" class="form-control" name="cidade"
     placeholder="São Paulo" id="cidade" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="estado">*Estado:</label>
   <div class="col-md-1">
    <input type="text" style="text-transform: uppercase" maxlength="2"
     class="form-control" name="estado" placeholder="SP" id="estado"
     required>
   </div>
  </div>

  <br />
  <h3>Dados de acesso</h3>


  <div class="form-group">
   <label class="col-md-4 control-label" for="email">*Email:</label>
   <div class="col-md-5">
    <input type="email" class="form-control" name="email"
     placeholder="maria@coolcar.com" id="email" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="email">*Confirme
    seu email:</label>
   <div class="col-md-5">
    <input type="email" class="form-control" name="confirmacaoDeEmail"
     placeholder="maria@coolcar.com" id="confirmacaoDeEmail" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="pwd">*Senha de
    acesso (8-15 caracteres):</label>
   <div class="col-md-5">
    <input type="password" class="form-control" placeholder="*****"
     pattern=".{8,15}" name="pwd" id="pwd" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="pwd">*Confirme sua
    senha:</label>
   <div class="col-md-5">
    <input type="password" class="form-control" placeholder="*****"
     pattern=".{8,15}" name="confirmacaoDePassword"
     id="confirmacaoDePassword" required>
   </div>
  </div>

  <div class="form-group">
   <label class="col-md-4 control-label" for="button"></label>
   <div class="col-md-4">
    <button id="button" type="submit" class="btn btn-default">Criar
     Conta</button>
   </div>
  </div>
 </form>
</div>

<jsp:include page="footer.jsp" />
