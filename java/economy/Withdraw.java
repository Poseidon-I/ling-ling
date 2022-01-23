package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

public class Withdraw {
	public Withdraw(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long amount;
		long balance = (long) data.get("bank");
		if(message[1].equals("all")) {
			amount = balance;
		} else {
			try {
				amount = Long.parseLong(message[1]);
			} catch(Exception exception) {
				e.getMessage().reply("You have to either input `all` or an integer.").mentionRepliedUser(false).queue();
				return;
			}
		}
		if(amount > balance) {
			e.getMessage().reply("You can't withdraw more than you have in your bank account, you fool.").mentionRepliedUser(false).queue();
		} else if(amount < 1) {
			e.getMessage().reply("Stop wasting my time trying to withdraw a negative amount, shame on you").mentionRepliedUser(false).queue();
		} else {
			balance -= amount;
			data.replace("violins", (long) data.get("violins") + amount);
			data.replace("bank", balance);
			e.getMessage().reply("You withdrew " + Numbers.FormatNumber(amount) + ":violin: from your bank.  You now have " + Numbers.FormatNumber(balance) + ":violin: in your bank.").mentionRepliedUser(false).queue();
			new SaveData(e, data);
		}
	}
}