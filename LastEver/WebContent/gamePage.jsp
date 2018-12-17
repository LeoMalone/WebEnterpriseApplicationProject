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
		<c:when test="${empty result}">

			<title>Last Ever - <fmt:message key="game_head1" /></title>
		</c:when>
		<c:otherwise>
			<title>Last Ever - <c:forEach var="row" items="${result}">
					<c:out value="${row.homeTeam}" />
					<fmt:message key="game_vs" />
					<c:out value="${row.awayTeam}" />
				</c:forEach></title>
		</c:otherwise>
	</c:choose>
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
													class="dropdown-item" href="teamEmails"><fmt:message
														key="team_dd6" /></a> <a class="dropdown-item" href="logout"
													method="post"><fmt:message key="team_dd4" /></a>
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
				</div>
			</div>
		</nav>


		<div class="main-cover">
			<!-- Page Content
		- cards with information on them
		- teams, schedules, results, standings, leaders
		- calls from database 'LastEver' to get all information
		-->
			<div class="cards-container container">
				<h1 class="my-4">
					<fmt:message key="game_head1" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<c:forEach var="row" items="${result}">
									<c:out value="${row.homeTeam}" />
									<fmt:message key="game_vs" />
									<c:out value="${row.awayTeam}" />
								</c:forEach>
							</h4>
							<div class="card-body">
								<div class="d-flex justify-content-center align-items-center">
									<c:forEach var="row" items="${result}">
										<div>
											<c:choose>
												<c:when test="${not empty row.homeTeamLogo}">
													<img class="responsive" src="${row.homeTeamLogo}" />
												</c:when>
												<c:otherwise>
													<img class="responsive"
														src="https://i.imgur.com/zSAVaUJ.png" />
												</c:otherwise>
											</c:choose>
										</div>
										<span style="display: inline-block; width: 30px;"></span>
										<div>
											<h1 class="score">
												<c:choose>
													<c:when test="${row.homeScore lt row.awayScore}">
														<span class="badge badge-danger"><c:out
																value="${row.homeScore}" /></span>
													</c:when>
													<c:when test="${row.homeScore gt row.awayScore}">
														<span class="badge badge-success"><c:out
																value="${row.homeScore}" /></span>
													</c:when>
													<c:otherwise>
														<span class="badge badge-primary"><c:out
																value="${row.homeScore}" /></span>
													</c:otherwise>
												</c:choose>
											</h1>
										</div>
										<span style="display: inline-block; width: 30px;"></span>
										<div>
											<h1 class="score">
												<c:choose>
													<c:when test="${row.awayScore lt row.homeScore}">
														<span class="badge badge-danger"><c:out
																value="${row.awayScore}" /></span>
													</c:when>
													<c:when test="${row.awayScore gt row.homeScore}">
														<span class="badge badge-success"><c:out
																value="${row.awayScore}" /></span>
													</c:when>
													<c:otherwise>
														<span class="badge badge-primary"><c:out
																value="${row.awayScore}" /></span>
													</c:otherwise>
												</c:choose>
											</h1>
										</div>
										<span style="display: inline-block; width: 30px;"></span>
										<div>
											<c:choose>
												<c:when test="${not empty row.awayTeamLogo}">
													<img class="responsive" src="${row.awayTeamLogo}" />
												</c:when>
												<c:otherwise>
													<img class="responsive"
														src="https://i.imgur.com/zSAVaUJ.png" />
												</c:otherwise>
											</c:choose>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<div class="card-body table-responsive">
								<table
									class="table-bordered table-striped table-dark table-hover table-sm"
									style="width: 100%; text-align: center">
									<c:forEach var="row" items="${result}">
										<tr>
											<td><fmt:message key="div_head2_text1" />: <c:out
													value="${row.date}" /></td>
										</tr>
										<tr>
											<td><fmt:message key="div_head2_text2" />: <c:if
													test="${cookie.language.value eq 'fr'}">
													<fmt:formatDate type="time" pattern="H:mm"
														value="${row.time}" />
												</c:if> <c:if test="${cookie.language.value ne 'fr'}">
													<fmt:formatDate type="time" pattern="h:mm a"
														value="${row.time}" />
												</c:if></td>
										</tr>
										<tr>
											<td><fmt:message key="venue_head1" />: <c:choose>
													<c:when test="${not empty row.venue}">
														<a href="venue?id=${row.venueID}"><c:out
																value="${row.venue}" /></a>
													</c:when>
													<c:otherwise>
														<fmt:message key="venue_no_info" />
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<div class="card-header">
								<nav>
									<c:forEach var="row" items="${result}">
										<div class="nav nav-tabs" id="nav-tab" role="tablist">
											<a class="nav-item nav-link active" id="home-team"
												data-toggle="tab" href="#home" role="tab"
												aria-controls="home" aria-selected="true"><c:out
													value="${row.homeTeam}" /></a> <a class="nav-item nav-link"
												id="away-team" data-toggle="tab" href="#away" role="tab"
												aria-controls="away" aria-selected="false"><c:out
													value="${row.awayTeam}" /></a>
										</div>
									</c:forEach>
								</nav>
							</div>
							<div class="card-body table-responsive">
								<div class="tab-content" id="nav-tabContent">
									<div class="tab-pane fade show active" id="home"
										role="tabpanel" aria-labelledby="home-tab">
										<table id="hstatistics"
											class="table table-bordered table-striped table-dark table-hover table-sm">
											<thead>
												<tr>
													<th scope="col" style="text-align: center"><fmt:message
															key="div_head5_text3" /></th>
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
													<c:when test="${empty result}">
														<td colspan=4 style="text-align: center"><b><fmt:message
																	key="div_noplayers" /></b></td>
													</c:when>
													<c:otherwise>
														<c:forEach items="${result}" var="home">
															<c:forEach items="${home.homeScorer}" var="hs">
																<tr>
																	<td><a href="player?id=${hs.ID}"> <c:choose>
																				<c:when test="${hs.hidePage eq true }">
																					<fmt:message key="name_withheld" />
																				</c:when>
																				<c:otherwise>
																					<c:out value="${hs.name}" />
																				</c:otherwise>
																			</c:choose>
																	</a></td>
																	<td style="text-align: center"><c:out
																			value="${hs.goals}" /></td>
																	<td style="text-align: center"><c:out
																			value="${hs.yellowCards}" /></td>
																	<td style="text-align: center"><c:out
																			value="${hs.redCards}" /></td>
																</tr>
															</c:forEach>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
									</div>
									<div class="tab-pane fade" id="away" role="tabpanel"
										aria-labelledby="nav-away-tab">
										<table id="astatistics"
											class="table table-bordered table-striped table-dark table-hover table-sm">
											<thead>
												<tr>
													<th scope="col" style="text-align: center"><fmt:message
															key="div_head5_text3" /></th>
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
													<c:when test="${empty result}">
														<td colspan=4 style="text-align: center"><b><fmt:message
																	key="div_noplayers" /></b></td>
													</c:when>
													<c:otherwise>
														<c:forEach items="${result}" var="away">
															<c:forEach items="${away.awayScorer}" var="as">
																<tr>
																	<td><a href="player?id=${as.ID}"> <c:choose>
																				<c:when test="${as.hidePage eq true }">
																					<fmt:message key="name_withheld" />
																				</c:when>
																				<c:otherwise>
																					<c:out value="${as.name}" />
																				</c:otherwise>
																			</c:choose>
																	</a></td>
																	<td style="text-align: center"><c:out
																			value="${as.goals}" /></td>
																	<td style="text-align: center"><c:out
																			value="${as.yellowCards}" /></td>
																	<td style="text-align: center"><c:out
																			value="${as.redCards}" /></td>
																</tr>
															</c:forEach>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
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
					&copy; <img src="images/logo_sm4.png" /> <script>document.writenew Date.getFullYear</script>
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
	<script type="text/javascript" src="js/gamePage.js"></script>
</body>
</html>