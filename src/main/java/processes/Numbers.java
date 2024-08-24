package processes;

import org.json.simple.JSONObject;

public class Numbers {
	public static double luthierChance(int members) {
		if(members < 10) {
			return 0.1;
		} else if(members < 100) {
			return 0.1 - 0.001 * (members - 10);
		} else if(members < 1000) {
			return 0.01;
		} else if(members < 10000) {
			return 0.01 - 0.000001 * (members - 1000);
		} else {
			return 0.001;
		}
	}

	public static String formatNumber(Object number) {
		long tempNum = (long) number;
		String num = String.valueOf(tempNum);
		StringBuilder result = new StringBuilder();
		int temp = 0;
		for(int i = num.length() - 1; i >= 0; i--) {
			result.insert(0, num.charAt(i));
			if(temp == 2 && i != 0) {
				result.insert(0, " ");
				temp = 0;
			} else {
				temp++;
			}
		}
		if(result.charAt(0) == '-' && result.charAt(1) == ' ') {
			result.deleteCharAt(1);
		}
		return "`" + result + "`";
	}

	public static long calculateAmount(JSONObject data, long base) {
		long efficiency = (long) data.get("efficiency");
		if(efficiency < 10) {
			base = (long) (base * Math.pow(1.1, efficiency));
		} else if(efficiency < 100) {
			base = (long) (base * (Math.pow(1.1, 10) * Math.pow(1.05, efficiency - 10)));
		} else {
			base = (long) (base * (Math.pow(1.1, 10) * Math.pow(1.05, 90) * Math.pow(1.025, efficiency - 100)));
		}
		base = (long) (base * Math.pow(1.125, (long) data.get("hall")));
		base = (long) (base * Math.pow(1.3, (long) data.get("moreCommandIncome")));
		if((boolean) data.get("isBooster")) {
			base = (long) (base * (0.3 + (double) data.get("serverLevel")));
		} else {
			base = (long) (base * (double) data.get("serverLevel"));
		}
		return base;
	}

	public static long itemCost(long level, double power, long base) {
		return (long) (base * Math.pow(power, level));
	}

	public static long maxLoan(JSONObject data) {
		return (long) Math.pow(100, Math.log10((long) data.get("income")) - 1);
	}

	public static void calculateLoan(JSONObject data, long earned) {
		long loan = (long) data.get("loan");
		long violins = (long) data.get("violins");
		long maxLoan = maxLoan(data);
		if(loan > maxLoan * 2) {
			violins = (long) (violins - (earned * 0.1));
			loan = (long) (loan - (earned * 1.1));
		} else if(loan > maxLoan * 1.25) {
			violins = (long) (violins + earned * 0.2);
			loan = (long) (loan - (earned * 0.8));
		} else if(loan > maxLoan / 2) {
			violins = (long) (violins + earned * 0.5);
			loan = (long) (loan - (earned * 0.5));
		} else if(loan > 0) {
			violins = (long) (violins + earned * 0.8);
			loan = (long) (loan - (earned * 0.2));
		} else {
			violins += earned;
		}
		if(loan < 0) {
			violins -= loan;
			loan = 0;
		}
		data.replace("violins", violins);
		data.replace("loan", loan);
	}

	public static boolean containsBadLanguage(String input) {
		return input.contains("@everyone") || input.contains("@here") || input.contains("<@&") || input.contains("nigg") || input.contains("nibba") || input.contains("cunt") || input.contains("chink");
	}

	public static long maxBank(JSONObject data) {
		return 20000000 * (long) data.get("storage") + 1000000 * (long) data.get("benevolentBankers");
	}

