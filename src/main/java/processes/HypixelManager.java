package processes;

import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import org.json.simple.JSONObject;

import java.util.UUID;

public class HypixelManager {
	private static HypixelHttpClient client;

	public static void connectToHypixel(boolean beta) {
		JSONObject data = DatabaseManager.getMiscData();
		try {
			assert data != null;
			if(beta) {
				client = new ApacheHttpClient(UUID.fromString((String) data.get("hypixelDevKey")));
			} else {
				client = new ApacheHttpClient(UUID.fromString((String) data.get("hypixelKey")));
			}
		} catch(Exception exception) {
			System.out.println("Unable to connect to Hypixel API.");
			System.exit(-1);
		}
	}

	public static HypixelHttpClient getClient() {
		return client;
	}
}