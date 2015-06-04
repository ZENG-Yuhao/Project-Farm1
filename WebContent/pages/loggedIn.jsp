<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/pages/loggedIn.jsp" />
</jsp:include>

<div class="jumbotron">
	<h2 class = "text-center">Project ideas are seeds to change the world</h2>      
	<p class = "text-center">
		<% 
			if (session.getAttribute("userType").equals("Owner")){
		%>
				<a class = "btn btn-info btn-lg" href="<%=request.getContextPath()%>/InitAddProjectIdea" method="post">
					 <span class="glyphicon glyphicon-plus"></span> New project idea 
				</a>
		<% 
			}else {
		%>
				<button class = "btn btn-info btn-lg" ><span class="glyphicon glyphicon-expand"></span> Learn more </button>
		<%
			} 
		%>
	</p>  
</div>



<jsp:include page="/utils/footer.jsp" />