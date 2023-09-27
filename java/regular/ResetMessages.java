package regular;

import com.mongodb.client.MongoCollection;
import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.DatabaseManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
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
			JSONObject currentData = DatabaseManager.getDataForUser(e, "Leveling Data", user.getId());
			if(currentData == null) {
				continue;
			}
			try {
				currentData.replace("messages", 0L);
			} catch(Exception exception) {
				currentData.put("messages", 0L);
			}
			DatabaseManager.saveDataForUser(e, "Leveling Data", user.getId(), currentData);
		}
		e.reply("Message counts successfully reset!");
	}
}
