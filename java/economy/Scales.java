package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

import java.util.Random;

public class Scales {
	public Scales(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		Random random = new Random();
		if(time < (long) data.get("scaleCD")) {
			long milliseconds = (long) data.get("scaleCD") - time;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.getChannel().sendMessage("Chill, you can't play two scales at once!  Wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			long base = Calculate.CalculateAmount(data, random.nextInt(11) + 10);
			data.replace("violins", (long) data.get("violins") + base);
			if((boolean) data.get("timeCrunch")) {
				data.replace("scaleCD", time + 64500);
			} else {
				data.replace("scaleCD", time + 89500);
			}
			e.getChannel().sendMessage("You played your scales and earned " + base + ":violin:").queue();
			data.replace("scalesPlayed", (long) data.get("scalesPlayed") + 1);
			data.replace("earnings", (long) data.get("earnings") + base);
			new SaveData(e, data);
		}
	}
}