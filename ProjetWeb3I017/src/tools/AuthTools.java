package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AuthTools {
	
	// pas sur si login et logout vraiment ici, ou plutot dans le services
		
//	public static void login(String login, String password) throws SQLException {
//		
//		// a ce point on suppose que
//		// --- le user exists
//		// --- les arguments sont correctes
//		// --- le user n'est pas encore logged in
//		
//		// est-ce qu'on verifie dans cette fonction que les credentiels du user sont correctes ou est-ce qu'on peut deja les supposer?
//		
//		// decider si on appelle login avec les informations pour creer une entree dans la BD, ou si l'on appelle
//		// avec les infos pour login et password et recherche les autres infos et entre les valeurs dans la BD aussi, ou soit dans une autre fonction
//		
//		String key = UUID.randomUUID().toString();
//		// session id is key of length 32, we shorten it
//		key = key.substring(0, 31); // comme ca ca va dans notre key varchar(32), peut etre c'est mieux de aggrandir jusqu'a varchar(36) et eviter ca
//		
//		String query = "INSERT INTO Session (id_user, key_session, date_session, root)"
//				     + " VALUES (" + id + ",'" + key + "', " + date + ", " + root + ");";
//		
//		
//		// je crois a ce point on ne doit plus tester si les arguments sont correctes, on le fait avant qu'on appelle la fonction login?
//		
//		// use: tools.Usertools.userExists
//		
//		// ajouter nouvelle entree dans table des sessions
//	}
//	
//	public static void logout(String login, String password) throws SQLException {
//		
//	}
	
	public static boolean verifyUserConnected(int id) throws SQLException {
		
		if (getSessionKey(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	public static String getSessionKey(int id) throws SQLException {

		String query = "SELECT id_user from Session WHERE id_user=" + id + ";";
		Connection conn = tools.DataBaseTools.getConnection();
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
	
	
	public static String insertSession(int id, boolean root) throws SQLException {
		// verify if user is actually logged out and not in session table anymore
		// find out how timestamp works
		
		int rootInt = 0;
		if (root) {
			rootInt = 1;
		}
		
		String key = genKey();
		
		String query = "INSERT INTO Session VALUES (" + id + ",'" + key + "', NOW() , " + rootInt + ";)";
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return key;
	}
	
	

	
	public static boolean checkPassword(String login, String password) throws SQLException {
		
		int id =  tools.UserTools.getUserID(login);
		
		String query = "SELECT * from Session WHERE id_user=" + id + " AND password_user='" + password + "';";
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		boolean response = res.next();
		
		res.close();
		st.close();
		conn.close();
		
		return response;
		
		
	}
	
	public int getSessionID(String key) throws SQLException {
		
		String query = "SELECT id_user from Session WHERE key='" + key + "';";
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
		
		return id;
		//test
	}
}
