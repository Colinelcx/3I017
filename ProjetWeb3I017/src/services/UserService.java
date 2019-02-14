package services;

import org.json.JSONObject;

public class UserService {
	
	public static JSONObject createUser(String nom, String prenom, String login, String password){
		if (nom == null || prenom == null || login == null || password == null) {
			return tools.ServiceTools.ServiceRefused("Wrong arguments", 0);			
		}
		
		// Verification user à compléter
		//insertion dans la base de données
		
		System.out.println("Création user : "+ nom + " " + prenom);
		
		return tools.ServiceTools.ServiceAccepted("ok");
		
		
	}
}
