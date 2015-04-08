package com.iut.couchutlebouche.presing;

import org.json.JSONException;
import org.json.JSONWriter;

public class ModelToJsonFormat {
	/**
	 * This method take an object user and parset it with JsonWriter
	 * @param user : User to parse an json format
	 * @param jsonWriter : Writer to use for parsins
	 * @throws JSONException
	 */
	
	public static void userToJson(User user, JSONWriter jsonWriter) throws JSONException
	
	{
		jsonWriter.object();
		jsonWriter.key("loginStatus").value("yes");
		jsonWriter.key("username").value(user.username);
		jsonWriter.key("userpassword").value(user.userpassword);
		jsonWriter.endObject();
		
	}
}
