package test;
import org.json.JSONObject;
import services.User;


public class CreateUserTest {
	public static void main(String args){
		JSONObject json = User.createUser("Lacoux", "Coline", "3670163", "ztfef");
		System.out.println(json.toString());
	} //test
}
