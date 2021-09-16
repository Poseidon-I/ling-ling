package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Deposit {
	public Deposit(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] bankdata = LoadData.loadData(e,"Bank Data");
		String[] message = e.getMessage().getContentRaw().split(" ");
		long amount;
		long wallet = Long.parseLong(data[0]);
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
		} else {
			long max = 15000000 * Long.parseLong(bankdata[1]);
			long balance = Long.parseLong(bankdata[0]);
			if(balance + amount > max) {
				amount = max - balance;
				balance = max;
				e.getChannel().sendMessage("You deposited " + amount + ":violin: into your bank.  You now have " + balance + ":violin: in your bank (MAX VIOLINS).").queue();
			} else {
				balance += amount;
				e.getChannel().sendMessage("You deposited " + amount + ":violin: into your bank.  You now have " + balance + ":violin: in your bank.").queue();
			}
			data[0] = String.valueOf(wallet - amount);
			bankdata[0] = String.valueOf(balance);
			new SaveData(e, data, "Economy Data");
			new SaveData(e, bankdata, "Bank Data");
		}
	}
}