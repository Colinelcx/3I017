package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthTools;
import tools.FriendTools;
import tools.MessageTools;
import tools.ServiceTools;
import tools.UserTools;

public class MessageService {
	
	
	public static JSONObject search(String key, String type, String user) {
		if ((type == null) || (key == null)|| (user == null)){
			return (tools.ServiceTools.ServiceRefused("Wrong web arguments", -1));
		}
		
		if (type.compareTo("timeline") == 0)
			return getRecentMessages(key);
		if (type.compareTo("user") == 0)
			return getUserMessages(key, user);
		return null;
	}

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
	
	// all kind of message queries need to return JSON in the following string form:
	//	'{"123":{"username":"test", "id":234, "nom":"testn", "prenom":"testpn", "date":"123", "text":"123"},
//	     "456":{"username":"secondusername", "id":888, "nom":"secondn", "prenom":"seconpn", "date":"987", "text":"second text"}}'


	public static JSONObject getUserMessages(String key, String username) {
		
		if (key == null){
			return (tools.ServiceTools.ServiceRefused("Wrong web arguments", -1));
		}
		
		try {
			//Vérification de la session
			boolean session_OK = AuthTools.checkSession(key);
			if (!session_OK)
				return ServiceTools.ServiceRefused("Invalid session", 1);
			
			int id_user = UserTools.getUserID(username);
			
			//Vérification de l'identifiant
			boolean is_user = UserTools.userExists(username);
			if (!is_user) 
				return ServiceTools.ServiceRefused("unknown user " + id_user, 1);
			
			//Récupération des messages
			return MessageTools.getUserMessages(id_user);
			
		} catch (SQLException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 1000);
		} catch (JSONException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 100);
		}
	}
	
	public static JSONObject getRecentMessages(String key) {
		
		if (key == null){
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
			
			//Récupération des messages
			int[] id_friends = FriendTools.getFriendsList(id_user);
			return MessageTools.getRecentMessages(id_friends);
			
		} catch (SQLException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 1000);
		} catch (JSONException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 100);
		}
	}

}