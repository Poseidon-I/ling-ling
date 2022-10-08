package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Objects;

public class Deposit {
	public static void deposit(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long amount;
		long wallet = (long) data.get("violins");
		String temp = Objects.requireNonNull(e.getOption("amount")).getAsString();
		try {
			if(temp.equals("max")) {
				amount = wallet;
			} else {
				try {
					amount = Long.parseLong(temp);
				} catch(Exception exception) {
					e.reply("You have to either input `max` or an integer.").setEphemeral(true).queue();
					return;
				}
			}
			if(amount > wallet) {
				e.reply("You can't deposit more than you have in your balance.").setEphemeral(true).queue();
			} else if(amount < 1) {
				e.reply("Stop wasting my time trying to deposit a negative amount.").setEphemeral(true).queue();
			} else {
				long max = 20000000 * (long) data.get("storage");
				long balance = (long) data.get("bank");
				if(balance + amount > max) {
					amount = max - balance;
					balance = max;
					e.reply("**MAX VIOLINS**\nYou deposited " + Numbers.formatNumber(amount) + Emoji.VIOLINS + " into your bank.  You now have " + Numbers.formatNumber(balance) + Emoji.VIOLINS + " in your bank.").queue();
				} else {
					balance += amount;
					e.reply("You deposited " + Numbers.formatNumber(amount) + Emoji.VIOLINS + " into your bank.  You now have " + Numbers.formatNumber(balance) + Emoji.VIOLINS + " in your bank.").queue();
				}
				data.replace("violins", wallet - amount);
				data.replace("bank", balance);
				SaveData.saveData(e, data);
			}
		} catch(Exception exception) {
			e.reply("You have to deposit something.").setEphemeral(true).queue();
		}
	}
}