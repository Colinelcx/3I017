package test;

import java.sql.SQLException;

import org.json.JSONObject;

import services.MessageService;
import tools.AuthTools;
import tools.MessageTools;
import tools.UserTools;

public class MessageTest {

	public static void main(String[] args) {
		int user;
		try {
			user = UserTools.getUserID("collacoux");
			JSONObject json = MessageService.addMessage(AuthTools.getSessionKey(user), "test message");
			System.out.println(json.toString());
		} catch (SQLException e) {
			JSONObject json = tools.ServiceTools.ServiceRefused("Erreur de logins", 1000000);
			System.out.println(json.toString());
		}
		//System.out.println("Message de l'utilisateur " + user + " :");
		//System.out.println(MessageTools.getListmessages(user));
	}

}
