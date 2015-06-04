<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/pages/learnMore.jsp" />
</jsp:include>


<div class="jumbotron">
	<h2 class = "text-center"><span class="glyphicon glyphicon-expand"></span> Learn More</h2>      
	<p class = "text-center">
				<h3> This project is a version without database, in order to show examples, every time when <strong>"Owner"</strong> log in, and 
				<strong>"loginServlet"</strong> is invoked, there will be serveral exemples being added into memory. 
				If you want to see these examples, please log in with the account <strong>"john@acme.com"</strong>.
				Tank you.
				</h3>
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