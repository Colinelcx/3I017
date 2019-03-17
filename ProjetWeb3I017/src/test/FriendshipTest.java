package test;

import java.sql.SQLException;

import org.json.JSONObject;

import tools.AuthTools;
import tools.UserTools;

public class FriendshipTest {
	public static void main(String[] args){
		int user1;
		int user2;
		try {
			user1 = UserTools.getUserID("colinelcx");
			user2 = UserTools.getUserID("charelf");
			JSONObject json = services.FriendService.removeFriend(AuthTools.getSessionKey(user1), user2);
			System.out.println(json.toString());
		} catch (SQLException e) {
			JSONObject json = tools.ServiceTools.ServiceRefused("Erreur de logins", 1000000);
			System.out.println(json.toString());
		}
	}
	
}
