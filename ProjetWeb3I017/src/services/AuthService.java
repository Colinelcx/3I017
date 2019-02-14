package services;

import java.sql.SQLException; 

import org.json.JSONObject;

public class AuthService {
	public static JSONObject login(String login, String mdp) {
		if ((login == null) || (mdp == null)){
			return (tools.ServiceTools.ServiceRefused("Wrong arguments", 0));
		}
		boolean is_user=true;
		try {
			is_user = tools.UserTools.userExists(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!is_user) {
			return (tools.ServiceTools.ServiceRefused("User doesn't exists", 1));
		}
		String UserID = tools.UserTools.getUserID(login);
		//String key = ;
		return null;
		
	}
}
