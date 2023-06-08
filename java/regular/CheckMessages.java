package regular;

import eventListeners.GenericDiscordEvent;
import leveling.Leveling;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Objects;
// BEETHOVEN-ONLY CLASS
public class CheckMessages {
	public static void checkMessages(GenericDiscordEvent e) {
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
				e.reply("This save file does not exist!");
				return;
			}
		}
		e.reply("Messages this month for `" + Objects.requireNonNull(e.getJDA().getUserById(user)).getName() + "`: `" + data.get("messages") + "` messages");
	}
}