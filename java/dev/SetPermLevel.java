package dev;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class SetPermLevel {
	public static void setPermLevel(@NotNull SlashCommandInteractionEvent e) {
		String target;
		try {
			target = Objects.requireNonNull(e.getOption("user")).getAsString();
		} catch(NullPointerException exception) {
			e.reply("You have to provide a user to promote or demote, dumbass.").queue();
			return;
		}
		
		int newRank;
		try {
			newRank = Integer.parseInt(Objects.requireNonNull(e.getOption("rank")).getAsString());
		} catch(NullPointerException exception) {
			e.reply("You have to provide a rank to move the user to, stupid.").setEphemeral(true).queue();
			return;
		}
		
		if(newRank >= 0 && newRank <= 2) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.reply("Invalid user provided!").setEphemeral(true).queue();
				return;
			}
			data.replace("perms", newRank);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				e.reply("How does an error appear here???").setEphemeral(true).queue();
			}
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			e.reply("Set the permission level for " + user + " to `" + newRank + "`").queue();
			
		} else {
			e.reply("Invalid level provided!\n\nValid levels:\n`0`: No Permissions\n`1`: Mod Permissions\n`2`: Admin Permissions").setEphemeral(true).queue();
		}
	}
}