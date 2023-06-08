package regular;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.User;
// BEETHOVEN-ONLY CLASS
public class CheckDM {
	public static void checkDM(GenericDiscordEvent e) {
		e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
		String id = e.getMessage().getContentRaw().split(" ")[2];
		if(id.charAt(0) == '<' && id.charAt(1) == '@' && id.charAt(id.length() - 1) == '>') {
			e.sendMessage("**OI " + id + ", " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**");
		} else {
			e.reply("You dumbass you have to PING someone to yell at them.");
			return;
		}
		try {
			User send = e.getJDA().getUserById(id.substring(2, id.length() - 1));
			assert send != null;
			send.openPrivateChannel().complete().sendMessage("**OI " + id + ", " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
		} catch(Exception exception) {
			e.reply("Either the recipient was being a n00b and didn't have their DMs open or you are dumb and didn't mention a user / mentioned a bot.");
		}
	}
}
