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
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<link href="css/carousel.css" rel="stylesheet">
<link href="css/maps.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - <c:choose>
			<c:when test="${empty team}">
				Team
			</c:when>
			<c:otherwise>
				<c:forEach items="${team}" var="t">
					<c:out value="${t.teamName}" />
				</c:forEach>
			</c:otherwise>
		</c:choose></title>
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
						<li class="nav-item active"><a class="nav-link" href="index"><fmt:message
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
						<c:choose>
							<c:when test="${signedIn == null}">
								<li class="nav-item"><a class="nav-link" href="./login">Sign
										In</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="${userType}">${userName}</a></li>
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
		- cards with information on them
		- widgets
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<c:choose>
						<c:when test="${empty team}">
									Team
									</c:when>
						<c:otherwise>
							<c:forEach items="${team}" var="t">
								<c:out value="${t.teamName}" />
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body">
								<c:choose>
									<c:when test="${empty team}">
									No team information
									</c:when>
									<c:otherwise>
										<c:forEach items="${team}" var="t">
											<c:if test="${not empty t.teamLogo }">
												<center>
													<img src="${t.teamLogo}"
														style="width: 100%; max-width: 500px; height: auto" />
												</center>
											</c:if>
											<table>
												<c:choose>
													<c:when test="${empty t.teamAbout}">
														<tr>
															<td><b>About Team</b></td>
															<td>No information given</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>About Team</b></td>
															<td><c:out value="${t.teamAbout}" escapeXml="false" /></td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty t.divisionName}">
														<tr>
															<td><b>Division</b></td>
															<td>No division</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Division</b></td>
															<td><c:out value="${t.divisionName}" /></td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty t.teamAbbreviation}">
														<tr>
															<td><b>Abbreviation</b></td>
															<td>NAB</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
													<td><b>Abbreviation</b></td>
													<td><c:out value="${t.teamAbbreviation}" /></td>
												</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty teamowner}">
														<tr>
															<td><b>Team Owner</b></td>
															<td>Not Available</td>
														</tr>
														<tr>
															<td><b>Contact</b></td>
															<td>Not Available</td>
														</tr>
													</c:when>
													<c:otherwise>
														<c:forEach items="${teamowner}" var="to">
															<tr>
																<td><b>Team Owner</b></td>
																<td><c:out value="${to.firstName}" /> <c:out
																		value="${to.lastName}" /></td>
															</tr>
															<tr>
																<td><b>Contact</b></td>
																<td><a
																	href="mailto:<c:out value="${to.emailAddress}" />">
																		Contact Team Owner</a></td>
															</tr>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</table>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body">
								<h4 class="card-header">Current Standings</h4>
								<table id="standings"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text5" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text6" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text7" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text8" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text9" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text10" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty standings}">
												<td colspan=10 style="text-align: center"><b><fmt:message
															key="div_noteams" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${standings}" var="stand">
													<c:forEach items="${team}" var="t">
														<c:choose>
															<c:when test="${stand.teamName eq t.teamName}">
																<tr>
																	<td class="bg-primary" scope="row"
																		style="text-align: center"><c:out
																			value="${stand.rank}" /></td>
																	<td class="bg-primary" scope="row"><c:out
																			value="${stand.teamName}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.gamesPlayed}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.wins}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.losses}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.draws}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.points}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.goalsFor}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.goalsAgainst}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.goalDiff}" /></td>
																</tr>
															</c:when>
															<c:otherwise>
																<tr>
																	<td scope="row" style="text-align: center"><c:out
																			value="${stand.rank}" /></td>
																	<td scope="row"><c:out value="${stand.teamName}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.gamesPlayed}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.wins}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.losses}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.draws}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.points}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.goalsFor}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.goalsAgainst}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.goalDiff}" /></td>
																</tr>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					-->
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body">
								<h4 class="card-header">Upcoming Games</h4>
								<table id="standings"
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
											<th scope="col" style="text-align: center">Venue</th>
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
																<fmt:formatDate type="date" pattern="d MMM y"
																	value="${sched.date}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="MMM d y"
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
														<td><c:out value="${sched.homeTeam}" /></td>
														<td><c:out value="${sched.awayTeam}" /></td>
														<td><c:out value="${sched.venue}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body">
								<h4 class="card-header">Recent Results</h4>
								<table id="standings"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text5" /></th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty results}">
												<td colspan=7 style="text-align: center"><b><fmt:message
															key="div_noresults" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${results}" var="res">
													<tr>
														<td scope="row" style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="date" pattern="d MMM y"
																	value="${res.date}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="MMM d y"
																	value="${res.date}" />
															</c:if></td>
														<td><c:out value="${res.homeTeam}" /></td>
														<td style="text-align: center"><c:out
																value="${res.homeScore}" /></td>
														<td><c:out value="${res.awayTeam}" /></td>
														<td style="text-align: center"><c:out
																value="${res.awayScore}" /></td>
														<td style="text-align: center"><c:out
																value="${res.status}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body">
								<h4 class="card-header">Team Statistics</h4>
								<table id="standings"
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
														<td scope="row"><c:out value="${stats.teamName}" /></td>
														<td><c:out value="${stats.name}" /></td>
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
			</fmt:bundle>
			<!-- /.row -->
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
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>