package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Objects;

public class PayLoan {
	public static void payLoan(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long violins = (long) data.get("violins");
		long owed = (long) data.get("loan");
		if(owed == 0) {
			e.reply("You don't owe anything!").queue();
		} else {
			long amount;
			String temp;
			try {
				temp = Objects.requireNonNull(e.getOption("amount")).getAsString();
			} catch(Exception exception) {
				e.reply("You have to either input `max` or an integer.").setEphemeral(true).queue();
				return;
			}
			if(temp.equals("max")) {
				amount = Math.min(violins, owed);
			} else {
				try {
					amount = Long.parseLong(temp);
				} catch(Exception exception) {
					e.reply("You have to put in a real number or `max` dummy").setEphemeral(true).queue();
					return;
				}
			}
			if(amount > violins) {
				e.reply("You can't pay more than what you have, don't think I'm stupid, I have a quadragintuple PhD in Mathematics").queue();
			} else {
				amount = Math.min(amount, owed);
				data.replace("loan", owed - amount);
				data.replace("violins", violins - amount);
				e.reply("You paid back `" + Numbers.formatNumber(amount) + "`" + Emoji.VIOLINS + ".  You now owe `" + Numbers.formatNumber(owed - amount) + "`" + Emoji.VIOLINS + ".").queue();
				SaveData.saveData(e, data);
			}
		}
	}
}