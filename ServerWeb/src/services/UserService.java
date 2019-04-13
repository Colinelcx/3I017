package services;
import java.sql.SQLException;

import org.json.JSONException;
//
import org.json.JSONObject;

import tools.AuthTools;
import tools.FriendTools;
import tools.ServiceTools;
import tools.UserTools;

public class UserService {
	
	/**
	 * Création d'un nouvel utilisateur,
	 * ajout de celui-ci dans la base de données
	 * @param login nom d'utilisateur
	 * @param nom  
	 * @param prenom
	 * @param password unique
	 * @param email unique
	 * @return
	 */
	public static JSONObject createUser(String login, String nom, String prenom, String password, String mail){
		//Vérification des arguments
		if (nom == null || prenom == null || login == null || password == null || mail == null) {
			return tools.ServiceTools.ServiceRefused("Wrong web arguments", -1);			
		}
		
		try {
			//On vérifie que le nom d'utilisateur n'est pas déja pris
			boolean user_exists = UserTools.userExists(login);
			if (user_exists)
				return ServiceTools.ServiceRefused("username " + login + " already tooken ", 1);
			//Vérification de l'adresse mail
			boolean mail_exists = UserTools.mailExists(mail);
			if (mail_exists)
				return ServiceTools.ServiceRefused("mail adress " + mail + " already tooken ", 1);
			
			//Création de l'utilisateur
			tools.UserTools.insertUser(login, nom, prenom, password, mail);
			return ServiceTools.ServiceAccepted("create user OK");
			
		} catch (SQLException e) {			
			return tools.ServiceTools.ServiceRefused(e.getMessage(), 1000);
			
		}	
	}
	
	public static JSONObject infosUser(String key, String username) {
		
		if ((key == null) || (username== null)){
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
			boolean is_user2 = UserTools.userExists(username);
			if (!is_user1) 
				return ServiceTools.ServiceRefused("unknown user " + id_user1, 1);
			if (!is_user2) 
				return ServiceTools.ServiceRefused("unknown user " + username, 1);
			int id_user2 = UserTools.getUserID(username);
			
			//On vérifie qu'ils ne sont pas déjà amis
			boolean friendship = FriendTools.areFriends(id_user1, id_user2);
			JSONObject json = new JSONObject();
			json.put("id_user", id_user2);
			json.put("username", username);
			json.put("nom", UserTools.getPrenom(id_user2));
			json.put("prenom", UserTools.getNom(id_user2));
			json.put("friend", friendship);

			return json;
			
		} catch (SQLException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 1000);
		} catch( JSONException e) {
			return ServiceTools.ServiceRefused(e.getMessage(), 100);

		}
	}

}
