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
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					${teamName}:<br />
					<fmt:message key="team_view_roster1" />
				</h1>
				
				<a href="./createPlayer?=${teamId}" class="btn btn-success"><fmt:message key="team_view_roster6" /></a>		
		
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">				
							<div class="table-responsive">										
								<table class="table table-striped">
									<thead class="thead-dark">
									    <tr>
									    	<th scope="col">#</th>
									    	<th scope="col"><fmt:message key="team_view_roster2" /></th>
									    	<th scope="col"><fmt:message key="team_view_roster3" /></th>
										    <th scope="col"><fmt:message key="team_view_roster4" /></th>
										    
										    <th scope="col"><fmt:message key="team_view_roster5" /></th>
										    
									    </tr>
									</thead>
								    <c:forEach items="${playerList}" var="player">
								        <tr>
								        	<td scope="col">${player.playerNumber}</td>
								        	<td scope="col">${player.playerFirstName}</td>
								        	<td scope="col">${player.playerLastName}</td>
								        	<td scope="col">${player.playerPosition}</td>
								        	
								        	<td scope="col">
								        		<a href="./editTeamPlayer?=${player.id}" class="btn btn-dark btn-sm">
								        			<i class="fa fa-edit"></i> 
												</a>
											</td>	
											 
											 
											 	            
								        </tr>
								    </c:forEach>
								</table>
							</div>			
						</div>
					</div>
				</div>					
				<!-- /row -->
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