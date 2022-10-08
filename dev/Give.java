package dev;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Give {
	public static void give(@NotNull SlashCommandInteractionEvent e) {
		String id;
		try {
			id = Objects.requireNonNull(e.getOption("user")).getAsString();
		} catch(NullPointerException exception) {
			e.reply("You didn't provide a valid user, dummy.").setEphemeral(true).queue();
			return;
		} catch(Exception exception) {
			e.reply("You didn't even provide a user, dummy.").setEphemeral(true).queue();
			return;
		}
		
		long add;
		try {
			add = Long.parseLong(Objects.requireNonNull(e.getOption("amount")).getAsString());
		} catch(NullPointerException exception) {
			e.reply("You have to give a number, I can't give an unknown amount of items.  Go study physics or something.").setEphemeral(true).queue();
			return;
		} catch(Exception exception) {
			e.reply("You can only give an integer amount of items, stupid.").setEphemeral(true).queue();
			return;
		}
		
		JSONParser parser = new JSONParser();
		JSONObject data;
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("This save file doesn't exist!").queue();
			return;
		}
		String item;
		try {
			item = Objects.requireNonNull(e.getOption("item")).getAsString();
		} catch(Exception exception) {
			e.reply("You have to give out something, idiot, I can't give out nothing.").setEphemeral(true).queue();
			return;
		}
		long oldAmount;
		try {
			oldAmount = (long) data.get(item);
		} catch(Exception exception) {
			e.reply("You should know that " + item + " IS NOT AN INTEGER ITEM, GO GROW A BRAIN").setEphemeral(true).queue();
			return;
		}
		String user;
		try {
			user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
		} catch(Exception exception) {
			user = "Someone";
		}
		data.replace(item, oldAmount + add);
		e.reply("Successfully gave `" + Numbers.formatNumber(add) + "` " + item + " to " + user).queue();
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Moderator: " + e.getUser().getName(), "User: <@" + id + ">\nItem type: " + item + "\nAmount given: " + add, false)
				.setTitle("__**Currency Alteration Info**__");
		Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
			writer.write(data.toJSONString());
			writer.close();
		} catch(Exception exception) {
			//nothing here lol
		}
		Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("**SPAWNED BY AN ADMIN LOL**\nA Mod/Admin has given you `" + Numbers.formatNumber(add) + "` " + item).queue());
	}
}