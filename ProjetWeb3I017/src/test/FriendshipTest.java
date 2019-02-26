package test;

import org.json.JSONObject;

public class FriendshipTest {
	public static void main(String[] args){
		JSONObject json = services.FriendService.addFriends("collacoux", "cha");
		System.out.println(json.toString());
		
		//JSONObject json = services.FriendService.removeFriends("collacoux", "cha");
		//System.out.println(json.toString());
		
		
	}
	
}
