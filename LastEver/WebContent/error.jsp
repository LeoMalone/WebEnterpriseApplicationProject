<%@ page isErrorPage="true"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<fmt:bundle basename="TestBundle">
	<head>
	<title>....... Last Ever - ${errorcode} .......</title>
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
			<!-- Page Content
		- cards with information on them
		- teams, schedules, results, standings, leaders
		- calls from database 'LastEver' to get all information
		-->
			<div class="cards-container container">
				<h1 class="my-4">${errorcode} <fmt:message key="error"/></h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">${error}</div>
						</div>
					</div>

				</div>
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