package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthTools;

//test 

public class AuthService {
	
	public static JSONObject login(String login, String mdp) {
		
		if ((login == null) || (mdp == null)){
			return (tools.ServiceTools.ServiceRefused("Wrong arguments", 0));
		}
		
		try {
			boolean is_user=tools.UserTools.userExists(login);
			
			if (!is_user) return tools.ServiceTools.ServiceRefused("unknown user " + login, 1);
			
			boolean password_ok=tools.AuthTools.checkPassword(login, mdp);
			
			if (!password_ok) return tools.ServiceTools.ServiceRefused("bad password" + login, 2);
			
			int id_user = tools.UserTools.getUserID(login);
			
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
				
	}
	
public static JSONObject logout(String key) {
		
		if (key == null) {
			return (tools.ServiceTools.ServiceRefused("Wrong arguments", 0));
		}
		
		try {
			
			
			boolean is_connected = AuthTools.verifyUserConnected(id)
			boolean is_user=tools.UserTools.userExists(login);
			
			if (!is_user) return tools.ServiceTools.ServiceRefused("unknown user " + login, 1);
			
			boolean password_ok=tools.AuthTools.checkPassword(login, mdp);
			
			if (!password_ok) return tools.ServiceTools.ServiceRefused("bad password" + login, 2);
			
			int id_user = tools.UserTools.getUserID(login);
			
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
				
	}
}
