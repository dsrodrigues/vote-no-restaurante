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
					<p>Confira abaixo o andamento da Pesquisa.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading" style="text-align: center">Sua Votação</div>

						<!-- Table -->
						<c:choose>
							<c:when test="${userRestaurantRanking == null || userRestaurantRanking.isEmpty()} ">
						  		Nenhum resultado encontrado para o E-mail informado.
						  	</c:when>
							<c:otherwise>
								<table class="table">
									<tr>
										<th>Restaurante</th>
										<th style="text-align: center">Nível de Preferência</th>
									</tr>
									<c:forEach var="voting" items="${userRestaurantRanking}">
										<tr>
											<td>${voting.restaurant.name}</td>
											<td style="text-align: center">${voting.vote}</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading" style="text-align: center">Votação</div>

						<!-- Table -->
						<table class="table">
							<tr>
								<th>Restaurante</th>
								<th style="text-align: center">Nível de Prferência</th>
							</tr>
							<c:forEach var="restaurant" items="${restaurantRanking}">
								<tr>
									<td>${restaurant.name}</td>
									<td style="text-align: center">${restaurant.vote}</td>
								</tr>
							</c:forEach>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>