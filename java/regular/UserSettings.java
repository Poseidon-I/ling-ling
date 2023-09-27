package regular;

import economy.LoadData;
import economy.SaveData;
import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;

import java.awt.*;

public class UserSettings {
	public static void setBooleanOption(GenericDiscordEvent e, JSONObject data, String key, String newValue) {
		try {
			if(newValue.equals("on") || newValue.equals("true") || newValue.equals("yes")) {
				data.replace(key, true);
				SaveData.saveData(e, data);
				e.reply("Turned `" + key + "` on!");
			} else if(newValue.equals("off") || newValue.equals("false") || newValue.equals("no")) {
				data.replace(key, false);
				SaveData.saveData(e, data);
				e.reply("Turned `" + key + "` off!");
			} else {
				e.reply("Invalid option provided!  Valid options: `on`/`true`/`yes` or `off`/`false`/`no`");
			}
		} catch(Exception exception) {
			e.reply("You must specify `on`/`true`/`yes` or `off`/`false`/`no`.");
		}
	}
	public static void userSettings(GenericDiscordEvent e, String option, String newValue) {
		JSONObject data = LoadData.loadData(e);
		if(option.equals("none")) {
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.decode((String) data.get("color")))
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
					.setTitle("**__Settings for " + e.getAuthor().getGlobalName() + "__**")
					.addField("Color", "Sets the embed color for your commands.  Does not apply to `/help` for technical reasons.\nCurrent option: `" + data.get("color") + "`\nID: `color`", false)
					.addField("DMs", "Toggles whether DMs are sent for Rob, Gift, and Market.\nCurrent option: `" + data.get("DMs") + "`\nID: `dms`", false)
					.addField("Extra Information", "Toggles whether extra info is shown, like your chance of a successful `/rob` or what you rolled to get a lootbox.\nCurrent option: `" + data.get("extraInfo") + "`\nID: `info`", false);
			e.replyEmbeds(builder.build());
		} else {
			switch(option) {
				case "color" -> {
					try {
						switch(newValue) {
							case "black" -> {
								data.replace("color", "#000000");
								e.reply("Changed color to Black.");
							}
							case "white" -> {
								data.replace("color", "#FFFFFF");
								e.reply("Changed color to White.");
							}
							case "red" -> {
								data.replace("color", "#FF0000");
								e.reply("Changed color to Red.");
							}
							case "orange" -> {
								data.replace("color", "#FF7F00");
								e.reply("Changed color to Orange.");
							}
							case "yellow" -> {
								data.replace("color", "#FFFF00");
								e.reply("Changed color to Yellow.");
							}
							case "lightgreen" -> {
								data.replace("color", "#00FF00");
								e.reply("Changed color to Light Green.");
							}
							case "green" -> {
								data.replace("color", "#008F00");
								e.reply("Changed color to Green.");
							}
							case "cyan" -> {
								data.replace("color", "#00FFFF");
								e.reply("Changed color to Cyan.");
							}
							case "blue" -> {
								data.replace("color", "#0000FF");
								e.reply("Changed color to Blue.");
							}
							case "darkblue" -> {
								data.replace("color", "#00008F");
								e.reply("Changed color to Dark Blue.");
							}
							case "purple" -> {
								data.replace("color", "#7F007F");
								e.reply("Changed color to Purple.");
							}
							case "magenta" -> {
								data.replace("color", "#FF00FF");
								e.reply("Changed color to Magenta.");
							}
							default -> e.reply("Invalid color provided.  Valid colors: `red` `orange` `yellow` `lightgreen` `green` `cyan` `blue` `darkblue` `purple` `magenta` `white` `black`");
						}
						SaveData.saveData(e, data);
					} catch(Exception exception) {
						e.reply("You have to specify a color.  Valid colors: `red` `orange` `yellow` `lightgreen` `green` `cyan` `blue` `darkblue` `purple` `magenta` `white` `black`");
					}
				}
				case "dms" -> setBooleanOption(e, data, "DMs",newValue);
				case "info" -> setBooleanOption(e, data, "extraInfo", newValue);
				default -> e.reply("Invalid option provided!  Valid settings: `color` `dms` `info`");
			}
		}
	}
}