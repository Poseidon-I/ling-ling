package economy;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;
import processes.DatabaseManager;
import processes.Numbers;

import java.awt.*;

public class Stats {
	public static void stats(GenericDiscordEvent e, String user) {
		JSONObject data = DatabaseManager.getDataForUser(e, "Economy Data", user);
		if(data == null) {
			e.reply("This save file does not exist!");
			return;
		}
		if(user.equals("768056391814086676")) {
			user = "**NARWHAL**";
		} else {
			try {
				user = data.get("discordName").toString();
			} catch(Exception exception) {
				user = "Someone";
			}
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.decode((String) data.get("color")))
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle(user + "'s Stats")
				.addField("**__RNGesus__**", "**Net Winnings**: `" + Numbers.formatNumber(data.get("winnings")) +
						"`\n**Million-Dollar Tickets Drawn**: `" + Numbers.formatNumber(data.get("millions")) +
						"`\n**Magic Find**: `" + Numbers.formatNumber(data.get("magicFind")) +
						"`\n**RNGesus Weight**: `" + Numbers.formatNumber(data.get("RNGesusWeight")) + "`", true)
				.addBlankField(true)
				.addField("**__Robbing__**", "**Amount Earned from Robbing**: `" + Numbers.formatNumber(data.get("robbed")) +
						"`\n**Amount Lost to Robbers**: `" + Numbers.formatNumber(data.get("lostToRob")) + "`", true)
				.addField("**__Commands__**", "**Hours Practised**: `" + Numbers.formatNumber((long) (double) data.get("hoursPractised")) +
						"`\n**Scales Played**: `" + Numbers.formatNumber(data.get("scalesPlayed")) +
						"`\n**Rehearsals Attended**: `" + Numbers.formatNumber(data.get("rehearsals")) +
						"`\n**Performances Given**: `" + Numbers.formatNumber(data.get("performances")) +
						"`\n**Hours Taught: **`" + Numbers.formatNumber((long) (double) data.get("hoursTaught")) + "`", true)
				.addBlankField(true)
				.addField("**__Lootboxes__**", "**Gifts Given**: `" + Numbers.formatNumber(data.get("giftsGiven")) +
						"`\n**Gifts Received**: `" + Numbers.formatNumber(data.get("giftsReceived")) +
						"`\n**Free Boxes Claimed**: `" + Numbers.formatNumber(data.get("votes")) + "`", true)
				.addField("**__Market__**", "**Items Purchased**: `" + Numbers.formatNumber(data.get("itemsBought")) +
						"`\n**Items Sold**: `" + Numbers.formatNumber(data.get("itemsSold")) +
						"`\n**Money Earned**: `" + Numbers.formatNumber(data.get("moneyEarned")) +
						"`\n**Money Spent**: `" + Numbers.formatNumber(data.get("moneySpent")) +
						"`\n**Taxes Paid**: `" + Numbers.formatNumber(data.get("taxPaid")) + "`", true)
				.addBlankField(true)
				.addField("**__Miscellaneous__**", "**Highest Daily Streak**: `" + Numbers.formatNumber(data.get("maxStreak")) +
						"`\n**Luthiers Unscrambled**: `" + Numbers.formatNumber(data.get("luthiers")) +
						"`\n**Violins Earned**: `" + Numbers.formatNumber(data.get("earnings")) +
						"`\n**Interest Earned**: `" + Numbers.formatNumber(data.get("interestEarned")) +
						"`\n**Penalties Paid**: `" + Numbers.formatNumber(data.get("penaltiesIncurred")) + "`", true);
		e.replyEmbeds(builder.build());
	}
}