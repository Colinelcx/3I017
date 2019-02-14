package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceTools {

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