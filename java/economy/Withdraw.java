package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Withdraw {
	public Withdraw(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] bankdata = LoadData.loadData(e,"Bank Data");
		String[] message = e.getMessage().getContentRaw().split(" ");
		long amount;
		long balance = Long.parseLong(bankdata[0]);
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
		} else {
			long violins = Long.parseLong(data[0]);
			balance -= amount;
			data[0] = String.valueOf(violins + amount);
			bankdata[0] = String.valueOf(balance);
			e.getChannel().sendMessage("You withdrew " + amount + ":violin: from your bank.  You now have " + balance + ":violin: in your bank.").queue();
			new SaveData(e, data, "Economy Data");
			new SaveData(e, bankdata, "Bank Data");
		}
	}
}