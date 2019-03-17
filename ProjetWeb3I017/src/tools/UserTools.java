package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.Statement;

import databases.MySQLTools;

public class UserTools {

	/**
	 * vérifie si l'utilisateur est présent dans la base
	 * @param id identifiant de l' utilisateur
	 * @return true si l'utilisateur existe dans la base de bonnées, false sinon
	 * @throws SQLException erreur requête SQL
	 */
	public static boolean userExists(int id) throws SQLException {

		String query = "SELECT * from User WHERE id_user=" + id + ";"; // il faut peut être rendre ces queries SQL injection attack secure
		ResultSet res = MySQLTools.executeQuery(query);
		boolean response = res.next();	
		return response;
	}
	
	/**
	 * vérifie si l'utilisateur est présent dans la base
	 * @param login nom d'utilisateur
	 * @return true si l'utilisateur existe dans la base de bonnées, false sinon
	 * @throws SQLException erreur requête SQL
	 */
	public static boolean userExists(String login) throws SQLException {	
		int id = getUserID(login);
		return userExists(id);	
	}
	

	/**
	 * Retourne l'id du login en argument
	 * @param login 
	 * @return l'identifiant correspondant au login passé en argument
	 * @throws SQLException problème requête SQL
	 */
	public static int getUserID(String login) throws SQLException {
		
		String query = "SELECT id_user from User WHERE login_user='" + login + "';";
		ResultSet res = MySQLTools.executeQuery(query);
		res.next();
		int id = res.getInt("id_user");
		return id; // erreur si -1

	}
	
	public static void insertUser(String login, String nom, String prenom, String password, String email) throws SQLException {
		String query = "INSERT INTO User (login_user, first_name_user, family_name_user, password_user, mail_user)"
				+ "VALUES ('" + login + "', '" + nom + "', '" + prenom + "', '" + password + "', '" + email + "');";
		// on n'a pas besoin de mettre id_user comme c'est auto increment
		
		Connection conn = databases.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;

	}
	
	public static String getLogin(int id) throws SQLException {
		
		String query = "SELECT login_user from User WHERE id_user=" + id + ";";
		Connection conn = databases.DataBaseTools.getConnection();
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
