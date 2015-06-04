package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evaluation;
import model.Project;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;

/**
 * Servlet implementation class ShowAllProjects
 */
@WebServlet("/ShowAllProjects")
public class ShowAllProjects extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2959762065170057499L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		try
		{
			LinkedList<Project> allProjects = (LinkedList<Project>) ProjectDB.getAllProjects();
			StringBuilder sb = new StringBuilder();
			Iterator<Project> iter = allProjects.iterator();
			while (iter.hasNext())
			{
				Project currentProject = iter.next();
				String projectName = currentProject.getAcronym();
				String category = currentProject.getCategory().getDescription();
				double budget = currentProject.getBudget();
				LinkedList<Evaluation> evaList = (LinkedList<Evaluation>) currentProject.getEvaluations();
				int numberOfEvaluators = evaList.size();

				sb.append("<tr>");
				sb.append("<td >" + projectName + "</td>");
				sb.append("<td>" + category + "</td>");
				sb.append("<td> 100 </td>");
				sb.append("<td>" + budget + " </td>");
				sb.append("<td>" + numberOfEvaluators + "</td>");
				sb.append("<td><a href=\"/ProjectFarm/PrepareProjectDetails?projectName=" + projectName + "\"><span class=\"glyphicon glyphicon-pencil\"></span></a></td>");
				sb.append("</tr>");

			}// end while
			request.setAttribute("tables", sb.toString());
			RequestDispatcher disp = request.getRequestDispatcher("/pages/allProjects.jsp");
			disp.forward(request, response);
		} catch (DatabaseAccessError | IOException e)
		{

			e.printStackTrace();
		}

	}// end service
}
