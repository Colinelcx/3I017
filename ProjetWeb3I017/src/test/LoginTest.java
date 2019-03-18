package test;
import org.json.JSONObject;

import services.AuthService;


public class LoginTest {
	public static void main(String[] args){
		
		JSONObject json = AuthService.login("collacoux", "jgjh");
		System.out.println(json.toString());
	}
}
