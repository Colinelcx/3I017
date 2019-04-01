package test;

import java.sql.SQLException;

import org.json.JSONObject;

import services.FriendService;
import tools.AuthTools;
import tools.UserTools;

public class FriendshipTest {
	public static void main(String[] args){
		int user1;
		int user2;
		try {
			user1 = UserTools.getUserID("collacoux");
			user2 = UserTools.getUserID("collacoux2");
			FriendService.addFriend(AuthTools.getSessionKey(user1), user2);
			FriendService.removeFriend(AuthTools.getSessionKey(user1), user2);
			JSONObject json = FriendService.addFriend(AuthTools.getSessionKey(user1), user2);
			System.out.println(json.toString());
		} catch (SQLException e) {
			JSONObject json = tools.ServiceTools.ServiceRefused("Erreur de logins", 1000000);
			System.out.println(json.toString());
		}
	}
	
}
