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
<!-- DataTables core CSS -->
<link rel="stylesheet" type="text/css"
	href="DataTables/datatables.min.css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<script defer
	src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
<fmt:bundle basename="TestBundle">
	<title>Last Ever - <fmt:message key="admin_title_user" /></title>
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
									href="adminUsers"><fmt:message key="nav_admin_users" /></a> <a
									class="dropdown-item" href="adminTeams"><fmt:message
										key="nav_admin_teams" /></a> <a class="dropdown-item"
									href="adminDivisions"><fmt:message key="nav_admin_divs" /></a>
								<a class="dropdown-item" href="adminSchedule"><fmt:message
										key="nav_admin_sched" /></a> <a class="dropdown-item"
									href="adminEmails"><fmt:message key="nav_admin_email" /></a> <a
									class="dropdown-item" href="adminLeagues"><fmt:message
										key="nav_leagues" /></a> <a class="dropdown-item" href="logout"><fmt:message
										key="team_dd4" /></a>
							</div></li>
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
		<div class="cards-container container-fluid">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<c:out value="${userName}" />
					<fmt:message key="admin_title_user" />
				</h1>
				<a href="./adminCreate" class="btn btn-success"><fmt:message
						key="au_create" /></a>
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<div class="card-header">
								<nav>
									<div class="nav nav-tabs" id="nav-tab" role="tablist">
										<a class="nav-item nav-link active" id="user-tab"
											data-toggle="tab" href="#user" role="tab"
											aria-controls="user" aria-selected="true">Users</a> <a
											class="nav-item nav-link" id="unactivated-users-tab"
											data-toggle="tab" href="#unactivated-users" role="tab"
											aria-controls="unactivated-users" aria-selected="false"><span
											class="badge badge-info"><c:out value="${numUsers}" />
												</span> Unactivated Users</a>
									</div>
								</nav>
							</div>
							<div class="card-body table-responsive">
								<div class="tab-content" id="nav-tabContent">
									<div class="tab-pane fade show active" id="user"
										role="tabpanel" aria-labelledby="user">
										<table class="table table-striped" id="users">
											<thead class="thead-dark">
												<tr>
													<th scope="col">ID</th>
													<th scope="col"><fmt:message key="au_username" /></th>
													<th scope="col"><fmt:message key="au_firtname" /></th>
													<th scope="col"><fmt:message key="au_lastname" /></th>
													<th scope="col"><fmt:message key="au_usertype" /></th>
													<th scope="col"><fmt:message key="au_email" /></th>
													<th scope="col"><fmt:message key="au_email_val" /></th>
													<th scope="col"><fmt:message key="au_account_created" /></th>
													<th scope="col"><fmt:message key="au_last_up" /></th>
													<th scope="col"><fmt:message key="au_last_log" /></th>
													<th scope="col"><fmt:message key="au_edit" /></th>
												</tr>
											</thead>
											<c:forEach items="${userList}" var="user">
												<tr>
													<td scope="col">${user.id}</td>
													<td scope="col">${user.username}</td>
													<td scope="col">${user.firstName}</td>
													<td scope="col">${user.lastName}</td>
													<td scope="col">${user.userType}</td>
													<td scope="col">${user.emailAddress}</td>
													<td scope="col">${user.emailValidated}</td>
													<td scope="col">${user.accountCreated.toString()}</td>
													<td scope="col">${user.accountUpdated}</td>
													<td scope="col">${user.lastLogin.toString()}</td>
													<td scope="col"><a href="./editUser?=${user.id}"
														class="btn btn-dark btn-sm"> <i class="fa fa-edit"></i>
													</a></td>
												</tr>
											</c:forEach>
										</table>
									</div>
									<div class="tab-pane fade" id="unactivated-users"
										role="tabpanel" aria-labelledby="unactivated-users-tab">
										<table class="table table-striped" id="unactivated-users-table">
											<thead class="thead-dark">
												<tr>
												<th scope="col">ID</th>
													<th scope="col"><fmt:message key="au_username" /></th>
													<th scope="col"><fmt:message key="au_firtname" /></th>
													<th scope="col"><fmt:message key="au_lastname" /></th>
													<th scope="col"><fmt:message key="au_usertype" /></th>
													<th scope="col"><fmt:message key="au_email" /></th>
													<th scope="col"><fmt:message key="au_account_created" /></th>
													<th scope="col">Activate</th>
												</tr>
											</thead>
											<c:forEach items="${unactivatedUser}" var="user">
												<tr>
													<td scope="col">${user.id}</td>
													<td scope="col">${user.username}</td>
													<td scope="col">${user.firstName}</td>
													<td scope="col">${user.lastName}</td>
													<td scope="col">${user.userType}</td>
													<td scope="col">${user.emailAddress}</td>
													<td scope="col">${user.accountCreated.toString()}</td>
													<td scope="col"><a href="activateUser?id=${user.id}"
														class="btn btn-success btn-sm"> Activate </a></td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</fmt:bundle>
			<!-- /row -->
		</div>
	</div>


	<fmt:bundle basename="TestBundle">
		<!-- Footer -->
		<footer class="page-footer py-3 bg-dark">
			<div class="container-fluid">
				<p class="m-0 text-center text-white">
					<fmt:message key="footer_copyright" />
					&copy; <img src="images/logo_sm4.png" /> 2018
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
	<!-- DataTables core JavaScript -->
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>
	<script type="text/javascript" src="js/users.js"></script>
</body>
</html>