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
<div class="modal fade" id="deleteDivision" tabindex="-1"
			role="dialog" aria-labelledby="deleteDivisionLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="deleteDivisionLabel">
							<fmt:message key="admin_eu_model_del" />
							: ${division.divisionName}
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<fmt:message key="admin_ed_modal_body" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<fmt:message key="admin_eu_model_cls" />
						</button>
						<form action="deleteDivision?=${division.divisionId}"
							method="POST">
							<button type="submit" class="btn btn-danger">
								<fmt:message key="admin_ed_del" />
							</button>
						</form>
					</div>
				</div>
			</div>
</div>


		<div class="main-cover">
			<!-- Page Content -->
			<div class="cards-container container">
				<h1 class="my-4">${userName}: ${division.divisionName}</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="admin_ed_title" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<form action="editDivision?=${division.divisionId}"
									method="POST">
									<div class="form-group">
										<label for="editDivisionName"><fmt:message
												key="admin_cd_name" /></label> <input type="text"
											class="form-control" name="editDivisionName"
											value="${division.divisionName}">
									</div>
									<button type="submit" class="btn btn-outline-success">
										<fmt:message key="admin_eu_save" />
									</button>
								</form>
							</div>
							<div class="card-footer">
								<button type="button" class="btn btn-danger" data-toggle="modal"
									data-target="#deleteDivision">
									<fmt:message key="admin_ed_del" />
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