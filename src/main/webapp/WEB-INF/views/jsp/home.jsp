<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Diego da Silva Rodrigues">
<title>Vote No Restaurante</title>
<link href="<c:url value="/resources/dist/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/examples/jumbotron-narrow/jumbotron-narrow.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/assets/js/ie-emulation-modes-warning.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/js/home.js" />"></script>
</head>

<body>
	<div class="container">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation" class="active"><a href="./">Home</a></li>
				</ul>
			</nav>
			<h3 class="text-muted">Bem vindo ao Vote No Restaurante!</h3>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<p>Participe mais dessa pesquisa e contribua eleger o melhor restaurante.</p>
				</div>
			</div>
		</div>

		<div class="jumbotron">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<p id="message">Qual sua preferência?</p>
					</div>
					<div class="col-md-6">
						<div id="restaurantLeft" data-id="" style="float: left;">
							<img id="imgLeft"></img><br /> <span id="nameLeft"></span>
						</div>
					</div>
					<div class="col-md-6">
						<div id="restaurantRight" data-id="">
							<img id="imgRight"></img><br /> <span id="nameRight"></span>
						</div>
					</div>
				</div>
				<div class="row" id="progress">
					<div class="col-md-12">
						<div class="progress">
							<div class="progress-bar" id="progress-bar" role="progressbar" aria-valuenow="5" aria-valuemin="0" aria-valuemax="100"
								style="width: 5%;">0%</div>
						</div>
					</div>
				</div>
				<div class="row" id="register" style="display: none">
					<div class="col-md-12">
						<div class="alert alert-danger" role="alert" id="erroAlert" style="display: none"></div>
					</div>
					<div class="col-md-12">
						<form action="voting" id="formUser">
							<span>Nome:</span> <input name="name" id="name"></input> <span>E-mail:</span> <input name="email" id="email"></input> <input
								id="votes" name="votes" style="display: none"></input> <input type="submit" value="Cadastrar"></input>
						</form>
					</div>
				</div>
			</div>
		</div>

		<footer class="footer">
			<p>&copy; Vote No Restaurante 2015</p>
		</footer>
	</div>
	<script src="<c:url value="/resources/assets/js/ie10-viewport-bug-workaround.js"/>"></script>
</body>
</html>