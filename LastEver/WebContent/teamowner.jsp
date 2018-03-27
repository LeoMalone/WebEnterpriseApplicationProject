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
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					${teamName}:<br /> 
					<fmt:message key="signin_prop2"/> <fmt:message key="team_signedin_home1"/>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="admin-cards">
					<div class="row">
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="team_signedin_home2"/>
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="team_signedin_home3"/>
									</p>
								</div>
								 <div class="card-footer bg-transparent">
								 	<a href="./teamRoster" class="btn btn-outline-light"><fmt:message key="team_signedin_home4"/></a>
								</div>
							</div>
						</div>
						<!-- 
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									Team Photos
								</h4>
								<div class="card-body">
									<p class="card-text">
										Add Team Photo and Team Logo
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./teamPhotos" class="btn btn-outline-light">Go To Photos</a>
								</div>
							</div>
						</div>
						 -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									<fmt:message key="team_signedin_home5"/>
								</h4>
								<div class="card-body">
									<p class="card-text">
										<fmt:message key="team_signedin_home6"/>
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./teamSchedule" class="btn btn-outline-light"><fmt:message key="team_signedin_home7"/></a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div>
					<form action="logout" method="post">
						<button type="submit" class="btn btn-danger">
							<fmt:message key="logged_in_signout" />
						</button>
					</form>
				</div>
				<!-- /row -->
			</fmt:bundle>
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