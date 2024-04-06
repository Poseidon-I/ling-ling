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
		return result.toString();
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
		long cost = (long) (base * Math.pow(power, level));
		if(cost <= 100000000) {
			return cost;
		} else if(cost <= 210000000) {
			return (long) (cost * 0.9 + 10000000);
		} else if(cost <= 330000000) {
			return (long) (cost * 0.8 + 31000000);
		} else if(cost <= 470000000) {
			return (long) (cost * 0.75 + 47500000);
		} else if(cost <= 610000000) {
			return (long) (cost * 0.7 + 71000000);
		} else if(cost <= 780000000) {
			return (long) (cost * 0.6 + 132000000);
		} else {
			return (long) (cost * 0.5 + 210000000);
		}
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
}