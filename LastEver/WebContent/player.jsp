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
<link href="css/carousel.css" rel="stylesheet">
<link href="css/maps.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - <c:choose>
			<c:when test="${empty player}">
				<fmt:message key="player" />
			</c:when>
			<c:otherwise>
				<c:forEach items="${player}" var="p">
					<c:choose>
						<c:when test="${p.hidePage eq true}">Name Witheld</c:when>
						<c:otherwise>
							<c:out value="${p.playerFirstName}" />
							<c:out value="${p.playerLastName}" />
						</c:otherwise>
					</c:choose>
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
									<c:when test="${empty league}">

										<a class="dropdown-item" href=""><fmt:message
												key="nav_divisions" /></a>
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
								</c:choose>
								<c:choose>

									<%--  IF SIGNED IN AS A REFEREE --%>
									<c:when test="${userType == './referee'}">
										<li class="nav-item"><a class="nav-link"
											href="${userType}">${userName}</a></li>
									</c:when>
								</c:choose>
								<c:choose>

									<%--  IF SIGNED IN AS ADMIN --%>
									<c:when test="${userType == './admin'}">
										<li class="nav-item"><a class="nav-link"
											href="${userType}">${userName}</a></li>
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

	<div class="main-cover">
		<!-- Page Content
		- cards with information on them
		- widgets
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<c:choose>
						<c:when test="${empty player}">
							<fmt:message key="player" />
						</c:when>
						<c:otherwise>
							<c:forEach items="${player}" var="p">
								<c:choose>
									<c:when test="${p.hidePage eq true}">Name Witheld</c:when>
									<c:otherwise>
										<c:out value="${p.playerFirstName}" />
										<c:out value="${p.playerLastName}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<c:choose>
									<c:when test="${empty player}">
										<fmt:message key="player_no_info" />
									</c:when>
									<c:otherwise>
										<c:forEach items="${player}" var="p">
											<c:choose>
												<c:when test="${p.hidePage eq true}">
										This player has chosen to keep his information private. Sorry for the inconvience.
										</c:when>
												<c:otherwise>
													<c:if test="${not empty p.playerPhoto }">
														<center>
															<img src="${p.playerPhoto}"
																style="width: 100%; max-width: 500px; height: auto" />
														</center>
													</c:if>
													<table>
														<tr>
															<td><b><fmt:message key="player_current_team" /></b></td>
															<c:choose>
																<c:when test="${empty p.teamName}">
																	<td><fmt:message key="not_available" /></td>
																</c:when>
																<c:otherwise>
																	<td><c:out value="${p.teamName}" /></td>

																</c:otherwise>
															</c:choose>
														</tr>
														<tr>
															<td><b><fmt:message key="player_division" /></b></td>
															<c:choose>
																<c:when test="${empty p.divisionName}">
																	<td><fmt:message key="not_available" /></td>
																</c:when>
																<c:otherwise>
																	<td><c:out value="${p.divisionName}" /></td>
																</c:otherwise>
															</c:choose>
														</tr>
														<tr>
															<td><b><fmt:message key="player_number" /></b></td>
															<c:choose>
																<c:when test="${empty p.playerNumber}">
																	<td><fmt:message key="not_available" /></td>
																</c:when>
																<c:otherwise>
																	<td>#<c:out value="${p.playerNumber}" /></td>
																</c:otherwise>
															</c:choose>
														</tr>
														<tr>
															<td><b><fmt:message key="player_position" /></b></td>
															<c:choose>
																<c:when test="${empty p.playerPosition}">
																	<td><fmt:message key="not_available" /></td>
																</c:when>
																<c:otherwise>
																	<td><c:out value="${p.playerPosition}" /></td>
																</c:otherwise>
															</c:choose>
														</tr>
														<tr>
															<td><b><fmt:message key="player_country" /></b></td>
															<c:choose>
																<c:when test="${empty p.playerCountry}">
																	<td><fmt:message key="not_available" /></td>
																</c:when>
																<c:otherwise>
																	<td><c:out value="${p.playerCountry}" /></td>

																</c:otherwise>
															</c:choose>
														</tr>
														<tr>
															<td><b><fmt:message key="player_height" /></b></td>
															<c:choose>
																<c:when test="${p.playerHeight eq 0.0}">
																	<td><fmt:message key="not_available" /></td>
																</c:when>
																<c:otherwise>
																	<td><fmt:formatNumber value="${p.playerHeight}"
																			groupingUsed="true" /> cm</td>
																</c:otherwise>
															</c:choose>
														</tr>
														<tr>
															<td><b><fmt:message key="player_weight" /></b></td>
															<c:choose>
																<c:when test="${p.playerWeight eq 0.0}">
																	<td><fmt:message key="not_available" /></td>
																</c:when>
																<c:otherwise>
																	<td><fmt:formatNumber value="${p.playerWeight}"
																			groupingUsed="true" /> kg</td>
																</c:otherwise>
															</c:choose>
														</tr>
													</table>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card bg-light">
							<div class="card-body table-responsive">
								<table id="standings"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
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
												<td colspan=6 style="text-align: center"><b><fmt:message
															key="div_noplayers" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${statistics}" var="stats">
													<tr>
														<td><a href="team?id=${stats.teamID}">${stats.teamName}</a></td>
														<td><c:forEach items="${player}" var="p">
																<c:choose>
																	<c:when test="${p.hidePage eq true}">
																	Name Witheld
																	</c:when>
																	<c:otherwise>
																		<c:out value="${stats.name}" />
																	</c:otherwise>
																</c:choose>
															</c:forEach></td>
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
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>