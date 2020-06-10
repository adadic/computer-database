<%@ page pageEncoding= "UTF-8" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<!-- Bootstrap -->
	<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="./css/main.css" rel="stylesheet" media="screen">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="shortcut icon" href="#">
</head>
<body>
<script>
	var direction = 0;
</script>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="#"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${size} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th onclick="sortTable();">
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
	                <c:forEach items="${computers}" var="computer">
        				<tr>
	                        <td class="editMode">
	                            <input type="checkbox" name="cb" class="cb" value="${computer.id}">
	                        </td>
	                        <td>
	                            <a href="editComputer?id=${computer.id}" onclick="">${computer.name}</a>
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
              	<c:when test="${page > max-2}">
	              	<li>
	                    <a href="?page=${page-1}&lines=${lines}&search=${search}" aria-label="Previous">
	                      <span aria-hidden="true">&laquo;</span>
		                  </a>
		              </li>
		              <li><a href="?page=${max-4}&lines=${lines}&search=${search}">${max-4}</a></li>
		              <li><a href="?page=${max-3}&lines=${lines}&search=${search}">${max-3}</a></li>
		              <li><a href="?page=${max-2}&lines=${lines}&search=${search}">${max-2}</a></li>
		              <li><a href="?page=${max-1}&lines=${lines}&search=${search}">${max-1}</a></li>
		              <li><a href="?page=${max}&lines=${lines}&search=${search}">${max}</a></li>
		              <c:choose>
		              		<c:when test="${page == max - 1}">
		              		 	<li>
				                    <a href="?page=${max}&lines=${lines}&search=${search}" aria-label="Next">
					                    <span aria-hidden="true">&raquo;</span>
					                </a>
				              	</li>
		              		</c:when>
		              		<c:otherwise>
		              			<li>
				                    <a href="" aria-label="Next">
					                    <span aria-hidden="true">&raquo;</span>
					                </a>	
				              	</li>
		              		</c:otherwise>
		              	</c:choose>
	              </c:when>
	              
	              <c:when test="${page > 2}">
	              	<li>
	                    <a href="?page=${page-1}&lines=${lines}&search=${search}" aria-label="Previous">
	                      <span aria-hidden="true">&laquo;</span>
		                  </a>
		              </li>
		              <li><a href="?page=${page-2}&lines=${lines}&search=${search}">${page-2}</a></li>
		              <li><a href="?page=${page-1}&lines=${lines}&search=${search}">${page-1}</a></li>
		              <li><a href="?page=${page}&lines=${lines}&search=${search}">${page}</a></li>
		              <li><a href="?page=${page+1}&lines=${lines}&search=${search}">${page+1}</a></li>
		              <li><a href="?page=${page+2}&lines=${lines}&search=${search}">${page+2}</a></li>
		              <li>
		                <a href="?page=${page+1}&lines=${lines}&search=${search}" aria-label="Next">
		                    <span aria-hidden="true">&raquo;</span>
		                </a>
		              </li>
	              </c:when>
	              
	              <c:otherwise>
	              	<c:choose>
	              		<c:when test="${page == 1}">
	              		 	<li>
			                    <a href="" aria-label="Previous">
			                      <span aria-hidden="true">&laquo;</span>
				                </a>
			              	</li>
	              		</c:when>
	              		<c:otherwise>
	              			<li>
			                    <a href="?page=1&lines=${lines}&search=${search}" aria-label="Previous">
			                      <span aria-hidden="true">&laquo;</span>
				                </a>
			              	</li>
	              		</c:otherwise>
	              	</c:choose>
		             
		              <li><a href="?page=1&lines=${lines}&search=${search}">1</a></li>
		              <li><a href="?page=2&lines=${lines}&search=${search}">2</a></li>
		              <li><a href="?page=3&lines=${lines}&search=${search}">3</a></li>
		              <li><a href="?page=4&lines=${lines}&search=${search}">4</a></li>
		              <li><a href="?page=5&lines=${lines}&search=${search}">5</a></li>
		              <li>
		                <a href="?page=${page+1}&lines=${lines}&search=${search}" aria-label="Next">
		                    <span aria-hidden="true">&raquo;</span>
		                </a>
		              </li>
	              </c:otherwise>
              </c:choose>
              
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default" onclick="location.href = '?page=1&lines=10&search=${search}';">10</button>
            <button type="button" class="btn btn-default" onclick="location.href = '?page=1&lines=25&search=${search}';">25</button>
            <button type="button" class="btn btn-default" onclick="location.href = '?page=1&lines=50&search=${search}';">50</button>
            <button type="button" class="btn btn-default" onclick="location.href = '?page=1&lines=100&search=${search}';">100</button>
        </div>
        </div>
    </footer>
    <c:if test="${not empty errorLog}">
	    <script>
	         window.location.replace("/maven/dashboard?");
	    </script>
	</c:if>
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