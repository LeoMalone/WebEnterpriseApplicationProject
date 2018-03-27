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
		<div class="modal fade" id="deleteSchedule" tabindex="-1"
			role="dialog" aria-labelledby="deleteScheduleLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="deleteScheduleLabel">
							<fmt:message key="admin_es_modal_title" />
							${schedule.title}
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<fmt:message key="admin_es_modal_body" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<fmt:message key="admin_eu_model_cls" />
						</button>
						<form action="deleteSchedule?=${schedule.title}" method="POST">
							<button type="submit" class="btn btn-danger">
								<fmt:message key="admin_es_del" />
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	
		<div class="main-cover">
			<!-- Page Content -->
			<div class="cards-container container">
				<h1 class="my-4">${userName}:
					<fmt:message key="admin_es_title" />
					${schedule.title}
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="admin_es_head" />
							</h4>
							<div class="card-body">
								<form action="editSchedule?=${schedule.title}" method="POST">
									<div class="form-group">
										<label for="editGameDate"><fmt:message
												key="admin_cs_date" /></label> <input type="text"
											class="form-control" id="datePickInput" name="editGameDate"
											value="${schedule.gameDate}">
									</div>
									<div class="form-group clockpicker">
										<label for="editGameDate"><fmt:message
												key="admin_cs_time" /></label> <input type="text"
											class="form-control" name="editGameTime"
											value="${schedule.gameTime}">
									</div>
									<div class="form-group">
										<label for="editHomeTeam"><fmt:message
												key="admin_cs_ht" /></label> <select
											class="custom-select my-1 mr-sm-2" id="editHomeTeam"
											name="editHomeTeam">
											<c:forEach items="${teamList}" var="team">
												<option value="${team.teamId}"
													${team.teamId == schedule.homeTeam ?'selected':''}>${team.teamName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="editAwayTeam"><fmt:message
												key="admin_cs_at" /></label> <select
											class="custom-select my-1 mr-sm-2" id="editAwayTeam"
											name="editAwayTeam">
											<c:forEach items="${teamList}" var="team">
												<option value="${team.teamId}"
													${team.teamId == schedule.awayTeam ?'selected':''}>${team.teamName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="editHomeScore"><fmt:message
												key="admin_cs_hs" /></label> <input type="text"
											class="form-control" name="editHomeScore"
											value="${schedule.homeScore}">
									</div>
									<div class="form-group">
										<label for="editAwayScore"><fmt:message
												key="admin_cs_as" /></label> <input type="text"
											class="form-control" name="editAwayScore"
											value="${schedule.awayScore}">
									</div>
									<div class="form-group">
										<label for="editGameStatus"><fmt:message
												key="admin_cs_gs" /></label> <select
											class="custom-select my-1 mr-sm-2" id="editGameStatus"
											name="editGameStatus">
											<option value="Final"
												${schedule.gameStatus == 'Final' ?'selected':''}>Final</option>
											<option value="Scheduled"
												${schedule.gameStatus == 'Scheduled' ?'selected':''}>Scheduled</option>
										</select>
									</div>
									<button type="submit" class="btn btn-outline-success">
										<fmt:message key="admin_eu_save" />
									</button>
								</form>
							</div>
							<div class="card-footer">
								<button type="button" class="btn btn-danger" data-toggle="modal"
									data-target="#deleteSchedule">
									<fmt:message key="admin_es_del" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /row -->
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





	<script type="text/javascript"
		src="date-picker/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="clockpicker/js/clockpicker.js"></script>
	<script type="text/javascript" src="js/edit_schedule.js"></script>

</fmt:bundle>
</html>