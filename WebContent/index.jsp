<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>


<div class="jumbotron">
	<h2 class = "text-center">Project ideas are seeds to change the world</h2>      
	<p class = "text-center">
					<a class = "btn btn-info btn-lg" href="<%=request.getContextPath()%>/pages/learnMore.jsp" method="post">
						<span class="glyphicon glyphicon-expand"></span> Learn more 
					</a>
	</p>  
</div>
<%
	if (session.getAttribute("loginState")!=null && (boolean)session.getAttribute("loginState")){
%>
    	<jsp:forward page="/pages/loggedIn.jsp"></jsp:forward>

<%
	}
%>

<% 
	if (session.getAttribute("loginState")!=null) 
      	if ((boolean)session.getAttribute("loginState") == false) {
%>		
			<script type="text/javascript">
				alert("Non-exist user ID or wrong password");
			</script>
<%
		}
%>

<jsp:include page="/utils/footer.jsp" />