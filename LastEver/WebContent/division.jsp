<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
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

<sql:query dataSource="${dataSource}" var="div1">
select divsionName from division where divisionID = ?
<sql:param value="${param.id}" />
</sql:query>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - <c:forEach var="row" items="${div1.rows}">
			<c:out value="${row.divsionName}" />
		</c:forEach></title>
</fmt:bundle>
</head>

<body>

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
					aria-haspopup="true" aria-expanded="false"> Divisions </a> <c:choose>
						<c:when test="${result.rowCount == 0}">
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
								<a class="dropdown-item" href="">No Divisions</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">
								<c:forEach var="row" items="${div2.rows}">
									<a class="dropdown-item"
										href="division.jsp?id=${row.divisionID}">${row.divsionName}</a>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose></li>



				<li class="nav-item"><a class="nav-link" href="login.jsp">Sign
						In</a></li>
				<li class="nav-item"><a class="nav-link" href=""></a></li>


				<li class="nav-item"><fmt:bundle basename="TestBundle">
						<form action="" method="post">
							<select class="form-control form-control-sm" name="language"
								onchange="this.form.submit()">
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
					<c:forEach var="row" items="${div1.rows}">
						<c:out value="${row.divsionName}" />
					</c:forEach>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">

					<div class="col-lg-12 mb-5">
						<div class="card">

							<h4 class="card-header">
								<fmt:message key="div_head1" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<sql:query dataSource="${dataSource}" var="result">
					select t.teamName, t.teamAbbreviation from team t inner join teamxdivision td on td.teamID = t.teamID where td.divisionID = ?
					<sql:param value="${param.id}" />
									</sql:query>
								<table width="100%" class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center">Team Name</th>
											<th scope="col" style="text-align: center">Team
												Abbreviation</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="2" style="text-align: center"><b>No Teams</b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row"><c:out value="${row.teamName}" /></td>
														<td><c:out value="${row.teamAbbreviation}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								</p>

							</div>
						</div>
					</div>
					<br>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head2" />
							</h4>
							<div class="card-body">
								<p class="card-text">

									<sql:query dataSource="${dataSource}" var="result">
					select s.gameDate, s.gameTime, h.teamName, concat(a.teamName, '') as away from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on td.teamID = h.teamID where td.divisionID = ? and s.gameStatus = 'Scheduled'
					<sql:param value="${param.id}" />
									</sql:query>
								<table width="100%"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center">Date</th>
											<th scope="col" style="text-align: center">Time</th>
											<th scope="col" style="text-align: center">Home</th>
											<th scope="col" style="text-align: center">Away</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="4" style="text-align: center"><b>No Games Scheduled</b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row" style="text-align: center"><fmt:formatDate type="date"
														pattern="MMM d y" value="${row.gameDate}" /></td>
														<td style="text-align: center"><fmt:formatDate type="time"
														pattern="h:mm a" value="${row.gameTime}" /></td>
														<td><c:out value="${row.teamName}" /></td>
														<td><c:out value="${row.away}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head3" />
							</h4>
							<div class="card-body">
								<p class="card-text"></p>

								<sql:query dataSource="${dataSource}" var="result">
					select s.gameDate, h.teamName, s.homeScore, concat(a.teamName, '') as away, s.awayScore, s.gameStatus from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on td.teamID = h.teamID where td.divisionID = ? and s.gameStatus = 'Final'
					<sql:param value="${param.id}" />
								</sql:query>
								<table width="100%"
									class="ttable table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center">Date</th>
											<th scope="col" style="text-align: center">Home</th>
											<th scope="col" style="text-align: center">Score</th>
											<th scope="col" style="text-align: center">Away</th>
											<th scope="col" style="text-align: center">Score</th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="5" style="text-align: center"><b>No Results</b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row" style="text-align: center"><fmt:formatDate type="date"
														pattern="MMM d y" value="${row.gameDate}" /></td>
														<td><c:out value="${row.teamName}" /></td>
														<td style="text-align: center"><c:out
																value="${row.homescore}" /></td>
														<td><c:out value="${row.away}" /></td>
														<td style="text-align: center"><c:out
																value="${row.awayScore}" /></td>
														<td style="text-align: center"><c:out
																value="${row.gameStatus}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>

							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head4" />
							</h4>
							<div class="card-body">
								<p class="card-text">

									<sql:query dataSource="${dataSource}" var="result">
					select team, GP, W, D, L, PTS, GF, GA, GD from standings where divisionID = ? order by PTS desc, W desc, L asc, GD desc
					<sql:param value="${param.id}" />
									</sql:query>
								<table width="100%"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center">Team Name</th>
											<th scope="col" style="text-align: center">GP</th>
											<th scope="col" style="text-align: center">W</th>
											<th scope="col" style="text-align: center">L</th>
											<th scope="col" style="text-align: center">D</th>
											<th scope="col" style="text-align: center">PTS</th>
											<th scope="col" style="text-align: center">GF</th>
											<th scope="col" style="text-align: center">GA</th>
											<th scope="col" style="text-align: center">GD</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="9" style="text-align: center"><b>No Teams</b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row"><c:out value="${row.team}" /></td>
														<td style="text-align: center"><c:out
																value="${row.GP}" /></td>
														<td style="text-align: center"><c:out
																value="${row.W}" /></td>
														<td style="text-align: center"><c:out
																value="${row.L}" /></td>
														<td style="text-align: center"><c:out
																value="${row.D}" /></td>
														<td style="text-align: center"><c:out
																value="${row.PTS}" /></td>
														<td style="text-align: center"><c:out
																value="${row.GF}" /></td>
														<td style="text-align: center"><c:out
																value="${row.GA}" /></td>
														<td style="text-align: center"><c:out
																value="${row.GD}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								</p>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head5" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<sql:query dataSource="${dataSource}" var="result">
					select teamName, GP, playerName, goals, yellowCards, redCards from statistics where divisionID = ? order by goals desc, GP asc, playerName asc
					<sql:param value="${param.id}" />
									</sql:query>
								<table width="100%"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center">Team Name</th>
											<th scope="col" style="text-align: center">Player Name</th>
											<th scope="col" style="text-align: center">GP</th>
											<th scope="col" style="text-align: center">Goals</th>
											<th scope="col" style="text-align: center">Yellow Cards</th>
											<th scope="col" style="text-align: center">Red Cards</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="6" style="text-align: center"><b>No Players</b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row"><c:out value="${row.teamName}" /></td>
														<td><c:out value="${row.playerName}" /></td>
														<td style="text-align: center"><c:out
																value="${row.GP}" /></td>
														<td style="text-align: center"><c:out
																value="${row.Goals}" /></td>
														<td style="text-align: center"><c:out
																value="${row.yellowCards}" /></td>
														<td style="text-align: center"><c:out
																value="${row.redCards}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
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