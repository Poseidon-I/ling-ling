package dev;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.DatabaseManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class SetPermLevel {
	public static void setPermLevel(GenericDiscordEvent e, String target, int newRank) {
		if(target.isEmpty()) {
			e.reply("You have to provide a user to promote or demote, dumbass.");
			return;
		}
		if(newRank == -1) {
			e.reply("You have to provide a rank to move the user to, stupid.");
			return;
		}
		if(newRank >= 0 && newRank <= 2) {
			JSONObject data = DatabaseManager.getDataForUser(e, "Economy Data", target);
			if(data == null) {
				e.reply("This user does not exist.");
				return;
			}
			data.replace("perms", newRank);
			DatabaseManager.saveDataForUser(e, "Economy Data", target, data);
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