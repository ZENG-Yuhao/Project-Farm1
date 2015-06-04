package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Evaluation;
import model.Evaluator;
import model.Owner;
import model.Project;
import model.User;
import model.db.CategoryDB;
import model.db.ProjectDB;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = -1731274764063357773L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String userid = request.getParameter("userID");
			Boolean loginState = UserDB.checkLogin(userid, request.getParameter("password"));
			HttpSession session = request.getSession(true);
			User user = UserDB.getUser(userid);
			String userType = (UserDB.getOwner(userid) != null) ? "Owner" : "Evaluator";
			session.setAttribute("userType", userType);
			session.setAttribute("loginState", loginState);
			session.setAttribute("pageSuccess", request.getParameter("pageSuccess"));
			if (userType.equals("Owner"))
				initConfigurations();
			if (loginState)
			{
				session.setAttribute("userId", userid);
				session.setAttribute("name", user.getName());
				// response.sendRedirect(request.getContextPath()+"/pages/loggedIn.jsp");
				request.getRequestDispatcher("/pages/loggedIn.jsp").forward(request, response);

			} else
			{
				RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
				disp.forward(request, response);
			}

		} catch (DatabaseAccessError e)
		{
			e.printStackTrace();
		}
	}

	public void initConfigurations()
	{
		try
		{
			Evaluator evaluator1 = UserDB.getEvaluator("sarah@geek.com");
			Evaluator evaluator2 = UserDB.getEvaluator("thibault@geek.com");
			Evaluator evaluator3 = UserDB.getEvaluator("george@geek.com");
			Evaluator evaluator4 = UserDB.getEvaluator("enzo4");
			Evaluator evaluator5 = UserDB.getEvaluator("enzo5");
			Evaluation eva1 = new Evaluation(evaluator1, 1, 5);
			Evaluation eva2 = new Evaluation(evaluator2, 2, 4);
			Evaluation eva3 = new Evaluation(evaluator3, 3, 3);
			Evaluation eva4 = new Evaluation(evaluator4, 4, 2);
			Evaluation eva5 = new Evaluation(evaluator5, 5, 5);
			// Owner owner = UserDB.getOwner("enzo");
			Owner owner = UserDB.getOwner("john@acme.com");
			Project p = new Project(
					"ProjectFarm",
					"this is an exemple of test this is an exemple of test this is an exemple of test this is an exemple of test this is an exemple of test this is an exemple of test this is an exemple of test this is an exemple of test",
					100, 300000L, owner, CategoryDB.getCategory("Information Systems"));
			Project p1 = new Project("ProjectFarm1", "this is an exemple of test", 100, 300000L, owner, CategoryDB.getCategory("Apps"));
			Project p2 = new Project("ProjectFarm2", "this is an exemple of test", 100, 300000L, owner, CategoryDB.getCategory("Hardware"));
			Project p3 = new Project("ProjectFarm3", "this is an exemple of test", 100, 300000L, UserDB.getOwner("mary@acme.com"), CategoryDB.getCategory("Robotics"));

			p.setCreated(new Date());
			p1.setCreated(new Date());
			p2.setCreated(new Date());
			p3.setCreated(new Date());

			p.addEvaluation(eva1);
			p.addEvaluation(eva2);
			p.addEvaluation(eva3);
			p.addEvaluation(eva4);
			p.addEvaluation(eva5);

			p1.addEvaluation(eva1);
			p1.addEvaluation(eva2);

			p2.addEvaluation(eva3);
			p2.addEvaluation(eva4);
			p2.addEvaluation(eva5);

			p3.addEvaluation(eva4);
			p3.addEvaluation(eva5);

			ProjectDB.saveProject(p);
			ProjectDB.saveProject(p1);
			ProjectDB.saveProject(p2);
			ProjectDB.saveProject(p3);
		} catch (DatabaseAccessError | InvalidDataException e)
		{
			e.printStackTrace();
		}

	}
}
