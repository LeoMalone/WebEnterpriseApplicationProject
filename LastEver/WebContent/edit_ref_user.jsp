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
		- text, form, button to sign in
		-->
			<div class="cards-container container">
				<h1 class="my-4">
					${userName}:
					<fmt:message key="signin_prop3" />
					Edit
				</h1>
				<div class="row">
					<!-- Edit referee profile form -->
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="signin_prop3" />
								<fmt:message key="reg_head1" />
							</h4>
							<form action="editRefUser?=${user.id}" method="POST">
								<div class="card-body">
									<p class="card-text">
									<div class="form-group">
										<label for="staticEmail"><fmt:message
												key="signin_user" /></label> <input type="text" readonly
											class="form-control" name="editUsername" value="${userName}">
									</div>
									<div class="form-group">
										<label for="newEmailAddress"><fmt:message
												key="signin_email" /></label> <input type="email"
											class="form-control" name="editEmail"
											aria-describedby="emailHelp" value="${user.emailAddress}">
									</div>
									<div class="form-group">
										<label for="newPass"><fmt:message
												key="signin_password" /></label> <input type="password"
											class="form-control" name="editPass" value="${user.password}">
									</div>
									<!-- Pre-checked radio button for referee-profie-edit  -->
									<div class="form-check">
										<input class="form-check-input" type="radio" name="editRadio"
											value="Referee" ${user.userType=='Referee'?'checked':''}
											checked> <label class="form-check-label"
											for="editRadio"> <fmt:message key="signin_prop3" />
										</label>
									</div>
								</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-outline-success">Save</button>
								</div>
								<div class="card-footer">
									<button type="submit" formaction="deleteUser?=${user.id}"
										class="btn btn-outline-success">Delete User</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- /.row -->

				<form action="deleteUser?=${user.id}" method="post">
					<button type="submit" class="btn btn-danger float-right">Delete
						User</button>
				</form>
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