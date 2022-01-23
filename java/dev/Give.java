package dev;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Objects;

public class Give {
	public Give(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		String id;
		try {
			id = message[1];
		} catch(Exception exception) {
			e.getMessage().reply("You didn't even provide a user, dummy.").mentionRepliedUser(false).queue();
			return;
		}
		long add;
		try {
			add = Long.parseLong(message[3]);
		} catch(Exception exception) {
			e.getMessage().reply("You can only give an integer amount of items, stupid.").mentionRepliedUser(false).queue();
			return;
		}
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.getMessage().reply("This save file doesn't exist!").mentionRepliedUser(false).queue();
			return;
		}
		String[] validElements = {"violins", "medals", "rice", "tea", "blessings", "voteBox", "giftBox", "kits", "linglingBox", "crazyBox"};
		try {
			if(Arrays.asList(validElements).contains(message[2])) {
				String user;
				try {
					user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
				} catch(Exception exception) {
					user = "Someone";
				}
				long oldAmount = (long) data.get(message[2]);
				data.replace(message[2], oldAmount + add);
				e.getMessage().reply("Successfully gave `" + add + "` " + message[2] + " to " + user).mentionRepliedUser(false).queue();
			} else {
				e.getMessage().reply("You provided an invalid item!  Valid items to give: `violins`, `medals`, `rice`, `tea`, `blessings`, `voteBox`, `giftBox`, `kits`, `linglingBox`, `crazyBox`.").mentionRepliedUser(false).queue();
			}
		} catch(Exception exception) {
			e.getMessage().reply("You have to tell me what to give out, idiot, I can't give out nothing.").mentionRepliedUser(false).queue();
			return;
		}
		
		try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
			writer.write(data.toJSONString());
			writer.close();
		} catch(Exception exception) {
			//nothing here lol
		}
		
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Moderator: " + e.getAuthor().getName(), "User: <@" + id + ">\nItem type: " + message[2] + "\nAmount given: " + message[3], false)
				.setTitle("__**Currency Alteration Info**__");
		Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
}