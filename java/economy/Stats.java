package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class Stats {
	public Stats(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		JSONObject data;
		String user;
		if(message.length == 1) {
			user = e.getAuthor().getId();
			data = LoadData.loadData(e);
		} else {
			user = message[1];
			JSONParser parser = new JSONParser();
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.getMessage().reply("This save file does not exist!").mentionRepliedUser(false).queue();
				return;
			}
		}
		try {
			user = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
		} catch(Exception exception) {
			user = "Someone";
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle(user + "'s Stats");
		builder.addField("**__Gambling__**", "**Net Winnings**: " + Numbers.FormatNumber(data.get("winnings")) + "\n**Million-Dollar Tickets Drawn**: " + Numbers.FormatNumber(data.get("millions")), false);
		builder.addField("**__Robbing__**", "**Amount Earned from Robbing**: " + Numbers.FormatNumber(data.get("robbed")) + "\n**Amount Lost to Robbers**: " + Numbers.FormatNumber(data.get("lostToRob")), false);
		builder.addField("**__Commands__**", "**Hours Practised**: " + Numbers.FormatNumber((long) (double) data.get("hoursPractised")) + "\n**Scales Played**: " + Numbers.FormatNumber(data.get("scalesPlayed")) + "\n**Rehearsals Attended**: " + Numbers.FormatNumber(data.get("rehearsals")) + "\n**Performances Given**: " + Numbers.FormatNumber(data.get("performances")) + "\n**Hours Taught: **" + Numbers.FormatNumber((long) (double) data.get("hoursTaught")), false);
		builder.addField("**__Lootboxes__**", "**Gifts Given**: " + Numbers.FormatNumber(data.get("giftsGiven")) + "\n**Gifts Received**: " + Numbers.FormatNumber(data.get("giftsReceived")) + "\n**Number of Votes**: " + Numbers.FormatNumber(data.get("votes")), false);
		builder.addField("**__Miscellaneous__**", "**Highest Daily Streak**: " + Numbers.FormatNumber(data.get("maxStreak")) + "\n**Luthiers Unscrambled**: " + Numbers.FormatNumber(data.get("luthiers")) + "\n**Violins Earned**: " + Numbers.FormatNumber(data.get("earnings")) + "\n**Interest Earned**: " + Numbers.FormatNumber(data.get("interestEarned")) + "\n**Penalties Paid**: " + Numbers.FormatNumber(data.get("penaltiesIncurred")), false);
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
}