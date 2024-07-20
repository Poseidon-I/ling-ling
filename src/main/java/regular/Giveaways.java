package regular;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.bson.Document;
import processes.DatabaseManager;

// BEETHOVEN-ONLY CLASS
public class Giveaways {
	public static void giveaways(GenericDiscordEvent e, String[] message) {
		e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
		int minutes;
		try {
			minutes = Integer.parseInt(message[2]);
		} catch(Exception exception) {
			e.reply("You must provide a valid number of minutes!");
			throw new IllegalArgumentException();
		}
		int winners;
		try {
			winners = Integer.parseInt(message[3]);
		} catch(Exception exception) {
			e.reply("You must provide a valid number of winners!");
			throw new IllegalArgumentException();
		}
		boolean hasRequirement = message[4].equals("true");
		int i = 5;
		if(!message[4].equals("true") && !message[4].equals("false")) {
			i = 4;
		}
		StringBuilder item = new StringBuilder();
		for(; i < message.length; i++) {
			item.append(message[i]).append(" ");
		}
		item.deleteCharAt(item.length() - 1);
		String itemReal = item.toString();
		String send = ":tada: **GIVEAWAY for __" + item + "__** :tada:\n\nWinners: `" + winners + "`\nEnds in `" + minutes + "` Minutes\n\nReact to Enter! <@&734697425595531285> <@&750876814842527754>";
		if(hasRequirement) {
			send += "\n\n:warning: **This giveaway has a REQUIREMENT that can be found below.  " +
					"Failure to complete this requirement will result in a warning and a blacklist from all giveaways and events for up to a week.**";
		}
		Message hi = e.getChannel().sendMessage(send).complete();
		hi.addReaction(Emoji.fromUnicode("U+1f389")).queue();
		String id = hi.getId();
		MongoCollection<Document> collection = DatabaseManager.prepareStoreAllData("Giveaways Data");
		InsertOneResult result = collection.insertOne(new Document()
				.append("end", System.currentTimeMillis() + (60000L * minutes))
				.append("winners", winners)
				.append("item", itemReal)
				.append("requirement", hasRequirement)
				.append("discordID", id));
	}
}