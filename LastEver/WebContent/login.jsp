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
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<fmt:message key="signin_header" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="signin_head1" />
							</h4>
							<form action="login" method="POST">
							<div class="card-body">
								 <div class="form-group">
								    <label for="loginEmail"><fmt:message key="signin_email" /></label>
								    <input type="email" class="form-control" name="loginEmail" aria-describedby="emailHelp" placeholder="<fmt:message key='signin_enter_email' />">
								 </div>
								 <div class="form-group">
									<label for="loginPass"><fmt:message key="signin_password" /></label>
									<input type="password" class="form-control" name="loginPass" placeholder="<fmt:message key='signin_enter_password' />">
								 </div>						        										
							</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-secondary"><fmt:message key="signin_button1"/></button>	
								</div>
							</form>
						</div>
					</div>
				</div>				
				<!-- /.row -->
				
				<h1 class="my-4">
					<fmt:message key="signin_register" />
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="signin_createnew" />
							</h4>
							<form action="createAccount" method="POST">
							<div class="card-body">
								<div class="form-group">
									<label for="newFirstName"><fmt:message key="signin_fname" /></label>
									<input type="text" class="form-control" name="newFirstName" placeholder="<fmt:message key='signin_enter_fname' />">
								</div>
								<div class="form-group">
									<label for="newLastName"><fmt:message key="signin_lname" /></label>
									<input type="text" class="form-control" name="newLastName" placeholder="<fmt:message key='signin_enter_lname' />">
								</div>
								<div class="form-group">
									<label for="newUsername"><fmt:message key="signin_user" /></label>
									<input type="text" class="form-control" name="newUsername" placeholder="<fmt:message key='signin_enter_user' />">
								</div>
								 <div class="form-group">
								    <label for="newEmail"><fmt:message key="signin_email" /></label>
								    <input type="email" class="form-control" name="newEmail" aria-describedby="emailHelp" placeholder="<fmt:message key='signin_enter_email' />">
								 </div>
								 <div class="form-group">
									<label for="newPass"><fmt:message key="signin_password" /></label>
									<input type="password" class="form-control" name="newPass" placeholder="<fmt:message key='signin_enter_password' />">
								 </div>	
								 <div class="form-check">
								  <input aria-describedby="adminHelp" class="form-check-input" type="radio" name="createRadio" value="Administrator">
								  <label class="form-check-label" for="createRadio">
								    <fmt:message key="signin_prop1" />
								  </label>									  
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="radio" name="createRadio" value="Team Owner">
								  <label class="form-check-label" for="createRadio">
								    <fmt:message key="signin_prop2" />
								  </label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="radio" name="createRadio" value="Referee">
								  <label class="form-check-label" for="createRadio">
								    <fmt:message key="signin_prop3" />
								  </label>
								  <small id="emailHelp" class="form-text text-muted"><fmt:message key="sign_in_verify"/></small>
								</div>					        										
							</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-secondary"><fmt:message key="signin_button1"/></button>	
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- /.row -->
				
			</fmt:bundle>			
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