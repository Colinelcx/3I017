package databases;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBTools {
	
	public static MongoCollection<Document> getCollection(String collection) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("lacoux_felten");
		MongoCollection<Document> coll = db.getCollection(collection);
		return coll;
	}
}
