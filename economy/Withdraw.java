package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Objects;

public class Withdraw {
	public static void withdraw(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long amount;
		long balance = (long) data.get("bank");
		String temp;
		try {
			temp = Objects.requireNonNull(e.getOption("amount")).getAsString();
		} catch(Exception exception) {
			e.reply("You have to withdraw something.").setEphemeral(true).queue();
			return;
		}
		if(temp.equals("max")) {
			amount = balance;
		} else {
			try {
				amount = Long.parseLong(temp);
			} catch(Exception exception) {
				e.reply("You have to either input `max` or an integer.").setEphemeral(true).queue();
				return;
			}
		}
		if(amount > balance) {
			e.reply("You can't withdraw more than you have in your bank account, you fool.").setEphemeral(true).queue();
		} else if(amount < 1) {
			e.reply("Stop wasting my time trying to withdraw a negative amount, shame on you").setEphemeral(true).queue();
		} else {
			balance -= amount;
			data.replace("violins", (long) data.get("violins") + amount);
			data.replace("bank", balance);
			e.reply("You withdrew " + Numbers.formatNumber(amount) + Emoji.VIOLINS + " from your bank.  You now have " + Numbers.formatNumber(balance) + Emoji.VIOLINS + " in your bank.").queue();
			SaveData.saveData(e, data);
		}
	}
}