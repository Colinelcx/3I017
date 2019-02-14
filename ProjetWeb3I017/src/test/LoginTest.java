package test;
import org.json.JSONObject;
import services.UserService;


public class LoginTest {
	public static void main(String[] args){
		JSONObject json = UserService.createUser("test", "Coline", "3670163", "ztfef");
		System.out.println(json.toString());
	}
}
