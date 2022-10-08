package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class LoadData {
	public static JSONObject loadData(@NotNull SlashCommandInteractionEvent e) {
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getUser().getId() + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("You don't have a save file!  Run `/start` to get a profile!").queue();
			throw new IllegalArgumentException();
		}
		if((boolean) data.get("banned")) {
			e.reply("You are banned from using the economy!").queue();
			throw new IllegalArgumentException();
		} else {
			return data;
		}
	}
}