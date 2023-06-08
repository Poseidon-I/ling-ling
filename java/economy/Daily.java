package economy;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

public class Daily {
	public static void daily(GenericDiscordEvent e) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadDailyToday")) {
			e.reply("I can't give out violins that fast, wait until 00:00 UTC!");
		} else {
			long streak = (long) data.get("streak") + 1;
			long income = (long) data.get("income");
			if(!(boolean) data.get("retainDaily") && streak != 0) {
				e.reply("Oh no!  Your streak was reset!\nYou received a total of `" + Numbers.formatNumber(income) + "`" + Emoji.VIOLINS + ", with `0`" + Emoji.VIOLINS + " coming from your `0`-day streak!\n\nRemember to vote for Ling Ling at <https://top.gg/bot/733409243222507670/vote>!");
				streak = 0;
			}
			long base = income + (streak * (income / 100));
			Numbers.calculateLoan(data, base);
			data.replace("earnings", (long) data.get("earnings") + base);
			data.replace("hadDailyToday", true);
			if(streak != 0) {
				e.reply("You received a total of `" + Numbers.formatNumber(base) + "`" + Emoji.VIOLINS + ", with `" + Numbers.formatNumber(streak * (income / 100)) + "`" + Emoji.VIOLINS + " coming from your `" + streak + "`-day streak!\n\nRemember to vote for Ling Ling at <https://top.gg/bot/733409243222507670/vote>!");
			}
			if(streak > (long) data.get("maxStreak")) {
				data.replace("maxStreak", streak);
			}
			if(streak % 28 == 0 && streak != 0) {
				data.replace("medals", (long) data.get("medals") + 1);
				e.getChannel().sendMessage("You reached a streak of `" + streak + "`!  Enjoy your Ling Ling Medal!").queue();
			}
			data.replace("streak", streak);
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
		}
	}
}