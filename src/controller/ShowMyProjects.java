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
import model.Owner;
import model.Project;
import model.db.ProjectDB;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;

/**
 * Servlet implementation class showMyProjects
 */
@WebServlet("/ShowMyProjects")
public class ShowMyProjects extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2452215401043600330L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{

		try
		{
			String login = (String) request.getSession().getAttribute("userId");
			Owner currentOwner = UserDB.getOwner(login);
			LinkedList<Project> myProjects = (LinkedList<Project>) ProjectDB.getProjectsOfOwner(currentOwner);
			StringBuilder sb = new StringBuilder();
			Iterator<Project> iter = myProjects.iterator();
			while (iter.hasNext())
			{
				Project currentProject = iter.next();
				String projectName = currentProject.getAcronym();
				String category = currentProject.getCategory().getDescription();
				double budget = currentProject.getBudget();
				LinkedList<Evaluation> evaList = (LinkedList<Evaluation>) currentProject.getEvaluations();
				int numberOfEvaluators = evaList.size();

				double[] buffer = new double[2]; // to save the result of risk level and attractiveness
				ProjectDB.calculateRiskLevelAndAttractiveness(evaList, buffer);
				double riskLevel = buffer[0];
				double attractiveness = buffer[1];

				sb.append("<tr>");
				sb.append("<td ><a href=\"/ProjectFarm/PrepareProjectDetails?projectName=" + projectName
						+ "\">" + projectName + "</a></td>");
				sb.append("<td>" + category + "</td>");
				sb.append("<td> 100 </td>");
				sb.append("<td>" + budget + " </td>");
				sb.append("<td bgcolor=\"" + calculateColor(riskLevel) + "\">"
						+ String.format("%.1f", riskLevel) + "</td>");
				sb.append("<td bgcolor=\"" + calculateColor(attractiveness) + "\">"
						+ String.format("%.1f", attractiveness) + "</td>");
				sb.append("<td>" + numberOfEvaluators + "</td>");
				sb.append("</tr>");

			}// end while
			request.setAttribute("tables", sb.toString());
			RequestDispatcher disp = request.getRequestDispatcher("/pages/myProjects.jsp");
			disp.forward(request, response);

		} catch (DatabaseAccessError | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end try
	}// end service

	private String calculateColor(double v)
	{
		double ref = 1.91;
		if (v >= 0.0 && v <= ref)
			return "green";
		else if (v > ref && v <= 2 * ref)
			return "yellow";
		else
			return "red";
	}
}// end servlet
