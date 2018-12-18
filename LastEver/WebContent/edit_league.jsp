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
	<title>Last Ever - <fmt:message key="admin_title_ct" /></title>
</fmt:bundle>
</head>
<body>
	<fmt:bundle basename="TestBundle">
		<div class="modal fade" id="deleteLeague" tabindex="-1" role="dialog"
			aria-labelledby="deleteLeagueLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="deleteLeagueLabel">
							<fmt:message key="admin_et_model_del" />
							: ${editLeague.leagueName}
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<fmt:message key="admin_el_modal" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<fmt:message key="admin_eu_model_cls" />
						</button>
						<form action="deleteLeague?=${editLeague.leagueId}" method="POST">
							<button type="submit" class="btn btn-danger">
								<fmt:message key="admin_el_del" />
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
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle active" href="#"
								id="navbarDropdownPortfolio" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <c:out value="${userName}" /> </a>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="navbarDropdownPortfolio">
									<a class="dropdown-item" href="${userType}"><c:out value="${userName}" /></a>
									<a class="dropdown-item" href="adminUsers"><fmt:message	key="nav_admin_users" /></a>
									<a class="dropdown-item" href="adminTeams"><fmt:message key="nav_admin_teams" /></a>
									<a class="dropdown-item" href="adminDivisions"><fmt:message	key="nav_admin_divs" /></a>
									<a class="dropdown-item" href="adminSchedule"><fmt:message key="nav_admin_sched" /></a>
									<a class="dropdown-item" href="adminEmails"><fmt:message key="nav_admin_email" /></a>
									<a class="dropdown-item" href="adminLeagues"><fmt:message key="nav_leagues" /></a>
									<a class="dropdown-item" href="logout"><fmt:message key="team_dd4" /></a>
								</div>
							</li>
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
				</div>
			</div>
		</nav>

		<div class="main-cover">
			<div class="cards-container container">
				<h1 class="my-4">
					<c:out value="${userName}:" />
					<fmt:message key="admin_el_title" />
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="admin_cl_header" />
							</h4>
							<form action="editLeague?=${editLeague.leagueId}" method="POST">
								<div class="card-body">
									<div class="form-group">
										<label for="newLeagueName"><fmt:message key="admin_cl_name" /></label>
										<input type="text" class="form-control" name="newLeagueName" placeholder="<fmt:message key="admin_cl_name_place"/>" value="${editLeague.leagueName}" required>
									</div>
									<div class="form-group">
										<label for="newLeaguePlayoffs"><fmt:message key="admin_cl_ph" /></label>
										<select class="form-control" name="newLeaguePlayoffs" required>
										  <option value="0" ${editLeague.leaguePlayoffs == 0?'selected':''}><fmt:message key="admin_cl_ph_1"/></option>
										  <option value="1" ${editLeague.leaguePlayoffs == 1?'selected':''}><fmt:message key="admin_cl_ph_2"/></option>										 
										</select>
									</div>
									<div class="form-group">
										<label for="leaguePlayoffTeams"><fmt:message key="admin_cl_pt" /></label>
										<input type="number" class="form-control" name="leaguePlayoffTeams" placeholder="<fmt:message key="admin_cl_pt_place"/>" value="${editLeague.leaguePlayoffTeams}" required>
									</div>
									<div class="form-group">
										<label for="newLeagueStatus"><fmt:message key="admin_cl_status" /></label>
										<select class="form-control" name="newLeagueStatus" required>
										  <option value="Newly Created"><fmt:message key="admin_cl_status_1"/></option>
										  <option value="In Progress"><fmt:message key="admin_cl_status_2"/></option>
										  <option value="Playoffs"><fmt:message key="admin_cl_status_3"/></option>
										</select>									
									</div>
									<button type="submit" class="btn btn-outline-success">
										<fmt:message key="admin_eu_save" />
									</button>									
								</div>
								<div class="card-footer">
									<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteLeague">
										<fmt:message key="admin_el_del" />
									</button>
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
					<fmt:message key="footer_copyright" />
					&copy; <img src="images/logo_sm4.png" /> 2018 - <script>document.writenew Date.getFullYear</script>
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