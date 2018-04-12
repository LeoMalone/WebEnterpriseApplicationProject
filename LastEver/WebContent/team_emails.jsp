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
<script defer
	src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
	<fmt:bundle basename="TestBundle">
<title>Last Ever - <fmt:message key="team_email"/></title>
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
											key="rules" /></a> <a class="dropdown-item"
										href="./registration"><fmt:message key="registration" /></a>
									<a class="dropdown-item" href="./contact"><fmt:message
											key="contact" /></a>
								</div></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownPortfolio" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <fmt:message
												key="nav_league" /> </a>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="navbarDropdownPortfolio">
									<c:choose>
										<c:when test="${empty league}">

											<a class="dropdown-item" href=""><fmt:message
													key="nav_league" /></a>
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
							<c:when test="${signedIn == null}">
								<li class="nav-item"><a class="nav-link" href="./login"><fmt:message key="nav_signin" /></a></li>
							</c:when>
							<c:otherwise>
								
								
								<!-- <li class="nav-item"><a class="nav-link" href="${userType}"><c:out value="${userName}"/></a></li> -->
								
								<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <c:out value="${userName}"/>
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">

								<a class="dropdown-item" href="${userType}"><c:out value="${userName}"/><fmt:message key="team_dd1" /></a>
								<a class="dropdown-item" href="teamRoster"><fmt:message key="team_dd2" /></a>
								<a class="dropdown-item" href="teamSchedule"><fmt:message key="team_dd3" /></a>
								<a class="dropdown-item" href="teamEmails"><fmt:message key="team_dd6" /></a>
								<a class="dropdown-item" href="logout" method="post"><fmt:message key="team_dd4" /></a>
							</div></li>
								
							</c:otherwise>
						</c:choose>
							<li class="nav-item"><a class="nav-link" href=""></a></li>
							<li class="nav-item">
								<form>
									<select class="form-control form-control-sm" name="language"
										onchange="submit()">
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
				<h1 class="my-4"><c:out value="${userName}"/>: <fmt:message key="team_emails1"/></h1>
				<form action="./teamEmails" method="POST">
					<button type="submit" class="btn btn-success"><fmt:message key="team_emails2"/></button>
				</form>
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div id="accordion">
						  <div class="card">
						    <div class="card-header" id="headingOne">
						      <h5 class="mb-0">
						        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
						         <fmt:message key="team_emails3"/>
						        </button>
						      </h5>
						    </div>						
						    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
						      <div class="card-body">
						      	<form action="./teamEmails?=1" method="POST">
						      		<c:forEach items="${admins}" var="admin">
										<div class="form-check">
										  <input name="admins" class="form-check-input" type="checkbox" value="${admin.emailAddress}" id="defaultCheck1">
										  <label class="form-check-label" for="defaultCheck1"><b>${admin.firstName} ${admin.lastName}:</b> ${admin.emailAddress}</label>
										</div>
									</c:forEach>
									<hr />
									<button type="submit" class="btn btn-outline-dark"><fmt:message key="team_emails4"/></button>
						      	</form>						      							      							        
						      </div>
						    </div>
						  </div>
						  <div class="card">
						    <div class="card-header" id="headingTwo">
						      <h5 class="mb-0">
						        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
						          <fmt:message key="team_emails6"/>
						        </button>
						      </h5>
						    </div>
						    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
						      <div class="card-body">
						      	<div class="form-check">
									<form action="./teamEmails?=2" method="POST">
							      		<c:forEach items="${tos}" var="to">
											<div class="form-check">
											  <input name="tos" class="form-check-input" type="checkbox" value="${to.emailAddress}" id="defaultCheck1">
											  <label class="form-check-label" for="defaultCheck1"><b><c:out value="${to.firstName} ${to.lastName}:" /></b><c:out value=" ${to.emailAddress}" /></label>
											</div>
										</c:forEach>
										<hr />
										<button type="submit" class="btn btn-outline-dark"><fmt:message key="team_emails4"/></button>
						      		</form>
								</div>
						      </div>
						    </div>
						  </div>
						  
						  <!-- 
						  <div class="card">
						    <div class="card-header" id="headingThree">
						      <h5 class="mb-0">
						        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
						        	Players						          
						        </button>
						      </h5>
						    </div>
						    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
						      <div class="card-body">
						      	<div class="form-check">
									<form action="./teamEmails?=3" method="POST">
							      		<c:forEach items="${players}" var="players">
											<div class="form-check">
											  <input name="refs" class="form-check-input" type="checkbox" value="${players.emailAddress}" id="defaultCheck1">
											  <label class="form-check-label" for="defaultCheck1"><b>${players.firstName} ${players.lastName}:</b> ${players.emailAddress}</label>
											</div>
										</c:forEach>
										<hr />
										<button type="submit" class="btn btn-outline-dark">Send</button>
						      		</form>
								</div>
						      </div>
						    </div>
						  </div>
						  -->
						</div>
					</div>
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

		<!-- Bootstrap core JavaScript -->
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
			integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
			crossorigin="anonymous"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	</fmt:bundle>
</body>
</html>