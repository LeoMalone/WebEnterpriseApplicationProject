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
	<title>Last Ever - <fmt:message key="contact" /></title>
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

						<li class="nav-item dropdown active"><a
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
							aria-haspopup="true" aria-expanded="false"> ${userName}
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">

								<a class="dropdown-item" href="${userType}">${userName}<fmt:message key="team_dd1" /></a>
								<a class="dropdown-item" href="teamRoster"><fmt:message key="team_dd2" /></a>
								<a class="dropdown-item" href="teamSchedule"><fmt:message key="team_dd3" /></a>
								<a class="dropdown-item" href="logout"><fmt:message key="team_dd4" /></a>
							</div></li>
								</c:when>
								</c:choose>
								<c:choose>
								
								<%--  IF SIGNED IN AS A REFEREE --%>
								<c:when test="${userType == './referee'}">
								<li class="nav-item"><a class="nav-link" href="${userType}">${userName}${userName}${userName}</a></li>
								</c:when>
								</c:choose>
								<c:choose>
								
								<%--  IF SIGNED IN AS ADMIN --%>
								<c:when test="${userType == './admin'}">
								<li class="nav-item"><a class="nav-link" href="${userType}">${userName}${userName}</a></li>
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
		- card with information on it
		- text, mailto link
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<fmt:message key="contact_header" />
				</h1>
				<!-- Email and Phone Number -->
				<div class="row" align="center">
					<div class="col-lg-4 mb-4" align="center">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="contact_head1" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<fmt:message key="contact_text1" />
								</p>
							</div>
						</div>
					</div>
					<!-- Google Maps Location -->
					<div class="col-lg-4 mb-4" align="center">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="ab_head3" />
							</h4>
							<div class="card-body">
								<iframe
									src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d22409.355508682052!2d-75.70157807521635!3d45.40592270176919!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x4a69b90dfe9b6eb2!2sTD+Place!5e0!3m2!1sen!2sca!4v1518301220254"
									width="300px" height="300px" frameborder="0" style="border: 0"
									allowfullscreen></iframe>
								<p class="card-text">
									<br />
									<fmt:message key="ab_text3" />
								</p>
							</div>
						</div>
					</div>
					<!-- Get in Touch! -->
					<div class="col-lg-4 mb-4" align="center">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="ab_head4" />
							</h4>
					<form action="./getInTouch" method="POST"> <!-- Need to create getInTouch page-->
							<div class="card-body">
								<p class="card-text">
									<div class="form-group" align="left">
										<label for="newFirstName">First Name</label>
										<input type="text" class="form-control" name="newFirstName" placeholder="You Name">
									</div>
									<div class="form-group" align="left">
										<label for="newLastName">Last Name</label>
										<input type="text" class="form-control" name="newLastName" placeholder="Your Last Name">
									</div>
									<div class="form-group" align="left">
									<!-- ADD city -->
										<label for="city">City</label>
									    <select id="city" name="city">
									      <option value="ottawa">Ottawa</option>
									      <option value="nepean">Nepean</option>
									      <option value="gatineau">Gatineau</option>
									      <option value="kanata">Kanata</option>
									   	</select>
									</div>
									<div class="form-group" align = "left">
										<!-- Add subject -->
										<label for="subject">Subject</label> <br> 
    									<textarea id="subject" name="subject" style = "width:100%" placeholder="Write something..">
    									</textarea>									
									</div>
								</p>
							</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-secondary"><fmt:message key="signin_button1"/></button>	
								</div>
							</form>
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>