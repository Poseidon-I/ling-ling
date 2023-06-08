package regular;

import eventListeners.GenericDiscordEvent;
import leveling.Leveling;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.FileReader;
import java.util.List;
// BEETHOVEN-ONLY CLASS
public class MessageLeaderboard {
	public static void messageLeaderboard(GenericDiscordEvent e) {
		e.getGuild().loadMembers();
		JSONObject data = Leveling.loadData(e);
		long userMessages = (long) data.get("messages");
		String[] entry = new String[]{e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + " `" + e.getAuthor().getId() + "`: " + data.get("messages") + " messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n", "null#0000 `0`: 0 messages\n"};
		List<Member> list = e.getGuild().getMembers();
		long place = 1;
		JSONParser parser = new JSONParser();
		for(Member user : list) {
			if(user.getUser().isBot()) {
				continue;
			}
			JSONObject currentData;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Leveling\\" + user.getId() + ".json")) {
				currentData = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				continue;
			}
			long messages;
			try {
				messages = (long) currentData.get("messages");
			} catch(Exception exception) {
				messages = 0;
			}
			if(messages == 0) {
				continue;
			}
			for(int i = 0; i < 10; i++) {
				if(messages > Long.parseLong(entry[i].split(" ")[entry[i].split(" ").length - 2]) && !user.getId().equals(e.getAuthor().getId())) {
					System.arraycopy(entry, i, entry, i + 1, 9 - i);
					if(user.getId().equals("768056391814086676")) {
						entry[i] = "**NARWHAL** `768056391814086676`: " + messages + " messages\n";
					} else {
						entry[i] = user.getUser().getName() + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: " + messages + " messages\n";
					}
					if(messages > userMessages) {
						place++;
					}
					break;
				}
			}
		}
		StringBuilder board = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			if(entry[i].contains("#0000")) {
				break;
			}
			board.append("**").append(i + 1).append(".** ").append(entry[i]);
		}
		if(place >= 11) {
			board.append("\n**").append(place).append(". You**: ").append(userMessages).append(" messages");
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle("__**XP Leaderboard**__")
				.addField("Most Active Users This Month", board.toString(), false);
		e.replyEmbeds(builder.build());
	}
}