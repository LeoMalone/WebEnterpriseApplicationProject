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
			<c:when test="${empty player}">
				Player
			</c:when>
			<c:otherwise>
				<c:forEach items="${player}" var="p">
					<c:out value="${p.playerFirstName}" /> 
					<c:out value="${p.playerLastName}" />
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
						<c:when test="${empty player}">
							Player
						</c:when>
						<c:otherwise>
							<c:forEach items="${player}" var="p">
								<c:out value="${p.playerFirstName}" /> 
								<c:out value="${p.playerLastName}" />
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
									No player information
									</c:when>
									<c:otherwise>
										<c:forEach items="${player}" var="p">
											<c:if test="${not empty p.playerPhoto }">
												<center>
													<img src="${p.playerPhoto}"
														style="width: 100%; max-width: 500px; height: auto" />
												</center> 
											</c:if>
											<table>
												<c:choose>
													<c:when test="${empty team}">
														<tr>
															<td><b>Current Team</b></td>
															<td>Not on a team</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Current Team</b></td>
															<td><c:out value="${team}" /></td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty division}">
														<tr>
															<td><b>Division</b></td>
															<td>No division</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Division</b></td>
															<td><c:out value="${division}" /></td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty p.playerNumber}">
														<tr>
															<td><b>Player Number</b></td>
															<td>No number</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Player Number</b></td>
															<td>#<c:out value="${p.playerNumber}" /></td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty p.playerPosition}">
														<tr>
															<td><b>Player Position</b></td>
															<td>No position</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Player Position</b></td>
															<td><c:out value="${p.playerPosition}" /></td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${empty p.playerCountry}">
														<tr>
															<td><b>Country</b></td>
															<td>Not Available</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Country</b></td>
															<td><c:out value="${p.playerCountry}" /></td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${p.playerHeight eq 0.0}">
														<tr>
															<td><b>Height</b></td>
															<td>Not Available</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Height</b></td>
															<td><c:out value="${p.playerHeight}" /> cm</td>
														</tr>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${p.playerWeight eq 0.0}">
														<tr>
															<td><b>Weight</b></td>
															<td>Not Available</td>
														</tr>
													</c:when>
													<c:otherwise>
														<tr>
															<td><b>Weight</b></td>
															<td><c:out value="${p.playerWeight}" /> kg</td>
														</tr>
													</c:otherwise>
												</c:choose>
											</table>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card bg-light">
							<div class="card-body">
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