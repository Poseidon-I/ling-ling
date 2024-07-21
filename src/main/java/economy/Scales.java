package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import processes.DatabaseManager;
import processes.Numbers;

import java.util.Random;

import static economy.Cooldowns.reformat;
import static economy.Cooldowns.reformatMilliseconds;

public class Scales {


	public static void scales(GenericDiscordEvent e) {
		JSONObject data = DatabaseManager.getEconomyData(e);
		if(data == null) {
			return;
		}
		long time = System.currentTimeMillis();
		Random random = new Random();
		if(time < (long) data.get("scaleCD")) {
			long milliseconds = (long) data.get("scaleCD") - time;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.reply("Chill, you can't play two scales at once!  Wait " + seconds + " seconds " + milliseconds + " milliseconds!");
		} else {
			long base = Numbers.calculateAmount(data, random.nextInt(11) + 15);
			Numbers.calculateLoan(data, base);
			if((boolean) data.get("timeCrunch")) {
				data.replace("scaleCD", time + 59500);
			} else {
				data.replace("scaleCD", time + 89500);
			}
			String message = "You played your scales and earned `" + Numbers.formatNumber(base) + "`" + Emoji.VIOLINS + "\n";
			data.replace("scalesPlayed", (long) data.get("scalesPlayed") + 1);
			data.replace("earnings", (long) data.get("earnings") + base);

			long streakExpires = (long) data.get("scaleStreakExpire");
			if(streakExpires == 0) {
				message += "\nYou have begun a Scale Streak!  It will expire in `24:01:00.000`";
				data.replace("scaleStreak", 1);
				data.replace("scaleStreakExpire", time + 86460000);
			} else if(time > streakExpires) {
				message += "\nYour Scale Streak has expired!  You reached a streak of `" + Numbers.formatNumber(data.get("scaleStreak")) + "`.  The timer has reset and your streak now expires in `24:01:00.000`";
				data.replace("scaleStreak", 1);
				data.replace("scaleStreakExpire", time + 86460000);
			} else {
				long milliseconds = streakExpires - time;
				long hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				long minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				long seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				long streak = (long) data.get("scaleStreak") + 1;
				data.replace("scaleStreak", streak);
				if(streak > (long) data.get("scaleStreakRecord")) {
					data.replace("scaleStreakRecord", streak);
				} else if (streak == (long) data.get("scaleStreakRecord")) {
					message += "\n**PERSONAL BEST**";
				}
				message += "\nYour Scale Streak is now `" + Numbers.formatNumber(data.get("scaleStreak")) + "`.  It will expire in `" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`";
			}
			e.reply(message);
			RNGesus.lootbox(e, data);
			Achievement.calculateAchievement(e, data, "scalesPlayed", "Technician");
			Achievement.calculateAchievement(e, data, "scaleStreakRecord", "No-Life");
			SaveData.saveData(e, data);
		}
	}
}