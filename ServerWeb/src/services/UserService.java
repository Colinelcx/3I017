package services;
import java.sql.SQLException;

//
import org.json.JSONObject;

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

}
