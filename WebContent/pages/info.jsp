<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/pages/info.jsp" />
</jsp:include>


<div class="jumbotron">
	<h2 class = "text-center"> <%=request.getAttribute("message") %> </h2>      
	<p class = "text-center">
				<a class = "btn btn-info" href="<%=request.getAttribute("redirectPath")%>"> If page does not redirect automatically, click here </a>
	</p>  
</div>

<jsp:include page="/utils/footer.jsp" />