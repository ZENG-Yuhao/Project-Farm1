package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Document;
import model.Evaluation;
import model.Project;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/PrepareProjectDetails")
public class PrepareProjectDetails extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5172490822766045892L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String projectName = request.getParameter("projectName");
			Project project = ProjectDB.getProject(projectName);
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateformat.format(project.getCreated());
			String description = project.getDescription();
			String category = project.getCategory().getDescription();
			Long budget = project.getBudget();
			LinkedList<Document> documents = (LinkedList<Document>) project.getDocuments();

			LinkedList<Evaluation> evaList = (LinkedList<Evaluation>) project.getEvaluations();
			int numberOfEvaluators = evaList.size();

			double[] buffer = new double[2]; // to save the result of risk level and attractiveness
			ProjectDB.calculateRiskLevelAndAttractiveness(evaList, buffer);
			double riskLevel = buffer[0];
			double attractiveness = buffer[1];

			request.setAttribute("projectName", projectName);
			request.setAttribute("date", date);
			request.setAttribute("description", description);
			request.setAttribute("category", category);
			request.setAttribute("budget", budget);
			request.setAttribute("numberOfEvaluators", numberOfEvaluators);

			StringBuilder docList = new StringBuilder();
			Iterator<Document> iter = documents.iterator();
			Document doc = null;
			while (iter.hasNext())
			{
				doc = iter.next();
				String serverPath = doc.getDocumentPath();
				Date today = new Date();
				Long deltT = (today.getTime() - doc.getAdded().getTime()) / (1000 * 60);
				if (deltT >= 5)
					docList.append("<a  class=\"h4\" href=\"" + serverPath + "\">" + "<span class=\"glyphicon glyphicon-paperclip\"></span> " + doc.getDocumentName() + "</a> <br>");
				else
					docList.append("<a class=\"h4\" href=\"" + serverPath + "\">" + "<span class=\"glyphicon glyphicon-paperclip\"></span> " + doc.getDocumentName()
							+ " <span class=\"label label-danger\">New</span></a> <br>");
			}

			if (doc != null)
				request.setAttribute("docList", docList.toString());

			HttpSession session = request.getSession();
			if (session.getAttribute("userType").equals("Owner"))
			{
				request.setAttribute("riskLevel", String.format("%.1f", riskLevel));
				request.setAttribute("attractiveness", String.format("%.1f", attractiveness));
				request.getRequestDispatcher("/pages/projectDetailsOwner.jsp").forward(request, response);
			} else if (session.getAttribute("userType").equals("Evaluator"))
				request.getRequestDispatcher("/pages/projectDetailsEvaluator.jsp").forward(request, response);

		} catch (DatabaseAccessError e)
		{
			e.printStackTrace();
		}

	}// end service
}
