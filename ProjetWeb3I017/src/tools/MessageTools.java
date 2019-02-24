package tools;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MessageTools {
	
	public static void addMessage(int user_id, String message) {
		
		MongoCollection<Document> coll = tools.DataBaseTools.getMongoCollection("messages");
		
		Document query = new Document();
		 
		query.append("user_id", user_id);
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
