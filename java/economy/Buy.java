package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

public class Buy {
	
	public static void ProcessBooleanUpgrade(MessageReceivedEvent e, long cost, int add, String item, String currency, JSONObject data) {
		long amount = (long) data.get(currency);
		if((boolean) data.get(item)) {
			e.getMessage().reply("You already purchased `" + item + "`!").mentionRepliedUser(false).queue();
		} else if(amount < cost) {
			e.getMessage().reply("You do not have enough " + currency + " to purchase `" + item + "`!\nYou need `" + Numbers.FormatNumber(cost - amount) + "` more " + currency + ".").mentionRepliedUser(false).queue();
		} else {
			data.replace(currency, amount - cost);
			data.replace("income", (long) data.get("income") + add);
			data.replace(item, true);
			e.getMessage().reply("Successfully purchased `" + item + "` for `" + Numbers.FormatNumber(cost) + "` " + currency + "\nYou have `" + Numbers.FormatNumber(amount - cost) + "` " + currency + " left.").mentionRepliedUser(false).queue();
			if(item.equals("orchestra")) {
				data.replace("storage", 1);
				e.getMessage().reply("The Bank of TwoSet has noticed you, and has given you space to store violins!  You have 15 million storage for free; this can increased by buying more space using medals.").mentionRepliedUser(false).queue();
			}
			RNGesus.Lootbox(e, data);
			new SaveData(e, data);
		}
	}
	
	public static void ProcessUpgrade(MessageReceivedEvent e, long cost, int add, int maxAmount, String item, String currency, JSONObject data) {
		long amount = (long) data.get(currency);
		if((long) data.get(item) == maxAmount) {
			e.getMessage().reply("You already purchased the maximum amount of `" + item + "`!").mentionRepliedUser(false).queue();
		} else if(amount < cost) {
			e.getMessage().reply("You do not have enough " + currency + " to purchase `" + item + "`!\nYou need `" + Numbers.FormatNumber(cost - amount) + "` more " + currency + ".").mentionRepliedUser(false).queue();
		} else {
			data.replace(currency, amount - cost);
			data.replace("income", (long) data.get("income") + add);
			long itemCount = (long) data.get(item) + 1;
			data.replace(item, itemCount);
			if(maxAmount == 2147483647) {
				e.getMessage().reply("Successfully purchased `" + item + " #" + itemCount + "` for `" + Numbers.FormatNumber(cost) + "` " + currency + "\nYou have `" + Numbers.FormatNumber(amount - cost) + "` " + currency + " left.").mentionRepliedUser(false).queue();
			} else {
				if(amount == maxAmount) {
					e.getMessage().reply("**MAX LEVEL**\nSuccessfully purchased `" + item + " #" + itemCount + "/" + maxAmount + "` for `" + cost + "` " + Numbers.FormatNumber(currency) + "\nYou have `" + Numbers.FormatNumber(amount - cost) + "` " + currency + " left.").mentionRepliedUser(false).queue();
				} else {
					e.getMessage().reply("Successfully purchased `" + item + " #" + itemCount + "/" + maxAmount + "` for `" + Numbers.FormatNumber(cost) + "` " + currency + "\nYou have `" + Numbers.FormatNumber(amount - cost) + "` " + currency + " left.").mentionRepliedUser(false).queue();
				}
			}
			RNGesus.Lootbox(e, data);
			new SaveData(e, data);
		}
	}
	
