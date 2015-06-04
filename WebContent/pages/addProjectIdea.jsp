<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="ProjectFarm" />
	<jsp:param name="page" value="/pages/addProjectIdea.jsp" />
</jsp:include>

<div class="panel panel-default">
	<div class="panel-heading"> <h2><span class="glyphicon glyphicon-tag"></span> New project idea</h2></div>
	<div class="panel-body">
	<form action="<%=request.getContextPath()%>/SaveProjectIdea">
			<div class="form-group">
				<label for="projectTitle">Title</label>
				<input type="text" class="form-control" id="projectTitle" name="title"  placeholder="Project Title">
				<label for="projectDescription">Description</label>
				<textarea class="form-control" id="projectDescription" name="description" rows="10" placeholder="Project Description"></textarea>
				<br>
				
			</div>
			<div class="form-inline">
					<label for="category">Category</label>
					<select id="category" class="form-control" name="category">
       					      
       					      <% String arrCategories = (String)request.getAttribute("arrCategories"); 
							        String[] categories = arrCategories.split(",");
							  		for (int i =0; i<categories.length; i++){
							  %>
							        		<option value="<%=categories[i]%>"><%=categories[i]%></option>
							  <%
							  		}//for 
							  %>
      				</select>
      				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
      				<label for="budget">Budget</label>
      			    	<div class="input-group">	
      			    		<div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
      						<input type="text" class="form-control text-right" id="budget" name="budget">
      						<div class="input-group-addon">.00</div>
			  		</div>
			</div>
			<br>
				    <p> 
						<button type="submit" class="btn btn-info" style="width: 150px"><span class="glyphicon glyphicon-ok"></span> save</button> 
						<button  class="btn btn-info" style="width: 150px"  type="reset"><span class="glyphicon glyphicon-remove"></span> discard</button>
					 </p>
	</form>
	
	</div>  
</div>
<jsp:include page="/utils/footer.jsp" />