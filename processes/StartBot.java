package processes;

import eventListeners.Buttons;
import eventListeners.Disconnect;
import eventListeners.NewReceiver;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StartBot {
	public static void startBot() {
		File file = new File("Ling Ling Bot Data\\loadedservers.txt");
		try {
			file.delete();
			file.createNewFile();
		} catch(Exception exception1) {
			//nothing here lol
		}
		JDA jda;
		try(BufferedReader rdr = new BufferedReader(new FileReader("Ling Ling Bot Data\\token.txt"))) {
			jda = JDABuilder.createDefault(rdr.readLine(), GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_EMOJIS_AND_STICKERS)
					.disableCache(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS)
					.setMemberCachePolicy(MemberCachePolicy.ALL)
					.addEventListeners(new Disconnect())
					.addEventListeners(new NewReceiver())
					.addEventListeners(new Buttons())
					.useSharding(0, 1)
					.build();
			jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
			jda.getPresence().setActivity(Activity.playing("violin forty hours a day."));
			/*OptionData data;
			// ONE TIEM UPSERT OF SLASH COMMANDS
			
			// help page 1
			jda.upsertCommand(
					Commands.slash("joke", "Gives you a fun music-related joke!")
			).queue();
			jda.upsertCommand(
					Commands.slash("kill", "Totally kills your friends.")
							.addOption(OptionType.STRING, "target", "Who do you want to kill?")
			).queue();
			jda.upsertCommand(
					Commands.slash("emojify", "Turns your message into Emojis.")
							.addOption(OptionType.STRING, "message", "Put your message here.")
			).queue();
			
			// help page 2
			jda.upsertCommand(
					Commands.slash("rules", "Rules of the bot.  VERY IMPORTANT")
			).queue();
			jda.upsertCommand(
					Commands.slash("staff", "Lists the staff of the bot.")
			).queue();
			jda.upsertCommand(
					Commands.slash("poll", "Creates a poll where reactions are used to vote.")
							.addOption(OptionType.STRING, "title", "Name your poll!")
							.addOption(OptionType.STRING, "choices", "List your choices, separated by semicolons.")
			).queue();
			jda.upsertCommand(
					Commands.slash("invite", "Gives you the invite link for the bot.")
			).queue();
			
			data = new OptionData(OptionType.STRING, "page", "Select which question you want answered.")
					.addChoice("No Rob Disabling", "rob")
					.addChoice("No Gifting Violins", "give")
					.addChoice("Getting Luthier Multipliers", "luthier")
					.addChoice("Increase Hourly Income", "hourly")
					.addChoice("Hire an Orchestra", "orchestra")
					.addChoice("hElP mY sAvE wAs dElEtEd", "delete")
					.addChoice("Rob Succeed Chance", "robchance")
					.addChoice("Where Leveling ??!?!?1!?1??!?!1/", "leveling")
					.addChoice("WHY CAN'T I PING AS;DKFJ S;ALFJS;AKL", "ping")
					.addChoice("pls rngesus bless me", "rngesus")
					.addChoice("Luthier Spawn Chance", "luthierchance")
					.addChoice("Magic Find", "magicfind")
					.addChoice("Command Income Multiplier", "commandmulti")
					.addChoice("Market", "market");
			jda.upsertCommand(
					Commands.slash("faq", "The FAQ pages for this bot.")
							.addOptions(data)
			).queue();
			jda.upsertCommand(
					Commands.slash("support", "Gives you the support server for the bot.")
			).queue();
			jda.upsertCommand(
					Commands.slash("website", "Gives you the link to the official website for the bot.")
			).queue();
			jda.upsertCommand(
					Commands.slash("botstats", "Gives stats for the bot.")
			).queue();
			data = new OptionData(OptionType.STRING, "option", "Select which option you want edited.")
					.addChoice("Color", "color")
					.addChoice("DMs", "dms")
					.addChoice("Extra Info", "info");
			jda.upsertCommand(
					Commands.slash("settings", "Edit your personal settings!")
							.addOptions(data)
							.addOption(OptionType.STRING, "newvalue", "Choose the new value you want for the settings.")
			).queue();
			jda.upsertCommand(
					Commands.slash("help", "Main Help page for the bot.")
							.addOption(OptionType.STRING, "page", "Select which help page you want to view.")
			).queue();
			jda.upsertCommand(
					Commands.slash("guide", "Beginnner's Guide for the bot.")
			).queue();
			
			// help page 3
			jda.upsertCommand(
					Commands.slash("start", "Begin your journey!")
			).queue();
			jda.upsertCommand(
					Commands.slash("b", "View your balance.")
							.addOption(OptionType.STRING, "otheruser", "Use this to view another user's profile.")
			).queue();
			jda.upsertCommand(
					Commands.slash("stats", "View your stats.")
							.addOption(OptionType.STRING, "otheruser", "Use this to view another user's stats.")
			).queue();
			data = new OptionData(OptionType.INTEGER, "page", "Select the page to view.")
					.addChoice("1", 1)
					.addChoice("2", 2)
					.addChoice("3", 3);
			jda.upsertCommand(
					Commands.slash("inv", "View your inventory.")
							.addOptions(data)
							.addOption(OptionType.STRING, "otheruser", "Use this to view another user's inventory.")
			).queue();
			data = new OptionData(OptionType.STRING, "action", "Choose whether to Buy, Sell, or view an item or your offers.")
					.addChoice("Buy Items", "buy")
					.addChoice("Sell Items", "sell")
					.addChoice("View Item Prices", "view")
					.addChoice("View Your Offers", "offers");
			OptionData data2 = new OptionData(OptionType.STRING, "item", "Pick the item you want to Buy, Sell, or View.")
					.addChoice("Grains", "grains")
					.addChoice("Plastic", "plastic")
					.addChoice("Water", "water")
					.addChoice("Tea Base", "teaBase")
					.addChoice("Wood", "wood")
					.addChoice("Pine Sap", "pineSap")
					.addChoice("Steel", "steel")
					.addChoice("Horse Hair", "horseHair");
			jda.upsertCommand(
					Commands.slash("market", "View and interact with the Public Market!")
							.addOptions(data, data2)
							.addOption(OptionType.INTEGER, "amount", "Pick the amount you want to Buy or Sell.")
							.addOption(OptionType.INTEGER, "price", "Pick the price you want to Sell your items at.")
			).queue();
			data = new OptionData(OptionType.STRING, "item", "Choose the item you want to craft.")
					.addChoice("Rice", "rice")
					.addChoice("Bubble Tea", "tea")
					.addChoice("Rosin", "rosin")
					.addChoice("New Strings", "strings")
					.addChoice("Bow Hair", "bowHair")
					.addChoice("Violin Service", "service")
					.addChoice("1x Luthier", "luthier");
			jda.upsertCommand(
					Commands.slash("craft", "Craft items!")
							.addOptions(data)
							.addOption(OptionType.STRING, "amount", "How much do you want to craft?")
			).queue();
			jda.upsertCommand(
					Commands.slash("link", "Link your Minecraft acccount!  SEE /rules FOR POLICIES REGARDING MINECRAFT LINKING.")
							.addOption(OptionType.STRING, "ign", "Put in your Minecraft IGN!")
			).queue();
			
			jda.upsertCommand(
					Commands.slash("c", "View your cooldowns.")
			).queue();
			data = new OptionData(OptionType.INTEGER, "page", "Select the page to view.")
					.addChoice("Income Upgrades", 1)
					.addChoice("Woodwinds (Orchestra)", 2)
					.addChoice("Brass (Orchestra)", 3)
					.addChoice("Strings (Orchestra)", 4)
					.addChoice("Choir (Orchestra)", 5)
					.addChoice("Misc Orchestra Upgrades", 6)
					.addChoice("Teaching Upgrades (Certificate)", 7)
					.addChoice("Other Misc Upgrades", 8)
					.addChoice("Medal Upgrades", 9)
					.addChoice("Bank Upgrades (Orchestra)", 10);
			jda.upsertCommand(
					Commands.slash("up", "View your upgrades.")
							.addOptions(data)
			).queue();
			jda.upsertCommand(
					Commands.slash("buy", "Buy something.  CASE SENSITIVE!")
							.addOption(OptionType.STRING, "item", "Pick the item to buy.")
			).queue();
			data = new OptionData(OptionType.STRING, "item", "Choose the item you want to craft.")
					.addChoice("Rice", "rice")
					.addChoice("Bubble Tea", "tea")
					.addChoice("Blessing", "blessing")
					.addChoice("Rosin", "rosin")
					.addChoice("New Strings", "string")
					.addChoice("Bow Hair", "bowhair")
					.addChoice("Violin Service", "service")
					.addChoice("Free Box", "free")
					.addChoice("Gift Box", "gift")
					.addChoice("Musician Kit", "kit")
					.addChoice("Ling Ling Box", "llbox")
					.addChoice("Crazy Person Box", "crazybox")
					.addChoice("RNGesus Box", "rngesus");
			jda.upsertCommand(
					Commands.slash("use", "Use something.")
							.addOptions(data)
							.addOption(OptionType.STRING, "amount", "How much to use.")
			).queue();
			jda.upsertCommand(
					Commands.slash("claim", "Claim a Free Box, every 24 hours!")
			).queue();
			jda.upsertCommand(
					Commands.slash("gift", "Gift a Friend!")
							.addOption(OptionType.STRING, "otheruser", "Put your friend's ID here.")
			).queue();
			data = new OptionData(OptionType.STRING, "leaderboard", "Pick which leaderboard you want to see.")
					.addChoice("Richest Users", "violins")
					.addChoice("Highest Hourly Incomes", "income")
					.addChoice("Longest Daily Streaks", "streak")
					.addChoice("Most Ling Ling Medals", "medals")
					.addChoice("Most Gamble Winnings", "winnings")
					.addChoice("Most Million-Violin Tickets Drawn", "million")
					.addChoice("Most Violins Robbed", "rob")
					.addChoice("Most Scales Played", "scales")
					.addChoice("Most Hours Practised", "hours")
					.addChoice("Most Rehearsals Attended", "rehearsals")
					.addChoice("Most Performances Given", "performances")
					.addChoice("Most Hours Taught", "teach")
					.addChoice("Most Violins Earned", "earnings")
					.addChoice("Most Luthier Unscrambles", "luthier")
					.addChoice("Most Gifts Given", "gift")
					.addChoice("Most Free Boxes Claimed", "free")
					.addChoice("Luckiest Users", "rng")
					.addChoice("Most Magic Find", "magicfind");
			jda.upsertCommand(
					Commands.slash("lb", "View a leaderboard!")
							.addOptions(data)
			).queue();
			jda.upsertCommand(
					Commands.slash("dep", "Deposit Violins into your bank account.")
							.addOption(OptionType.STRING, "amount", "How much you want to deposit.")
			).queue();
			jda.upsertCommand(
					Commands.slash("with", "Withdraws Violins into your bank account.")
							.addOption(OptionType.STRING, "amount", "How much you want to withdraw.")
			).queue();
			jda.upsertCommand(
					Commands.slash("loan", "Loans Violins from The Bank of TwoSet.")
							.addOption(OptionType.STRING, "amount", "How much you want to loan.")
			).queue();
			jda.upsertCommand(
					Commands.slash("payloan", "Pays back your loan.")
							.addOption(OptionType.STRING, "amount", "How much you want to pay back.")
			).queue();
			
			// help page 4
			jda.upsertCommand(
					Commands.slash("s", "Plays some scales.")
			).queue();
			jda.upsertCommand(
					Commands.slash("p", "Practises for 45 minutes.")
			).queue();
			jda.upsertCommand(
					Commands.slash("r", "Goes to Rehearsal.")
			).queue();
			jda.upsertCommand(
					Commands.slash("pf", "Gives a performance.")
			).queue();
			jda.upsertCommand(
					Commands.slash("t", "Teach a lesson.")
			).queue();
			jda.upsertCommand(
					Commands.slash("d", "Get your Daily Dose of Violins!")
			).queue();
			data = new OptionData(OptionType.STRING, "game", "Which gambling game you want to play.")
					.addChoice("50/50", "rng")
					.addChoice("Slot Machine", "slots")
					.addChoice("Scratch Tickets", "scratch");
			jda.upsertCommand(
					Commands.slash("bet", "Gamble your Violins!  But don't do it like Paganini.  RNGesus isn't that nice.")
							.addOptions(data)
							.addOption(OptionType.STRING, "amount", "How much you want to bet.")
			).queue();
			jda.upsertCommand(
					Commands.slash("rob", "Rob your friends!")
							.addOption(OptionType.STRING, "target", "Put the ID of your unfortunate target.")
			).queue();
			
			// help page 5
			jda.upsertCommand(
					Commands.slash("give", "Give a user some items.")
							.addOption(OptionType.STRING, "user", "Person")
							.addOption(OptionType.STRING, "item", "The item")
							.addOption(OptionType.INTEGER, "amount", "The amount")
			).queue();
			jda.upsertCommand(
					Commands.slash("warn", "Warns a user for misbehaviour.")
							.addOption(OptionType.STRING, "user", "The misbehaver")
							.addOption(OptionType.STRING, "reason", "Why are you warning them?????")
			).queue();
			jda.upsertCommand(
					Commands.slash("resetsave", "Resets the save of a user.")
							.addOption(OptionType.STRING, "user", "The misbehaver")
							.addOption(OptionType.STRING, "reason", "Why are you resetting their save?????")
			).queue();
			data = new OptionData(OptionType.STRING, "actiontype", "Setup or Edit")
					.addChoice("Setup", "setup")
					.addChoice("Edit", "edit");
			data2 = new OptionData(OptionType.STRING, "editoption", "Which Edit option")
					.addChoice("Channel", "channel")
					.addChoice("Multiplier", "multiplier")
					.addChoice("Word", "word");
			jda.upsertCommand(
					Commands.slash("luthier", "Changes the Luthier configuration for a server.")
							.addOptions(data, data2)
							.addOption(OptionType.STRING, "newvalue", "See Name")
			).queue();
			jda.upsertCommand(
					Commands.slash("resetincomes", "Adjusts all users' incomes to correct values.")
			).queue();
			jda.upsertCommand(
					Commands.slash("ban", "BANs a user.")
							.addOption(OptionType.STRING, "user", "The misbehaver")
							.addOption(OptionType.STRING, "reason", "Why are you banning them?????")
			).queue();
			jda.upsertCommand(
					Commands.slash("unban", "unbans a user.")
							.addOption(OptionType.STRING, "user", "The pardoned")
							.addOption(OptionType.BOOLEAN, "resetsave", "Do you want to reset their save?")
							.addOption(OptionType.STRING, "reason", "Why are you unbanning them?????")
			).queue();
			jda.upsertCommand(
					Commands.slash("updateluthierchance", "Forcibly updates all luthier spawn rates.")
			).queue();
			
			//help page 6
			jda.upsertCommand(
					Commands.slash("updateusers", "Updates all saves.")
							.addOption(OptionType.STRING, "newkey", "See name")
							.addOption(OptionType.STRING, "datatype", "See name")
							.addOption(OptionType.STRING, "setvalue", "Value of the new key")
			).queue();
			jda.upsertCommand(
					Commands.slash("updateroles", "Forcibly updates all roles for users.")
			).queue();
			data = new OptionData(OptionType.INTEGER, "rank", "The new permission level")
					.addChoice("0", 0)
					.addChoice("1", 1)
					.addChoice("2", 2)
					.addChoice("3", 3);
			jda.upsertCommand(
					Commands.slash("setpermlevel", "Sets the permission level for a user.")
							.addOption(OptionType.STRING, "user", "The user to change the Permissions for.")
							.addOptions(data)
			).queue();
			jda.upsertCommand(
					Commands.slash("globalstats", "View the combined stats of EVERYONE.")
			).queue();
			jda.upsertCommand(
					Commands.slash("forcestop", "Forcibly stops the bot, and prevents its restart.")
							.addOption(OptionType.STRING, "password", "To prevent accidental usage.")
			).queue();
			jda.upsertCommand(
					Commands.slash("resetdaily", "Gives everyone more time to run /d")
			).queue();
			jda.upsertCommand(
					Commands.slash("custom", "Custom Command - Usually Disabled")
			).queue();
			
			// LUTHIER
			jda.upsertCommand(
					Commands.slash("answer", "Answers a Luthier unscramble.")
							.addOption(OptionType.STRING, "guess", "See name")
			).queue();
			System.out.println("All commands upserted!");*/
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}