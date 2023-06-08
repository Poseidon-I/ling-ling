package regular;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class FAQ {
	public static void faq(GenericDiscordEvent e, String page) {
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot FAQ**__");
		try {
			switch(page) {
				case "rob" -> builder.addField(":cop: Why is there no way to disable robbing?", "Robbing, or the ability to hamper other users, is an integral part of the bot.  While there are ways to lower the effect of robbers, robbing **will never have an option to be disabled**.  User feedback is always accepted, and balancing happens regularly.", false);
				case "give" -> builder.addField(":money_with_wings: Why can't you give money to other users?", "Since robbing is dynamic, one can easily abuse an alt to rob a person and transfer those funds to the main account, or run commands on mutiple accounts and channel it into the main account.  Giving users money **will never be implemented**, however, user-sponsored giveaways are a thing, and you can always sell items on the Market.", false);
				case "luthier" -> builder.addField(":violin: How do I get Luthier in my server?", "Luthiers can be crafted.  See `/craft` for more information.", false);
				case "hourly" -> builder.addField(":timer: How do I increase hourly income?", "Run `/upgrades 1` to see a list of hourly income upgrades.", false);
				case "orchestra" -> builder.addField(":musical_score: How do I hire an orchestra?", "You need an hourly income of at least 7 500:violin:/hour and 25 000 000:violin: to hire one.", false);
				case "delete" -> builder.addField(":wastebasket: Why was my save randomly deleted?", "You were either save-reset/banned, or a random IO error happened.  Check DMs with the bot to see if you were save-reset or banned, and contact `Stradivarius Violin#6156` if you weren't.\n*Dev note: I THINK I fixed the IO bug when migrating to JSON, but it's here bc funny*", false);
				case "robchance" -> builder.addField(":cop: What are the chances to succeed in a rob?", "Your chance is determined by your balance and the target's balance.  The exact chance of **failing** is your balance divided by the sum of your balance and the target's balance.  There is a cap of 5 000 000:violin: during a robbery that can be stolen, and there is no cap on how much you can be fined for failing.\n\ntl;dr - the more you have, the harder it is to succeed in robbing someone", false);
				case "leveling" -> builder.addField(":chart_with_upwards_trend: What happened to Leveling and server settings?", "Leveling was moved to a new bot that can be invited using `!invite` (use your server's prefix).  All other server settings were completely removed in an effort to save resources.", false);
				case "ping" -> builder.addField(":ping_pong: Why can't I rob/look up another user?", "People hate pings (legacy reason), so the ability to rob/look up users by ping was removed a long time ago.  You must use the User ID of the person you want to rob/look up.  If you don't know how to find the User ID, click this link: <https://support.discord.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID->", false);
				case "rngesus" -> builder.addField(":gift: What are the loot tables?", "**INCOMPLETE**\n<https://docs.google.com/spreadsheets/d/118BxHRJbCEd7aTeMgoxy7D2Nya5RaWdRLLZj0_WTvOI/edit?usp=sharing>", false);
				case "luthierchance" -> builder.addField(":tools: What are the chances of Luthier spawning?", "0-20 Members: 10%\n21-100 Members: 10% - 0.1% per member\n101-1000 Members: 2%\n1001-10000 Members: 2% - 0.00002% per member\n10001+ Members: 0.2%", false);
				case "magicfind" -> builder.addField(":star: How does Magic Find work?", "Magic Find increases the chance of getting lootboxes by 1% per stat.\n\nExample: With 10 Magic Find, the chance to drop a Musician Kit increases from 0.4% (1 in 250) to 0.4 * 1.1 = 0.44% (1 in ~227)", false);
				case "commandmulti" -> builder.addField(":heavy_multiplication_x: How does Command Income work?", "Efficient Practising is the only upgrade that compounds with itself.  All other income upgrades add together to produce a final second multiplier after Efficient Practising.", false);
				case "market" -> builder.addField(":convenience_store: How does the Market work?", "The Market is an anonymous trading hub for all raw materials.\n\n**Sell Offers**\nPeople looking to sell items will put up offers for a specific price (if no price is provided, the default is 1 lower than the lowest price).  They must then wait for buyers.\n\n**Buying Items**\nBuying items will instantly purchase them from the cheapest Sell Offers.\n**BE WARNED** - If you buy too many items, you might accidentally spend more than you intended!  Always check the price of items.", false);
				default -> builder.addField("You entered an invalid entry!  FAQ Entries", "Use `/faq [item]` to view a page in depth.\n\n`rob` `give` `luthier` `hourly` `orchestra` `delete` `robchance` `leveling` `ping` `rngesus` `luthierchance`", false);
			}
		} catch(Exception exception) {
			builder.addField("FAQ Entries", "Use `/faq [item]` to view a page in depth.\n\n`rob` `give` `luthier` `hourly` `orchestra` `delete` `robchance` `leveling` `ping` `rngesus` `luthierchance` `magicfind` `commandmulti` `market`", false);
		}
		e.replyEmbeds(builder.build());
	}
}