package dev;

import com.mongodb.client.MongoCollection;
import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.DatabaseManager;

import java.util.ArrayList;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class ResetDaily {
	public static void resetDaily(GenericDiscordEvent e) {
		ArrayList<Document> documents = DatabaseManager.getAllEconomyData();
		MongoCollection<Document> collection = DatabaseManager.prepareStoreAllEconomyData();
		for(Document file : documents) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try {
				data = (JSONObject) parser.parse(file.toJson());
			} catch(Exception exception) {
				continue;
			}
			if((boolean) data.get("hadDailyToday")) {
				data.replace("retainDaily", true);
			} else {
				data.replace("retainDaily", false);
			}
			data.replace("hadDailyToday", false);
			data.replace("hadGiftToday", false);
			data.replace("medalToday", false);
			collection.replaceOne(eq("discordID", data.get("discordID")), Document.parse(data.toJSONString()));
		}

		UpdateLuthierChance.updateLuthierChance(e, false);
		
		if(e.getJDA().getSelfUser().getId().equals("733409243222507670")) {
			int users = documents.size();
			VoiceChannel channel = Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getVoiceChannelById("839877827838476289");
			assert channel != null;
			channel.getManager().setName(users + " Ling Ling Wannabes").queue();
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Daily cooldowns reset!").queue();
		}
	}
}