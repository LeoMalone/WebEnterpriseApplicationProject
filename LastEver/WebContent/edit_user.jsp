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
		<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog"
			aria-labelledby="deleteUserLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="deleteUserLabel">
							<fmt:message key="admin_eu_model_del" />
							: ${user.username}
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<fmt:message key="admin_eu_model_body" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<fmt:message key="admin_eu_model_cls" />
						</button>
						<form action="deleteUser?=${user.id}" method="post">
							<button type="submit" class="btn btn-danger">
								<fmt:message key="admin_eu_del" />
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>


		<div class="main-cover">
			<!-- Page Content
		- card with information on it
		- text, form, button to sign in
		-->
			<div class="cards-container container">
				<h1 class="my-4">
					${userName}:
					<fmt:message key="admin_eu_head" />
					${user.username}
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="admin_eu_title" />
							</h4>
							<div class="card-body">
								<p class="card-text">
								<form action="editUser?=${user.id}" method="POST">
									<div class="form-group">
										<label for="editFirstName"><fmt:message
												key="signin_fname" /></label> <input type="text"
											class="form-control" name="editFirstName"
											value="${user.firstName}">
									</div>
									<div class="form-group">
										<label for="editLastName"><fmt:message
												key="signin_lname" /></label> <input type="text"
											class="form-control" name="editLastName"
											value="${user.lastName}">
									</div>
									<div class="form-group">
										<label for="newUsername"><fmt:message
												key="signin_user" /></label> <input type="text"
											class="form-control" name="editUsername"
											value="${user.username}">
									</div>
									<div class="form-group">
										<label for="newEmail"><fmt:message key="signin_email" /></label>
										<input type="email" class="form-control" name="editEmail"
											aria-describedby="emailHelp" value="${user.emailAddress}">
									</div>
									<div class="form-group">
										<label for="newPass"><fmt:message
												key="signin_password" /></label> <input type="password"
											class="form-control" name="editPass"
											placeholder="<fmt:message key="admin_eu_pass" />">
									</div>
									<div class="form-check">
										<input aria-describedby="adminHelp" class="form-check-input"
											type="radio" name="editRadio" value="Administrator"
											${user.userType=='Administrator'?'checked':''}> <label
											class="form-check-label" for="editRadio"> <fmt:message
												key="signin_prop1" />
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="editRadio"
											value="Team Owner"
											${user.userType=='Team Owner'?'checked':''}> <label
											class="form-check-label" for="editRadio"> <fmt:message
												key="signin_prop2" />
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="editRadio"
											value="Referee" ${user.userType=='Referee'?'checked':''}>
										<label class="form-check-label" for="editRadio"> <fmt:message
												key="signin_prop3" />
										</label>
									</div>
									<br />
									<button type="submit" class="btn btn-outline-success">
										<fmt:message key="admin_eu_save" />
									</button>
								</form>

							</div>
							<div class="card-footer">
								<button type="button" class="btn btn-danger" data-toggle="modal"
									data-target="#deleteUser">
									<fmt:message key="admin_eu_del" />
								</button>
							</div>
						</div>
					</div>
				</div>
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