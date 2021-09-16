package processes;

import economy.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Economy {
	public Economy(GuildMessageReceivedEvent e, String[] message, char prefix) {
		switch(message[0]) {
			case "start" -> new Start(e, prefix);
			case "upgrades", "up", "u", "shop" -> new Upgrades(e, prefix);
			case "buy" -> new Buy(e);
			case "cooldowns", "c" -> new Cooldowns(e);
			case "use" -> new Use(e, prefix);
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
			case "deposit" -> new Deposit(e);
			case "withdraw", "with" -> new Withdraw(e);
			case "loan" -> new Loan(e);
			case "payloan" -> new PayLoan(e);
			case "leaderboard", "lb" -> {
				String[] data = LoadData.loadData(e, "Economy Data");
				try {
					switch(message[1]) {
						case "violins" -> new Leaderboard(":violin:", "Richest Users", e, 0, Long.parseLong(data[0]));
						case "streak" -> new Leaderboard(":calendar:", "Longest Daily Streaks", e, 47, Long.parseLong(data[47]));
						case "medals" -> new Leaderboard(":military_medal:", "Most Ling Ling Worthy Users", e, 55, Long.parseLong(data[55]));
						case "income" -> new Leaderboard(":violin:/hour", "Highest Hourly Incomes", e, 12, Long.parseLong(data[12]));
						case "winnings" -> new Leaderboard(":moneybag:", "Best Gamblers", e, 66, Long.parseLong(data[66]));
						case "million" -> new Leaderboard(":tickets:", "Luckiest Users", e, 67, Long.parseLong(data[67]));
						case "rob" -> new Leaderboard(":violin:", "Most Heartless Users", e, 68, Long.parseLong(data[68]));
						case "scales" -> new Leaderboard(":scales:", "Most Scales Playes", e, 70, Long.parseLong(data[70]));
						case "hours" -> new Leaderboard(":clock2:", "Most Hours Practised", e, 71, (long) Double.parseDouble(data[71]));
						case "rehearsals" -> new Leaderboard(":musical_score:", "Most Rehearsals Attended", e, 72, Long.parseLong(data[72]));
						case "performances" -> new Leaderboard(":microphone:", "Most Performances", e, 73, Long.parseLong(data[73]));
						case "earnings" -> new Leaderboard(":violin:", "Most Hardworking Users", e, 75, Long.parseLong(data[75]));
						case "teach" -> new Leaderboard(":teacher:", "Most Influential Users", e, 76, (long) Double.parseDouble(data[76]));
						case "luthier" -> new Leaderboard(":question:", "Best Unscramblers", e, 77, Long.parseLong(data[77]));
						case "gift" -> new Leaderboard(":gift:", "Most Generous Users", e, 85, Long.parseLong(data[85]));
						case "vote" -> new Leaderboard(":ballot_box:", "Most Outspoken Users", e, 88, Long.parseLong(data[88]));
						default -> e.getChannel().sendMessage("You must provide a valid leaderboard type.  Run the command with no arguments for a list of leaderboards.").queue();
					}
				} catch(Exception exception) {
					e.getChannel().sendMessage("**__Leaderboard Types__**\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`vote`: Users that have voted the most").queue();
				}
			}
		}
	}
}