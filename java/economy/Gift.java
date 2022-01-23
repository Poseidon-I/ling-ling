package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class Gift {
	public Gift(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadGiftToday")) {
			e.getMessage().reply("I appreciate your generosity, but I can't let you give away too much.  Wait until 00:00 UTC!").queue();
		} else {
			String target;
			try {
				target = e.getMessage().getContentRaw().split(" ")[1];
			} catch(Exception exception1) {
				e.getMessage().reply("You have to gift someone for this to work.").queue();
				return;
			}
			if(target.equals(e.getAuthor().getId())) {
				e.getMessage().reply("Hey you greedy mf!  Don't gift yourself!").queue();
			} else {
				JSONParser parser = new JSONParser();
				JSONObject targetdata;
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
					targetdata = (JSONObject) parser.parse(reader);
					reader.close();
				} catch(Exception exception) {
					e.getMessage().reply("You did not provide a valid User ID.  Doesn't make sense to gift someone nonexistant, does it?").queue();
					return;
				}
				data.replace("giftsGiven", (long) data.get("giftsGiven") + 1);
				data.replace("hadGiftToday", true);
				targetdata.replace("giftsReceived", (long) targetdata.get("giftsReceived") + 1);
				targetdata.replace("giftBox", (long) targetdata.get("giftBox") + 1);
				RNGesus.Lootbox(e, data);
				new SaveData(e, data);
				try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
					writer.write(targetdata.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				e.getMessage().reply("Successfully gifted 1 Gift Box to <@" + target + ">").queue();
			}
		}
	}
}