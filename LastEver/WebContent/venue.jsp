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
			<c:when test="${empty venue}">
				<fmt:message key="venue_head1" />
			</c:when>
			<c:otherwise>
				<c:forEach items="${venue}" var="v">
					<c:out value="${v.venueName}" />
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
						<c:when test="${empty venue}">
							<fmt:message key="venue_head1" />
						</c:when>
						<c:otherwise>
							<c:forEach items="${venue}" var="v">
								<c:out value="${v.venueName}" />
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
									<c:when test="${empty venue}">
										<fmt:message key="venue_no_info" />
									</c:when>
									<c:otherwise>
										<c:forEach items="${venue}" var="v">
											<c:if test="${not empty v.venuePicture }">
												<center>
													<img src="${v.venuePicture}"
														style="width: 100%; max-width: 500px; height: auto" />
												</center>
											</c:if>
											<table>
												<tr>
													<td><b><fmt:message key="venue_about" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueAbout}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${v.venueAbout}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_contact" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueContact}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${v.venueContact}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_phone_number" /></b></td>
													<c:choose>
														<c:when test="${empty v.venuePhoneNumber}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><a
																href="tel:+<c:out value="${v.venuePhoneNumber}" />">
																	<c:out value="${v.venuePhoneNumber}" />
															</a></td>
														</c:otherwise>
													</c:choose>
												<tr>
													<td><b><fmt:message key="venue_email" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueEmail}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><a
																href="mailto:<c:out value="${v.venueEmail}" />"> <fmt:message
																		key="venue_email_us" /></a></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_address" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueAddress}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${v.address1}" /> <c:out
																	value="${v.address2}" /> <br> <c:out
																	value="${v.city}" />, <c:out value="${v.province}" />
																<c:out value="${v.country}" /><br> <c:out
																	value="${v.postal}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_map" /></b></td>
													<td><div id="map"></div> <script>
														function initMap() {
															var map = new google.maps.Map(
																	document
																			.getElementById('map'),
																	{
																		zoom : 15,
																		center : {
																			lat : 25,
																			lng : 25
																		}
																	});
															var geocoder = new google.maps.Geocoder();

															geocodeAddress(
																	geocoder,
																	map);
														}

														function geocodeAddress(
																geocoder,
																resultsMap) {
															var address = <c:forEach items="${venue}" var="v">
															"${v.venueAddress}"
															</c:forEach>;

															geocoder
																	.geocode(
																			{
																				'address' : address
																			},
																			function(
																					results,
																					status) {
																				if (status === 'OK') {
																					resultsMap
																							.setCenter(results[0].geometry.location);
																					var marker = new google.maps.Marker(
																							{
																								map : resultsMap,
																								position : results[0].geometry.location
																							});
																				}
																			});
														}
													</script> <script
															src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGUmrbP4bA8jEkouNt9KIRFlBzpyT5oUA&callback=initMap"></script>
													</td>
												</tr>
											</table>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card bg-light">
							<div class="card-header">
								<h4 class="card-header">
									<fmt:message key="div_head2" />
								</h4>
								<ul class="nav nav-tabs card-header-tabs">
									<c:forEach items="${allDiv}" var="division">
										<li class="nav-item"><a
											class="nav-link ${division.divisionId==divID?'active':''}"
											href="venue?id=${venID}&div=${division.divisionId}#standings">${division.divisionName}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="card-body">
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