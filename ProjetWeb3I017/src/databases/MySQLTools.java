package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTools {
	private static Database database;
	
	/**
	 * Création de connection à la base de données SQL
	 * @return 
	 * @throws SQLException
	 */
	public static Connection getMySQLConnection() throws SQLException {
		if (DBStatic.mySQLPooling == false) {
			return(DriverManager.getConnection("jdbc:mysql://" + DBStatic.mySQLHost + "/" + 
					DBStatic.mySQLName, DBStatic.mySQLUser, DBStatic.mySQLPassword));
		}
		else {
			if (database==null) {
			database=new Database("jdbc/db");
			} return(database.getConnection());
			}
		
	}
	
		/**
		 * Exécute une requête SQL de type interrogation, passée en argument en interrogeant la database du serveur
		 * @param query requête à exécuter
		 * @return le résultat de la requête
		 * @throws SQLException erreur dans la requête
		 */
		public static ResultSet executeQuery(String query) throws SQLException {
			Connection conn = getMySQLConnection();
			Statement st = conn.createStatement();
			return st.executeQuery(query);
		}
		
		/**
		 * Exécute une requête SQL de type mise-à-jour, passée en argument en interrogeant la database du serveur
		 * @param query requête à exécuter
		 * @return le nombre de lignes affectées
		 * @throws SQLException erreur dans la requête
		 */
		public static int executeUpdate(String query) throws SQLException {
			Connection conn = getMySQLConnection();	
			Statement st = conn.createStatement();
			return st.executeUpdate(query);
		}
	}

