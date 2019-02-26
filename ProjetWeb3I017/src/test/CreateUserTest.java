package test;
import org.json.JSONObject;
import services.UserService;


public class CreateUserTest {
	public static void main(String[] args){
		JSONObject json = UserService.createUser("cha", "felten", "charel", "jgjh", "ddfg");
		System.out.println(json.toString());
	} //test
}
