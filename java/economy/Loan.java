package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;
import processes.Prefix;

public class Loan {
	public Loan(MessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long balance = (long) data.get("loan");
		if(balance > 0) {
			e.getMessage().reply("You still have an outstanding balance of " + Numbers.FormatNumber(balance) + ":violin:!").mentionRepliedUser(false).queue();
		} else {
			long loan;
			try {
				long maxLoan = 200 * (long) data.get("income");
				if (message[1].equals("max")) {
					loan = maxLoan;
				} else {
					try {
						loan = Long.parseLong(message[1]);
					} catch (Exception exception) {
						e.getMessage().reply("You have to either input `max` or an integer.").mentionRepliedUser(false).queue();
						return;
					}
					if (loan > maxLoan) {
						e.getMessage().reply("You can only borrow a maximum of " + Numbers.FormatNumber(maxLoan) + ":violin:.  Increase your hourly income to get a higher limit!").mentionRepliedUser(false).queue();
						return;
					}
				}
			} catch(Exception exception) {
				e.getMessage().reply("You have to either input `max` or an integer.").mentionRepliedUser(false).queue();
				return;
			}
			data.replace("loan", loan);
			data.replace("violins", (long) data.get("violins") + loan);
			e.getMessage().reply("You have borrowed " + Numbers.FormatNumber(loan) + ":violin: from the bank.  Most actions will result in a portion being used to pay back the loan.  You can also manually contribute by using `" + Prefix.GetPrefix(e) + "payloan`.").mentionRepliedUser(false).queue();
			new SaveData(e, data);
		}
	}
}