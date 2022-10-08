package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Objects;

public class Buy {
	private static JSONObject data;
	private static SlashCommandInteractionEvent e;
	private static String currencyEmoji;
	private static String item;
	
	public static void setEmoji(String currency) {
		switch(currency) {
			case "violins" -> currencyEmoji = Emoji.VIOLINS;
			case "medals" -> currencyEmoji = Emoji.MEDALS;
		}
	}
	
	public static void processBooleanUpgrade(long cost, int add, String currency) {
		setEmoji(currency);
		long amount = (long) data.get(currency);
		if((boolean) data.get(item)) {
			e.reply("You already purchased `" + item + "`!").queue();
		} else if(amount < cost) {
			e.reply("You do not have enough " + currency + " to purchase `" + item + "`!\nYou need `" + Numbers.formatNumber(cost - amount) + "` more " + currency + ".").queue();
		} else {
			data.replace(currency, amount - cost);
			data.replace("income", (long) data.get("income") + add);
			data.replace(item, true);
			e.reply("Successfully purchased `" + item + "` for `" + Numbers.formatNumber(cost) + "`" + currencyEmoji + "\nYou have `" + Numbers.formatNumber(amount - cost) + "`" + currencyEmoji + " left.").queue();
			if(item.equals("orchestra")) {
				data.replace("storage", 1);
				e.reply("The Bank of TwoSet has noticed you, and has given you space to store violins!  You have 20 million storage for free; this can increased by buying more space using medals.").queue();
			}
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
		}
	}
	
	public static void processUpgrade(long cost, int add, int maxAmount, String currency) {
		setEmoji(currency);
		long amount = (long) data.get(currency);
		if((long) data.get(item) == maxAmount) {
			e.reply("You already purchased the maximum amount of `" + item + "`!").queue();
		} else if(amount < cost) {
			e.reply("You do not have enough " + currency + " to purchase `" + item + "`!\nYou need `" + Numbers.formatNumber(cost - amount) + "` more " + currency + ".").queue();
		} else {
			data.replace(currency, amount - cost);
			data.replace("income", (long) data.get("income") + add);
			long itemCount = (long) data.get(item) + 1;
			data.replace(item, itemCount);
			if(maxAmount == 2147483647) {
				e.reply("Successfully purchased `" + item + " #" + itemCount + "` for `" + Numbers.formatNumber(cost) + "`" + currencyEmoji + "\nYou have `" + Numbers.formatNumber(amount - cost) + "`" + currencyEmoji + " left.").queue();
			} else {
				if(amount == maxAmount) {
					e.reply("**MAX LEVEL**\nSuccessfully purchased `" + item + " #" + itemCount + "/" + maxAmount + "` for `" + cost + "`" + currencyEmoji + "\nYou have `" + Numbers.formatNumber(amount - cost) + "`" + currencyEmoji + " left.").queue();
				} else {
					e.reply("Successfully purchased `" + item + " #" + itemCount + "/" + maxAmount + "` for `" + Numbers.formatNumber(cost) + "`" + currencyEmoji + "\nYou have `" + Numbers.formatNumber(amount - cost) + "`" + currencyEmoji + " left.").queue();
				}
			}
			if(item.contains("magicFind")) {
				data.replace("magicFind", (long) data.get("magicFind") + 1);
			}
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
		}
	}
	
