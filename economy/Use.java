package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Use {
	public static void generateArray(Map<String, Long> addItems, long rolls, long range, long min) {
		long[] temp = {0, 0, 0, 0, 0, 0, 0, 0}; //Grains, Plastic, Water, TeaBase, Wood, PineSap, Steel, HorseHair
		Random random = new Random();
		for(int i = 0; i < rolls; i++) {
			temp[random.nextInt(8)] += random.nextLong(range) + min;
		}
		try {
			addItems.replace("grains", addItems.get("grains") + temp[0]);
			addItems.replace("plastic", addItems.get("plastic") + temp[1]);
			addItems.replace("water", addItems.get("water") + temp[2]);
			addItems.replace("teaBase", addItems.get("teaBase") + temp[3]);
			addItems.replace("wood", addItems.get("wood") + temp[4]);
			addItems.replace("pineSap", addItems.get("pineSap") + temp[5]);
			addItems.replace("steel", addItems.get("steel") + temp[6]);
			addItems.replace("horseHair", addItems.get("horseHair") + temp[7]);
		} catch(Exception exception) {
			addItems.put("grains", temp[0]);
			addItems.put("plastic", temp[1]);
			addItems.put("water", temp[2]);
			addItems.put("teaBase", temp[3]);
			addItems.put("wood", temp[4]);
			addItems.put("pineSap", temp[5]);
			addItems.put("steel", temp[6]);
			addItems.put("horseHair", temp[7]);
		}
	}
	
	public static void addTime(JSONObject data, String item, long amount) {
		long original = (long) data.get(item);
		if(System.currentTimeMillis() < original) {
			data.replace(item, (long) data.get(item) + amount);
		} else {
			data.replace(item, System.currentTimeMillis() + amount);
		}
	}
	
	public static void use(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		Random random = new Random();
		Map<String, Long> addItems = new HashMap<>(0);
		long income = (long) data.get("income");
		long useAmount;
		long extraRolls = income / 50000;
		try {
			useAmount = Objects.requireNonNull(e.getOption("amount")).getAsLong();
		} catch(Exception exception) {
			try {
				if(Objects.requireNonNull(e.getOption("amount")).getAsString().equals("max")) {
					useAmount = 2147483647;
				} else {
					useAmount = 1;
				}
			} catch(Exception exception1) {
				useAmount = 1;
			}
		}
		if(useAmount < 1) {
			e.reply("You can't use a negative amount of items.  Grow a brain.").setEphemeral(true).queue();
			return;
		}
		try {
			switch(Objects.requireNonNull(e.getOption("item")).getAsString()) {
				case "rice" -> {
					long rice = (long) data.get("rice");
					if(rice <= 0) {
						e.reply("You scourge your pantry but find no rice.  Then you remember you don't have any more.").setEphemeral(true).queue();
						return;
					} else if(income == 0) {
						e.reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > rice) {
							useAmount = rice;
						}
						addItems.put("rice", -useAmount);
						addItems.put("violins", income * 2 * useAmount);
						e.reply("You ate " + Numbers.formatNumber(useAmount) + Emoji.RICE + "  The God of Rice gave you " + Numbers.formatNumber(income * 2 * useAmount) + Emoji.VIOLINS + "\nYou have " + (rice - useAmount) + Emoji.RICE + " left").queue();
					}
				}
				case "tea" -> {
					long tea = (long) data.get("tea");
					if(tea <= 0) {
						e.reply("You scourge your fridge but find no more bubble tea.  Then you remember you don't have any more.").setEphemeral(true).queue();
						return;
					} else if(income == 0) {
						e.reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > tea) {
							useAmount = tea;
						}
						addItems.put("tea", -useAmount);
						addItems.put("violins", income * 6 * useAmount);
						e.reply("You drank " + Numbers.formatNumber(useAmount) + Emoji.TEA + "  Brett and Eddy approved and gave you " + Numbers.formatNumber(income * 6 * useAmount) + Emoji.VIOLINS + "\nYou have " + (tea - useAmount) + Emoji.TEA + " left").queue();
					}
				}
				case "blessing" -> {
					long blessings = (long) data.get("blessings");
					if((long) data.get("blessings") <= 0) {
						e.reply("You already used all your blessings, run more commands to get back into Ling Ling's good graces!").setEphemeral(true).queue();
						return;
					} else if(income == 0) {
						e.reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > blessings) {
							useAmount = blessings;
						}
						double num = random.nextDouble();
						long[] medals = {0, 0, 0, 0}; // Medals, First, Second, Third
						for(int i = 0; i < useAmount; i++) {
							if(num > 0.5) {
								medals[0]++;
								medals[3]++;
							} else if(num > 0.2) {
								medals[0] += 2;
								medals[2]++;
							} else {
								medals[0] += 3;
								medals[1]++;
							}
						}
						addItems.put("blessings", -useAmount);
						addItems.put("violins", income * 24 * useAmount);
						addItems.put("medals", medals[0]);
						addItems.put("firstPlace", medals[1]);
						addItems.put("secondPlace", medals[2]);
						addItems.put("thirdPlace", medals[3]);
						e.reply("You performed " + useAmount + " times in front of Ling Ling!  He (she?) gave you the following ratings...\n" + Emoji.THIRD_PLACE + " " + medals[3] + "\n" + Emoji.SECOND_PLACE + " " + medals[2] + "\n" + Emoji.FIRST_PLACE + " " + medals[1] + "\n\nYou walked away with...\n" + Emoji.VIOLINS + " " + income * 24 * useAmount + "\n" + Emoji.MEDALS + " " + medals[0]).queue();
					}
				}
				case "free" -> {
					long boxes = (long) data.get("voteBox");
					if(boxes <= 0) {
						e.reply("You already used all your Free Boxes!  Wait to get more!").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > boxes) {
							useAmount = boxes;
						}
						addItems.put("voteBox", -useAmount);
						for(int i = 0; i < useAmount; i++) {
							generateArray(addItems, 2 + extraRolls, 2, 2);
						}
						e.reply("You opened " + Numbers.formatNumber(useAmount) + Emoji.FREE_BOX + "  You received the following items...\n\n" + Numbers.formatNumber(addItems.get("grains")) + Emoji.GRAINS + " " + Numbers.formatNumber(addItems.get("plastic")) + Emoji.PLASTIC + " " + Numbers.formatNumber(addItems.get("water")) + Emoji.WATER + " " + Numbers.formatNumber(addItems.get("teaBase")) + Emoji.TEABAG + " " + Numbers.formatNumber(addItems.get("wood")) + Emoji.WOOD + " " + Numbers.formatNumber(addItems.get("pineSap")) + Emoji.SAP + " " + Numbers.formatNumber(addItems.get("steel")) + Emoji.STEEL + " " + Numbers.formatNumber(addItems.get("horseHair")) + Emoji.HORSE_HAIR).queue();
					}
				}
				case "gift" -> {
					long boxes = (long) data.get("giftBox");
					if(boxes <= 0) {
						e.reply("You already used all your Gift Boxes!  Convince your friends to gift you for more!").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > boxes) {
							useAmount = boxes;
						}
						addItems.put("giftBox", -useAmount);
						for(int i = 0; i < useAmount; i++) {
							generateArray(addItems, 3 + extraRolls, 3, 3);
						}
						e.reply("You opened " + Numbers.formatNumber(useAmount) + Emoji.GIFT_BOX + "  You received the following items...\n\n" + Numbers.formatNumber(addItems.get("grains")) + Emoji.GRAINS + " " + Numbers.formatNumber(addItems.get("plastic")) + Emoji.PLASTIC + " " + Numbers.formatNumber(addItems.get("water")) + Emoji.WATER + " " + Numbers.formatNumber(addItems.get("teaBase")) + Emoji.TEABAG + " " + Numbers.formatNumber(addItems.get("wood")) + Emoji.WOOD + " " + Numbers.formatNumber(addItems.get("pineSap")) + Emoji.SAP + " " + Numbers.formatNumber(addItems.get("steel")) + Emoji.STEEL + " " + Numbers.formatNumber(addItems.get("horseHair")) + Emoji.HORSE_HAIR).queue();
					}
				}
				case "kit" -> {
					long boxes = (long) data.get("kits");
					if(boxes <= 0) {
						e.reply("You already used all your Musician Kits!").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > boxes) {
							useAmount = boxes;
						}
						addItems.put("kits", -useAmount);
						for(int i = 0; i < useAmount; i++) {
							generateArray(addItems, 4 + extraRolls, 4, 5);
						}
						e.reply("You opened " + Numbers.formatNumber(useAmount) + Emoji.MUSICIAN_KIT + "  You received the following items...\n\n" + Numbers.formatNumber(addItems.get("grains")) + Emoji.GRAINS + " " + Numbers.formatNumber(addItems.get("plastic")) + Emoji.PLASTIC + " " + Numbers.formatNumber(addItems.get("water")) + Emoji.WATER + " " + Numbers.formatNumber(addItems.get("teaBase")) + Emoji.TEABAG + " " + Numbers.formatNumber(addItems.get("wood")) + Emoji.WOOD + " " + Numbers.formatNumber(addItems.get("pineSap")) + Emoji.SAP + " " + Numbers.formatNumber(addItems.get("steel")) + Emoji.STEEL + " " + Numbers.formatNumber(addItems.get("horseHair")) + Emoji.HORSE_HAIR).queue();
					}
				}
				case "llbox" -> {
					long boxes = (long) data.get("linglingBox");
					if(boxes <= 0) {
						e.reply("You already used all your Ling Ling Boxes!").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > boxes) {
							useAmount = boxes;
						}
						addItems.put("linglingBox", -useAmount);
						for(int i = 0; i < useAmount; i++) {
							generateArray(addItems, 5 + extraRolls, 4, 7);
						}
						e.reply("You opened " + Numbers.formatNumber(useAmount) + Emoji.LING_LING_BOX + "  You received the following items...\n\n" + Numbers.formatNumber(addItems.get("grains")) + Emoji.GRAINS + " " + Numbers.formatNumber(addItems.get("plastic")) + Emoji.PLASTIC + " " + Numbers.formatNumber(addItems.get("water")) + Emoji.WATER + " " + Numbers.formatNumber(addItems.get("teaBase")) + Emoji.TEABAG + " " + Numbers.formatNumber(addItems.get("wood")) + Emoji.WOOD + " " + Numbers.formatNumber(addItems.get("pineSap")) + Emoji.SAP + " " + Numbers.formatNumber(addItems.get("steel")) + Emoji.STEEL + " " + Numbers.formatNumber(addItems.get("horseHair")) + Emoji.HORSE_HAIR).queue();
					}
				}
				case "crazybox" -> {
					long boxes = (long) data.get("crazyBox");
					if(boxes <= 0) {
						e.reply("You already used all your Crazy Person Boxes!").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > boxes) {
							useAmount = boxes;
						}
						addItems.put("crazyBox", -useAmount);
						for(int i = 0; i < useAmount; i++) {
							generateArray(addItems, 6 + extraRolls, 5, 8);
						}
						e.reply("You opened " + Numbers.formatNumber(useAmount) + Emoji.CRAZY_BOX + "  You received the following items...\n\n" + Numbers.formatNumber(addItems.get("grains")) + Emoji.GRAINS + " " + Numbers.formatNumber(addItems.get("plastic")) + Emoji.PLASTIC + " " + Numbers.formatNumber(addItems.get("water")) + Emoji.WATER + " " + Numbers.formatNumber(addItems.get("teaBase")) + Emoji.TEABAG + " " + Numbers.formatNumber(addItems.get("wood")) + Emoji.WOOD + " " + Numbers.formatNumber(addItems.get("pineSap")) + Emoji.SAP + " " + Numbers.formatNumber(addItems.get("steel")) + Emoji.STEEL + " " + Numbers.formatNumber(addItems.get("horseHair")) + Emoji.HORSE_HAIR).queue();
					}
				}
				case "rngesus" -> {
					long boxes = (long) data.get("RNGesusBox");
					if(boxes <= 0) {
						e.reply("You already used all your RNGesus Boxes!").setEphemeral(true).queue();
						return;
					} else {
						if(useAmount > boxes) {
							useAmount = boxes;
						}
						addItems.put("RNGesusBox", -useAmount);
						for(int i = 0; i < useAmount; i++) {
							generateArray(addItems, 7 + extraRolls, 6, 10);
						}
						e.reply("You opened " + Numbers.formatNumber(useAmount) + Emoji.RNGESUS_BOX + "  You received the following items...\n\n" + Numbers.formatNumber(addItems.get("grains")) + Emoji.GRAINS + " " + Numbers.formatNumber(addItems.get("plastic")) + Emoji.PLASTIC + " " + Numbers.formatNumber(addItems.get("water")) + Emoji.WATER + " " + Numbers.formatNumber(addItems.get("teaBase")) + Emoji.TEABAG + " " + Numbers.formatNumber(addItems.get("wood")) + Emoji.WOOD + " " + Numbers.formatNumber(addItems.get("pineSap")) + Emoji.SAP + " " + Numbers.formatNumber(addItems.get("steel")) + Emoji.STEEL + " " + Numbers.formatNumber(addItems.get("horseHair")) + Emoji.HORSE_HAIR).queue();
					}
				}
				case "rosin" -> {
					long rosin = (long) data.get("rosin");
					if(rosin <= 0) {
						e.reply("You look in your violin case, but find no rosin left.  You are disappointed.").setEphemeral(true).queue();
					} else {
						if(useAmount > rosin) {
							useAmount = rosin;
						}
						addItems.put("rosin", -useAmount);
						addTime(data, "rosinExpire", useAmount * 90000000);
						e.reply(Emoji.ROSIN + "  You apply rosin to your bow.  You are now entitled to " + Numbers.formatNumber(25 * useAmount) + " more hours of some of your income.").queue();
					}
				}
				case "string" -> {
					long strings = (long) data.get("string");
					if(strings <= 0) {
						e.reply("You scourge your stocks, but you can't find violin strings.  You then remember that you have to buy some, then promptly forget.").setEphemeral(true).queue();
					} else {
						if(useAmount > strings) {
							useAmount = strings;
						}
						addItems.put("string", -useAmount);
						addTime(data, "stringsExpire", useAmount * 198000000);
						e.reply(Emoji.STRING + "  You change the strings on yoru violin.  You are now entitled to " + Numbers.formatNumber(55 * useAmount) + " more hours of some of your income.").queue();
					}
				}
				case "bowhair" -> {
					long hairs = (long) data.get("bowHair");
					if(hairs <= 0) {
						e.reply("You scourge your stocks, but you can't find extra bow hair.  You then remember that you have to buy some, then promptly forget.").setEphemeral(true).queue();
					} else {
						if(useAmount > hairs) {
							useAmount = hairs;
						}
						addItems.put("bowHair", -useAmount);
						addTime(data, "bowHairExpire", useAmount * 306000000);
						e.reply(Emoji.BOW_HAIR + "  You asked Olaf to rehair your bow, to which he agreed.  You are now entitled to " + Numbers.formatNumber(85 * useAmount) + " more hours of some of your income.").queue();
					}
				}
				case "service" -> {
					long service = (long) data.get("violinService");
					if(service <= 0) {
						e.reply("You go to Olaf to get your violin repaired, but forget to bring materials.  He kicks you out of the store.").setEphemeral(true).queue();
					} else {
						if(useAmount > service) {
							useAmount = service;
						}
						addItems.put("violinService", -useAmount);
						addTime(data, "serviceExpire", useAmount * 612000000);
						e.reply(Emoji.SERVICE + "  You asked Olaf to service your violin, to which he agreed.  You are now entitled to " + Numbers.formatNumber(170 * useAmount) + " more hours of some of your income.").queue();
					}
				}
				default -> {
					e.reply("You can't use something that doesn't exist, that doesn't make sense.").setEphemeral(true).queue();
					return;
				}
			}
			for(Map.Entry<String, Long> entry : addItems.entrySet()) {
				data.replace(entry.getKey(), (long) data.get(entry.getKey()) + entry.getValue());
				if(entry.getKey().equals("violins")) {
					data.replace("earnings", (long) data.get("earnings") + entry.getValue());
				}
			}
			SaveData.saveData(e, data);
		} catch(Exception exception) {
			e.reply("You can't use nothing.").setEphemeral(true).queue();
		}
	}
}