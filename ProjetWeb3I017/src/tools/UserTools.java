package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTools {

	public static boolean userExists(String login) throws SQLException {
		// probleme: il faut changer le String login en int id, comme c'est notre primary key, ou non?
		String query = "SELECT user_id from User WHERE user_id='" + login + "';";
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		//System.out.println(res);
		
		while (res.next()) {
			String id = res.getString("user_id");
		}
		
		st.close();
		conn.close();
		return true; // return true or false if we could finf the login in the result set
	}

	public static String getUserID(String login) throws SQLException {
		// il faut le transformer en int ou non?
		
		String query = "SELECT user_id from User WHERE user_id='" + login + "';";
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		
		
		while (res.next()) {
			int id = res.getInt("user_id");
		}
		// normalement on devrait recevoir une seul ligne, contenant une seul column, let user_id
		
		res.close();
		st.close();
		conn.close();
		return id;
		
		
	}
	
	public static boolean verifyUserConnected(String login) throws SQLException {
		
		String query = "SELECT user_id from Session WHERE user_id='" + login + "';";
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		while (res.next()) {
			String id = res.getString("user_id");
		}
		
		res.close();
		st.close();
		conn.close();
		return true; // return true or false if the string id has been assigned a value, meaning the user_id is in the session table
	}
	
	

}









//package tools;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserTools {
//
//	public static boolean userExists(String login) throws SQLException {
//		String query = "SELECT user_id from User WHERE user_id='" + login + "';";
//		ResultSet res = tools.DataBaseTools.executeRequete(query);
//		System.out.println(res);
//		return true;
//	}
//
//	public static String getUserID(String login) throws SQLException {
//		String query = "SELECT user_id from User WHERE user_id='" + login + "';";
//		ResultSet res = tools.DataBaseTools.executeRequete(query);
//		// extract user id string from ResultSet
//		return "string";
//	}
//	
//	public static boolean verifyUserConnected(String login) throws SQLexception {
//		String query = "SELECT user_id from User WHERE user_id='" + login + "';";
//	}
//	
//	
//
//}
