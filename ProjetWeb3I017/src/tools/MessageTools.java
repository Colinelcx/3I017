package tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MessageTools {
	
	public static void addMessage(int user_id, String message) {
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		
		MongoCollection<Document> coll = tools.DataBaseTools.getMongoCollection("messages");
		
		Document query = new Document();
		
		String login = null;
		try {
			login = tools.UserTools.getLogin(user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		query.append("user_id", user_id);
		query.append("login", login);
		query.append("date", date);
		query.append("content", message);

		coll.insertOne(query);
		
	}
	
	public static void deleteMessage(int user_id, String message) {
		
		MongoCollection<Document> coll = tools.DataBaseTools.getMongoCollection("messages");
		
		Document query = new Document();
		 
		query.append("id", id);
		query.append("content", message);
		
		coll.insertOne(query);
	}

	public static JSONObject getMessage(String message_id) {
		
		MongoCollection<Document> coll = tools.DataBaseTools.getMongoCollection("messages");
		
		Document query = new Document();
		 
		query.append("id", 2);
		query.append("nom", "tata");
		
		MongoCursor<Document> cursor = coll.find(query).iterator();
		
		while (cursor.hasNext()) {
			Document o = cursor.next();
			System.out.println(o);
		}
		
		
		
	}


}
