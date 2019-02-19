package test;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoTest {
	public static void main(String[] args){
		
		//normalement en ne met pas le strings, mais on les prend de DBStatic
		
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		 
		MongoDatabase db = mongo.getDatabase("lacoux_felten"); // nimporte quel nom on veut
		 
		MongoCollection<Document> coll = db.getCollection("test"); // nimporte quel nom on veut
		
		Document query = new Document();
		 
		query.append("id", 1);
		query.append("nom", "toto");
		
		coll.insertOne(query); // utiliser insertOne pour un document, insert many pour multiple documents
		 
		 /*
		  * si on l'execute, on recoit qqch comme ca:
		  * févr. 19, 2019 2:32:12 PM com.mongodb.diagnostics.logging.JULLogger log
		  * INFOS: Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[3, 2, 11]}, minWireVersion=0, maxWireVersion=4, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=null, roundTripTimeNanos=1034320}
		  * févr. 19, 2019 2:32:12 PM com.mongodb.diagnostics.logging.JULLogger log
		  * INFOS: Opened connection [connectionId{localValue:2, serverValue:11}] to localhost:27017
		  * 
		  * 
		  * ceci nous indique que tout a fonctionné
		  */
		
		
		//Document query2 = new Document();
		 
		//query2.append("id", 2);
		//query2.append("nom", "tata");
		
		//coll.insertOne(query2);
		
		MongoCursor<Document> cursor = coll.find(query).iterator();
		
		while (cursor.hasNext()) {
			Document o = cursor.next();
			System.out.println(o);
		}
		
		// ceci imprime ca: Document{{_id=5c6c13e27d471d785bf3a1cc, id=1, nom=toto}}
		// ca fonctionne donc
		 
		 
	}
}
