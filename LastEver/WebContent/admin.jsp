<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<fmt:bundle basename="TestBundle">
	<head>
<!-- if language is not set to French, set language to English -->
<c:if test="${cookie.language.value ne 'fr'}">
	<html lang="en">
<jsp:include page="_header.jsp" /><!-- Check out dat header file -->
</c:if>
<c:if test="${cookie.language.value eq 'fr'}">
	<html lang="fr">
<jsp:include page="_header_fr.jsp" /><!-- Check out dat header file -->
</c:if>
<fmt:setLocale value="${cookie.language.value}" />
	</head>
<body>

		<div class="main-cover">
		<!-- Page Content -->
		<div class="cards-container container">

				<h1 class="my-4">
					${userName}: <fmt:message key="admin_cp" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="admin-cards">
					<div class="row">
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="ah_user_title" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="ah_user_body" />
									</p>
								</div>
								 <div class="card-footer bg-transparent">
								 	<a href="./adminUsers" class="btn btn-outline-light"><fmt:message key="ah_user_goto" /></a>
								</div>
							</div>
						</div>
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="ah_team_title" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="ah_team_body" />
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./adminTeams" class="btn btn-outline-light"><fmt:message key="ah_team_goto" /></a>
								</div>
							</div>
						</div>
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="ah_div_title" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="ah_div_body" />
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./adminDivisions" class="btn btn-outline-light"><fmt:message key="ah_div_goto" /></a>
								</div>
							</div>
						</div>				
					</div>
					<!-- row -->
					
					<div class="row">
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="ah_sched_title" />
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="ah_sched_body" />
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./adminSchedule" class="btn btn-outline-light"><fmt:message key="ah_sched_goto" /></a>
								</div>
							</div>
						</div>				
					</div>
				</div>
				<div class="mb-4">
					<form action="logout" method="post">
						<button type="submit" class="btn btn-danger">
							<fmt:message key="logged_in_signout" />
						</button>
					</form>
				</div>
				<!-- /row -->
		</div>
	</div>
</body>

	<!-- Check out dat footer file - depending on language -->
	<c:if test="${cookie.language.value ne 'fr'}">

		<jsp:include page="_footer.jsp" />
	</c:if>
	<c:if test="${cookie.language.value eq 'fr'}">
		<jsp:include page="_footer_fr.jsp" />
	</c:if>

</fmt:bundle>
</html>