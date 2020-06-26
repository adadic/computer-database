<%@page language="java" contentType="text/html; charset=UTF-8"
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
			<button class="langButton flagEN" id="english"
				onclick="window.location.replace('?lang=en')"></button>
			<button class="langButton flagFR" id="french"
				onclick="window.location.replace('?lang=fr')"></button>
		</div>
	</header>

	<section id="main">
		<c:if test="${msg == -1}">
			<div class="container">
				<div class="alert alert-danger">
					<spring:message code="error.fieldAdd" />
					<br />
				</div>
			</div>
		</c:if>
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="form.titleAdd" />
					</h1>
					<form action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message
										code="form.computer" /></label> <input type="text"
									class="form-control" id="computerName" name="computerName"
									placeholder="<spring:message code="form.computer"/>" required>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message
										code="form.introduced" /></label> <input type="date" min="1970-01-01"
									onchange="setDate();" class="form-control" id="introduced"
									name="introduced"
									placeholder="<spring:message code="form.introduced"/>">
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message
										code="form.discontinued" /></label> <input type="date"
									class="form-control" id="discontinued" name="discontinued"
									placeholder="<spring:message code="form.discontinued"/>"
									disabled>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="form.company" /></label> <select class="form-control"
									id="companyId" name="companyId">
									<option value="0">--</option>

									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>

								</select>
							</div>
						</fieldset>
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