<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<title>LastEver</title>
</head>

<body>
	<nav
		class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="index.jsp"><i>LastEver</i></a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>


				<%--kevin read
            updating menu bar - feb 10
            --%>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownPortfolio" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> League </a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdownPortfolio">
						<a class="dropdown-item" href="about.jsp">About</a> <a
							class="dropdown-item" href="rules.jsp">Rules</a> <a
							class="dropdown-item" href="registration.jsp">Registration</a> <a
							class="dropdown-item" href="contact.jsp">Contact</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownPortfolio" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Divisions </a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdownPortfolio">
						<a class="dropdown-item" href="division1.jsp">Division 1</a> <a
							class="dropdown-item" href="division2.jsp">Division 2</a> <a
							class="dropdown-item" href="division3.jsp">Division 3</a>
					</div></li>



				<li class="nav-item"><a class="nav-link" href="login.jsp">Sign
						In</a></li>
			</ul>
		</div>
	</div>
	</nav>


	<div class="main-cover">
		<!-- Page Content -->
		<div class="cards-container container">
			<h1 class="my-4">Division 2</h1>
			<!-- Marketing Icons Section -->
			<div class="row">
				<div class="col-lg-4 mb-4">
					<div class="card h-100">
						<h4 class="card-header">Teams</h4>
						<div class="card-body">
							<p class="card-text">



									
								<%
								boolean status = false;
						        Connection conn = null;
						        PreparedStatement pst = null;
						        ResultSet rs = null;
						       
						        
						        String url = "jdbc:mysql://localhost:3306/";
						        String dbName = "lastever";
						        String driver = "com.mysql.jdbc.Driver";
						        String userName = "admin";
						        String password = "lastever";
						        try {
						            Class.forName(driver).newInstance();
						            conn = DriverManager.getConnection(url + dbName, userName, password);
						            pst = conn.prepareStatement("select teamName from team");
						            rs = pst.executeQuery();
						            status = rs.next();
						           
						            %>
						            <tr>
							        	<th>Team Name</th>
							        	<th>Team Abbreviation</th>
							   		</tr>
							    
							        <tr>
							            <td><%=rs.getString(1) %></td>
							        </tr>
						            <%			            
						            
						        } catch (Exception e) {
						            System.out.println(e);
						        } finally {
						            if (conn != null) {
						            	try {
						                    conn.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						            if (pst != null) {
						            	try {
						                    pst.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						            if (rs != null) {
						            	try {
						                    rs.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						        }
								%>
							
							</table>





							</p>

						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-4">
					<div class="card h-100">
						<h4 class="card-header">Schedule</h4>
						<div class="card-body">
							<p class="card-text">







								<%
								status = false;
						       	conn = null;
						       	pst = null;
						        rs = null;
						       
						        
						        url = "jdbc:mysql://localhost:3306/";
						        dbName = "lastever";
						        driver = "com.mysql.jdbc.Driver";
						        userName = "admin";
						        password = "lastever";
						        try {
						            Class.forName(driver).newInstance();
						            conn = DriverManager.getConnection(url + dbName, userName, password);
						            pst = conn.prepareStatement("select schedule from team");
						            rs = pst.executeQuery();
						            status = rs.next();
						           
						            %>
						            <tr>
							        	<th>Team Name            </th>
							        	<th>Team Abbreviation</th>
							   		</tr>
							    
							        <tr>
							            <td><%=rs.getString(1) %></td>
							            <td><%=rs.getString(2) %></td>
							        </tr>
						            <%
						            
						            
						        } catch (Exception e) {
						            System.out.println(e);
						        } finally {
						            if (conn != null) {
						            	try {
						                    conn.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						            if (pst != null) {
						            	try {
						                    pst.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						            if (rs != null) {
						            	try {
						                    rs.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						        }
								%>
							</table>












						</div>
									</div>
								</div>
								<div class="col-lg-4 mb-4">
									<div class="card h-100">
										<h4 class="card-header">Results</h4>
										<div class="card-body">
											<p class="card-text">Results</p>
										</div>
									</div>
								</div>
								<div class="col-lg-4 mb-4">
									<div class="card h-100">
										<h4 class="card-header">Standings</h4>
										<div class="card-body">
											<p class="card-text">Standings</p>
										</div>
									</div>
								</div>
								<div class="col-lg-4 mb-4">
									<div class="card h-100">
										<h4 class="card-header">Leaders</h4>
										<div class="card-body">
											<p class="card-text">





												
							<table>

								<%
								status = false;
						        conn = null;
						        pst = null;
						        rs = null;
						       
						        
						        url = "jdbc:mysql://localhost:3306/";
						        dbName = "lastever";
						        driver = "com.mysql.jdbc.Driver";
						        userName = "admin";
						        password = "lastever";
						        try {
						            Class.forName(driver).newInstance();
						            conn = DriverManager.getConnection(url + dbName, userName, password);
						            pst = conn.prepareStatement("select teamName from team");
						            rs = pst.executeQuery();
						            status = rs.next();
						           
						            
						            
						            
						            %>
						            <tr>
							        	<th>Team Name</th>
							        	<th>Team Abbreviation</th>
							   		</tr>
							    
							        <tr>
							            <td><%=rs.getString(1) %></td>
							        </tr>
						            <%
						            
						            
						        } catch (Exception e) {
						            System.out.println(e);
						        } finally {
						            if (conn != null) {
						            	try {
						                    conn.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						            if (pst != null) {
						            	try {
						                    pst.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						            if (rs != null) {
						            	try {
						                    rs.close();
						                } catch (SQLException e) {
						                    e.printStackTrace();
						                }
						            }
						        }
								%>
						</table>







											</p>
										</div>
									</div>
								</div>
								</div>
								<!-- /.row -->
								</div>
								</div>
								<!-- Footer -->
								<footer class="page-footer py-3 bg-dark">
								<div class="container-fluid">
									<p class="m-0 text-center text-white">Copyright &copy;
										LastEver 2018</p>
								</div>
								</footer>

								<!-- Bootstrap core JavaScript -->
								<script type="text/javascript"
		src="bootstrap/js/bootstrap.min.js"></script>
								<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
								<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
								<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</body>
</html>