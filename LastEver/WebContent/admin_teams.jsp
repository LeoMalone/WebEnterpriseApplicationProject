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
					${userName}:
					<fmt:message key="at_title" />
				</h1>
				<a href="./teamCreate" class="btn btn-success"><fmt:message
						key="at_create" /></a>
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card bg-light">
							<div class="card-header">
								<ul class="nav nav-tabs card-header-tabs">
									<li class="nav-item"><a
										class="nav-link ${currentId==null?'active':''}"
										href="./adminTeams"><fmt:message key="at_nodiv" /></a></li>
									<c:forEach items="${divList}" var="division">
										<li class="nav-item"><a
											class="nav-link ${division.divisionId==currentId?'active':''}"
											href="./adminTeams?=${division.divisionId}">${division.divisionName}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="card-body">
								<table class="table table-striped">
									<thead class="thead-dark">
										<tr>
											<th scope="col">Id</th>
											<th scope="col"><fmt:message key="at_name" /></th>
											<th scope="col"><fmt:message key="at_abbr" /></th>
											<th scope="col"><fmt:message key="at_edit" /></th>
										</tr>
									</thead>
									<c:forEach items="${teamList}" var="team">
										<tr>
											<td scope="col">${team.teamId}</td>
											<td scope="col">${team.teamName}</td>
											<td scope="col">${team.teamAbbreviation}</td>
											<td scope="col"><a href="./editTeam?=${team.teamId}"
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