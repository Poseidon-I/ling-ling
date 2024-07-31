package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import processes.DatabaseManager;

public class SaveData {
	public static void saveData(GenericDiscordEvent e, JSONObject data) {
		Achievement.calculateAchievement(e, data, "earnings", "Moneymaker");
		Achievement.calculateAchievement(e, data, "moneyEarned", "Entrepeneur");
		DatabaseManager.saveDataByUser(e, "Economy Data", data);
	}
}