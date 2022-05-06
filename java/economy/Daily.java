package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

public class Daily {
	public Daily(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadDailyToday")) {
			e.getMessage().reply("I can't give out violins that fast, wait until 00:00 UTC!").mentionRepliedUser(false).queue();
		} else {
			long streak = (long) data.get("streak") + 1;
			if(!(boolean) data.get("retainDaily") && streak != 0) {
				e.getMessage().reply("Oh no!  Your streak was reset!").mentionRepliedUser(false).queue();
				streak = 0;
			}
			long base = 100000 + (streak * 1000);
			Numbers.CalculateLoan(data, base);
			data.replace("earnings", (long) data.get("earnings") + base);
			data.replace("hadDailyToday", true);
			e.getMessage().reply("You received a total of " + Numbers.FormatNumber(base) + ":violin:, with " + Numbers.FormatNumber(streak * 1000) + ":violin: coming from your " + streak + "-day streak!").mentionRepliedUser(false).queue();
			if(streak > (long) data.get("maxStreak")) {
				data.replace("maxStreak", streak);
			}
			if(streak % 28 == 0 && streak != 0) {
				data.replace("medals", (long) data.get("medals") + 1);
				e.getMessage().reply("You reached a streak of " + streak + "!  Enjoy your Ling Ling Medal!").mentionRepliedUser(false).queue();
			}
			data.replace("streak", streak);
			RNGesus.Lootbox(e, data);
			new SaveData(e, data);
		}
	}
}