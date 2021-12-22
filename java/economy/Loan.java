package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

public class Loan {
	public Loan(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long loan;
		long maxLoan = 100 * (long) data.get("income");
		if(message[1].equals("max")) {
			loan = maxLoan;
		} else {
			try {
				loan = Long.parseLong(message[1]);
			} catch(Exception exception) {
				e.getChannel().sendMessage("You have to either input `max` or an integer.").queue();
				throw new IllegalArgumentException();
			}
			if(loan > maxLoan) {
				e.getChannel().sendMessage("You can only borrow a maximum of " + maxLoan + ":violin:.  Increase your hourly income to get a higher limit!").queue();
				throw new IllegalArgumentException();
			}
			
		}
		long balance = (long) data.get("loan");
		if(balance > 0) {
			e.getChannel().sendMessage("You still have an outstanding balance of " + balance + ":violin:!").queue();
		} else {
			data.replace("loan", loan);
			data.replace("violins", (long) data.get("violins") + loan);
			e.getChannel().sendMessage("You have borrowed " + loan + ":violin: from the bank.  Remember to pay back the violins otherwise penalties will bite your back!").queue();
			new SaveData(e, data);
		}
	}
}