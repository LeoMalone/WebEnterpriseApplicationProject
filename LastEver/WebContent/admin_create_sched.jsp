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
			<div class="cards-container container">
				<h1 class="my-4">
					${userName}:
					<fmt:message key="admin_cs_title" />
				</h1>
				<div class="row">
					<div class="col-lg-12 mb-4">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="admin_cs_head" />
							</h4>
							<form action="scheduleCreate" method="POST">
								<div class="card-body">
									<div class="form-group">
										<label for="editGameDate"><fmt:message
												key="admin_cs_date" /></label> <input type="text"
											class="form-control" id="datePickInput" name="newGameDate"
											placeholder="yyyy-mm-dd">
									</div>
									<div class="form-group clockpicker">
										<label for="newGameTime"><fmt:message
												key="admin_cs_time" /></label> <input type="text"
											class="form-control" name="newGameTime" placeholder="hh:mm">
									</div>
									<div class="form-group">
										<label for="newHomeTeam"><fmt:message
												key="admin_cs_ht" /></label> <select
											class="custom-select my-1 mr-sm-2" id="newHomeTeam"
											name="newHomeTeam">
											<c:forEach items="${teamList}" var="team">
												<option value="${team.teamId}">${team.teamName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="newAwayTeam"><fmt:message
												key="admin_cs_at" /></label> <select
											class="custom-select my-1 mr-sm-2" id="newAwayTeam"
											name="newAwayTeam">
											<c:forEach items="${teamList}" var="team">
												<option value="${team.teamId}">${team.teamName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="newHomeScore"><fmt:message
												key="admin_cs_hs" /></label> <input type="text"
											class="form-control" name="newHomeScore"
											placeholder="<fmt:message key="admin_cs_ph" />">
									</div>
									<div class="form-group">
										<label for="newAwayScore"><fmt:message
												key="admin_cs_as" /></label> <input type="text"
											class="form-control" name="newAwayScore"
											placeholder="<fmt:message key="admin_cs_ph" />">
									</div>
									<div class="form-group">
										<label for="newGameStatus"><fmt:message
												key="admin_cs_gs" /></label> <select
											class="custom-select my-1 mr-sm-2" id="newGameStatus"
											name="newGameStatus">
											<option value="Final">Final</option>
											<option value="Scheduled" selected>Scheduled</option>
										</select>
									</div>
								</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-success">
										<fmt:message key="signin_button1" />
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
	</body>

	<!-- Check out dat footer file - depending on language -->
	<c:if test="${cookie.language.value ne 'fr'}">

		<jsp:include page="_footer.jsp" />
	</c:if>
	<c:if test="${cookie.language.value eq 'fr'}">
		<jsp:include page="_footer_fr.jsp" />
	</c:if>


	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript"
		src="date-picker/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="clockpicker/js/clockpicker.js"></script>
	<script type="text/javascript" src="js/edit_schedule.js"></script>
</fmt:bundle>
</html>