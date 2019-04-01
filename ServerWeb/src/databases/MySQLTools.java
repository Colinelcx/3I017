package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		 * @return le nombre de résultats de la requête
		 * @throws SQLException erreur dans la requête
		 */
		public static int executeQuery(String query) throws SQLException {
			int cpt = 0;
			Connection conn = getMySQLConnection();
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery(query);
			res = st.executeQuery(query);
			while(res.next())
				cpt++;
			st.close();
			conn.close();
			return cpt;
		}
		
		/**
		 * Exécute une requête SQL de type interrogation, passée en argument en interrogeant la database du serveur
		 * @param query requête à exécuter
		 * @param attribut la clonne que l'on souhaite récupérer
		 * @return la liste des résultats de la requête sur la colonne attribut
		 * @throws SQLException erreur dans la requête
		 */
		public static List<String> executeQuery(String query, String attribut) throws SQLException {
			List<String> listRes = new ArrayList<String>();
			Connection conn = getMySQLConnection();
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery(query);
			res = st.executeQuery(query);
			while(res.next())
				listRes.add(res.getString(attribut));
			st.close();
			conn.close();
			return listRes;
		}
		
		/**
		 * Exécute une requête SQL de type mise-à-jour, passée en argument en interrogeant la database du serveur
		 * @param query requête à exécuter
		 * @return le nombre de lignes affectées
		 * @throws SQLException erreur dans la requête
		 */
		public static int executeUpdate(String query) throws SQLException {
			int res = -1;
			Connection conn = null;
			Statement st = null;
			conn = getMySQLConnection();	
			st = conn.createStatement();
			res = st.executeUpdate(query);
			st.close();
			conn.close();
			return res;
		}
	}

