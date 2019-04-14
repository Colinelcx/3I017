package test;

import java.sql.SQLException;

import org.json.JSONObject;

import services.UserService;
import tools.AuthTools;
import tools.UserTools;

public class InfosUserTest {

	public static void main(String[] args) {
		int user;
		try {
			user = UserTools.getUserID("collacoux");
			JSONObject json = UserService.infosUser(AuthTools.getSessionKey(user), "collacoux");
			System.out.println(json.toString());

		} catch (SQLException e) {
			JSONObject json = tools.ServiceTools.ServiceRefused("Erreur de logins", 1000000);
			System.out.println(json.toString());
		}
	}

}
