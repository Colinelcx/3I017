package services;

import java.sql.SQLException;

import org.json.JSONObject;

import tools.AuthTools;
import tools.MessageTools;
import tools.ServiceTools;
import tools.UserTools;

public class MessageService {

	/**
	 * Ajoute un message écrit par un utilisateur dans la base de données
	 * @param key clé de session de l'utilisateur
	 * @param content contenu du message
	 * @return
	 */
	public static JSONObject addMessage(String key, String content) {
	
		if ((key == null) || (content == null)){
			return (tools.ServiceTools.ServiceRefused("Wrong web arguments", -1));
		}
		
		try {
			//Vérification de la session
			boolean session_OK = AuthTools.checkSession(key);
			if (!session_OK)
				return ServiceTools.ServiceRefused("Invalid session", 1);
			
			int id_user = AuthTools.getSessionID(key);
			
			//Vérification de l'identifiant
			boolean is_user = UserTools.userExists(id_user);
			if (!is_user) 
				return ServiceTools.ServiceRefused("unknown user " + id_user, 1);
			
			//Création du message
			MessageTools.addMessage(id_user, content);;
			String user = UserTools.getLogin(id_user);
			return ServiceTools.ServiceAccepted("message added by " + user);
			
		} catch (SQLException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 1000);
		}
	}

}
 //s