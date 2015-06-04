<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/pages/projectDetailsOwner.jsp" />
</jsp:include>

<div class="panel panel-default">
  <div class="panel-heading"><h2> <span class="glyphicon glyphicon-tag"></span> <%=request.getAttribute("projectName") %> </h2></div>
  <div class="panel-body">
		<div class="panel panel-default">
			<div class="panel-body">
				<h4><span class="glyphicon glyphicon-info-sign"></span> <strong>Evaluation</strong></h4>
				 	 <h4>Created<span class="glyphicon glyphicon-menu-right"></span> <%=request.getAttribute("date") %> </h4>
				 	 <h4>Description<span class="glyphicon glyphicon-menu-down"></span></h4>
				 			 <div class="panel panel-default">
							 		<div class="panel-body">
							 				<%=request.getAttribute("description") %>
							 		</div>
							 </div>
				 	 <h4>Category<span class="glyphicon glyphicon-menu-right"></span> <%=request.getAttribute("category") %></h4>
				<div class="form-inline">
					<span class="h4">Budget<span class="glyphicon glyphicon-menu-right"></span> </span>
					<div class="input-group">
						<div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span> </div>
						<input type="text" class="form-control text-right" readonly id="budget" value="<%=request.getAttribute("budget") %>">
						<div class="input-group-addon">.00</div>
					</div>
				</div>

				<hr>
				<h4><span class="glyphicon glyphicon-folder-open"></span> <strong>Documents</strong></h4>
				 	 <%
				 	 	if (request.getAttribute("docList")!=null){  
				 	 %>
						 	 <%=request.getAttribute("docList") %>	
				 	 <%
				 	 	} else {
				 	 %>
				 	 		 <h4> (This project has not been added any document) </h4>
					<%
						}
					%>
					<form action="<%=request.getContextPath() %>/UploadFile?projectName=<%=request.getAttribute("projectName") %>" method="post" enctype="multipart/form-data">
      				<div>
      				<input type="file" class="form-controlt" style="width: 350px" name="file" id="file" /> <br/>
      				<input type="submit" class="btn btn-info" value="Upload" name="upload" id="upload" style="width: 150px" /> 
        			</div>
        			</form>
   				<hr>
				<h4><span class="glyphicon glyphicon-stats"></span> <strong>Statistics</strong></h4>
					 <span class="h4 form-inline">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
					 												Risk Level: <%=request.getAttribute("riskLevel") %></span>
				 	 
					 <span class="h4 form-inline">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
					 												Attractiveness: <%=request.getAttribute("attractiveness") %></span>

					 <span class="h4 form-inline">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
					 												# of evaluators: <%=request.getAttribute("numberOfEvaluators") %></span>
			</div>

		</div>
				    <p> 
						<a href="<%=request.getContextPath() %>/pages/ok.jsp"class="btn btn-info" style="width: 150px">
						<span class="glyphicon glyphicon-ok"></span> save</a> 
						
						<a  href="<%=request.getContextPath() %>/index.jsp" class="btn btn-info" style="width: 150px">
						<span class="glyphicon glyphicon-remove"></span> discard</a>
					 </p>
	</div>
</div>


<jsp:include page="/utils/footer.jsp" />