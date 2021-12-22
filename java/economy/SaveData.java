package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

import java.io.FileWriter;

public class SaveData {
	public SaveData(GuildMessageReceivedEvent e, JSONObject newData) {
		try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".json")) {
			writer.write(newData.toJSONString());
			writer.close();
		} catch(Exception exception) {
			e.getChannel().sendMessage("Well this is awkward...").queue();
		}
	}
}