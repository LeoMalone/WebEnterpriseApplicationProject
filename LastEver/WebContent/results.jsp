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
					<fmt:message key="div_head3" />
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
													<li class="nav-item active"><a class="nav-link"
														href="results?id=${row.divisionId}"> <fmt:message
																key="div_head3" />
													</a></li>
													<li class="nav-item"><a class="nav-link"
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
								<fmt:message key="div_head3" />
							</h4>
							<div class="card-body">
								<c:choose>
									<c:when test="${empty results}">
										<center>
											<b><fmt:message key="div_noresults" /></b>
										</center>
									</c:when>
									<c:otherwise>
										<c:forEach items="${results}" var="res">
											<table id="standings"
												class="table table-bordered table-striped table-dark table-hover table-sm"
												style="width: 100%; max-width: 500px;">
												<thead>
													<tr>
														<th scope="col" style="text-align: center"><c:if
																test="${cookie.language.value eq 'fr'}">
																<fmt:formatDate type="date" pattern="d MMM y"
																	value="${res.date}" />
															</c:if> <c:if test="${cookie.language.value ne 'fr'}">
																<fmt:formatDate type="date" pattern="MMM d y"
																	value="${res.date}" />
															</c:if></th>
														<th scope="col" style="text-align: center"><c:out
																value="${res.status}" /></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><a href="team?id=${res.homeID}">${res.homeTeam}</a></td>
														<td style="text-align: center"><c:out
																value="${res.homeScore}" /></td>
													</tr>
													<tr>
														<td><a href="team?id=${res.awayID}">${res.awayTeam}</a></td>
														<td style="text-align: center"><c:out
																value="${res.awayScore}" /></td>
													</tr>
													<tr>
														<td colspan="2"><b><fmt:message
																	key="home_scorers" /></b> <br> <c:choose>
																<c:when test="${res.homeScore eq 0}">
																	<fmt:message key="div_no_scorers" />
																</c:when>
																<c:otherwise>
																	<c:forEach items="${res.homeScorer}" var="hs">
																		<c:if test="${hs.goals eq 1}">
																			<a href="player?id=${hs.ID}">${hs.name}</a>
																			<br>
																		</c:if>
																		<c:if test="${hs.goals gt 1}">
																			<a href="player?id=${hs.ID}">${hs.name}</a> 
																			(<c:out value="${hs.goals}" />) 
																<br>
																		</c:if>
																	</c:forEach>
																</c:otherwise>
															</c:choose></td>
													</tr>
													<tr>
														<td colspan="2"><b><fmt:message
																	key="away_scorers" /></b> <br> <c:choose>
																<c:when test="${res.awayScore eq 0}">
																	<fmt:message key="div_no_scorers" />
																</c:when>
																<c:otherwise>
																	<c:forEach items="${res.awayScorer}" var="as">
																		<c:if test="${as.goals eq 1}">
																			<a href="player?id=${hs.ID}">${as.name}</a>
																			<br>
																		</c:if>
																		<c:if test="${as.goals gt 1}">
																			<a href="player?id=${as.ID}">${as.name}</a> 
																(<c:out value="${as.goals}" />)<br>
																		</c:if>
																	</c:forEach>
																</c:otherwise>
															</c:choose></td>
													</tr>
												</tbody>
											</table>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-lg-12 mt-5 mb-5">
						<div class="card">
							<div class="card-body">
								<c:forEach var="d" items="${currDiv}">
									<ul class="pagination justify-content-center">
										<c:choose>
											<c:when test="${currPage eq 1}">
												<li class="page-item disabled"><a class="page-link"
													href="#" tabindex="-1"><fmt:message key="prev_page" /></a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="results?id=${d.divisionId}&page=${currPage - 1}"><fmt:message
															key="prev_page" /></a></li>
											</c:otherwise>
										</c:choose>
										<li class="page-item active"><a class="page-link"
											href="results?id=${d.divisionId}&page=${currPage}"><c:out
													value="${currPage}" /></a></li>
										<c:choose>
											<c:when test="${currPage + 1 gt totalPages}">
												<li class="page-item disabled"><a class="page-link"
													href="#" tabindex="-1"><fmt:message key="next_page" /></a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="results?id=${d.divisionId}&page=${currPage + 1}"><fmt:message
															key="next_page" /></a></li>
											</c:otherwise>
										</c:choose>
									</ul>
								</c:forEach>
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