package services;

import java.sql.SQLException;

import org.json.JSONObject;

import tools.AuthTools;
import tools.FriendTools;
import tools.ServiceTools;
import tools.UserTools;

public class FriendService {

	/**
	 * Ajoute à l'utilisateur connecté un lien d'amitié avec l'utilisateur id_friend
	 * @param key clé de session
	 * @param id_friend identifiant de l'ami
	 * @return {}, ou {mesage, code} si erreur
	 */
	public static JSONObject addFriend(String key, int id_friend) {
	
		if ((key == null) || (id_friend < 0)){
			return (tools.ServiceTools.ServiceRefused("Wrong web arguments", -1));
		}
		
		try {
			//Vérification de la session
			boolean session_OK = AuthTools.checkSession(key);
			if (!session_OK)
				return ServiceTools.ServiceRefused("Invalid session", 1);
			
			int id_user1 = AuthTools.getSessionID(key);
			
			//Vérification des identifiants
			boolean is_user1 = UserTools.userExists(id_user1);
			boolean is_user2 = UserTools.userExists(id_friend);
			if (!is_user1) 
				return ServiceTools.ServiceRefused("unknown user " + id_user1, 1);
			if (!is_user2) 
				return ServiceTools.ServiceRefused("unknown user " + id_friend, 1);
			if (id_user1 == id_friend)
				return ServiceTools.ServiceRefused("you can't be friend with yourself !", 1);
			
			//On vérifie qu'ils ne sont pas déjà amis
			boolean friendship = FriendTools.areFriends(id_user1, id_friend);
			if (friendship)
				return ServiceTools.ServiceRefused("already friends", 1);
			
			//Création du lien d'amitié
			FriendTools.insertFriendship(id_user1, id_friend);
			String friend1 = UserTools.getLogin(id_user1);
			String friend2 = UserTools.getLogin(id_friend);
			return ServiceTools.ServiceAccepted("friendship created between " + friend1 + " and " + friend2);
			
		} catch (SQLException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 1000);
		}
	}

	/**
	 * Supprime le lien d'amitié avec l'utilisateur id_friend
	 * @param key clé de session
	 * @param id_friend identifiant de l'ami
	 * @return {}, ou {mesage, code} si erreur
	 */
	public static JSONObject removeFriend(String key, int id_friend) {
		
		if ((key == null) || (id_friend < 0)){
			return (ServiceTools.ServiceRefused("Wrong web arguments", -1));
		}
		
		try {
			//Vérification de la session
			boolean session_OK = AuthTools.checkSession(key);
			if (!session_OK)
				return ServiceTools.ServiceRefused("Invalid session", 1);
			
			int id_user1 = AuthTools.getSessionID(key);
			//Vérification des identifiants
			boolean is_user1 = UserTools.userExists(id_user1);
			boolean is_user2 = UserTools.userExists(id_friend);
			if (!is_user1) 
				return ServiceTools.ServiceRefused("unknown user " + id_user1, 1);
			if (!is_user2) 
				return ServiceTools.ServiceRefused("unknown user " + id_friend, 1);
			
			//On vérifie qu'ils sont bien amis
			boolean friends = tools.FriendTools.areFriends(id_user1, id_friend);
			if (!friends) 
				return ServiceTools.ServiceRefused("friendship does not exists", 1);
			
			//Supression du lien d'amitié
			tools.FriendTools.removeFriendship(id_user1, id_friend);

			String friend1 = UserTools.getLogin(id_user1);
			String friend2 = UserTools.getLogin(id_friend);
			return ServiceTools.ServiceAccepted("friendship removed beetween " + friend1 + " and " + friend2);
			
		} catch (SQLException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 1000);
		}
			
	}
}
