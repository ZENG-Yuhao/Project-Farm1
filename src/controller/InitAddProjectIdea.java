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

import model.Category;
import model.db.CategoryDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/InitAddProjectIdea")
public class InitAddProjectIdea extends HttpServlet
{

	private static final long serialVersionUID = -635878109577156722L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			LinkedList<Category> categories = (LinkedList<Category>) CategoryDB.getCategories();
			Iterator<Category> iter = categories.iterator();
			String arrCategories = "";
			while (iter.hasNext())
			{
				arrCategories += iter.next().getDescription() + ",";
			}
			request.setAttribute("arrCategories", arrCategories);
			RequestDispatcher disp = request.getRequestDispatcher("/pages/addProjectIdea.jsp");
			disp.forward(request, response);

		} catch (DatabaseAccessError e)
		{
			e.printStackTrace();
		}

	}// service

}