	public static void buy(@NotNull SlashCommandInteractionEvent event) {
		e = event;
		data = LoadData.loadData(e);
		boolean boughtItem = false;
		boolean hasOrchestra = (boolean) data.get("orchestra");
		boolean hasCertificate = (boolean) data.get("certificate");
		try {
			item = Objects.requireNonNull(e.getOption("item")).getAsString();
		} catch(Exception exception) {
			e.reply("You have to specify an upgrade to buy, can't give you nothing.").setEphemeral(true).queue();
			return;
		}
		if(hasCertificate) {
			boughtItem = true;
			switch(item) {
				case "longerLessons" -> processBooleanUpgrade(30000000, 0, "violins");
				case "studio" -> processBooleanUpgrade(20000000, 5000, "violins");
				case "training" ->
						processUpgrade(2000000 * ((long) data.get("training") + 1), 1000, 10, "violins");
				case "lessonCharge" ->
						processUpgrade(3000000 * ((long) data.get("lessonCharge") + 1), 3000, 5, "violins");
				case "students" ->
						processUpgrade(Numbers.itemCost((long) data.get("students"), 2, 1000000), 2000, 2147483647, "violins");
				default -> boughtItem = false;
			}
		}
		if(hasOrchestra && !boughtItem) {
			boughtItem = true;
			switch(item) {
				case "piccolo" -> processBooleanUpgrade(200000, 30, "violins");
				case "contraBassoon" -> processBooleanUpgrade(250000, 30, "violins");
				case "harp" -> processBooleanUpgrade(350000, 80, "violins");
				case "flute" -> processUpgrade(250000 * ((long) data.get("flute") + 1), 60, 4, "violins");
				case "oboe" -> processUpgrade(250000 * ((long) data.get("oboe") + 1), 60, 4, "violins");
				case "clarinet" ->
						processUpgrade(200000 * ((long) data.get("clarinet") + 1), 50, 4, "violins");
				case "bassoon" ->
						processUpgrade(200000 * ((long) data.get("bassoon") + 1), 40, 4, "violins");
				case "horn" -> processUpgrade(200000 * ((long) data.get("horn") + 1), 40, 8, "violins");
				case "trumpet" ->
						processUpgrade(200000 * ((long) data.get("trumpet") + 1), 30, 4, "violins");
				case "trombone" ->
						processUpgrade(200000 * ((long) data.get("trombone") + 1), 20, 6, "violins");
				case "tuba" -> processUpgrade(200000 * ((long) data.get("tuba") + 1), 20, 2, "violins");
				case "timpani" ->
						processUpgrade(250000 * ((long) data.get("timpani") + 1), 60, 2, "violins");
				case "percussion" ->
						processUpgrade(100000 * ((long) data.get("percussion") + 1), 10, 2, "violins");
				case "violin1" -> processUpgrade(450000 * (long) data.get("violin1"), 70, 20, "violins");
				case "violin2" -> processUpgrade(350000 * (long) data.get("violin2"), 60, 20, "violins");
				case "cello" -> processUpgrade(300000 * ((long) data.get("cello") + 1), 50, 15, "violins");
				case "doubleBass" ->
						processUpgrade(300000 * ((long) data.get("doubleBass") + 1), 50, 5, "violins");
				case "piano" -> processUpgrade(750000 * ((long) data.get("piano") + 1), 110, 2, "violins");
				case "soprano" ->
						processUpgrade(80000 * ((long) data.get("soprano") + 1), 30, 20, "violins");
				case "alto" -> processUpgrade(60000 * ((long) data.get("alto") + 1), 20, 20, "violins");
				case "tenor" -> processUpgrade(60000 * ((long) data.get("tenor") + 1), 20, 20, "violins");
				case "bass" -> processUpgrade(60000 * ((long) data.get("bass") + 1), 20, 20, "violins");
				case "soloist" ->
						processUpgrade(250000 * ((long) data.get("soloist") + 1), 60, 4, "violins");
				case "conductor" ->
						processUpgrade(Numbers.itemCost((long) data.get("conductor"), 3, 100000), 500, 2147483647, "violins");
				case "advertising" ->
						processUpgrade(100000 * ((long) data.get("advertising") + 1), 100, 20, "violins");
				case "tickets" ->
						processUpgrade(Numbers.itemCost((long) data.get("tickets"), 2, 1000000), 1000, 2147483647, "violins");
				case "moreInterest" -> processBooleanUpgrade(15, 0, "medals");
				case "storage" -> processUpgrade(3 * (long) data.get("storage"), 0, 2147483647, "medals");
				case "certificate" -> {
					if((long) data.get("income") < 40000) {
						e.reply("You do not have neough hourly income to become a teacher!").queue();
					} else {
						processBooleanUpgrade(200000000, 5000, "violins");
					}
				}
				default -> boughtItem = false;
			}
		}
		if(!boughtItem) {
			switch(item) {
				case "insurance" -> processBooleanUpgrade(2500000, 0, "violins");
				case "timeCrunch" -> processBooleanUpgrade(120000000, 0, "violins");
				case "efficiency" ->
						processUpgrade(Numbers.itemCost((long) data.get("efficiency"), 1.1, 400), 0, 2147483647, "violins");
				case "luck" ->
						processUpgrade(Numbers.itemCost((long) data.get("luck"), 1.25, 1000), 0, 50, "violins");
				case "sophistication" ->
						processUpgrade(Numbers.itemCost((long) data.get("sophistication"), 1.4, 5000), 0, 30, "violins");
				case "violinQuality" ->
						processUpgrade(Numbers.itemCost((long) data.get("violinQuality"), 3, 1000), 600, 2147483647, "violins");
				case "skills" ->
						processUpgrade(Numbers.itemCost((long) data.get("skills"), 2, 500), 250, 2147483647, "violins");
				case "lessonQuality" ->
						processUpgrade(Numbers.itemCost((long) data.get("lessonQuality"), 2, 400), 200, 2147483647, "violins");
				case "stringQuality" ->
						processUpgrade(Numbers.itemCost((long) data.get("stringQuality"), 1.5, 300), 150, 2147483647, "violins");
				case "bowQuality" ->
						processUpgrade(Numbers.itemCost((long) data.get("bowQuality"), 2.5, 600), 250, 2147483647, "violins");
				case "math" -> processBooleanUpgrade(10000000, 6500, "violins");
				case "moreIncome" ->
						processUpgrade((long) data.get("moreIncome") + 1, 2000, 2147483647, "medals");
				case "moreCommandIncome" ->
						processUpgrade((long) Math.pow(2, (long) data.get("moreCommandIncome")), 0, 2147483647, "medals");
				case "moreMulti" ->
						processUpgrade((long) data.get("moreMulti") + 1, 0, 2147483647, "medals");
				case "moreRob" ->
						processUpgrade((long) Math.pow(2, (long) data.get("moreRob")), 0, 2147483647, "medals");
				case "shield" -> processBooleanUpgrade(10, 0, "medals");
				case "duplicator" -> processBooleanUpgrade(15, 0, "medals");
				case "magicFindViolins" ->
						processUpgrade(Numbers.itemCost((long) data.get("magicFindViolins"), 4, 1000000), 0, 2147483647, "violins");
				case "magicFindMedals" ->
						processUpgrade((long) Math.pow(2, (long) data.get("magicFindMedals")), 0, 2147483647, "medals");
				case "orchestra" -> {
					if((long) data.get("income") < 7500) {
						e.reply("You do not have enough hourly income to hire an orchestra!").queue();
					} else {
						processBooleanUpgrade(25000000, 3100, "violins");
					}
				}
				case "hall" -> {
					if(!hasOrchestra) {
						processUpgrade(Numbers.itemCost((long) data.get("hall"), 4, 100000), 300, 3, "violins");
					} else {
						processUpgrade(Numbers.itemCost((long) data.get("hall"), 4, 100000), 300, 2147483647, "violins");
					}
				}
				default ->
						e.reply("You can't buy something that's not for sale, that would be quite a waste of time and violins.").setEphemeral(true).queue();
			}
		}
	}
}