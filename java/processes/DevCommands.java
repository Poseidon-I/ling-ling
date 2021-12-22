package processes;

import dev.*;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class DevCommands {
	public static int CheckPermLevel(GuildMessageReceivedEvent e, boolean isDev) {
		if(isDev) {
			return 3;
		} else {
			JSONParser parser = new JSONParser();
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".json")) {
				JSONObject data = (JSONObject) parser.parse(reader);
				reader.close();
				return (int) data.get("perms");
			} catch(Exception exception) {
				return 0;
			}
		}
	}
	
	public DevCommands(GuildMessageReceivedEvent e, boolean isDev) {
		//1 - mods, 2 - admins, 3 - devs
		String[] message = e.getMessage().getContentRaw().split(" ");
		switch(message[0]) {
			case "!give" -> {
				if(CheckPermLevel(e, isDev) >= 1) {
					new Give(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!warn" -> {
				if(CheckPermLevel(e, isDev) >= 1) {
					new Warn(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!resetsave" -> {
				if(CheckPermLevel(e, isDev) >= 1) {
					new ResetSave(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!ban" -> {
				if(CheckPermLevel(e, isDev) >= 2) {
					new Ban(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!unban" -> {
				if(CheckPermLevel(e, isDev) >= 2) {
					new Unban(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!luthier" -> {
				if(CheckPermLevel(e, isDev) >= 2) {
					new AdminLuthier(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!resetincomes" -> {
				if(CheckPermLevel(e, isDev) >= 2) {
					new ResetIncomes(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			/*case "!updateusers" -> {
				if(CheckPermLevel(e, isDev) == 3) {
					new UpdateUsers(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}*/
			case "!forcestoπ" -> {
				if(CheckPermLevel(e, isDev) == 3) {
					e.getChannel().sendMessage("Forcing bot to stop...");
					e.getJDA().shutdownNow();
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!updateroles" -> {
				if(CheckPermLevel(e, isDev) == 3) {
					new UpdateRoles(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!setpermlevel" -> {
				if(CheckPermLevel(e, isDev) == 3) {
					new SetPermLevel(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.").queue();
				}
			}
			case "!resetdaily" -> {
				if(CheckPermLevel(e, isDev) == 3) {
					new MoreDailyTime(e);
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.").queue();
				}
			}
			case "!status" -> {
				if(CheckPermLevel(e, isDev) == 3) {
					e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
					switch(message[1]) {
						case "online" -> e.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
						case "away" -> e.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
						case "dnd" -> e.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
					}
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!activity" -> {
				if(CheckPermLevel(e, isDev) == 3) {
					e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
					StringBuilder activity = new StringBuilder();
					for(int i = 2; i < message.length; i++) {
						activity.append(message[i]).append(" ");
					}
					switch(message[1]) {
						case "playing" -> e.getJDA().getPresence().setActivity(Activity.playing(activity.toString()));
						case "listening" -> e.getJDA().getPresence().setActivity(Activity.listening(activity.toString()));
						case "watching" -> e.getJDA().getPresence().setActivity(Activity.watching(activity.toString()));
						case "streaming" -> e.getJDA().getPresence().setActivity(Activity.streaming(activity.toString(), "https://www.youtube.com/channel/UCfqRDWapZD42yFcIlj15oRw"));
						case "nothing" -> e.getJDA().getPresence().setActivity(null);
					}
				} else {
					e.getChannel().sendMessage(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").queue();
				}
			}
			case "!custom" -> { // not finished, and it's one-time use to convert everything
				File directory = new File("Ling Ling Bot Data\\Economy Data");
				File[] files = directory.listFiles();
				assert files != null;
				for(File file : files) {
					String[] oldData;
					String[] bankData;
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
						oldData = reader.readLine().split(" ");
						reader.close();
						reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\Bank Data\\" + file.getName()));
						bankData = reader.readLine().split(" ");
						reader.close();
					} catch(Exception exception) {
						e.getChannel().sendMessage("Shit!\n" + file.getAbsolutePath()).queue();
						continue;
					}
					
					JSONObject data = new JSONObject();
					data.put("violins", Long.parseLong(oldData[0]));
					data.put("bank", 0);
					data.put("loan", 0);
					data.put("medals", 4095);
					data.put("thirdPlace", 0);
					data.put("secondPlace", 0);
					data.put("firstPlace", 0);
					
					data.put("rice", 10);
					data.put("tea", 10);
					data.put("blessings", 10);
					data.put("voteBox", 10);
					data.put("giftBox", 10);
					data.put("kits", 10);
					data.put("linglingBox", 10);
					data.put("crazyBox", 10);
					
					data.put("income", 0);
					data.put("streak", 0);
					data.put("earnings", 0);
					data.put("winnings", 0);
					data.put("millions", 0);
					data.put("robbed", 0);
					data.put("lostToRob", 0);
					data.put("scalesPlayed", 0);
					data.put("hoursPractised", 0.0);
					data.put("rehearsals", 0);
					data.put("performances", 0);
					data.put("hoursTaught", 0.0);
					data.put("maxStreak", 0);
					data.put("votes", 0);
					data.put("luthiers", 0);
					data.put("giftsGiven", 0);
					data.put("giftsReceived", 0);
					data.put("interestEarned", 0);
					data.put("penaltiesIncurred", 0);
					
					data.put("betCD", 0);
					data.put("scaleCD", 0);
					data.put("practiceCD", 0);
					data.put("teachCD", 0);
					data.put("rehearseCD", 0);
					data.put("performCD", 0);
					data.put("robCD", 0);
					data.put("voteCD", 0);
					data.put("hadDailyToday", false);
					data.put("hadGiftToday", false);
					
					data.put("efficiency", 0);
					data.put("luck", 0);
					data.put("sophistication", 0);
					data.put("insurance", false);
					data.put("timeCrunch", false);
					
					data.put("violinQuality", 0);
					data.put("skills", 0);
					data.put("lessonQuality", 0);
					data.put("stringQuality", 0);
					data.put("bowQuality", 0);
					data.put("math", false);
					
					data.put("orchestra", false);
					
					data.put("piccolo", false);
					data.put("flute", 0);
					data.put("oboe", 0);
					data.put("clarinet", 0);
					data.put("bassoon", 0);
					data.put("contraBassoon", false);
					
					data.put("horn", 0);
					data.put("trumpet", 0);
					data.put("trombone", 0);
					data.put("tuba", 0);
					data.put("timpani", 0);
					data.put("percussion", 0);
					
					data.put("violin1", 1);
					data.put("violin2", 1);
					data.put("cello", 0);
					data.put("doubleBass", 0);
					data.put("piano", 0);
					data.put("harp", false);
					
					data.put("soprano", 0);
					data.put("alto", 0);
					data.put("tenor", 0);
					data.put("bass", 0);
					data.put("soloist", 0);
					
					data.put("hall", 0);
					data.put("conductor", 0);
					data.put("tickets", 0);
					data.put("advertising", 0);
					
					data.put("certificate", false);
					data.put("students", 0);
					data.put("lessonCharge", 0);
					data.put("training", 0);
					data.put("studio", false);
					data.put("longerLessons", false);
					
					data.put("moreIncome", 0);
					data.put("moreCommandIncome", 0);
					data.put("moreMulti", 0);
					data.put("moreRob", 0);
					data.put("shield", false);
					data.put("duplicator", false);
					
					data.put("storage", 0);
					data.put("moreInterest", false);
					data.put("lessPenalty", false);
					
					data.put("banned", false);
					data.put("perms", 0);
					data.put("medalToday", false);
					data.put("retainDaily", true);
					data.put("isBooster", false);
					data.put("serverLevel", 1.0);
					
					try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + file.getName().substring(0, file.getName().length() - 4) + ".json")) {
						writer.write(data.toJSONString());
						writer.close();
					} catch(Exception exception) {
						//nothing here lol
					}
				}
				e.getChannel().sendMessage("Custom command completed!").queue();
				
				/*
				Economy Data Folder

    
				ex. 100 16000000000 50 16000000000 25 16000000000 15 16000000000 16000000000 true 0 0 10000 5 5 5 5 5 true true true 1 1 1 1 true 1 1 1 1 1 1 20 20 15 5 2 true 20 20 20 20 4 5 5 20 5 0 true false true 0 3 2 1 10 2 2 2 1 true false 0 0 16000000000 0 0 0 0 0 0 0.0 0 0 0 0 0 0 0 true 0 0 0 0 true true 1 1 1 1 16000000000 1 0 0 0 false true true 2


 				Bank Data Folder

    			[0] violins storageLevel hasHigherInterest loanAmount hasLowerLoanInterest

				ex. 10000 2 true 0 5 false
				*/
			}
		}
	}
}