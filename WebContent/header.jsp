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
    <a class="navbar-brand" href="#">coolCar</a>
   </div>
   <div id="navbar" class="navbar-collapse collapse">
    <form class="navbar-form navbar-right">
     <div class="form-group">
      <input type="text" placeholder="E-mail" class="form-control">
     </div>
     <div class="form-group">
      <input type="password" placeholder="Senha" class="form-control">
     </div>
     <button type="submit" class="btn btn-success">Entrar</button>
    </form>
   </div>
  </div>
 </nav>