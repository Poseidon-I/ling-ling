package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PayLoan {
	public PayLoan(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] bankdata = LoadData.loadData(e, "Bank Data");
		String[] message = e.getMessage().getContentRaw().split(" ");
		long violins = Long.parseLong(data[0]);
		long owed = Long.parseLong(bankdata[3]);
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
				e.getChannel().sendMessage("You can't pay more than what you have, don't think I'm stupid, I have a octuple degree in Maths").queue();
			} else {
				amount = Math.min(amount, owed);
				bankdata[3] = String.valueOf(owed - amount);
				data[0] = String.valueOf(violins - amount);
				e.getChannel().sendMessage("You paid back " + amount + ":violin:.  You now owe " + bankdata[3] + ":violin:.").queue();
				new SaveData(e, data, "Economy Data");
				new SaveData(e, bankdata, "Bank Data");
			}
		}
	}
}