package processes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class DevCommands {
    public DevCommands(GuildMessageReceivedEvent e, String[] message) {
        BufferedReader reader;
        PrintWriter writer;
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
                    case "streaming" -> e.getJDA().getPresence().setActivity(Activity.streaming(activity.toString(), "https://www.youtube.com/channel/UCfqRDWapZD42yFcIlj15oRw"));
                    case "nothing" -> e.getJDA().getPresence().setActivity(null);
                }
            }
            case "!lookdata" -> {
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
            }
            case "!editdata" -> {
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
            }
            case "!give" -> {
                String id = message[1];
                long add = Long.parseLong(message[3]);
                long newValue = -1;
                String userData;
                int index = -1;
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt"));
                    userData = reader.readLine();
                    reader.close();
                } catch (Exception exception) {
                    e.getChannel().sendMessage("This user save doesn't exist!").queue();
                    throw new IllegalArgumentException();
                }
                switch(message[2]) {
                    case "violin" -> {
                        index = 0;
                        newValue = Long.parseLong(userData.split(" ")[0]) + add;
                        e.getChannel().sendMessage("Successfully gave " + add + ":violin: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                    }
                    case "rice" -> {
                        index = 51;
                        newValue = Long.parseLong(userData.split(" ")[51]) + add;
                        e.getChannel().sendMessage("Successfully gave " + add + ":rice: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                    }
                    case "medal" -> {
                        index = 55;
                        newValue = Long.parseLong(userData.split(" ")[55]) + add;
                        e.getChannel().sendMessage("Successfully gave " + add + ":military_medal: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                    }
                    case "tea" -> {
                        newValue = Long.parseLong(userData.split(" ")[62]) + add;
                        e.getChannel().sendMessage("Successfully gave " + add + ":bubble_tea: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                    }
                    case "blessing" -> {
                        newValue = Long.parseLong(userData.split(" ")[63]) + add;
                        e.getChannel().sendMessage("Successfully gave " + add + ":angel: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                    }
                    default -> e.getChannel().sendMessage("You wrote this bot you dumbass, you should know that they are all singular.").queue();
                }
                StringBuilder newData = new StringBuilder();
                for (int i = 0; i < userData.split(" ").length; i++) {
                    if(i == index) {
                        newData.append(newValue).append(" ");
                    } else {
                        newData.append(userData.split(" ")[i]).append(" ");
                    }
                }
                newData.deleteCharAt(newData.length() - 1);
                try {
                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
                    writer.print(newData);
                    writer.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
            }
            case "!luthier" -> {
                try {
                    switch (message[1]) {
                        case "setup" -> {
                            File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt");
                            try {
                                file.createNewFile();
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                                writer.print(e.getChannel().getId() + " 1 0.01 false blank 0");
                                writer.close();
                                e.getChannel().sendMessage("Successfully set up Luthier for " + e.getGuild().getName() + " in " + e.getChannel().getAsMention()).queue();
                            } catch (Exception exception2) {
                                //nothing here lol
                            }
                        }
                        case "edit" -> {
                            String id = e.getGuild().getId();
                            StringBuilder luthierData = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                luthierData.append(message[i]).append(" ");
                            }
                            luthierData.deleteCharAt(luthierData.length() - 1);
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".txt")));
                                writer.print(luthierData);
                                writer.close();
                                e.getChannel().sendMessage("Successfully edited the data of Luthier for " + e.getGuild().getName()).queue();
                            } catch (Exception exception1) {
                                //nothing here lol
                            }
                        }
                        case "add" -> {
                            String[] data = new String[0];
                            try {
                                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                                data = reader.readLine().split(" ");
                                reader.close();
                            } catch (Exception exception1) {
                                //nothing here lol
                            }
                            long add = Long.parseLong(message[2]);
                            StringBuilder luthierData = new StringBuilder().append(data[0]).append(" ").append((Long.parseLong(data[1]) + add));
                            for (int i = 2; i < data.length; i++) {
                                luthierData.append(" ").append(data[i]);
                            }
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                                writer.print(luthierData);
                                writer.close();
                                e.getChannel().sendMessage("Successfully added " + message[2] + "x multiplier to " + e.getGuild().getName() + ".  New multiplier: " + (Long.parseLong(data[1]) + add)).queue();
                            } catch (Exception exception1) {
                                //nothing here lol
                            }
                        }
                    }
                } catch (Exception exception) {
                    String luthierData;
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                        luthierData = reader.readLine();
                        e.getChannel().sendMessage("Luthier Settings for " + e.getGuild().getName() + ": " + luthierData).queue();
                        reader.close();
                    } catch (Exception exception1) {
                        e.getChannel().sendMessage("Luthier has not been set up!").queue();
                    }
                }
            }
            case "!updateservers" -> {
                if (message.length > 1 && message[1].equals("confirm")) {
                    e.getChannel().sendMessage("Updating saves for all servers...").queue();
                    File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server");
                    File[] files = directory.listFiles();
                    StringBuilder append = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        append.append(" ").append(message[i]);
                    }
                    if (files != null) {
                        for (File file : files) {
                            try {
                                reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                String currentData = reader.readLine();
                                reader.close();
                                writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                writer.print(currentData + append);
                                writer.close();
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
                    File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
                    File[] files = directory.listFiles();
                    StringBuilder append = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        append.append(" ").append(message[i]);
                    }
                    if (files != null) {
                        for (File file : files) {
                            try {
                                reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                String currentData = reader.readLine();
                                reader.close();
                                writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                writer.print(currentData + append);
                                writer.close();
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
                File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
                File[] files = directory.listFiles();
                if (files != null) {
                    int deleted = 0;
                    for (File file : files) {
                        try {
                            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                            String[] line = reader.readLine().split(" ");
                            long violins = Integer.parseInt(line[0]);
                            long income = Integer.parseInt(line[12]);
                            reader.close();
                            if (violins == 0 && income == 0) {
                                file.delete();
                                deleted++;
                            }
                        } catch (Exception exception) {
                            //nothing here lol
                        }
                    }
                    e.getChannel().sendMessage("Successfully purged " + deleted + " files!").queue();
                }
            }
            case "!resetincomes" -> {
                if (!e.getAuthor().isBot()) {
                    e.getChannel().sendMessage("Maually overriding automatic user save reset...").queue();
                }
                File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String[] data;
                        try {
                            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                            data = reader.readLine().split(" ");
                            reader.close();
                        } catch (Exception exception) {
                            continue;
                        }
                        long violinQuality = Integer.parseInt(data[13]);
                        long skillLevel = Integer.parseInt(data[14]);
                        long lessonQuality = Integer.parseInt(data[15]);
                        long stringQuality = Integer.parseInt(data[16]);
                        long bowQuality = Integer.parseInt(data[17]);
                        boolean hasMath = Boolean.parseBoolean(data[18]);
                        boolean hasOrchestra = Boolean.parseBoolean(data[19]);
                        boolean piccolo = Boolean.parseBoolean(data[20]);
                        long flute = Integer.parseInt(data[21]);
                        long oboe = Integer.parseInt(data[22]);
                        long clarinet = Integer.parseInt(data[23]);
                        long bassoon = Integer.parseInt(data[24]);
                        boolean contrabassoon = Boolean.parseBoolean(data[25]);
                        long horn = Integer.parseInt(data[26]);
                        long trumpet = Integer.parseInt(data[27]);
                        long trombone = Integer.parseInt(data[28]);
                        long tuba = Integer.parseInt(data[29]);
                        long timpani = Integer.parseInt(data[30]);
                        long percussion = Integer.parseInt(data[31]);
                        long first = Integer.parseInt(data[32]);
                        long second = Integer.parseInt(data[33]);
                        long cello = Integer.parseInt(data[34]);
                        long stringBass = Integer.parseInt(data[35]);
                        long piano = Integer.parseInt(data[36]);
                        boolean harp = Boolean.parseBoolean(data[37]);
                        long soprano = Integer.parseInt(data[38]);
                        long alto = Integer.parseInt(data[39]);
                        long tenor = Integer.parseInt(data[40]);
                        long bass = Integer.parseInt(data[41]);
                        long soloists = Integer.parseInt(data[42]);
                        long hallLevel = Integer.parseInt(data[43]);
                        long conductor = Integer.parseInt(data[44]);
                        long advertising = Integer.parseInt(data[45]);
                        long tickets = Integer.parseInt(data[46]);
                        boolean moreIncome = Boolean.parseBoolean(data[56]);
                        long income = violinQuality * 600 + skillLevel * 240 + lessonQuality * 150 + stringQuality * 100 + bowQuality * 200 + flute * 60 + oboe * 50 + clarinet * 40 + bassoon * 40 + horn * 40 + trumpet * 30 + trombone * 20 + tuba * 20 + timpani * 60 + percussion * 10 + first * 70 + second * 60 + cello * 50 + stringBass * 50 + piano * 110 + soprano * 30 + alto * 20 + tenor * 20 + bass * 20 + soloists * 60 + hallLevel * 300 + conductor * 200 + advertising * 100 + tickets * 1000;
                        if (hasMath) {
                            income += 6500;
                        }
                        if (hasOrchestra) {
                            income += 3100;
                        } else {
                            income -= 130;
                        }
                        if (piccolo) {
                            income += 30;
                        }
                        if (contrabassoon) {
                            income += 30;
                        }
                        if (harp) {
                            income += 80;
                        }
                        if (moreIncome) {
                            income += 8000;
                        }
                        data[12] = income + "";
                        StringBuilder write = new StringBuilder(data[0]);
                        for (int i = 1; i < data.length; i++) {
                            write.append(" ").append(data[i]);
                        }
                        try {
                            writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                            writer.print(write);
                            writer.close();
                        } catch (Exception exception) {
                            //nothing here lol
                        }
                    }
                    e.getChannel().sendMessage("Incomes successfully reset!").queue();
                }
            }
            case "!custom" -> {
                File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
                File[] files = directory.listFiles();
                assert files != null;
                for (File file : files) {
                    String[] data;
                    try {
                        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                        data = reader.readLine().split(" ");
                    } catch (Exception exception) {
                        continue;
                    }
                    data[75] = "0";
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                        writer.print(data[0]);
                        for(int i = 1; i < data.length; i ++) {
                            writer.print(" " + data[i]);
                        }
                        writer.close();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                }
            }
            case "!warn" -> {
                StringBuilder reason = new StringBuilder();
                for (int i = 2; i < message.length; i++) {
                    reason.append(" ").append(message[i]);
                }
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                User user;
                try {
                    user = e.getMessage().getMentionedUsers().get(0);
                } catch (Exception exception) {
                    try {
                        user = e.getJDA().getUserById(message[1]);
                    } catch (Exception exception1) {
                        e.getChannel().sendMessage("You tried to warn a non-existant user.  And you're a dev.  You should know better smh.").queue();
                        break;
                    }
                }
                assert user != null;
                EmbedBuilder builder = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                        .addField("Developer: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                        .setTitle("__**Bot Warning Info**__");
                Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("709632179340312597")).getTextChannelById("800613646380040233")).sendMessage(builder.build()).queue();
                user.openPrivateChannel().complete().sendMessage("You have received an official bot warning for" + reason + ".  Continuation of this action will result in a save file reset and/or a bot ban.").queue();
                e.getChannel().sendMessage(":warning: " + user.getName() + " was successfully warned!").queue();
            }
            case "!restart" -> {
                e.getChannel().sendMessage("Restarting bot...");
                e.getJDA().shutdownNow();
                new StartBot();
            }
        }
    }
}