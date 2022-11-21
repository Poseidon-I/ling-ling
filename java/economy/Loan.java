package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Objects;

public class Loan {
	public static void loan(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long balance = (long) data.get("loan");
		if(balance > 0) {
			e.reply("You still have an outstanding balance of `" + Numbers.formatNumber(balance) + "`" + Emoji.VIOLINS + "!").queue();
		} else {
			long loan;
			String amount;
			try {
				amount = Objects.requireNonNull(e.getOption("amount")).getAsString();
			} catch(Exception exception) {
				e.reply("You have to either input `max` or an integer.").setEphemeral(true).queue();
				return;
			}
			long maxLoan = 200 * (long) data.get("income");
			if(amount.equals("max")) {
				loan = maxLoan;
			} else {
				try {
					loan = Long.parseLong(amount);
				} catch(Exception exception) {
					e.reply("You have to either input `max` or an integer.").setEphemeral(true).queue();
					return;
				}
				if(loan > maxLoan) {
					loan = maxLoan;
				}
			}
			data.replace("loan", loan);
			data.replace("violins", (long) data.get("violins") + loan);
			e.reply("You have borrowed `" + Numbers.formatNumber(loan) + "`" + Emoji.VIOLINS + " from the bank.  Most actions will result in a portion being used to pay back the loan.  You can also manually contribute by using `/payloan`.").queue();
			SaveData.saveData(e, data);
		}
	}
}