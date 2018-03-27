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
					${userName}: Add a Player To Your Roster
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								Create New Player
							</h4>
							<form action="createPlayer?=${teamId}" method="post">
							<div class="card-body">
								<div class="form-group">
									<label for="newFirstName">First Name</label>
									<input type="text" class="form-control" name="newFirstName" placeholder="Enter First Name">
								</div>
								<div class="form-group">
									<label for="newLastName">Last Name</label>
									<input type="text" class="form-control" name="newLastName" placeholder="Enter Last Name">
								</div>
								<div class="form-group">
									<label for="newUsername">#</label>
									<input type="text" class="form-control" name="newNumber" placeholder="Enter Player Number">
								</div>
								 <div class="form-group">
								    <label for="newEmail">Player Position</label>
								    <input type="text" class="form-control" name="newPosition" placeholder="Enter Player Position">
								 </div>
								
								<div class="card-footer">
									<button type="submit" class="btn btn-success"><fmt:message key="signin_button1"/></button>	
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