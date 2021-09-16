package processes;

import dev.*;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class DevCommands {
	public DevCommands(GuildMessageReceivedEvent e, int permLevel) {
		//1 - mods, 2 - admins, 3 - devs
		String[] message = e.getMessage().getContentRaw().split(" ");
		BufferedReader reader;
		PrintWriter writer;
		switch(message[0]) {
			case "!give" -> {
				if(permLevel >= 1) {
					new Give(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!warn" -> {
				if(permLevel >= 1) {
					new Warn(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!resetsave" -> {
				if(permLevel >= 1) {
					new ResetSave(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!ban" -> {
				if(permLevel >= 2) {
					new Ban(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!unban" -> {
				if(permLevel >= 2) {
					new Unban(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!luthier" -> {
				if(permLevel >= 2) {
					new AdminLuthier(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!resetincomes" -> {
				if(permLevel >= 2) {
					new ResetIncomes(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!updateusers" -> {
				if(permLevel == 3) {
					new UpdateUsers(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!purgeusers" -> {
				if(permLevel == 3) {
					new PurgeUsers(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!restart" -> {
				if(permLevel == 3) {
					e.getChannel().sendMessage("Restarting bot...");
					e.getJDA().shutdownNow();
					new StartBot();
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!updateroles" -> {
				if(permLevel == 3) {
					new UpdateRoles(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!setpermlevel" -> {
				if(permLevel == 3) {
					new SetPermLevel(e);
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not nave permission to run this command.").queue();
				}
			}
			case "!status" -> {
				if(permLevel == 3) {
					e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
					switch(message[1]) {
						case "online" -> e.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
						case "away" -> e.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
						case "dnd" -> e.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
					}
				} else {
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
			case "!activity" -> {
				if(permLevel == 3) {
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
					e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
				}
			}
            /*case "!custom" -> {
                File directory = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
                File[] files = directory.listFiles();
                assert files != null;
                for (File file : files) {
                    String[] data;
                    try {
                        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                        data = reader.readLine().split(" ");
                        reader.close();
                    } catch (Exception exception) {
                        continue;
                    }
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                        writer.print(data[0]);
                        for (int i = 1; i < data.length; i ++) {
                            if(i == 10 || i == 11) {
                                writer.print(" 0");
                            } else {
                                writer.print(" " + data[i]);
                            }
                        }
                        writer.close();
                        File file2 = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Bank Data\\" + file.getName());
						writer = new PrintWriter(new BufferedWriter(new FileWriter(file2.getAbsolutePath())));
						writer.print("0 0 false 0 false");
						writer.close();
                    } catch (Exception exception) {
                        System.out.println();
                    }
                }
                e.getChannel().sendMessage("Custom command completed!").queue();
            }*/
		}
	}
}