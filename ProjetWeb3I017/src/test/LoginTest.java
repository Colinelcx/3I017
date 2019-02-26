package test;
import org.json.JSONObject;
import services.UserService;


public class LoginTest {
	public static void main(String[] args){
		JSONObject json = UserService.login("Coline", "3670163");
		System.out.println(json.toString());
	}
}
