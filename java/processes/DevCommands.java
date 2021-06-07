package processes;

import dev.Luthier;
import dev.*;
import dev.ResetSave;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.Objects;

public class DevCommands {
    public DevCommands(GuildMessageReceivedEvent e, String[] message, int permLevel) {
        //0 - mods, 1 - admins, 2 - devs
        BufferedReader reader;
        PrintWriter writer;
        switch (message[0]) {
            case "!status" -> {
                if(permLevel == 2) {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    switch (message[1]) {
                        case "online" -> e.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
                        case "away" -> e.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
                        case "dnd" -> e.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
                    }
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!activity" -> {
                if(permLevel == 2) {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    StringBuilder activity = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        activity.append(message[i]).append(" ");
                    }
                    switch (message[1]) {
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
            case "!lookdata" -> {
                if(permLevel >= 0) {
                    String userData;
                    String target = message[1];
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                        userData = reader.readLine();
                        e.getChannel().sendMessage(Objects.requireNonNull(e.getJDA().getUserById(target)).getName() + "'s data: " + userData).queue();
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This user save doesn't exist!").queue();
                    }
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!editdata" -> {
                if(permLevel >= 1) {
                    String id = message[1];
                    StringBuilder userData = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        userData.append(message[i]).append(" ");
                    }
                    userData.deleteCharAt(userData.length() - 1);
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
                        writer.print(userData);
                        writer.close();
                        e.getChannel().sendMessage("Successfully edited the data of " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This user save doesn't exist!").queue();
                    }
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!give" -> {
                if(permLevel >= 0) {
                    new Give(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!luthier" -> {
                if(permLevel >= 1) {
                    new Luthier(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!updateusers" -> {
                if(permLevel == 2) {
                    new UpdateUsers(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!purgeusers" -> {
                if(permLevel == 2) {
                    new PurgeUsers(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!resetincomes" -> {
                if(permLevel >= 1) {
                    new ResetIncomes(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
           /* case "!custom" -> {
                File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
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
                        if (!data[78].equals("true") && !data[78].equals("false")) {
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                for (int i = 0; i < 78; i++) {
                                    writer.print(data[i] + " ");
                                }
                                writer.print("false");
                                for (int i = 79; i < 85; i++) {
                                    writer.print(" " + data[i]);
                                }
                            } catch (Exception exception) {
                                //nothing here lol
                            }
                        }
                }
            }*/
            case "!warn" -> {
                if(permLevel >= 0) {
                    new Warn(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!resetsave" -> {
                if(permLevel >= 0) {
                    new ResetSave(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!ban" -> {
                if(permLevel >= 1) {
                    new Ban(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!unban" -> {
                if(permLevel >= 1) {
                    new Unban(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!restart" -> {
                if(permLevel == 2) {
                    e.getChannel().sendMessage("Restarting bot...");
                    e.getJDA().shutdownNow();
                    new StartBot();
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
        }
    }
}