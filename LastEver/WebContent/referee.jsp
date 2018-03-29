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
	<title>Last Ever - Referee<fmt:message key="home" /></title>
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
									<c:when test="${empty league}">

										<a class="dropdown-item" href=""><fmt:message
												key="nav_divisions" /></a>
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
											class="nav-link active dropdown-toggle" href="#"
											id="navbarDropdownPortfolio" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> ${userName} </a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}">${userName}</a>
												<a class="dropdown-item" href="logout"><fmt:message
														key="team_dd4" /></a></li>

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
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					${userName}: <fmt:message key="signin_prop3" /> <fmt:message key="signin_prop3" /> <fmt:message key="div_head3_text2" /><br \>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="admin-cards">
					<div class="row">
						<!-- View and edit referee profiles -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									${userName}'s <fmt:message key="au_profile" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										 <fmt:message key="ah_user_body" /><br \>
									</p>
								</div>
								 <div class="card-footer bg-transparent">
								 	<a href="./refUsers?=${id}" class="btn btn-outline-light"><fmt:message key="au_edit" /></a>
								</div>
							</div>
						</div>
						<!-- Referee Assignments -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									${userName}'s <fmt:message key="ah_sched_title" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="ah_sched_body" /><br \>
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./refSchedule" class="btn btn-outline-light"><fmt:message key="ah_sched_goto" /></a>
								</div>
							</div>
						</div>
						<!-- Referee Email - Only to Admin and other Referees -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="email_send" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="email_send_options" />
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./refEmail" class="btn btn-outline-light"><fmt:message key="email_go" /></a>
								</div>
							</div>
						</div>				
						<!-- NEW - 4th Card -->
						<div class="col-lg-12 mb-5 mt-5">
							<div class="card h-100 text-white bg-dark">
								<div class="card-body">
									<h4 class="card-header"><fmt:message key="admin_cs_gs" /></h4>
								 	<table class="table table-striped table-dark">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col"><fmt:message key="admin_es_title" /></th>
									      <th scope="col"><fmt:message key="div_head3_text2" /></th>
									      <th scope="col"><fmt:message key="home_scorers" /></th>
									      <th scope="col"><fmt:message key="div_head3_text4" /></th>
									      <th scope="col"><fmt:message key="away_scorers" /></th>
									      <th scope="col"><fmt:message key="div_head3" /></th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr>
									      <th scope="row">1</th>
									      <td>123</td>
									      <td>Barcelona</td>
									      <td>2</td>
									      <td>Aesenal</td>
									      <td>1</td>
									      <td>Barcelona</td>
									    </tr>
									    <tr>
									      <th scope="row">2</th>
									      <td>456</td>
									      <td>Real Madrid</td>
									      <td>3</td>
									      <td>MC United</td>
									      <td>2</td>
									      <td>Real Madrid</td>
									    </tr>
									    <tr>
									      <th scope="row">3</th>
									     <td>789</td>
									      <td>PSG</td>
									      <td>1</td>
									      <td>AC Milan</td>
									      <td>1</td>
									      <td>Draw</td>
									    </tr>
									  </tbody>
									</table>
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
				<fmt:message key="footer_copyright" /> &copy; <img src="images/logo_sm4.png" /> 2018
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