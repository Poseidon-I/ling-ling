package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Prefix;

import java.io.FileReader;

public class LoadData {
	public static JSONObject loadData(MessageReceivedEvent e) {
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.getMessage().reply("You don't have a save file!  Run `" + Prefix.GetPrefix(e) + "start` to get a profile!").mentionRepliedUser(false).queue();
			throw new IllegalArgumentException();
		}
		if((boolean) data.get("banned")) {
			e.getMessage().reply("You are banned from using the economy!").mentionRepliedUser(false).queue();
			throw new IllegalArgumentException();
		} else {
			return data;
		}
	}
}