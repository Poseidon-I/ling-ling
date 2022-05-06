package regular;

import economy.LoadData;
import economy.SaveData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;

import java.awt.*;

public class UserSettings {
	public void SetBooleanOption(MessageReceivedEvent e, String[] message, JSONObject data, String key) {
		try {
			if(message[2].equals("on") || message[2].equals("true") || message[2].equals("yes")) {
				data.replace(key, true);
				new SaveData(e, data);
				e.getMessage().reply("Turned `" + key + "` on!").mentionRepliedUser(false).queue();
			} else if(message[2].equals("off") || message[2].equals("false") || message[2].equals("no")) {
				data.replace(key, false);
				new SaveData(e, data);
				e.getMessage().reply("Turned `" + key + "` off!").mentionRepliedUser(false).queue();
			} else {
				e.getMessage().reply("Invalid option provided!  Valid options: `on`/`true`/`yes` or `off`/`false`/`no`").mentionRepliedUser(false).queue();
			}
		} catch(Exception exception) {
			e.getMessage().reply("You must specify `on`/`true`/`yes` or `off`/`false`/`no`.").mentionRepliedUser(false).queue();
		}
	}
	public UserSettings(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		JSONObject data = LoadData.loadData(e);
		try {
			switch(message[1]) {
				case "color" -> {
					try {
						switch(message[2]) {
							case "black" -> {
								data.replace("color", "#000000");
								e.getMessage().reply("Changed color to Black.").mentionRepliedUser(false).queue();
							}
							case "white" -> {
								data.replace("color", "#FFFFFF");
								e.getMessage().reply("Changed color to White.").mentionRepliedUser(false).queue();
							}
							case "red" -> {
								data.replace("color", "#FF0000");
								e.getMessage().reply("Changed color to Red.").mentionRepliedUser(false).queue();
							}
							case "orange" -> {
								data.replace("color", "#FF7F00");
								e.getMessage().reply("Changed color to Orange.").mentionRepliedUser(false).queue();
							}
							case "yellow" -> {
								data.replace("color", "#FFFF00");
								e.getMessage().reply("Changed color to Yellow.").mentionRepliedUser(false).queue();
							}
							case "lightgreen" -> {
								data.replace("color", "#00FF00");
								e.getMessage().reply("Changed color to Light Green.").mentionRepliedUser(false).queue();
							}
							case "green" -> {
								data.replace("color", "#008F00");
								e.getMessage().reply("Changed color to Green.").mentionRepliedUser(false).queue();
							}
							case "cyan" -> {
								data.replace("color", "#00FFFF");
								e.getMessage().reply("Changed color to Cyan.").mentionRepliedUser(false).queue();
							}
							case "blue" -> {
								data.replace("color", "#0000FF");
								e.getMessage().reply("Changed color to Blue.").mentionRepliedUser(false).queue();
							}
							case "darkblue" -> {
								data.replace("color", "#00008F");
								e.getMessage().reply("Changed color to Dark Blue.").mentionRepliedUser(false).queue();
							}
							case "purple" -> {
								data.replace("color", "#7F007F");
								e.getMessage().reply("Changed color to Purple.").mentionRepliedUser(false).queue();
							}
							case "magenta" -> {
								data.replace("color", "#FF00FF");
								e.getMessage().reply("Changed color to Magenta.").mentionRepliedUser(false).queue();
							}
							default -> e.getMessage().reply("Invalid color provided.  Valid colors: `red` `orange` `yellow` `lightgreen` `green` `cyan` `blue` `darkblue` `purple` `magenta` `white` `black`").mentionRepliedUser(false).queue();
						}
						new SaveData(e, data);
					} catch(Exception exception) {
						e.getMessage().reply("You have to specify a color.  Valid colors: `red` `orange` `yellow` `lightgreen` `green` `cyan` `blue` `darkblue` `purple` `magenta` `white` `black`").mentionRepliedUser(false).queue();
					}
				}
				case "dms" -> SetBooleanOption(e, message, data, "DMs");
				case "info" -> SetBooleanOption(e, message, data, "extraInfo");
				default -> e.getMessage().reply("Invalid option provided!  Valid settings: `color` `dms` `info`").mentionRepliedUser(false).queue();
			}
		} catch(Exception exception) {
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.decode((String) data.get("color")))
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
					.setTitle("**__Settings for " + e.getAuthor().getName() + "__**")
					.addField("Color", "Sets the embed color for your commands.  Does not apply to `!help` for technical reasons.\nCurrent option: `" + data.get("color") + "`\nID: `color`", false)
					.addField("DMs", "Toggles whether DMs are sent for Rob and Gift.\nCurrent option: `" + data.get("DMs") + "`\nID: `dms`", false)
					.addField("Extra Information", "Toggles whether extra info is shown, like your chance of a successful `!rob` or the ticket breakdown from scratch.\nCurrent option: `" + data.get("extraInfo") + "`\nID: `info`", false);
			e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
		}
	}
}