package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.simple.JSONObject;

import java.io.FileWriter;

public class SaveData {
	public static void saveData(SlashCommandInteractionEvent e, JSONObject newData) {
		try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + e.getUser().getId() + ".json")) {
			writer.write(newData.toJSONString());
			writer.close();
		} catch(Exception exception) {
			e.reply("Well this is awkward...").queue();
		}
	}
}