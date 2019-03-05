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
		
		Document comments = new Document();
		 
		query.append("id_user", id_user);
		query.append("login", login);
		query.append("date", date);
		query.append("content", content);
		query.append("comments", comments);

		coll.insertOne(query);
		
	}
	
	
	// look how to do this... right now it is just a copy paste of the message code
	/*public static void addComment(int id_user, String content) {
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		
		MongoCollection<Document> coll = tools.DataBaseTools.getMongoCollection("messages");
		
		Document query = new Document();
		
		String login = null;
		try {
			login = tools.UserTools.getLogin(id_user);
		} catch (SQLException e) {
			login = "unidentified_user";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document comments = new Document();
		 
		query.append("id_user", id_user);
		query.append("login", login);
		query.append("date", date);
		query.append("content", message);
		query.append("comments", comments);

		coll.insertOne(query);
		
	}*/
	
	
	
	
	
	public static void deleteMessage(int id_user, String id_) {
		
		// verify that the user has the right to delete this message
		
		MongoCollection<Document> coll = databases.DataBaseTools.getMongoCollection("messages");
			
		Document query = new Document();
		 
		query.append("id_", id_);
		
		MongoCursor<Document> cursor = coll.find(query).iterator();
		// supprimer le message, je ne sais pas comment
		
	}

	public static JSONObject getMessage(String message_id) {
		
		MongoCollection<Document> coll = databases.DataBaseTools.getMongoCollection("messages");
		
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
