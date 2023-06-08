package eventListeners;

import dev.*;
import economy.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.HourlyIncome;
import processes.InterestPenalty;
import processes.Luthier;
import regular.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewReceiver extends ListenerAdapter {
	public static long CheckPermLevel(@NotNull GenericDiscordEvent e) {
		if(e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962")) {
			return 3;
		} else {
			JSONParser parser = new JSONParser();
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".json")) {
				JSONObject data = (JSONObject) parser.parse(reader);
				reader.close();
				return (long) data.get("perms");
			} catch(Exception exception) {
				exception.printStackTrace();
				return 0;
			}
		}
	}

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
		//LOAD SERVER MEMBERS ONLY ONCE
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\loadedservers.txt"));
			String line = reader.readLine();
			reader.close();
			try {
				List<String> loaded = Arrays.asList(line.split(" "));
				if(!loaded.contains(Objects.requireNonNull(e.getGuild()).getId()) || e.getJDA().getSelfUser().getId().equals("772582345944334356")) {
					e.getGuild().loadMembers();
					line += " " + e.getGuild().getId();
				}
			} catch(Exception exception) {
				Objects.requireNonNull(e.getGuild()).loadMembers();
				line = e.getGuild().getId();
			}
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\loadedservers.txt")));
			writer.print(line);
			writer.close();
		} catch(Exception exception1) {
			//nothing here lol
		}

		GenericDiscordEvent e1 = new GenericDiscordEvent(e);

		//LUTHIER
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
			Luthier.luthier(e1, (JSONObject) parser.parse(reader), "");
			reader.close();
		} catch(Exception exception) {
			//nothing here lol
		}

		// ALL COMMANDS
		String commandName = e.getName();
		switch(commandName) {
			// NON-ECON COMMANDS
			case "help" -> {
				String page;
				try {
					page = Objects.requireNonNull(e.getOption("page")).getAsString();
				} catch(Exception exception) {
					page = "";
				}
				Help.help(e1, page);
			}
			case "faq" -> {
				String page;
				try {
					page = Objects.requireNonNull(e.getOption("page")).getAsString();
				} catch(Exception exception) {
					page = "";
				}
				FAQ.faq(e1, page);
			}
			case "website" ->
					e1.reply("Weebly is hard to use, so there is currently no website.  Maybe in the future I'll get someone to make it, but for now, everything is on Google Sheets or Google Docs.");
			case "support" -> e1.reply("Join the support server at discord.gg/gNfPwa8");
			case "vote" -> e.reply("Vote here to earn an extra Free Box!\n<https://top.gg/bot/733409243222507670/vote>");
			case "guide" ->
					e1.reply("The Beginner Guide can be found at <https://docs.google.com/document/d/1Oo8m8XuGsIOyMzJhllUN9SpOJI8hSUeQt5RbyPY9qMI/edit?usp=sharing>, written by `bubblepotatochips#0498`");
			case "kill" -> {
				String target;
				try {
					target = Objects.requireNonNull(e.getOption("page")).getAsString();
				} catch(Exception exception) {
					target = "Nobody";
				}
				Kill.kill(e1, target);
			}
			case "joke" -> Joke.joke(e1);
			case "poll" -> {
				String title;
				String choices;
				try {
					title = Objects.requireNonNull(e.getOption("title")).getAsString();
				} catch(Exception exception) {
					title = "No Title";
				}
				try {
					choices = Objects.requireNonNull(e.getOption("choices")).getAsString();
				} catch(Exception exception) {
					choices = "";
				}
				Poll.poll(e1, title, choices);
			}
			case "emojify" -> {
				String message;
				try {
					message = Objects.requireNonNull(e.getOption("message")).getAsString();
				} catch(Exception exception) {
					message = "";
				}
				Emojify.emojify(e1, message);
			}
			case "invite" ->
					e1.reply("You can add the bot to your server using the below link:\n<https://discord.com/api/oauth2/authorize?client_id=733409243222507670&permissions=67398720&scope=bot%20applications.commands>");
			case "staff" -> e1.reply("""
					```fix
					███████╗████████╗ █████╗ ███████╗███████╗
					██╔════╝╚══██╔══╝██╔══██╗██╔════╝██╔════╝
					███████╗   ██║   ███████║█████╗  █████╗
					╚════██║   ██║   ██╔══██║██╔══╝  ██╔══╝
					███████║   ██║   ██║  ██║██║     ██║
					╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝
					```
					**Developer**: `Stradivarius Violin#6156`
					**Bot Admins**: `JMusical#5262` `jacqueline#1343`
					**Bot Mods**: `Penguin Irina#6514` `akc0303#5743`
					""");
			case "rules" -> e1.reply("""
					```fix
					██████╗ ██╗   ██╗██╗     ███████╗███████╗
					██╔══██╗██║   ██║██║     ██╔════╝██╔════╝
					██████╔╝██║   ██║██║     █████╗  ███████╗
					██╔══██╗██║   ██║██║     ██╔══╝  ╚════██║
					██║  ██║╚██████╔╝███████╗███████╗███████║
					╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚══════╝╚══════╝
					```
					1. Do not spam commands, excessively ping users, or send messages to trigger luthier or take any sort of action that may cause the bot to crash.
					2. Do not abuse bugs or exploits.  If a bug/exploit is found, **IMMEDIATELY** report it to `Stradivarius Violin#6156`.
					3. Do not overexcessively or inappropriately use `!checkdm` or `!kill`.  These were written to poke fun at others, not to annoy/hurt them.
					4. Do not ask mods/admins/devs for information that can easily be found by reading the patch notes, using `!help`, or using `!faq`.
					5. **All bot mods/admins reserve the right to punish users when breaking bot rules is involved.**  Attempting to stop bot mods/admins from using bot moderation commands will result in your punishment as well.
										
					**Privacy Notice** - Ling Ling **does not store any data** except for data explicitly related to the economy system, which are variables all generated by the bot itself, with the exception of IDs provided by Discord (eg. Server ID for Luthier & Prefixes, User ID for saves), which are necessary to allow the bot to distinguish between different users and servers.
										
					tl;dr: ling ling doesn't store any data except your discord user id and stuff it makes by itself so that it works properly
					""");
			case "botstats" -> {
				int serverCount = e.getJDA().getGuilds().size();
				File[] files = new File("Ling Ling Bot Data\\Economy Data").listFiles();
				assert files != null;
				e1.reply("Servers: " + serverCount + "\nUsers: " + files.length);
			}
			case "settings" -> {
				String option;
				String newValue;
				try {
					option = Objects.requireNonNull(e.getOption("option")).getAsString();
				} catch(Exception exception) {
					option = "none";
				}
				try {
					newValue = Objects.requireNonNull(e.getOption("newvalue")).getAsString();
				} catch(Exception exception) {
					newValue = "";
				}
				UserSettings.userSettings(e1, option, newValue);
			}
			case "market" -> {
				String action;
				String item;
				long amount;
				long price;
				try {
					action = Objects.requireNonNull(e.getOption("action")).getAsString();
				} catch(Exception exception) {
					action = "";
				}
				try {
					item = Objects.requireNonNull(e.getOption("item")).getAsString();
				} catch(Exception exception) {
					item = "none";
				}
				try {
					amount = Objects.requireNonNull(e.getOption("amount")).getAsInt();
				} catch(Exception exception) {
					amount = 1;
				}
				try {
					price = Objects.requireNonNull(e.getOption("price")).getAsInt();
				} catch(Exception exception) {
					price = -1;
				}

				Market.market(e1, item, action, amount, price);
			}
			case "craft" -> {
				long craftAmount;
				String name;
				try {
					name = Objects.requireNonNull(e.getOption("item")).getAsString();
				} catch(Exception exception) {
					name = "";
				}
				try {
					craftAmount = Objects.requireNonNull(e.getOption("amount")).getAsLong();
				} catch(Exception exception) {
					craftAmount = -1;
				}
				Craft.craft(e1, craftAmount, name);
			}

			// ECON COMMANDS
			case "start" -> Start.start(e1, e.getUser().getId(), false);
			case "upgrades", "up", "u", "shop" -> {
				int page;
				try {
					page = Objects.requireNonNull(e.getOption("page")).getAsInt();
				} catch(Exception exception) {
					page = -1;
				}
				Upgrades.upgrades(e1, page);
			}
			case "buy" -> {
				String item;
				try {
					item = Objects.requireNonNull(e.getOption("item")).getAsString();
				} catch(Exception exception) {
					item = "";
				}
				Buy.buy(e1, item);
			}
			case "cooldowns", "c" -> Cooldowns.cooldowns(e1);
			case "use" -> {
				String item;
				String amount;
				try {
					item = Objects.requireNonNull(e.getOption("item")).getAsString();
				} catch(NullPointerException exception) {
					item = "";
				}
				try {
					amount = Objects.requireNonNull(e.getOption("amount")).getAsString();
				} catch(NullPointerException exception) {
					amount = "1";
				}
				Use.use(e1, item, amount);
			}
			case "scales", "s" -> Scales.scales(e1);
			case "practice", "p" -> Practise.practise(e1);
			case "rehearse", "r" -> Rehearse.rehearse(e1);
			case "perform", "pf" -> Perform.perform(e1);
			case "daily", "d" -> Daily.daily(e1);
			case "teach", "t" -> Teach.teach(e1);
			case "rob" -> {
				String user;
				try {
					user = Objects.requireNonNull(e.getOption("target")).getAsString();
				} catch(NullPointerException exception) {
					user = e.getUser().getId();
				}
				Rob.rob(e1, user);
			}
			case "inventory", "inv" -> {
				int page;
				String user;
				try {
					page = Objects.requireNonNull(e.getOption("page")).getAsInt();
				} catch(NullPointerException exception) {
					page = -1;
				}
				try {
					user = Objects.requireNonNull(e.getOption("otheruser")).getAsString();
				} catch(NullPointerException exception) {
					user = e.getUser().getId();
				}
				Inventory.inventory(e1, user, page);
			}
			case "profile", "balance", "bal", "b" -> {
				String user;
				try {
					user = Objects.requireNonNull(e.getOption("otheruser")).getAsString();
				} catch(NullPointerException exception) {
					user = e.getUser().getId();
				}
				Balance.balance(e1, user);
			}
			case "stats" -> {
				String user;
				try {
					user = Objects.requireNonNull(e.getOption("otheruser")).getAsString();
				} catch(NullPointerException exception) {
					user = e.getUser().getId();
				}
				Stats.stats(e1, user);
			}
			case "claim" -> Vote.vote(e1);
			case "gift" -> {
				String user;
				try {
					user = Objects.requireNonNull(e.getOption("otheruser")).getAsString();
				} catch(NullPointerException exception) {
					user = e.getUser().getId();
				}
				Gift.gift(e1, user);
			}
			case "deposit", "dep" -> {
				String amount;
				try {
					amount = Objects.requireNonNull(e.getOption("amount")).getAsString();
				} catch(Exception exception) {
					amount = "";
				}
				Deposit.deposit(e1, amount);
			}
			case "withdraw", "with" -> {
				String amount;
				try {
					amount = Objects.requireNonNull(e.getOption("amount")).getAsString();
				} catch(Exception exception) {
					amount = "";
				}
				Withdraw.withdraw(e1, amount);
			}
			case "loan" -> {
				String amount;
				try {
					amount = Objects.requireNonNull(e.getOption("amount")).getAsString();
				} catch(Exception exception) {
					amount = "";
				}
				Loan.loan(e1, amount);
			}
			case "payloan" -> {
				String amount;
				try {
					amount = Objects.requireNonNull(e.getOption("amount")).getAsString();
				} catch(Exception exception) {
					amount = "";
				}
				PayLoan.payLoan(e1, amount);
			}
			case "answer" -> {
				String answer;
				try {
					answer = Objects.requireNonNull(e.getOption("guess")).getAsString();
				} catch(Exception exception) {
					answer = "none";
				}
				parser = new JSONParser();
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
					Luthier.luthier(e1, (JSONObject) parser.parse(reader), answer);
					reader.close();
				} catch(Exception exception) {
					//nothing here lol
				}
			}
			case "leaderboard", "lb" -> {
				JSONObject data = LoadData.loadData(e1);
				try {
					switch(Objects.requireNonNull(e.getOption("leaderboard")).getAsString()) {
						case "violins" ->
								Leaderboard.leaderboard(e1, Emoji.VIOLINS, "Richest Users", "violins", (long) data.get("violins"), (String) data.get("color"));
						case "streak" ->
								Leaderboard.leaderboard(e1, ":calendar:", "Longest Daily Streaks", "streak", (long) data.get("streak"), (String) data.get("color"));
						case "medals" ->
								Leaderboard.leaderboard(e1, Emoji.MEDALS, "Most Worthy Users", "medals", (long) data.get("medals"), (String) data.get("color"));
						case "income" ->
								Leaderboard.leaderboard(e1, Emoji.VIOLINS + "/hour", "Highest Hourly Incomes", "income", (long) data.get("income"), (String) data.get("color"));
						case "winnings" ->
								Leaderboard.leaderboard(e1, ":moneybag:", "Best Gamblers", "winnings", (long) data.get("winnings"), (String) data.get("color"));
						case "million" ->
								Leaderboard.leaderboard(e1, ":tickets:", "Luckiest Users", "millions", (long) data.get("millions"), (String) data.get("color"));
						case "rob" ->
								Leaderboard.leaderboard(e1, Emoji.VIOLINS, "Most Heartless Users", "robbed", (long) data.get("robbed"), (String) data.get("color"));
						case "scales" ->
								Leaderboard.leaderboard(e1, ":scales:", "Most Scales Played", "scalesPlayed", (long) data.get("scalesPlayed"), (String) data.get("color"));
						case "hours" ->
								Leaderboard.leaderboard(e1, ":clock2:", "Most Hours Practised", "hoursPractised", (long) ((double) data.get("hoursPractised")), (String) data.get("color"));
						case "rehearsals" ->
								Leaderboard.leaderboard(e1, ":musical_score:", "Most Rehearsals Attended", "rehearsals", (long) data.get("rehearsals"), (String) data.get("color"));
						case "performances" ->
								Leaderboard.leaderboard(e1, ":microphone:", "Most Performances", "performances", (long) data.get("performances"), (String) data.get("color"));
						case "earnings" ->
								Leaderboard.leaderboard(e1, Emoji.VIOLINS, "Most Hardworking Users", "earnings", (long) data.get("earnings"), (String) data.get("color"));
						case "teach" ->
								Leaderboard.leaderboard(e1, ":teacher:", "Most Influential Users", "hoursTaught", (long) ((double) data.get("hoursTaught")), (String) data.get("color"));
						case "luthier" ->
								Leaderboard.leaderboard(e1, ":question:", "Best Unscramblers", "luthiers", (long) data.get("luthiers"), (String) data.get("color"));
						case "gift" ->
								Leaderboard.leaderboard(e1, Emoji.GIFT_BOX, "Most Generous Users", "giftsGiven", (long) data.get("giftsGiven"), (String) data.get("color"));
						case "free" ->
								Leaderboard.leaderboard(e1, ":money_mouth:", "Most Stingy Users", "votes", (long) data.get("votes"), (String) data.get("color"));
						case "rng" ->
								Leaderboard.leaderboard(e1, ":slot_machine:", "Truly Luckiest Users", "RNGesusWeight", (long) data.get("RNGesusWeight"), (String) data.get("color"));
						case "magicfind" ->
								Leaderboard.leaderboard(e1, ":star:", "Statistically Luckiest Users", "magicFind", (long) data.get("magicFind"), (String) data.get("color"));
						case "moneyearned" ->
								Leaderboard.leaderboard(e1, Emoji.VIOLINS, "Best Entrepreneurs", "moneyEarned", (long) data.get("moneyEarned"), (String) data.get("color"));
						case "moneyspent" ->
								Leaderboard.leaderboard(e1, Emoji.VIOLINS, "Biggest Spenders", "moneySpent", (long) data.get("moneySpent"), (String) data.get("color"));
						default ->
								e1.reply("You must provide a valid leaderboard type.  Valid types...\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`free`: Users that have claimed the most Free Boxes\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find");
					}
				} catch(Exception exception) {
					e1.reply("**__Leaderboard Types__**\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`free`: Users that have claimed the most Free Boxes\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find");
				}
			}

			// DEV COMMANDS
			case "give" -> {
				if(CheckPermLevel(e1) >= 1) {
					String receiver;
					long add;
					String item;
					try {
						receiver = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						receiver = "";
					}
					try {
						add = Long.parseLong(Objects.requireNonNull(e.getOption("amount")).getAsString());
					} catch(NullPointerException exception) {
						add = -1;
					} catch(Exception exception) {
						add = -2;
					}
					try {
						item = Objects.requireNonNull(e.getOption("item")).getAsString();
					} catch(Exception exception) {
						item = "";
					}
					Give.give(e1, receiver, add, item);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "warn" -> {
				if(CheckPermLevel(e1) >= 1) {
					String idToModerate;
					String reason;
					try {
						idToModerate = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						idToModerate = "";
					}
					try {
						reason = Objects.requireNonNull(e.getOption("reason")).getAsString();
					} catch(Exception exception) {
						reason = "None";
					}
					Warn.warn(e1, idToModerate, reason);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "resetsave" -> {
				if(CheckPermLevel(e1) >= 1) {
					String idToModerate;
					String reason;
					try {
						idToModerate = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						idToModerate = "";
					}
					try {
						reason = Objects.requireNonNull(e.getOption("reason")).getAsString();
					} catch(Exception exception) {
						reason = "None";
					}
					ResetSave.resetSave(e1, idToModerate, reason);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "ban" -> {
				if(CheckPermLevel(e1) >= 2) {
					String idToModerate;
					String reason;
					try {
						idToModerate = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						idToModerate = "";
					}
					try {
						reason = Objects.requireNonNull(e.getOption("reason")).getAsString();
					} catch(Exception exception) {
						reason = "None";
					}
					Ban.ban(e1, idToModerate, reason);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "unban" -> {
				if(CheckPermLevel(e1) >= 2) {
					String idToModerate;
					String reason;
					Boolean reset;
					try {
						idToModerate = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						idToModerate = "";
					}
					try {
						reason = Objects.requireNonNull(e.getOption("reason")).getAsString();
					} catch(Exception exception) {
						reason = "None";
					}
					try {
						reset = Objects.requireNonNull(e.getOption("resetsave")).getAsBoolean();
					} catch(Exception exception) {
						reset = null;
					}
					Unban.unban(e1, idToModerate, reason, reset);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "luthier" -> {
				if(CheckPermLevel(e1) >= 2) {
					String actionType;
					String editOption;
					String newValue;
					try {
						actionType = Objects.requireNonNull(e.getOption("actiontype")).getAsString();
					} catch(Exception exception) {
						actionType = "";
					}
					try {
						editOption = Objects.requireNonNull(e.getOption("editOption")).getAsString();
					} catch(Exception exception) {
						editOption = "";
					}
					try {
						newValue = Objects.requireNonNull(e.getOption("newValue")).getAsString();
					} catch(Exception exception) {
						newValue = "";
					}
					AdminLuthier.adminLuthier(e1, actionType, editOption, newValue);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "resetincomes" -> {
				if(CheckPermLevel(e1) >= 2) {
					e1.reply(ResetIncomes.resetIncomes());
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "updateluthierchance" -> {
				if(CheckPermLevel(e1) >= 2) {
					UpdateLuthierChance.updateLuthierChance(e1);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "updateusers" -> {
				if(CheckPermLevel(e1) == 3) {
					String dataType;
					String name;
					String value;
					try {
						dataType = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						dataType = "";
					}
					try {
						name = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						name = "";
					}
					try {
						value = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						value = "";
					}
					UpdateUsers.updateUsers(e1, dataType, name, value);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "forcestop" -> {
				if(CheckPermLevel(e1) == 3 && Objects.requireNonNull(e.getOption("PASSWORD")).getAsString().equals("@#$%FUCK")) {
					e1.reply("Forcing bot to stop...");
					File file = new File("Ling Ling Bot Data\\pid.txt");
					file.delete();
					e.getJDA().shutdownNow();
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command, or you entered the wrong Password.");
				}
			}
			case "updateroles" -> {
				if(CheckPermLevel(e1) == 3) {
					UpdateRoles.updateRoles(e1);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
				}
			}
			case "setpermlevel" -> {
				if(CheckPermLevel(e1) == 3) {
					String target;
					int newRank;
					try {
						target = Objects.requireNonNull(e.getOption("user")).getAsString();
					} catch(Exception exception) {
						target = "";
					}
					try {
						newRank = Objects.requireNonNull(e.getOption("rank")).getAsInt();
					} catch(Exception exception) {
						newRank = -1;
					}
					SetPermLevel.setPermLevel(e1, target, newRank);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.");
				}
			}
			case "globalstats" -> {
				if(CheckPermLevel(e1) == 3) {
					GlobalStats.gobalStats(e1);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.");
				}
			}
			case "resetdaily" -> {
				if(CheckPermLevel(e1) == 3) {
					MoreDailyTime.moreDailyTime(e1);
				} else {
					e1.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.");
				}
			}
			case "custom" -> //noinspection RedundantLabeledSwitchRuleCodeBlock
			{
					/*File[] files = new File("Ling Ling Bot Data\\Economy Data").listFiles();
					assert files != null;
					for(File file : files) {
						JSONObject data;
						try(FileReader reader = new FileReader(file)) {
							data = (JSONObject) parser.parse(reader);
						} catch(Exception exception) {
							continue;
						}
						data.put("itemsSold", 0L);
						data.put("itemsBought", 0L);
						data.put("moneySpent", 0L);
						data.put("moneyEarned", 0L);
						data.put("taxPaid", 0L);
						try(FileWriter writer = new FileWriter(file)) {
							writer.write(data.toJSONString());
						} catch(Exception exception) {
							// nothing here lol
						}
					}
					/*List<Guild> list = e.getJDA().getGuilds();
					for(Guild g : list) {
						List<TextChannel> channels = g.getTextChannels();
						for(TextChannel channel : channels) {
							try {
								channel.sendMessage("""
										**IMPORTANT INFORMATION REGARDING LING LING FROM DEVELOPER**
										
										This message was manually coded to be sent to all servers the bot is in, in the first avaliable text channel.  This message may be deleted after it has been read and understood.
										**IGNORING THIS MESSAGE MAY RESULT IN AN INABILITY TO USE THE BOT**
										
										Starting on 31 August 2022, Discord will require all bots to use Slash Commands.  With the most recent bot update, I have done the work on my end in migrating, but server owners/admins have to do some stuff to enable Slash Commands on your server.  Because Discord is bad, they are **not** automatically enabling Slash Commands, so you will have to reauthorize the bot with the new permissions and scopes using `/invite` or `!invite`.  I know it's annoying, but it's the best solution.
										
										Along with that announcement, here is the latest info, which I am adding on because it is VERY important as to how the bot will run in the next two months.  If you have questions, run `/support` and join the support server to ask..
										
										It looks like there are no, or next to no, bugs in the beta.  So tonight I'm going to push the slash commands update onto the live bot, and the old message-based code will become depreciated (google it if you dont know what that means).  I will still keep the old code around until 31 August, after which Discord will enforce the message intent.
										
										**What will this mean?**
										The new slash commands will run alongside the old message-based bot.  They won't clash due to being two separate input types.
										
										**What about crashes?**
										The slash command version will have 24/7 uptime.  Do not expect the old prefix-based version to be up all the time.
										""");
								break;
							} catch(Exception exception) {
								exception.printStackTrace();
								//oof
							}
						}
					}*/
				e1.reply("No Update Here!");
			}
		}

		//HOURLY
		long time = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\hourly.txt"));
			time = Long.parseLong(reader.readLine());
			reader.close();
		} catch(Exception exception) {
			//nothing here lol
		}

		while(System.currentTimeMillis() > time) {
			HourlyIncome.hourlyIncome();

			//RESET COOLDOWNS BOUND TO 12AM UTC
			if(time % 86400000 == 0) {
				ResetDaily.resetDaily(e1);
			}

			//BANK SHIT
			if(time % 259200000 == 0) {
				InterestPenalty.interestPenalty();
				Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Loan penalties applied; Interest awarded!").queue();
			}

			time += 3600000;
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Hourly Incomes Sent!").queue();
			try {
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\hourly.txt")));
				writer.print(time);
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
	}
}