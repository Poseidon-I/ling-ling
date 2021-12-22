package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class SetPermLevel {
	public SetPermLevel(GuildMessageReceivedEvent e) {
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
				e.getChannel().sendMessage("Invalid user provided!").queue();
				throw new IllegalArgumentException();
			}
			data.replace("perms", newRank);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				e.getChannel().sendMessage("How does an error appear here???").queue();
			}
			e.getChannel().sendMessage("Set the permission level for <@" + target + "> to `" + newRank + "`").queue();
			
		} else {
			e.getChannel().sendMessage("Invalid level provided!\n\nValid levels:\n`0`: No Permissions\n`1`: Mod Permissions\n`2`: Admin Permissions").queue();
		}
	}
}