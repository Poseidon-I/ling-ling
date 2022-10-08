package processes;

import org.json.simple.JSONObject;

public class Numbers {
	public static double luthierChance(int members) {
		if(members < 20) {
			return 0.1;
		} else if(members < 100) {
			return 0.1 - 0.001 * (members - 20);
		} else if(members < 1000) {
			return 0.02;
		} else if(members < 10000) {
			return 0.02 - 0.000002 * (members - 1000);
		} else {
			return 0.002;
		}
	}
	
	public static String formatNumber(Object number) {
		long tempNum = (long) number;
		String num = String.valueOf(tempNum);
		StringBuilder result = new StringBuilder();
		int temp = 0;
		for(int i = num.length() - 1; i >= 0; i --) {
			result.insert(0, num.charAt(i));
			if(temp == 2 && i != 0) {
				result.insert(0, " ");
				temp = 0;
			} else {
				temp ++;
			}
		}
		return result.toString();
	}
	
	public static long calculateAmount(JSONObject data, long base) {
		long efficiency = (long) data.get("efficiency");
		if(efficiency < 10) {
			base *= Math.pow(1.1, efficiency);
		} else if(efficiency < 100) {
			base *= Math.pow(1.1, 10) * Math.pow(1.05, efficiency - 10);
		} else {
			base *= Math.pow(1.1, 10) * Math.pow(1.05, 90) * Math.pow(1.02, efficiency - 100);
		}
		double multiplier = 1.0;
		multiplier += (double) data.get("hall") * 0.125;
		multiplier += (double) data.get("moreIncome") * 0.3;
		multiplier += (double) data.get("serverLevel") - 1;
		if((boolean) data.get("isBooster")) {
			multiplier += 0.3;
		}
		return (long) (base * multiplier);
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
	
	public static void calculateLoan(JSONObject data, long earned) {
		long loan = (long) data.get("loan");
		long income = (long) data.get("income");
		long violins = (long) data.get("violins");
		if(loan > income * 400) {
			violins -= earned * 0.1;
			loan -= earned * 1.1;
		} else if(loan > income * 250) {
			violins += earned * 0.2;
			loan -= earned * 0.8;
		} else if(loan > income * 100) {
			violins += earned * 0.5;
			loan -= earned * 0.5;
		} else if(loan > 0) {
			violins += earned * 0.8;
			loan -= earned * 0.2;
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
}