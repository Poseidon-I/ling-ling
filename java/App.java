import processes.StartBot;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		/*long pid = ProcessHandle.current().pid();
		System.out.println(pid);
		File file = new File("Ling Ling Bot Data\\pid.txt");
		try {
			file.delete();
			file.createNewFile();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\pid.txt")));
			writer.print(pid);
			writer.close();
		} catch(Exception exception1) {
			//nothing here lol
		}*/
		//DatabaseManager.connectToDatabase();
		StartBot.startBot();
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("stop")) {
			System.out.println("Exiting...");
			System.exit(0);
		}

		/* File[] directory = new File("C:\\Users\\bqian\\Desktop\\Ling Ling Bot Data\\Economy Data").listFiles();
		MongoCollection<Document> collection = DatabaseManager.prepareStoreAllData("Economy Data");
		JSONParser parser = new JSONParser();
		JSONObject data;
		JSONObject data2;
		assert directory != null;
		for(File file : directory) {
			try(FileReader reader = new FileReader(file)) {
				data = (JSONObject) parser.parse(reader);
				data2 = (JSONObject) parser.parse(Objects.requireNonNull(collection.find(eq("discordID", file.getName().substring(0, file.getName().length() - 5))).first()).toJson());
			} catch(Exception exception) {
				continue;
			}

			data2.replace("earnings", data.get("earnings"));

			collection.replaceOne(eq("discordID", file.getName().substring(0, file.getName().length() - 5)), Document.parse(data2.toJSONString()));
		}
			/* InsertOneResult result = collection.insertOne(new Document()
					.append("discordID", file.getName().substring(0, file.getName().length() - 5))

					// Currencies
					.append("violins", Long.parseLong(data.get("violins").toString()))
					.append("bank", Long.parseLong(data.get("bank").toString()))
					.append("loan", Long.parseLong(data.get("loan").toString()))
					.append("medals", Long.parseLong(data.get("medals").toString()))
					.append("magicFind", Long.parseLong(data.get("magicFind").toString()))
					.append("thirdPlace", Long.parseLong(data.get("thirdPlace").toString()))
					.append("secondPlace", Long.parseLong(data.get("secondPlace").toString()))
					.append("firstPlace", Long.parseLong(data.get("firstPlace").toString()))

					// Crafting Items
					.append("grains", Long.parseLong(data.get("grains").toString()))
					.append("plastic", Long.parseLong(data.get("plastic").toString()))
					.append("water", Long.parseLong(data.get("water").toString()))
					.append("teaBase", Long.parseLong(data.get("teaBase").toString()))
					.append("wood", Long.parseLong(data.get("wood").toString()))
					.append("pineSap", Long.parseLong(data.get("pineSap").toString()))
					.append("steel", Long.parseLong(data.get("steel").toString()))
					.append("horseHair", Long.parseLong(data.get("horseHair").toString()))

					// AFK Income Items
					.append("rosin", Long.parseLong(data.get("rosin").toString()))
					.append("string", Long.parseLong(data.get("string").toString()))
					.append("bowHair", Long.parseLong(data.get("bowHair").toString()))
					.append("violinService", Long.parseLong(data.get("violinService").toString()))

					// Consumable Items
					.append("rice", Long.parseLong(data.get("rice").toString()))
					.append("tea", Long.parseLong(data.get("tea").toString()))
					.append("blessings", Long.parseLong(data.get("blessings").toString()))

					// Lootboxes
					.append("voteBox", Long.parseLong(data.get("voteBox").toString()))
					.append("giftBox", Long.parseLong(data.get("giftBox").toString()))
					.append("kits", Long.parseLong(data.get("kits").toString()))
					.append("linglingBox", Long.parseLong(data.get("linglingBox").toString()))
					.append("crazyBox", Long.parseLong(data.get("crazyBox").toString()))
					.append("RNGesusBox", Long.parseLong(data.get("RNGesusBox").toString()))

					// AFK Income Penalty
					.append("rosinExpire", Long.parseLong(data.get("rosinExpire").toString()))
					.append("stringsExpire", Long.parseLong(data.get("stringsExpire").toString()))
					.append("bowHairExpire", Long.parseLong(data.get("bowHairExpire").toString()))
					.append("serviceExpire", Long.parseLong(data.get("serviceExpire").toString()))

					// Statistics
					.append("income", Long.parseLong(data.get("income").toString()))
					.append("streak", Long.parseLong(data.get("streak").toString()))
					.append("earnings", Long.parseLong(data.get("earnings").toString()))
					.append("winnings", Long.parseLong(data.get("winnings").toString()))
					.append("millions", Long.parseLong(data.get("millions").toString()))
					.append("robbed", Long.parseLong(data.get("robbed").toString()))
					.append("lostToRob", Long.parseLong(data.get("lostToRob").toString()))
					.append("scalesPlayed", Long.parseLong(data.get("scalesPlayed").toString()))
					.append("hoursPractised", Double.parseDouble(data.get("hoursPractised").toString()))
					.append("rehearsals", Long.parseLong(data.get("rehearsals").toString()))
					.append("performances", Long.parseLong(data.get("performances").toString()))
					.append("hoursTaught", Double.parseDouble(data.get("hoursTaught").toString()))
					.append("maxStreak", Long.parseLong(data.get("maxStreak").toString()))
					.append("votes", Long.parseLong(data.get("votes").toString()))
					.append("luthiers", Long.parseLong(data.get("luthiers").toString()))
					.append("giftsGiven", Long.parseLong(data.get("giftsGiven").toString()))
					.append("giftsReceived", Long.parseLong(data.get("giftsReceived").toString()))
					.append("interestEarned", Long.parseLong(data.get("interestEarned").toString()))
					.append("penaltiesIncurred", Long.parseLong(data.get("penaltiesIncurred").toString()))
					.append("RNGesusWeight", Long.parseLong(data.get("RNGesusWeight").toString()))
					.append("itemsSold", Long.parseLong(data.get("itemsSold").toString()))
					.append("itemsBought", Long.parseLong(data.get("itemsBought").toString()))
					.append("moneyEarned", Long.parseLong(data.get("moneyEarned").toString()))
					.append("moneySpent", Long.parseLong(data.get("moneySpent").toString()))
					.append("taxPaid", Long.parseLong(data.get("taxPaid").toString()))

					// Cooldowns
					.append("betCD", Long.parseLong(data.get("betCD").toString()))
					.append("scaleCD", Long.parseLong(data.get("scaleCD").toString()))
					.append("practiceCD", Long.parseLong(data.get("practiceCD").toString()))
					.append("teachCD", Long.parseLong(data.get("teachCD").toString()))
					.append("rehearseCD", Long.parseLong(data.get("rehearseCD").toString()))
					.append("performCD", Long.parseLong(data.get("performCD").toString()))
					.append("robCD", Long.parseLong(data.get("robCD").toString()))
					.append("voteCD", Long.parseLong(data.get("voteCD").toString()))
					.append("hadDailyToday", Boolean.parseBoolean(data.get("hadDailyToday").toString()))
					.append("hadGiftToday", Boolean.parseBoolean(data.get("hadGiftToday").toString()))

					// Misc Upgrades
					.append("efficiency", Long.parseLong(data.get("efficiency").toString()))
					.append("luck", Long.parseLong(data.get("luck").toString()))
					.append("sophistication", Long.parseLong(data.get("sophistication").toString()))
					.append("insurance", Boolean.parseBoolean(data.get("insurance").toString()))
					.append("timeCrunch", Boolean.parseBoolean(data.get("timeCrunch").toString()))
					.append("magicFindViolins", Long.parseLong(data.get("magicFindViolins").toString()))

					// Hourly Upgrades
					.append("violinQuality", Long.parseLong(data.get("violinQuality").toString()))
					.append("skills", Long.parseLong(data.get("skills").toString()))
					.append("lessonQuality", Long.parseLong(data.get("lessonQuality").toString()))
					.append("stringQuality", Long.parseLong(data.get("stringQuality").toString()))
					.append("bowQuality", Long.parseLong(data.get("bowQuality").toString()))
					.append("math", Boolean.parseBoolean(data.get("math").toString()))

					// Orchestra
					.append("orchestra", Boolean.parseBoolean(data.get("orchestra").toString()))

					// Winds
					.append("piccolo", Boolean.parseBoolean(data.get("piccolo").toString()))
					.append("flute", Long.parseLong(data.get("flute").toString()))
					.append("oboe", Long.parseLong(data.get("oboe").toString()))
					.append("clarinet", Long.parseLong(data.get("clarinet").toString()))
					.append("bassoon", Long.parseLong(data.get("bassoon").toString()))
					.append("contraBassoon", Boolean.parseBoolean(data.get("contraBassoon").toString()))

					// Brass
					.append("horn", Long.parseLong(data.get("horn").toString()))
					.append("trumpet", Long.parseLong(data.get("trumpet").toString()))
					.append("trombone", Long.parseLong(data.get("trombone").toString()))
					.append("tuba", Long.parseLong(data.get("tuba").toString()))
					.append("timpani", Long.parseLong(data.get("timpani").toString()))
					.append("percussion", Long.parseLong(data.get("percussion").toString()))

					// Strings
					.append("violin1", Long.parseLong(data.get("violin1").toString()))
					.append("violin2", Long.parseLong(data.get("violin2").toString()))
					.append("cello", Long.parseLong(data.get("cello").toString()))
					.append("doubleBass", Long.parseLong(data.get("doubleBass").toString()))
					.append("piano", Long.parseLong(data.get("piano").toString()))
					.append("harp", Boolean.parseBoolean(data.get("harp").toString()))

					// Chorus
					.append("soprano", Long.parseLong(data.get("soprano").toString()))
					.append("alto", Long.parseLong(data.get("alto").toString()))
					.append("tenor", Long.parseLong(data.get("tenor").toString()))
					.append("bass", Long.parseLong(data.get("bass").toString()))
					.append("soloist", Long.parseLong(data.get("soloist").toString()))

					// Misc Orchestra
					.append("hall", Long.parseLong(data.get("hall").toString()))
					.append("conductor", Long.parseLong(data.get("conductor").toString()))
					.append("tickets", Long.parseLong(data.get("tickets").toString()))
					.append("advertising", Long.parseLong(data.get("advertising").toString()))

					// Teaching Upgrades
					.append("certificate", Boolean.parseBoolean(data.get("certificate").toString()))
					.append("students", Long.parseLong(data.get("students").toString()))
					.append("lessonCharge", Long.parseLong(data.get("lessonCharge").toString()))
					.append("training", Long.parseLong(data.get("training").toString()))
					.append("studio", Boolean.parseBoolean(data.get("studio").toString()))
					.append("longerLessons", Boolean.parseBoolean(data.get("longerLessons").toString()))

					// Medal Upgrades
					.append("moreIncome", Long.parseLong(data.get("moreIncome").toString()))
					.append("moreCommandIncome", Long.parseLong(data.get("moreCommandIncome").toString()))
					.append("moreMulti", Long.parseLong(data.get("moreMulti").toString()))
					.append("moreRob", Long.parseLong(data.get("moreRob").toString()))
					.append("shield", Boolean.parseBoolean(data.get("shield").toString()))
					.append("duplicator", Boolean.parseBoolean(data.get("duplicator").toString()))
					.append("magicFindMedals", Long.parseLong(data.get("magicFindMedals").toString()))

					// Bank Upgrades
					.append("storage", Long.parseLong(data.get("storage").toString()))
					.append("moreInterest", Boolean.parseBoolean(data.get("moreInterest").toString()))
					.append("lessPenalty", Boolean.parseBoolean(data.get("lessPenalty").toString()))

					// Settings
					.append("color", data.get("color").toString())
					.append("DMs", Boolean.parseBoolean(data.get("DMs").toString()))
					.append("extraInfo", Boolean.parseBoolean(data.get("extraInfo").toString()))

					// Misc Data
					.append("banned", Boolean.parseBoolean(data.get("banned").toString()))
					.append("perms", Long.parseLong(data.get("perms").toString()))
					.append("medalToday", Boolean.parseBoolean(data.get("medalToday").toString()))
					.append("retainDaily", Boolean.parseBoolean(data.get("retainDaily").toString()))
					.append("isBooster", Boolean.parseBoolean(data.get("isBooster").toString()))
					.append("serverLevel", Double.parseDouble(data.get("serverLevel").toString()))
					.append("mcIGN", "")
					.append("discordName", ""));
		}

		 /* File[] directory2 = new File("C:\\Users\\bqian\\Desktop\\Ling Ling Bot Data\\Leveling").listFiles();
		assert directory2 != null;
		for(File file : directory2) {
			try(FileReader reader = new FileReader(file)) {
				data = (JSONObject) parser.parse(reader);
			} catch(Exception exception) {
				continue;
			}
			MongoCollection<Document> collection = DatabaseManager.prepareStoreAllData("Leveling Data");
			InsertOneResult result = collection.insertOne(new Document()
					.append("discordID", file.getName().substring(0, file.getName().length() - 5))
					.append("level", Long.parseLong(data.get("level").toString()))
					.append("xp", Long.parseLong(data.get("xp").toString()))
					.append("time", Long.parseLong(data.get("time").toString()))
					.append("messages", 0L)
					.append("discordName", ""));
		} */

		/* File[] directory = new File("C:\\Users\\bqian\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier").listFiles();
		MongoCollection<Document> collection = DatabaseManager.prepareStoreAllData("Luthier Data");
		JSONParser parser = new JSONParser();
		JSONObject data;
		assert directory != null;
		for(File file : directory) {
			try(FileReader reader = new FileReader(file)) {
				data = (JSONObject) parser.parse(reader);
			} catch(Exception exception) {
				continue;
			}
			InsertOneResult result = collection.insertOne(new Document()
					.append("discordID", file.getName().substring(0, file.getName().length() - 5))
					.append("amount", Long.parseLong(data.get("amount").toString()))
					.append("chance", Double.parseDouble(data.get("chance").toString()))
					.append("multiplier", Long.parseLong(data.get("multiplier").toString()))
					.append("channel", data.get("channel").toString())
					.append("word", data.get("word").toString())
					.append("hasWord", Boolean.parseBoolean(data.get("hasWord").toString())));
		}
		
		/*Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				// Runtime.getRuntime().exec("cmd /c start \"\" lingling.bat");
				Runtime.getRuntime().halt(0);
			} catch(Exception e) {
				e.printStackTrace();
			}
		})); */
	}
}