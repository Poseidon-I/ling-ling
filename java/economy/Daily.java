package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

public class Daily {
	public Daily(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadDailyToday")) {
			e.getChannel().sendMessage("I can't give out violins that fast, wait until 00:00 UTC!").queue();
		} else {
			long streak = (long) data.get("streak");
			if(!(boolean) data.get("retainDaily") && streak != 0) {
				e.getChannel().sendMessage("Oh no!  Your streak was reset!").queue();
				streak = 0;
			}
			long base = 100000 + (streak * 1000);
			data.replace("violins", (long) data.get("violins") + base);
			data.replace("earnings", (long) data.get("earnings") + base);
			data.replace("hadDailyToday", true);
			e.getChannel().sendMessage("You received a total of " + base + ":violin:, with " + streak * 1000 + ":violin: coming from your " + streak + "-day streak!").queue();
			if(streak > (long) data.get("maxStreak")) {
				data.replace("maxStreak", streak);
			}
			if(streak % 28 == 0 && streak != 0) {
				data.replace("medals", (long) data.get("medals") + 1);
				e.getChannel().sendMessage("You reached a streak of " + streak + "!  Enjoy your Ling Ling Medal!").queue();
			}
			data.replace("streak", (long) data.get("streak") + 1);
			new SaveData(e, data);
		}
	}
}