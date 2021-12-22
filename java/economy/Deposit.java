package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

public class Deposit {
	public Deposit(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long amount;
		long wallet = (long) data.get("violins");
		if(message[1].equals("max")) {
			amount = wallet;
		} else {
			try {
				amount = Long.parseLong(message[1]);
			} catch(Exception exception) {
				e.getChannel().sendMessage("You have to either input `max` or an integer.").queue();
				throw new IllegalArgumentException();
			}
		}
		if(amount > wallet) {
			e.getChannel().sendMessage("You can't deposit more than you have in your wallet, you fool.").queue();
		} else if(amount < 1) {
			e.getChannel().sendMessage("Stop wasting my time trying to deposit a negative amount, shame on you").queue();
		} else {
				long max = 15000000 * (long) data.get("storage");
				long balance = (long) data.get("bank");
				if(balance + amount > max) {
					amount = max - balance;
					balance = max;
					e.getChannel().sendMessage("You deposited " + amount + ":violin: into your bank.  You now have " + balance + ":violin: in your bank (MAX VIOLINS).").queue();
				} else {
					balance += amount;
					e.getChannel().sendMessage("You deposited " + amount + ":violin: into your bank.  You now have " + balance + ":violin: in your bank.").queue();
				}
				data.replace("violins", wallet - amount);
				data.replace("bank", balance);
				new SaveData(e, data);
		}
	}
}