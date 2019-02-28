package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthTools;
import tools.FriendTools;
import tools.ServiceTools;

public class FriendService {

	public static JSONObject addFriends(int id_user1, int id_user2) {
	
		
		
		/*if ((id_user1 == null) || (id_user2 == null)){
			return (tools.ServiceTools.ServiceRefused("Wrong arguments", 0));
		}
		*/
		try {
			
			//int id_user1 = tools.UserTools.getUserID(login1);
			//int id_user2 = tools.UserTools.getUserID(login2);
			
			
			boolean is_user1 = tools.UserTools.userExists(id_user1);
			boolean is_user2 = tools.UserTools.userExists(id_user2);
			
			if (!is_user1) return tools.ServiceTools.ServiceRefused("unknown user " + id_user1, 1);
			if (!is_user2) return tools.ServiceTools.ServiceRefused("unknown user " + id_user2, 1);
			
			tools.FriendTools.insertFriendship(id_user1, id_user2);
			
			
			return ServiceTools.ServiceAccepted("friendship created");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return tools.ServiceTools.ServiceRefused("probleme sql frienship", 100);
		}
				
	}

	public static JSONObject removeFriends(int id_user1, int id_user2) {
	

		try {
			
			//int id_user1 = tools.UserTools.getUserID(login1);
			//int id_user2 = tools.UserTools.getUserID(login2);
			
			boolean is_user1 = tools.UserTools.userExists(id_user1);
			boolean is_user2 = tools.UserTools.userExists(id_user2);
			
			if (!is_user1) return tools.ServiceTools.ServiceRefused("unknown user " + id_user1, 1);
			if (!is_user2) return tools.ServiceTools.ServiceRefused("unknown user " + id_user2, 1);
			
			boolean friends = tools.FriendTools.areFriends(id_user1, id_user2);
			
			if (!friends) return tools.ServiceTools.ServiceRefused("already friends ", 1);
			
			tools.FriendTools.removeFriendship(id_user1, id_user2);
			
			
			return ServiceTools.ServiceAccepted("friendship removed");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return tools.ServiceTools.ServiceRefused("probleme sql frienship", 100);
		}
			
	}
}
