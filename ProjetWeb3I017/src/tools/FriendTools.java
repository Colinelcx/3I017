package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendTools {
	
	public static boolean areFriends(int id_user1, int id_user2) throws SQLException {
		
		String query = "SELECT * from Friendship WHERE id_user1 = " + id_user1 + " AND id_user2 = " + id_user2 + ";";
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		boolean response = res.next();
		
		res.close(); // est-ce que c'est vraiment necessaire de fermer res?????
		st.close();
		conn.close();
		
		return response;
	}
	
	public static void insertFriendship(int id_user1, int id_user2) throws SQLException {
		
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
	
	public static void removeFriendship(int id_user1, int id_user2) throws SQLException {

		String query = "DELETE FROM Friendship WHERE id_user1 = " + id_user1 + " AND id_user2 = " + id_user2 + ";";
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		conn.close();
		
		return;		
	}
	
	
	public static int[] iDFriendsList (int id_user1) throws SQLException {
		//retourne une liste des ids des amis de user_1
		
		String query = "SELECT id_user2 from Friendship WHERE id_user1 = " + id_user1 + ";";
		
		Connection conn = tools.DataBaseTools.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(query);
		
		ArrayList<Integer> friendsArrayList = new ArrayList<Integer>();
		
		while (res.next()) {
			friendsArrayList.add(res.getInt("id_user2");
		}
		
		int[] friendsList = new int[friendsArrayList.size()];
		
		friendsList = friendsArrayList.toArray(new int[0]); // voir pq ca ne fonctionne pas
		
		return friendsList;
	}
	
	
	// a ajouter (peut etre)
	// - confirmer un friendship, donc ne l'ajouter que si les deux personnes sont d accord
}
