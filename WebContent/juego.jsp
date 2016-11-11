<%@ page import="negocio.CtrlCombate" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">
    
    <title>Combate!</title>

 <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/game.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

</head>
<% CtrlCombate ctrl=(CtrlCombate)session.getAttribute("ctrlCombate"); %>
<body>

<div align="center">

<table>
	<tr>
		<td class="lblJugador">
			<!-- Nombre jugador 1 -->
			<%= ctrl.getJugador1() %>
		</td>
		<td></td>
		<td class="lblJugador" align="right">
		<!-- Nombre jugador 2 -->
			<%= ctrl.getJugador2() %>
		</td>
	</tr>
	<tr>
		<td>
			<div class="progress">
				<div class="progress-bar progress-bar-success" role="progressbar"
					style="width: <%= (ctrl.getJugador1().getVidaRestante()*100)/ctrl.getJugador1().getVida() %>%"></div>
				<span class="lblValores">
					<!-- Vida Jugador 1 -->
						<%= ctrl.getJugador1().getVidaRestante() %>
				</span>
			</div>
		</td>
		<td valign="top" align="center">
			<span class="lblVida">
				VIDA
			</span>
		</td>
		<td align="right">
		<div class="progress">
				<div class="progress-bar progress-bar-success" role="progressbar"
					style="width: <%= (ctrl.getJugador2().getVidaRestante()*100)/ctrl.getJugador2().getVida() %>%; display: block; float: right;"></div>
				<span class="lblValores">
					<!-- Vida Jugador 2 -->
						<%= ctrl.getJugador2().getVidaRestante() %>
				</span>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div class="progress">
				<div class="progress-bar" role="progressbar"
					style="width: <%= (ctrl.getJugador1().getEnergiaRestante()*100)/ctrl.getJugador1().getEnergia() %>%"></div>
				<span class="lblValores">
				<!-- Energía jugador 1 -->
					<%= ctrl.getJugador1().getEnergiaRestante() %>
				</span>
			</div>
		</td>
		<td valign="top" align="center">
			<span class="lblEnergia">
				ENERGÍA
			</span>
		</td>
		<td align="right">
			<div class="progress">
				<div class="progress-bar" role="progressbar"
					style="width: <%= (ctrl.getJugador2().getEnergiaRestante()*100)/ctrl.getJugador2().getEnergia() %>%; display: block; float: right;"></div>
				<span class="lblValores">
				<!-- Energía jugador 2 -->
					<%= ctrl.getJugador2().getEnergiaRestante() %>
				</span>
			</div>
		</td>
	</tr>
</table>

</div>

<div class="turno" align="center">
	<h3>
		TURNO
	</h3>
	<h2>
		<%= ctrl.getTurno() %>
	</h2>
</div>

<div align="center">
	<form class="form-atacar" action="atacar" method="post">
		<input type="text" name="PuntosAtaque" id="PuntosAtaque" class="form-control" required autofocus>
		<button class="btn btn-lg btn-primary btn-block" type="submit">ATACAR</button>
	</form>
	<form class="form-defender" action="defender" method="post">
		<button class="btn btn-lg btn-primary btn-block" type="submit">DEFENDER</button>
	</form>
</div>

<div align="center" class="error">
	<% 	String mensaje="";
		if(request.getAttribute("Error")!=null) mensaje=(String)request.getAttribute("Error");  %>
	<%= mensaje %>
</div>


</body>

<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>

</html>