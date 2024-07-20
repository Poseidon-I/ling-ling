package regular;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import processes.DatabaseManager;
// BEETHOVEN-ONLY CLASS
public class Annoy {
	public static void annoy(GenericDiscordEvent e) {
		if(e.getChannel().getId().equals("1044740962867478609")) {
			JSONObject data = DatabaseManager.getMiscData();
			assert data != null;
			long time = (long) data.get("annoy");
			if(System.currentTimeMillis() < time && !e.getAuthor().getId().equals("619989388109152256")) {
				e.reply("The 5-minute global cooldown has not expired yet!  Wait before annoying a friend!");
			} else {
				String target = e.getMessage().getContentRaw().split(" ")[2];
				if(target.charAt(0) == '<' && target.charAt(1) == '@' && target.charAt(target.length() - 1) == '>') {
					if(target.contains("&")) {
						e.reply("Imagine trying to mass-ping a role SMFH");
						return;
					} else if(target.contains("846097308504293393") || target.contains("733409243222507670")) {
						e.reply("DO NOT ATTEMPT TO ANNOY ME OR LING LING.  YOU SHALT FACE PUNISHMENT.");
						target = "<@" + e.getAuthor().getId() + ">";
					}
					for(int i = 0; i < 19; i++) {
						e.sendMessage("annoy " + target);
					}
					DatabaseManager.saveMiscData(data);
				} else {
					e.reply("You dumbass you have to PING someone to annoy them.  Now you've wasted your once-per-hour chance.");
				}
			}
		} else {
			e.reply("Wrong channel, stupid.  Go to <#1044740962867478609>.");
		}
	}
}