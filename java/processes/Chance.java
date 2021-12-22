package processes;

public class Chance {
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
}