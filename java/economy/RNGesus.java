package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class RNGesus {
	public static void sendLog(SlashCommandInteractionEvent e, String drop) {
		EmbedBuilder builder = new EmbedBuilder()
				.setTitle("RNGesus Drop!")
				.addField("User: " + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + " `" + e.getUser().getId() + "`", "Drop: " + drop, false);
		switch(drop) {
			case "Musician Kit" -> builder.setColor(Color.BLUE);
			case "Ling Ling Box", "Crazy Person Box" -> builder.setColor(Color.MAGENTA);
			case "RNGesus Box" -> builder.setColor(Color.RED);
		}
		Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("1029498872441077860")).sendMessageEmbeds(builder.build()).queue();
	}
	
	public static JSONObject lootbox(SlashCommandInteractionEvent e, JSONObject data) {
		Random random = new Random();
		double chance = random.nextDouble();
		double increase = 1.0 + (long) data.get("magicFind") * 0.01;
		if(chance > 0.01375 * increase) {
			return data;
		} else if(chance > 0.00375 * increase) { //0.01 (1 in 100)
			data.replace("kits", (long) data.get("kits") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 3);
			sendLog(e, "Musician Kit");
			e.getChannel().sendMessage("**Very Rare Drop!** " + Emoji.MUSICIAN_KIT + "<@" + e.getUser().getId() + ">\nYou found a Musician Kit while you were out and about.").queue();
		} else if(chance > 0.00125 * increase) { //0.0025 (1 in 400)
			data.replace("linglingBox", (long) data.get("linglingBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			e.getChannel().sendMessage("**CRAZY RARE DROP!** " + Emoji.LING_LING_BOX + "<@" + e.getUser().getId() + ">\nYou found a Ling Ling Box sitting in your room!").queue();
			sendLog(e, "Ling Ling Box");
		} else if(chance > 0.00025 * increase) { // 0.001 (1 in 1000)
			data.replace("crazyBox", (long) data.get("crazyBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			e.reply("**CRAZY RARE DROP!** " + Emoji.CRAZY_BOX + "<@" + e.getUser().getId() + ">\nYou see a CRAZY PERSON BOX appear in front of you!").queue();
			sendLog(e, "Crazy Person Box");
		} else { // 0.00025 (1 in 4000)
			data.replace("RNGesusBox", (long) data.get("RNGesusBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 10);
			e.reply("**__INSANE DROP!__** " + Emoji.RNGESUS_BOX + "<@" + e.getUser().getId() + ">\nYou see an **__RNGESUS BOX__** appear in front of you! GG!").mentionRepliedUser(true).queue();
			sendLog(e, "RNGesus Box");
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("734697505543159879")).sendMessage("WOW!  `" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + "` found an **__RNGESUS BOX__**!!!").queue();
		}
		return data;
	}
}