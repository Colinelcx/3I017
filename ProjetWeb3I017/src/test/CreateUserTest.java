package test;
import org.json.JSONObject;
import services.UserService;


public class CreateUserTest {
	public static void main(String args){
		JSONObject json = UserService.createUser("Lacoux", "Coline", "3670163", "ztfef");
		System.out.println(json.toString());
	} //test
}
