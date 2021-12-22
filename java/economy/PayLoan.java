package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

public class PayLoan {
	public PayLoan(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long violins = (long) data.get("violins");
		long owed = (long) data.get("loan");
		long amount;
		if(owed == 0) {
			e.getChannel().sendMessage("You don't owe anything!").queue();
		} else {
			if(message[1].equals("max")) {
				amount = Math.min(violins, owed);
			} else {
				try {
					amount = Long.parseLong(message[1]);
				} catch(Exception exception) {
					e.getChannel().sendMessage("You have to put in a real number or `max` dummy").queue();
					throw new IllegalArgumentException();
				}
			}
			if(amount > violins) {
				e.getChannel().sendMessage("You can't pay more than what you have, don't think I'm stupid, I have a quadragintuple PhD in Mathematics").queue();
			} else {
				amount = Math.min(amount, owed);
				data.replace("loan", owed - amount);
				data.replace("violins", violins - amount);
				e.getChannel().sendMessage("You paid back " + amount + ":violin:.  You now owe " + (owed - amount) + ":violin:.").queue();
				new SaveData(e, data);
			}
		}
	}
}