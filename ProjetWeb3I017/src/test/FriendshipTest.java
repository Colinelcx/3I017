package test;

import org.json.JSONObject;

public class FriendshipTest {
	public static void main(String[] args){
		//JSONObject json = services.FriendService.addFriends(7, 5); // il faut entrer des id
		//System.out.println(json.toString());
		
		JSONObject json = services.FriendService.removeFriends(7, 5); // il faut entrer des id
		System.out.println(json.toString());
		
		
	}
	
}
