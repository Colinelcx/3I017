package services;
import java.sql.SQLException;

import org.json.JSONException;
//
import org.json.JSONObject;

import tools.AuthTools;

public class UserService {
	
	public static JSONObject createUser(String nom, String prenom, String login, String password, String email){
		if (nom == null || prenom == null || login == null || password == null) {
			return tools.ServiceTools.ServiceRefused("Wrong arguments", 0);			
		}
		
		try {
			boolean is_user =tools.UserTools.userExists(login);
			
			if (is_user) return tools.ServiceTools.ServiceRefused("user already exists" + login, 1);
									
			tools.UserTools.insertUser(nom, prenom, login, password, email);
			
			JSONObject retour = new JSONObject();
			
			String key = AuthTools.insertSession(id_user, false);
			
			retour.put("key", key);
			
			return retour;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return tools.ServiceTools.ServiceRefused("probleme sql" + login, 100);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return tools.ServiceTools.ServiceRefused("probleme JSON" + login, 1000);
		}
		
		System.out.println("Création user : "+ nom + " " + prenom);
		
		return tools.ServiceTools.ServiceAccepted("ok");
		
		
	}

}
