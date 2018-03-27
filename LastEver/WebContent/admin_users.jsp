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
		<div class="cards-container container-fluid">
				<h1 class="my-4">
					${userName}: <fmt:message key="admin_title_user" />
				</h1>
				<a href="./adminCreate" class="btn btn-success"><fmt:message key="au_create" /></a>					
				<div class="row">
					<div class="col-lg-12 mb-5 mt-5">
						<div class="card">
							<div class="card-body">
								<div class="table-responsive">										
								<table class="table table-striped">
									<thead class="thead-dark">
									    <tr>
									    	<th scope="col">ID</th>
									    	<th scope="col"><fmt:message key="au_username" /></th>
									    	<th scope="col"><fmt:message key="au_firtname" /></th>
									    	<th scope="col"><fmt:message key="au_lastname" /></th>
										    <th scope="col"><fmt:message key="au_usertype" /></th>
										    <th scope="col"><fmt:message key="au_email" /></th>
										    <th scope="col"><fmt:message key="au_email_val" /></th>
										    <th scope="col"><fmt:message key="au_account_created" /></th>
										    <th scope="col"><fmt:message key="au_last_up" /></th>
										    <th scope="col"><fmt:message key="au_last_log" /></th>
										    <th scope="col"><fmt:message key="au_edit" /></th>
									    </tr>
									</thead>
								    <c:forEach items="${userList}" var="user">
								        <tr>
								        	<td scope="col">${user.id}</td>
								        	<td scope="col">${user.username}</td>
								        	<td scope="col">${user.firstName}</td>
								        	<td scope="col">${user.lastName}</td>
								        	<td scope="col">${user.userType}</td>
								        	<td scope="col">${user.emailAddress}</td>
								        	<td scope="col">${user.emailValidated}</td>
								        	<td scope="col">${user.accountCreated.toString()}</td>
								        	<td scope="col">${user.accountUpdated}</td>
								        	<td scope="col">${user.lastLogin.toString()}</td>
								        	<td scope="col">
								        		<a href="./editUser?=${user.id}" class="btn btn-dark btn-sm">
								        			<i class="fa fa-edit"></i> 
												</a>
											</td>		            
								        </tr>
								    </c:forEach>
								</table>
							</div>
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

</fmt:bundle>
</html>