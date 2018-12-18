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
	<title>Last Ever - <fmt:message key="teamowner_home" /></title>
</fmt:bundle>
</head>

<body>
	<fmt:bundle basename="TestBundle">
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
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="index"><fmt:message
									key="nav_home" /></a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <fmt:message
									key="nav_info" />
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">

								<a class="dropdown-item" href="about"><fmt:message
										key="about" /></a> <a class="dropdown-item" href="rules"><fmt:message
										key="rules" /></a> <a class="dropdown-item" href="registration"><fmt:message
										key="registration" /></a> <a class="dropdown-item" href="contact"><fmt:message
										key="contact" /></a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <fmt:message
									key="nav_league" /></a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
								<c:choose>
									<c:when test="${empty league}">

										<a class="dropdown-item" href=""><fmt:message
												key="nav_league" /></a>
									</c:when>
									<c:otherwise>
										<c:forEach var="l" items="${league}">
											<a class="dropdown-item" href="league?id=${l.leagueId}">${l.leagueName}</a>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div></li>
						<c:choose>
							<c:when test="${signedIn == null}">
								<li class="nav-item"><a class="nav-link" href="./login"><fmt:message
											key="nav_signin" /></a></li>
							</c:when>
							<c:otherwise>

								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#"
									id="navbarDropdownPortfolio" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> <c:out value="${userName}"/> </a>
									<div class="dropdown-menu dropdown-menu-right"
										aria-labelledby="navbarDropdownPortfolio">

										<a class="dropdown-item" href="${userType}"><c:out value="${userName}"/><fmt:message
												key="team_dd1" /></a> <a class="dropdown-item"
											href="teamRoster"><fmt:message key="team_dd2" /></a> <a
											class="dropdown-item" href="teamSchedule"><fmt:message
												key="team_dd3" /></a> <a class="dropdown-item"
											href="teamEmails"><fmt:message key="team_dd6" /></a><a
											class="dropdown-item" href="logout"><fmt:message
												key="team_dd4" /></a>
									</div></li>
							</c:otherwise>
						</c:choose>
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
				</div>
			</div>
		</nav>


		<div class="main-cover">
			<!-- Page Content -->
			<div class="cards-container container">
				<h1 class="my-4">
					${teamName}:<br />
					<fmt:message key="signin_prop2" />
					<fmt:message key="team_signedin_home1" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="admin-cards">
					<div class="row">
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="team_signedin_home2" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="team_signedin_home3" />
									</p>
								</div>
								<div class="card-footer bg-transparent">
									<a href="./teamRoster" class="btn btn-outline-light"><fmt:message
											key="team_signedin_home4" /></a>
								</div>
							</div>
						</div>
						<!-- 
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									Team Photos
								</h4>
								<div class="card-body">
									<p class="card-text">
										Add Team Photo and Team Logo
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./teamPhotos" class="btn btn-outline-light">Go To Photos</a>
								</div>
							</div>
						</div>
						 -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="team_signedin_home5" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="team_signedin_home6" />
									</p>
								</div>
								<div class="card-footer bg-transparent">
									<a href="./teamSchedule" class="btn btn-outline-light"><fmt:message
											key="team_signedin_home7" /></a>
								</div>
							</div>
						</div>

						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="team_signedin_home8" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="team_signedin_home9" />
									</p>
								</div>
								<div class="card-footer bg-transparent">
									<a href="./teamEmails" class="btn btn-outline-light"><fmt:message
											key="team_signedin_home10" /></a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div>
					<form action="logout" method="post">
						<button type="submit" class="btn btn-danger">
							<fmt:message key="logged_in_signout" />
						</button>
					</form>
				</div>
				<!-- /row -->
			</div>
		</div>

		<!-- Footer -->
		<footer class="page-footer py-3 bg-dark">
			<div class="container-fluid">
				<p class="m-0 text-center text-white">
					Copyright &copy; <img src="images/logo_sm4.png" /> 2018 - <script>document.write(new Date().getFullYear())</script>
				</p>
			</div>
		</footer>
	</fmt:bundle>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>