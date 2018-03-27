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
				<h1 class="my-4">${userName}: Divisions</h1>
				<a href="./divisionCreate" class="btn btn-success"><fmt:message	key="ad_create" /></a>
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card bg-light">
							<div class="card-body">
								<table class="table table-striped">
									<thead class="thead-dark">
										<tr>
											<th scope="col">Id</th>
											<th scope="col"><fmt:message key="ad_name" /></th>
											<th scope="col"><fmt:message key="at_edit" /></th>
										</tr>
									</thead>
									<c:forEach items="${allDiv}" var="division">
										<tr>
											<td scope="col">${division.divisionId}</td>
											<td scope="col">${division.divisionName}</td>
											<td scope="col"><a
												href="./editDivision?=${division.divisionId}"
												class="btn btn-dark btn-sm"> <i class="fa fa-edit"></i>
											</a></td>
										</tr>
									</c:forEach>
								</table>
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

</fmt:bundle>
</html>
