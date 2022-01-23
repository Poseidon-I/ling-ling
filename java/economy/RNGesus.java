package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;

import java.util.Random;

public class RNGesus {
	public static JSONObject Lootbox(MessageReceivedEvent e, JSONObject data) {
		Random random = new Random();
		double chance = random.nextDouble();
		if(chance > 0.1) {
			return data;
		} else if(chance > 0.005) {
			data.replace("kits", (long) data.get("kits") + 1);
			e.getMessage().reply("**CRAZY RARE DROP!**\nYou found a Musician Kit while you were out and about.").mentionRepliedUser(true).queue();
		} else if(chance > 0.0004) {
			data.replace("linglingBox", (long) data.get("linglingBox") + 1);
			e.getMessage().reply("**CRAZY RARE DROP!**\nYou found a Ling Ling Box sitting in your room!").mentionRepliedUser(true).queue();
		} else {
			data.replace("crazyBox", (long) data.get("crazyBox") + 1);
			e.getMessage().reply("**INSANE DROP!**\nYou see a CRAZY PERSON BOX appear in front of you!  GG!").mentionRepliedUser(true).queue();
		}
		return data;
	}
}