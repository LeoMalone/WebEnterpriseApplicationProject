<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - Create User</title>
</fmt:bundle>
</head>
<body>

	<!-- nav bar - home, league(about, rules, register, contact us), divisions (womens, mens), sign in 
	- sets parent link active
	- in dropdown, sets active with full bar color
	-->
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
									key="nav_league" /></a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">

								<a class="dropdown-item" href="./about"><fmt:message
										key="about" /></a> <a class="dropdown-item" href="./rules"><fmt:message
										key="rules" /></a> <a class="dropdown-item"
									href="./registration"><fmt:message key="registration" /></a>
								<a class="dropdown-item" href="./contact"><fmt:message
										key="contact" /></a>
							</div>
						</li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> Divisions </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
								<c:choose>
									<c:when test="${empty allDiv}">

										<a class="dropdown-item" href=""><fmt:message
												key="nav_divisions" /></a>
									</c:when>
									<c:otherwise>
										<c:forEach var="div1" items="${allDiv}">
											<a class="dropdown-item"
												href="division?id=${div1.divisionId}">${div1.divisionName}</a>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div></li>
						<li class="nav-item"><a class="nav-link active" href="${userType}">${userName}</a></li>
						<li class="nav-item"><a class="nav-link" href=""></a></li>
						<li class="nav-item">
							<form>
								<select class="form-control form-control-sm" name="language"
									onchange="submit()">
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
		<!-- Page Content
		- card with information on it
		- text, form, button to sign in
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">				
				<h1 class="my-4">
					${userName}: Create
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="signin_createnew" />
							</h4>
							<form action="adminCreate" method="POST">
							<div class="card-body">
								<div class="form-group">
									<label for="newFirstName">First Name</label>
									<input type="text" class="form-control" name="newFirstName" placeholder="Enter First Name">
								</div>
								<div class="form-group">
									<label for="newLastName">Last Name</label>
									<input type="text" class="form-control" name="newLastName" placeholder="Enter Last Name">
								</div>
								<div class="form-group">
									<label for="newUsername"><fmt:message key="signin_user" /></label>
									<input type="text" class="form-control" name="newUsername" placeholder="<fmt:message key='signin_enter_user' />">
								</div>
								 <div class="form-group">
								    <label for="newEmail"><fmt:message key="signin_email" /></label>
								    <input type="email" class="form-control" name="newEmail" aria-describedby="emailHelp" placeholder="<fmt:message key='signin_enter_email' />">
								 </div>
								 <div class="form-group">
									<label for="newPass"><fmt:message key="signin_password" /></label>
									<input type="password" class="form-control" name="newPass" placeholder="<fmt:message key='signin_enter_password' />">
								 </div>	
								 <div class="form-check">
								  <input aria-describedby="adminHelp" class="form-check-input" type="radio" name="createRadio" value="Administrator">
								  <label class="form-check-label" for="createRadio">
								    <fmt:message key="signin_prop1" />
								  </label>									  
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="radio" name="createRadio" value="Team Owner">
								  <label class="form-check-label" for="createRadio">
								    <fmt:message key="signin_prop2" />
								  </label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="radio" name="createRadio" value="Referee">
								  <label class="form-check-label" for="createRadio">
								    <fmt:message key="signin_prop3" />
								  </label>
								  <small id="emailHelp" class="form-text text-muted"><fmt:message key="sign_in_verify"/></small>
								</div>							
							</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-success"><fmt:message key="signin_button1"/></button>	
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- /.row -->				
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
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>