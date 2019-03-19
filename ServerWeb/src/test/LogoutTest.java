package test;
import java.sql.SQLException;

import org.json.JSONObject;

import services.AuthService;
import tools.AuthTools;
import tools.UserTools;


public class LogoutTest {
	public static void main(String[] args){

		try {
			JSONObject json = AuthService.logout(AuthTools.getSessionKey(UserTools.getUserID("collacoux")));
			System.out.println(json.toString());
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
