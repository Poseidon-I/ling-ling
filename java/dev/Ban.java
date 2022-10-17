package dev;

import economy.Start;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Ban {
	public static void ban(@NotNull SlashCommandInteractionEvent e) {
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
		
		if(id.equals(e.getUser().getId())) {
			e.reply("Imagine trying to ban yourself, how dumb are you???").queue();
		} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
			e.reply("Imagine trying to ban a developer smh").queue();
		} else {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
				data.replace("banned", true);
				FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				Start.start(e, id, true);
			}
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("1029498872441077860")).sendMessage("""
					:hammer: **THE BAN HAMMER HAS SPOKEN** :hammer:
					            Someone has been banned.
					
					https://i.imgur.com/KTyk7EC.mp4""").queue();
			LogCase.logCase(e, "BAN", id, reason);
			e.reply(":hammer: " + user + " was successfully banned!").queue();
			String finalReason = reason;
			Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("You were banned.  Reason: " + finalReason).queue());
		}
	}
}