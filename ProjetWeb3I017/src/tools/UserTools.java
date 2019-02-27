package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.Statement;

public class UserTools {

	public static boolean userExists(String login) throws SQLException {
		
		String query = "SELECT id_user from User WHERE login_user='" + login + "';"; // il faut peut etre rendre ces queries SQL injection attack secure
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
		
		String query = "SELECT id_user from User WHERE id_user=" + id + ";"; // il faut peut etre rendre ces queries SQL injection attack secure
		
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
		
		String query = "SELECT id_user from User WHERE login_user='" + login + "';";
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		int id = -1;
		
		while (res.next()) {
			id = res.getInt("id_user");
		}
		
		res.close();
		st.close();
		conn.close();
		
		
		return id; // si retour -1, alors il y avait une erreur

	}
	
	public static void insertUser(String login, String nom, String prenom, String password, String email) throws SQLException {
		String query = "INSERT INTO User (login_user, first_name_user, family_name_user, password_user, mail_user)"
				+ "VALUES ('" + login + "', '" + nom + "', '" + prenom + "', '" + password + "', '" + email + "');";
		// on n'a pas besoin de mettre id_user comme c'est auto increment
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;

	}
	
	public static String getLogin(int id) throws SQLException {
		
		String query = "SELECT login_user from User WHERE id_user=" + id + ";";
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
}
