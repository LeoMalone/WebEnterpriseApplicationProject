<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<!-- if language is not set to French, set language to English -->
<c:if test="${cookie.language.value ne 'fr'}">
	<html lang="en">
</c:if>
<c:if test="${cookie.language.value eq 'fr'}">
	<html lang="fr">
</c:if>

<fmt:setLocale value="${cookie.language.value}" />
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/lastever" user="admin"
	password="lastever" />

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever</title>
</fmt:bundle>
</head>

<body>
	<!-- nav bar - home, league(about, rules, register, contact us), divisions (womens, mens), sign in 
	- sets parent link active
	- in dropdown, sets active with full bar color
	-->
	<sql:query dataSource="${dataSource}" var="div2">
	select divisionID, divsionName from division
	</sql:query>
	<nav
		class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="index"><img
				src="images/logo_sm4.png" /></a>

			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<fmt:bundle basename="TestBundle">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="index"><fmt:message
									key="nav_home" /></a></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <fmt:message
									key="nav_league" />
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">

								<a class="dropdown-item" href="about.jsp"><fmt:message
										key="about" /></a> <a class="dropdown-item" href="rules.jsp"><fmt:message
										key="rules" /></a> <a class="dropdown-item"
									href="registration.jsp"><fmt:message key="registration" /></a>
								<a class="dropdown-item" href="contact.jsp"><fmt:message
										key="contact" /></a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> Divisions </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
								<c:choose>
									<c:when test="${div2.rowCount == 0}">

										<a class="dropdown-item active" href=""><fmt:message
												key="nav_divisions" /></a>
									</c:when>
									<c:otherwise>
										<c:forEach var="row" items="${div2.rows}">
											<a class="dropdown-item"
												href="division?id=${row.divisionID}">${row.divsionName}</a>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div></li>
						<li class="nav-item"><a class="nav-link active" href="${userType}">${userName}</a></li>
						<li class="nav-item"><a class="nav-link" href=""></a></li>
						<li class="nav-item">
							<form action="" method="post">
								<select class="form-control form-control-sm" name="language"
									onchange="this.form.submit()">
									<option value="en"
										${cookie.language.value == "en" ? 'selected' : ''}><fmt:message
											key="english" /></option>
									<option value="fr"
										${cookie.language.value == "fr" ? 'selected' : ''}><fmt:message
											key="french" /></option>
								</select>
							</form>
						</li>
					</ul>
				</fmt:bundle>
			</div>
		</div>
	</nav>

	<div class="main-cover">
		<!-- Page Content -->
		<div class="cards-container container-fluid">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					${userName}: Users
				</h1>
				<a href="./adminCreate" class="btn btn-success">Create New User</a>					
				<div class="row">
					<div class="col mt-5">
						<div class="card">
							<div class="table-responsive table-striped">										
								<table class="table">
									<thead class="thead-dark">
									    <tr>
									    	<th scope="col">User ID</th>
									    	<th scope="col">Username</th>
									    	<th scope="col">First Name</th>
									    	<th scope="col">Last Name</th>
										    <th scope="col">User Type</th>
										    <th scope="col">Email Address</th>
										    <th scope="col">Password</th>
										    <th scope="col">Email Validated</th>
										    <th scope="col">Account Created</th>
										    <th scope="col">Last Account Update</th>
										    <th scope="col">Last Login</th>
										    <th scope="col">Edit</th>
									    </tr>
									</thead>
								    <c:forEach items="${userList}" var="user">
								        <tr>
								        	<td scope="col">${user.id}</td>
								        	<td scope="col">${user.username}</td>
								        	<td scope="col">${user.firstName}</td>
								        	<td scope="col">${user.lastName}</td>
								        	<td scope="col">${user.userType}</td>
								        	<td scope="col">${user.emailAddress}</td>
								        	<td scope="col">${user.password}</td>
								        	<td scope="col">${user.emailValidated}</td>
								        	<td scope="col">${user.accountCreated.toString()}</td>
								        	<td scope="col">${user.accountUpdated}</td>
								        	<td scope="col">${user.lastLogin.toString()}</td>
								        	<td scope="col">
								        		<a href="./editUser?=${user.id}" class="btn btn-dark btn-sm">
								        			<i class="fa fa-edit"></i> 
												</a>
											</td>		            
								        </tr>
								    </c:forEach>
								</table>
							</div>				
						</div>
					</div>
				</div>					
				<!-- /row -->
			</fmt:bundle>
		</div>
	</div>

	<!-- Footer -->
	<footer class="page-footer py-3 bg-dark">
		<div class="container-fluid">
			<p class="m-0 text-center text-white">
				Copyright &copy; <img src="images/logo_sm4.png" /> 2018
			</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>