package regular;

import economy.LoadData;
import economy.SaveData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.Objects;

public class UserSettings {
	public static void setBooleanOption(SlashCommandInteractionEvent e, JSONObject data, String key) {
		try {
			String newValue = Objects.requireNonNull(e.getOption("newvalue")).getAsString();
			if(newValue.equals("on") || newValue.equals("true") || newValue.equals("yes")) {
				data.replace(key, true);
				SaveData.saveData(e, data);
				e.reply("Turned `" + key + "` on!").queue();
			} else if(newValue.equals("off") || newValue.equals("false") || newValue.equals("no")) {
				data.replace(key, false);
				SaveData.saveData(e, data);
				e.reply("Turned `" + key + "` off!").queue();
			} else {
				e.reply("Invalid option provided!  Valid options: `on`/`true`/`yes` or `off`/`false`/`no`").queue();
			}
		} catch(Exception exception) {
			e.reply("You must specify `on`/`true`/`yes` or `off`/`false`/`no`.").queue();
		}
	}
	public static void userSettings(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		try {
			String option = Objects.requireNonNull(e.getOption("option")).getAsString();
			switch(option) {
				case "color" -> {
					try {
						String newValue = Objects.requireNonNull(e.getOption("newvalue")).getAsString();
						switch(newValue) {
							case "black" -> {
								data.replace("color", "#000000");
								e.reply("Changed color to Black.").queue();
							}
							case "white" -> {
								data.replace("color", "#FFFFFF");
								e.reply("Changed color to White.").queue();
							}
							case "red" -> {
								data.replace("color", "#FF0000");
								e.reply("Changed color to Red.").queue();
							}
							case "orange" -> {
								data.replace("color", "#FF7F00");
								e.reply("Changed color to Orange.").queue();
							}
							case "yellow" -> {
								data.replace("color", "#FFFF00");
								e.reply("Changed color to Yellow.").queue();
							}
							case "lightgreen" -> {
								data.replace("color", "#00FF00");
								e.reply("Changed color to Light Green.").queue();
							}
							case "green" -> {
								data.replace("color", "#008F00");
								e.reply("Changed color to Green.").queue();
							}
							case "cyan" -> {
								data.replace("color", "#00FFFF");
								e.reply("Changed color to Cyan.").queue();
							}
							case "blue" -> {
								data.replace("color", "#0000FF");
								e.reply("Changed color to Blue.").queue();
							}
							case "darkblue" -> {
								data.replace("color", "#00008F");
								e.reply("Changed color to Dark Blue.").queue();
							}
							case "purple" -> {
								data.replace("color", "#7F007F");
								e.reply("Changed color to Purple.").queue();
							}
							case "magenta" -> {
								data.replace("color", "#FF00FF");
								e.reply("Changed color to Magenta.").queue();
							}
							default -> e.reply("Invalid color provided.  Valid colors: `red` `orange` `yellow` `lightgreen` `green` `cyan` `blue` `darkblue` `purple` `magenta` `white` `black`").queue();
						}
						SaveData.saveData(e, data);
					} catch(Exception exception) {
						e.reply("You have to specify a color.  Valid colors: `red` `orange` `yellow` `lightgreen` `green` `cyan` `blue` `darkblue` `purple` `magenta` `white` `black`").queue();
					}
				}
				case "dms" -> setBooleanOption(e, data, "DMs");
				case "info" -> setBooleanOption(e, data, "extraInfo");
				default -> e.reply("Invalid option provided!  Valid settings: `color` `dms` `info`").queue();
			}
		} catch(Exception exception) {
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.decode((String) data.get("color")))
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
					.setTitle("**__Settings for " + e.getUser().getName() + "__**")
					.addField("Color", "Sets the embed color for your commands.  Does not apply to `/help` for technical reasons.\nCurrent option: `" + data.get("color") + "`\nID: `color`", false)
					.addField("DMs", "Toggles whether DMs are sent for Rob and Gift.\nCurrent option: `" + data.get("DMs") + "`\nID: `dms`", false)
					.addField("Extra Information", "Toggles whether extra info is shown, like your chance of a successful `/rob` or the ticket breakdown from scratch.\nCurrent option: `" + data.get("extraInfo") + "`\nID: `info`", false);
			e.replyEmbeds(builder.build()).queue();
		}
	}
}