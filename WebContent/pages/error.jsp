<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/error.jsp" />
</jsp:include>


<div class="jumbotron">
	<h2 class = "text-center"><span class="glyphicon glyphicon-alert"></span> Error!</h2>      
	<p class = "text-center">
				<h3> An error has occured.
				</h3>
	</p>  
</div>


<jsp:include page="/utils/footer.jsp" />