package processes;

import org.json.simple.JSONObject;

public class Numbers {
	public static double LuthierChance(int members) {
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
	
	public static String FormatNumber(Object number) {
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
	
	public static long CalculateAmount(JSONObject data, long base) {
		long efficiency = (long) data.get("efficiency");
		if(efficiency > 20) {
			base *= Math.pow(1.1, 20) * Math.pow(1.04, efficiency - 20);
		} else {
			base *= Math.pow(1.1, efficiency);
		}
		base *= Math.pow(1.1, (long) data.get("hall"));
		base *= Math.pow(1.25, (long) data.get("moreIncome"));
		if((boolean) data.get("isBooster")) {
			base *= 1.3;
		}
		double level = (double) data.get("serverLevel");
		return (long) (base * level);
	}
	
	public static long ItemCost(long level, double power, long base) {
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
}