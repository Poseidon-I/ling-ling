package processes;

import com.mongodb.*;
import com.mongodb.client.*;
import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseManager {

	private static MongoDatabase database;
	//private static MongoDatabase databasePunishments;
	//private static MongoDatabase databaseMarket;
	@SuppressWarnings("FieldCanBeLocal")
	private static MongoClient mongoClient;

	public static MongoDatabase getDatabase() {
		return database;
	}

	public static ArrayList<Document> getAllEconomyData() {
		MongoCollection<Document> collection = database.getCollection("Economy Data");
		ArrayList<Document> results = new ArrayList<>();
		FindIterable<Document> iterable = collection.find();
		iterable.into(results);
		return results;
	}

	public static ArrayList<Document> getAllData(String name) {
		MongoCollection<Document> collection = database.getCollection(name);
		ArrayList<Document> results = new ArrayList<>();
		FindIterable<Document> iterable = collection.find();
		iterable.into(results);
		return results;
	}

	public static MongoCollection<Document> prepareStoreAllEconomyData() {
		return database.getCollection("Economy Data");
	}

	public static MongoCollection<Document> prepareStoreAllData(String name) {
		return database.getCollection(name);
	}

	public static JSONObject getEconomyData(GenericDiscordEvent e) {
		MongoCollection<Document> collection = database.getCollection("Economy Data");
		Document document = collection.find(eq("discordID", e.getAuthor().getId())).first();
		if(document == null) {
			e.reply("You do not have a save!  Run `/start` to get one!");
			return null;
		} else {
			try {
				JSONParser parser = new JSONParser();
				return (JSONObject) parser.parse(document.toJson());
			} catch(Exception exception) {
				e.reply("An error occured!");
				exception.printStackTrace();
				return null;
			}
		}
	}

	public static JSONObject getDataByUser(GenericDiscordEvent e, String collection1) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		Document document = collection.find(eq("discordID", e.getAuthor().getId())).first();
		if(document == null) {
			return null;
		} else {
			try {
				JSONParser parser = new JSONParser();
				return (JSONObject) parser.parse(document.toJson());
			} catch(Exception exception) {
				e.reply("An error occured!");
				exception.printStackTrace();
				return null;
			}
		}
	}

	public static JSONObject getDataForUser(GuildMemberRoleAddEvent e, String collection1, String target) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		Document document = collection.find(eq("discordID", target)).first();
		if(document == null) {
			return null;
		} else {
			try {
				JSONParser parser = new JSONParser();
				return (JSONObject) parser.parse(document.toJson());
			} catch(Exception exception) {
				exception.printStackTrace();
				return null;
			}
		}
	}

	public static JSONObject getDataForUser(GuildMemberRoleRemoveEvent e, String collection1, String target) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		Document document = collection.find(eq("discordID", target)).first();
		if(document == null) {
			return null;
		} else {
			try {
				JSONParser parser = new JSONParser();
				return (JSONObject) parser.parse(document.toJson());
			} catch(Exception exception) {
				exception.printStackTrace();
				return null;
			}
		}
	}


	public static JSONObject getDataForUser(GenericDiscordEvent e, String collection1, String target) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		Document document = collection.find(eq("discordID", target)).first();
		if(document == null) {
			return null;
		} else {
			try {
				JSONParser parser = new JSONParser();
				return (JSONObject) parser.parse(document.toJson());
			} catch(Exception exception) {
				e.reply("An error occured!");
				exception.printStackTrace();
				return null;
			}
		}
	}

	public static JSONObject getDataByGuild(GenericDiscordEvent e, String collection1) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		Document document = collection.find(eq("discordID", e.getGuild().getId())).first();
		if(document == null) {
			return null;
		} else {
			try {
				JSONParser parser = new JSONParser();
				return (JSONObject) parser.parse(document.toJson());
			} catch(Exception exception) {
				e.reply("An error occured!");
				exception.printStackTrace();
				return null;
			}
		}
	}

	public static JSONObject getMiscData() {
		MongoCollection<Document> collection = database.getCollection("Misc Files");
		Document document = collection.find(eq("discordID", -1)).first();
		if(document == null) {
			return null;
		} else {
			try {
				JSONParser parser = new JSONParser();
				return (JSONObject) parser.parse(document.toJson());
			} catch(Exception exception) {
				exception.printStackTrace();
				return null;
			}
		}
	}

	public static void saveDataByUser(GenericDiscordEvent e, String collection1, JSONObject newData) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		collection.replaceOne(eq("discordID", e.getAuthor().getId()), Document.parse(newData.toJSONString()));
	}

	public static void saveDataForUser(GuildMemberRoleAddEvent e, String collection1, String target, JSONObject newData) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		collection.replaceOne(eq("discordID", target), Document.parse(newData.toJSONString()));
	}

	public static void saveDataForUser(GuildMemberRoleRemoveEvent e, String collection1, String target, JSONObject newData) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		collection.replaceOne(eq("discordID", target), Document.parse(newData.toJSONString()));
	}

	public static void saveDataForUser(GenericDiscordEvent e, String collection1, String target, JSONObject newData) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		collection.replaceOne(eq("discordID", target), Document.parse(newData.toJSONString()));
	}

	public static void saveDataByGuild(GenericDiscordEvent e, String collection1, JSONObject newData) {
		MongoCollection<Document> collection = database.getCollection(collection1);
		collection.replaceOne(eq("discordID", e.getGuild().getId()), Document.parse(newData.toJSONString()));
	}

	public static void saveMiscData(JSONObject newData) {
		MongoCollection<Document> collection = database.getCollection("Misc Files");
		collection.replaceOne(eq("discordID", -1), Document.parse(newData.toJSONString()));
	}

	/*public static MongoCollection<Document> getPunishmentData(GenericDiscordEvent e, String target) {
		return databasePunishments.getCollection(target);
	}*/

	public static void connectToDatabase() {
		// establish connection to database
		String connectionString = "mongodb+srv://StradivariusViolin:tDIEzkZyDmnbA7vE@linglingbot.ac7xhbw.mongodb.net/?retryWrites=true&w=majority";
		ServerApi serverApi = ServerApi.builder()
				.version(ServerApiVersion.V1)
				.build();
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(connectionString))
				/*.applyToConnectionPoolSettings(builder ->
						builder.maxConnectionIdleTime(1, TimeUnit.HOURS)
								.maxConnectionLifeTime(1, TimeUnit.HOURS)
								.build())*/
				.serverApi(serverApi)
				.build();
		// Create a new client and connect to the server
		try {
			mongoClient = MongoClients.create(settings);
			// Send a ping to confirm a successful connection
			database = mongoClient.getDatabase("Ling_Ling");
			database.runCommand(new Document("ping", 1));
			System.out.println("Connected to main database.");

			/*databasePunishments = mongoClient.getDatabase("Punishment_Logs");
			databasePunishments.runCommand(new Document("ping", 1));
			System.out.println("Connected to punisment database.");

			databasePunishments = mongoClient.getDatabase("Market_Data");
			databasePunishments.runCommand(new Document("ping", 1));
			System.out.println("Connected to market database.");*/


		} catch(MongoException e) {
			e.printStackTrace();
		}
	}
}