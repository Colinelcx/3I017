package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import tools.FriendTools;

public class FriendService {

	public static void addFriend(int id_user1, int id_user2) throws SQLException {
		
		if (Friend	Tools.areFriends(id_user1, id_user2)) {
			return;
		}
				
		// supprimer la colonne root de la table Friendship, elle ne met aucun sens
		
		String query = "INSERT INTO Friendship (id_user1, id_user2, date_connexion)"
				+ "VALUES (" + id_user1 + ", " + id_user2 + ", NOW());";
		// le NOW() fait la date automatiquement dans SQL
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;
	}
	
	public static void removeFriend(int id_user1, int id_user2) throws SQLException {

		if (FriendTools.areFriends(id_user1, id_user2) == false) {
			return;
		}
		
		String query = "DELETE FROM Friendship WHERE id_user1 = " + id_user1 + " AND id_user2 = " + id_user2 + ");";
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;		
	}
}
