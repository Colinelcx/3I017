package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.Statement;

public class UserTools {
	private static int compt =0;

	public static boolean userExists(String login) throws SQLException {
		
		String query = "SELECT user_id from User WHERE login_user='" + login + "';"; // il faut peut etre rendre ces queries SQL injection attack secure
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		boolean response = res.next();
		
		res.close(); // est-ce que c'est vraiment necessaire de fermer res?????
		st.close();
		conn.close();
		
		return response;
	}
	
	public static boolean userExists(int id) throws SQLException {
		
		String query = "SELECT user_id from User WHERE user_id=" + login + ";"; // il faut peut etre rendre ces queries SQL injection attack secure
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
		
		String query = "SELECT user_id from User WHERE login_user='" + login + "';";
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
	
	public static void insertUser(String login, String nom, String prenom, String password, String email) throws SQLException {
		int id = createID();
		String query = "INSERT INTO User (user_id, login_user, first_name_user, family_name_user, password_user, mail_user)"
				+ "VALUES (" + id + ", '" + login + "', '" + nom + "', '" + prenom + "', '" + password + "', '" + email + "');";
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;

	}
	
	public static String getLogin(int user_id) throws SQLException {
		
		String query = "SELECT login_user from User WHERE id_user=" + user_id + ";";
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		String login = null;
		
		while (res.next()) {
			login = res.getString("login_user");
		}
		
		res.close();
		st.close();
		conn.close();
		
		
		return login; // si retour null, alors il y avait une erreur

	}

	private static int createID() {
		return compt++;
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
