package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Random;

public class Scales {
	public Scales(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		Random random = new Random();
		if(time < (long) data.get("scaleCD")) {
			long milliseconds = (long) data.get("scaleCD") - time;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.getMessage().reply("Chill, you can't play two scales at once!  Wait " + seconds + " seconds " + milliseconds + " milliseconds!").mentionRepliedUser(false).queue();
		} else {
			long base = Numbers.CalculateAmount(data, random.nextInt(11) + 10);
			data.replace("violins", (long) data.get("violins") + base);
			if((boolean) data.get("timeCrunch")) {
				data.replace("scaleCD", time + 64500);
			} else {
				data.replace("scaleCD", time + 89500);
			}
			e.getMessage().reply("You played your scales and earned " + Numbers.FormatNumber(base) + ":violin:").mentionRepliedUser(false).queue();
			data.replace("scalesPlayed", (long) data.get("scalesPlayed") + 1);
			data.replace("earnings", (long) data.get("earnings") + base);
			RNGesus.Lootbox(e, data);
			new SaveData(e, data);
		}
	}
}