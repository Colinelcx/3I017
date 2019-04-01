package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import databases.MySQLTools;

public class AuthTools {
	
	/**
	 * Vérifie que le password correspond au bon utilisateur
	 * @param id : identifiant de l'utilisateur
	 * @param password : mot de passe à tester
	 * @return true si le mot de passe est le bon, false sinon
	 * @throws SQLException erreur requête SQL
	 */
	public static boolean checkPassword(int id, String password) throws SQLException {
				
		String query = "SELECT * from User WHERE id_user=" + id + " AND password_user='" + password + "';";
		int res = MySQLTools.executeQuery(query);
		return (res==1); // l'utilisateur est trouvé dans la base

	}
	
	/**
	 * Crée une nouvelle session pour l'utilisateur qui vient de se connecter
	 * @param id identifiant de l'utilisateur
	 * @return la clé de session venant d'être crée
	 * @throws SQLException
	 */
	public static String insertSession(int id, boolean root) throws SQLException {
		//Conversion du root en entier pour SQL
		int rootInt = 0;
		if (root)
			rootInt = 1;
		
		//Création de la clé de session
		String key = genKey();
		
		String query = "INSERT INTO Session VALUES (" + id + ",'" + key + "', NOW() , " + rootInt + ");";
		MySQLTools.executeUpdate(query);
		return key;
	}
	
	/** Supprime une session de la table Session
	 * @param key clé de session
	 * @throws SQLException 
	 */
	public static void removeSession(String key) throws SQLException {
		System.out.println("remove " + key);
		String query = "DELETE FROM Session WHERE key_session = '" + key + "';";
		int i = MySQLTools.executeUpdate(query);
		if (i==1)
			System.out.println("Session supprimée");
	}
	
	/**
	 * Vérifie que la session est bien présente dans la table Session
	 * et qu'elle est valide (date < date actuelle - 1h)
	 * Si oui, met à jour la date
	 * @param key clé de session
	 * @return true si la session est valide, false sinon
	 * @throws SQLException
	 */
	public static boolean checkSession(String key) throws SQLException {
		if (key == "")
			return false;
		String query = "SELECT date_session FROM Session WHERE key_session ='" + key + "' AND TIMESTAMPDIFF(MINUTE, date_session, NOW()) < 30;";
		int res = MySQLTools.executeQuery(query);
		if (!(res==1)) {
			System.out.println("Invalid session");;
			removeSession(key);
			return false;
		}
		else {
			query = "UPDATE Session SET date_session = NOW() WHERE key_session ='" + key + "' AND TIMESTAMPDIFF(MINUTE, date_session, NOW()) < 30;";
			MySQLTools.executeUpdate(query);
			return true;
		}
		
	}
	
	
	/**
	 * Génère une clé de session aléatoire
	 * @return la clé crée
	 * @throws SQLException
	 */
	private static String genKey() throws SQLException {
		String key = "";
		
		String query = "SELECT key_session from Session";
		List<String> listKeys = MySQLTools.executeQuery(query, "key_session");
		while (key =="" || listKeys.contains(key))
			key = UUID.randomUUID().toString();
		return key;
	}
	

	/**
	 * Renvoie la clé de session correspondant à un utilisateur dont l'identifiant est id
	 * @param id identitfiant de l'utilisateur
	 * @return la clé de session de l'utilisateur
	 * @throws SQLException
	 */
	public static String getSessionKey(int id) throws SQLException {

		String query = "SELECT key_session from Session WHERE id_user=" + id + ";";
		List<String> res = MySQLTools.executeQuery(query, "key_session");
		if (!(res.size() == 1))
			return "";
		return res.get(0);

	}
	
	/**
	 * Renvoie l'id de l'utilisateur qui détient la session de clé key
	 * @param key clé de la session
	 * @return l'identifiant de l'utilisateur
	 * @throws SQLException
	 */
	public static int getSessionID(String key) throws SQLException {
		
		String query = "SELECT id_user from Session WHERE key_session='" + key + "';";
		List<String> res = MySQLTools.executeQuery(query, "id_user");
		int id = -1;
		
		if (res.size()==1)
			id = Integer.parseInt(res.get(0));
		
		return id;
	}
}
