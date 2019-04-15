package tools;

import java.sql.SQLException;
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
		 
		String login = UserTools.getLogin(id_user);
		String nom = UserTools.getNom(id_user);
		String prenom = UserTools.getPrenom(id_user);

		query.append("id_user", id_user);
		query.append("login", login);
		query.append("nom", nom);
		query.append("prenom", prenom);
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
			json.append(message.get("_id").toString(), message.toJson());
		}
		return json;

	}
		
	public static JSONObject getRecentMessages(int[] id_friends) throws JSONException {
		
		JSONObject json = new JSONObject();
		MongoCollection<Document> coll = MongoDBTools.getCollection("messages");
		for (int id_f: id_friends) {
			FindIterable<Document> messages = coll.find(eq("id_user", id_f));
			MongoCursor<Document> cursor = messages.iterator();
	
			while (cursor.hasNext()){
				Document message = cursor.next();
				json.append(message.get("_id").toString(), message.toJson());
			}
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

}
