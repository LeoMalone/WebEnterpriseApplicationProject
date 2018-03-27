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
