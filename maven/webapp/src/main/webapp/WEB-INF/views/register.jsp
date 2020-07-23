<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/function.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"><spring:message
					code="main.title" /></a>
			<button class="btn connexionButton" id="login"
				onclick="window.location.replace('login')">
				<spring:message code="conn.connexion" />
			</button>
			<button class="btn langButton flagEN" id="english"
				onclick="window.location.replace('?lang=en')"></button>
			<button class="btn langButton flagFR" id="french"
				onclick="window.location.replace('?lang=fr')"></button>
		</div>
	</header>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<form method="post" action="register" class="form">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" id="username" name="username"
							placeholder="name">
					</div>

					

					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="password">
					</div>
				<div class="form-group">
				<label>Matching Password</label>
				<input type="password" name="matchingPassword" class="form-control" />
				</div>

					<!-- 
					<div class="form-group">
						<label for="role">Example select</label> <select
							class="form-control" id="role" name="role">
							<option>ADMIN</option>
							<option>USER</option>
						</select>
					</div>
					 -->
					<div>
						<input type="checkbox" required="required"> I accept the <a
							href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a>
					</div>
					<button class="btn btn-primary" type="submit">Create user</button>
				</form>

			</div>
		</div>
	</div>
</body>
</html>