package economy;

import org.json.simple.JSONObject;

public class Calculate {
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
}