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

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="date-picker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
<link href="clockpicker/css/clockpicker.css" rel="stylesheet" type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - Create Schedule</title>
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
					${userName}: Create Game
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								Create New Game
							</h4>
							<form action="scheduleCreate" method="POST">
							<div class="card-body">																
								<div class="form-group">
									<label for="editGameDate">Game Date</label>
								    <input type="text" class="form-control"  id="datePickInput" name="newGameDate" placeholder="yyyy-mm-dd">
								</div>
								<div class="form-group clockpicker">
									<label for="newGameTime">Game Time</label>
								    <input type="text" class="form-control" name="newGameTime" placeholder="hh:mm">
								</div>
								<div class="form-group">
									<label for="newHomeTeam">Home Team</label>
								  	<select class="custom-select my-1 mr-sm-2" id="newHomeTeam" name="newHomeTeam">
								    	<c:forEach items="${teamList}" var="team">
											<option value="${team.teamId}">${team.teamName}</option>
										</c:forEach>
								  	</select>
								</div>
								<div class="form-group">
									<label for="newAwayTeam">Away Team</label>
								  	<select class="custom-select my-1 mr-sm-2" id="newAwayTeam" name="newAwayTeam">
								    	<c:forEach items="${teamList}" var="team">
											<option value="${team.teamId}">${team.teamName}</option>
										</c:forEach>
								  	</select>
								</div>
								<div class="form-group">
									<label for="newHomeScore">Home Score</label>
									<input type="text" class="form-control" name="newHomeScore" placeholder="Set 0 if New Game">
								</div>
								<div class="form-group">
									<label for="newAwayScore">Away Score</label>
									<input type="text" class="form-control" name="newAwayScore" placeholder="Set 0 if New Game">
								</div>
								<div class="form-group">
									<label for="newGameStatus">GameStatus</label>
								  	<select class="custom-select my-1 mr-sm-2" id="newGameStatus" name="newGameStatus">
								  		<option value="Final">Final</option>
								  		<option value="Scheduled" selected>Scheduled</option>												
								  	</select>
								</div>							
							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-success">Submit!</button>	
							</div>
							</form>
						</div>							
					</div>
				</div>
			</div>
		<!-- /.row -->
		</fmt:bundle>
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
	<script type="text/javascript" src="date-picker/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="clockpicker/js/clockpicker.js"></script>
	<script type="text/javascript" src="js/edit_schedule.js"></script>
</body>
</html>