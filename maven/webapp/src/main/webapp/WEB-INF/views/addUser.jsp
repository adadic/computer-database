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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/function.js"></script>
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
		<c:if test="${msg == -1}">
			<div class="container">
				<div class="alert alert-danger">
					<spring:message code="error.user" />
					<br />
				</div>
			</div>
		</c:if>
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="form.titleUser" />
					</h1>
					<form action="addUser" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="userName"><spring:message code="form.user" /></label>
								<input type="text" class="form-control" id="userName"
									name="username"
									placeholder="<spring:message code="form.user"/>" required>
							</div>
							<!-- 
							<div class="form-group">
								<label for="roleId"><spring:message code="form.role" /></label>
								<select class="form-control"
									onchange="$('#roleName').val($('#roleId [value = ' + $('#roleId').val() + ']').text())"
									id="roleId" name="roleId">
									<c:forEach items="${role}" var="roles">
										<option value="${roles.id}">${roles.roleName}</option>
									</c:forEach>

								</select>
							</div>
							 -->

							<div class="form-group">
								<label for="introduced"><spring:message
										code="form.password" /></label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="<spring:message code="form.password"/>">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="passwordCheck"
									name="passwordCheck"
									placeholder="<spring:message code="form.password"/>">
							</div>
						</fieldset>
						<input type="hidden" value="" name="roleName" id="roleName" />
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="form.add"/>"
								class="btn btn-primary"> or <a href="dashboard"
								class="btn btn-default"><spring:message code="form.cancel"
									text="default" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>