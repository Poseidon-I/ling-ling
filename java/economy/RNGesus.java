package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class RNGesus {
	public static JSONObject Lootbox(MessageReceivedEvent e, JSONObject data) {
		Random random = new Random();
		double chance = random.nextDouble() * (1 - (long) data.get("magicFind") * 0.001);
		if(chance > 0.00262) {
			return data;
		} else if(chance > 0.00062) { //0.002 (1 in 500)
			data.replace("kits", (long) data.get("kits") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 3);
			e.getMessage().reply("**Very Rare Drop!**\nYou found a Musician Kit while you were out and about.").mentionRepliedUser(true).queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + " `" + e.getAuthor().getId() + "`", "Drop: Musician Kit", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		} else if(chance > 0.00012) { //0.0005 (1 in 2000)
			data.replace("linglingBox", (long) data.get("linglingBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			e.getMessage().reply("**CRAZY RARE DROP!**\nYou found a Ling Ling Box sitting in your room!").mentionRepliedUser(true).queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + " `" + e.getAuthor().getId() + "`", "Drop: Ling Ling Box", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		} else if(chance > 0.00002) { //0.0001 (1 in 10000)
			data.replace("crazyBox", (long) data.get("crazyBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 6);
			e.getMessage().reply("**CRAZY RARE DROP!**\nYou see a CRAZY PERSON BOX appear in front of you!").mentionRepliedUser(true).queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + " `" + e.getAuthor().getId() + "`", "Drop: Crazy Person box", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
		} else { // 0.00002 (1 in 50000)
			data.replace("RNGesusBox", (long) data.get("RNGesusBox") + 1);
			data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 10);
			e.getMessage().reply("**__INSANE DROP!__**\nYou see an **__RNGESUS BOX__** appear in front of you!").mentionRepliedUser(true).queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setTitle("RNGesus Drop!")
					.addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + " `" + e.getAuthor().getId() + "`", "Drop: RNGesus Box", false);
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("734697505543159879")).sendMessage("WOW!  `" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + "` found an **__RNGESUS BOX__**!!!").queue();
		}
		return data;
	}
}