package tools;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTools {

	public static boolean userExists(String login) throws SQLException {
		String query = "SELECT user_id from User WHERE user_id='" + login + "';";
		ResultSet res = tools.DataBaseTools.executeRequete(query);
		System.out.println(res);
		return true;
	}

	public static String getUserID(String login) {
		return "0000";
	}

}
