<%@page import="coolcar.Sessao, coolcar.modelos.Usuario"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>
 <%
 	if (request.getParameter("pageTitle") != null && !request.getParameter("pageTitle").isEmpty()) {
         out.println(request.getParameter("pageTitle") + " |");
       }
 %> coolCar
</title>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="css/coolcar.css" rel="stylesheet" />
</head>
<body>
 <nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
   <div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed"
     data-toggle="collapse" data-target="#navbar" aria-expanded="false"
     aria-controls="navbar">
     <span class="sr-only">Menu</span> <span class="icon-bar"></span> <span
      class="icon-bar"></span> <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="index.jsp">coolCar</a>
   </div>
   <div id="navbar" class="navbar-collapse collapse">
    <%
			Sessao s = new Sessao(request.getCookies());
			if (s.isLogged()) {
	%>
    <form class="navbar-form navbar-right" action="LogoutServlet"
     method="post">
     <div class="form-group">
      <font color="#bbbbbb">Bem vindo <strong> <% out.println(s.getNomeUsuario()); %>!
      </strong></font>
     </div>
     <button type="submit" class="btn btn-danger">Logout</button>
    </form>

    <form class="navbar-form navbar-right" action="reservas.jsp"
     method="post">
     <button type="submit" class="btn btn-primary">Histórico de
      Reservas</button>
    </form>


    <%
			} else {
		%>
    <form class="navbar-form navbar-right" action="cadastro.jsp"
     method="post">
     <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>

    <form class="navbar-form navbar-right" action="LoginServlet"
     method="post">
     <div class="form-group">
      <input type="text" placeholder="E-mail" class="form-control"
       name="userEmail">
     </div>
     <div class="form-group">
      <input type="password" placeholder="Senha" class="form-control"
       name="userPwd">
     </div>
     <button type="submit" class="btn btn-success">Entrar</button>
    </form>
    <%
			}
		%>
   </div>
  </div>
 </nav>