package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class LoadData {
	public static JSONObject loadData(GenericDiscordEvent e) {
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("You don't have a save file!  Run `/start` to get a profile!");
			throw new IllegalArgumentException();
		}
		if((boolean) data.get("banned")) {
			e.reply("You are banned from using the economy!");
			throw new IllegalArgumentException();
		} else {
			return data;
		}
	}
}