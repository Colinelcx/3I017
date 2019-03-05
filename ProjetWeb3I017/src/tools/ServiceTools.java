package tools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 3670163
 *
 */
public class ServiceTools {

	/**
	 * Renvoie un JSON contenant un message d'erreur ainsi que son code
	 * @param message : message d'erreur
	 * @param code : - Code -1 erreur d’arguments passé au Web service (argument manquant, mauvais format, ...)
 					 - Code 100 erreur de JSON
 					 - Code 1000 erreur de SQL
 					 - Code 10000 erreur de JAVA
 					 - Code 100000 erreur de valeur (mauvais login, mauvaise session, mauvais mot de passe...)
	 * @return le JSON contenant le message d'erreur et le code
	 */
	public static JSONObject ServiceRefused(String message, int code) {
		try {
			JSONObject o = new JSONObject();
			o.put("message", message);
			o.put("code", code);
			return o;
		}	
		catch (JSONException exc) {
			exc.printStackTrace();
			return null;
		}
	}

	/**
	 * Renvoie un JSON avec un message indiquant que tout s'est bien passé
	 * @param message : message à renvoyer
	 * @return JSON contenant le message
	 */
	public static JSONObject ServiceAccepted(String message) {
		try {
			JSONObject o = new JSONObject();
			o.put("message", message);
			return o;
		}	
		catch (JSONException exc) {
			exc.printStackTrace();
			return null;
		}
	}
}