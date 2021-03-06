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
	<link href="css/weather.css" rel="stylesheet">
	<fmt:bundle basename="TestBundle">
		<title>Last Ever - <fmt:message key="home" /></title>
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
					<li class="nav-item active"><a class="nav-link" href="index"><fmt:message
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
											aria-haspopup="true" aria-expanded="false"> <c:out
											value="${userName}" />
									</a>
										<div class="dropdown-menu dropdown-menu-right"
											 aria-labelledby="navbarDropdownPortfolio">
											<a class="dropdown-item" href="${userType}"><c:out
													value="${userName}" /></a> <a class="dropdown-item"
																				  href="adminUsers"><fmt:message key="nav_admin_users" /></a>
											<a class="dropdown-item" href="adminTeams"><fmt:message
													key="nav_admin_teams" /></a> <a class="dropdown-item"
																					href="adminDivisions"><fmt:message key="nav_admin_divs" /></a>
											<a class="dropdown-item" href="adminSchedule"><fmt:message
													key="nav_admin_sched" /></a> <a class="dropdown-item"
																					href="adminEmails"><fmt:message key="nav_admin_email" /></a>
											<a class="dropdown-item" href="adminLeagues"><fmt:message
													key="nav_leagues" /></a> <a class="dropdown-item"
																				href="logout"><fmt:message key="team_dd4" /></a>
										</div></li>
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
    - widgets
    -->
		<div class="cards-container container">
			<h1 class="my-4">
				<fmt:message key="home_header" />
			</h1>
			<!-- Marketing Icons Section -->
			<div class="row">
				<div class="col-lg-8 mb-5">
					<div class="card">
						<div class="card-body">
							<div id="mainCarousel" class="carousel slide"
								 data-ride="carousel">
								<div class="carousel-inner">
									<div class="carousel-item active">
										<img class="d-block w-100"
											 src="https://images.pexels.com/photos/114296/pexels-photo-114296.jpeg?w=1920&h=1080"
											 alt="First slide">
									</div>
									<div class="carousel-item">
										<img class="d-block w-100"
											 src="https://images.pexels.com/photos/17598/pexels-photo.jpg?w=1920&h=1080"
											 alt="Second slide">
									</div>
								</div>
								<a class="carousel-control-prev" href="#mainCarousel"
								   role="button" data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
									<span class="sr-only">Previous</span>
								</a> <a class="carousel-control-next" href="#mainCarousel"
										role="button" data-slide="next"> <span
									class="carousel-control-next-icon" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5">
					<div class="card">
						<h4 class="card-header">
							<fmt:message key="home_head" />
						</h4>
						<div class="card-body">
							<c:choose>
								<c:when test="${empty weather}">
									<b style="text-align: center"><fmt:message
											key="weather_no_data" /></b>
								</c:when>
								<c:otherwise>
									<table style="width: 100%">
										<tr>
											<c:choose>
												<c:when test="${empty weather.weatherCity}">
													<td colspan="2" id="weather-city"><b>Ottawa, CA <br></b></td>
												</c:when>
												<c:otherwise>
													<td colspan="2" id="weather-city"><b><c:out
															value="${weather.weatherCity }" />, <c:out
															value="${weather.weatherCountry }" /> <br></b></td>
												</c:otherwise>
											</c:choose>
										</tr>
										<tr>
											<td id="weather-temp"><b><c:choose>

												<c:when
														test="${weather.weatherTemp gt -0.5 and weather.weatherTemp lt 0 }">
													<!-- To prevent -0 from showing as the current tempreature -->
													0&deg;C
												</c:when>
												<c:when test="${empty weather.weatherTemp}">
													N/A
												</c:when>
												<c:otherwise>
													<!-- Show on decimal point for better accuracy
													Can show 0 digits if you want -->
													<fmt:formatNumber maxFractionDigits="0"
																	  value="${weather.weatherTemp}" />&deg;C
												</c:otherwise>
											</c:choose> </b></td>
											<td id="weather" style="width: 45%"><c:choose>
                                                <c:when
                                                        test="${weather.weatherCode eq 800}">
                                                    <!-- Show day/night weather icons for codes 800 -->
                                                    <i class="icon owm-${weather.weatherCode}-c"></i>
                                                    <i class="icon owm-${weather.weatherCode}-${weather.weatherDay}"></i>
                                                </c:when>
												<c:when
														test="${weather.weatherCode gt 800 and weather.weatherCode lt 804}">
													<!-- Show day/night weather icons for codes 800-803 otherwise show generic ones -->
                                                    <i class="icon-sm owm-${weather.weatherCode}-c"></i>
                                                    <i class="icon owm-${weather.weatherCode}-${weather.weatherDay}"></i>
												</c:when>
												<c:when test="${weather.weatherCode eq 0}">
                                                    <i class="icon owm-800-c"></i>
                                                    <i class="icon owm-800-day"></i>
												</c:when>
												<c:otherwise>
                                                    <i class="icon owm-${weather.weatherCode}-c"></i>
                                                    <i class="icon owm-${weather.weatherCode}"></i>
												</c:otherwise>
											</c:choose></td>
										</tr>
										<tr>
											<c:choose>
												<c:when test="${empty weather.weatherDescription}">
													<td id="weather-description" colspan="2">
														<fmt:bundle basename="weather">
														<fmt:message key="unknown" />
														</fmt:bundle>
													</td>
												</c:when>
												<c:otherwise>
													<td id="weather-description" colspan="2"><fmt:bundle basename="weather">
														<fmt:message key="${weather.weatherDescription}" />
													</fmt:bundle></td>
												</c:otherwise>
											</c:choose>
										</tr>
										<tr id="wind">
											<c:choose>
												<c:when test="${empty weather.weatherWind}">
													<td id="weather-wind"><b><fmt:message
															key="weather_wind" /></b></td>
													<td>N/A</td>
												</c:when>
												<c:otherwise>
													<td id="weather-wind"><b><fmt:message
															key="weather_wind" /></b></td>
													<td><fmt:formatNumber maxFractionDigits="0"
																		  value="${weather.weatherWind}" /> km/h</td>
												</c:otherwise>
											</c:choose>
										</tr>
										<!-- Only show wind gust if there is a wind gust occurring -->
										<c:if test="${weather.weatherGust gt 0.0}">
											<tr id="wind-gust">
												<td id="weather-gust"><b><fmt:message
														key="weather_gust" /></b></td>
												<td><fmt:formatNumber maxFractionDigits="0"
																	  value="${weather.weatherGust}" /> km/h</td>
											</tr>
										</c:if>
										<tr id="humidity">
											<c:choose>
												<c:when test="${empty weather.weatherWind}">
													<td id="weather-humidity"><b><fmt:message
															key="weather_humidity" /></b></td>
													<td>N/A</td>
												</c:when>
												<c:otherwise>
													<td id="weather-humidity"><b><fmt:message
															key="weather_humidity" /></b></td>
													<td><c:out value="${weather.weatherHumidity}" /> %</td>
												</c:otherwise>
											</c:choose>
										</tr>
										<tr id="pressure">
											<c:choose>
												<c:when test="${empty weather.weatherPressure}">
													<td id="weather-pressure"><b><fmt:message
															key="weather_pressure" /></b></td>
													<td>N/A</td>
												</c:when>
												<c:otherwise>
													<td id="weather-pressure"><b><fmt:message
															key="weather_pressure" /></b></td>
													<td><fmt:formatNumber maxFractionDigits="1"
																		  value="${weather.weatherPressure}" /> kPa</td>
												</c:otherwise>
											</c:choose>
										</tr>
										<tr>
											<!-- Link to where the data was fetched from -->
											<td><a href="https://openweathermap.org/">OpenWeatherMap</a></td>
											<td id="weather-update"><fmt:formatDate type="both"
																					pattern="YYYY-MM-dd H:mm" value="${currtime}"
																					timeZone="America/Toronto" /></td>
										</tr>
									</table>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${empty news}">
						<div class="col-lg-12 mb-5">
							<div class="card">
								<h4 class="card-header">
									<fmt:message key="news_no_news" />
								</h4>
								<div class="card-body">
									<b style="text-align: center"><fmt:message
											key="news_no_news_message" /></b>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${news}" var="n">
							<div class="col-lg-12 mb-5 mt-5">
								<div class="card">
									<div class="card-header">
										<h4>
											<c:choose>
												<c:when test="${cookie.language.value == 'fr' }">
													<c:out value="${n.titleFR}" />
												</c:when>
												<c:otherwise>
													<c:out value="${n.title}" />
												</c:otherwise>
											</c:choose>
										</h4>
									</div>
									<div class="card-body">
											<b>
											<c:out value="${n.userName}" />
											</b> <c:out value="${n.postedTime}" />
										<br />
										<c:choose>
											<c:when test="${cookie.language.value == 'fr' }">
												<c:out value="${n.contentFR}" escapeXml="false" />
											</c:when>
											<c:otherwise>
												<c:out value="${n.content}" escapeXml="false" />
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<div class="col-lg-12 mt-5 mb-5">
					<div class="card">
						<div class="card-body">
							<center>
								<a href="news"><fmt:message key="news_view_all" /></a>
							</center>
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
				&copy; <img src="images/logo_sm4.png" /> <script>document.write(new Date().getFullYear())</script>
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