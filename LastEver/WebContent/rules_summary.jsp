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

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - <fmt:message key="rules" /></title>
</fmt:bundle>
</head>

<body>

	<!-- nav bar - home, league(about, rules, register, contact us), divisions (womens, mens), sign in 
	- sets parent link active
	- in dropdown, sets active with full bar color
	-->

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
				<ul class="navbar-nav ml-auto">
					<fmt:bundle basename="TestBundle">
						<li class="nav-item"><a class="nav-link" href="index.jsp"><fmt:message
									key="nav_home" /></a></li>

						<li class="nav-item dropdown"><a
							class="nav-link active dropdown-toggle" href="#"
							id="navbarDropdownPortfolio" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <fmt:message
									key="nav_league" />
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownPortfolio">

								<a class="dropdown-item" href="about.jsp"><fmt:message
										key="about" /></a> <a class="dropdown-item active"
									href="rules.jsp"><fmt:message key="rules" /></a> <a
									class="dropdown-item" href="registration.jsp"><fmt:message
										key="registration" /></a> <a class="dropdown-item"
									href="contact.jsp"><fmt:message key="contact" /></a>
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
									<option value="en" ${cookie.language.value == "en" ? 'selected' : ''}><fmt:message
											key="english" /></option>
									<option value="fr" ${cookie.language.value == "fr" ? 'selected' : ''}><fmt:message
											key="french" /></option>
								</select>
							</form>
					</fmt:bundle>
					</li>


				</ul>
			</div>
		</div>
	</nav>
	<div class="main-cover">
		<!-- Page Content
		- card with information on it
		- text
		-->

		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<fmt:message key="rules_summary_header" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="rules_summary_text1" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<ul class="list-group list-group-flush">
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text2" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text3" />
								  </li>
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text4" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text5" />
								  </li>
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text6" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text7" />
								  </li>
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text8" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text8" />
								  </li>
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text9" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text10" />
								  </li>
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text11" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text12" />
								  </li>
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text13" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text14" />
								  </li>
								  <li class="list-group-item list-group-item-secondary">
								  	<fmt:message key="rules_summary_text15" />
								  </li>
								  <li class="list-group-item">
								  	<fmt:message key="rules_summary_text16" />
								  </li>
								</ul>			
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