	public static String makeCooldownTime(long milliseconds) {
		long hours = milliseconds / 3600000;
		milliseconds -= hours * 3600000;
		long minutes = milliseconds / 60000;
		milliseconds -= minutes * 60000;
		long seconds = milliseconds / 1000;
		milliseconds -= seconds * 1000;

		String answer = "`";
		if(hours > 0) {
			answer += reformat(hours) + ":";
		}
		if(minutes > 0) {
			answer += reformat(minutes) + ":";
		}
		answer += reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`";
		return answer;
	}

	public static String reformat(long string) {
		String newString = String.valueOf(string);
		if(String.valueOf(string).length() == 1) {
			newString = "0" + string;
		}
		return newString;
	}

	public static String reformatMilliseconds(long string) {
		String newString = String.valueOf(string);
		if(String.valueOf(string).length() == 1) {
			newString = "00" + string;
		} else if(String.valueOf(string).length() == 2) {
			newString = "0" + string;
		}
		return newString;
	}

	public static long skillLevel(long xp) {
		if(xp < 50) {
			return 0;
		} else if(xp < 175) {
			return 1;
		} else if(xp < 375) {
			return 2;
		} else if(xp < 675) {
			return 3;
		} else if(xp < 1175) {
			return 4;
		} else if(xp < 1925) {
			return 5;
		} else if(xp < 2925) {
			return 6;
		} else if(xp < 4425) {
			return 7;
		} else if(xp < 6425) {
			return 8;
		} else if(xp < 9925) {
			return 9;
		} else if(xp < 14925) {
			return 10;
		} else if(xp < 22425) {
			return 11;
		} else if(xp < 32425) {
			return 12;
		} else if(xp < 47425) {
			return 13;
		} else if(xp < 67425) {
			return 14;
		} else if(xp < 97425) {
			return 15;
		} else if(xp < 147425) {
			return 16;
		} else if(xp < 222425) {
			return 17;
		} else if(xp < 322425) {
			return 18;
		} else if(xp < 522425) {
			return 19;
		} else if(xp < 822425) {
			return 20;
		} else if(xp < 1222425) {
			return 21;
		} else if(xp < 1722425) {
			return 22;
		} else if(xp < 2322425) {
			return 23;
		} else if(xp < 3022425) {
			return 24;
		} else if(xp < 3822425) {
			return 25;
		} else if(xp < 4722425) {
			return 26;
		} else if(xp < 5722425) {
			return 27;
		} else if(xp < 6822425) {
			return 28;
		} else if(xp < 8022425) {
			return 29;
		} else if(xp < 9322425) {
			return 30;
		} else if(xp < 10722425) {
			return 31;
		} else if(xp < 12222425) {
			return 32;
		} else if(xp < 13822425) {
			return 33;
		} else if(xp < 15522425) {
			return 34;
		} else if(xp < 17322425) {
			return 35;
		} else if(xp < 19222425) {
			return 36;
		} else if(xp < 21222425) {
			return 37;
		} else if(xp < 23322425) {
			return 38;
		} else if(xp < 25522425) {
			return 39;
		} else if(xp < 27822425) {
			return 40;
		} else if(xp < 30222425) {
			return 41;
		} else if(xp < 32722425) {
			return 42;
		} else if(xp < 35322425) {
			return 43;
		} else if(xp < 38072425) {
			return 44;
		} else if(xp < 40972425) {
			return 45;
		} else if(xp < 44072425) {
			return 46;
		} else if(xp < 47472425) {
			return 47;
		} else if(xp < 51172425) {
			return 48;
		} else if(xp < 55172425) {
			return 49;
		} else if(xp < 59472425) {
			return 50;
		} else if(xp < 64072425) {
			return 51;
		} else if(xp < 68072425) {
			return 52;
		} else if(xp < 74172425) {
			return 53;
		} else if(xp < 79672425) {
			return 54;
		} else if(xp < 85472425) {
			return 55;
		} else if(xp < 91572425) {
			return 56;
		} else if(xp < 97972425) {
			return 57;
		} else if(xp < 104672425) {
			return 58;
		} else if(xp < 111672425) {
			return 59;
		} else {
			return 60;
		}
	}

	public static long cataLevel(long xp) {
		if(xp < 50) {
			return 0;
		} else if(xp < 125) {
			return 1;
		} else if(xp < 235) {
			return 2;
		} else if(xp < 395) {
			return 3;
		} else if(xp < 625) {
			return 4;
		} else if(xp < 955) {
			return 5;
		} else if(xp < 1425) {
			return 6;
		} else if(xp < 2095) {
			return 7;
		} else if(xp < 3045) {
			return 8;
		} else if(xp < 4385) {
			return 9;
		} else if(xp < 6275) {
			return 10;
		} else if(xp < 8940) {
			return 11;
		} else if(xp < 12700) {
			return 12;
		} else if(xp < 17960) {
			return 13;
		} else if(xp < 25340) {
			return 14;
		} else if(xp < 35640) {
			return 15;
		} else if(xp < 50040) {
			return 16;
		} else if(xp < 70040) {
			return 17;
		} else if(xp < 97640) {
			return 18;
		} else if(xp < 135640) {
			return 19;
		} else if(xp < 188140) {
			return 20;
		} else if(xp < 259640) {
			return 21;
		} else if(xp < 356640) {
			return 22;
		} else if(xp < 488640) {
			return 23;
		} else if(xp < 668640) {
			return 24;
		} else if(xp < 911640) {
			return 25;
		} else if(xp < 1239640) {
			return 26;
		} else if(xp < 1684640) {
			return 27;
		} else if(xp < 2284640) {
			return 28;
		} else if(xp < 3084640) {
			return 29;
		} else if(xp < 4149640) {
			return 30;
		} else if(xp < 5559640) {
			return 31;
		} else if(xp < 7459640) {
			return 32;
		} else if(xp < 9959640) {
			return 33;
		} else if(xp < 13259640) {
			return 34;
		} else if(xp < 17559640) {
			return 35;
		} else if(xp < 23159640) {
			return 36;
		} else if(xp < 30359640) {
			return 37;
		} else if(xp < 39559640) {
			return 38;
		} else if(xp < 51559640) {
			return 39;
		} else if(xp < 66559640) {
			return 40;
		} else if(xp < 85559640) {
			return 41;
		} else if(xp < 109559640) {
			return 42;
		} else if(xp < 139559640) {
			return 43;
		} else if(xp < 177559640) {
			return 44;
		} else if(xp < 225559640) {
			return 45;
		} else if(xp < 285559640) {
			return 46;
		} else if(xp < 360559640) {
			return 47;
		} else if(xp < 453559640) {
			return 48;
		} else if(xp < 569809640) {
			return 49;
		} else {
			return 50;
		}
	}

	public static long slayerLevel(long xp) {
		if(xp < 10) {
			return 0;
		} else if(xp < 30) {
			return 1;
		} else if(xp < 250) {
			return 2;
		} else if(xp < 1500) {
			return 3;
		} else if(xp < 5000) {
			return 4;
		} else if(xp < 20000) {
			return 5;
		} else if(xp < 100000) {
			return 6;
		} else if(xp < 400000) {
			return 7;
		} else if(xp < 1000000) {
			return 8;
		} else {
			return 9;
		}
	}

	public static long hotmLevel(long xp) {
		if(xp < 3000) {
			return 1;
		} else if(xp < 12000) {
			return 2;
		} else if(xp < 37000) {
			return 3;
		} else if(xp < 97000) {
			return 4;
		} else if(xp < 197000) {
			return 5;
		} else if(xp < 347000) {
			return 6;
		} else if(xp < 557000) {
			return 7;
		} else if(xp < 827000) {
			return 8;
		} else if(xp < 1247000) {
			return 9;
		} else {
			return 10;
		}
	}

	public static long cropMilestone(long collected, double multiplier) {
		if(collected < multiplier * 30) {
			return 0;
		} else if(collected < multiplier * 80) {
			return 1;
		} else if(collected < multiplier * 160) {
			return 2;
		} else if(collected < multiplier * 330) {
			return 3;
		} else if(collected < multiplier * 660) {
			return 4;
		} else if(collected < multiplier * 1330) {
			return 5;
		} else if(collected < multiplier * 2660) {
			return 6;
		} else if(collected < multiplier * 5160) {
			return 7;
		} else if(collected < multiplier * 8660) {
			return 8;
		} else if(collected < multiplier * 13660) {
			return 9;
		} else if(collected < multiplier * 20160) {
			return 10;
		} else if(collected < multiplier * 28160) {
			return 11;
		} else if(collected < multiplier * 38160) {
			return 12;
		} else if(collected < multiplier * 58160) {
			return 13;
		} else if(collected < multiplier * 93160) {
			return 14;
		} else if(collected < multiplier * 143160) {
			return 15;
		} else if(collected < multiplier * 218160) {
			return 16;
		} else if(collected < multiplier * 318160) {
			return 17;
		} else if(collected < multiplier * 493160) {
			return 18;
		} else if(collected < multiplier * 743160) {
			return 19;
		} else if(collected < multiplier * 1093160) {
			return 20;
		} else if(collected < multiplier * 1593160) {
			return 21;
		} else if(collected < multiplier * 2343160) {
			return 22;
		} else if(collected < multiplier * 3343160) {
			return 23;
		} else if(collected < multiplier * 4543160) {
			return 24;
		} else if(collected < multiplier * 6243160) {
			return 25;
		} else if(collected < multiplier * 8243160) {
			return 26;
		} else if(collected < multiplier * 10543160) {
			return 27;
		} else if(collected < multiplier * 13143160) {
			return 28;
		} else if(collected < multiplier * 16143160) {
			return 29;
		} else if(collected < multiplier * 19143160) {
			return 30;
		} else if(collected < multiplier * 22143160) {
			return 31;
		} else if(collected < multiplier * 25143160) {
			return 32;
		} else if(collected < multiplier * 28143160) {
			return 33;
		} else if(collected < multiplier * 31143160) {
			return 34;
		} else if(collected < multiplier * 34143160) {
			return 35;
		} else if(collected < multiplier * 37143160) {
			return 36;
		} else if(collected < multiplier * 40143160) {
			return 37;
		} else if(collected < multiplier * 43143160) {
			return 38;
		} else if(collected < multiplier * 46143160) {
			return 39;
		} else if(collected < multiplier * 49143160) {
			return 40;
		} else if(collected < multiplier * 52143160) {
			return 41;
		} else if(collected < multiplier * 55143160) {
			return 42;
		} else if(collected < multiplier * 58143160) {
			return 43;
		} else if(collected < multiplier * 61143160) {
			return 44;
		} else if(collected < multiplier * 64143160) {
			return 45;
		} else {
			return 46;
		}
	}
}