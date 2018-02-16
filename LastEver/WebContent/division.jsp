<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<!-- ----------------------------------------------------------------------------- -->
<!-- ------------------------------  COOKIE LOGIC  ------------------------------- -->
<!-- ----------------------------------------------------------------------------- -->
<%
	String userName = null;
	String sessionID = null;
	
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("username")) userName = cookie.getValue();
		}
	}		
%>

<!-- if language is not set to French, set language to English -->
<!-- cookie - future development -->
<c:if test="${cookie.language eq null}">
	<%
		Cookie cookieLanguage = new Cookie("language", "en");
			cookieLanguage.setMaxAge(60 * 60 * 60 * 30);
			response.addCookie(cookieLanguage);
	%>
</c:if>
<c:if test="${cookie.language ne null}">
	<%
		String language = request.getParameter("language");
			Cookie cookieLanguage;
			Cookie[] theCookies = request.getCookies();

			for (Cookie tempCookie : theCookies) {
				if ("language".equals(tempCookie.getName())) {
					if (language != null)
						tempCookie.setValue(language);
					response.addCookie(tempCookie);
					break;
				}
			}
	%>

</c:if>

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

<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/lastever" user="admin"
	password="lastever" />

<sql:query dataSource="${dataSource}" var="div1">
select divsionName from division where divisionID = ?
<sql:param value="${param.id}" />
</sql:query>

<%
	int rank = 1;
	pageContext.setAttribute("rank", rank);
%>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<c:choose>
		<c:when test="${div1.rowCount == 0}">

			<title>Last Ever - Division</title>
		</c:when>
		<c:otherwise>
			<title>Last Ever - <c:forEach var="row" items="${div1.rows}">
					<c:out value="${row.divsionName}" />
				</c:forEach></title>
		</c:otherwise>
	</c:choose>
	<title>Last Ever - <c:forEach var="row" items="${div1.rows}">
			<c:out value="${row.divsionName}" />
		</c:forEach></title>
</fmt:bundle>
</head>

<body>

	<!-- nav bar - home, league(about, rules, register, contact us), divisions (womens, mens), sign in 
	- sets parent link active
	- in dropdown, sets active with full bar color
	-->
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
							</div>
						</li>
						<% if (session.getAttribute("signedIn") != null) {%>
						    <li class="nav-item"><a class="nav-link" href="<%=session.getAttribute("userType")%>"><%=userName %></a></li>
						<% } else {%>
						   <li class="nav-item"><a class="nav-link" href="login.jsp"><fmt:message key="nav_signin" /></a></li>
						<% } %>
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
					<c:choose>
						<c:when test="${div1.rowCount == 0}">
						Division
						</c:when>
						<c:otherwise>
							<c:forEach var="row" items="${div1.rows}">
								<c:out value="${row.divsionName}" />
							</c:forEach>
						</c:otherwise>
					</c:choose>

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
								<table width="100%"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head1_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head1_text2" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="2" style="text-align: center"><b><fmt:message
															key="div_noteams" /></b></td>
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
											<c:when test="${result.rowCount == 0}">
												<td colspan="4" style="text-align: center"><b><fmt:message
															key="div_nogames" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row" style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="date" pattern="d MMM y"
																	value="${row.gameDate}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="MMM d y"
																	value="${row.gameDate}" />
															</c:if></td>
														<td style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="time" pattern="H:mm"
																	value="${row.gameTime}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="time" pattern="h:mm a"
																	value="${row.gameTime}" />
															</c:if></td>
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
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="6" style="text-align: center"><b><fmt:message
															key="div_noresults" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row" style="text-align: center"><c:if
																test="${param.language eq 'fr'}">
																<fmt:formatDate type="date" pattern="d MMM y"
																	value="${row.gameDate}" />
															</c:if> <c:if test="${param.language ne 'fr'}">
																<fmt:formatDate type="date" pattern="MMM d y"
																	value="${row.gameDate}" />
															</c:if></td>
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
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text5" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text6" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text7" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text8" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text9" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text10" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${result.rowCount == 0}">
												<td colspan="10" style="text-align: center"><b><fmt:message
															key="div_noteams" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row" style="text-align: center">
															<% pageContext.setAttribute("rank", rank++); %> ${rank}
														</td>
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
									<% rank = 1;
									pageContext.setAttribute("rank", rank); %>
								
								<table width="100%"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text1" /></th>
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
											<c:when test="${result.rowCount == 0}">
												<td colspan="7" style="text-align: center"><b><fmt:message
															key="no_players" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${result.rows}">
													<tr>
														<td scope="row" style="text-align: center">
															<% pageContext.setAttribute("rank", rank++);%> ${rank}
														</td>
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