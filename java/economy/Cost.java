package economy;

public class Cost {
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