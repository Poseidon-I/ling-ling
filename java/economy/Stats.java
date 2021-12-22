package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class Stats {
	public Stats(GuildMessageReceivedEvent e) {
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
				e.getChannel().sendMessage("This save file does not exist!").queue();
				throw new IllegalArgumentException();
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
		builder.addField("**__Gambling__**", "**Net Winnings**: " + data.get("winnings") + "\n**Million-Dollar Tickets Drawn**: " + data.get("millions"), false);
		builder.addField("**__Robbing__**", "**Amount Earned from Robbing**: " + data.get("robbed") + "\n**Amount Lost to Robbers**: " + data.get("lostToRob"), false);
		builder.addField("**__Commands__**", "**Hours Practised**: " + data.get("hoursPractised") + "\n**Scales Played**: " + data.get("scalesPlayed") + "\n**Rehearsals Attended**: " + data.get("rehearsals") + "\n**Performances Given**: " + data.get("performances") + "\n**Hours Taught: **" + data.get("hoursTaught"), false);
		builder.addField("**__Lootboxes__**", "**Gifts Given**: " + data.get("giftsGiven") + "\n**Gifts Received**: " + data.get("giftsReceived") + "\n**Number of Votes**: " + data.get("votes"), false);
		builder.addField("**__Miscellaneous__**", "**Highest Daily Streak**: " + data.get("maxStreak") + "\n**Luthiers Unscrambled**: " + data.get("luthiers") + "\n**Violins Earned**: " + data.get("earnings") + "\n**Interest Earned**: " + data.get("interestEarned") + "\n**Penalties Paid**: " + data.get("penaltiesIncurred"), false);
		e.getChannel().sendMessageEmbeds(builder.build()).queue();
	}
}