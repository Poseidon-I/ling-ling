package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.HashMap;
import java.util.Map;
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

	public static String pickItem(String item) {
		switch(item) {
			case "rice" -> {
				return "rice";
			}
			case "tea" -> {
				return "tea";
			}
			case "blessings", "blessing" -> {
				return "blessings";
			}
			case "vote", "free", "votebox", "freebox", "voteBox" -> {
				return "voteBox";
			}
			case "gift", "giftbox", "giftBox" -> {
				return "giftBox";
			}
			case "kits", "kit" -> {
				return "kits";
			}
			case "linglingBox", "linglingbox", "lingling", "llbox" -> {
				return "linglingBox";
			}
			case "crazyBox", "crazy", "crazybox" -> {
				return "crazyBox";
			}
			case "RNGesusBox", "rngesus", "rngesusbox" -> {
				return "RNGesusBox";
			}
			case "rosin" -> {
				return "rosin";
			}
			case "string" -> {
				return "string";
			}
			case "bowHair", "bowhair", "hair" -> {
				return "bowHair";
			}
			case "violinService", "violinservice", "service" -> {
				return "violinService";
			}
			default -> {
				return "nothing";
			}
		}
	}

	public static void use(GenericDiscordEvent e, String item1, String temp) {
		if(item1.isEmpty()) {
			e.reply("You can't use nothing.");
			return;
		}
		String item = pickItem(item1);
		JSONObject data = LoadData.loadData(e);
		Random random = new Random();
		Map<String, Long> addItems = new HashMap<>(0);
		long income = (long) data.get("income");
		long itemAmount = (long) data.get(item);
		long useAmount;
		long extraRolls = income / 100000;
		long extraItems = income / 25000;
		try {
			useAmount = Long.parseLong(temp);
		} catch(Exception exception) {
			if(temp.equals("max")) {
				useAmount = 2147483647;
			} else {
				e.reply("You have to either input `max` or an integer.");
				return;
			}
		}
		if(useAmount < 1) {
			e.reply("You can't use a negative amount of items.  Grow a brain.");
			return;
		}
		if(useAmount > itemAmount) {
			useAmount = itemAmount;
		}
		switch(item) {
			case "rice" -> {
				if((long) data.get(item) <= 0) {
					e.reply("You scourge your pantry but find no rice.  Then you remember you don't have any more.");
					return;
				} else if(income == 0) {
					e.reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.");
					return;
				} else {
					addItems.put("violins", income * 2 * useAmount);
					e.reply("You ate `" + Numbers.formatNumber(useAmount) + "`" + Emoji.RICE + "  The God of Rice gave you `" + Numbers.formatNumber(income * 2 * useAmount) + "`" + Emoji.VIOLINS);
				}
			}
			case "tea" -> {
				if((long) data.get("tea") <= 0) {
					e.reply("You scourge your fridge but find no more bubble tea.  Then you remember you don't have any more.");
					return;
				} else if(income == 0) {
					e.reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.");
					return;
				} else {
					addItems.put("violins", income * 6 * useAmount);
					e.reply("You drank `" + Numbers.formatNumber(useAmount) + "`" + Emoji.TEA + "  Brett and Eddy approved and gave you `" + Numbers.formatNumber(income * 6 * useAmount) + "`" + Emoji.VIOLINS);
				}
			}
			case "blessings" -> {
				if((long) data.get("blessings") <= 0) {
					e.reply("You already used all your blessings, run more commands to get back into Ling Ling's good graces!");
					return;
				} else if(income == 0) {
					e.reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.");
					return;
				} else {
					long add = 0;
					for(int i = 0; i < useAmount; i++) {
						double num = random.nextDouble();
						if(num > 0.5) {
							add++;
						} else if(num > 0.2) {
							add += 2;
						} else {
							add += 3;
						}
					}
					addItems.put("medals", add);
					e.reply("Ling Ling blessed you thanks to your `" + useAmount + "` performances!  You received `" + add + "`" + Emoji.MEDALS);
				}
			}
			case "voteBox" -> {
				if((long) data.get("voteBox") <= 0) {
					e.reply("You already used all your Free Boxes!  Wait to get more!");
					return;
				} else {
					addItems.put("voteBox", -useAmount);
					for(int i = 0; i < useAmount; i++) {
						generateArray(addItems, 5 + extraRolls, 2, 3 + extraItems); // initial: 2-3 --> 3-4
					}
					e.reply("You opened `" + Numbers.formatNumber(useAmount) + "`" + Emoji.FREE_BOX + "  You received the following items...\n\n`" + Numbers.formatNumber(addItems.get("grains")) + "`" + Emoji.GRAINS + " `" + Numbers.formatNumber(addItems.get("plastic")) + "`" + Emoji.PLASTIC + " `" + Numbers.formatNumber(addItems.get("water")) + "`" + Emoji.WATER + " `" + Numbers.formatNumber(addItems.get("teaBase")) + "`" + Emoji.TEABAG + " `" + Numbers.formatNumber(addItems.get("wood")) + "`" + Emoji.WOOD + " `" + Numbers.formatNumber(addItems.get("pineSap")) + "`" + Emoji.SAP + " `" + Numbers.formatNumber(addItems.get("steel")) + "`" + Emoji.STEEL + " `" + Numbers.formatNumber(addItems.get("horseHair")) + "`" + Emoji.HORSE_HAIR);
				}
			}
			case "giftBox" -> {
				if((long) data.get("giftBox") <= 0) {
					e.reply("You already used all your Gift Boxes!  Convince your friends to gift you for more!");
					return;
				} else {
					addItems.put("giftBox", -useAmount);
					for(int i = 0; i < useAmount; i++) {
						generateArray(addItems, 6 + extraRolls, 3, 4 + extraItems); // initial: 3-5 --> 4-6
					}
					e.reply("You opened `" + Numbers.formatNumber(useAmount) + "`" + Emoji.GIFT_BOX + "  You received the following items...\n\n`" + Numbers.formatNumber(addItems.get("grains")) + "`" + Emoji.GRAINS + " `" + Numbers.formatNumber(addItems.get("plastic")) + "`" + Emoji.PLASTIC + " `" + Numbers.formatNumber(addItems.get("water")) + "`" + Emoji.WATER + " `" + Numbers.formatNumber(addItems.get("teaBase")) + "`" + Emoji.TEABAG + " `" + Numbers.formatNumber(addItems.get("wood")) + "`" + Emoji.WOOD + " `" + Numbers.formatNumber(addItems.get("pineSap")) + "`" + Emoji.SAP + " `" + Numbers.formatNumber(addItems.get("steel")) + "`" + Emoji.STEEL + " `" + Numbers.formatNumber(addItems.get("horseHair")) + "`" + Emoji.HORSE_HAIR);
				}
			}
			case "kits" -> {
				if((long) data.get("kits") <= 0) {
					e.reply("You already used all your Musician Kits!");
					return;
				} else {
					addItems.put("kits", -useAmount);
					for(int i = 0; i < useAmount; i++) {
						generateArray(addItems, 7 + extraRolls, 5, 6 + extraItems); // initial: 5-8 --> 6-10
					}
					e.reply("You opened `" + Numbers.formatNumber(useAmount) + "`" + Emoji.MUSICIAN_KIT + "  You received the following items...\n\n`" + Numbers.formatNumber(addItems.get("grains")) + "`" + Emoji.GRAINS + " `" + Numbers.formatNumber(addItems.get("plastic")) + "`" + Emoji.PLASTIC + " `" + Numbers.formatNumber(addItems.get("water")) + "`" + Emoji.WATER + " `" + Numbers.formatNumber(addItems.get("teaBase")) + "`" + Emoji.TEABAG + " `" + Numbers.formatNumber(addItems.get("wood")) + "`" + Emoji.WOOD + " `" + Numbers.formatNumber(addItems.get("pineSap")) + "`" + Emoji.SAP + " `" + Numbers.formatNumber(addItems.get("steel")) + "`" + Emoji.STEEL + " `" + Numbers.formatNumber(addItems.get("horseHair")) + "`" + Emoji.HORSE_HAIR);
				}
			}
			case "linglingBox" -> {
				if((long) data.get("linglingBox") <= 0) {
					e.reply("You already used all your Ling Ling Boxes!");
					return;
				} else {
					addItems.put("linglingBox", -useAmount);
					for(int i = 0; i < useAmount; i++) {
						generateArray(addItems, 8 + extraRolls, 5, 8 + extraItems); // initial: 7-10 --> 8-12
					}
					e.reply("You opened `" + Numbers.formatNumber(useAmount) + "`" + Emoji.LING_LING_BOX + "  You received the following items...\n\n`" + Numbers.formatNumber(addItems.get("grains")) + "`" + Emoji.GRAINS + " `" + Numbers.formatNumber(addItems.get("plastic")) + "`" + Emoji.PLASTIC + " `" + Numbers.formatNumber(addItems.get("water")) + "`" + Emoji.WATER + " `" + Numbers.formatNumber(addItems.get("teaBase")) + "`" + Emoji.TEABAG + " `" + Numbers.formatNumber(addItems.get("wood")) + "`" + Emoji.WOOD + " `" + Numbers.formatNumber(addItems.get("pineSap")) + "`" + Emoji.SAP + " `" + Numbers.formatNumber(addItems.get("steel")) + "`" + Emoji.STEEL + " `" + Numbers.formatNumber(addItems.get("horseHair")) + "`" + Emoji.HORSE_HAIR);
				}
			}
			case "crazyBox" -> {
				if((long) data.get("crazyBox") <= 0) {
					e.reply("You already used all your Crazy Person Boxes!");
					return;
				} else {
					addItems.put("crazyBox", -useAmount);
					for(int i = 0; i < useAmount; i++) {
						generateArray(addItems, 9 + extraRolls, 6, 10 + extraItems); // initial: 8-12 --> 10-15
					}
					e.reply("You opened `" + Numbers.formatNumber(useAmount) + "`" + Emoji.CRAZY_BOX + "  You received the following items...\n\n`" + Numbers.formatNumber(addItems.get("grains")) + "`" + Emoji.GRAINS + " `" + Numbers.formatNumber(addItems.get("plastic")) + "`" + Emoji.PLASTIC + " `" + Numbers.formatNumber(addItems.get("water")) + "`" + Emoji.WATER + " `" + Numbers.formatNumber(addItems.get("teaBase")) + "`" + Emoji.TEABAG + " `" + Numbers.formatNumber(addItems.get("wood")) + "`" + Emoji.WOOD + " `" + Numbers.formatNumber(addItems.get("pineSap")) + "`" + Emoji.SAP + " `" + Numbers.formatNumber(addItems.get("steel")) + "`" + Emoji.STEEL + " `" + Numbers.formatNumber(addItems.get("horseHair")) + "`" + Emoji.HORSE_HAIR);
				}
			}
			case "RNGesusBox" -> {
				if((long) data.get("RNGesusBox") <= 0) {
					e.reply("You already used all your RNGesus Boxes!");
					return;
				} else {
					addItems.put("RNGesusBox", -useAmount);
					for(int i = 0; i < useAmount; i++) {
						generateArray(addItems, 10 + extraRolls, 7, 12 + extraItems); // initial: 10-15 --> 12-18
					}
					e.reply("You opened `" + Numbers.formatNumber(useAmount) + "`" + Emoji.RNGESUS_BOX + "  You received the following items...\n\n`" + Numbers.formatNumber(addItems.get("grains")) + "`" + Emoji.GRAINS + " `" + Numbers.formatNumber(addItems.get("plastic")) + "`" + Emoji.PLASTIC + " `" + Numbers.formatNumber(addItems.get("water")) + "`" + Emoji.WATER + " `" + Numbers.formatNumber(addItems.get("teaBase")) + "`" + Emoji.TEABAG + " `" + Numbers.formatNumber(addItems.get("wood")) + "`" + Emoji.WOOD + " `" + Numbers.formatNumber(addItems.get("pineSap")) + "`" + Emoji.SAP + " `" + Numbers.formatNumber(addItems.get("steel")) + "`" + Emoji.STEEL + " `" + Numbers.formatNumber(addItems.get("horseHair")) + "`" + Emoji.HORSE_HAIR);
				}
			}
			case "rosin" -> {
				if((long) data.get("rosin") <= 0) {
					e.reply("You look in your violin case, but find no rosin left.  You are disappointed.");
					return;
				} else {
					addItems.put("rosin", -useAmount);
					addTime(data, "rosinExpire", useAmount * 180000000);
					e.reply(Emoji.ROSIN + "  You apply rosin to your bow.  You are now entitled to `" + Numbers.formatNumber(50 * useAmount) + "` more hours of income.");
				}
			}
			case "string" -> {
				if((long) data.get("string") <= 0) {
					e.reply("You scourge your stocks, but you can't find violin strings.  You then remember that you have to buy some, then promptly forget.");
					return;
				} else {
					addItems.put("string", -useAmount);
					addTime(data, "stringsExpire", useAmount * 360000000);
					e.reply(Emoji.STRING + "  You change the strings on your violin.  You are now entitled to `" + Numbers.formatNumber(100 * useAmount) + "` more hours of income.");
				}
			}
			case "bowHair" -> {
				if((long) data.get("bowHair") <= 0) {
					e.reply("You scourge your stocks, but you can't find extra bow hair.  You then remember that you have to buy some, then promptly forget.");
					return;
				} else {
					addItems.put("bowHair", -useAmount);
					addTime(data, "bowHairExpire", useAmount * 540000000);
					e.reply(Emoji.BOW_HAIR + "  You asked Olaf to rehair your bow, to which he agreed.  You are now entitled to `" + Numbers.formatNumber(150 * useAmount) + "` more hours of income.");
				}
			}
			case "violinService" -> {
				if((long) data.get("violinService") <= 0) {
					e.reply("You go to Olaf to get your violin repaired, but forget to bring materials.  He kicks you out of the store.");
					return;
				} else {
					addItems.put("violinService", -useAmount);
					addTime(data, "serviceExpire", useAmount * 900000000);
					e.reply(Emoji.SERVICE + "  You asked Olaf to service your violin, to which he agreed.  You are now entitled to `" + Numbers.formatNumber(250 * useAmount) + "` more hours of income.");
				}
			}
			default -> {
				e.reply("You can't use something that doesn't exist, that doesn't make sense.");
				return;
			}
		}
		addItems.put(item, -useAmount);
		for(Map.Entry<String, Long> entry : addItems.entrySet()) {
			data.replace(entry.getKey(), (long) data.get(entry.getKey()) + entry.getValue());
			if(entry.getKey().equals("violins")) {
				data.replace("earnings", (long) data.get("earnings") + entry.getValue());
			}
		}
		SaveData.saveData(e, data);
	}
}