package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.json.simple.JSONObject;

import java.util.Objects;

public class Link {
	public static void link(SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		if(!data.get("mcIGN").equals("")) {
			e.reply("You have already linked an account!  Account name: `" + data.get("mcIGN") + "`").queue();
			return;
		}
		String name;
		try {
			name = Objects.requireNonNull(e.getOption("ign")).getAsString();
		} catch(Exception exception) {
			e.reply("You must provide your minecraft name.").setEphemeral(true).queue();
			return;
		}
		e.reply("Please click the button below to confirm that you want to link `" + name + "` to this Ling Ling profile.")
				.addActionRow(Button.primary("mc", "Confirm " + name))
				.setEphemeral(true)
				.queue();
	}
}