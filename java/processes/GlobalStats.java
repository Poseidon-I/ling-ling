package processes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class GlobalStats {
	public GlobalStats(MessageReceivedEvent e) {
		long winnings = 0;
		long millions = 0;
		long robEarned = 0;
		long weight = 0;
		double hours = 0;
		long scales = 0;
		long rehearsals = 0;
		long performances = 0;
		double taught = 0;
		long giftsGiven = 0;
		long votes = 0;
		long maxStreak = 0;
		long luthiers = 0;
		long earnings = 0;
		long interest = 0;
		long penalties = 0;
		File directory = new File("Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		assert files != null;
		for(File file : files) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader(file)) {
				data = (JSONObject) parser.parse(reader);
			} catch(Exception exception) {
				continue;
			}
			winnings += (long) data.get("winnings");
			millions += (long) data.get("millions");
			robEarned += (long) data.get("robbed");
			weight += (long) data.get("RNGesusWeight");
			hours += (double) data.get("hoursPractised");
			scales += (long) data.get("scalesPlayed");
			rehearsals += (long) data.get("rehearsals");
			performances += (long) data.get("performances");
			taught += (double) data.get("hoursTaught");
			giftsGiven += (long) data.get("giftsGiven");
			votes += (long) data.get("votes");
			maxStreak = Math.max(maxStreak, (long) data.get("maxStreak"));
			luthiers += (long) data.get("luthiers");
			earnings += (long) data.get("earnings");
			interest += (long) data.get("interestEarned");
			penalties += (long) data.get("penaltiesIncurred");
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle("Stats of the World");
		builder.addField("**__Gambling__**", "**Net Winnings**: " + Numbers.FormatNumber(winnings) + "\n**Million-Dollar Tickets Drawn**: " + Numbers.FormatNumber(millions) + "\n**Amount Earned from Robbing**: " + Numbers.FormatNumber(robEarned) + "\n**Total RNGesus Weight**: " + Numbers.FormatNumber(weight), false);
		builder.addField("**__Commands__**", "**Hours Practised**: " + Numbers.FormatNumber((long) hours) + "\n**Scales Played**: " + Numbers.FormatNumber(scales) + "\n**Rehearsals Attended**: " + Numbers.FormatNumber(rehearsals) + "\n**Performances Given**: " + Numbers.FormatNumber(performances) + "\n**Hours Taught: **" + Numbers.FormatNumber((long) taught), false);
		builder.addField("**__Lootboxes__**", "**Gifts Given**: " + Numbers.FormatNumber(giftsGiven) + "\n**Number of Votes**: " + Numbers.FormatNumber(votes), false);
		builder.addField("**__Miscellaneous__**", "**Highest Daily Streak**: " + Numbers.FormatNumber(maxStreak) + "\n**Luthiers Unscrambled**: " + Numbers.FormatNumber(luthiers) + "\n**Violins Earned**: " + Numbers.FormatNumber(earnings) + "\n**Interest Earned**: " + Numbers.FormatNumber(interest) + "\n**Penalties Paid**: " + Numbers.FormatNumber(penalties), false);
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
}
