package economy;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class RNGesus {
	private static double chance;
	private static StringBuilder message;
	private static boolean extraInfo;
	private static double increase;
	
	public static void sendLog(GenericDiscordEvent e, String drop) {
		EmbedBuilder builder = new EmbedBuilder()
				.setTitle("RNGesus Drop!")
				.addField("User: **" + e.getAuthor().getName() + "** `" + e.getAuthor().getId() + "`", "Drop: " + drop, false);
		switch(drop) {
			case "Musician Kit" -> {
				builder.setColor(Color.BLUE);
				if(extraInfo) {
					message.append("\n*You rolled `").append(chance).append("`, the range for obtaining this box was between `").append(0.00375 * increase).append("` and `").append(0.01375 * increase).append("`*");
				}
				builder.addField("Decimal rolled: `" +  chance + "`", "Range: `" + 0.00375 * increase + "` - `" + 0.01375 * increase + "`", false);
			}
			case "Ling Ling Box" -> {
				builder.setColor(Color.MAGENTA);
				if(extraInfo) {
					message.append("\n*You rolled `").append(chance).append("`, the range for obtaining this box was between `").append(0.00125 * increase).append("` and `").append(0.00375 * increase).append("`*");
				}
				builder.addField("Decimal rolled: `" +  chance + "`", "Range: `" + 0.00125 * increase + "` - `" + 0.00375 * increase + "`", false);
			}
			case "Crazy Person Box" -> {
				builder.setColor(Color.MAGENTA);
				if(extraInfo) {
					message.append("\n*You rolled `").append(chance).append("`, the range for obtaining this box was between `").append(0.00025 * increase).append("` and `").append(0.00125 * increase).append("`*");
				}
				builder.addField("Decimal rolled: `" +  chance + "`", "Range: `" + 0.00025 * increase + "` - `" + 0.00125 * increase + "`", false);
			}
			case "RNGesus Box" -> {
				builder.setColor(Color.RED);
				if(extraInfo) {
					message.append("\n*You rolled `").append(chance).append("`, the range for obtaining this box was between `0` and `").append(0.00025 * increase).append("`*");
				}
				builder.addField("Decimal rolled: `" +  chance + "`", "Range: `0` - `" + 0.00025 * increase + "`", false);
			}
		}
		Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("1029498872441077860")).sendMessageEmbeds(builder.build()).queue();
		e.getChannel().sendMessage(message).queue();
	}
	
	public static JSONObject lootbox(GenericDiscordEvent e, JSONObject data) {
		message = new StringBuilder();
		Random random = new Random();
		chance = random.nextDouble();
		increase = 1.0 + (long) data.get("magicFind") * 0.01;
		extraInfo = (boolean) data.get("extraInfo");
		if(chance > 0.01375 * increase) {
			return data;
		} else if(chance > 0.00375 * increase) { //0.01 (1 in 100)
			data.replace("kits", (long) data.get("kits") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 3);
			message.append("**Very Rare Drop!** " + Emoji.MUSICIAN_KIT + " <@").append(e.getAuthor().getId()).append(">\nYou found a Musician Kit while you were out and about.");
			sendLog(e, "Musician Kit");
		} else if(chance > 0.00125 * increase) { //0.0025 (1 in 400)
			data.replace("linglingBox", (long) data.get("linglingBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			message.append("**CRAZY RARE DROP!** " + Emoji.LING_LING_BOX + " <@").append(e.getAuthor().getId()).append(">\nYou found a Ling Ling Box sitting in your room!");
			sendLog(e, "Ling Ling Box");
		} else if(chance > 0.00025 * increase) { // 0.001 (1 in 1000)
			data.replace("crazyBox", (long) data.get("crazyBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			message.append("**CRAZY RARE DROP!** " + Emoji.CRAZY_BOX + " <@").append(e.getAuthor().getId()).append(">\nYou see a CRAZY PERSON BOX appear in front of you!");
			sendLog(e, "Crazy Person Box");
		} else { // 0.00025 (1 in 4000)
			data.replace("RNGesusBox", (long) data.get("RNGesusBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 10);
			message.append("https://imgur.com/a/SSjcgz3 " + Emoji.RNGESUS_BOX + " <@").append(e.getAuthor().getId()).append(">\nYou see an **__RNGESUS BOX__** appear in front of you! GG!");
			sendLog(e, "RNGesus Box");
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("734697505543159879")).sendMessage("WOW!  `" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + "` found an **__RNGESUS BOX__**!!!").queue();
		}
		return data;
	}
}