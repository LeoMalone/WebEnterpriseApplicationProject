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
	<c:choose>
		<c:when test="${empty currDiv}">

			<title>Last Ever - Division</title>
		</c:when>
		<c:otherwise>
			<title>Last Ever - <c:forEach var="row" items="${currDiv}">
					<c:out value="${row.divisionName}" />
				</c:forEach></title>
		</c:otherwise>
	</c:choose>
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
										key="rules" /></a> <a class="dropdown-item" href="./registration"><fmt:message
										key="registration" /></a> <a class="dropdown-item"
									href="./contact"><fmt:message key="contact" /></a>
							</div></li>
						<li class="nav-item dropdown active"><a
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
						<c:choose>
							
							<%--  IF NOT SIGNED IN --%>
							<c:when test="${signedIn == null}">
								<li class="nav-item"><a class="nav-link" href="./login"><fmt:message key="nav_signin" /></a></li>
							</c:when>
							<c:otherwise>
								
								<c:choose>

									<%--  IF SIGNED IN AS A TEAM OWNER --%>
									<c:when test="${userType == './teamowner'}">
										<li class="nav-item dropdown"><a
											class="nav-link dropdown-toggle" href="#"
											id="navbarDropdownPortfolio" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> ${userName} </a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}">${userName}<fmt:message
														key="team_dd1" /></a> <a class="dropdown-item"
													href="teamRoster"><fmt:message key="team_dd2" /></a> <a
													class="dropdown-item" href="teamSchedule"><fmt:message
														key="team_dd3" /></a> <a class="dropdown-item" href="logout"><fmt:message
														key="team_dd4" /></a>
											</div></li>
									</c:when>
									<%--  IF SIGNED IN AS A TEAM OWNER WITH NO TEAM --%>
									<c:when test="${userType == './teamCreateTeam'}">
										<li class="nav-item dropdown"><a
											class="nav-link dropdown-toggle" href="#"
											id="navbarDropdownPortfolio" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> ${userName} </a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}"><fmt:message key="team_dd5" /></a>
												<a class="dropdown-item" href="logout"><fmt:message
														key="team_dd4" /></a>
											</div></li>
									</c:when>
								</c:choose>
								<c:choose>
								
																	<%--  IF SIGNED IN AS A REFEREE --%>
									<c:when test="${userType == './referee'}">
										<li class="nav-item dropdown"><a
											class="nav-link dropdown-toggle" href="#"
											id="navbarDropdownPortfolio" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> ${userName} </a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}">${userName}</a>
												<a class="dropdown-item" href="logout"><fmt:message
														key="team_dd4" /></a></li>
									</c:when>
								</c:choose>
								<c:choose>

									<%--  IF SIGNED IN AS ADMIN --%>
									<c:when test="${userType == './admin'}">
										<li class="nav-item dropdown"><a
											class="nav-link dropdown-toggle" href="#"
											id="navbarDropdownPortfolio" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> ${userName} </a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}">${userName}</a>
												<a class="dropdown-item" href="logout"><fmt:message
														key="team_dd4" /></a></li>
									</c:when>
								</c:choose>
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

	<fmt:bundle basename="TestBundle">
		<div class="main-cover">
			<!-- Page Content
		- cards with information on them
		- teams, schedules, results, standings, leaders
		- calls from database 'LastEver' to get all information
		-->
			<div class="cards-container container">
				<h1 class="my-4">
					<c:forEach var="row" items="${currLeague}">
						<c:out value="${row.leagueName}" />
					</c:forEach>
					<fmt:message key="div_head3" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<!-- Crude Navbar for page navigation, needs CSS applied -->
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<nav class="navbar navbar-expand-lg navbar-light bg-faded">
									<ul class="navbar-nav mr-auto">
										<c:choose>
											<c:when test="${empty currLeague }">
												League
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${currLeague}">
													<li class="nav-item"><a class="nav-link"
														href="league?id=${row.leagueId}"> <c:out
																value="${row.leagueName}" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
														href="standings?id=${row.leagueId}"> <fmt:message
																key="div_head4" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
														href="schedule?id=${row.leagueId}"> <fmt:message
																key="div_head2" />
													</a></li>
													<li class="nav-item active"><a class="nav-link"
														href="results?id=${row.leagueId}"> <fmt:message
																key="div_head3" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
														href="statistics?id=${row.leagueId}"> <fmt:message
																key="div_head5" />
													</a></li>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</ul>
								</nav>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head3" />
							</h4>
							<div class="card-body">
								<c:choose>
									<c:when test="${empty results}">
										<center>
											<b><fmt:message key="div_noresults" /></b>
										</center>
									</c:when>
									<c:otherwise>
										<c:forEach items="${results}" var="res">
											<table id="standings"
												class="table table-bordered table-striped table-dark table-hover table-sm"
												style="width: 100%; max-width: 500px;">
												<thead>
													<tr>
														<th scope="col" style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="date" pattern="d MMM y"
																	value="${res.date}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="MMM d y"
																	value="${res.date}" />
															</c:if></th>
														<th scope="col" style="text-align: center"><c:out
																value="${res.status}" /></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><a href="team?id=${res.homeID}">${res.homeTeam}</a></td>
														<td style="text-align: center"><c:out
																value="${res.homeScore}" /></td>
													</tr>
													<tr>
														<td><a href="team?id=${res.awayID}">${res.awayTeam}</a></td>
														<td style="text-align: center"><c:out
																value="${res.awayScore}" /></td>
													</tr>
													<tr>
														<td colspan="2"><b><fmt:message
																	key="home_scorers" /></b> <br> <c:choose>
																<c:when test="${res.homeScore eq 0}">
																	<fmt:message key="div_no_scorers" />
																</c:when>
																<c:otherwise>
																	<c:forEach items="${res.homeScorer}" var="hs">
																		<c:if test="${hs.goals eq 1}">
																			<a href="player?id=${hs.ID}">${hs.name}</a>
																			<br>
																		</c:if>
																		<c:if test="${hs.goals gt 1}">
																			<a href="player?id=${hs.ID}">${hs.name}</a> 
																			(<c:out value="${hs.goals}" />) 
																<br>
																		</c:if>
																	</c:forEach>
																</c:otherwise>
															</c:choose></td>
													</tr>
													<tr>
														<td colspan="2"><b><fmt:message
																	key="away_scorers" /></b> <br> <c:choose>
																<c:when test="${res.awayScore eq 0}">
																	<fmt:message key="div_no_scorers" />
																</c:when>
																<c:otherwise>
																	<c:forEach items="${res.awayScorer}" var="as">
																		<c:if test="${as.goals eq 1}">
																			<a href="player?id=${hs.ID}">${as.name}</a>
																			<br>
																		</c:if>
																		<c:if test="${as.goals gt 1}">
																			<a href="player?id=${as.ID}">${as.name}</a> 
																(<c:out value="${as.goals}" />)<br>
																		</c:if>
																	</c:forEach>
																</c:otherwise>
															</c:choose></td>
													</tr>
												</tbody>
											</table>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mt-5 mb-5">
						<div class="card">
							<div class="card-body">
								<c:forEach var="d" items="${currDiv}">
									<ul class="pagination justify-content-center">
										<c:choose>
											<c:when test="${currPage eq 1}">
												<li class="page-item disabled"><a class="page-link"
													href="#" tabindex="-1"><fmt:message key="prev_page" /></a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="results?id=${d.divisionId}&page=${currPage - 1}"><fmt:message
															key="prev_page" /></a></li>
											</c:otherwise>
										</c:choose>
										<li class="page-item active"><a class="page-link"
											href="results?id=${d.divisionId}&page=${currPage}"><c:out
													value="${currPage}" /></a></li>
										<c:choose>
											<c:when test="${currPage + 1 gt totalPages}">
												<li class="page-item disabled"><a class="page-link"
													href="#" tabindex="-1"><fmt:message key="next_page" /></a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="results?id=${d.divisionId}&page=${currPage + 1}"><fmt:message
															key="next_page" /></a></li>
											</c:otherwise>
										</c:choose>
									</ul>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>

				<!-- /.row -->
			</div>

		</div>
	</fmt:bundle>
	<!-- Footer -->
	<footer class="page-footer py-3 bg-dark">
		<div class="container-fluid">
			<p class="m-0 text-center text-white">
				<fmt:message key="footer_copyright" /> &copy; <img src="images/logo_sm4.png" /> 2018
			</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>