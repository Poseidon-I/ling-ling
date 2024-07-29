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
				client = new ApacheHttpClient(UUID.fromString("bd49ecf6-3d03-407a-9694-a608910a968a"));
			} else {
				client = new ApacheHttpClient(UUID.fromString((String) data.get("hypixelKey")));
			}
		} catch(Exception exception) {
			System.out.println("Unable to connect to Hypixel API.");
			exception.printStackTrace();
			System.exit(-1);
		}
	}

	public static HypixelHttpClient getClient() {
		return client;
	}
}