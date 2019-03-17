package tools;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
		ResultSet res = MySQLTools.executeQuery(query);
		boolean response = res.next(); // faux si le resultat est vide, vrai sinon
		return response;

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
		
		String query = "INSERT INTO Session VALUES (" + id + ",'" + key + "', NOW() , " + rootInt + ";)";
		ResultSet res = MySQLTools.executeQuery(query);
		res.next();
		return res.getString("key_session");
	}
	
	/** Supprime une session de la table Session
	 * @param key clé de session
	 * @throws SQLException 
	 */
	public static void removeSession(String key) throws SQLException {

		String query = "DELETE FROM Session WHERE key_session = '" + key + "';";
		MySQLTools.executeUpdate(query);
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
		
		String query = "SELECT date_session FROM Session WHERE key_session ='" + key + "';";
		ResultSet res = MySQLTools.executeQuery(query);
		if (!res.next())
			return false;
		Date date = res.getDate("date_session");
		LocalDate local_date = LocalDate.now();
		Date limit_date = Date.valueOf(local_date);
		if (date.before(limit_date))
			return true;
		return false;
		
	}
	
	
	/**
	 * Génère une clé de session aléatoire
	 * @return la clé crée
	 * @throws SQLException
	 */
	private static String genKey() throws SQLException {
		ResultSet res;
		String key;
		do {
			key = UUID.randomUUID().toString();
			String query = "SELECT * from Session WHERE key_session='" + key + "';";
			res = MySQLTools.executeQuery(query);
		} while (res.next() == false);
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
		ResultSet res = MySQLTools.executeQuery(query);
		if (!res.next())
			return "";
		return res.getString("key");

	}
	
	/**
	 * Renvoie l'id de l'utilisateur qui détient la session de clé key
	 * @param key clé de la session
	 * @return l'identifiant de l'utilisateur
	 * @throws SQLException
	 */
	public static int getSessionID(String key) throws SQLException {
		
		String query = "SELECT id_user from Session WHERE key='" + key + "';";
		ResultSet res = MySQLTools.executeQuery(query);
		int id = -1;
		
		if (res.next())
			id = res.getInt("id_user");
		
		return id;
	}
}
