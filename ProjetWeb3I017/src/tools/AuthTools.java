package tools;

import java.sql.SQLException;

public class AuthTools {
	
	// c'est quoi la differnce entre les AuthTools et les Usertools?
	
	public static void login(String login, String password) throws SQLException {
		
		// je crois a ce point on ne doit plus tester si les arguments sont correctes, on le fait avant qu'on appelle la fonction login?
		
		// use: tools.Usertools.userExists
		
		// ajouter nouvelle entree dans table des sessions
	}
	
	public static void logout(String login, String password) throws SQLException {
		
	}
	
}
