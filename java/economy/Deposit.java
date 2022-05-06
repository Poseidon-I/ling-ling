package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

public class Deposit {
	public Deposit(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long amount;
		long wallet = (long) data.get("violins");
		try {
			if(message[1].equals("max")) {
				amount = wallet;
			} else {
				try {
					amount = Long.parseLong(message[1]);
				} catch(Exception exception) {
					e.getMessage().reply("You have to either input `max` or an integer.").mentionRepliedUser(false).queue();
					return;
				}
			}
			if(amount > wallet) {
				e.getMessage().reply("You can't deposit more than you have in your wallet, you fool.").mentionRepliedUser(false).queue();
			} else if(amount < 1) {
				e.getMessage().reply("Stop wasting my time trying to deposit a negative amount, shame on you").mentionRepliedUser(false).queue();
			} else {
				long max = 20000000 * (long) data.get("storage");
				long balance = (long) data.get("bank");
				if(balance + amount > max) {
					amount = max - balance;
					balance = max;
					e.getMessage().reply("**MAX VIOLINS**\nYou deposited " + Numbers.FormatNumber(amount) + ":violin: into your bank.  You now have " + Numbers.FormatNumber(balance) + ":violin: in your bank.").mentionRepliedUser(false).queue();
				} else {
					balance += amount;
					e.getMessage().reply("You deposited " + Numbers.FormatNumber(amount) + ":violin: into your bank.  You now have " + Numbers.FormatNumber(balance) + ":violin: in your bank.").mentionRepliedUser(false).queue();
				}
				data.replace("violins", wallet - amount);
				data.replace("bank", balance);
				new SaveData(e, data);
			}
		} catch(Exception exception) {
			e.getMessage().reply("You have to deposit something.").mentionRepliedUser(false).queue();
		}
	}
}