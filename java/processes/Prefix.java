package processes;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Prefix {
	public static char GetPrefix(GuildMessageReceivedEvent e) {
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".json")) {
			JSONParser parser = new JSONParser();
			reader.close();
			return (char) ((JSONObject) parser.parse(reader)).get("prefix");
		} catch(Exception exception) {
			try {
				JSONObject data = new JSONObject();
				data.put("prefix", '!');
				File file = new File("Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".json");
				file.createNewFile();
				FileWriter writer = new FileWriter(file.getAbsolutePath());
				writer.write(data.toJSONString());
				writer.close();
				return '!';
			} catch(Exception exception1) {
				return '!';
			}
		}
	}
}