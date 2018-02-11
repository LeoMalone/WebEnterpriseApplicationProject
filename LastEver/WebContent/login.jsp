<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<fmt:setLocale value="${param.language}" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LastEver</title>	
	<!-- Bootstrap core CSS -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<!-- Custom styles for this template -->
    <link href="css/cover.css" rel="stylesheet">
<fmt:bundle basename="TestBundle">
	<title>Last Ever - <fmt:message key="login"/></title>
	</fmt:bundle>
</head>
<body>
	<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
		<a class="navbar-brand" href="index.jsp"><img src="images/logo_sm4.png" /></a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="index.jsp">Home</a>
            </li>
            
            
            <%--kevin read
            updating menu bar - feb 10
            --%>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                League
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="about.jsp">About</a>
                <a class="dropdown-item" href="rules.jsp">Rules</a>
                <a class="dropdown-item" href="registration.jsp">Registration</a>
                <a class="dropdown-item" href="contact.jsp">Contact</a> 
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Divisions
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="division1.jsp">Division 1</a>
                <a class="dropdown-item" href="division2.jsp">Division 2</a>
                <a class="dropdown-item" href="division3.jsp">Division 3</a> 
              </div>
            </li>



            <li class="nav-item">
              <a class="nav-link active" href="#">Sign In</a>
              <li class="nav-item"><a class="nav-link" href=""></a></li>
              <li class="nav-item">
			<fmt:bundle basename="TestBundle">
    <form action="" method="post">
		<select name="language" onchange="this.form.submit()">
    	 	<option value="en" ${param.language == 'en' ? 'selected' : ''}><fmt:message key="english" /></option>
	    	<option value="fr" ${param.language == 'fr' ? 'selected' : ''}><fmt:message key="french" /></option>
    	</select>
    </form>
    </fmt:bundle>
    </li>
    
    
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="main-cover">    	
    	<!-- Page Content -->
		<div class="cards-container container">
	    	<fmt:bundle basename="TestBundle">
	    	<h1 class="my-4"><fmt:message key="signin_header"/></h1>
			<!-- Marketing Icons Section -->
			<div class="row">
			  <div class="col-lg-12 mb-4">
			    <div class="card h-100">
			      <h4 class="card-header"><fmt:message key="signin_head1"/></h4>
			      <div class="card-body">
			        <p class="card-text"><fmt:message key="signin_text1"/></p>
			      </div>
			      <div class="card-footer">
			        <a href="#" class="btn btn-primary"><fmt:message key="signin_button1"/></a>
			      </div>
			    </div>
			  </div>
			</div>
			</fmt:bundle>
			<!-- /.row -->
	    </div>
    </div>

    <!-- Footer -->
    <footer class="page-footer py-3 bg-dark">
      <div class="container-fluid">
        <p class="m-0 text-center text-white">Copyright &copy; <img src="images/logo_sm4.png" /> 2018</p>
      </div>
    </footer>
    
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>