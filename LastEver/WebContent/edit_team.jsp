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

		<!-- MODAL -->
		<div class="modal fade" id="deleteTeam" tabindex="-1" role="dialog"
			aria-labelledby="deleteTeamLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="deleteTeamLabel">
							<fmt:message key="admin_et_model_del" />
							: ${team.teamName}
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<fmt:message key="admin_et_model_body" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<fmt:message key="admin_eu_model_cls" />
						</button>
						<form action="deleteTeam?=${team.teamId}" method="POST">
							<button type="submit" class="btn btn-danger">
								<fmt:message key="admin_et_del" />
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="main-cover">
			<!-- Page Content -->
			<div class="cards-container container">
				<h1 class="my-4">${userName}: ${team.teamName}</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="admin_et_title" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<form action="editTeam?=${team.teamId}" method="POST">
									<div class="form-group">
										<label for="editTeamName"><fmt:message
												key="admin_ct_tn" /></label> <input type="text"
											class="form-control" name="editTeamName"
											value="${team.teamName}">
									</div>
									<div class="form-group">
										<label for="editTeamAbbr"><fmt:message
												key="admin_ct_ta" /></label> <input type="text"
											class="form-control" name="editTeamAbbr"
											value="${team.teamAbbreviation}">
									</div>
									<label for="divRadio"><fmt:message key="admin_ct_div" /></label>
									<c:forEach var="div1" items="${allDiv}">
										<div class="form-check">
											<input aria-describedby="adminHelp" class="form-check-input"
												type="radio" name="divRadio" value="${div1.divisionId}">
											<label class="form-check-label" for="divRadio">
												${div1.divisionName} </label>
										</div>
									</c:forEach>
									<br />
									<button type="submit" class="btn btn-outline-success">
										<fmt:message key="admin_eu_save" />
									</button>
								</form>
							</div>
							<div class="card-footer">
								<button type="button" class="btn btn-danger" data-toggle="modal"
									data-target="#deleteTeam">
									<fmt:message key="admin_et_del" />
								</button>
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