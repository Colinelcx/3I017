package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databases.MySQLTools;

public class FriendTools {
	
	/**
	 * Test le lien d'amitié entre 2 utilisateurs
	 * @param id_user1 utilisateur 1
	 * @param id_user2 utilisateur 2
	 * @return true si l'utilisateur 1 suit l'utilisateur 2, false sinon
	 * @throws SQLException
	 */
	public static boolean areFriends(int id_user1, int id_user2) throws SQLException {
		
		String query = "SELECT * from Friendship WHERE id_user1 = " + id_user1 + " AND id_user2 = " + id_user2 + ";";
		
		ResultSet res = MySQLTools.executeQuery(query);
		boolean response = res.next();
		return response;
	}
	
	/**
	 * Ajoute un lien d'amitié dans la table FriendShip
	 * @param id_user1 utilisateur 1
	 * @param id_user2 utilisateur 2
	 * @throws SQLException
	 */
	public static void insertFriendship(int id_user1, int id_user2) throws SQLException {
		
		String query = "INSERT INTO Friendship (id_user1, id_user2, date_connexion)"
				+ "VALUES (" + id_user1 + ", " + id_user2 + ", NOW());";
		
		MySQLTools.executeUpdate(query);
	}
	
	/**
	 * Supprime le lien d'amitié entre les deux utilisateurs dans la table Friendship
	 * @param id_user1 utilisateur 1
	 * @param id_user2 utilisateur 2
	 * @throws SQLException
	 */
	public static void removeFriendship(int id_user1, int id_user2) throws SQLException {

		String query = "DELETE FROM Friendship WHERE id_user1 = " + id_user1 + " AND id_user2 = " + id_user2 + ";";
		
		MySQLTools.executeUpdate(query);	
	}
	
	
	/**
	 * Retourne la liste des amis d'un utilisateur
	 * @param id_user identifiant de l'utilisateur
	 * @return la liste des identifiants des amis
	 * @throws SQLException
	 */
	public static List<Integer> getFriendsList (int id_user1) throws SQLException {
		
		String query = "SELECT id_user2 from Friendship WHERE id_user1 = " + id_user1 + ";";

		ResultSet res = MySQLTools.executeQuery(query);
		
		ArrayList<Integer> friendsList = new ArrayList<Integer>();
		
		while (res.next()) {
			friendsList.add(res.getInt("id_user2"));
		}
		
		return friendsList;
		
	}
}
