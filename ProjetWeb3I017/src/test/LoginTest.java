package test;
import org.json.JSONObject;

import services.AuthService;
import services.UserService;


public class LoginTest {
	public static void main(String[] args){
		JSONObject json = AuthService.login("collacoux", "3670163");
		System.out.println(json.toString());
	}
}
