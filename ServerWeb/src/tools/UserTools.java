package tools;

import java.sql.SQLException;
import java.util.List;

import databases.MySQLTools;

public class UserTools {

	/**
	 * Vérifie si l'utilisateur est présent dans la base
	 * @param id identifiant de l' utilisateur
	 * @return true si l'utilisateur existe dans la base de bonnées, false sinon
	 * @throws SQLException erreur requête SQL
	 */
	public static boolean userExists(int id) throws SQLException {

		String query = "SELECT * from User WHERE id_user=" + id + ";";
		int res = MySQLTools.executeQuery(query);
		return res==1;
	}
	 
	/**
	 * Vérifie si l'utilisateur est présent dans la base
	 * @param login nom d'utilisateur
	 * @return true si l'utilisateur existe dans la base de bonnées, false sinon
	 * @throws SQLException erreur requête SQL
	 */
	public static boolean userExists(String login) throws SQLException {	
		int id = getUserID(login);
		return userExists(id);	
	}
	
	/**
	 * Vérifie si l'adresse mail est déjà utilisée par un utilisateur
	 * @param mail adresse mail
	 * @return true si l'adresse mail est déjà utilisée, false sinon
	 * @throws SQLException
	 */
	public static boolean mailExists(String mail) throws SQLException {
		String query = "SELECT * from User WHERE mail_user='" + mail + "';"; 
		int res = MySQLTools.executeQuery(query);
		return res==1;
	}

	/**
	 * Retourne l'id du login en argument
	 * @param login 
	 * @return l'identifiant correspondant au login passé en argument, -1 si erreur
	 * @throws SQLException problème requête SQL
	 */
	public static int getUserID(String login) throws SQLException {
		
		String query = "SELECT id_user from User WHERE login_user='" + login + "';";
		List<String> res = MySQLTools.executeQuery(query, "id_user");
		int id = -1;
		if (res.size()==1)
			id = Integer.parseInt(res.get(0));
		return id; // -1 si erreur

	}
	
	/**
	 * Ajoute un nouvel utilisateur dans la base de données
	 * @param login
	 * @param nom
	 * @param prenom
	 * @param password
	 * @param email
	 * @throws SQLException
	 */
	public static void insertUser(String login, String nom, String prenom, String password, String email) throws SQLException {
		String query = "INSERT INTO User (login_user, first_name_user, family_name_user, password_user, mail_user)"
				+ "VALUES ('" + login + "', '" + nom + "', '" + prenom + "', '" + password + "', '" + email + "');";	
		MySQLTools.executeUpdate(query);
		return;

	}
	
	/**
	 * Retourne le login de l'utilisateur en fontion de son identifiant
	 * @param id identifiant de l'utilisateur
	 * @return le login de l'utilisateur, null si erreur
	 * @throws SQLException
	 */
	public static String getLogin(int id) throws SQLException {
		String query = "SELECT login_user from User WHERE id_user=" + id + ";";
		List<String> res = MySQLTools.executeQuery(query, "login_user");
		String login = null;
		if (res.size()==1) 
			login = res.get(0);		
		return login;
	}
	
	/**
	 * Retourne le nom de l'utilisateur en fontion de son identifiant
	 * @param id identifiant de l'utilisateur
	 * @return le nom de l'utilisateur, null si erreur
	 * @throws SQLException
	 */
	public static String getNom(int id) throws SQLException {
		String query = "SELECT family_name_user from User WHERE id_user=" + id + ";";
		List<String> res = MySQLTools.executeQuery(query, "family_name_user");
		String login = null;
		if (res.size()==1) 
			login = res.get(0);		
		return login;
	}
	
	/**
	 * Retourne le prénom de l'utilisateur en fontion de son identifiant
	 * @param id identifiant de l'utilisateur
	 * @return le prénom de l'utilisateur, null si erreur
	 * @throws SQLException
	 */
	public static String getPrenom(int id) throws SQLException {
		String query = "SELECT first_name_user from User WHERE id_user=" + id + ";";
		List<String> res = MySQLTools.executeQuery(query, "first_name_user");
		String login = null;
		if (res.size()==1) 
			login = res.get(0);		
		return login;
	}
	
}
