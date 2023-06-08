package leveling;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Objects;
// BEETHOVEN-ONLY CLASS

public class Rank {
	public static void rank(GenericDiscordEvent e) {
		JSONObject data;
		String[] message = e.getMessage().getContentRaw().split(" ");
		String user;
		if(message.length == 2) {
			user = e.getAuthor().getId();
			data = Leveling.loadData(e);
		} else {
			user = message[2];
			JSONParser parser = new JSONParser();
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Leveling\\" + user + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.getMessage().reply("This save file does not exist!").mentionRepliedUser(false).queue();
				return;
			}
		}
		user = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
		e.getMessage().reply("**__" + user + "'s Stats__**\nLevel `" + data.get("level") + "`\n`" + data.get("xp") + "/" + ((long) data.get("level") + 1) * 100 + "` XP").mentionRepliedUser(false).queue();
	}
}