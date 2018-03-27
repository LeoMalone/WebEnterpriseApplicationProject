<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
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
<fmt:bundle basename="TestBundle">

<body>
	<div class="main-cover">
		<!-- Page Content
		- cards with information on them
		- widgets
		-->
		<div class="cards-container container">
			<fmt:bundle basename="TestBundle">
				<h1 class="my-4">
					<fmt:message key="home_header" />
				</h1>
				<!-- Marketing Icons Section -->
				<div class="row">
					<div class="col-lg-8 mb-5">
						<div class="card">
							<div class="card-body">
								<div id="mainCarousel" class="carousel slide"
									data-ride="carousel">
									<div class="carousel-inner">
										<div class="carousel-item active">
											<img class="d-block w-100"
												src="https://images.pexels.com/photos/114296/pexels-photo-114296.jpeg?w=1920&h=1080"
												alt="First slide">
										</div>
										<div class="carousel-item">
											<img class="d-block w-100"
												src="https://images.pexels.com/photos/17598/pexels-photo.jpg?w=1920&h=1080"
												alt="Second slide">
										</div>
									</div>
									<a class="carousel-control-prev" href="#mainCarousel"
										role="button" data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next" href="#mainCarousel"
										role="button" data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4 mb-5">
						<div class="card">
							<div class="card-body">
								<h4 class="card-header">
									<fmt:message key="home_head" />
								</h4>
								<div id="openweathermap-widget-15"></div>
								<script>
									window.myWidgetParam ? window.myWidgetParam
											: window.myWidgetParam = [];
									window.myWidgetParam
											.push({
												id : 15,
												cityid : '6094817',
												appid : 'a4e18466ea056cf88f0ca54293678bfc',
												units : 'metric',
												containerid : 'openweathermap-widget-15',
											});
									(function() {
										var script = document
												.createElement('script');
										script.async = true;
										script.charset = "utf-8";
										script.src = "//openweathermap.org/themes/openweathermap/assets/vendor/owm/js/weather-widget-generator.js";
										var s = document
												.getElementsByTagName('script')[0];
										s.parentNode.insertBefore(script, s);
									})();
								</script>
							</div>
						</div>
					</div>
					<c:choose>
						<c:when test="${empty news}">
							<div class="col-lg-12 mb-5">
								<div class="card">
									<h4 class="card-header">
										<fmt:message key="news_no_news" />
									</h4>
									<div class="card-body">
										<b style="text-align: center"><fmt:message
												key="news_no_news_message" /></b>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${news}" var="n">
								<div class="col-lg-12 mb-5 mt-5">
									<div class="card">
										<h4 class="card-header">
											<c:out value="${n.title}" />
										</h4>
										<div class="card-body">
											<c:out value="${n.postedTime}" />
											|
											<fmt:message key="news_by" />
											:
											<c:out value="${n.userName}" />
											<br></br>
											<c:out value="${n.content}" escapeXml="false" />
										</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<div class="col-lg-12 mt-5 mb-5">
						<div class="card">
							<div class="card-body">
								<ul class="pagination justify-content-center">
									<c:choose>
										<c:when test="${currPage eq 1}">
											<li class="page-item disabled"><a class="page-link"
												href="#" tabindex="-1"><fmt:message key="prev_page" /></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="index?page=${currPage - 1}"><fmt:message
														key="prev_page" /></a></li>
										</c:otherwise>
									</c:choose>
									<li class="page-item active"><a class="page-link"
										href="index?page=${currPage}"><c:out value="${currPage}" /></a></li>
									<c:choose>
										<c:when test="${currPage + 1 gt totalPages}">
											<li class="page-item disabled"><a class="page-link"
												href="#" tabindex="-1"><fmt:message key="next_page" /></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="index?page=${currPage + 1}"><fmt:message
														key="next_page" /></a></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</fmt:bundle>
			<!-- /.row -->
		</div>
	</div>


</fmt:bundle>
	<!-- Check out dat footer file - depending on language -->
	<c:if test="${cookie.language.value ne 'fr'}">

		<jsp:include page="_footer.jsp" />
	</c:if>
	<c:if test="${cookie.language.value eq 'fr'}">
		<jsp:include page="_footer_fr.jsp" />
	</c:if>


</body>
</html>