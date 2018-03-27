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
						<c:when test="${empty player}">
							<fmt:message key="player" />
						</c:when>
						<c:otherwise>
							<c:forEach items="${player}" var="p">
								<c:out value="${p.playerFirstName}" />
								<c:out value="${p.playerLastName}" />
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
									<c:when test="${empty player}">
										<fmt:message key="player_no_info" />
									</c:when>
									<c:otherwise>
										<c:forEach items="${player}" var="p">
											<c:if test="${not empty p.playerPhoto }">
												<center>
													<img src="${p.playerPhoto}"
														style="width: 100%; max-width: 500px; height: auto" />
												</center>
											</c:if>
											<table>
												<tr>
													<td><b><fmt:message key="player_current_team" /></b></td>
													<c:choose>
														<c:when test="${empty p.teamName}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${p.teamName}" /></td>

														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="player_division" /></b></td>
													<c:choose>
														<c:when test="${empty p.divisionName}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${p.divisionName}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="player_number" /></b></td>
													<c:choose>
														<c:when test="${empty p.playerNumber}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td>#<c:out value="${p.playerNumber}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="player_position" /></b></td>
													<c:choose>
														<c:when test="${empty p.playerPosition}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${p.playerPosition}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="player_country" /></b></td>
													<c:choose>
														<c:when test="${empty p.playerCountry}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${p.playerCountry}" /></td>

														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="player_height" /></b></td>
													<c:choose>
														<c:when test="${p.playerHeight eq 0.0}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><fmt:formatNumber value="${p.playerHeight}"
																	groupingUsed="true" /> cm</td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="player_weight" /></b></td>
													<c:choose>
														<c:when test="${p.playerWeight eq 0.0}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><fmt:formatNumber value="${p.playerWeight}"
																	groupingUsed="true" /> kg</td>
														</c:otherwise>
													</c:choose>
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
							<div class="card-body table-responsive">
								<table id="standings"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text5" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text6" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text7" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty statistics}">
												<td colspan=6 style="text-align: center"><b><fmt:message
															key="div_noplayers" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${statistics}" var="stats">
													<tr>
														<td><a href="team?id=${stats.teamID}">${stats.teamName}</a></td>
														<td><c:out value="${stats.name}" /></td>
														<td style="text-align: center"><c:out
																value="${stats.gamesPlayed}" /></td>
														<td style="text-align: center"><c:out
																value="${stats.goals}" /></td>
														<td style="text-align: center"><c:out
																value="${stats.yellowCard}" /></td>
														<td style="text-align: center"><c:out
																value="${stats.redCard}" /></td>
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

</fmt:bundle>
</html>
