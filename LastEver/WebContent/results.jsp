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

<!-- TODO: Replace with Servlet -->
<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/lastever" user="admin"
	password="lastever" />

<!-- TODO: Do in query in Servlet -->
<sql:query dataSource="${dataSource}" var="div1">
select divsionName, divisionID from division where divisionID = ?
<sql:param value="${param.id}" />
</sql:query>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<c:choose>
		<c:when test="${div1.rowCount == 0}">

			<title>Last Ever - Division Results</title>
		</c:when>
		<c:otherwise>
			<title>Last Ever - <c:forEach var="row" items="${div1.rows}">
					<c:out value="${row.divsionName}" />
				</c:forEach> Results
			</title>
		</c:otherwise>
	</c:choose>
</fmt:bundle>
</head>

<body>

	<!-- nav bar - home, league(about, rules, register, contact us), divisions (womens, mens), sign in 
	- sets parent link active
	- in dropdown, sets active with full bar color
	-->
	<!-- TODO: Do in query in Servlet -->
	<sql:query dataSource="${dataSource}" var="div2">
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
						<li class="nav-item"><a class="nav-link" href="index.jsp"><fmt:message
									key="nav_home" /></a></li>

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
							class="nav-link dropdown-toggle active" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> Divisions </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
								<c:choose>
									<c:when test="${div2.rowCount == 0}">

										<a class="dropdown-item active" href=""><fmt:message
												key="nav_divisions" /></a>
									</c:when>
									<c:otherwise>
										<c:forEach var="row" items="${div2.rows}">
											<a class="dropdown-item"
												href="division.jsp?id=${row.divisionID}">${row.divsionName}</a>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div></li>
						<%
							if (session.getAttribute("signedIn") != null) {
						%>
						<li class="nav-item"><a class="nav-link"
							href="<%=session.getAttribute("userType")%>">${userName}</a></li>
						<%
							} else {
						%>
						<li class="nav-item"><a class="nav-link" href="login.jsp"><fmt:message
									key="nav_signin" /></a></li>
						<%
							}
						%>
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
					<c:forEach var="row" items="${div1.rows}">
						<c:out value="${row.divsionName}" />
					</c:forEach>
					Results
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<!-- Crude Navbar for page navigation, needs CSS applied -->
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<nav class="navbar navbar-expand-lg navbar-light bg-faded">
									<ul class="navbar-nav mr-auto">
										<li class="nav-item"><c:forEach var="row"
												items="${div1.rows}">
												<a class="nav-link" href="division.jsp?id=${row.divisionID}">
													<c:out value="${row.divsionName}" />
												</a>
											</c:forEach></li>
										<li class="nav-item"><c:forEach var="row"
												items="${div1.rows}">
												<a class="nav-link" href="standings?id=${row.divisionID}">
													Standings </a>
											</c:forEach></li>
										<li class="nav-item"><c:forEach var="row"
												items="${div1.rows}">
												<a class="nav-link" href="schedule?id=${row.divisionID}">
													Schedule </a>
											</c:forEach></li>
										<li class="nav-item active"><c:forEach var="row"
												items="${div1.rows}">
												<a class="nav-link" href="results?id=${row.divisionID}">
													Results </a>
											</c:forEach></li>
										<li class="nav-item"><c:forEach var="row"
												items="${div1.rows}">
												<a class="nav-link" href="statistics?id=${row.divisionID}">
													Statistics </a>
											</c:forEach></li>
									</ul>
								</nav>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head3" />
							</h4>
							<div class="card-body">
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
										<!-- TODO: No Results message -->
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
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<!-- /.row -->
			</div>

		</div>
	</fmt:bundle>
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