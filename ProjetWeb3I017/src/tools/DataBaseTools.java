package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


//public class DataBaseTools {
//	public static ResultSet executeRequete(String query) throws SQLException {
//		try {
//			
//			Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
//			// � quoi sert le .newInstance()? eclipse dit que la m�thode est "depreceated"
//			String url = "jdbc:mysql://localhost/lacoux_felten";
//			Connection conn;
//			conn = DriverManager.getConnection(url, "root", "root");
//			Statement st;
//			st = conn.createStatement();
//			return st.executeQuery(query);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//}

public class DataBaseTools {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // pas besoin d'utiliser newInstance
			String url = "jdbc:mysql://localhost/lacoux_felten";
			Connection conn;
			conn = DriverManager.getConnection(url, "root", "root");
			return conn;
//			Statement st;
//			st = conn.createStatement();
//			return st.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static MongoCollection<Document> getMongoCollection(String collection) {
		
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
	 
		MongoDatabase db = mongo.getDatabase("lacoux_felten");
		
		MongoCollection<Document> coll = db.getCollection(collection);
		
		return coll;
	}
}
