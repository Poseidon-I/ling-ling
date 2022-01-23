package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

public class PayLoan {
	public PayLoan(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long violins = (long) data.get("violins");
		long owed = (long) data.get("loan");
		long amount;
		if(owed == 0) {
			e.getMessage().reply("You don't owe anything!").mentionRepliedUser(false).queue();
		} else {
			if(message[1].equals("max")) {
				amount = Math.min(violins, owed);
			} else {
				try {
					amount = Long.parseLong(message[1]);
				} catch(Exception exception) {
					e.getMessage().reply("You have to put in a real number or `max` dummy").mentionRepliedUser(false).queue();
					return;
				}
			}
			if(amount > violins) {
				e.getMessage().reply("You can't pay more than what you have, don't think I'm stupid, I have a quadragintuple PhD in Mathematics").mentionRepliedUser(false).queue();
			} else {
				amount = Math.min(amount, owed);
				data.replace("loan", owed - amount);
				data.replace("violins", violins - amount);
				e.getMessage().reply("You paid back " + Numbers.FormatNumber(amount) + ":violin:.  You now owe " + Numbers.FormatNumber(owed - amount) + ":violin:.").mentionRepliedUser(false).queue();
				new SaveData(e, data);
			}
		}
	}
}