<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<fmt:setLocale value="${param.language}" />
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
	<title>Last Ever - <fmt:message key="div1" /></title>
</fmt:bundle>
</head>

<body>

	<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/lastever" user="admin"
		password="lastever" />
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
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>


				<%--kevin read
            updating menu bar - feb 10
            --%>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownPortfolio" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> League </a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdownPortfolio">

						<a class="dropdown-item" href="about.jsp">About</a> <a
							class="dropdown-item" href="rules.jsp">Rules</a> <a
							class="dropdown-item" href="registration.jsp">Registration</a> <a
							class="dropdown-item" href="contact.jsp">Contact</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownPortfolio" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Divisions </a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdownPortfolio">
						<a class="dropdown-item" href="division1.jsp">Division 1</a> <a
							class="dropdown-item" href="division2.jsp">Division 2</a> <a
							class="dropdown-item" href="division3.jsp">Division 3</a>
					</div></li>



				<li class="nav-item"><a class="nav-link" href="login.jsp">Sign
						In</a></li>
				<li class="nav-item"><a class="nav-link" href=""></a></li>


				<li class="nav-item"><fmt:bundle basename="TestBundle">
						<form action="" method="post">
							<select name="language" onchange="this.form.submit()">
								<option value="en" ${param.language == 'en' ? 'selected' : ''}><fmt:message
										key="english" /></option>
								<option value="fr" ${param.language == 'fr' ? 'selected' : ''}><fmt:message
										key="french" /></option>
							</select>
						</form>
					</fmt:bundle></li>
			</ul>

		</div>


	</div>

	</nav>


	<fmt:bundle basename="TestBundle">
		<div class="main-cover">
			<!-- Page Content -->
			<div class="cards-container container">
				<h1 class="my-4">
					<fmt:message key="div_header1" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">

					<div class="col-lg-4 mb-4">
						<div class="card h-100">

							<h4 class="card-header">
								<fmt:message key="div_head1" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<sql:query dataSource="${dataSource}" var="result">
					select t.teamName, t.teamAbbreviation from team t inner join teamxdivision td on td.teamID = t.teamID where td.divisionID = 1
					</sql:query>
								<table width="100%">
									<tr>
										<th>Team Name</th>
										<th>Team Abbreviation</th>
									</tr>
									<c:forEach var="row" items="${result.rows}">
										<tr>
											<td><c:out value="${row.teamName}" /></td>
											<td><c:out value="${row.teamAbbreviation}" /></td>
										</tr>
									</c:forEach>
								</table>
								</p>

							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="div_head2" />
							</h4>
							<div class="card-body" width="80%">
								<p class="card-text">

									<sql:query dataSource="${dataSource}" var="result">
					select s.gameDate, s.gameTime, h.teamName, concat(a.teamName, '') as away from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on td.teamID = h.teamID where td.divisionID = 1 and s.gameStatus = 'Scheduled'
					</sql:query>
								<table width="100%">
									<tr>
										<th>Date</th>
										<th>Time</th>
										<th>Home Team</th>
										<th>Away Team</th>
									</tr>
									<c:forEach var="row" items="${result.rows}">
										<tr>
											<td><c:out value="${row.gameDate}" /></td>
											<td><c:out value="${row.gameTime}" /></td>
											<td><c:out value="${row.teamName}" /></td>
											<td><c:out value="${row.away}" /></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="div_head3" />
							</h4>
							<div class="card-body">
								<p class="card-text"></p>

								<sql:query dataSource="${dataSource}" var="result">
					select s.gameDate, h.teamName, s.homeScore, concat(a.teamName, '') as away, s.awayScore, s.gameStatus from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on td.teamID = h.teamID where td.divisionID = 1 and s.gameStatus = 'Final'
					</sql:query>
								<table width="100%">
									<tr>
										<th>Date</th>
										<th>Home Team</th>
										<th>Score</th>
										<th>Away Team</th>
										<th>Score</th>
										<th></th>
									</tr>
									<c:forEach var="row" items="${result.rows}">
										<tr>
											<td><c:out value="${row.gameDate}" /></td>
											<td><c:out value="${row.teamName}" /></td>
											<td><c:out value="${row.homescore}" /></td>
											<td><c:out value="${row.away}" /></td>
											<td><c:out value="${row.awayScore}" /></td>
											<td><c:out value="${row.gameStatus}" /></td>
										</tr>
									</c:forEach>
								</table>

							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="div_head4" />
							</h4>
							<div class="card-body">
								<p class="card-text"></p>

								<sql:query dataSource="${dataSource}" var="result">
					select team, GP, W, D, L, PTS, GF, GA, GD from results where divisionID = 1 order by PTS desc, W desc, L asc, GD desc
					</sql:query>
								<table width="100%">
									<tr>
										<th>Team Name</th>
										<th>GP</th>
										<th>W</th>
										<th>L</th>
										<th>D</th>
										<th>PTS</th>
										<th>GF</th>
										<th>GA</th>
										<th>GD</th>
									</tr>
									<c:forEach var="row" items="${result.rows}">
										<tr>
											<td><c:out value="${row.team}" /></td>
											<td><c:out value="${row.GP}" /></td>
											<td><c:out value="${row.W}" /></td>
											<td><c:out value="${row.L}" /></td>
											<td><c:out value="${row.D}" /></td>
											<td><c:out value="${row.PTS}" /></td>
											<td><c:out value="${row.GF}" /></td>
											<td><c:out value="${row.GA}" /></td>
											<td><c:out value="${row.GD}" /></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="div_head5" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<table>
									<sql:query dataSource="${dataSource}" var="result">
					select teamName, playerName, goals from statistics where divisionID = 1 order by goals desc
					</sql:query>
									<table width="100%">
										<tr>
											<th>Team Name</th>
											<th>Player Name</th>
											<th>Goals</th>
										</tr>
										<c:forEach var="row" items="${result.rows}">
											<tr>
												<td><c:out value="${row.teamName}" /></td>
												<td><c:out value="${row.playerName}" /></td>
												<td><c:out value="${row.Goals}" /></td>
											</tr>
										</c:forEach>
									</table>
									</p>
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