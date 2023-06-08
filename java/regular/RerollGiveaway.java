package regular;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Message;
import processes.CheckGiveaways;
// BEETHOVEN-ONLY CLASS
public class RerollGiveaway {
		public static void rerollGiveaway(GenericDiscordEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(e.getChannel().getId().equals("734697492490354768")) {
			Message message1;
			try {
				message1 = e.getChannel().retrieveMessageById(message[2]).complete();
			} catch(Exception exception) {
				e.reply("Invalid message ID!");
				return;
			}
			int winners;
			try {
				winners = Integer.parseInt(message[3]);
			} catch(Exception exception) {
				e.reply("You must enter an Integer number of winners!");
				return;
			}
			StringBuilder thing = new StringBuilder();
			String[] content = message1.getContentRaw().split(" ");
			int i = 3;
			while(!content[i].equals("has") && i < content.length - 1) {
				thing.append(content[i]).append(" ");
				i++;
			}
			CheckGiveaways.completeGiveaway(message1, winners, thing.toString());
		} else {
			e.reply("This command can only be run in <#734697492490354768>!");
		}
	}
}
