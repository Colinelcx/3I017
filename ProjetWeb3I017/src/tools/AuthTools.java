package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AuthTools {
		
	public static void login(String login, String password) throws SQLException {
		
		// a ce point on suppose que
		// --- le user exists
		// --- les arguments sont correctes
		// --- le user n'est pas encore logged in
		
		// est-ce qu'on verifie dans cette fonction que les credentiels du user sont correctes ou est-ce qu'on peut deja les supposer?
		
		// decider si on appelle login avec les informations pour creer une entree dans la BD, ou si l'on appelle
		// avec les infos pour login et password et recherche les autres infos et entre les valeurs dans la BD aussi, ou soit dans une autre fonction
		
		String key = UUID.randomUUID().toString();
		// session id is key of length 32, we shorten it
		key = key.substring(0, 31); // comme ca ca va dans notre key varchar(32), peut etre c'est mieux de aggrandir jusqu'a varchar(36) et eviter ca
		
		String query = "INSERT INTO Session (id_user, key_session, date_session, root)"
				     + " VALUES (" + id + ",'" + key + "', " + date + ", " + root + ");";
		
		
		// je crois a ce point on ne doit plus tester si les arguments sont correctes, on le fait avant qu'on appelle la fonction login?
		
		// use: tools.Usertools.userExists
		
		// ajouter nouvelle entree dans table des sessions
	}
	
	public static void logout(String login, String password) throws SQLException {
		
	}
	
	https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse
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
		//test
		
		return key;
	}
	
}
