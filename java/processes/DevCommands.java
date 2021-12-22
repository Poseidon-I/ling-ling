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
			case "!updateluthierchance" -> {
				if(CheckPermLevel(e, isDev) >= 2) {
					new UpdateLuthierChance(e);
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
						file.delete();
						reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\Bank Data\\" + file.getName()));
						bankData = reader.readLine().split(" ");
						reader.close();
					} catch(Exception exception) {
						e.getChannel().sendMessage("Shit!\n" + file.getAbsolutePath()).queue();
						continue;
					}
					
					JSONObject data = new JSONObject();
					data.put("violins", Long.parseLong(oldData[0]));
					data.put("bank", Long.parseLong(bankData[0]));
					data.put("loan", Long.parseLong(bankData[3]));
					data.put("medals", Long.parseLong(oldData[55]));
					data.put("thirdPlace", Long.parseLong(oldData[52]));
					data.put("secondPlace", Long.parseLong(oldData[53]));
					data.put("firstPlace", Long.parseLong(oldData[54]));
					
					data.put("rice", Long.parseLong(oldData[51]));
					data.put("tea", Long.parseLong(oldData[62]));
					data.put("blessings", Long.parseLong(oldData[63]));
					data.put("voteBox", Long.parseLong(oldData[90]));
					data.put("giftBox", Long.parseLong(oldData[87]));
					data.put("kits", Long.parseLong(oldData[91]));
					data.put("linglingBox", Long.parseLong(oldData[92]));
					data.put("crazyBox", Long.parseLong(oldData[93]));
					
					data.put("income", Long.parseLong(oldData[12]));
					data.put("streak", Long.parseLong(oldData[47]));
					data.put("earnings", Long.parseLong(oldData[75]));
					data.put("winnings", Long.parseLong(oldData[66]));
					data.put("millions", Long.parseLong(oldData[67]));
					data.put("robbed", Long.parseLong(oldData[68]));
					data.put("lostToRob", Long.parseLong(oldData[69]));
					data.put("scalesPlayed", Long.parseLong(oldData[70]));
					data.put("hoursPractised", Double.parseDouble(oldData[71]));
					data.put("rehearsals", Long.parseLong(oldData[72]));
					data.put("performances", Long.parseLong(oldData[73]));
					data.put("hoursTaught", Double.parseDouble(oldData[76]));
					data.put("maxStreak", Long.parseLong(oldData[74]));
					data.put("votes", Long.parseLong(oldData[88]));
					data.put("luthiers", Long.parseLong(oldData[77]));
					data.put("giftsGiven", Long.parseLong(oldData[85]));
					data.put("giftsReceived", Long.parseLong(oldData[86]));
					data.put("interestEarned", Long.parseLong(oldData[10]));
					data.put("penaltiesIncurred", Long.parseLong(oldData[11]));
					
					data.put("betCD", Long.parseLong(oldData[3]));
					data.put("scaleCD", Long.parseLong(oldData[64]));
					data.put("practiceCD", Long.parseLong(oldData[1]));
					data.put("teachCD", Long.parseLong(oldData[79]));
					data.put("rehearseCD", Long.parseLong(oldData[7]));
					data.put("performCD", Long.parseLong(oldData[8]));
					data.put("robCD", Long.parseLong(oldData[5]));
					data.put("voteCD", Long.parseLong(oldData[89]));
					data.put("hadDailyToday", Boolean.parseBoolean(oldData[48]));
					data.put("hadGiftToday", Boolean.parseBoolean(oldData[49]));
					
					data.put("efficiency", Long.parseLong(oldData[2]));
					data.put("luck", Long.parseLong(oldData[4]));
					data.put("sophistication", Long.parseLong(oldData[6]));
					data.put("insurance", Boolean.parseBoolean(oldData[9]));
					data.put("timeCrunch", Boolean.parseBoolean(oldData[50]));
					
					data.put("violinQuality", Long.parseLong(oldData[13]));
					data.put("skills", Long.parseLong(oldData[14]));
					data.put("lessonQuality", Long.parseLong(oldData[15]));
					data.put("stringQuality", Long.parseLong(oldData[16]));
					data.put("bowQuality", Long.parseLong(oldData[17]));
					data.put("math", Boolean.parseBoolean(oldData[18]));
					
					data.put("orchestra", Boolean.parseBoolean(oldData[19]));
					
					data.put("piccolo", Boolean.parseBoolean(oldData[20]));
					data.put("flute", Long.parseLong(oldData[21]));
					data.put("oboe", Long.parseLong(oldData[22]));
					data.put("clarinet", Long.parseLong(oldData[23]));
					data.put("bassoon", Long.parseLong(oldData[24]));
					data.put("contraBassoon", Boolean.parseBoolean(oldData[25]));
					
					data.put("horn", Long.parseLong(oldData[26]));
					data.put("trumpet", Long.parseLong(oldData[27]));
					data.put("trombone", Long.parseLong(oldData[28]));
					data.put("tuba", Long.parseLong(oldData[29]));
					data.put("timpani", Long.parseLong(oldData[30]));
					data.put("percussion", Long.parseLong(oldData[31]));
					
					data.put("violin1", Long.parseLong(oldData[32]));
					data.put("violin2", Long.parseLong(oldData[33]));
					data.put("cello", Long.parseLong(oldData[34]));
					data.put("doubleBass", Long.parseLong(oldData[35]));
					data.put("piano", Long.parseLong(oldData[36]));
					data.put("harp", Boolean.parseBoolean(oldData[37]));
					
					data.put("soprano", Long.parseLong(oldData[38]));
					data.put("alto", Long.parseLong(oldData[39]));
					data.put("tenor", Long.parseLong(oldData[40]));
					data.put("bass", Long.parseLong(oldData[41]));
					data.put("soloist", Long.parseLong(oldData[42]));
					
					data.put("hall", Long.parseLong(oldData[43]));
					data.put("conductor", Long.parseLong(oldData[44]));
					data.put("tickets", Long.parseLong(oldData[46]));
					data.put("advertising", Long.parseLong(oldData[45]));
					
					data.put("certificate", Boolean.parseBoolean(oldData[78]));
					data.put("students", Long.parseLong(oldData[81]));
					data.put("lessonCharge", Long.parseLong(oldData[82]));
					data.put("training", Long.parseLong(oldData[80]));
					data.put("studio", Boolean.parseBoolean(oldData[83]));
					data.put("longerLessons", Boolean.parseBoolean(oldData[84]));
					
					data.put("moreIncome", Long.parseLong(oldData[56]));
					data.put("moreCommandIncome", Long.parseLong(oldData[57]));
					data.put("moreMulti", Long.parseLong(oldData[58]));
					data.put("moreRob", Long.parseLong(oldData[59]));
					data.put("shield", Boolean.parseBoolean(oldData[60]));
					data.put("duplicator", Boolean.parseBoolean(oldData[61]));
					
					data.put("storage", Long.parseLong(bankData[1]));
					data.put("moreInterest", Boolean.parseBoolean(bankData[2]));
					data.put("lessPenalty", Boolean.parseBoolean(bankData[4]));
					
					data.put("banned", false);
					data.put("perms", Long.parseLong(oldData[65]));
					data.put("medalToday", Boolean.parseBoolean(oldData[94]));
					data.put("retainDaily", Boolean.parseBoolean(oldData[95]));
					data.put("isBooster", Boolean.parseBoolean(oldData[96]));
					data.put("serverLevel", Double.parseDouble(oldData[97]));
					
					try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + file.getName().substring(0, file.getName().length() - 4) + ".json")) {
						writer.write(data.toJSONString());
						writer.close();
					} catch(Exception exception) {
						//nothing here lol
					}
				}
				
				directory = new File("Ling Ling Bot Data\\Settings\\Luthier");
				files = directory.listFiles();
				assert files != null;
				for(File file : files) {
					String[] oldData;
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
						oldData = reader.readLine().split(" ");
						reader.close();
						file.delete();
					} catch(Exception exception) {
						e.getChannel().sendMessage("Shit!\n" + file.getAbsolutePath()).queue();
						continue;
					}
					
					JSONObject object = new JSONObject();
					
					object.put("channel", oldData[0]);
					object.put("multiplier", Long.parseLong(oldData[1]));
					object.put("chance", Double.parseDouble(oldData[2]));
					object.put("hasWord", Boolean.parseBoolean(oldData[3]));
					object.put("word", oldData[4]);
					object.put("amount", Long.parseLong(oldData[5]));
					
					try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + file.getName().substring(0, file.getName().length() - 4) + ".json")) {
						writer.write(object.toJSONString());
						writer.close();
					} catch(Exception exception) {
						//nothing here lol
					}
				}
				e.getChannel().sendMessage("Custom command completed!").queue();
			}
		}
	}
}