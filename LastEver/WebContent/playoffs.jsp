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
<!-- DataTables core CSS -->
<link rel="stylesheet" type="text/css"
	href="DataTables/datatables.min.css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<c:choose>
		<c:when test="${empty currLeague}">

			<title>Last Ever - <fmt:message key="nav_league" /></title>
		</c:when>
		<c:otherwise>
			<title>Last Ever - <c:forEach var="row" items="${currLeague}">
					<c:out value="${row.leagueName}" />
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
									key="nav_info" /></a>
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

							<%--  IF NOT SIGNED IN --%>
							<c:when test="${signedIn == null}">
								<li class="nav-item"><a class="nav-link" href="./login"><fmt:message
											key="nav_signin" /></a></li>
							</c:when>
							<c:otherwise>

								<c:choose>

									<%--  IF SIGNED IN AS A TEAM OWNER --%>
									<c:when test="${userType == './teamowner'}">
										<li class="nav-item dropdown"><a
											class="nav-link dropdown-toggle" href="#"
											id="navbarDropdownPortfolio" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <c:out
													value="${userName}" />
										</a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}"><c:out
														value="${userName}" /> <fmt:message key="team_dd1" /></a> <a
													class="dropdown-item" href="teamRoster"><fmt:message
														key="team_dd2" /></a> <a class="dropdown-item"
													href="teamSchedule"><fmt:message key="team_dd3" /></a> <a
													class="dropdown-item" href="logout"><fmt:message
														key="team_dd4" /></a>
											</div></li>
									</c:when>
									<%--  IF SIGNED IN AS A TEAM OWNER WITH NO TEAM --%>
									<c:when test="${userType == './teamCreateTeam'}">
										<li class="nav-item dropdown"><a
											class="nav-link dropdown-toggle" href="#"
											id="navbarDropdownPortfolio" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <c:out
													value="${userName}" />
										</a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}"><fmt:message
														key="team_dd5" /></a> <a class="dropdown-item" href="logout"><fmt:message
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
											aria-haspopup="true" aria-expanded="false"> <c:out
													value="${userName}" />
										</a>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="navbarDropdownPortfolio">

												<a class="dropdown-item" href="${userType}"><c:out
														value="${userName}" /></a> <a class="dropdown-item"
													href="logout"><fmt:message key="team_dd4" /></a>
											</div></li>
									</c:when>
								</c:choose>
								<c:choose>

									<%--  IF SIGNED IN AS ADMIN --%>
									<c:when test="${userType == './admin'}">
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
					<fmt:message key="div_head6" />
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
												<fmt:message key="nav_league" />
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
													<li class="nav-item"><a class="nav-link"
														href="results?id=${row.leagueId}"> <fmt:message
																key="div_head3" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
														href="statistics?id=${row.leagueId}"> <fmt:message
																key="div_head5" />
													</a></li>
													<c:if test="${row.leagueStatus eq 'Playoffs'}">
														<li class="nav-item active"><a class="nav-link"
															href="playoffs?id=${row.leagueId}"> <fmt:message
																	key="div_head6" />
														</a></li>
													</c:if>
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
								<fmt:message key="div_head2" />
							</h4>
							<div class="card-body table-responsive">
								<table id="schedule"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="venue_head1" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty schedule}">
												<td colspan=5 style="text-align: center"><b><fmt:message
															key="div_nogames" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${schedule}" var="sched">
													<tr>
														<td scope="row" style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="date" pattern="YYYY-MM-dd"
																	value="${sched.date}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="YYYY-MM-dd"
																	value="${sched.date}" />
															</c:if></td>
														<td style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="time" pattern="H:mm"
																	value="${sched.time}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="time" pattern="h:mm a"
																	value="${sched.time}" />
															</c:if></td>
														<td><c:choose>
																<c:when test="${not empty sched.homeTeamLogo}">
																	<img class="responsive-sm" src="${sched.homeTeamLogo}" />
																</c:when>
																<c:otherwise>
																	<img class="responsive-sm"
																		src="https://i.imgur.com/zSAVaUJ.png" />
																</c:otherwise>
															</c:choose><a href="team?id=${sched.homeID}">${sched.homeTeam}</a></td>
														<td><c:choose>
																<c:when test="${not empty sched.awayTeamLogo}">
																	<img class="responsive-sm" src="${sched.awayTeamLogo}" />
																</c:when>
																<c:otherwise>
																	<img class="responsive-sm"
																		src="https://i.imgur.com/zSAVaUJ.png" />
																</c:otherwise>
															</c:choose><a href="team?id=${sched.awayID}">${sched.awayTeam}</a></td>
														<c:choose>
															<c:when test="${empty sched.venue}">
																<td><fmt:message key="div_novenue" /></td>
															</c:when>
															<c:otherwise>
																<td><a href="venue?id=${sched.venueID}">${sched.venue}</a></td>
															</c:otherwise>
														</c:choose>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head5" />
							</h4>
							<div class="card-body table-responsive">
								<table id="statistics"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text5" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text6" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text7" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty statistics}">
												<td colspan=7 style="text-align: center"><b><fmt:message
															key="div_noplayers" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${statistics}" var="stats">
													<tr>
														<td scope="row" style="text-align: center"><c:out
																value="${stats.rank}" /></td>
														<td><c:choose>
																<c:when test="${not empty stats.teamLogo}">
																	<img class="responsive-sm" src="${stats.teamLogo}" />
																</c:when>
																<c:otherwise>
																	<img class="responsive-sm"
																		src="https://i.imgur.com/zSAVaUJ.png" />
																</c:otherwise>
															</c:choose><a href="team?id=${stats.teamID}"><c:out
																	value="${stats.teamName}" /></a></td>
														<td><a href="player?id=${stats.playerID}"> <c:choose>
																	<c:when test="${stats.hidePage eq true }">
																		<fmt:message key="name_withheld" />
																	</c:when>
																	<c:otherwise>
																		<c:out value="${stats.name}" />
																	</c:otherwise>
																</c:choose>
														</a></td>
														<td style="text-align: center"><c:out
																value="${stats.gamesPlayed}" /></td>
														<td style="text-align: center"><c:out
																value="${stats.goals}" /></td>
														<td style="text-align: center"><c:out
																value="${stats.yellowCard}" /></td>
														<td style="text-align: center"><c:out
																value="${stats.redCard}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
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
					&copy; <img src="images/logo_sm4.png" /> 2018 - <script>document.write(new Date().getFullYear())</script>
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
	<!-- DataTables core JavaScript -->
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>
	<script type="text/javascript" src="js/playoffs.js"></script>
</body>
</html>