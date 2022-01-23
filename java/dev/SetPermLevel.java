package dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class SetPermLevel {
	public SetPermLevel(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		String target = message[1];
		int newRank = Integer.parseInt(message[2]);
		if(newRank >= 0 && newRank <= 2) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.getMessage().reply("Invalid user provided!").mentionRepliedUser(false).queue();
				return;
			}
			data.replace("perms", newRank);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				e.getMessage().reply("How does an error appear here???").mentionRepliedUser(false).queue();
			}
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			e.getMessage().reply("Set the permission level for " + user + " to `" + newRank + "`").mentionRepliedUser(false).queue();
			
		} else {
			e.getMessage().reply("Invalid level provided!\n\nValid levels:\n`0`: No Permissions\n`1`: Mod Permissions\n`2`: Admin Permissions").mentionRepliedUser(false).queue();
		}
	}
}