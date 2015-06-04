<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><%=request.getParameter("title")%></title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="/ProjectFarm/ext/bootstrap/3.2.2/css/bootstrap.min.css">
<script src="/ProjectFarm/ext/jquery/1.11.2/jquery-1.11.2.js"></script>
<script src="/ProjectFarm/ext/bootstrap/3.2.2/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/index.jsp"><span class="glyphicon glyphicon-home"></span> <%=request.getParameter("title")%></a>
			</div>

			<div>
				<%
					if (session.getAttribute("name") == null || (session.getAttribute("name")!=null && (boolean)session.getAttribute("loginState") == false )){
				%>
						<form class="navbar-form form-inline pull-right" action="<%=request.getContextPath()%>/LoginServlet" method="post">
						<% 
							if (session.getAttribute("loginState")!=null && (boolean)session.getAttribute("loginState") == false){ 
						%>
								<div class="form-group has-error">
						<%
							}
						%>
								<input type="text" class="form-control" placeholder="Your email" name="userID"> 
								<input type="password" class="form-control" placeholder="Password" name="password"> 
								<input	type="hidden" name="pageSuccess" value="<%=request.getParameter("page")%>" />
								<button type="submit" class="btn">Sign in</button>
						<% 
							if (session.getAttribute("loginState")!=null && (boolean)session.getAttribute("loginState") == false){ 
						%>
								</div>
						<%
							}
						%>
						</form>
				<%
					} else if ( (boolean)session.getAttribute("loginState")==true){
				%>
					<ul class="nav navbar-nav navbar-right">
						<li class="btn-group">
							<a class="btn btn-default" href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>  <%=session.getAttribute("name") %>  </a> 
							<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<% 
									if (session.getAttribute("userType").equals("Owner")) {
								%>
										<li><a href="<%=request.getContextPath()%>/ShowMyProjects" method="post">
												<span class="glyphicon glyphicon-list"></span> My projects </a></li>
								<%
									} else {
								%>
										<li><a href="<%=request.getContextPath()%>/ShowAllProjects" method="post">
												<span class="glyphicon glyphicon-list"></span> All projects </a></li>
								<%
									}
								%>
							
										<li><a href="<%=request.getContextPath()%>/pages/logout.jsp?pageSuccess=<%=session.getAttribute("pageSuccess")%>">
							      				<span class="glyphicon glyphicon-log-out"></span> Log out </a></li>
							</ul>
						</li>
						&nbsp; &nbsp; &nbsp;
				</ul>
				<%
				} 
				%>
			</div>
		</div>
	</nav>

	<div class="container">

		<!-- ProjectFarm -->