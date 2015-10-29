<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp" />
<div class="jumbotron">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h2>Faça já sua reserva!</h2>
				<p>O que você está esperando?</p>
				<form>
					<div class="form-group">
						<label for="local-retirada">Local de retirada</label> <select
							class="form-control" id="local-retirada">
							<option>Selecione uma opção</option>
							<option>Butantã</option>
							<option>Consolação</option>
							<option>Jaguaré</option>
							<option>Paraíso</option>
						</select>
					</div>
					<div class="form-group">
						<label for="local-devolucao">Local de devolucao</label> <select
							class="form-control" id="local-devolucao">
							<option>Selecione uma opção</option>
							<option>Butantã</option>
							<option>Consolação</option>
							<option>Jaguaré</option>
							<option>Paraíso</option>
						</select>
					</div>
					<div class="checkbox">
						<label class="checkbox-inline"> <input type="checkbox">
							Ar condicionado
						</label> <label class="checkbox-inline"> <input type="checkbox">
							Seguro
						</label> <label class="checkbox-inline"> <input type="checkbox">
							GPS
						</label>
					</div>
					<button type="submit" class="btn btn-default">Buscar</button>
				</form>
			</div>
			<div class="col-md-6">
				<img
					src="http://cache3.asset-cache.net/gc/200450962-001-young-couple-in-car-looking-back-at-gettyimages.jpg?v=1&c=IWSAsset&k=2&d=m%2FAUGnDFt6xNebPUjVzJSX9pcHXoxKqVX18SYVLte6RCuHfGru9TTn5vfsl1AAeutValEgq2%2BulO2bWwl44voA%3D%3D"
					class="img-circle nice-img-border" style="max-width: 100%;" />
			</div>
		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-4 text-center">
			<img
				src="http://www.empowerengenharia.com.br/wp-content/uploads/2015/03/Folha_logo.gif"
				class="img-circle nice-img-border" width="140" height="140" />
			<h2>&ldquo;Simplesmente incrível.&rdquo;</h2>
			<p>Folha de S. Paulo, 3 de novembro de 2015</p>
		</div>
		<div class="col-md-4 text-center">
			<img
				src="http://static6.bornrichimages.com/cdn2/500/500/91/c/wp-content/uploads/2015/03/big1_thumb.jpg"
				class="img-circle nice-img-border" width="140" height="140" />
			<h2>&ldquo;Nunca mais vou usar outras locadoras de
				carros.&rdquo;</h2>
			<p>Bill Gates, Microsoft</p>
		</div>
		<div class="col-md-4 text-center">
			<img
				src="https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfa1/v/t1.0-1/c13.13.157.157/182654_529408713765479_2075633354_n.jpg?oh=180b54de9f048e6a9270855cf08c240e&oe=56D0E911&__gda__=1454969251_401a24abf9c6e9ad5bf8003eb77ce832"
				class="img-circle nice-img-border" width="140" height="140" />
			<h2>&ldquo;Enfim uma locadora de carros do século XXI.&rdquo;</h2>
			<p>O Globo, 7 de novembro de 2015</p>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />