package dev;

import economy.Start;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Ban {
	public Ban(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder reason = new StringBuilder();
		for(int i = 2; i < message.length; i++) {
			reason.append(" ").append(message[i]);
		}
		if(reason.isEmpty()) {
			reason.append("None");
		}
		e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
		String id = message[1];
		try {
			Long.parseLong(id);
		} catch(Exception exception) {
			e.getChannel().sendMessage("You didn't provide a valid ID!").queue();
			throw new IllegalArgumentException();
		}
		if(id.equals(e.getAuthor().getId())) {
			e.getChannel().sendMessage("Imagine trying to ban yourself, how dumb are you???").queue();
		} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
			e.getChannel().sendMessage("Imagine trying to ban a developer smh").queue();
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
				new Start(e, id, true);
			}
			e.getChannel().sendMessage(":hammer: <@" + id + "> was successfully banned!").queue();
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("734697505543159879")).sendMessage("""
					:hammer: **THE BAN HAMMER HAS SPOKEN** :hammer:
					            Someone has been banned.
					
					https://i.imgur.com/KTyk7EC.mp4""").queue();
			Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().complete().sendMessage("You were banned.  Reason: " + reason).queue();
			new LogCase(e, "BAN", id, reason.toString());
		}
	}
}