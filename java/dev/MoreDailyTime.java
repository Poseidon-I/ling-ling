package dev;

import com.mongodb.client.MongoCollection;
import eventListeners.GenericDiscordEvent;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.DatabaseManager;

import javax.print.Doc;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;

import static com.mongodb.client.model.Filters.eq;

public class MoreDailyTime {
	public static void moreDailyTime(GenericDiscordEvent e) {
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
			data.replace("retainDaily", true);
			collection.replaceOne(eq("discordID", data.get("discordID")), Document.parse(data.toJSONString()));
		}
		e.reply("Gave everyone +1 Day to run `!daily`");
	}
}