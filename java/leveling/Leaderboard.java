package leveling;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.DatabaseManager;

import java.awt.*;
import java.util.List;
import java.util.Objects;
// BEETHOVEN-ONLY CLASS

public class Leaderboard {
	public static void leaderboard(GenericDiscordEvent e) {
		e.getGuild().loadMembers();
		JSONObject data = Leveling.loadData(e);
		long userLevel = (long) data.get("level");
		long userXP = (long) data.get("xp");
		String[] entry = new String[]{e.getAuthor().getGlobalName() + "** `" + e.getAuthor().getId() + "`: Level " + data.get("level") + " - " + data.get("xp") + " XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n", "<@0> `0`: Level 0 - 0 XP \n"};
		List<Member> list = e.getGuild().getMembers();
		long place = 1;
		JSONParser parser = new JSONParser();
		for(Member user : list) {
			if(user.getUser().isBot()) {
				continue;
			}
			JSONObject currentData = DatabaseManager.getDataForUser(e, "Leveling Data", user.getId());
			if(currentData == null) {
				continue;
			}
			long level = (long) currentData.get("level");
			long xp = (long) currentData.get("xp");
			if(level == 0 && xp == 0) {
				continue;
			}
			for(int i = 0; i < 10; i++) {
				if(level > Long.parseLong(entry[i].split(" ")[entry[i].split(" ").length - 5]) && !user.getId().equals(e.getAuthor().getId())) {
					System.arraycopy(entry, i, entry, i + 1, 9 - i);
					if(user.getId().equals("768056391814086676")) {
						entry[i] = "**NARWHAL** `768056391814086676`: Level " + level + " - " + xp + " XP \n";
					} else {
						entry[i] = user.getUser().getGlobalName() + "** `" + user.getId() + "`: Level " + level + " - " + xp + " XP \n";
					}
					if(level > userLevel) {
						place++;
					}
					break;
				} else if(level == Long.parseLong(entry[i].split(" ")[entry[i].split(" ").length - 5]) && xp > Long.parseLong(entry[i].split(" ")[entry[i].split(" ").length - 3]) && !user.getId().equals(e.getAuthor().getId())) {
					System.arraycopy(entry, i, entry, i + 1, 9 - i);
					if(user.getId().equals("768056391814086676")) {
						entry[i] = "**NARWHAL** `768056391814086676`: Level " + level + " - " + xp + " XP \n";
					} else {
						entry[i] = user.getUser().getGlobalName() + "** `" + user.getId() + "`: Level " + level + " - " + xp + " XP \n";
					}
					if(xp > userXP) {
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
			board.append("**").append(i + 1).append("\\. ").append(entry[i]);
		}
		if(place >= 11) {
			board.append("\n**").append(place).append(". You**: Level ").append(userLevel).append(" - ").append(userXP).append(" XP");
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle("__**XP Leaderboard**__")
				.addField("Most Active Users", board.toString(), false);
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
}