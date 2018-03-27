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
		<fmt:bundle basename="TestBundle">
		<div class="cards-container container">		
				<h1 class="my-4">
					${userName}: <fmt:message key="team_selectCreate" />
				</h1>
				
				<!--  first section - select a team -->
				
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="team_select" />
							</h4>
							<form action="teamOwnerSelect" method="POST" id="selectTeam">
							<div class="card-body">
								<p class="card-text">
											<div class="form-check">
												<select name="selectTeam" form="selectTeam">
													<c:forEach var="team" items="${allTeam}">
														<option value="${team.teamName}">
															${team.teamName}
														</option>
													</c:forEach>
												</select>
											</div>
							</div>
							<div class="card-footer">
								<form action="teamowner" id="selectTeam">
									<input type="submit" value="Submit Your Team" class="btn btn-success">
								</form>	
							</div>
							</form>
						</div>							
					</div>
				</div>
				
				
				<!--  second section - create a new team -->
				
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="team_createTeam" />
							</h4>
							<form action="teamOwnerNew" method="POST">
							<div class="card-body">
								<p class="card-text">
									<div class="form-group">
										<label for="newTeamName"><fmt:message key="team_teamName" /></label>
										<input type="text" class="form-control" name="newTeamName" placeholder="Enter Team Name">
									</div>
									<div class="form-group">
										<label for="newTeamAbbr"><fmt:message key="team_abb" /></label>
										<input type="text" class="form-control" name="newTeamAbbr" placeholder="Enter Team Abbreviation">
									</div>	
									<div class="form-group">
										<label for="aboutTeam"><fmt:message key="team_aboutTeam" /></label>
										<input type="text" class="form-control" name="aboutTeam" placeholder="Enter Team Information">
									</div>
									<label for="divRadio"><fmt:message key="team_selectDivision" /></label>								 
									 <c:forEach var="div1" items="${allDiv}">
											<div class="form-check">
											  <input aria-describedby="adminHelp" class="form-check-input" type="radio" name="divRadio" value="${div1.divisionId}">
												  <label class="form-check-label" for="divRadio">
												    ${div1.divisionName}
												  </label>
											  </div>
									</c:forEach>
								
							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-success"><fmt:message key="team_submit" /></button>	
							</div>
							</form>
						</div>							
					</div>
				</div>
				
			</div>
		<!-- /.row -->
		</fmt:bundle>
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