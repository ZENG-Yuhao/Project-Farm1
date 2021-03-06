package model.db;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Evaluation;
import model.Owner;
import model.Project;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {

	private static Map<String, Project> projects;

	static {
		projects = new LinkedHashMap<String, Project>();
	}

	public static void saveProject(Project project) throws DatabaseAccessError {
		projects.put(project.getAcronym(), project);
	}

	public static Project getProject(String acronym) throws DatabaseAccessError {
		return projects.get(acronym);
	}

	public static List<Project> getProjectsOfOwner(Owner owner) throws DatabaseAccessError {

		List<Project> projectsOfOwner = new LinkedList<Project>();

		for (Project p : projects.values()) {
			if (p.getOwner().equals(owner)) {
				projectsOfOwner.add(p);
			}
		}
		return projectsOfOwner;

	}
	
	public static List<Project> getAllProjects() throws DatabaseAccessError {
		return new LinkedList<Project>(projects.values());
	}
	
	public static void calculateRiskLevelAndAttractiveness(LinkedList<Evaluation> evaList, double[] buffer) {
		Iterator<Evaluation> iter = evaList.iterator();
		double riskLevel = 0.0, attractiveness = 0.0;
		int sum = evaList.size();
        if (sum==0) sum=1;
		while (iter.hasNext()) {
			Evaluation eva = iter.next();
			riskLevel += eva.getRiskLevel();
			attractiveness += eva.getAttractiveness();
		}
		buffer[0] = riskLevel / sum;
		buffer[1] = attractiveness / sum;
	}
}
