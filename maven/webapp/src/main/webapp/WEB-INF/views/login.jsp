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
			<button class="langButton flagEN" id="english"
				onclick="window.location.replace('?lang=en')"></button>
			<button class="langButton flagFR" id="french"
				onclick="window.location.replace('?lang=fr')"></button>
		</div>
	</header>

	<section id="main">
		<c:if test="${not empty error}">
			<div class="container">
				<div class="alert alert-danger">
					<spring:message code="error.login" />
					<br />
				</div>
			</div>
		</c:if>
		<div class="container">
			<form name='f' action="login" method="POST">
				<div>
					<label><spring:message code="login.user" /><input
						type="text" class="form-control" name="username"
						placeholder="<spring:message code="login.user"/>" /> </label>
				</div>
				<div>
					<label><spring:message code="login.pwd" /><input
						type="password" class="form-control" name="password"
						placeholder="<spring:message code="login.pwd"/>" /> </label>
				</div>
				<div>
					<input type="submit" value="<spring:message code="login.signin" />"
						class="btn btn-primary" />
				</div>
			</form>
		</div>
	</section>
</body>
</html>