package dev;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class SetPermLevel {
	public static void setPermLevel(GenericDiscordEvent e, String target, int newRank) {
		if(target.equals("")) {
			e.reply("You have to provide a user to promote or demote, dumbass.");
			return;
		}
		if(newRank == -1) {
			e.reply("You have to provide a rank to move the user to, stupid.");
			return;
		}
		if(newRank >= 0 && newRank <= 2) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.reply("Invalid user provided!");
				return;
			}
			data.replace("perms", newRank);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				e.reply("Something went horribly wrong!");
				return;
			}
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			e.reply("Set the permission level for " + user + " to `" + newRank + "`");
			
		} else {
			e.reply("Invalid level provided!\n\nValid levels:\n`0`: No Permissions\n`1`: Mod Permissions\n`2`: Admin Permissions");
		}
	}
}