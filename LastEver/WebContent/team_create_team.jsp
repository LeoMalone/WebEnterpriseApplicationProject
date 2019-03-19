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
	<title>Last Ever - Create Team</title>
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
									key="nav_info" /></a>
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
							aria-haspopup="true" aria-expanded="false"><fmt:message
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
											<a class="dropdown-item"
												href="league?id=${l.leagueId}">${l.leagueName}</a>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"><c:out value="${userName}"/>
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
						<a class="dropdown-item" href="${userType}"><fmt:message key="team_dd5" /></a>
								<a class="dropdown-item" href="logout" method="post"><fmt:message key="team_dd4" /></a>
						</div></li>
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
		<fmt:bundle basename="TestBundle">
		<div class="cards-container container">		
				<h1 class="my-4">
					<c:out value="${userName}"/>: <fmt:message key="team_selectCreate" />
				</h1>
				
				<!--  first section - select a team -->
				
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="team_select" />
							</h4>
							<form action="teamOwnerSelect" method="POST" id="selectTeam">
							<div class="card-body">
								<p class="card-text">
											<div class="form-check">
												<select name="selectTeam" form="selectTeam">
													<c:forEach var="team" items="${allTeam}">
														<option value="${team.teamName}">
															${team.teamName}
														</option>
													</c:forEach>
												</select>
											</div>
								</p>
							</div>
							<div class="card-footer">
								<form action="teamowner" id="selectTeam">
									<input type="submit" value="Submit Your Team" class="btn btn-success">
								</form>	
							</div>
							</form>
						</div>							
					</div>
				</div>
				
				
				<!--  second section - create a new team -->
				
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="team_createTeam" />
							</h4>
							<form action="teamOwnerNew" method="POST">
							<div class="card-body">
								<p class="card-text">
									<div class="form-group">
										<label for="newTeamName"><fmt:message key="team_teamName" /></label>
										<input type="text" class="form-control" name="newTeamName" placeholder="Enter Team Name">
									</div>
									<div class="form-group">
										<label for="newTeamAbbr"><fmt:message key="team_abb" /></label>
										<input type="text" class="form-control" name="newTeamAbbr" placeholder="Enter Team Abbreviation">
									</div>	
									<div class="form-group">
										<label for="aboutTeam"><fmt:message key="team_aboutTeam" /></label>
										<input type="text" class="form-control" name="aboutTeam" placeholder="Enter Team Information">
									</div>
									<label for="divRadio"><fmt:message key="team_selectDivision" /></label>								 
									 <c:forEach var="div1" items="${allDiv}">
											<div class="form-check">
											  <input aria-describedby="adminHelp" class="form-check-input" type="radio" name="divRadio" value="${div1.divisionId}">
												  <label class="form-check-label" for="divRadio">
												    ${div1.divisionName}
												  </label>
											  </div>
									</c:forEach>
								</p>
							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-success"><fmt:message key="team_submit" /></button>	
							</div>
							</form>
						</div>							
					</div>
				</div>
				
			</div>
		<!-- /.row -->

	</div>

	<!-- Footer -->
	<footer class="page-footer py-3 bg-dark">
		<div class="container-fluid">
			<p class="m-0 text-center text-white">
				<fmt:message key="footer_copyright" /> &copy; <img src="images/logo_sm4.png" /> 2018 - <script>document.write(new Date().getFullYear())</script>
			</p>
		</div>
	</footer>
</fmt:bundle>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>