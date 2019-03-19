package test;
import org.json.JSONObject;
import services.UserService;


public class CreateUserTest {
	public static void main(String[] args){
		UserService.createUser("collacoux", "felten", "charel", "jgjh", "hudfg");
		JSONObject json = UserService.createUser("collacoux2", "felten", "charel", "jgjh", "dgghudfg");
		System.out.println(json.toString());
	} //test
}
