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
				<a href="/createUser" class="btn btn-success">Create New User</a>					
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">				
							<table class="table">
								<thead>
								    <tr>
								      <th scope="col">Username</th>
								      <th scope="col">User Type</th>
								      <th scope="col">Email Address</th>
								      <th scope="col">Password</th>
								    </tr>
								</thead>
							    <c:forEach items="${userList}" var="user">
							       <c:set var="teamName" value="${userName}"/>
							        <tr>
							        	<td>${user.username}</td>	
							        	<td>${user.userType}</td>
							        	<td>${user.emailAddress}</td>
							        	<td>${user.password}</td>
							        	<td>
							        		<a href="./rules_summary" class="btn btn-secondary btn-sm">
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