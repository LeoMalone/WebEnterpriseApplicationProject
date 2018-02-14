<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<!-- if language is not set to French, set language to English -->
<c:if test="${param.language ne 'fr'}">
	<html lang="en">
<c:set var="language" value="en" />
</c:if>
<c:if test="${param.language eq 'fr'}">
	<html lang="fr">
</c:if>
<fmt:setLocale value="${param.language}" />
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/lastever" user="admin"
	password="lastever" />

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - <fmt:message key="home" /></title>
</fmt:bundle>
</head>

<body>

	<!-- nav bar - home, league(about, rules, register, contact us), divisions (womens, mens), sign in 
	- sets parent link active
	- in dropdown, sets active with full bar color
	-->

	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = 'https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.12';
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<sql:query dataSource="${dataSource}" var="div1">
	select divisionID, divsionName from division
	</sql:query>
	<nav
		class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="index.jsp"><img
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
						<li class="nav-item"><a class="nav-link active"
							href="index.jsp"><fmt:message key="nav_home" /></a></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <fmt:message
									key="nav_league" />
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">

								<a class="dropdown-item" href="about.jsp"><fmt:message
										key="about" /></a> <a class="dropdown-item" href="rules.jsp"><fmt:message
										key="rules" /></a> <a class="dropdown-item"
									href="registration.jsp"><fmt:message key="registration" /></a>
								<a class="dropdown-item" href="contact.jsp"><fmt:message
										key="contact" /></a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> Divisions </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
								<c:choose>
									<c:when test="${div1.rowCount == 0}">

										<a class="dropdown-item" href=""><fmt:message
												key="nav_divisions" /></a>
									</c:when>
									<c:otherwise>
										<c:forEach var="row" items="${div1.rows}">
											<a class="dropdown-item"
												href="division.jsp?id=${row.divisionID}">${row.divsionName}</a>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div></li>



						<li class="nav-item"><a class="nav-link" href="login.jsp"><fmt:message
									key="nav_signin" /></a></li>
						<li class="nav-item"><a class="nav-link" href=""></a></li>


						<li class="nav-item">
							<form action="" method="post">
								<select class="form-control form-control-sm" name="language"
									onchange="this.form.submit()">
									<option value="en" ${param.language == 'en' ? 'selected' : ''}><fmt:message
											key="english" /></option>
									<option value="fr" ${param.language == 'fr' ? 'selected' : ''}><fmt:message
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
					<fmt:message key="home_header" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="home_head1" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<div class="fb-page" data-href="https://fb.me/lasteversports"
									data-tabs="timeline" data-small-header="false"
									data-adapt-container-width="true" data-hide-cover="false"
									data-show-facepile="true">
									<blockquote cite="https://fb.me/lasteversports"
										class="fb-xfbml-parse-ignore">
										<a href="https://fb.me/lasteversports"> LastEver Sports</a>
									</blockquote>
								</div>


								</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="home_head2" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<iframe
										src="https://scorestream.com/widgets/scoreboards/vert?userWidgetId=21758"
										style="padding: 0px; border: 0px; width: 300px; height: 500px; max-height: 500px;"
										title="Realtime sports scoreboard widget" scrolling="no"
										frameBorder="0"></iframe>
								</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="home_head3" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<div id="plemx-root"></div>


								<div style="width: 320px;">
									<iframe style="display: block;"
										src="https://cdnres.willyweather.com/widget/loadView.html?id=81845"
										width="320" height="500" frameborder="0" scrolling="no"></iframe>
									<a
										style="display: block; z-index: 1; height: 20px; text-indent: -9999em; margin: -20px 0 0 0; position: relative"
										href="https://www.willyweather.com/pa/montour-county/ottawa.html"
										rel="nofollow">Forecast</a>
								</div>




								</p>
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