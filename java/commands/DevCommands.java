package commands;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;
import java.util.Objects;

public class DevCommands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        boolean isDev = false;
        if (e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612")) {
            isDev = true;
        }
        // **DEV COMMANDS** \\
        if (isDev) {
            switch (message[0]) {
                case "!status" -> {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    switch (message[1]) {
                        case "online" -> e.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
                        case "away" -> e.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
                        case "dnd" -> e.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
                    }
                }
                case "!activity" -> {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    StringBuilder activity = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        activity.append(message[i]).append(" ");
                    }
                    switch (message[1]) {
                        case "playing" -> e.getJDA().getPresence().setActivity(Activity.playing(activity.toString()));
                        case "listening" -> e.getJDA().getPresence().setActivity(Activity.listening(activity.toString()));
                        case "watching" -> e.getJDA().getPresence().setActivity(Activity.watching(activity.toString()));
                        case "streaming" -> e.getJDA().getPresence().setActivity(Activity.streaming(activity.toString(), "twitch.tv"));
                        case "nothing" -> e.getJDA().getPresence().setActivity(null);
                    }
                }
                case "!annoy" -> {
                    int pings = Integer.parseInt(message[1]);
                    if (pings > 100000) {
                        e.getChannel().sendMessage("Why would you want to run the command for more than two and a half days?  That's already bad enough and now you want to crash me.").queue();
                        throw new IllegalArgumentException();
                    }
                    StringBuilder target = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        target.append(message[i]).append(" ");
                    }
                    for (int i = 0; i < pings; i++) {
                        e.getChannel().sendMessage("annoy" + target).queue();
                    }
                }
                case "!spam" -> {
                    int pings = Integer.parseInt(message[1]);
                    if (pings > 100000) {
                        e.getChannel().sendMessage("Why would you want to run the command for more than two and a half days?  That's already bad enough and now you want to crash me.").queue();
                        throw new IllegalArgumentException();
                    }
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    User target = e.getMessage().getMentionedMembers().get(0).getUser();
                    StringBuilder send = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        send.append(message[i]).append(" ");
                    }
                    for (int i = 0; i < pings; i++) {
                        target.openPrivateChannel().complete().sendMessage("annoy" + send).queue();
                    }
                }
                case "!lookdata" -> {
                    BufferedReader br;
                    String data;
                    String target = message[1];
                    try {
                        br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                        data = br.readLine();
                        e.getChannel().sendMessage(Objects.requireNonNull(e.getJDA().getUserById(target)).getName() + "'s data: " + data).queue();
                        br.close();
                    } catch (Exception exception) {
                        throw new IllegalArgumentException();
                    }
                }
                case "!editdata" -> {
                    String id = message[1];
                    StringBuilder data = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        data.append(message[i]).append(" ");
                    }
                    data.deleteCharAt(data.length() - 1);
                    PrintWriter writeEdit;
                    try {
                        writeEdit = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
                    } catch (Exception exception) {
                        throw new IllegalArgumentException();
                    }
                    writeEdit.print(data.toString());
                    writeEdit.close();
                    e.getChannel().sendMessage("Successfully edited the data of " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                }
                case "!give" -> {
                    String id = message[1];
                    int add = Integer.parseInt(message[2]);
                    BufferedReader br;
                    int violins;
                    String data;
                    try {
                        br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt"));
                        data = br.readLine();
                        violins = Integer.parseInt(data.split(" ")[0]) + add;
                        br.close();
                    } catch (Exception exception) {
                        throw new IllegalArgumentException();
                    }
                    StringBuilder newData = new StringBuilder();
                    newData.append(violins);
                    for (int i = 1; i < data.split(" ").length; i++) {
                        newData.append(" ").append(data.split(" ")[i]);
                    }
                    PrintWriter writeEdit;
                    try {
                        writeEdit = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
                    } catch (Exception exception) {
                        throw new IllegalArgumentException();
                    }
                    writeEdit.print(newData);
                    writeEdit.close();
                    e.getChannel().sendMessage("Successfully gave " + add + " violins to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                }
                case "!luthier" -> {
                    PrintWriter writeEdit = null;
                    try {
                        if (message[1].equals("setup")) {
                            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt");
                            try {
                                file.createNewFile();
                                writeEdit = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                            } catch (Exception exception2) {
                                //nothing here lol
                            }
                            assert writeEdit != null;
                            writeEdit.print(e.getChannel().getId() + " 1 0.02 false blank 0");
                            writeEdit.close();
                            e.getChannel().sendMessage("Successfully set up Luthier for " + e.getGuild().getName()).queue();
                        } else if (message[1].equals("edit")) {
                            String id = e.getGuild().getId();
                            StringBuilder data = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                data.append(message[i]).append(" ");
                            }
                            data.deleteCharAt(data.length() - 1);
                            try {
                                writeEdit = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".txt")));
                                writeEdit.print(data.toString());
                                writeEdit.close();
                                e.getChannel().sendMessage("Successfully edited the data of Luthier for " + e.getGuild().getName()).queue();
                            } catch (Exception exception1) {
                                //nothing here lol
                            }
                        }
                    } catch (Exception exception) {
                        BufferedReader br;
                        String data;
                        try {
                            br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                            data = br.readLine();
                            e.getChannel().sendMessage("Luthier Settings for " + e.getGuild().getName() + ": " + data).queue();
                            br.close();
                        } catch (Exception exception1) {
                            e.getChannel().sendMessage("Luthier has not been set up!").queue();
                        }
                    }
                }
                case "!updateservers" -> {
                    if (message.length > 1 && message[1].equals("confirm")) {
                        e.getChannel().sendMessage("Updating saves for all servers...").queue();
                        File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server");
                        File[] files = directory.listFiles();
                        StringBuilder append = new StringBuilder();
                        for (int i = 2; i < message.length; i++) {
                            append.append(" ").append(message[i]);
                        }
                        if (files != null) {
                            for (File file : files) {
                                try {
                                    BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                    String currentData = br.readLine();
                                    br.close();
                                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                    pw.print(currentData + append);
                                    pw.close();
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                            }
                            e.getChannel().sendMessage("Successfully updated saves for all servers!").queue();
                        }
                    } else {
                        e.getChannel().sendMessage("Please type `!updateservers confirm` to confirm that you would like to update all data to the latest version.").queue();
                    }
                }
                case "!updateusers" -> {
                    if (message.length > 1 && message[1].equals("confirm")) {
                        e.getChannel().sendMessage("Updating saves for all users...").queue();
                        File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                        File[] files = directory.listFiles();
                        StringBuilder append = new StringBuilder();
                        for (int i = 2; i < message.length; i++) {
                            append.append(" ").append(message[i]);
                        }
                        if (files != null) {
                            for (File file : files) {
                                try {
                                    BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                    String currentData = br.readLine();
                                    br.close();
                                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                    pw.print(currentData + append);
                                    pw.close();
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                            }
                            e.getChannel().sendMessage("Successfully updated saves for all users!").queue();
                        }
                    } else {
                        e.getChannel().sendMessage("Please type `!updateusers confirm` to confirm that you would like to update all data to the latest version.").queue();
                    }
                }
                case "!purgeusers" -> {
                    e.getChannel().sendMessage("Purging saves for users with no violins...").queue();
                    File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                    File[] files = directory.listFiles();
                    if (files != null) {
                        int deleted = 0;
                        for (File file : files) {
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                int violins = Integer.parseInt(br.readLine().split(" ")[0]);
                                br.close();
                                if(violins == 0) {
                                    file.delete();
                                    deleted ++;
                                }
                            } catch (Exception exception) {
                                //nothing here lol
                            }
                        }
                        e.getChannel().sendMessage("Successfully purged " + deleted + " files!").queue();
                    }
                }
            }
        }
    }
}
