package processes;

import dev.*;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class DevCommands {
	public static int CheckPermLevel(MessageReceivedEvent e) {
		if(e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962")) {
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
	
	public DevCommands(MessageReceivedEvent e) {
		//1 - mods, 2 - admins, 3 - devs
		String[] message = e.getMessage().getContentRaw().split(" ");
		switch(message[0]) {
			case "!give" -> {
				if(CheckPermLevel(e) >= 1) {
					new Give(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!warn" -> {
				if(CheckPermLevel(e) >= 1) {
					new Warn(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!resetsave" -> {
				if(CheckPermLevel(e) >= 1) {
					new ResetSave(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!ban" -> {
				if(CheckPermLevel(e) >= 2) {
					new Ban(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!unban" -> {
				if(CheckPermLevel(e) >= 2) {
					new Unban(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!luthier" -> {
				if(CheckPermLevel(e) >= 2) {
					new AdminLuthier(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!resetincomes" -> {
				if(CheckPermLevel(e) >= 2) {
					new ResetIncomes(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!updateluthierchance" -> {
				if(CheckPermLevel(e) >= 2) {
					new UpdateLuthierChance(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!updateusers" -> {
				if(CheckPermLevel(e) == 3) {
					new UpdateUsers(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!forcestoπ" -> {
				if(CheckPermLevel(e) == 3) {
					e.getMessage().reply("Forcing bot to stop...");
					e.getJDA().shutdownNow();
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!updateroles" -> {
				if(CheckPermLevel(e) == 3) {
					new UpdateRoles(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!setpermlevel" -> {
				if(CheckPermLevel(e) == 3) {
					new SetPermLevel(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!resetdaily" -> {
				if(CheckPermLevel(e) == 3) {
					new MoreDailyTime(e);
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not nave permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!status" -> {
				if(CheckPermLevel(e) == 3) {
					e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
					switch(message[1]) {
						case "online" -> e.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
						case "away" -> e.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
						case "dnd" -> e.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
					}
				} else {
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!activity" -> {
				if(CheckPermLevel(e) == 3) {
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
					e.getMessage().reply(":no_entry: **403 FORBIDDEN** :no_entry:\nYou do not have permission to run this command.").mentionRepliedUser(false).queue();
				}
			}
			case "!custom" -> {
				JSONParser parser = new JSONParser();
				File[] directory = new File("Ling Ling Bot Data\\Economy Data").listFiles();
				JSONObject data;
				assert directory != null;
				for(File file : directory) {
					try {
						FileTime time = (FileTime) Files.getAttribute(Path.of(file.getAbsolutePath()), "creationTime");
						if(time.toMillis() > 1640332800000L) {
							try(FileReader reader = new FileReader(file.getAbsolutePath())) {
								data = (JSONObject) parser.parse(reader);
								reader.close();
							} catch(Exception exception) {
								System.out.println("Problem File is " + file.getName());
								continue;
							}
							data.replace("rice", (long) data.get("rice") - 10);
							data.replace("tea", (long) data.get("tea") - 10);
							data.replace("blessings", (long) data.get("blessings") - 10);
							data.replace("voteBox", (long) data.get("voteBox") - 10);
							data.replace("giftBox", (long) data.get("giftBox") - 10);
							data.replace("kits", (long) data.get("kits") - 10);
							data.replace("linglingBox", (long) data.get("linglingBox") - 10);
							data.replace("crazyBox", (long) data.get("crazyBox") - 10);
							try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
								writer.write(data.toJSONString());
								writer.close();
							} catch(Exception exception) {
								//nothing here lol
							}
						}
					} catch(Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		}
	}
}