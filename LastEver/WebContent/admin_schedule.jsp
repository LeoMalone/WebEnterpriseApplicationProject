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
				<h1 class="my-4">${userName}: <fmt:message key="as_head" /></h1>
				<a href="./scheduleCreate" class="btn btn-success"><fmt:message key="as_add" /></a>
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card bg-light">
							<div class="card-body">
								<div id='calendar'></div>
							</div>
						</div>
					</div>
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

		
		
		<!-- FullCalendar Scripts + Other -->
		<script type="text/javascript" src="fullcalendar/js/jquery.min.js"></script>
		<script type="text/javascript" src="fullcalendar/js/moment.min.js"></script>
		<script type="text/javascript" src="fullcalendar/js/fullcalendar.js"></script>
		<script type="text/javascript" src="js/custom_fullcalendar.js"></script>
		<script defer
			src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
</fmt:bundle>
</html>