package dev;

import com.mongodb.client.MongoCollection;
import eventListeners.GenericDiscordEvent;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.DatabaseManager;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class UpdateUsers {
	public static void updateUsers(GenericDiscordEvent e, String dataType, String name, String value) {
		if(dataType.isEmpty()) {
			e.reply("You have to specify a data type, dumbass");
			return;
		}
		if(name.isEmpty()) {
			e.reply("You have to give a name, can't just call it nothing, can you?");
			return;
		}
		if(value.isEmpty() && !dataType.equals("string")) {
			e.reply("You have to give a default value, stupid.");
			return;
		}

		ArrayList<Document> documents = DatabaseManager.getAllEconomyData();
		MongoCollection<Document> collection = DatabaseManager.prepareStoreAllEconomyData();
		for(Document file : documents) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try {
				data = (JSONObject) parser.parse(file.toJson());
			} catch(Exception exception) {
				continue;
			}
			try {
				switch(dataType) {
					case "boolean" -> data.put(name, Boolean.parseBoolean(value));
					case "double" -> data.put(name, Double.parseDouble(value));
					case "int" -> data.put(name, Long.parseLong(value));
					case "string" -> data.put(name, value);
					default -> {
						e.reply("You didn't specify a valid type!  Valid types: `int` `double` `boolean` `string`");
						return;
					}
				}
			} catch(Exception exception) {
				e.reply("You didn't provide a valid value to put in the key.");
				return;
			}
			collection.replaceOne(eq("discordID", data.get("discordID")), Document.parse(data.toJSONString()));
		}
		e.reply("Successfully added data value `" + name + "` with type `" + dataType + "` to all users.");
	}
}