package dev;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Unban {
	public static void unban(@NotNull SlashCommandInteractionEvent e) {
		String id;
		try {
			id = Objects.requireNonNull(e.getOption("user")).getAsString();
			Long.parseLong(id);
		} catch(NullPointerException exception) {
			e.reply("You didn't provide an ID!").setEphemeral(true).queue();
			return;
		} catch(Exception exception) {
			e.reply("You didn't provide a valid ID!").setEphemeral(true).queue();
			return;
		}
		
		String reason;
		try {
			reason = Objects.requireNonNull(e.getOption("reason")).getAsString();
		} catch(Exception exception) {
			reason = "None";
		}
		
		JSONParser parser = new JSONParser();
		JSONObject data;
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
		try(FileReader reader = new FileReader(file.getAbsolutePath())) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("File doesn't exist!  Looks like they never used the bot to begin with...").queue();
			return;
		}
		if((Boolean) data.get("banned")) {
			boolean resetSave;
			try {
				resetSave = Boolean.parseBoolean(Objects.requireNonNull(e.getOption("resetsave")).getAsString());
			} catch(Exception exception) {
				e.reply("You have to tell me whether you want to reset the save or not, dumb.").setEphemeral(true).queue();
				return;
			}
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			e.reply(":white_check_mark: " + user + " was successfully unbanned!").queue();
			if(resetSave) {
				file.delete();
				reason += "\nSave Reset: Yes";
			} else {
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
					data = (JSONObject) parser.parse(reader);
					reader.close();
					data.replace("banned", false);
					
					FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
					writer.write(data.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				reason += "\nSave Reset: No";
			}
			LogCase.logCase(e, "Unban", id, reason);
			String finalReason = reason;
			Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("You have been unbanned.  Reason: " + finalReason).queue());
		} else {
			e.reply("That user isn't even banned, stupid.").queue();
		}
	}
}