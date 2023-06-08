package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;

import java.io.FileWriter;

public class SaveData {
	public static void saveData(GenericDiscordEvent e, JSONObject newData) {
		try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".json")) {
			writer.write(newData.toJSONString());
			writer.close();
		} catch(Exception exception) {
			e.reply("Well this is awkward...");
		}
	}
}