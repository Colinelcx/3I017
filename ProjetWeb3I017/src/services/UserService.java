package services;
import java.sql.SQLException;

import org.json.JSONException;
//
import org.json.JSONObject;

import tools.AuthTools;
import tools.ServiceTools;

public class UserService {
	
	public static JSONObject createUser(String nom, String prenom, String login, String password, String email){
		if (nom == null || prenom == null || login == null || password == null) {
			return tools.ServiceTools.ServiceRefused("Wrong arguments", 0);			
		}
		
		try {
			boolean is_user =tools.UserTools.userExists(login);
			
			if (is_user) return tools.ServiceTools.ServiceRefused("user already exists" + login, 1);
									
			tools.UserTools.insertUser(login, nom, prenom, password, email);
			
			//int id = tools.UserTools.getUserID(login);
				
					
			return ServiceTools.ServiceAccepted("create user OK");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return tools.ServiceTools.ServiceRefused("probleme sql" + login, 100);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return tools.ServiceTools.ServiceRefused("probleme JSON" + login, 1000);
		}
		
		
		
		
	}

}
