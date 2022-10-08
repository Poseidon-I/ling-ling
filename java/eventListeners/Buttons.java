package eventListeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class Buttons extends ListenerAdapter {
	public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getUser().getId() + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("You don't have a save file!  Run `/start` to get a profile!").queue();
			return;
		}
		if((boolean) data.get("banned")) {
			e.reply("You are banned from using the economy!").queue();
			return;
		}
		switch(e.getComponentId()) {
			case "mc" -> {
				data.replace("mcIGN", e.getButton().getLabel().split(" ")[1]);
				try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + e.getUser().getId() + ".json")) {
					writer.write(data.toJSONString());
					writer.close();
				} catch(Exception exception) {
					e.reply("Well this is awkward...").queue();
				}
				e.getChannel().sendMessage("<@" + e.getUser().getId() + ">\nYour Minecraft account has been successfully linked.\n**You may no longer change your linked account, to prevent abuse.  If you change your IGN, DM me and I will sort it out for you.**\nYou are welcome to link multiple Ling Ling accounts to one Minecraft account, but be warned that abusing this will lead to punishment.").queue();
				e.editButton(e.getButton().asDisabled()).queue();
			}
		}
	}
}