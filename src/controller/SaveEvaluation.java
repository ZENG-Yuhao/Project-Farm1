package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evaluation;
import model.Evaluator;
import model.Project;
import model.db.ProjectDB;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

/**
 * Servlet implementation class SaveEvaluation
 */
@WebServlet("/SaveEvaluation")
public class SaveEvaluation extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8585216472872781938L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String projectName = request.getParameter("projectName");
			Project currentProject = ProjectDB.getProject(projectName);
			String login = (String) request.getSession().getAttribute("userId");
			Evaluator currentEvaluator = UserDB.getEvaluator(login);
			int riskLevel = Integer.valueOf(request.getParameter("riskLevel"));
			int attractiveness = Integer.valueOf(request.getParameter("attractiveness"));
			Evaluation evaluation = new Evaluation(currentEvaluator, attractiveness, riskLevel);
			currentProject.addEvaluation(evaluation);
			// request.getRequestDispatcher("/pages/loggedIn.jsp").forward(request, response);
			request.getRequestDispatcher("/pages/ok.jsp").forward(request, response);
		} catch (DatabaseAccessError | InvalidDataException | IOException e)
		{
			e.printStackTrace();
			request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
		}
	}// service
}
