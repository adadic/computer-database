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
			<a class="navbar-brand" href="dashboard"><spring:message
					code="main.title" /></a>
			<button class="btn connexionButton" id="logout"
				onclick="$('#logoutPage').click()">
				<spring:message code="conn.logout" />
			</button>
			<form style="display: none;" action="logout" method="POST">
				<input type="submit" id="logoutPage">
			</form>
			<button class="btn langButton flagEN" id="english"
				onclick="window.location.replace('?lang=en')"></button>
			<button class="btn langButton flagFR" id="french"
				onclick="window.location.replace('?lang=fr')"></button>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 403: Access denied! <br />
			</div>
		</div>
	</section>

	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/dashboard.js"></script>

</body>
</html>