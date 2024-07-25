package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import processes.DatabaseManager;

public class LoadData {
	public static JSONObject loadData(GenericDiscordEvent e) {
		JSONObject data = DatabaseManager.getEconomyData(e);
		if(data == null) {
			e.reply("You don't have a save file!  Run `/start` to get a profile!");
			throw new IllegalArgumentException();
		}
		if(data.get("discordName").toString().isEmpty()) {
			data.replace("discordName", e.getAuthor().getEffectiveName());
		}
		if((boolean) data.get("banned")) {
			e.reply("You are banned from using the economy!");
			throw new IllegalArgumentException();
		} else {
			return data;
		}
	}
}