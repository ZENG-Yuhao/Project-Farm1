<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/pages/projectDetailsEvaluator.jsp" />
</jsp:include>

<div class="panel panel-default">
  <div class="panel-heading"><h2> <span class="glyphicon glyphicon-tag"></span> <%=request.getAttribute("projectName") %> </h2></div>
  <div class="panel-body">
  <form action="<%=request.getContextPath()%>/SaveEvaluation?projectName=<%=request.getAttribute("projectName") %>" method="post"> 
  																											<! here, if post method is not declared, parameter 'projectName' would not be sent to server>
		<div class="panel panel-default">
			<div class="panel-body">
				<h4><span class="glyphicon glyphicon-info-sign"></span> <strong>Evaluation</strong></h4>
				<br>
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
						<div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
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
   				<hr>
				<h4><span class="glyphicon glyphicon-stats"></span> <strong>Statistics</strong></h4>
					<p class="h4">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Attention: new evaluation will replace the older one.)</p>
					 <span class="h4 form-inline">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Risk Level: 
					 		<select id="riskLevel" class="form-control" name="riskLevel">
       						  <option value="1">&nbsp; &nbsp;&nbsp; &nbsp;1&nbsp; &nbsp;&nbsp; &nbsp;</option>
       		  				  <option value="2">&nbsp; &nbsp;&nbsp; &nbsp;2&nbsp; &nbsp;&nbsp; &nbsp;</option>
       						  <option value="3">&nbsp; &nbsp;&nbsp; &nbsp;3&nbsp; &nbsp;&nbsp; &nbsp;</option>
       		  				  <option value="4">&nbsp; &nbsp;&nbsp; &nbsp;4&nbsp; &nbsp;&nbsp; &nbsp;</option>
       		  				  <option value="5">&nbsp; &nbsp;&nbsp; &nbsp;5&nbsp; &nbsp;&nbsp; &nbsp;</option>
      						</select>
					</span>
				 	 
					 <span class="h4 form-inline">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Attractiveness: 
					 		<select id="attractiveness" class="form-control" name="attractiveness">
       						  <option value="1">&nbsp; &nbsp;&nbsp; &nbsp;1&nbsp; &nbsp;&nbsp; &nbsp;</option>
       		  				  <option value="2">&nbsp; &nbsp;&nbsp; &nbsp;2&nbsp; &nbsp;&nbsp; &nbsp;</option>
       						  <option value="3">&nbsp; &nbsp;&nbsp; &nbsp;3&nbsp; &nbsp;&nbsp; &nbsp;</option>
       		  				  <option value="4">&nbsp; &nbsp;&nbsp; &nbsp;4&nbsp; &nbsp;&nbsp; &nbsp;</option>
       		  				  <option value="5">&nbsp; &nbsp;&nbsp; &nbsp;5&nbsp; &nbsp;&nbsp; &nbsp;</option>
      						</select>
					</span>
					 <span class="h4 form-inline">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
					 														# of evaluators: <%=request.getAttribute("numberOfEvaluators") %>

					</span>
			
			</div>

		</div>
					<p> 
					<button type="submit" class="btn btn-info" style="width: 150px"><span class="glyphicon glyphicon-ok"></span> save</button> 
					<button  class="btn btn-info" style="width: 150px"><span class="glyphicon glyphicon-remove"></span> discard</button>
					 </p>
	</form>
	</div>
</div>


<jsp:include page="/utils/footer.jsp" />