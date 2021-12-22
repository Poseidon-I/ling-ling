package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Prefix;

import java.io.*;

public class LoadData {
	public static JSONObject loadData(GuildMessageReceivedEvent e) {
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.getChannel().sendMessage("You don't have a save file!  Run `" + Prefix.GetPrefix(e) + "start` to get a profile!").queue();
			throw new IllegalArgumentException();
		}
		if((boolean) data.get("banned")) {
			e.getChannel().sendMessage("You are banned from using the economy!").queue();
			throw new IllegalArgumentException();
		} else {
			return data;
		}
	}
}