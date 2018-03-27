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
		- card with information on it
		- text, mailto link
		-->
			<div class="cards-container container">
				<h1 class="my-4">
					<fmt:message key="contact_header" />
				</h1>
				<!-- Email and Phone Number -->
				<div class="row" align="center">
					<div class="col-lg-4 mb-4" align="center">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="contact_head1" />
							</h4>
							<div class="card-body">
								<p class="card-text">
									<fmt:message key="contact_text1" />
								</p>
							</div>
						</div>
					</div>
					<!-- Google Maps Location -->
					<div class="col-lg-4 mb-4" align="center">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="ab_head3" />
							</h4>
							<div class="card-body">
								<iframe
									src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d22409.355508682052!2d-75.70157807521635!3d45.40592270176919!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x4a69b90dfe9b6eb2!2sTD+Place!5e0!3m2!1sen!2sca!4v1518301220254"
									width="300px" height="300px" frameborder="0" style="border: 0"
									allowfullscreen></iframe>
								<p class="card-text">
									<br />
									<fmt:message key="ab_text3" />
								</p>
							</div>
						</div>
					</div>
					<!-- Get in Touch! -->
					<div class="col-lg-4 mb-4" align="center">
						<div class="card h-100">
							<h4 class="card-header">
								<fmt:message key="ab_head4" />
							</h4>
							<form action="./getInTouch" method="POST">
								<!-- Need to create getInTouch page-->
								<div class="card-body">
									<p class="card-text">
									<div class="form-group" align="left">
										<label for="newFirstName">First Name</label> <input
											type="text" class="form-control" name="newFirstName"
											placeholder="You Name">
									</div>
									<div class="form-group" align="left">
										<label for="newLastName">Last Name</label> <input type="text"
											class="form-control" name="newLastName"
											placeholder="Your Last Name">
									</div>
									<div class="form-group" align="left">
										<!-- ADD city -->
										<label for="city">City</label> <select id="city" name="city">
											<option value="ottawa">Ottawa</option>
											<option value="nepean">Nepean</option>
											<option value="gatineau">Gatineau</option>
											<option value="kanata">Kanata</option>
										</select>
									</div>
									<div class="form-group" align="left">
										<!-- Add subject -->
										<label for="subject">Subject</label> <br>
										<textarea id="subject" name="subject" style="width: 100%"
											placeholder="Write something..">
    									</textarea>
									</div>
								</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-secondary">
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

</fmt:bundle>
</html>