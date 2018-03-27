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
		<!-- Page Content
		- cards with information on them
		- text, button with link to jsp
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<fmt:message key="rules_header" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="rules_head1" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<fmt:message key="rules_text1" />

								</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="rules_head2" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<fmt:message key="rules_text2" />
								</p>
							</div>
							<div class="card-footer">
								<a href="./rulesSummary" class="btn btn-secondary"><fmt:message key="rules_text3" /></a>
							</div>
						</div>
					</div>
				</div>
			</fmt:bundle>
			<!-- /.row -->
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
