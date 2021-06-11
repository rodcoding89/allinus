package AllinusModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Message {
	private Map map = new HashMap();
	
	public Message(String messageFrom,String messageTo, String message, String phone, String date,String pos) throws JSONException {
		JSONObject msgdetaille = new JSONObject();
		
		msgdetaille.put("msgFrom", messageFrom);
		msgdetaille.put("msgTo", messageTo);
		msgdetaille.put("message", message);
		msgdetaille.put("phone", phone);
		msgdetaille.put("date", date);
		msgdetaille.put("msgPos", pos);
		
		
		
		JSONObject msg = new JSONObject();
		msg.put("message", msgdetaille);
		
		
		JSONArray msglist = new JSONArray();
		msglist.put(msg);
		
		try (FileWriter file = new FileWriter("message.json")) {
			 
            file.write(msglist.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
