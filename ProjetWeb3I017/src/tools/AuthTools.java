package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import databases.MySQLTools;

public class AuthTools {
	

	public static boolean verifyUserConnected(int id) throws SQLException {
		
		if (getSessionKey(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	public static String getSessionKey(int id) throws SQLException {

		String query = "SELECT id_user from Session WHERE id_user=" + id + ";";
		Connection conn = databases.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		String key = null;
		
		while (res.next()) {
			key = res.getString("key");
		}
		
		res.close();
		st.close();
		conn.close();
		
		return key;
	}
	
	public static String genKey() {
		String key = UUID.randomUUID().toString();
		// session id is key of length 32, we shorten it
		//key = key.substring(0, 31);
		// verify that key does not exist
		return key;
	}
	
	
	/**
	 * @param id
	 * @param root
	 * @return
	 * @throws SQLException
	 */
	public static String insertSession(int id, boolean root) throws SQLException {
		
		// verify if user is actually logged out and not in session table anymore
	
		int rootInt = 0;
		if (root) {
			rootInt = 1;
		}
		String key = genKey();
		String query = "INSERT INTO Session VALUES (" + id + ",'" + key + "', NOW() , " + rootInt + ";)";

		ResultSet res = MySQLTools.executeQuery(query);
		res.next();
		return res.getString("key_session");
	}
	
	public static void removeSession(String key) throws SQLException {

		String query = "DELETE FROM Session WHERE key_session = '" + key + "';";
		
		Connection conn = databases.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;		
	}
	
	

	
	/**
	 * Vérifie que le password correspond au bon utilisateur
	 * @param id : identifiant de l'utilisateur
	 * @param password : mot de passe à tester
	 * @return true si le mot de passe est le bon, false sinon
	 * @throws SQLException erreur requête SQL
	 */
	public static boolean checkPassword(int id, String password) throws SQLException {
				
		String query = "SELECT * from User WHERE id_user=" + id + " AND password_user='" + password + "';";
		ResultSet res = MySQLTools.executeQuery(query);
		boolean response = res.next();
		return response;

	}
	
	public static int getSessionID(String key) throws SQLException {
		
		String query = "SELECT id_user from Session WHERE key='" + key + "';";
		Connection conn = databases.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		int id = -1;
		
		while (res.next()) {
			id = res.getInt("id_user");
		}
		
		res.close();
		st.close();
		conn.close();
		
		return id;
	}
	
	public static boolean checkSession(String key) throws SQLException {
		
		int id = getSessionID(key);
		
		if (id == -1) return false;
				
		return true;		
		
	}
}
