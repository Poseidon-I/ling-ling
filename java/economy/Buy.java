package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Buy {
	public static void ProcessBooleanUpgrade(GuildMessageReceivedEvent e, long cost, long add, int index, String item, String[] data) {
		long violins = Long.parseLong(data[0]);
		if(Boolean.parseBoolean(data[index])) {
			e.getChannel().sendMessage("You already purchased `" + item + "`!").queue();
		} else if(violins < cost) {
			e.getChannel().sendMessage("You do not have enough violins to purchase `" + item + "`!\nYou need `" + cost + "`:violin:, you only have `" + violins + "`:violin:").queue();
		} else {
			data[0] = String.valueOf(violins - cost);
			data[12] = String.valueOf(Long.parseLong(data[12]) + add);
			data[index] = "true";
			e.getChannel().sendMessage("Successfully purchased `" + item + "` for `" + cost + "`:violin:\nYou have `" + data[0] + "`:violin: left.").queue();
			new SaveData(e, data, "Economy Data");
			if(item.equals("Orchestra")) {
				String[] bankdata = LoadData.loadData(e, "Bank Data");
				bankdata[1] = "1";
				new SaveData(e, bankdata, "Bank Data");
				e.getChannel().sendMessage("The Bank of TwoSet has noticed you, and has given you space to store violins!  You have 15 million storage for free, this can increased by buying more space using medals.").queue();
			}
		}
	}
	
	public static void ProcessUpgrade(GuildMessageReceivedEvent e, long cost, long nextCost, long add, int index, long maxAmount, String item, String[] data) {
		long violins = Long.parseLong(data[0]);
		if(Long.parseLong(data[index]) == maxAmount) {
			e.getChannel().sendMessage("You already purchased the maximum amount of `" + item + "`!").queue();
		} else if(violins < cost) {
			e.getChannel().sendMessage("You do not have enough violins to purchase `" + item + "`!\nYou need `" + cost + "`:violin:, you only have `" + data[0] + "`:violin:").queue();
		} else {
			data[0] = String.valueOf(violins - cost);
			data[12] = String.valueOf(Long.parseLong(data[12]) + add);
			data[index] = String.valueOf(Long.parseLong(data[index]) + 1);
			if(Long.parseLong(data[index]) == maxAmount) {
				e.getChannel().sendMessage("Successfully purchased `" + item + " #" + data[index] + "` for `" + cost + "`:violin:\nYou have `" + data[0] + "`:violin: left.  **MAX LEVEL**").queue();
			} else {
				e.getChannel().sendMessage("Successfully purchased `" + item + " #" + data[index] + "` for `" + cost + "`:violin:\nYou have `" + data[0] + "`:violin: left.  The next level costs `" + nextCost + "`:violin:").queue();
			}
			new SaveData(e, data, "Economy Data");
		}
	}
	
	public static void ProcessMedalUpgrade(GuildMessageReceivedEvent e, long cost, long nextCost, int index, String item, String[] data) {
		long medals = Long.parseLong(data[55]);
		if(medals < cost) {
			e.getChannel().sendMessage("You do not have enough Ling Ling Medals to purchase `" + item + "`!\nYou need " + cost + ":military_medal:, you only have " + medals + ":military_medal:").queue();
		} else {
			data[55] = String.valueOf(medals - cost);
			data[index] = String.valueOf(Long.parseLong(data[index]) + 1);
			if(index == 56) {
				data[12] = String.valueOf(Long.parseLong(data[12]) + 2500);
			}
			e.getChannel().sendMessage("Successfully purchased `" + item + " #" + data[index] + "` for `" + cost + "`:military_medal:\nYou have `" + data[55] + "`:military_medal: left.  The next level costs `" + nextCost + "`:military_medal:").queue();
			new SaveData(e, data, "Economy Data");
		}
	}
	
	public static void ProcessMedalBooleanUpgrade(GuildMessageReceivedEvent e, long cost, int index, String item, String[] data) {
		long medals = Long.parseLong(data[55]);
		if(Boolean.parseBoolean(data[index])) {
			e.getChannel().sendMessage("You already purchased `" + item + "`!").queue();
		} else if(medals < cost) {
			e.getChannel().sendMessage("You do not have enough Ling Ling Medals to purchase `" + item + "`!\nYou need `" + cost + "`:military_medal:, you only have `" + medals + "`:military_medal:").queue();
		} else {
			data[55] = String.valueOf(medals - cost);
			data[index] = "true";
			e.getChannel().sendMessage("Successfully purchased `" + item + "` for `" + cost + "`:military_medal:\nYou have `" + data[55] + "`:military_medal: left.").queue();
			new SaveData(e, data, "Economy Data");
		}
	}
	
	public static void ProcessBankUpgrade(GuildMessageReceivedEvent e, long cost, long nextCost, int index, String item, String[] data, String[] bankdata) {
		long medals = Long.parseLong(data[55]);
		if(medals < cost) {
			e.getChannel().sendMessage("You do not have enough Ling Ling Medals to purchase `" + item + "`!\nYou need `" + cost + "`:military_medal:, you only have `" + data[55] + "`:military_medal:").queue();
		} else {
			data[55] = String.valueOf(medals - cost);
			bankdata[index] = String.valueOf(Long.parseLong(bankdata[index]) + 1);
			e.getChannel().sendMessage("Successfully purchased `" + item + " #" + bankdata[index] + "` for `" + cost + "`:military_medal:\nYou have `" + data[55] + "`:military_medal: left.  The next level costs `" + nextCost + "`:military_medal:").queue();
			new SaveData(e, data, "Economy Data");
			new SaveData(e, bankdata, "Bank Data");
		}
	}
	
	public static void ProcessBooleanBankUpgrade(GuildMessageReceivedEvent e, long cost, int index, String item, String[] data, String[] bankdata) {
		long medals = Long.parseLong(data[55]);
		if(Boolean.parseBoolean(bankdata[index])) {
			e.getChannel().sendMessage("You already purchased `" + item + "`!").queue();
		} else if(medals < cost) {
			e.getChannel().sendMessage("You do not have enough Ling Ling Medals to purchase `" + item + "`!\nYou need `" + cost + "`:military_medal:, you only have `" + medals + "`:military_medal:").queue();
		} else {
			data[55] = String.valueOf(medals - cost);
			bankdata[index] = "true";
			e.getChannel().sendMessage("Successfully purchased `" + item + "` for `" + cost + "`:military_medal:\nYou have `" + data[55] + "`:military_medal: left.").queue();
			new SaveData(e, data, "Economy Data");
			new SaveData(e, bankdata, "Bank Data");
		}
	}
	
	public Buy(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] message = e.getMessage().getContentRaw().split(" ");
		boolean boughtItem = false;
		boolean hasOrchestra = Boolean.parseBoolean(data[19]);
		boolean hasCertificate = Boolean.parseBoolean(data[78]);
		if(hasCertificate) {
			boughtItem = true;
			switch(message[1]) {
				case "longer" -> ProcessBooleanUpgrade(e, 25000000, 0, 84, "Longer Lessons", data);
				case "studio" -> ProcessBooleanUpgrade(e, 25000000, 5000, 83, "Teaching Studio", data);
				case "training" -> ProcessUpgrade(e, 1250000 * (Long.parseLong(data[80]) + 1), 1250000 * (Long.parseLong(data[80]) + 2), 1000, 80, 10, "Teacher Training", data);
				case "pricing" -> ProcessUpgrade(e, 2000000 * (Long.parseLong(data[82]) + 1), 2000000 * (Long.parseLong(data[82]) + 2), 3000, 82, 5, "Higher Lesson Rates", data);
				case "students" -> ProcessUpgrade(e, (long) Math.pow(2, Double.parseDouble(data[81])) * 1000000, (long) Math.pow(2, Double.parseDouble(data[81]) + 1) * 1000000, 2000, 81, 2147483647, "Student", data);
				default -> boughtItem = false;
			}
		}
		if(hasOrchestra && !boughtItem) {
			boughtItem = true;
			String[] bankdata = LoadData.loadData(e, "Bank Data");
			switch(message[1]) {
				case "piccolo" -> ProcessBooleanUpgrade(e, 250000, 30, 20, "Piccolo", data);
				case "contrabassoon", "cb" -> ProcessBooleanUpgrade(e, 300000, 30, 25, "ContraBassoon", data);
				case "harp" -> ProcessBooleanUpgrade(e, 400000, 80, 37, "Harp", data);
				case "flute" -> ProcessUpgrade(e, 300000 * (Long.parseLong(data[21]) + 1), 300000 * (Long.parseLong(data[21]) + 2), 60, 21, 4, "Flute", data);
				case "oboe" -> ProcessUpgrade(e, 300000 * (Long.parseLong(data[22]) + 1), 300000 * (Long.parseLong(data[22]) + 2), 60, 22, 4, "Oboe", data);
				case "clarinet" -> ProcessUpgrade(e, 250000 * (Long.parseLong(data[23]) + 1), 250000 * (Long.parseLong(data[23]) + 2), 50, 23, 4, "Clarinet", data);
				case "bassoon" -> ProcessUpgrade(e, 250000 * (Long.parseLong(data[24]) + 1), 250000 * (Long.parseLong(data[24]) + 2), 40, 24, 4, "Bassoon", data);
				case "horn" -> ProcessUpgrade(e, 250000 * (Long.parseLong(data[26]) + 1), 250000 * (Long.parseLong(data[26]) + 2), 40, 26, 8, "French Horn", data);
				case "trumpet" -> ProcessUpgrade(e, 200000 * (Long.parseLong(data[27]) + 1), 200000 * (Long.parseLong(data[27]) + 2), 30, 27, 4, "Trumpet", data);
				case "trombone" -> ProcessUpgrade(e, 200000 * (Long.parseLong(data[28]) + 1), 200000 * (Long.parseLong(data[28]) + 2), 20, 28, 6, "Trombone", data);
				case "tuba" -> ProcessUpgrade(e, 200000 * (Long.parseLong(data[29]) + 1), 200000 * (Long.parseLong(data[29]) + 2), 20, 29, 2, "Tuba", data);
				case "timpani" -> ProcessUpgrade(e, 300000 * (Long.parseLong(data[30]) + 1), 300000 * (Long.parseLong(data[30]) + 2), 60, 30, 2, "Timpani", data);
				case "percussion" -> ProcessUpgrade(e, 100000 * (Long.parseLong(data[31]) + 1), 100000 * (Long.parseLong(data[31]) + 2), 10, 31, 2, "Percussion", data);
				case "first" -> ProcessUpgrade(e, 500000 * (Long.parseLong(data[32])), 500000 * (Long.parseLong(data[32]) + 1), 70, 32, 20, "First Violin", data);
				case "second" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[33])), 400000 * (Long.parseLong(data[33]) + 1), 60, 33, 20, "Second Violin", data);
				case "cello" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[34]) + 1), 400000 * (Long.parseLong(data[34]) + 2), 50, 34, 15, "Cello", data);
				case "db", "doublebass" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[35]) + 1), 400000 * (Long.parseLong(data[35]) + 2), 50, 35, 5, "Double Bass", data);
				case "piano" -> ProcessUpgrade(e, 750000 * (Long.parseLong(data[36]) + 1), 750000 * (Long.parseLong(data[36]) + 2), 110, 36, 2, "Piano", data);
				case "soprano" -> ProcessUpgrade(e, 100000 * (Long.parseLong(data[38]) + 1), 100000 * (Long.parseLong(data[38]) + 2), 30, 38, 20, "Soprano Vocalist", data);
				case "alto" -> ProcessUpgrade(e, 75000 * (Long.parseLong(data[39]) + 1), 75000 * (Long.parseLong(data[39]) + 2), 20, 39, 20, "Alto Vocalist", data);
				case "tenor" -> ProcessUpgrade(e, 75000 * (Long.parseLong(data[40]) + 1), 75000 * (Long.parseLong(data[40]) + 2), 20, 40, 20, "Tenor Vocalist", data);
				case "bass" -> ProcessUpgrade(e, 75000 * (Long.parseLong(data[41]) + 1), 75000 * (Long.parseLong(data[41]) + 2), 20, 41, 20, "Bass Vocalist", data);
				case "soloist" -> ProcessUpgrade(e, 300000 * (Long.parseLong(data[42]) + 1), 300000 * (Long.parseLong(data[42]) + 2), 60, 42, 4, "Solo Vocalist", data);
				case "conductor", "musicality" -> ProcessUpgrade(e, (long) (Math.pow(4, Long.parseLong(data[44])) * 100000), (long) (Math.pow(4, Long.parseLong(data[44]) + 1) * 100000), 200, 44, 2147483647, "Conductor Musicality", data);
				case "advertisement", "ad" -> ProcessUpgrade(e, 100000 * (Long.parseLong(data[45]) + 1), 100000 * (Long.parseLong(data[45]) + 2), 100, 45, 20, "Advertising", data);
				case "tickets" -> ProcessUpgrade(e, (long) (Math.pow(2, Long.parseLong(data[46])) * 1000000), (long) (Math.pow(2, Long.parseLong(data[46]) + 1) * 1000000), 1000, 46, 2147483647, "Ticket Cost", data);
				case "interest" -> ProcessBooleanBankUpgrade(e, 15, 2, "Higher Interest", data, bankdata);
				case "lower" -> ProcessBooleanBankUpgrade(e, 15, 4, "Lower Loan Interest", data, bankdata);
				case "space" -> ProcessBankUpgrade(e, 3 * Long.parseLong(bankdata[1]), 3 * (Long.parseLong(bankdata[1]) + 1), 1, "Storage Space", data, bankdata);
				case "certificate" -> {
					if(Long.parseLong(data[12]) < 40000) {
						e.getChannel().sendMessage("You do not have neough hourly income to become a teacher!").queue();
					} else {
						ProcessBooleanUpgrade(e, 200000000, 5000, 78, "Teacher's Certificate", data);
					}
				}
				default -> boughtItem = false;
			}
		}
		if(!boughtItem) {
			boughtItem = true;
			switch(message[1]) {
				case "insurance" -> ProcessBooleanUpgrade(e, 2500000, 0, 9, "Ling Ling Insurance", data);
				case "timecrunch", "tc" -> ProcessBooleanUpgrade(e, 120000000, 0, 50, "Time Crunch", data);
				case "efficiency", "ep" -> ProcessUpgrade(e, (long) (Math.pow(1.1, Long.parseLong(data[2])) * 400), (long) (Math.pow(1.1, Long.parseLong(data[2]) + 1) * 400), 0, 2, 2147483647, "Efficient Practising", data);
				case "lucky", "lm" -> ProcessUpgrade(e, (long) (Math.pow(1.25, Long.parseLong(data[4])) * 1000), (long) (Math.pow(1.25, Long.parseLong(data[4]) + 1) * 1000), 0, 4, 50, "Lucky Musician", data);
				case "robbing", "sr" -> ProcessUpgrade(e, (long) (Math.pow(1.4, Long.parseLong(data[6])) * 5000), (long) (Math.pow(1.4, Long.parseLong(data[6]) + 1) * 5000), 0, 6, 30, "Sophisticated Robbing", data);
				case "violin", "v" -> ProcessUpgrade(e, (long) (Math.pow(3, Long.parseLong(data[13])) * 1000), (long) (Math.pow(3, Long.parseLong(data[13]) + 1) * 1000), 600, 13, 2147483647, "Violin Quality", data);
				case "skill", "s" -> ProcessUpgrade(e, (long) (Math.pow(2, Long.parseLong(data[14])) * 500), (long) (Math.pow(2, Long.parseLong(data[14]) + 1) * 500), 240, 14, 2147483647, "Skill Level", data);
				case "lesson", "l" -> ProcessUpgrade(e, (long) (Math.pow(2.5, Long.parseLong(data[15])) * 750), (long) (Math.pow(2.5, Long.parseLong(data[15]) + 1) * 750), 150, 15, 2147483647, "Lesson Quality", data);
				case "string", "str" -> ProcessUpgrade(e, (long) (Math.pow(1.75, Long.parseLong(data[16])) * 500), (long) (Math.pow(1.75, Long.parseLong(data[16]) + 1) * 500), 100, 16, 2147483647, "String Quality", data);
				case "bow", "b" -> ProcessUpgrade(e, (long) (Math.pow(3, Long.parseLong(data[17])) * 750), (long) (Math.pow(3, Long.parseLong(data[17]) + 1) * 750), 200, 17, 2147483647, "Bow Quality", data);
				case "math" -> ProcessBooleanUpgrade(e, 10000000, 6500, 18, "Math Tutoring", data);
				case "income" -> ProcessMedalUpgrade(e, Long.parseLong(data[56]) + 1, Long.parseLong(data[56]) + 2, 56, "Extra Income", data);
				case "commandincome" -> ProcessMedalUpgrade(e, (long) Math.pow(2, Long.parseLong(data[57])), (long) Math.pow(2, Long.parseLong(data[57]) + 1), 57, "Extra Command Income", data);
				case "gamblelimit" -> ProcessMedalUpgrade(e, Long.parseLong(data[58]) + 1, Long.parseLong(data[58]) + 2, 58, "Higher Gamble Limit", data);
				case "robsuccess" -> ProcessMedalUpgrade(e, (long) Math.pow(2, Long.parseLong(data[59])), (long) Math.pow(2, Long.parseLong(data[59]) + 1), 59, "Higher Rob Success Rate", data);
				case "shield" -> ProcessMedalBooleanUpgrade(e, 10, 60, "Steal Shield", data);
				case "duplicator" -> ProcessMedalBooleanUpgrade(e, 15, 61, "Violin Duplicator", data);
				case "orchestra", "o" -> {
					if(Long.parseLong(data[12]) < 7500) {
						e.getChannel().sendMessage("You do not have enough hourly income to hire an orchestra!").queue();
					} else {
						ProcessBooleanUpgrade(e, 25000000, 3100, 19, "Orchestra", data);
					}
				}
				case "concert", "hall" -> {
					if(!hasOrchestra) {
						ProcessUpgrade(e, (long) (Math.pow(5, Long.parseLong(data[43])) * 50000), (long) (Math.pow(5, Long.parseLong(data[43]) + 1) * 50000), 300, 43, 2, "Concert Hall Quality", data);
					} else {
						ProcessUpgrade(e, (long) (Math.pow(5, Long.parseLong(data[43])) * 50000), (long) (Math.pow(5, Long.parseLong(data[43]) + 1) * 50000), 300, 43, 2147483647, "Concert Hall Quality", data);
					}
				}
				default -> boughtItem = false;
			}
		}
		if(!boughtItem) {
			e.getChannel().sendMessage("You can't buy something that's not for sale, that would be quite a waste of time and violins.").queue();
		}
	}
}