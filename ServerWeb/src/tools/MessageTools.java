package tools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import databases.MongoDBTools;

public class MessageTools {
	
	public static void addMessage(int id_user, String content) throws SQLException {
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		
		MongoCollection<Document> coll = MongoDBTools.getCollection("messages");
		
		Document query = new Document();
		 
		query.append("id_user", id_user);
		query.append("date", date);
		query.append("content", content);

		coll.insertOne(query);
		
	}
	
			
	public static void deleteMessage(int id_user, String id_message) {
		
		MongoCollection<Document> coll = MongoDBTools.getCollection("messages");
		Document query = new Document();	 
		query.append("id_user", id_user);
		Document message = coll.find(query).first();
		coll.deleteOne(message);
		
	}

	
	public static JSONObject getUserMessages(int id_user) throws JSONException {
		
		JSONObject json = new JSONObject();
		MongoCollection<Document> coll = MongoDBTools.getCollection("messages");
		FindIterable<Document> messages = coll.find(eq("id_user",id_user));
		MongoCursor<Document> cursor = messages.iterator();
		while (cursor.hasNext()){
			Document message = cursor.next();
			json.append(message.getInteger("id_user").toString(), message.toString());
		}
		return json;

	}
		
	public static JSONObject getRecentMessages(int[] id_friends) throws JSONException {
		
		JSONObject json = new JSONObject();
		MongoCollection<Document> coll = MongoDBTools.getCollection("messages");
		FindIterable<Document> messages = coll.find(in("_id", id_friends));
		MongoCursor<Document> cursor = messages.iterator();

		while (cursor.hasNext()){
			Document message = cursor.next();
			json.append(message.getInteger("id_user").toString(), message.toString());
		}
		return json;

		}
	
	
	public static void addComment(int id_user, String content) {
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		
		String login = null;
		try {
			login = tools.UserTools.getLogin(id_user);
		} catch (SQLException e) {
			login = "unidentified_user";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		//MongoCollection<Document> coll = MongoDBTools.getCollection("messages");
		
		//Document query = new Document("_id" /*get the id of the message and insert it here */);
		
		//MongoCursor<Document> cursor = coll.find(query).iterator();
		
		/*while (cursor.hasNext()) {
			Document o = cursor.next();
			System.out.println(o);
		}*/
		/* see if we can retrieve the original document */
		
		/*if (msg.hasNext()) {
			Document message = msg.next(); //should map the original message to this new Document message
			
			Document comment = new Document();
			
			comment.append("id_user", id_user);
			comment.append("login", login);
			comment.append("date", date);
			comment.append("content", content);
			
			// adding our comment to the existing list of commments
			
			ArrayList<Document> commentList = (ArrayList<Document>) message.get("commentList");
			
			commentList.add(comment);
			
			/* do we insert the modified list back into the original message? */
		//}
		
	//}
	
	


	public static JSONObject getListMessage(int id_user) {
		
		MongoCollection<Document> coll = MongoDBTools.getCollection("messages");
		
		Document query = new Document();
		JSONObject messageListJSON = new JSONObject();
		ArrayList<Document> messages = new ArrayList<Document>();
		
		query.append("id_user", id_user);
		
		MongoCursor<Document> cursor = coll.find(query).iterator();
		
		
		
		while (cursor.hasNext()) {
			//Document o = cursor.next();
			//System.out.println(o);
			
			Document message = cursor.next(); // not sure if we also get the comments this way
			messages.add(message);
			
		}
		
		try {
			messageListJSON.put("messages", messages);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return messageListJSON;
		
	}
	
	/*public static JSONObject listAllMessages() {
		
		// if above code works, reuse by doing a query on all messages
		
	}
	
	public static JSONObject listMessagesOfFriends(int id_user) {
	
		int[] friendIDList = FriendTools.iDFriendsList(id_user);
		
		int i;
		
		JSONObject friendMessageListJSON = new JSONObject();
		
		for (i=0; i<friendIDList.length; i++) {
			
			try {
				friendMessageListJSON.put("friendMessages", listMessageOfId(friendIDList[i]));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return friendMessageListJSON;
		
	}*/


}
