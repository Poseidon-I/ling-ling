package processes;

import economy.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;

public class Economy {
	public Economy(MessageReceivedEvent e, String[] message) {
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
						case "violins" -> new Leaderboard(":violin:", "Richest Users", e, "violins", (long) data.get("violins"), (String) data.get("color"));
						case "streak" -> new Leaderboard(":calendar:", "Longest Daily Streaks", e, "streak", (long) data.get("streak"), (String) data.get("color"));
						case "medals" -> {
							long num = (long) data.get("thirdPlace") + 2 * (long) data.get("secondPlace") + 3 * (long) data.get("firstPlace");
							new Leaderboard(":military_medal:", "Most Worthy Users", e, "medals", num, (String) data.get("color"));
						}
						case "income" -> new Leaderboard(":violin:/hour", "Highest Hourly Incomes", e, "income", (long) data.get("income"), (String) data.get("color"));
						case "winnings" -> new Leaderboard(":moneybag:", "Best Gamblers", e, "winnings", (long) data.get("winnings"), (String) data.get("color"));
						case "million" -> new Leaderboard(":tickets:", "Luckiest Users", e, "millions", (long) data.get("millions"), (String) data.get("color"));
						case "rob" -> new Leaderboard(":violin:", "Most Heartless Users", e, "robbed", (long) data.get("robbed"), (String) data.get("color"));
						case "scales" -> new Leaderboard(":scales:", "Most Scales Played", e, "scalesPlayed", (long) data.get("scalesPlayed"), (String) data.get("color"));
						case "hours" -> new Leaderboard(":clock2:", "Most Hours Practised", e, "hoursPractised", (long) ((double) data.get("hoursPractised")), (String) data.get("color"));
						case "rehearsals" -> new Leaderboard(":musical_score:", "Most Rehearsals Attended", e, "rehearsals", (long) data.get("rehearsals"), (String) data.get("color"));
						case "performances" -> new Leaderboard(":microphone:", "Most Performances", e, "performances", (long) data.get("performances"), (String) data.get("color"));
						case "earnings" -> new Leaderboard(":violin:", "Most Hardworking Users", e, "earnings", (long) data.get("earnings"), (String) data.get("color"));
						case "teach" -> new Leaderboard(":teacher:", "Most Influential Users", e, "hoursTaught", (long) ((double) data.get("hoursTaught")), (String) data.get("color"));
						case "luthier" -> new Leaderboard(":question:", "Best Unscramblers", e, "luthiers", (long) data.get("luthiers"), (String) data.get("color"));
						case "gift" -> new Leaderboard(":gift:", "Most Generous Users", e, "giftsGiven", (long) data.get("giftsGiven"), (String) data.get("color"));
						case "vote" -> new Leaderboard(":ballot_box:", "Most Outspoken Users", e, "votes", (long) data.get("votes"), (String) data.get("color"));
						case "rng" -> new Leaderboard(":slot_machine:", "Truly Luckiest Users", e, "RNGesusWeight", (long) data.get("RNGesusWeight"), (String) data.get("color"));
						case "magicfind" -> new Leaderboard(":star:", "Statistically Luckiest Users", e, "magicFind", (long) data.get("magicFind"), (String) data.get("color"));
						case "loan" -> new Leaderboard(":bank:", "Worst-Behaved Users", e, "loan", (long) data.get("loan"), (String) data.get("color"));
						default -> e.getMessage().reply("You must provide a valid leaderboard type.  Valid types...\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`vote`: Users that have voted the most\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find\n`loan`: Users with the highest loan").mentionRepliedUser(false).queue();
					}
				} catch(Exception exception) {
					e.getMessage().reply("**__Leaderboard Types__**\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`vote`: Users that have voted the most\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find\n`loan`: Users with the highest loan").mentionRepliedUser(false).queue();
				}
			}
		}
	}
}