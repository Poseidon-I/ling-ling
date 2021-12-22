package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Gift {
	public Gift(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadGiftToday")) {
			e.getChannel().sendMessage("I appreciate your generosity, but I can't let you give away too much.  Wait until 00:00 UTC!").queue();
		} else {
			String target;
			try {
				target = e.getMessage().getContentRaw().split(" ")[1];
			} catch(Exception exception1) {
				e.getChannel().sendMessage("You have to gift someone for this to work.").queue();
				throw new IllegalArgumentException();
			}
			if(target.equals(e.getAuthor().getId())) {
				e.getChannel().sendMessage("Hey you greedy mf!  Don't gift yourself!").queue();
			} else {
				JSONParser parser = new JSONParser();
				JSONObject targetdata;
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
					targetdata = (JSONObject) parser.parse(reader);
					reader.close();
				} catch(Exception exception) {
					e.getChannel().sendMessage("You did not provide a valid User ID.  Doesn't make sense to gift someone nonexistant, does it?").queue();
					throw new IllegalArgumentException();
				}
				data.replace("giftsGiven", (long) data.get("giftsGiven") + 1);
				data.replace("hadGiftToday", true);
				data.replace("giftsReceived", (long) data.get("giftsReceived") + 1);
				data.replace("giftBox", (long) data.get("giftBox") + 1);
				new SaveData(e, data);
				try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
					writer.write(targetdata.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				e.getChannel().sendMessage("Successfully gifted 1 Gift Box to <@" + target + ">").queue();
			}
		}
	}
}