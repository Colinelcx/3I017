package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//public class DataBaseTools {
//	public static ResultSet executeRequete(String query) throws SQLException {
//		try {
//			
//			Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
//			// à quoi sert le .newInstance()? eclipse dit que la méthode est "depreceated"
//			String url = "jdbc:mysql://localhost/lacoux_felten";
//			Connection conn;
//			conn = DriverManager.getConnection(url, "root", "root");
//			Statement st;
//			st = conn.createStatement();
//			return st.executeQuery(query);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//}

public class DataBaseTools {
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver")/*.newInstance()*/;
			// à quoi sert le .newInstance()? eclipse dit que la méthode est "depreceated"
			String url = "jdbc:mysql://localhost/lacoux_felten";
			Connection conn;
			conn = DriverManager.getConnection(url, "root", "root");
			return conn;
//			Statement st;
//			st = conn.createStatement();
//			return st.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) { // ici il y un erreur commee j'ai commenté la methode .newInstance()
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
