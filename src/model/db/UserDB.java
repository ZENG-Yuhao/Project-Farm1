package model.db;

import java.util.LinkedHashMap;
import java.util.Map;

import model.Evaluator;
import model.Owner;
import model.User;
import model.db.exception.DatabaseAccessError;

public class UserDB {
	
	private static Map<String,User> users;
	
	static {
		users = new LinkedHashMap<String, User>();
		initializeUsersList();
	}

	private static void initializeUsersList() {
		users.put("john@acme.com",new Owner("john@acme.com","John Silver","123"));
		users.put("mary@acme.com",new Owner("mary@acme.com","Mary Moon","123"));
		users.put("paul@acme.com",new Owner("paul@acme.com","Paul McDonalds","123"));
		users.put("enzo", new Owner("enzo", "Enzo", "123"));
		users.put("sarah@geek.com",new Evaluator("sarah@geek.com","Sarah Logan","456"));
		users.put("thibault@geek.com",new Evaluator("thibault@geek.com","Thibault Moulin","456"));
		users.put("george@geek.com",new Evaluator("george@geek.com","George Papalodeminus","456"));
		users.put("enzo1", new Evaluator("enzo1", "Enzo Eval", "123"));
		users.put("enzo2", new Evaluator("enzo2", "Enzo Eval2", "123"));
		users.put("enzo3", new Evaluator("enzo3", "Enzo Eval3", "123"));
		users.put("enzo4", new Evaluator("enzo4", "Enzo Eval4", "123"));
		users.put("enzo5", new Evaluator("enzo5", "Enzo Eval5", "123"));
	}
	
	public static boolean checkLogin(String login,String password) throws DatabaseAccessError{
		User u = users.get(login);
		if(u == null) 
			return false;
		return u.getPassword().equals(password);
	}
	
	public static User getUser(String login) throws DatabaseAccessError {
		User u = getOwner(login);
		if(u == null) {
			u = getEvaluator(login);
		}
		return u;
	}
	
	public static Owner getOwner(String login) throws DatabaseAccessError{
		User u = users.get(login);
		if(u == null || !(u instanceof Owner))
			return null;
		return (Owner) u;
	}
	
	public static Evaluator getEvaluator(String login) throws DatabaseAccessError {
		User u = users.get(login);
		if(u == null || !(u instanceof Evaluator))
			return null;
		return (Evaluator) u;		
	}
}
