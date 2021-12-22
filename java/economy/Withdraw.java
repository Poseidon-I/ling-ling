package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

public class Withdraw {
	public Withdraw(GuildMessageReceivedEvent e) {
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
				e.getChannel().sendMessage("You have to either input `all` or an integer.").queue();
				throw new IllegalArgumentException();
			}
		}
		if(amount > balance) {
			e.getChannel().sendMessage("You can't withdraw more than you have in your bank account, you fool.").queue();
		} else if(amount < 1) {
			e.getChannel().sendMessage("Stop wasting my time trying to withdraw a negative amount, shame on you").queue();
		} else {
			balance -= amount;
			data.replace("violins", (long) data.get("violins") + amount);
			data.replace("bank", balance);
			e.getChannel().sendMessage("You withdrew " + amount + ":violin: from your bank.  You now have " + balance + ":violin: in your bank.").queue();
			new SaveData(e, data);
		}
	}
}