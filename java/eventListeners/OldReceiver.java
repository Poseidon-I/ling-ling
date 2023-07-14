package eventListeners;

import dev.*;
import economy.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
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

public class OldReceiver extends ListenerAdapter {
	public static long CheckPermLevel(GenericDiscordEvent e) {
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

	public static void runLingLingCommand(GenericDiscordEvent e, String[] message) {
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

		if(message[0].equals("<@733409243222507670>") || message[0].equals("<@772582345944334356>")) {
			// Apply Vote Rewards
			if(e.getChannel().getId().equals("863135059712409632")) {
				String id = message[1];
				JSONParser parser = new JSONParser();
				JSONObject data;
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
					data = (JSONObject) parser.parse(reader);
					reader.close();
				} catch(Exception exception) {
					Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().complete().sendMessage("Thank you for voting for Ling Ling!  Unfortunately, I was unable to reward you because you did not have a save.  Run `/start` in any server to get one!").queue();
					return;
				}
				if((boolean) data.get("banned")) {
					Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().complete().sendMessage("Thank you for voting for Ling Ling!  Unfortunately, you are currently banned from using the economy, but I am nice and have rewarded you anyway in the unlikely case you do get unbanned.").queue();
				}
				data.replace("voteBox", (long) data.get("voteBox") + 1);
				try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
					writer.write(data.toJSONString());
					writer.close();
				} catch(Exception exception) {
					return;
				}
				Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().complete().sendMessage("Thank you for voting for Ling Ling!  You have received 1x Free Box!").queue();
			}

			// ALL COMMANDS
			String commandName;
			try {
				commandName = message[1];
			} catch(Exception exception) {
				commandName = "";
			}
			switch(commandName) {
				// NON-ECON COMMANDS
				case "help" -> {
					String page;
					try {
						page = message[2];
					} catch(Exception exception) {
						page = "";
					}
					Help.help(e, page);
				}
				case "faq" -> {
					String page;
					try {
						page = message[2];
					} catch(Exception exception) {
						page = "";
					}
					FAQ.faq(e, page);
				}
				case "website" ->
						e.reply("Weebly is hard to use, so there is currently no website.  Maybe in the future I'll get someone to make it, but for now, everything is on Google Sheets or Google Docs.");
				case "support" -> e.reply("Join the support server at discord.gg/gNfPwa8");
				case "vote" ->
						e.reply("Vote here to earn an extra Free Box!\n<https://top.gg/bot/733409243222507670/vote>");
				case "guide" ->
						e.reply("The Beginner Guide can be found at <https://docs.google.com/document/d/1Oo8m8XuGsIOyMzJhllUN9SpOJI8hSUeQt5RbyPY9qMI/edit?usp=sharing>, written by `bubblepotatochips#0498`");
				case "kill" -> {
					StringBuilder target = new StringBuilder();
					try {
						for(int i = 2; i < message.length; i++) {
							target.append(message[i]).append(" ");
						}
						target.deleteCharAt(target.length() - 1);
					} catch(Exception exception) {
						target = new StringBuilder("Nobody");
					}
					Kill.kill(e, target.toString());
				}
				case "joke" -> Joke.joke(e);
				case "poll" -> {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					String message1 = e.getMessage().getContentRaw();
					StringBuilder title = new StringBuilder();
					String choices;
					int current;
					try {
						current = message1.indexOf('\"') + 1;
						while(message1.charAt(current) != '\"') {
							title.append(message1.charAt(current));
							current++;
						}
					} catch(Exception exception) {
						title = new StringBuilder("No Title");
						current = 5;
					}
					current += 2;
					try {
						choices = message1.substring(current);
					} catch(Exception exception) {
						choices = "";
					}
					Poll.poll(e, title.toString(), choices);
				}
				case "emojify" -> {
					StringBuilder message1 = new StringBuilder();
					try {
						for(int i = 2; i < message.length; i++) {
							message1.append(message[i]).append(' ');
						}
						message1.deleteCharAt(message1.length());
					} catch(Exception exception) {
						message1 = new StringBuilder();
					}
					Emojify.emojify(e, String.valueOf(message1));
				}
				case "invite" ->
						e.reply("You can add the bot to your server using the below link:\n<https://discord.com/api/oauth2/authorize?client_id=733409243222507670&permissions=67398720&scope=bot%20applications.commands>");
				case "staff" -> e.reply("""
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
				case "rules" -> e.reply("""
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
					e.reply("Servers: " + serverCount + "\nUsers: " + files.length);
				}
				case "settings" -> {
					String option;
					String newValue;
					try {
						option = message[2];
					} catch(Exception exception) {
						option = "none";
					}
					try {
						newValue = message[3];
					} catch(Exception exception) {
						newValue = "";
					}
					UserSettings.userSettings(e, option, newValue);
				}
				case "market" -> {
					String action;
					String item;
					long amount;
					long price;
					try {
						action = message[2];
					} catch(Exception exception) {
						action = "";
					}
					try {
						item = message[3];
					} catch(Exception exception) {
						item = "none";
					}
					try {
						amount = Long.parseLong(message[4]);
					} catch(Exception exception) {
						amount = 1;
					}
					try {
						price = Long.parseLong(message[5]);
					} catch(Exception exception) {
						price = -1;
					}

					Market.market(e, item, action, amount, price);
				}
				case "craft" -> {
					long craftAmount;
					String name;
					try {
						name = message[2];
					} catch(Exception exception) {
						name = "";
					}
					try {
						craftAmount = Long.parseLong(message[3]);
					} catch(Exception exception) {
						craftAmount = -1;
					}
					Craft.craft(e, craftAmount, name);
				}

				// ECON COMMANDS
				case "start" -> Start.start(e, e.getAuthor().getId(), false);
				case "upgrades", "up", "u", "shop" -> {
					int page;
					try {
						page = Integer.parseInt(message[2]);
					} catch(Exception exception) {
						page = -1;
					}
					Upgrades.upgrades(e, page);
				}
				case "buy" -> {
					String item;
					try {
						item = message[2];
					} catch(Exception exception) {
						item = "";
					}
					Buy.buy(e, item);
				}
				case "cooldowns", "c" -> Cooldowns.cooldowns(e);
				case "use" -> {
					String item;
					String amount;
					try {
						item = message[2];
					} catch(Exception exception) {
						item = "";
					}
					try {
						amount = message[3];
					} catch(Exception exception) {
						amount = "1";
					}
					Use.use(e, item, amount);
				}
				case "scales", "s" -> Scales.scales(e);
				case "practice", "p" -> Practise.practise(e);
				case "rehearse", "r" -> Rehearse.rehearse(e);
				case "perform", "pf" -> Perform.perform(e);
				case "daily", "d" -> Daily.daily(e);
				case "teach", "t" -> Teach.teach(e);
				case "gamble", "bet" -> {
					String game;
					String amount;
					try {
						game = message[2];
					} catch(Exception exception) {
						game = "";
					}
					try {
						amount = message[3];
					} catch(Exception exception) {
						amount = "-1";
					}
					Gamble.gamble(e, game, amount);
				}
				case "rob" -> {
					String user;
					try {
						user = message[2];
					} catch(Exception exception) {
						user = e.getAuthor().getId();
					}
					Rob.rob(e, user);
				}
				case "inventory", "inv" -> {
					int page;
					String user;
					try {
						page = Integer.parseInt(message[2]);
					} catch(Exception exception) {
						page = -1;
					}
					try {
						user = message[3];
					} catch(Exception exception) {
						user = e.getAuthor().getId();
					}
					Inventory.inventory(e, user, page);
				}
				case "profile", "balance", "bal", "b" -> {
					String user;
					try {
						user = message[2];
					} catch(Exception exception) {
						user = e.getAuthor().getId();
					}
					Balance.balance(e, user);
				}
				case "stats" -> {
					String user;
					try {
						user = message[2];
					} catch(Exception exception) {
						user = e.getAuthor().getId();
					}
					Stats.stats(e, user);
				}
				case "claim" -> Vote.vote(e);
				case "gift" -> {
					String user;
					try {
						user = message[2];
					} catch(Exception exception) {
						user = e.getAuthor().getId();
					}
					Gift.gift(e, user);
				}
				case "deposit", "dep" -> {
					String amount;
					try {
						amount = message[2];
					} catch(Exception exception) {
						amount = "";
					}
					Deposit.deposit(e, amount);
				}
				case "withdraw", "with" -> {
					String amount;
					try {
						amount = message[2];
					} catch(Exception exception) {
						amount = "";
					}
					Withdraw.withdraw(e, amount);
				}
				case "loan" -> {
					String amount;
					try {
						amount = message[2];
					} catch(Exception exception) {
						amount = "";
					}
					Loan.loan(e, amount);
				}
				case "payloan" -> {
					String amount;
					try {
						amount = message[2];
					} catch(Exception exception) {
						amount = "";
					}
					PayLoan.payLoan(e, amount);
				}
				case "answer" -> {
					String answer;
					try {
						answer = message[2];
					} catch(Exception exception) {
						answer = "none";
					}
					JSONParser parser = new JSONParser();
					try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
						Luthier.luthier(e, (JSONObject) parser.parse(reader), answer);
						reader.close();
					} catch(Exception exception) {
						//nothing here lol
					}
				}
				case "leaderboard", "lb" -> {
					JSONObject data = LoadData.loadData(e);
					try {
						switch(message[2]) {
							case "violins" ->
									Leaderboard.leaderboard(e, Emoji.VIOLINS, "Richest Users", "violins", (long) data.get("violins"), (String) data.get("color"));
							case "streak" ->
									Leaderboard.leaderboard(e, ":calendar:", "Longest Daily Streaks", "streak", (long) data.get("streak"), (String) data.get("color"));
							case "medals" ->
									Leaderboard.leaderboard(e, Emoji.MEDALS, "Most Worthy Users", "medals", (long) data.get("medals"), (String) data.get("color"));
							case "income" ->
									Leaderboard.leaderboard(e, Emoji.VIOLINS + "/hour", "Highest Hourly Incomes", "income", (long) data.get("income"), (String) data.get("color"));
							case "winnings" ->
									Leaderboard.leaderboard(e, ":moneybag:", "Best Gamblers", "winnings", (long) data.get("winnings"), (String) data.get("color"));
							case "million" ->
									Leaderboard.leaderboard(e, ":tickets:", "Luckiest Users", "millions", (long) data.get("millions"), (String) data.get("color"));
							case "rob" ->
									Leaderboard.leaderboard(e, Emoji.VIOLINS, "Most Heartless Users", "robbed", (long) data.get("robbed"), (String) data.get("color"));
							case "scales" ->
									Leaderboard.leaderboard(e, ":scales:", "Most Scales Played", "scalesPlayed", (long) data.get("scalesPlayed"), (String) data.get("color"));
							case "hours" ->
									Leaderboard.leaderboard(e, ":clock2:", "Most Hours Practised", "hoursPractised", (long) ((double) data.get("hoursPractised")), (String) data.get("color"));
							case "rehearsals" ->
									Leaderboard.leaderboard(e, ":musical_score:", "Most Rehearsals Attended", "rehearsals", (long) data.get("rehearsals"), (String) data.get("color"));
							case "performances" ->
									Leaderboard.leaderboard(e, ":microphone:", "Most Performances", "performances", (long) data.get("performances"), (String) data.get("color"));
							case "earnings" ->
									Leaderboard.leaderboard(e, Emoji.VIOLINS, "Most Hardworking Users", "earnings", (long) data.get("earnings"), (String) data.get("color"));
							case "teach" ->
									Leaderboard.leaderboard(e, ":teacher:", "Most Influential Users", "hoursTaught", (long) ((double) data.get("hoursTaught")), (String) data.get("color"));
							case "luthier" ->
									Leaderboard.leaderboard(e, ":question:", "Best Unscramblers", "luthiers", (long) data.get("luthiers"), (String) data.get("color"));
							case "gift" ->
									Leaderboard.leaderboard(e, Emoji.GIFT_BOX, "Most Generous Users", "giftsGiven", (long) data.get("giftsGiven"), (String) data.get("color"));
							case "free" ->
									Leaderboard.leaderboard(e, ":money_mouth:", "Most Stingy Users", "votes", (long) data.get("votes"), (String) data.get("color"));
							case "rng" ->
									Leaderboard.leaderboard(e, ":slot_machine:", "Truly Luckiest Users", "RNGesusWeight", (long) data.get("RNGesusWeight"), (String) data.get("color"));
							case "magicfind" ->
									Leaderboard.leaderboard(e, ":star:", "Statistically Luckiest Users", "magicFind", (long) data.get("magicFind"), (String) data.get("color"));
							case "moneyearned" ->
									Leaderboard.leaderboard(e, Emoji.VIOLINS, "Best Entrepreneurs", "moneyEarned", (long) data.get("moneyEarned"), (String) data.get("color"));
							case "moneyspent" ->
									Leaderboard.leaderboard(e, Emoji.VIOLINS, "Biggest Spenders", "moneySpent", (long) data.get("moneySpent"), (String) data.get("color"));
							default ->
									e.reply("You must provide a valid leaderboard type.  Valid types...\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`free`: Users that have claimed the most Free Boxes\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find\n`moneyearned`: Users who have earned the most money from Market\n`moneyspent`: Users who have spent the most money on Market.");
						}
					} catch(Exception exception) {
						e.reply("**__Leaderboard Types__**\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`free`: Users that have claimed the most Free Boxes\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find\n`moneyearned`: Users who have earned the most money from Market\n`moneyspent`: Users who have spent the most money on Market.");
					}
				}

				// DEV COMMANDS
				case "give" -> {
					if(CheckPermLevel(e) >= 1) {
						String receiver;
						long add;
						String item;
						try {
							receiver = message[2];
						} catch(Exception exception) {
							receiver = "";
						}
						try {
							add = Long.parseLong(message[3]);
						} catch(NullPointerException exception) {
							add = -1;
						} catch(Exception exception) {
							add = -2;
						}
						try {
							item = message[4];
						} catch(Exception exception) {
							item = "";
						}
						Give.give(e, receiver, add, item);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "warn" -> {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					if(CheckPermLevel(e) >= 1) {
						String idToModerate;
						String reason;
						try {
							idToModerate = message[2];
						} catch(Exception exception) {
							idToModerate = "";
						}
						try {
							reason = message[3];
						} catch(Exception exception) {
							reason = "None";
						}
						Warn.warn(e, idToModerate, reason);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "resetsave" -> {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					if(CheckPermLevel(e) >= 1) {
						String idToModerate;
						String reason;
						try {
							idToModerate = message[2];
						} catch(Exception exception) {
							idToModerate = "";
						}
						try {
							reason = message[3];
						} catch(Exception exception) {
							reason = "None";
						}
						ResetSave.resetSave(e, idToModerate, reason);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "ban" -> {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					if(CheckPermLevel(e) >= 2) {
						String idToModerate;
						String reason;
						try {
							idToModerate = message[2];
						} catch(Exception exception) {
							idToModerate = "";
						}
						try {
							reason = message[3];
						} catch(Exception exception) {
							reason = "None";
						}
						Ban.ban(e, idToModerate, reason);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "unban" -> {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					if(CheckPermLevel(e) >= 2) {
						String idToModerate;
						String reason;
						Boolean reset;
						try {
							idToModerate = message[2];
						} catch(Exception exception) {
							idToModerate = "";
						}
						try {
							reason = message[3];
						} catch(Exception exception) {
							reason = "None";
						}
						try {
							reset = Boolean.valueOf(message[4]);
						} catch(Exception exception) {
							reset = null;
						}
						Unban.unban(e, idToModerate, reason, reset);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "luthier" -> {
					if(CheckPermLevel(e) >= 2) {
						String actionType;
						String editOption;
						String newValue;
						try {
							actionType = message[2];
						} catch(Exception exception) {
							actionType = "";
						}
						try {
							editOption = message[3];
						} catch(Exception exception) {
							editOption = "";
						}
						try {
							newValue = message[4];
						} catch(Exception exception) {
							newValue = "";
						}
						AdminLuthier.adminLuthier(e, actionType, editOption, newValue);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "resetincomes" -> {
					if(CheckPermLevel(e) >= 2) {
						e.reply(ResetIncomes.resetIncomes());
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "updateluthierchance" -> {
					if(CheckPermLevel(e) >= 2) {
						UpdateLuthierChance.updateLuthierChance(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "updateusers" -> {
					if(CheckPermLevel(e) == 3) {
						String dataType;
						String name;
						String value;
						try {
							dataType = message[2];
						} catch(Exception exception) {
							dataType = "";
						}
						try {
							name = message[3];
						} catch(Exception exception) {
							name = "";
						}
						try {
							value = message[4];
						} catch(Exception exception) {
							value = "";
						}
						UpdateUsers.updateUsers(e, dataType, name, value);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "forcestop" -> {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					if(CheckPermLevel(e) == 3 && Objects.requireNonNull(message[2]).equals("@#$%FUCK")) {
						e.reply("Forcing bot to stop...");
						File file = new File("Ling Ling Bot Data\\pid.txt");
						file.delete();
						e.getJDA().shutdownNow();
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command, or you entered the wrong Password.");
					}
				}
				case "updateroles" -> {
					if(CheckPermLevel(e) == 3) {
						UpdateRoles.updateRoles(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.");
					}
				}
				case "setpermlevel" -> {
					if(CheckPermLevel(e) == 3) {
						String target;
						int newRank;
						try {
							target = message[2];
						} catch(Exception exception) {
							target = "";
						}
						try {
							newRank = Integer.parseInt(message[3]);
						} catch(Exception exception) {
							newRank = -1;
						}
						SetPermLevel.setPermLevel(e, target, newRank);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.");
					}
				}
				case "globalstats" -> {
					if(CheckPermLevel(e) == 3) {
						GlobalStats.gobalStats(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.");
					}
				}
				case "resetdaily" -> {
					if(CheckPermLevel(e) == 3) {
						MoreDailyTime.moreDailyTime(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.");
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
					e.reply("No Update Here!");
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
					ResetDaily.resetDaily(e);
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

	@Override
	public void onMessageReceived(@NotNull MessageReceivedEvent e) {
		GenericDiscordEvent e1 = new GenericDiscordEvent(e);

		if(e1.getMessage().getContentRaw().toLowerCase().contains("bad bot")) {
			e1.reply("sowwy strad :(");
		} else if(e1.getMessage().getContentRaw().toLowerCase().contains("good bot")) {
			e1.reply("senkyoo strad :)");
		} else if(e1.getMessage().getContentRaw().toLowerCase().contains("right bot?")) {
			e1.reply("yes master");
		}

		//LUTHIER
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
			Luthier.luthier(e1, (JSONObject) parser.parse(reader), "");
			reader.close();
		} catch(Exception exception) {
			//nothing here lol
		}

		String[] message = e.getMessage().getContentRaw().toLowerCase().split(" ");
		runLingLingCommand(e1, message);
	}
}