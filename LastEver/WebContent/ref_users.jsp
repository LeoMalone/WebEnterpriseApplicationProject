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
					${userName}: Users
				</h1>					
				<div class="row">
				<!-- List of referee profiles  -->
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">				
							<table class="table">
								<thead class="thead-dark">
								    <tr>
								      <th scope="col">Username</th>
								      <th scope="col">User Type</th>
								      <th scope="col">Email Address</th>
								      <th scope="col">Password</th>
								      <th scope="col"></th>
								    </tr>
								</thead>
							    <c:forEach items="${refUserList}" var="user">
							        <tr>
							        	<td>${user.username}</td>	
							        	<td>${user.userType}</td>
							        	<td>${user.emailAddress}</td>
							        	<td>${user.password}</td>
							        	<td>
							        		<a href="./editRefUser?=${user.id}" class="btn btn-dark btn-sm">
							        			<i class="fa fa-edit"></i> 
											</a>
										</td>		            
							        </tr>
							    </c:forEach>
							</table>				
						</div>
					</div>
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
