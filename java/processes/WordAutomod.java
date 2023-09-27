package processes;

import com.vdurmont.emoji.EmojiParser;
import eventListeners.GenericDiscordEvent;
import leveling.Leveling;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// BEETHOVEN-ONLY CLASS
public class WordAutomod {
	public static void moderate(GenericDiscordEvent e, String category, boolean delete) {
		EmbedBuilder builder = new EmbedBuilder().setFooter("Beethoven", e.getJDA().getSelfUser().getAvatarUrl())
				.setColor(Color.RED);
		if(delete) {
			e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
			builder.addField("Offender: " + e.getAuthor().getName(), "Category: " + category + "\nRefer to Carl's message below for the full message.", false)
					.setTitle("Message Deleted");
			e.getAuthor().openPrivateChannel().complete().sendMessage("Your message was deleted for the following reason: " + category + "\nYou have lost 10 XP and 1 Message").queue();
		} else {
			builder.addField("Offender: " + e.getAuthor().getName(), "Category: " + category + "\n" + e.getMessage().getJumpUrl(), false)
					.setTitle("Beethoven Warning");
			e.getAuthor().openPrivateChannel().complete().sendMessage("You have been warned for the following reason: " + category + "\nYou have lost 10 XP and 1 Message\n**This is NOT a Carl-bot Warning**").queue();
		}
		Objects.requireNonNull(e.getGuild().getTextChannelById("734697496688853012")).sendMessageEmbeds(builder.build()).queue();
		JSONObject data = Leveling.loadData(e);
		data.replace("xp", (long) data.get("xp") - 10);
		data.replace("messages", (long) data.get("messages") - 1);
		Leveling.saveData(e, data);
	}

	public static void wordAutomod(GenericDiscordEvent e) {
		List<String> messageArray = Arrays.stream(e.getMessage().getContentRaw().split(" ")).toList();
		String message = EmojiParser.parseToAliases(e.getMessage().getContentRaw()).toLowerCase();
		if(messageArray.contains("bruce") && Objects.requireNonNull(e.getGuild().getMember(e.getAuthor())).getRoles().contains(e.getGuild().getRoleById("759521422015725568"))) {
			moderate(e, "names", true);
		}
		if(message.contains("foraging")) {
			moderate(e, "f\\*raging", false);
		}
		int caps = 0;
		int zalgos = 0;
		int letters = 0;
		ArrayList<Character> bad = new ArrayList<>();
		for(int i = 0; i < message.length(); i++) {
			char character = message.charAt(i);
			if(character >= 65 && character <= 90) {
				caps++;
				letters++;
			} else if(character >= 97 && character <= 122) {
				letters++;
			}
			if(character > 127) {
				zalgos++;
				bad.add(character);
			}
		}

		if(message.contains("(╯°□°）╯︵ ┻━┻")) {
			zalgos -= 10;
		}
		if(message.contains("┬─┬ ノ( ゜-゜ノ)")) {
			zalgos -= 7;
		}
		if(message.contains("¯\\_(ツ)_/¯")) {
			zalgos -= 3;
		}

		if(message.length() > 10) {
			if((double) zalgos / message.length() > 0.1) {
				moderate(e, "z̶αłg̸ø\n" + zalgos + " Zalgos\nOffending Characters: " + bad + "\n% zalgo: " + (double) zalgos / message.length(), false);
			}
		}
		if(letters > 50) {
			if(caps == letters) {
				moderate(e, "SCREAMING", false);
			} else if((double) caps / letters > 0.5) {
				moderate(e, "sCrEaMiNg", false);
			}
		} else if(letters > 25) {
			if(caps == letters) {
				moderate(e, "SCREAMING", false);
			}
		}
	}
}