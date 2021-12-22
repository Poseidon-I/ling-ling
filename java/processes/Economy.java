package processes;

import economy.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

public class Economy {
	public Economy(GuildMessageReceivedEvent e, String[] message) {
		try {
			switch(message[0]) {
				case "start" -> new Start(e, e.getAuthor().getId(), false);
				case "upgrades", "up", "u", "shop" -> new Upgrades(e);
				case "buy" -> new Buy(e);
				case "cooldowns", "c" -> new Cooldowns(e);
				case "use" -> new Use(e);
				case "scales", "s" -> new Scales(e);
				case "practice", "p" -> new Practise(e);
				case "rehearse", "r" -> new Rehearse(e);
				case "perform", "pf" -> new Perform(e);
				case "daily", "d" -> new Daily(e);
				case "teach", "t" -> new Teach(e);
				case "rob" -> new Rob(e);
				case "gamble", "bet" -> new Gamble(e);
				case "inventory", "inv" -> new Inventory(e);
				case "profile", "balance", "bal", "b" -> new Balance(e);
				case "stats" -> new Stats(e);
				case "claim" -> new Vote(e);
				case "gift" -> new Gift(e);
				case "deposit", "dep" -> new Deposit(e);
				case "withdraw", "with" -> new Withdraw(e);
				case "loan" -> new Loan(e);
				case "payloan" -> new PayLoan(e);
				case "leaderboard", "lb" -> {
					JSONObject data = LoadData.loadData(e);
					try {
						switch(message[1]) {
							case "violins" -> new Leaderboard(":violin:", "Richest Users", e, "violins", (long) data.get("violins"));
							case "streak" -> new Leaderboard(":calendar:", "Longest Daily Streaks", e, "streak", (long) data.get("streak"));
							case "medals" -> {
								long num = (long) data.get("thirdPlace") + 2 * (long) data.get("secondPlace") + 3 * (long) data.get("firstPlace");
								new Leaderboard(":military_medal:", "Most Worthy Users", e, "medals", num);
							}
							case "income" -> new Leaderboard(":violin:/hour", "Highest Hourly Incomes", e, "income", (long) data.get("income"));
							case "winnings" -> new Leaderboard(":moneybag:", "Best Gamblers", e, "winnings", (long) data.get("winnings"));
							case "million" -> new Leaderboard(":tickets:", "Luckiest Users", e, "millions", (long) data.get("millions"));
							case "rob" -> new Leaderboard(":violin:", "Most Heartless Users", e, "robbed", (long) data.get("robbed"));
							case "scales" -> new Leaderboard(":scales:", "Most Scales Playes", e, "scalesPlayed", (long) data.get("scalesPlayed"));
							case "hours" -> new Leaderboard(":clock2:", "Most Hours Practised", e, "hoursPractised", (long) data.get("hoursPractised"));
							case "rehearsals" -> new Leaderboard(":musical_score:", "Most Rehearsals Attended", e, "rehearsals", (long) data.get("rehearsals"));
							case "performances" -> new Leaderboard(":microphone:", "Most Performances", e, "performances", (long) data.get("performances"));
							case "earnings" -> new Leaderboard(":violin:", "Most Hardworking Users", e, "earnings", (long) data.get("earnings"));
							case "teach" -> new Leaderboard(":teacher:", "Most Influential Users", e, "hoursTaught", (long) data.get("hoursTaught"));
							case "luthier" -> new Leaderboard(":question:", "Best Unscramblers", e, "luthiers", (long) data.get("luthiers"));
							case "gift" -> new Leaderboard(":gift:", "Most Generous Users", e, "giftsGiven", (long) data.get("giftsgiven"));
							case "vote" -> new Leaderboard(":ballot_box:", "Most Outspoken Users", e, "votes", (long) data.get("votes"));
							default -> e.getChannel().sendMessage("You must provide a valid leaderboard type.  Run the command with no arguments for a list of leaderboards.").queue();
						}
					} catch(Exception exception) {
						e.getChannel().sendMessage("**__Leaderboard Types__**\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`vote`: Users that have voted the most").queue();
					}
				}
				
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}