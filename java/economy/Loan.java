package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Loan {
	public Loan(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] bankdata = LoadData.loadData(e, "Bank Data");
		long loan = 500 * Long.parseLong(data[12]);
		long balance = Long.parseLong(bankdata[3]);
		if(balance > 0) {
			e.getChannel().sendMessage("You still have an outstanding balance of " + balance + ":violin:!").queue();
		} else {
			bankdata[3] = String.valueOf(loan);
			data[0] = String.valueOf(Long.parseLong(data[0]) + loan);
			e.getChannel().sendMessage("You have borrowed " + loan + ":violin: from the bank.  Remember to pay back the violins otherwise interest will bite your back!").queue();
			new SaveData(e, data, "Economy Data");
			new SaveData(e, bankdata, "Bank Data");
		}
	}
}