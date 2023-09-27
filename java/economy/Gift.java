package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import processes.DatabaseManager;

import java.util.Objects;

public class Gift {
	public static void gift(GenericDiscordEvent e, String target) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadGiftToday")) {
			e.reply("I appreciate your generosity, but I can't let you give away too much.  Wait until 00:00 UTC!");
		} else {
			if(target.isEmpty()) {
				e.reply("You have to gift someone for this to work.");
				return;
			}
			if(target.equals(e.getAuthor().getId())) {
				e.reply("Hey greedy!  Don't gift yourself!");
				return;
			}
			if(target.equals("733409243222507670")) {
				e.reply("Thanks for your generosity, but I can't use things.  Give this to someone else!");
				return;
			}

			JSONObject targetdata = DatabaseManager.getDataForUser(e, "Economy Data", target);
			if(targetdata == null) {
				e.reply("You did not provide a valid User ID.  Doesn't make sense to gift someone nonexistant, does it?");
				return;
			}
			data.replace("giftsGiven", (long) data.get("giftsGiven") + 1);
			data.replace("hadGiftToday", true);
			targetdata.replace("giftsReceived", (long) targetdata.get("giftsReceived") + 1);
			targetdata.replace("giftBox", (long) targetdata.get("giftBox") + 1);
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
			DatabaseManager.saveDataForUser(e, "Economy Data", target, targetdata);
			try {
				e.reply("Successfully gifted `1`" + Emoji.GIFT_BOX + " to " + targetdata.get("discordName"));
			} catch(Exception exception) {
				e.reply("Successfully gifted `1`" + Emoji.GIFT_BOX + " to Someone");
			}
			if((boolean) targetdata.get("DMs")) {
				try {
					Objects.requireNonNull(e.getJDA().getUserById(target)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getAuthor().getId() + "> (" + data.get("discordName") + ") just gifted you!").queue());
				} catch(Exception exception) {
					// nothing here lol
				}
			}
		}
	}
}