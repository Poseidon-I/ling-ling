package processes;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// BEETHOVEN-ONLY CLASS

public class CheckGiveaways {
	public static void completeGiveaway(Message message, long numWinners, String thing) {
		List<User> list = message.getReactions().get(0).retrieveUsers().complete();
		ArrayList<Integer> hasWon = new ArrayList<>();
		boolean nobody = false;
		for(int i = 0; i < numWinners; i++) {
			Random random = new Random();
			while(true) {
				int index = random.nextInt(list.size());
				if(!hasWon.contains(index) && !list.get(index).isBot()) {
					hasWon.add(index);
					break;
				} else if(list.size() == 1) {
					message.getChannel().sendMessage("Nobody entered!  How sad.\n\n" + message.getJumpUrl()).queue();
					nobody = true;
					break;
				}
			}
		}
		StringBuilder winnerList = new StringBuilder();
		if(nobody) {
			message.getChannel().editMessageById(message.getId(), ":tada: **Giveaway for " + thing + " has ENDED :tada:\n\nWinners - nobody :(").queue();
		} else {
			for(int i = 0; i < numWinners; i++) {
				String string = list.get(hasWon.get(i)).getAsMention();
				winnerList.append(string).append(" ");
			}
			winnerList.deleteCharAt(winnerList.length() - 1);
			message.getChannel().editMessageById(message.getId(), ":tada: **Giveaway for __" + thing + "__** has ENDED :tada:\n\nWinners - " + winnerList).queue();
			message.getChannel().sendMessage("**Congratulations " + winnerList + "!**  You have won **" + thing + "**!\n\n" + message.getJumpUrl()).queue();
		}
		File file = new File("Ling Ling Bot Data\\Giveaways\\" + message.getId() + ".json");
		file.delete();
	}
	
	public static void checkGiveaways(GenericDiscordEvent e) {
		TextChannel channel = e.getGuild().getTextChannelById("734697492490354768");
		File[] files = new File("Ling Ling Bot Data\\Giveaways").listFiles();
		assert files != null;
		JSONParser parser = new JSONParser();
		for(File file : files) {
			JSONObject data;
			try(FileReader reader = new FileReader(file)) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				exception.printStackTrace();
				continue;
			}
			assert channel != null;
			Message message;
			try {
				message = channel.retrieveMessageById(file.getName().substring(0, file.getName().lastIndexOf('.'))).complete();
			} catch(Exception exception) {
				exception.printStackTrace();
				continue;
			}
			assert message != null;
			long winners = (long) data.get("winners");
			String item = (String) data.get("item");
			long endTime = (long) data.get("end");
			long minutes = (endTime - System.currentTimeMillis()) / 60000;
			boolean hasRequirement = (boolean) data.get("requirement");
			if(minutes > 0) {
				String send = ":tada: **GIVEAWAY for __" + item + "__** :tada:\n\nWinners: `" + winners + "`\nEnds in `" + minutes + "` Minutes\n\nReact to Enter! <@&734697425595531285> <@&750876814842527754>";
				if(hasRequirement) {
					send += "\n\n:warning: **This giveaway has a REQUIREMENT that can be found below.  Failure to complete this requirement will result in a warning and a blacklist from all giveaways and events for up to a week.**";
				}
				channel.editMessageById(message.getId(), send).queue();
			} else {
				completeGiveaway(message, winners, item);
			}
		}
		if(!e.getChannel().getId().equals("734697496688853012") && !e.getChannel().getId().equals("863135059712409632")) {
			e.sendMessage("Done!");
		}
	}
}