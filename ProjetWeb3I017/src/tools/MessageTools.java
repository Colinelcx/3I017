package tools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MessageTools {
	
	public static void addMessage(int id_user, String content) {
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		
		MongoCollection<Document> coll = databases.DataBaseTools.getMongoCollection("messages");
		
		Document query = new Document();
		
		String login = null;
		try {
			login = tools.UserTools.getLogin(id_user);
		} catch (SQLException e) {
			login = "unidentified_user";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Document> commentList = new ArrayList<Document>();
		 
		query.append("id_user", id_user);
		query.append("login", login);
		query.append("date", date);
		query.append("content", content);
		query.append("comments", commentList);

		coll.insertOne(query);
		
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
		
		
		
		MongoCollection<Document> coll = databases.DataBaseTools.getMongoCollection("messages");
		
		Document query = new Document("_id", /*get the id of the message and insert it here */);
		
		MongoCursor<Document> cursor = coll.find(query).iterator();
		
		/*while (cursor.hasNext()) {
			Document o = cursor.next();
			System.out.println(o);
		}*/
		/* see if we can retrieve the original document */
		
		if (msg.hasNext()) {
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
		}
		
	}
	
	
	
	
	
	public static void deleteMessage(int id_user, String id_) {
		
		// verify that the user has the right to delete this message
		
		MongoCollection<Document> coll = databases.DataBaseTools.getMongoCollection("messages");
			
		Document query = new Document();
		 
		query.append("id_", id_);
		
		MongoCursor<Document> cursor = coll.find(query).iterator();
		// supprimer le message, je ne sais pas comment
		
	}

	public static JSONObject listMessageOfId(int id_user) {
		
		MongoCollection<Document> coll = databases.DataBaseTools.getMongoCollection("messages");
		
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
	
	public static JSONObject listAllMessages() {
		
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
		
	}


}
