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
	<title>LastEver - <fmt:message key="team_edit1" /></title>
</head>
<body>
	<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog"
		aria-labelledby="deleteUserLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteUserLabel">
						<fmt:message key="team_edit2" />
						: ${player.playerFirstName} ${player.playerLastName}
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<fmt:message key="team_edit3" />
					: ${player.playerFirstName} ${player.playerLastName}?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<fmt:message key="team_edit4" />
					</button>
					<form action="deleteTeamPlayer?=${player.id}" method="post">
						<button type="submit" class="btn btn-danger">
							<fmt:message key="team_edit5" />
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
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
								key="nav_info" /></a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdownPortfolio">

							<a class="dropdown-item" href="./about"><fmt:message
									key="about" /></a> <a class="dropdown-item" href="./rules"><fmt:message
									key="rules" /></a> <a class="dropdown-item" href="./registration"><fmt:message
									key="registration" /></a> <a class="dropdown-item"
								href="./contact"><fmt:message key="contact" /></a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownPortfolio" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <fmt:message
								key="nav_league" />
					</a>
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


							<!-- <li class="nav-item"><a class="nav-link" href="${userType}"><c:out value="${userName}"/></a></li> -->

							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownPortfolio" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <c:out value="${userName}"/> </a>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="navbarDropdownPortfolio">

									<a class="dropdown-item" href="${userType}"><c:out value="${userName}"/><fmt:message
											key="team_dd1" /></a> <a class="dropdown-item" href="teamRoster"><fmt:message
											key="team_dd2" /></a> <a class="dropdown-item"
										href="teamSchedule"><fmt:message key="team_dd3" /></a> <a
										class="dropdown-item" href="teamEmails"><fmt:message
											key="team_dd6" /></a> <a class="dropdown-item" href="logout"
										method="post"><fmt:message key="team_dd4" /></a>
								</div></li>

						</c:otherwise>
					</c:choose>
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
		<!-- Page Content
		- card with information on it
		- text, form, button to sign in
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<fmt:message key="team_edit6" />
					: ${player.playerFirstName} ${player.playerLastName}
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="team_edit7" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<form action="editTeamPlayer?=${player.id}" method="POST">
									<div class="form-group">
										<label for="editFirstName"><fmt:message
												key="team_view_roster2" /></label> <input type="text"
											class="form-control" name="editPlayerFirstName"
											value="${player.playerFirstName}" required>
									</div>
									<div class="form-group">
										<label for="editLastName"><fmt:message
												key="team_view_roster3" /></label> <input type="text"
											class="form-control" name="editPlayerLastName"
											value="${player.playerLastName}" required>
									</div>
									<div class="form-group">
										<label for="newUsername">#</label> <input type="number" min=0
											max=99 class="form-control" name="editPlayerNumber"
											value="${player.playerNumber}" required>
									</div>
									<div class="form-group">
										<label for="newEmail"><fmt:message
												key="team_view_roster4" /></label> <input type="text"
											class="form-control" name="editPlayerPosition"
											value="${player.playerPosition}" required>
									</div>

									<br />
									<button type="submit" class="btn btn-outline-success">
										<fmt:message key="team_edit8" />
									</button>
								</form>
								</p>
							</div>
							<div class="card-footer">
								<button type="button" class="btn btn-danger" data-toggle="modal"
									data-target="#deleteUser">
									<fmt:message key="team_edit9" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
		</div>
	</div>

	<!-- Footer -->
	<footer class="page-footer py-3 bg-dark">
		<div class="container-fluid">
			<p class="m-0 text-center text-white">
				<fmt:message key="footer_copyright" />
				&copy; <img src="images/logo_sm4.png" /> 2018
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