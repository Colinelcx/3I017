package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthTools;
import tools.ServiceTools;


public class AuthService {
	
	/**
	 * Connection d'un utilisateur au site. Vérifie les paramètres (login puis password)
	 * Puis génère une nouvelle clé de session
	 * @param login : nom d'accès de l'utilisateur au site
	 * @param password : mot de passe de l'utilisateur
	 * @return {id, login, key} ou alors message d'erreur : {message, code}
	 */
	public static JSONObject login(String login, String password) {
		
		// Vérification des arguments web
		if ((login == null) || (password == null)){
			return (tools.ServiceTools.ServiceRefused("Wrong arguments for login", -1));
		}
		
		try {
			
			// Vérification du login
			boolean is_user=tools.UserTools.userExists(login);
			if (!is_user) 
				return tools.ServiceTools.ServiceRefused("unknown user : " + login, 100000);
			
			int id = tools.UserTools.getUserID(login);

			//Vérification du mot de passe
			boolean password_ok=tools.AuthTools.checkPassword(id, password);
			if (!password_ok) 
				return tools.ServiceTools.ServiceRefused("bad password" + login, 2);
						
			// Création du JSON
			JSONObject retour = new JSONObject();
			String key = AuthTools.insertSession(id, false);
			retour.put("id", id);
			retour.put("login", login);
			retour.put("key", key);
			
			return retour;
			
		} catch (SQLException e) {
			return tools.ServiceTools.ServiceRefused(e.getMessage(), 1000);
			
		} catch (JSONException e) {
			return tools.ServiceTools.ServiceRefused(e.getMessage(), 100);
		}
				
	}
	
public static JSONObject logout(String key) {
		
		if (key == null) {
			return (tools.ServiceTools.ServiceRefused("Wrong arguments", 0));
		}
		
		try {
			
			boolean session = tools.AuthTools.checkSession(key);
			
			if (!session) return tools.ServiceTools.ServiceRefused("session does not exist ", 1);
			
			tools.AuthTools.removeSession(key);
			
			return ServiceTools.ServiceAccepted("logged out");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return tools.ServiceTools.ServiceRefused("probleme sql logout", 100);
		}
				
	}
}
