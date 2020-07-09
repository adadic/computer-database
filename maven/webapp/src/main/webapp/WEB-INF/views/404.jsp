<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
<link rel="shortcut icon" href="#">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
			<c:choose>
				<c:when test="">
					<button class="connexionButton" id="logout"
						onclick="$('#logoutPage').click()">Disconnect</button>
					<form style="display: none;" action="logout" method="POST">
						<input type="submit" id="logoutPage">
					</form>
				</c:when>
				<c:otherwise>
					<button class="connexionButton" id="login"
						onclick="window.location.replace('login')">Connect</button>
				</c:otherwise>
			</c:choose>
			<button class="langButton flagEN" id="english"
				onclick="window.location.replace('?lang=en')"></button>
			<button class="langButton flagFR" id="french"
				onclick="window.location.replace('?lang=fr')"></button>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 404: Page not found. Too bad bitch! <br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/dashboard.js"></script>

</body>
</html>