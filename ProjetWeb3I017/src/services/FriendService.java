package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthTools;
import tools.FriendTools;

public class FriendService {

public static JSONObject addFriends(String id_user1, String id_user1) {
	
	
		
		if ((id_user1 == null) || (mdp == null)){
			return (tools.ServiceTools.ServiceRefused("Wrong arguments", 0));
		}
		
		try {
			
			boolean is_user1 = tools.UserTools.userExists(login);
			
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
	if (areFriends(id_user1, id_user2)) {
		return;
	}
}
