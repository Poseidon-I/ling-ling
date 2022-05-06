package dev;

import economy.Start;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Ban {
	public Ban(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder reason = new StringBuilder();
		for(int i = 2; i < message.length; i++) {
			reason.append(" ").append(message[i]);
		}
		if(reason.isEmpty()) {
			reason.append("None");
		}
		String id = message[1];
		try {
			Long.parseLong(id);
		} catch(Exception exception) {
			e.getMessage().reply("You didn't provide a valid ID!").mentionRepliedUser(false).queue();
			return;
		}
		if(id.equals(e.getAuthor().getId())) {
			e.getMessage().reply("Imagine trying to ban yourself, how dumb are you???").mentionRepliedUser(false).queue();
		} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
			e.getMessage().reply("Imagine trying to ban a developer smh").mentionRepliedUser(false).queue();
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
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("734697505543159879")).sendMessage("""
					:hammer: **THE BAN HAMMER HAS SPOKEN** :hammer:
					            Someone has been banned.
					
					https://i.imgur.com/KTyk7EC.mp4""").queue();
			new LogCase(e, "BAN", id, reason.toString());
			e.getChannel().sendMessage(":hammer: " + user + " was successfully banned!").queue();
			Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("You were banned.  Reason: " + reason).queue());
			e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
		}
	}
}