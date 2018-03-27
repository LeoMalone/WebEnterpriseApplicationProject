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
						<c:when test="${empty team}">
							<fmt:message key="team_head" />
						</c:when>
						<c:otherwise>
							<c:forEach items="${team}" var="t">
								<c:out value="${t.teamName}" />
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body">
								<c:choose>
									<c:when test="${empty team}">
										<fmt:message key="team_no_info" />
									</c:when>
									<c:otherwise>
										<c:forEach items="${team}" var="t">
											<c:if test="${not empty t.teamLogo }">
												<center>
													<img src="${t.teamLogo}"
														style="width: 100%; max-width: 300px; height: auto" />
												</center>
											</c:if>
											<table>
												<tr>
													<td><b><fmt:message key="team_about" /></b></td>
													<c:choose>
														<c:when test="${empty t.teamAbout}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${t.teamAbout}" escapeXml="false" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="team_division" /></b></td>
													<c:choose>
														<c:when test="${empty t.divisionName}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${t.divisionName}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td><b><fmt:message key="team_abbreviation" /></b></td>
													<c:choose>
														<c:when test="${empty t.teamAbbreviation}">
															<td><fmt:message key="not_available" /></td>
														</c:when>
														<c:otherwise>
															<td><c:out value="${t.teamAbbreviation}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<c:choose>
													<c:when test="${empty teamowner}">
														<tr>
															<td><b><fmt:message key="team_owner" /></b></td>
															<td><fmt:message key="not_available" /></td>
														</tr>
														<tr>
															<td><b><fmt:message key="team_contact" /></b></td>
															<td><fmt:message key="not_available" /></td>
														</tr>
													</c:when>
													<c:otherwise>
														<c:forEach items="${teamowner}" var="to">
															<tr>
																<td><b><fmt:message key="team_owner" /></b></td>
																<td><c:out value="${to.firstName}" /> <c:out
																		value="${to.lastName}" /></td>
															</tr>
															<tr>
																<td><b><fmt:message key="team_contact" /></b></td>
																<td><a
																	href="mailto:<c:out value="${to.emailAddress}" />">
																		<fmt:message key="team_contact_owner" />
																</a></td>
															</tr>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</table>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body table-responsive">
								<h4 class="card-header">
									<fmt:message key="div_head4" />
								</h4>
								<table id="standings"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text5" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text6" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text7" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text8" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text9" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head4_text10" /></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty standings}">
												<td colspan=10 style="text-align: center"><b><fmt:message
															key="div_noteams" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${standings}" var="stand">
													<c:forEach items="${team}" var="t">
														<c:choose>
															<c:when test="${stand.teamName eq t.teamName}">
																<tr>
																	<td class="bg-primary" scope="row"
																		style="text-align: center"><c:out
																			value="${stand.rank}" /></td>
																	<td class="bg-primary" scope="row"><c:out
																			value="${stand.teamName}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.gamesPlayed}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.wins}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.losses}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.draws}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.points}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.goalsFor}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.goalsAgainst}" /></td>
																	<td class="bg-primary" style="text-align: center"><c:out
																			value="${stand.goalDiff}" /></td>
																</tr>
															</c:when>
															<c:otherwise>
																<tr>
																	<td scope="row" style="text-align: center"><c:out
																			value="${stand.rank}" /></td>
																	<td scope="row"><a href="team?id=${stand.teamID}">
																			${stand.teamName}</a></td>
																	<td style="text-align: center"><c:out
																			value="${stand.gamesPlayed}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.wins}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.losses}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.draws}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.points}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.goalsFor}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.goalsAgainst}" /></td>
																	<td style="text-align: center"><c:out
																			value="${stand.goalDiff}" /></td>
																</tr>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					-->
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body table-responsive">
								<h4 class="card-header">
									<fmt:message key="div_head2" />
								</h4>
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
											<th scope="col" style="text-align: center"><fmt:message
													key="venue_head1" /></th>
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
																<fmt:formatDate type="date" pattern="yyyy-dd-MM"
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
														<c:choose>
															<c:when test="${empty sched.venue}">
																<td><fmt:message key="div_novenue" /></td>
															</c:when>
															<c:otherwise>
																<td><a href="venue?id=${sched.venueID}">${sched.venue}</a></td>
															</c:otherwise>
														</c:choose>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body table-responsive">
								<h4 class="card-header">
									<fmt:message key="div_head3" />
								</h4>
								<table id="results"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text1" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text2" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text3" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text4" /></th>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head3_text5" /></th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty results}">
												<td colspan=7 style="text-align: center"><b><fmt:message
															key="div_noresults" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${results}" var="res">
													<tr>
														<td scope="row" style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="date" pattern="YYYY-MM-dd"
																	value="${res.date}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="YYYY-MM-dd"
																	value="${res.date}" />
															</c:if></td>
														<td><a href="team?id=${res.homeID}">${res.homeTeam}</a></td>
														<td style="text-align: center"><c:out
																value="${res.homeScore}" /></td>
														<td><a href="team?id=${res.awayID}">${res.awayTeam}</a></td>
														<td style="text-align: center"><c:out
																value="${res.awayScore}" /></td>
														<td style="text-align: center"><c:out
																value="${res.status}" /></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5">
						<div class="card">
							<div class="card-body table-responsive">
								<h4 class="card-header">
									<fmt:message key="div_head5" />
								</h4>
								<table id="statistics"
									class="table table-bordered table-striped table-dark table-hover table-sm">
									<thead>
										<tr>
											<th scope="col" style="text-align: center"><fmt:message
													key="div_head5_text1" /></th>
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
												<td colspan=7 style="text-align: center"><b><fmt:message
															key="div_noplayers" /></b></td>
											</c:when>
											<c:otherwise>
												<c:forEach items="${statistics}" var="stats">
													<tr>
														<td scope="row" style="text-align: center"><c:out
																value="${stats.rank}" /></td>
														<td scope="row"><c:out value="${stats.teamName}" /></td>
														<td><a href="player?id=${stats.playerID}">${stats.name}</a></td>
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

	<!-- DataTables core JavaScript -->
	<script type="text/javascript" src="DataTables/datatables.min.js"></script>
	<script type="text/javascript" src="js/team.js"></script>
</fmt:bundle>
</html>