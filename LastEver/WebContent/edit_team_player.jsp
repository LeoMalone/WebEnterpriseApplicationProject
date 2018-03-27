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
	<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-labelledby="deleteUserLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="deleteUserLabel">Delete: ${player.playerFirstName} ${player.playerLastName}</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	Are you sure you want to delete this Player: ${player.playerFirstName} ${player.playerLastName}?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <form action="deleteTeamPlayer?=${player.id}" method="post">	
	        	<button type="submit" class="btn btn-danger">Delete Player</button>
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
					Edit Player: ${player.playerFirstName} ${player.playerLastName}
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								Edit Player Credentials
							</h4>
							<div class="card-body">
								<p class="card-text">
									<form action="editTeamPlayer?=${player.id}" method="POST">
										<div class="form-group">
											<label for="editFirstName"><fmt:message key="team_view_roster2" /></label>
											<input type="text" class="form-control" name="editPlayerFirstName" value="${player.playerFirstName}">
										</div>
										<div class="form-group">
											<label for="editLastName"><fmt:message key="team_view_roster3" /></label>
											<input type="text" class="form-control" name="editPlayerLastName" value="${player.playerLastName}">
										</div>
										<div class="form-group">
											<label for="newUsername">#</label>
											<input type="text" class="form-control" name="editPlayerNumber" value="${player.playerNumber}">
										</div>
										 <div class="form-group">
										    <label for="newEmail"><fmt:message key="team_view_roster4" /></label>
										    <input type="text" class="form-control" name="editPlayerPosition" value="${player.playerPosition}">
										 </div>
										 
										<br />										
										<button type="submit" class="btn btn-outline-success">Save</button>	
									</form>																
							</div>
							<div class="card-footer">
								<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteUser">Delete Player</button>
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
