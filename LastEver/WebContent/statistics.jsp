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
		- teams, schedules, results, standings, leaders
		- calls from database 'LastEver' to get all information
		-->
			<div class="cards-container container">
				<h1 class="my-4">
					<c:forEach var="row" items="${currDiv}">
						<c:out value="${row.divisionName}" />
					</c:forEach>
					<fmt:message key="div_head5" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<!-- Crude Navbar for page navigation, needs CSS applied -->
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<nav class="navbar navbar-expand-lg navbar-light bg-faded">
									<ul class="navbar-nav mr-auto">
										<c:choose>
											<c:when test="${empty currDiv }">
												Division
											</c:when>
											<c:otherwise>
												<c:forEach var="row" items="${currDiv}">
													<li class="nav-item"><a class="nav-link"
														href="division?id=${row.divisionId}"> <c:out
																value="${row.divisionName}" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
														href="standings?id=${row.divisionId}"> <fmt:message
																key="div_head4" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
														href="schedule?id=${row.divisionId}"> <fmt:message
																key="div_head2" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
														href="results?id=${row.divisionId}"> <fmt:message
																key="div_head3" />
													</a></li>
													<li class="nav-item active"><a class="nav-link"
														href="statistics?id=${row.divisionId}"> <fmt:message
																key="div_head5" />
													</a></li>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</ul>
								</nav>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<h4 class="card-header">
								<fmt:message key="div_head5" />
							</h4>
							<div class="card-body table-responsive">
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
														<td><a href="team?id=${stats.teamID}">${stats.teamName}</a></td>
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
	<script type="text/javascript" src="js/statistics.js"></script>
</fmt:bundle>
</html>