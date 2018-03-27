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
			<div class="cards-container container">
				<h1 class="my-4">
					${userName}:
					<fmt:message key="admin_ct_head" />
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="admin_ct_title" />
							</h4>
							<form action="teamCreate" method="POST">
								<div class="card-body">
									<div class="form-group">
										<label for="newTeamName"><fmt:message
												key="admin_ct_tn" /></label> <input type="text"
											class="form-control" name="newTeamName"
											placeholder="<fmt:message key="admin_ct_tnplace"/>">
									</div>
									<div class="form-group">
										<label for="newTeamAbbr"><fmt:message
												key="admin_ct_ta" /></label> <input type="text"
											class="form-control" name="newTeamAbbr"
											placeholder="<fmt:message key="admin_ct_taplace"/>">
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
								</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-success">
										<fmt:message key="signin_button1" />
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->
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