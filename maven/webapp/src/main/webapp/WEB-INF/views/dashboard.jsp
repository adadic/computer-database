<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="shortcut icon" href="#">
</head>
<body>

	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<sec:authorize access="isAuthenticated()">
				<button class="btn connexionButton" id="logout"
					onclick="$('#logoutPage').click()">
					<spring:message code="conn.logout" />
				</button>
				<form style="display: none;" action="logout" method="POST">
					<input type="submit" id="logoutPage">
				</form>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<button class="btn connexionButton" id="login"
					onclick="window.location.replace('login')">
					<spring:message code="conn.connexion" />
				</button>
			</sec:authorize>
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="main.title" />
			</a>
			<button class="btn langButton flagEN" id="english"
				onclick="window.location.replace('?lang=en')"></button>
			<button class="btn langButton flagFR" id="french"
				onclick="window.location.replace('?lang=fr')"></button>
		</div>
	</header>

	<section id="main">
		<c:if test="${not empty msg}">
			<c:choose>
				<c:when test="${msg == 1}">
					<div class="container">
						<div class="success">
							<spring:message code="success.delete" />
							<br />
						</div>
					</div>
				</c:when>
				<c:when test="${msg == 2}">
					<div class="container">
						<div class="success">
							<spring:message code="success.add" />
							<br />
						</div>
					</div>
				</c:when>
				<c:when test="${msg == 3}">
					<div class="container">
						<div class="success">
							<spring:message code="success.update" />
							<br />
						</div>
					</div>
				</c:when>
				<c:when test="${msg == 4}">
					<div class="container">
						<div class="success">
							<spring:message code="success.user" />
							<br />
						</div>
					</div>
				</c:when>
				<c:when test="${msg == -1}">
					<div class="container">
						<div class="alert alert-danger">
							<spring:message code="error.uri" />
							<br />
						</div>
					</div>
				</c:when>
				<c:when test="${msg == -2}">
					<div class="container">
						<div class="alert alert-danger">
							<spring:message code="error.delete" />
							<br />
						</div>
					</div>
				</c:when>
				<c:when test="${msg == -3}">
					<div class="container">
						<div class="alert alert-danger">
							<spring:message code="error.exist" />
							<br />
						</div>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>

		</c:if>
		<div class="container">
			<h1 id="homeTitle">${size}
				<spring:message code="dashboard.title" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message
							code="search.label"/>" /> <input
							type="submit" id="searchsubmit"
							value="<spring:message
							code="search.button"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<sec:authorize access="hasRole('ADMIN')">
						<a class="btn btn-primary" id="addUser" href="addUser"><spring:message
								code="add.button.user" /></a>
					</sec:authorize>
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							code="add.button" /></a>
					<sec:authorize access="hasRole('ADMIN')">
						<a class="btn btn-default" id="editComputer" href="#"
							onclick="$.fn.toggleEditMode();"><spring:message
								code="edit.button" /></a>
					</sec:authorize>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="delete" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<c:choose>
							<c:when
								test="${fn:contains(order,'computer') and direction == 1}">
								<th onclick="sortTable('computer', 0, ${lines}, '${search}');"><spring:message
										code="table.computer" /></th>
							</c:when>
							<c:otherwise>
								<th onclick="sortTable('computer', 1, ${lines}, '${search}');"><spring:message
										code="table.computer" /></th>
							</c:otherwise>
						</c:choose>
						<th><spring:message code="table.introduced" /></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="table.discontinued" /></th>
						<!-- Table header for Company -->
						<c:choose>
							<c:when test="${fn:contains(order,'company') and direction == 1}">
								<th onclick="sortTable('company', 0, ${lines}, '${search}');"><spring:message
										code="table.company" /></th>
							</c:when>
							<c:otherwise>
								<th onclick="sortTable('company', 1, ${lines}, '${search}');"><spring:message
										code="table.company" /></th>
							</c:otherwise>
						</c:choose>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="editComputer?id=${computer.id}" onclick="">${computer.name}</a>
							</td>
							<td>${fn:substring(computer.introduced,0,10)}</td>
							<td>${fn:substring(computer.discontinued,0,10)}</td>
							<td>${computer.company.name}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">

				<c:choose>
					<c:when test="${max < 5}">
						<c:choose>
							<c:when test="${page > 1}">
								<li><a
									href="?order=${order}&direction=${direction}&page=${page - 1}&lines=${lines}&search=${search}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach var="i" begin="1" end="${max}">
							<li><a
								href="?order=${order}&direction=${direction}&page=${i}&lines=${lines}&search=${search}">${i}</a></li>
						</c:forEach>

						<c:choose>
							<c:when test="${page < max}">
								<li><a
									href="?order=${order}&direction=${direction}&page=${page + 1}&lines=${lines}&search=${search}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>


					</c:when>
					<c:when test="${page > max-2}">
						<li><a
							href="?order=${order}&direction=${direction}&page=${page-1}&lines=${lines}&search=${search}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${max-4}&lines=${lines}&search=${search}">${max-4}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${max-3}&lines=${lines}&search=${search}">${max-3}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${max-2}&lines=${lines}&search=${search}">${max-2}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${max-1}&lines=${lines}&search=${search}">${max-1}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${max}&lines=${lines}&search=${search}">${max}</a></li>
						<c:choose>
							<c:when test="${page == max - 1}">
								<li><a
									href="?order=${order}&direction=${direction}&page=${max}&lines=${lines}&search=${search}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>
					</c:when>

					<c:when test="${page > 2}">
						<li><a
							href="?order=${order}&direction=${direction}&page=${page-1}&lines=${lines}&search=${search}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${page-2}&lines=${lines}&search=${search}">${page-2}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${page-1}&lines=${lines}&search=${search}">${page-1}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${page}&lines=${lines}&search=${search}">${page}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${page+1}&lines=${lines}&search=${search}">${page+1}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${page+2}&lines=${lines}&search=${search}">${page+2}</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${page+1}&lines=${lines}&search=${search}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:when>

					<c:otherwise>
						<c:choose>
							<c:when test="${page == 1}">
								<li><a href="" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="?order=${order}&direction=${direction}&page=1&lines=${lines}&search=${search}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>

						<li><a
							href="?order=${order}&direction=${direction}&page=1&lines=${lines}&search=${search}">1</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=2&lines=${lines}&search=${search}">2</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=3&lines=${lines}&search=${search}">3</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=4&lines=${lines}&search=${search}">4</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=5&lines=${lines}&search=${search}">5</a></li>
						<li><a
							href="?order=${order}&direction=${direction}&page=${page+1}&lines=${lines}&search=${search}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:otherwise>
				</c:choose>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default"
					onclick="location.href = '?order=${order}&direction=${direction}&page=1&lines=10&search=${search}';">10</button>
				<button type="button" class="btn btn-default"
					onclick="location.href = '?order=${order}&direction=${direction}&page=1&lines=25&search=${search}';">25</button>
				<button type="button" class="btn btn-default"
					onclick="location.href = '?order=${order}&direction=${direction}&page=1&lines=50&search=${search}';">50</button>
				<button type="button" class="btn btn-default"
					onclick="location.href = '?order=${order}&direction=${direction}&page=1&lines=100&search=${search}';">100</button>
			</div>
		</div>
	</footer>
	<c:if test="${not empty search}">
		<script>
	         $('#searchbox').val("${search}");
	    </script>
	</c:if>
	<script>
		$('.pagination li a').css('background','white');
		$('.pagination li a:contains(${page})').css('background','#969696c9');

		$('.navbar-fixed-bottom button').css('background','white');
		if(${lines} == "10"){
			$('.navbar-fixed-bottom button:nth-child(1)').css('background','#969696c9');
		}
		else{
			$('.navbar-fixed-bottom button:contains(${lines})').css('background','#969696c9');
		}
	</script>
	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/dashboard.js"></script>
	<script src="./js/function.js"></script>

</body>
</html>