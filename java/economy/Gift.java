package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Gift {
	public static void gift(GenericDiscordEvent e, String target) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadGiftToday")) {
			e.reply("I appreciate your generosity, but I can't let you give away too much.  Wait until 00:00 UTC!");
		} else {
			if(target.equals("")) {
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
			JSONParser parser = new JSONParser();
			JSONObject targetdata;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				targetdata = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.reply("You did not provide a valid User ID.  Doesn't make sense to gift someone nonexistant, does it?");
				return;
			}
			data.replace("giftsGiven", (long) data.get("giftsGiven") + 1);
			data.replace("hadGiftToday", true);
			targetdata.replace("giftsReceived", (long) targetdata.get("giftsReceived") + 1);
			targetdata.replace("giftBox", (long) targetdata.get("giftBox") + 1);
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
				writer.write(targetdata.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
			try {
				e.reply("Successfully gifted `1`" + Emoji.GIFT_BOX + " to " + Objects.requireNonNull(e.getJDA().getUserById(target)).getName());
			} catch(Exception exception) {
				e.reply("Successfully gifted `1`" + Emoji.GIFT_BOX + " to Someone");
			}
			if((boolean) targetdata.get("DMs")) {
				try {
					Objects.requireNonNull(e.getJDA().getUserById(target)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just gifted you!"));
				} catch(Exception exception) {
					// nothing here lol
				}
			}
		}
	}
}