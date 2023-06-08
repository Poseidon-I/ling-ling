package dev;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Unban {
	public static void unban(GenericDiscordEvent e, String idToModerate, String reason, Boolean reset) {
		try {
			if(idToModerate.equals("")) {
				throw new IllegalArgumentException();
			}
			Long.parseLong(idToModerate);
		} catch(Exception exception) {
			e.reply( "You didn't provide an ID!");
			return;
		}

		if(reset == null) {
			e.reply("You have to tell me whether you want to reset the save or not, dumb.");
			return;
		}
		
		JSONParser parser = new JSONParser();
		JSONObject data;
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + idToModerate + ".json");
		try(FileReader reader = new FileReader(file.getAbsolutePath())) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("File doesn't exist!  Looks like they never used the bot to begin with...");
			return;
		}
		if((Boolean) data.get("banned")) {
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			if(reset) {
				file.delete();
				reason += "\nSave Reset: Yes";
			} else {
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + idToModerate + ".json")) {
					data = (JSONObject) parser.parse(reader);
					reader.close();
					data.replace("banned", false);

					FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + idToModerate + ".json");
					writer.write(data.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				reason += "\nSave Reset: No";
			}
			LogCase.logCase(e, "Unban", idToModerate, reason);
			try {
				String finalReason = reason;
				Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).openPrivateChannel().queue((channel) -> channel.sendMessage("You have been unbanned.  Reason: " + finalReason).queue());
			} catch(Exception exception) {
				// nothing here lol
			}
			e.reply(":white_check_mark: " + user + " was successfully unbanned!");
		} else {
			e.reply("That user isn't even banned, stupid.");
		}
	}
}
