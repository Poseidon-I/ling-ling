package eventListeners;

import dev.*;
import economy.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.*;
import regular.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewReceiver extends ListenerAdapter {
	public static long CheckPermLevel(@NotNull SlashCommandInteractionEvent e) {
		if(e.getUser().getId().equals("619989388109152256") || e.getUser().getId().equals("488487157372157962")) {
			return 3;
		} else {
			JSONParser parser = new JSONParser();
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getUser().getId() + ".json")) {
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
				new InterestPenalty();
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
		//LUTHIER
		if(!e.getUser().isBot()) {
			JSONParser parser = new JSONParser();
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
				Luthier.luthier(e, (JSONObject) parser.parse(reader));
				reader.close();
			} catch(Exception exception) {
				//nothing here lol
			}
			
			// ALL COMMANDS
			String name = e.getName();
			switch(name) {
				// NON-ECON COMMANDS
				case "help" -> Help.help(e);
				case "faq" -> FAQ.faq(e);
				case "website" -> e.reply("Check out the website at <https://linglingdev.weebly.com/>").queue();
				case "support" -> e.reply("Join the support server at discord.gg/gNfPwa8").queue();
				case "guide" ->
						e.reply("The Beginner Guide can be found at <https://linglingdev.weebly.com/beginners-guide.html>, written by `bubblepotatochips#0498`").queue();
				case "kill" -> Kill.kill(e);
				case "joke" -> Joke.joke(e);
				case "poll" -> Poll.poll(e);
				case "emojify" -> Emojify.emojify(e);
				case "invite" ->
						e.reply("You can add the bot to your server using the below link:\n<https://discord.com/api/oauth2/authorize?client_id=733409243222507670&permissions=67398720&scope=bot%20applications.commands>").queue();
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
						""").queue();
				case "rules" -> e.reply("""
						```fix
						██████╗  ██████╗ ████████╗    ██████╗ ██╗   ██╗██╗     ███████╗███████╗
						██╔══██╗██╔═══██╗╚══██╔══╝    ██╔══██╗██║   ██║██║     ██╔════╝██╔════╝
						██████╔╝██║   ██║   ██║       ██████╔╝██║   ██║██║     █████╗  ███████╗
						██╔══██╗██║   ██║   ██║       ██╔══██╗██║   ██║██║     ██╔══╝  ╚════██║
						██████╔╝╚██████╔╝   ██║       ██║  ██║╚██████╔╝███████╗███████╗███████║
						╚═════╝  ╚═════╝    ╚═╝       ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚══════╝╚══════╝
						```
						1. Do not spam commands, excessively ping users, or send messages to trigger luthier or take any sort of action that may cause the bot to crash.
						2. Do not abuse bugs or exploits.  If a bug/exploit is found, **IMMEDIATELY** report it to `Stradivarius Violin#6156`.
						3. Do not overexcessively or inappropriately use `!checkdm` or `!kill`.  These were written to poke fun at others, not to annoy/hurt them.
						4. Do not ask mods/admins/devs for information that can easily be found by reading the patch notes, using `!help`, or using `!faq`.
						5. **All bot mods/admins reserve the right to punish users when breaking bot rules is involved.**  Attempting to stop bot mods/admins from using bot moderation commands will result in your punishment as well.
						
						**Privacy Notice** - Ling Ling **does not store any data** except for data explicitly related to the economy system, which are variables all generated by the bot itself, with the exception of IDs provided by Discord (eg. Server ID for Luthier & Prefixes, User ID for saves), which are necessary to allow the bot to distinguish between different users and servers.
						
						tl;dr: ling ling doesn't store any data except your discord user id and stuff it makes by itself so that it works properly
						""").queue();
				case "botstats" -> {
					int serverCount = e.getJDA().getGuilds().size();
					File[] files = new File("Ling Ling Bot Data\\Economy Data").listFiles();
					assert files != null;
					e.reply("Servers: " + serverCount + "\nUsers: " + files.length).queue();
				}
				case "settings" -> UserSettings.userSettings(e);
				case "link" -> Link.link(e);
				case "market" -> Market.market(e);
				case "craft" -> Craft.craft(e);
				
				// ECON COMMANDS
				case "start" -> Start.start(e, e.getUser().getId(), false);
				case "upgrades", "up", "u", "shop" -> Upgrades.upgrades(e);
				case "buy" -> Buy.buy(e);
				case "cooldowns", "c" -> Cooldowns.cooldowns(e);
				case "use" -> Use.use(e);
				case "scales", "s" -> Scales.scales(e);
				case "practice", "p" -> Practise.practise(e);
				case "rehearse", "r" -> Rehearse.rehearse(e);
				case "perform", "pf" -> Perform.perform(e);
				case "daily", "d" -> Daily.daily(e);
				case "teach", "t" -> Teach.teach(e);
				case "rob" -> Rob.rob(e);
				case "gamble", "bet" -> Gamble.gamble(e);
				case "inventory", "inv" -> Inventory.inventory(e);
				case "profile", "balance", "bal", "b" -> Balance.balance(e);
				case "stats" -> Stats.stats(e);
				case "claim" -> Vote.vote(e);
				case "gift" -> Gift.gift(e);
				case "deposit", "dep" -> Deposit.deposit(e);
				case "withdraw", "with" -> Withdraw.withdraw(e);
				case "loan" -> Loan.loan(e);
				case "payloan" -> PayLoan.payLoan(e);
				case "answer" -> {
					parser = new JSONParser();
					try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
						Luthier.luthier(e, (JSONObject) parser.parse(reader));
						reader.close();
					} catch(Exception exception) {
						//nothing here lol
					}
				}
				case "leaderboard", "lb" -> {
					JSONObject data = LoadData.loadData(e);
					try {
						switch(Objects.requireNonNull(e.getOption("leaderboard")).getAsString()) {
							case "violins" ->
									Leaderboard.leaderboard(Emoji.VIOLINS, "Richest Users", e, "violins", (long) data.get("violins"), (String) data.get("color"));
							case "streak" ->
									Leaderboard.leaderboard(":calendar:", "Longest Daily Streaks", e, "streak", (long) data.get("streak"), (String) data.get("color"));
							case "medals" -> {
								long num = (long) data.get("thirdPlace") + 2 * (long) data.get("secondPlace") + 3 * (long) data.get("firstPlace");
								Leaderboard.leaderboard(Emoji.MEDALS, "Most Worthy Users", e, "medals", num, (String) data.get("color"));
							}
							case "income" ->
									Leaderboard.leaderboard(Emoji.VIOLINS + "/hour", "Highest Hourly Incomes", e, "income", (long) data.get("income"), (String) data.get("color"));
							case "winnings" ->
									Leaderboard.leaderboard(":moneybag:", "Best Gamblers", e, "winnings", (long) data.get("winnings"), (String) data.get("color"));
							case "million" ->
									Leaderboard.leaderboard(":tickets:", "Luckiest Users", e, "millions", (long) data.get("millions"), (String) data.get("color"));
							case "rob" ->
									Leaderboard.leaderboard(Emoji.VIOLINS, "Most Heartless Users", e, "robbed", (long) data.get("robbed"), (String) data.get("color"));
							case "scales" ->
									Leaderboard.leaderboard(":scales:", "Most Scales Played", e, "scalesPlayed", (long) data.get("scalesPlayed"), (String) data.get("color"));
							case "hours" ->
									Leaderboard.leaderboard(":clock2:", "Most Hours Practised", e, "hoursPractised", (long) ((double) data.get("hoursPractised")), (String) data.get("color"));
							case "rehearsals" ->
									Leaderboard.leaderboard(":musical_score:", "Most Rehearsals Attended", e, "rehearsals", (long) data.get("rehearsals"), (String) data.get("color"));
							case "performances" ->
									Leaderboard.leaderboard(":microphone:", "Most Performances", e, "performances", (long) data.get("performances"), (String) data.get("color"));
							case "earnings" ->
									Leaderboard.leaderboard(Emoji.VIOLINS, "Most Hardworking Users", e, "earnings", (long) data.get("earnings"), (String) data.get("color"));
							case "teach" ->
									Leaderboard.leaderboard(":teacher:", "Most Influential Users", e, "hoursTaught", (long) ((double) data.get("hoursTaught")), (String) data.get("color"));
							case "luthier" ->
									Leaderboard.leaderboard(":question:", "Best Unscramblers", e, "luthiers", (long) data.get("luthiers"), (String) data.get("color"));
							case "gift" ->
									Leaderboard.leaderboard(Emoji.GIFT_BOX, "Most Generous Users", e, "giftsGiven", (long) data.get("giftsGiven"), (String) data.get("color"));
							case "free" ->
									Leaderboard.leaderboard(":money_mouth:", "Most Stingy Users", e, "votes", (long) data.get("votes"), (String) data.get("color"));
							case "rng" ->
									Leaderboard.leaderboard(":slot_machine:", "Truly Luckiest Users", e, "RNGesusWeight", (long) data.get("RNGesusWeight"), (String) data.get("color"));
							case "magicfind" ->
									Leaderboard.leaderboard(":star:", "Statistically Luckiest Users", e, "magicFind", (long) data.get("magicFind"), (String) data.get("color"));
							default ->
									e.reply("You must provide a valid leaderboard type.  Valid types...\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`free`: Users that have claimed the most Free Boxes\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find").queue();
						}
					} catch(Exception exception) {
						e.reply("**__Leaderboard Types__**\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles\n`gift`: Users that have given the most Gifts\n`free`: Users that have claimed the most Free Boxes\n`rng`: Users with highest RNGesus Weight\n`magicfind`: Users with the most Magic Find").queue();
					}
				}
				
				// DEV COMMANDS
				case "give" -> {
					if(CheckPermLevel(e) >= 1) {
						Give.give(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "warn" -> {
					if(CheckPermLevel(e) >= 1) {
						Warn.warn(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "resetsave" -> {
					if(CheckPermLevel(e) >= 1) {
						ResetSave.resetSave(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "ban" -> {
					if(CheckPermLevel(e) >= 2) {
						Ban.ban(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "unban" -> {
					if(CheckPermLevel(e) >= 2) {
						Unban.unban(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "luthier" -> {
					if(CheckPermLevel(e) >= 2) {
						AdminLuthier.adminLuthier(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "resetincomes" -> {
					if(CheckPermLevel(e) >= 2) {
						ResetIncomes.resetIncomes(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "updateluthierchance" -> {
					if(CheckPermLevel(e) >= 2) {
						UpdateLuthierChance.updateLuthierChance(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "updateusers" -> {
					if(CheckPermLevel(e) == 3) {
						UpdateUsers.updateUsers(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "forcestop" -> {
					if(CheckPermLevel(e) == 3 && Objects.requireNonNull(e.getOption("PASSWORD")).getAsString().equals("@#$%FUCK")) {
						e.reply("Forcing bot to stop...");
						File file = new File("Ling Ling Bot Data\\pid.txt");
						file.delete();
						e.getJDA().shutdownNow();
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command, or you entered the wrong Password.").queue();
					}
				}
				case "updateroles" -> {
					if(CheckPermLevel(e) == 3) {
						UpdateRoles.updateRoles(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
					}
				}
				case "setpermlevel" -> {
					if(CheckPermLevel(e) == 3) {
						SetPermLevel.setPermLevel(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.").queue();
					}
				}
				case "globalstats" -> {
					if(CheckPermLevel(e) == 3) {
						GlobalStats.gobalStats(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.").queue();
					}
				}
				case "resetdaily" -> {
					if(CheckPermLevel(e) == 3) {
						MoreDailyTime.moreDailyTime(e);
					} else {
						e.reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.").queue();
					}
				}
				case "custom" -> {/*
					File[] files = new File("Ling Ling Bot Data\\Economy Data").listFiles();
					assert files != null;
					for(File file : files) {
						JSONObject data;
						try(FileReader reader = new FileReader(file)) {
							data = (JSONObject) parser.parse(reader);
						} catch(Exception exception) {
							continue;
						}
						data.remove("mfLLBox");
						data.remove("mfCrazyBox");
						data.remove("mfRNGesusBox");
						data.put("mcIGN", "");
						data.put("rosinExpire", System.currentTimeMillis() + 612000000);
						data.put("stringsExpire", System.currentTimeMillis() + 612000000);
						data.put("bowHairExpire", System.currentTimeMillis() + 612000000);
						data.put("serviceExpire", System.currentTimeMillis() + 612000000);
						data.put("grains", 0L);
						data.put("plastic", 0L);
						data.put("water", 0L);
						data.put("teaBase", 0L);
						data.put("wood", 0L);
						data.put("pineSap", 0L);
						data.put("steel", 0L);
						data.put("horseHair", 0L);
						data.put("rosin", 0L);
						data.put("string", 0L);
						data.put("bowHair", 0L);
						data.put("violinService", 0L);
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
										""").queue();
								break;
							} catch(Exception exception) {
								exception.printStackTrace();
								//oof
							}
						}
					}*/
					e.reply("No Update Here!").queue();
				}
			}
		}
	}
}