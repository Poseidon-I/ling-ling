package dev;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Give {
	public static void give(GenericDiscordEvent e, String receiver, long amount, String item) {
		if(amount == -1) {
			e.reply("You have to give a number, I can't give an unknown amount of items.  Go study physics or something.");
			return;
		}
		if(amount == -2) {
			e.reply("You can only give an integer amount of items, stupid.");
			return;
		}
		if(receiver.equals("")) {
			e.reply("You didn't even provide a user, dummy.");
			return;
		}
		if(item.equals("")) {
			e.reply("You have to give out something, idiot, I can't give out nothing.");
			return;
		}
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + receiver + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("This save file doesn't exist!");
			return;
		}
		long oldAmount;
		try {
			oldAmount = (long) data.get(item);
		} catch(Exception exception) {
			e.reply("You should know that " + item + " IS NOT AN INTEGER ITEM, GO GROW A BRAIN");
			return;
		}
		String user;
		try {
			user = Objects.requireNonNull(e.getJDA().getUserById(receiver)).getName();
		} catch(Exception exception) {
			user = "Someone";
		}
		data.replace(item, oldAmount + amount);
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).addField("Moderator: " + e.getAuthor().getName(), "User: <@" + receiver + ">\nItem type: " + item + "\nAmount given: `" + Numbers.formatNumber(amount) + "`", false).setTitle("__**Currency Alteration Info**__");
		Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + receiver + ".json")) {
			writer.write(data.toJSONString());
			writer.close();
		} catch(Exception exception) {
			//nothing here lol
		}
		Objects.requireNonNull(e.getJDA().getUserById(receiver)).openPrivateChannel().queue((channel) -> channel.sendMessage("**SPAWNED BY AN ADMIN LOL**\nA Mod/Admin has given you `" + Numbers.formatNumber(amount) + "` " + item).queue());
		e.reply("Successfully gave `" + Numbers.formatNumber(amount) + "` " + item + " to " + user);
	}
}