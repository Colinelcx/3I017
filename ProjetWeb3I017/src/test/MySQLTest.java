package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost/twister_DB";
		// attention au nom twister_DB, ca peut bien changer comme on est un peu mal organisé
		// mais c'est en faite le nom de la BD sur phpmyadmin
		
		Connection conn = DriverManager.getConnection(url, "root", "root");
		
		String query = "SELECT * FROM user";
		
		Statement st = conn.createStatement();
		
		ResultSet rs =  st.executeQuery(query);
		
		while (rs.next()) {
			String login = rs.getString("login_user");
			System.out.println("this is an id " + login);
			String email = rs.getString("mail_user");
			System.out.println("this is an email " + email);
	
		}
		
		rs.close();
		st.close();
		conn.close();
		
		System.out.println("no error"); // il l'a imprimé
		
		/*
		 * j'ai ajouté des valeurs dans la bd sur phpmyadmin
		 * ensuite j'ai fait une nouvelle execution, et j'ai recu ca
		 * 
		 * this is an id charelF
		 * this is an email c@email.com
		 * no error
		 * 
		 * 
		 * donc tout fonctionne tres bien
		 */

	}

}