	public Buy(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		boolean boughtItem = false;
		boolean hasOrchestra = (boolean) data.get("orchestra");
		boolean hasCertificate = (boolean) data.get("certificate");
		if(hasCertificate) {
			boughtItem = true;
			switch(message[1]) {
				case "longer" -> ProcessBooleanUpgrade(e, 30000000, 0, "longerLessons", "violins", data);
				case "studio" -> ProcessBooleanUpgrade(e, 20000000, 5000, "studio", "violins", data);
				case "training" -> ProcessUpgrade(e, 2000000 * ((long) data.get("training") + 1), 1000, 10, "training", "violins", data);
				case "pricing" -> ProcessUpgrade(e, 3000000 * ((long) data.get("lessonCharge") + 1), 3000, 5, "lessonCharge", "violins", data);
				case "students" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("students"), 2, 1000000), 2000, 2147483647, "students", "violins", data);
				default -> boughtItem = false;
			}
		}
		if(hasOrchestra && !boughtItem) {
			boughtItem = true;
			switch(message[1]) {
				case "piccolo" -> ProcessBooleanUpgrade(e, 200000, 30, "piccolo", "violins", data);
				case "contrabassoon", "cb" -> ProcessBooleanUpgrade(e, 250000, 30, "contraBassoon", "violins", data);
				case "harp" -> ProcessBooleanUpgrade(e, 350000, 80, "harp", "violins", data);
				case "flute" -> ProcessUpgrade(e, 250000 * ((long) data.get("flute") + 1), 60, 4, "flute", "violins", data);
				case "oboe" -> ProcessUpgrade(e, 250000 * ((long) data.get("oboe") + 1), 60, 4, "oboe", "violins", data);
				case "clarinet" -> ProcessUpgrade(e, 200000 * ((long) data.get("clarinet") + 1), 50, 4, "clarinet", "violins", data);
				case "bassoon" -> ProcessUpgrade(e, 200000 * ((long) data.get("bassoon") + 1), 40, 4, "bassoon", "violins", data);
				case "horn" -> ProcessUpgrade(e, 200000 * ((long) data.get("horn") + 1), 40, 8, "horn", "violins", data);
				case "trumpet" -> ProcessUpgrade(e, 200000 * ((long) data.get("trumpet") + 1), 30, 4, "trumpet", "violins", data);
				case "trombone" -> ProcessUpgrade(e, 200000 * ((long) data.get("trombone") + 1), 20, 6, "trombone", "violins", data);
				case "tuba" -> ProcessUpgrade(e, 200000 * ((long) data.get("tuba") + 1), 20, 2, "tuba", "violins", data);
				case "timpani" -> ProcessUpgrade(e, 250000 * ((long) data.get("timpani") + 1), 60, 2, "timpani", "violins", data);
				case "percussion" -> ProcessUpgrade(e, 100000 * ((long) data.get("percussion") + 1), 10, 2, "percussion", "violins", data);
				case "first" -> ProcessUpgrade(e, 450000 * (long) data.get("violin1"), 70, 20, "violin1", "violins", data);
				case "second" -> ProcessUpgrade(e, 350000 * (long) data.get("violin2"), 60, 20, "violin2", "violins", data);
				case "cello" -> ProcessUpgrade(e, 300000 * ((long) data.get("cello") + 1), 50, 15, "cello", "violins", data);
				case "db", "doublebass" -> ProcessUpgrade(e, 300000 * ((long) data.get("doubleBass") + 1), 50, 5, "doubleBass", "violins", data);
				case "piano" -> ProcessUpgrade(e, 750000 * ((long) data.get("piano") + 1), 110, 2, "piano", "violins", data);
				case "soprano" -> ProcessUpgrade(e, 80000 * ((long) data.get("soprano") + 1), 30, 20, "soprano", "violins", data);
				case "alto" -> ProcessUpgrade(e, 60000 * ((long) data.get("alto") + 1), 20, 20, "alto", "violins", data);
				case "tenor" -> ProcessUpgrade(e, 60000 * ((long) data.get("tenor") + 1), 20, 20, "tenor", "violins", data);
				case "bass" -> ProcessUpgrade(e, 60000 * ((long) data.get("bass") + 1), 20, 20, "bass", "violins", data);
				case "soloist" -> ProcessUpgrade(e, 250000 * ((long) data.get("soloist") + 1), 60, 4, "soloist", "violins", data);
				case "conductor", "musicality" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("conductor"), 4, 100000), 200, 2147483647, "conductor", "violins", data);
				case "advertisement", "ad" -> ProcessUpgrade(e, 100000 * ((long) data.get("advertising") + 1), 100, 20, "advertising", "violins", data);
				case "tickets" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("tickets"), 2, 1000000), 1000, 2147483647, "tickets", "violins", data);
				case "interest" -> ProcessBooleanUpgrade(e, 15, 0, "moreInterest", "medals", data);
				case "lower" -> ProcessBooleanUpgrade(e, 15, 0, "lessPenalty", "medals", data);
				case "space" -> ProcessUpgrade(e, 3 * (long) data.get("storage"), 0, 2147483647, "storage", "medals", data);
				case "certificate" -> {
					if((long) data.get("income") < 40000) {
						e.getMessage().reply("You do not have neough hourly income to become a teacher!").mentionRepliedUser(false).queue();
					} else {
						ProcessBooleanUpgrade(e, 200000000, 5000, "certificate", "violins", data);
					}
				}
				default -> boughtItem = false;
			}
		}
		if(!boughtItem) {
			switch(message[1]) {
				case "insurance" -> ProcessBooleanUpgrade(e, 2500000, 0, "insurance", "violins", data);
				case "timecrunch", "tc" -> ProcessBooleanUpgrade(e, 120000000, 0, "timeCrunch", "violins", data);
				case "efficiency", "ep" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("efficiency"), 1.1, 400), 0, 2147483647, "efficiency", "violins", data);
				case "lucky", "lm" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("luck"), 1.25, 1000), 0, 50, "luck", "violins", data);
				case "sophistication", "sr" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("sophistication"), 1.4, 5000), 0, 30, "sophistication", "violins", data);
				case "violin", "v" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("violinQuality"), 3, 1000), 600, 2147483647, "violinQuality", "violins", data);
				case "skill", "s" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("skills"), 2, 500), 240, 2147483647, "skills", "violins", data);
				case "lesson", "l" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("lessonQuality"), 2.5, 750), 150, 2147483647, "lessonQuality", "violins", data);
				case "string", "str" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("stringQuality"), 1.75, 500), 100, 2147483647, "stringQuality", "violins", data);
				case "bow", "b" -> ProcessUpgrade(e, Numbers.ItemCost((long) data.get("bowQuality"), 3, 750), 200, 2147483647, "bowQuality", "violins", data);
				case "math" -> ProcessBooleanUpgrade(e, 10000000, 6500, "math", "violins", data);
				case "income" -> ProcessUpgrade(e, (long) data.get("moreIncome") + 1, 2000, 2147483647, "moreIncome", "medals", data);
				case "commandincome" -> ProcessUpgrade(e, (long) Math.pow(2, (long) data.get("moreCommandIncome")), 0, 2147483647, "moreCommandIncome", "medals", data);
				case "gamblelimit" -> ProcessUpgrade(e, (long) data.get("moreMulti" + 1), 0, 2147483647, "moreMulti", "medals", data);
				case "robsuccess" -> ProcessUpgrade(e, (long) Math.pow(2, (long) data.get("moreRob")), 0, 2147483647, "moreRob", "medals", data);
				case "shield" -> ProcessBooleanUpgrade(e, 10, 0, "shield", "medals", data);
				case "duplicator" -> ProcessBooleanUpgrade(e, 15, 0, "duplicator", "medals", data);
				case "orchestra", "o" -> {
					if((long) data.get("income") < 7500) {
						e.getMessage().reply("You do not have enough hourly income to hire an orchestra!").mentionRepliedUser(false).queue();
					} else {
						ProcessBooleanUpgrade(e, 25000000, 3100, "orchestra", "violins", data);
					}
				}
				case "concert", "hall" -> {
					if(!hasOrchestra) {
						ProcessUpgrade(e, Numbers.ItemCost((long) data.get("hall"), 4, 100000), 300, 3, "hall", "violins", data);
					} else {
						ProcessUpgrade(e, Numbers.ItemCost((long) data.get("hall"), 4, 100000), 300, 2147483647, "hall", "violins", data);
					}
				}
				default -> e.getMessage().reply("You can't buy something that's not for sale, that would be quite a waste of time and violins.").mentionRepliedUser(false).queue();
			}
		}
	}
}