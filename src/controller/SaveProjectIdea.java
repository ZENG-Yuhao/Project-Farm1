package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Owner;
import model.Project;
import model.db.CategoryDB;
import model.db.ProjectDB;
import model.db.UserDB;

@WebServlet("/SaveProjectIdea")
public class SaveProjectIdea extends HttpServlet
{

	private static final long serialVersionUID = -3832720774369006078L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String acronym = request.getParameter("title");
			String description = request.getParameter("description");
			Long budget = Long.valueOf(request.getParameter("budget"));
			Owner owner = UserDB.getOwner((String) request.getSession().getAttribute("userId"));
			Category category = CategoryDB.getCategory(request.getParameter("category"));
			Project project = new Project(acronym, description, 100, budget, owner, category);
			project.setCreated(new Date());
			ProjectDB.saveProject(project);
			// request.getRequestDispatcher("/ShowMyProjects").forward(request, response);
			request.getRequestDispatcher("/pages/ok.jsp").forward(request, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
		}

	}// service

}
