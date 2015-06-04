<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/pages/allProjects.jsp" />
</jsp:include>

<table class="table table-hover text-center">
	<tr>
		<th class="text-center">Acronym</th>
		<th class="text-center">Category</th>
		<th class="text-center"># of incubation days</th>
		<th class="text-center">Budget</th>
		<th class="text-center"># of evaluation</th>
		<th class="text-center">Evaluate</th>
	</tr>
	<%=request.getAttribute("tables")%>
</table>
<jsp:include page="/utils/footer.jsp" />