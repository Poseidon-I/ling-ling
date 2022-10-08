package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class RNGesus {
	public static JSONObject lootbox(SlashCommandInteractionEvent e, JSONObject data) {
		Random random = new Random();
		double chance = random.nextDouble();
		double increase = 1 + (long) data.get("magicFind") * 0.01;
		if(chance > 0.00524 * increase) {
			return data;
		} else if(chance > 0.00124 * increase) { //0.004 (1 in 250)
			data.replace("kits", (long) data.get("kits") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 3);
			e.getChannel().sendMessage("**Very Rare Drop!** " + Emoji.MUSICIAN_KIT + "\nYou found a Musician Kit while you were out and about.").queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + " `" + e.getUser().getId() + "`", "Drop: Musician Kit", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		} else if(chance > 0.00024 * increase) { //0.001 (1 in 1000)
			data.replace("linglingBox", (long) data.get("linglingBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			e.getChannel().sendMessage("**CRAZY RARE DROP!** " + Emoji.LING_LING_BOX + "\nYou found a Ling Ling Box sitting in your room!").queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + " `" + e.getUser().getId() + "`", "Drop: Ling Ling Box", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		} else if(chance > 0.00004 * increase) { //0.0002 (1 in 5000)
			data.replace("crazyBox", (long) data.get("crazyBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			e.reply("**CRAZY RARE DROP!** " + Emoji.CRAZY_BOX + "\nYou see a CRAZY PERSON BOX appear in front of you!").queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + " `" + e.getUser().getId() + "`", "Drop: Crazy Person box", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		} else { // 0.00004 (1 in 25000)
			data.replace("RNGesusBox", (long) data.get("RNGesusBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 10);
			e.reply("**__INSANE DROP!__** " + Emoji.RNGESUS_BOX + "\nYou see an **__RNGESUS BOX__** appear in front of you! GG!").mentionRepliedUser(true).queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + " `" + e.getUser().getId() + "`", "Drop: RNGesus Box", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("734697505543159879")).sendMessage("WOW!  `" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + "` found an **__RNGESUS BOX__**!!!").queue();
		}
		return data;
	}
}