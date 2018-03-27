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
		- cards with information on them
		- widgets
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<c:choose>
						<c:when test="${empty venue}">
							<fmt:message key="venue_head1" />
						</c:when>
						<c:otherwise>
							<c:forEach items="${venue}" var="v">
								<c:out value="${v.venueName}" />
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<c:choose>
									<c:when test="${empty venue}">
										<fmt:message key="venue_no_info" />
									</c:when>
									<c:otherwise>
										<c:forEach items="${venue}" var="v">
											<c:if test="${not empty v.venuePicture }">
												<center>
													<img src="${v.venuePicture}"
														style="width: 100%; max-width: 500px; height: auto" />
												</center>
											</c:if>
											<table>
												<tr>
													<td><b><fmt:message key="venue_about" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueAbout}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${v.venueAbout}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_contact" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueContact}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${v.venueContact}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_phone_number" /></b></td>
													<c:choose>
														<c:when test="${empty v.venuePhoneNumber}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><a
																href="tel:+<c:out value="${v.venuePhoneNumber}" />">
																	<c:out value="${v.venuePhoneNumber}" />
															</a></td>
														</c:otherwise>
													</c:choose>
												<tr>
													<td><b><fmt:message key="venue_email" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueEmail}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><a
																href="mailto:<c:out value="${v.venueEmail}" />"> <fmt:message
																		key="venue_email_us" /></a></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_address" /></b></td>
													<c:choose>
														<c:when test="${empty v.venueAddress}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${v.address1}" /> <c:out
																	value="${v.address2}" /> <br> <c:out
																	value="${v.city}" />, <c:out value="${v.province}" />
																<c:out value="${v.country}" /><br> <c:out
																	value="${v.postal}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="venue_map" /></b></td>
													<td><div id="map"></div> <script>
														function initMap() {
															var map = new google.maps.Map(
																	document
																			.getElementById('map'),
																	{
																		zoom : 15,
																		center : {
																			lat : 25,
																			lng : 25
																		}
																	});
															var geocoder = new google.maps.Geocoder();

															geocodeAddress(
																	geocoder,
																	map);
														}

														function geocodeAddress(
																geocoder,
																resultsMap) {
															var address = <c:forEach items="${venue}" var="v">
															"${v.venueAddress}"
															</c:forEach>;

															geocoder
																	.geocode(
																			{
																				'address' : address
																			},
																			function(
																					results,
																					status) {
																				if (status === 'OK') {
																					resultsMap
																							.setCenter(results[0].geometry.location);
																					var marker = new google.maps.Marker(
																							{
																								map : resultsMap,
																								position : results[0].geometry.location
																							});
																				}
																			});
														}
													</script> <script
															src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGUmrbP4bA8jEkouNt9KIRFlBzpyT5oUA&callback=initMap"></script>
													</td>
												</tr>
											</table>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card bg-light">
							<div class="card-header">
								<h4 class="card-header">
									<fmt:message key="div_head2" />
								</h4>
								<ul class="nav nav-tabs card-header-tabs">
									<c:forEach items="${allDiv}" var="division">
										<li class="nav-item"><a
											class="nav-link ${division.divisionId==divID?'active':''}"
											href="venue?id=${venID}&div=${division.divisionId}#schedule">${division.divisionName}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="card-body table-responsive">
								<table id="schedule"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head2_text4" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty schedule}">
												<td colspan=5 style="text-align: center"><b><fmt:message
															key="div_nogames" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${schedule}" var="sched">
													<tr>
														<td scope="row" style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="date" pattern="YYYY-MM-dd"
																	value="${sched.date}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="YYYY-MM-dd"
																	value="${sched.date}" />
															</c:if></td>
														<td style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="time" pattern="H:mm"
																	value="${sched.time}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="time" pattern="h:mm a"
																	value="${sched.time}" />
															</c:if></td>
														<td><a href="team?id=${sched.homeID}">${sched.homeTeam}</a></td>
														<td><a href="team?id=${sched.awayID}">${sched.awayTeam}</a></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</fmt:bundle>
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


	<!-- DataTables core JavaScript -->
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>
	<script type="text/javascript" src="js/venue.js"></script>
</fmt:bundle>
</html>