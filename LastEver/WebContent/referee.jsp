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
					${userName}: Your Referee Home Page<br \>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="admin-cards">
					<div class="row">
						<!-- View and edit referee profiles -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									${userName}'s Profile
								</h4>
								<div class="card-body">
									<p class="card-text">
										View and Edit Your Referee Profile <br \>
									</p>
								</div>
								 <div class="card-footer bg-transparent">
								 	<a href="./refUsers?=${id}" class="btn btn-outline-light">Go To Profile</a>
								</div>
							</div>
						</div>
						<!-- Referee Assignments -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-white bg-dark">
								<h4 class="card-header">
									${userName}'s Assignments
								</h4>
								<div class="card-body">
									<p class="card-text">
										View Your Referee Assignments <br \>
									</p>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./adminSchedule" class="btn btn-outline-light">Go To Your Assignments</a>
								</div>
							</div>
						</div>
						<!-- Referee Status  -->
						<div class="col-lg-4 mb-4">
							<div class="card h-100 text-black bg-dark">
								<h4 class="card-header">
									<font color="white"> ${userName}'s Status</font>
								</h4>
								<div class="card-body">
									<ul class="list-group">
									  <li class="list-group-item d-flex justify-content-between align-items-center">
									    Upcoming Games
									    <span class="badge badge-primary badge-pill">14</span>
									  </li>
									  <li class="list-group-item d-flex justify-content-between align-items-center">
									    Pending Games Result(s)
									    <span class="badge badge-primary badge-pill">2</span>
									  </li>
									  <li class="list-group-item d-flex justify-content-between align-items-center">
									    Admin Notification(s)
									    <span class="badge badge-primary badge-pill">1</span>
									  </li>
									</ul>
								</div>
								<div class="card-footer bg-transparent">
								 	<a href="./refAssignments?=${id}" class="btn btn-outline-light">Open Notifications</a>
								</div>
							</div>
						</div>
						<!-- NEW - 4th Card -->
						<div class="col-lg-12 mb-5 mt-5">
							<div class="card h-100 text-white bg-dark">
								<div class="card-body">
									<h4 class="card-header"> Your Recent Games </h4>
								 	<table class="table table-striped table-dark">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">Game ID#</th>
									      <th scope="col">Home Team</th>
									      <th scope="col">Home Team Goal</th>
									      <th scope="col">Away Team</th>
									      <th scope="col">Away Team Goal</th>
									      <th scope="col">Result</th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr>
									      <th scope="row">1</th>
									      <td>123</td>
									      <td>Barcelona</td>
									      <td>2</td>
									      <td>Aesenal</td>
									      <td>1</td>
									      <td>Barcelona</td>
									    </tr>
									    <tr>
									      <th scope="row">2</th>
									      <td>456</td>
									      <td>Real Madrid</td>
									      <td>3</td>
									      <td>MC United</td>
									      <td>2</td>
									      <td>Real Madrid</td>
									    </tr>
									    <tr>
									      <th scope="row">3</th>
									     <td>789</td>
									      <td>PSG</td>
									      <td>1</td>
									      <td>AC Milan</td>
									      <td>1</td>
									      <td>Draw</td>
									    </tr>
									  </tbody>
									</table>
							 </div>							 		
							</div>
						</div>
							
					</div>
				</div>
				<div>
					<form action="logout" method="post">
						<button type="submit" class="btn btn-danger">
							<fmt:message key="logged_in_signout" />
						</button>
					</form>
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
