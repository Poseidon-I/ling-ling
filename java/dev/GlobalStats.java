package dev;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.DatabaseManager;
import processes.Numbers;

import java.awt.*;
import java.util.ArrayList;

public class GlobalStats {
	public static void gobalStats(GenericDiscordEvent e) {
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
		long purchases = 0;
		long marketEarnings = 0;
		ArrayList<Document> documents = DatabaseManager.getAllEconomyData();
		for(Document document : documents) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try {
				data = (JSONObject) parser.parse(document.toJson());
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
			purchases += (long) data.get("itemsBought");
			marketEarnings += (long) data.get("moneyEarned");
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle("Stats of the World");
		builder.addField("**__Gambling & Robbing__**", "**Net Winnings**: `" + Numbers.formatNumber(winnings) + "`\n**Million-Dollar Tickets Drawn**: `" + Numbers.formatNumber(millions) + "`\n**Amount Earned from Robbing**: `" + Numbers.formatNumber(robEarned) + "`\n**Total RNGesus Weight**: `" + Numbers.formatNumber(weight) + "`", true);
		builder.addBlankField(true);
		builder.addField("**__Commands__**", "**Hours Practised**: `" + Numbers.formatNumber((long) hours) + "`\n**Scales Played**: `" + Numbers.formatNumber(scales) + "`\n**Rehearsals Attended**: `" + Numbers.formatNumber(rehearsals) + "`\n**Performances Given**: `" + Numbers.formatNumber(performances) + "`\n**Hours Taught: **`" + Numbers.formatNumber((long) taught) + "`", true);
		builder.addField("**__Lootboxes & Market__**", "**Gifts Given**: `" + Numbers.formatNumber(giftsGiven) + "`\n**Number of Votes**: `" + Numbers.formatNumber(votes) + "`\n**Items Purchased on Market**: `" + Numbers.formatNumber(purchases) + "`\n**Money Earned from Market**: `" + Numbers.formatNumber(marketEarnings) + "`", true);
		builder.addBlankField(true);
		builder.addField("**__Miscellaneous__**", "**Highest Daily Streak**: `" + Numbers.formatNumber(maxStreak) + "`\n**Luthiers Unscrambled**: `" + Numbers.formatNumber(luthiers) + "`\n**Violins Earned**: `" + Numbers.formatNumber(earnings) + "`\n**Interest Earned**: `" + Numbers.formatNumber(interest) + "`\n**Penalties Paid**: `" + Numbers.formatNumber(penalties) + "`", true);
		e.replyEmbeds(builder.build());
	}
}
