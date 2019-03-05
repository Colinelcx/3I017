package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTools {
	
		/**
		 * Exécute une requête passée en argument en interrogeant la database du serveur
		 * @param query requête à exécuter
		 * @return le résultat de la requête
		 * @throws SQLException erreur dans la requête
		 */
		public static ResultSet executeQuery(String query) throws SQLException {
			try {	
				
				Class.forName(DBStatic.mySQLDriver);/*getDeclaredConstructor().newInstance;*/
				String url = DBStatic.mySQLName;
				Connection conn = DriverManager.getConnection(url, DBStatic.mySQLUser, DBStatic.mySQLPassword);
				Statement st = conn.createStatement();
				return st.executeQuery(query);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

