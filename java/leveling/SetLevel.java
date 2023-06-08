package leveling;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
// BEETHOVEN-ONLY CLASS

public class SetLevel {
	public static void setLevel(GenericDiscordEvent e) {
		if(Objects.requireNonNull(e.getGuild().getMember(e.getAuthor())).getRoles().contains(e.getGuild().getRoleById("735308459452661802"))) {
			String[] message = e.getMessage().getContentRaw().split(" ");
			String id = message[2];
			JSONParser parser = new JSONParser();
			JSONObject data;
			try (FileReader reader = new FileReader("Ling Ling Bot Data\\Leveling\\" + id + ".json")) {
				data = (JSONObject) parser.parse(reader);
			} catch (Exception exception) {
				e.getMessage().reply("This save doesn't exist!").queue();
				return;
			}
			data.replace("level", Long.parseLong(message[3]));
			try (FileWriter writer = new FileWriter("Ling Ling Bot Data\\Leveling\\" + id + ".json")) {
				writer.write(data.toJSONString());
			} catch (Exception exception) {
				// well this is awkwaryd
			}
			e.getMessage().reply("Successfully set `" + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + "`'s level to `" + message[3] + "`.").queue();
		} else {
			e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command!").mentionRepliedUser(false).queue();
		}
	}
}