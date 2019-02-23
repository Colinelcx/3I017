package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.Statement;

public class UserTools {

	public static boolean userExists(String login) throws SQLException {
		
		String query = "SELECT user_id from User WHERE user_id='" + login + "';"; // il faut peut etre rendre ces queries SQL injection attack secure
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		boolean response = res.next();
		
		res.close(); // est-ce que c'est vraiment necessaire de fermer res?????
		st.close();
		conn.close();
		
		return response;
	}

	public static int getUserID(String login) throws SQLException {
		
		String query = "SELECT user_id from User WHERE user_id='" + login + "';";
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		int id = -1;
		
		while (res.next()) {
			id = res.getInt("user_id");
		}
		
		res.close();
		st.close();
		conn.close();
		
		
		return id; // si retour -1, alors il y avait une erreur

	}
	
	public static void insertUser(String id, String login, String nom, String prenom, String password, String email) throws SQLException {
		
		String query = "INSERT INTO User (id_user, login_user, first_name_user, family_name_user, password_user, mail_user)"
				+ "VALUES ('" + id + "', '" + login + "', '" + nom + "', '" + prenom + "', '" + password + "', '" + email + "');";
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;

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
