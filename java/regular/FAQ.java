package regular;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import processes.Prefix;

import java.awt.*;

public class FAQ {
	public FAQ(MessageReceivedEvent e) {
		char prefix = Prefix.GetPrefix(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot FAQ**__");
		try {
			switch(message[1]) {
				case "give" -> builder.addField(":money_with_wings: Why can't you give money to other users?", "Since robbing is dynamic, one can easily abuse an alt to rob a person and transfer those funds to the main account, or run commands on mutiple accounts and channel it into the main account.  Giving users money **will never be implemented**, however, user-sponsored giveaways may become a thing in the future.", false);
				case "luthier" -> builder.addField(":violin: How do I get Luthier in my server?", "Luthier is currently unobtainable by normal or stable means.  Currently, the only way to obtain this exclusive feature is by winning giveaways.", false);
				case "hourly" -> builder.addField(":timer: How do I increase hourly income?", "Run `" + prefix + "upgrades 1` to see a list of hourly income upgrades.", false);
				case "orchestra" -> builder.addField(":musical_score: How do I hire an orchestra?", "You need an hourly income of at least 7500:violin:/hour and 25 000 000:violin: to hire one.  Run `" + prefix + "orchestra` for more info.", false);
				case "rob" -> builder.addField(":cop: Why is there no way to disable robbing?", "Robbing, or the ability to hamper other users, is an integral part of the bot.  While there are ways to lower the effect of robbers, robbing **will never have an option to be disabled**.  User feedback is always accepted, and balancing happens regularly.", false);
				case "delete" -> builder.addField(":wastebasket: Why was my save randomly deleted?", "It is most likely a random IO error.  Contact `Stradivarius Violin#6156` for help.", false);
				case "robchance" -> builder.addField(":cop: What are the chances to succeed in a rob?", "Your chance is determined by your balance and the target's balance.  The exact chance of **failing** is your balance divided by the sum of your balance and the target's balance.  There is a cap of 5 000 000:violin: during a robbery that can be stolen, and there is no cap on how much you can be fined for failing.\n\ntl;dr - the more you have, the harder it is to succeed in robbing someone", false);
				case "leveling" -> builder.addField(":chart_with_upwards_trend: What happened to Leveling and server settings?", "Leveling was moved to a new bot that can be invited using `!invite` (use your server's prefix).  All other server settings were completely removed in an effort to save resources.", false);
				case "robping" -> builder.addField(":ping_pong: Why can't I rob/look up another user?", "People hate pings, so the ability to rob/look up users by ping was removed a long time ago.  You must use the User ID of the person you want to rob/look up.  If you don't know how to find the User ID, click this link: <https://support.discord.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID->", false);
				case "rngesus" -> builder.addField(":gift: What are the loot tables?", "<https://linglingdev.weebly.com/rngesus-corner.html>", false);
				case "luthierchance" -> builder.addField(":tools: What are the chances of Luthier spawning?", "0-10 Members: 10%\n11-100 Members: 10% -0.1% per member\n101-1000 Members: 1%\n1001-10000 Members: 1% -0.0001% per member\n10001+ Members: 0.1%", false);
				default -> builder.addField("You entered an invalid entry!  FAQ Entries", "Use `" + prefix + "faq [item]` to view a page in depth.\n\n`rob` `give` `luthier` `hourly` `orchestra` `delete` `robchance` `leveling` `robping` `rngesus` `luthierchance`", false);
			}
		} catch(Exception exception) {
			builder.addField("FAQ Entries", "Use `" + prefix + "faq [item]` to view a page in depth.\n\n`rob` `give` `luthier` `hourly` `orchestra` `delete` `robchance` `leveling` `robping` `rngesus` `luthierchance`", false);
		}
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
}
