package regular;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Member;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
// BEETHOVEN-ONLY CLASS
public class ResetMessages {
	public static void resetMessages(GenericDiscordEvent e) {
		List<Member> list = e.getGuild().getMembers();
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
			try {
				currentData.replace("messages", 0L);
			} catch(Exception exception) {
				currentData.put("messages", 0L);
			}
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Leveling\\" + user.getId() + ".json")) {
				writer.write(currentData.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
		e.reply("Message counts successfully reset!");
	}
}
