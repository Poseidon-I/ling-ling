package economy;

import java.util.Comparator;

public class MarketComparator implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		long price1 = Integer.parseInt(o1.split(" ")[0]);
		long price2 = Integer.parseInt(o2.split(" ")[0]);
		return Long.compare(price1, price2);
	}
}