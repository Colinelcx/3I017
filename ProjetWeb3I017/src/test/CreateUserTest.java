package test;
import org.json.JSONObject;
import services.UserService;


public class CreateUserTest {
	public static void main(String[] args){
		JSONObject json = UserService.createUser("collacoux", "felten", "charel", "jgjh", "dggdfg");
		System.out.println(json.toString());
	} //test
}
