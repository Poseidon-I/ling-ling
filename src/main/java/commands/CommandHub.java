package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Random;

public class CommandHub extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        boolean isDev = false;
        boolean hasBotId = false;
        boolean hasSameUser = false;
        boolean hasDevPing = false;
        boolean pingBot = false;
        boolean isBot = false;
        try {
            if (e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById(e.getAuthor().getId()))) {
                hasSameUser = true;
            }
            if (e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("733409243222507670"))) {
                hasBotId = true;
            }
            if (e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612")) {
                isDev = true;
            }
            if (e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("619989388109152256")) || e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("488487157372157962")) || e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("706933826193981612"))) {
                hasDevPing = true;
            }
            if (e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("733409243222507670"))) {
                pingBot = true;
            }
            if (e.getAuthor().getId().equals("733409243222507670")) {
                isBot = true;
            }
        } catch (Exception exception) {
            //nothing here lol
        }
        Random random = new Random();
        String server = "";
        try {
            server = e.getGuild().getId();
        } catch (Exception exception) {
            if (!e.getAuthor().isBot()) {
                if (message[0].equals("!report")) {
                    TextChannel channel = Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("749076642927739041");
                    StringBuilder report = new StringBuilder();
                    for (int i = 1; i < message.length; i++) {
                        report.append(message[i]).append(" ");
                    }
                    assert channel != null;
                    channel.sendMessage("<@" + e.getAuthor().getId() + "> is reporting the following: " + report).queue();
                    e.getChannel().sendMessage(":white_check_mark: Your message was successfully sent!  Please wait patiently for a mod or admin to follow up.").queue();
                }
            }
        }
        String fullMessage = e.getMessage().getContentRaw();
        char prefix = '!';
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
                        e.getChannel().sendMessage(e.getJDA().getUserById(target).getName() + "'s data: " + data).queue();
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
                    int violins = 0;
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
                    newData.append(violins).append(" ");
                    for (int i = 1; i < data.split(" ").length; i++) {
                        newData.append(data.split(" ")[i]).append(" ");
                    }
                    newData.deleteCharAt(data.length() - 1);
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
                            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + server + ".txt");
                            try {
                                file.createNewFile();
                                writeEdit = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + server + ".txt")));
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
                        }
                    } else {
                        e.getChannel().sendMessage("Please type `!updateusers confirm` to confirm that you would like to update all data to the latest version.").queue();
                    }
                }
            }
        }
        if (isBot) {
            try {
                if (message[1].contains("POLL:")) {
                    int options = 0;
                    try {
                        options = Integer.parseInt(message[0]);
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                    int hex = 127462;
                    for (int i = 0; i < options; i++) {
                        String unicode = "U+" + Integer.toHexString(hex);
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), unicode).queue();
                        hex++;
                    }
                }
            } catch (Exception exception) {
                //nothing here lol
            }
        }
        if (!e.getAuthor().isBot()) {
            if (pingBot && !e.getAuthor().isBot()) {
                e.getChannel().sendMessage("https://media.discordapp.net/attachments/734697499515682886/753329739074109700/trigger.gif").queue();
            }
            BufferedReader prefixes;
            PrintWriter pw = null;
            try {
                prefixes = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt"));
                prefix = (char) prefixes.read();
                prefixes.close();
            } catch (Exception exception) {
                File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt");
                try {
                    file.createNewFile();
                    pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt")));
                } catch (Exception exception1) {
                    //nothing here lol
                }
                assert pw != null;
                pw.print('!');
                pw.close();
            }
            if (isDev) {
                prefix = '!';
            }
            if (message[0].equals("!prefix")) {
                if (message.length == 1) {
                    e.getChannel().sendMessage("The prefix is `" + prefix + "`").queue();
                } else {
                    if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.ADMINISTRATOR) || isDev) {
                        char newPrefix = message[1].charAt(0);
                        try {
                            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt")));
                        } catch (Exception exception1) {
                            //nothing here lol
                        }
                        assert pw != null;
                        pw.print(newPrefix);
                        pw.close();
                        e.getChannel().sendMessage("The prefix is now `" + newPrefix + "`").queue();
                    } else {
                        e.getChannel().sendMessage("You must have the `ADMINISTRATOR` permission or be a bot developer to change the prefix.").queue();
                    }
                }
            }
            if (isDev) {
                prefix = '!';
            }
            String target = "";
            String targetPing = "";
            try {
                target = e.getMessage().getMentionedUsers().get(0).getId();
                targetPing = e.getMessage().getMentionedUsers().get(0).getName();
            } catch (Exception exception) {
                try {
                    target = message[1];
                    targetPing = e.getJDA().getUserById(message[1]).getName();
                } catch (Exception exception1) {
                    if (random.nextDouble() < 0.025) {
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F3BB").queue();
                    }
                }
            }
            if (targetPing.contains("@everyone") || targetPing.contains("@here") || targetPing.contains("<@&")) {
                targetPing = "**nice try**";
            }
            if (e.getMessage().getContentRaw().contains("@everyone") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getMessage().getContentRaw().contains("@here") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getMessage().getContentRaw().contains("<@&") && e.getMessage().getContentRaw().charAt(0) == prefix) {
                e.getChannel().sendMessage("why the hell did you ping here, everyone, or a role dumbass").queue();
            } else if (e.getAuthor().getName().contains("@everyone") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getAuthor().getName().contains("@here") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getAuthor().getName().contains("<@&") && e.getMessage().getContentRaw().charAt(0) == prefix) {
                e.getChannel().sendMessage("Nice try but no").queue();
            } else if (targetPing.contains("@everyone") && e.getMessage().getContentRaw().charAt(0) == prefix || targetPing.contains("@here") && e.getMessage().getContentRaw().charAt(0) == prefix || targetPing.contains("<@&") && e.getMessage().getContentRaw().charAt(0) == prefix) {
                e.getChannel().sendMessage("Nice try but no").queue();
            } else if (message[0].charAt(0) == prefix) {
                message[0] = message[0].substring(1);
                switch (message[0]) {
                    case "serversettings" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.ADMINISTRATOR) || isDev) {
                            String[] data = new String[0];
                            try {
                                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt"));
                                data = br.readLine().split(" ");
                                br.close();
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("Something went wrong when the bot joined your server, please contact <@619989388109152256> to fix this problem.").queue();
                            }
                            try {
                                switch (message[1]) {
                                    case "autoresponse" -> {
                                        try {
                                            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false")) {
                                                pw.print("false " + data[1] + " " + data[2] + " " + data[3]);
                                                pw.close();
                                                e.getChannel().sendMessage("Turned off Autoresponse!").queue();
                                            } else if (message[2].equals("true")) {
                                                pw.print("true " + data[1] + " " + data[2] + " " + data[3]);
                                                pw.close();
                                                e.getChannel().sendMessage("Turned on Autoresponse!").queue();
                                            }
                                        } catch (Exception exception1) {
                                            //nothing here lol
                                        }
                                    }
                                    case "viola" -> {
                                        try {
                                            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false")) {
                                                pw.print(data[0] + " false " + data[2] + " " + data[3]);
                                                pw.close();
                                                e.getChannel().sendMessage("Turned off Viola Mode!").queue();
                                            } else if (message[2].equals("true")) {
                                                pw.print(data[0] + " true " + data[2] + " " + data[3]);
                                                pw.close();
                                                e.getChannel().sendMessage("Turned on Viola Mode!").queue();
                                            }
                                        } catch (Exception exception1) {
                                            //nothing here lol
                                        }
                                    }
                                    case "logging" -> {
                                        try {
                                            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false")) {
                                                pw.print(data[0] + " " + data[1] + " false " + data[3]);
                                                pw.close();
                                                e.getChannel().sendMessage("Turned off Message Logging!").queue();
                                            } else if (message[2].equals("true")) {
                                                pw.print(data[0] + " " + data[1] + " false " + data[3]);
                                                pw.close();
                                                e.getChannel().sendMessage("Turned on Message Logging!").queue();
                                            }
                                        } catch (Exception exception1) {
                                            //nothing here lol
                                        }
                                    }
                                    case "automod" -> {
                                        try {
                                            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false")) {
                                                pw.print(data[0] + " " + data[1] + " " + data[2] + " false");
                                                pw.close();
                                                e.getChannel().sendMessage("Turned off Automod! (not a good idea)").queue();
                                            } else if (message[2].equals("true")) {
                                                pw.print(data[0] + " " + data[1] + " " + data[2] + " true");
                                                pw.close();
                                                e.getChannel().sendMessage("Turned on Automod!").queue();
                                            }
                                        } catch (Exception exception1) {
                                            //nothing here lol
                                        }
                                    }
                                }
                            } catch (Exception exception) {
                                EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE)
                                        .setTitle("Server Settings for " + e.getGuild().getName())
                                        .addField("Autoresponse", "A variety of more than forty triggers to add some pizzazz to your conversation!  Current value: `" + data[0] + "`\nID: `autoresponse`", false)
                                        .addField("Viola Mode", "All messages sent in channels with \"announcement\" in their name will have a 100% chance to have the V I O L A reaction.  Current value: `" + data[1] + "`\nID: `viola`", false)
                                        .addField("Message Logging", "Will log Edited and Deleted Messages!  Work-in-progress, requires a channel named \"moderation-log\"  Current value: `" + data[2] + "`\nID: `logging`", false)
                                        .addField("Automod", "A variety pre-determined bad words that the bot will automatically filter out!  Current value: `" + data[3] + "`\nID: `automod`", false)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                        } else {
                            e.getChannel().sendMessage("You must have the `ADMINISTRATOR` permission or be a bot developer to change the server settings.").queue();
                        }
                    }
                    case "settings" -> {

                    }
                    case "warn" -> {
                        if (e.getGuild().getMemberById(e.getAuthor().getId()).hasPermission(Permission.MESSAGE_MANAGE) || isDev) {
                            StringBuilder reason = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                reason.append(message[i]).append(" ");
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to warn yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot warn me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                User user = null;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried to warn a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                        .setTitle("__**Warning Info**__");
                                try {
                                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                    textChannel.sendMessage(builder.build()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("The warning was not logged, you must have a channel named \"moderation log\"").queue();
                                }
                                user.openPrivateChannel().complete().sendMessage("You were warned in " + e.getGuild().getName() + " for " + reason + "!").queue();
                                e.getChannel().sendMessage(":warning: " + targetPing + " was successfully warned!").queue();
                            }
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                    }
                    case "mute" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.MESSAGE_MANAGE) || isDev) {
                            StringBuilder reason = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                reason.append(message[i]).append(" ");
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to warn yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot mute me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                User user = null;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried to mute a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                if (server.equals("670725611207262219")) {
                                    try {
                                        e.getGuild().addRoleToMember(user.getId(), Objects.requireNonNull(e.getGuild().getRoleById("734697394389778462"))).queue();
                                    } catch (Exception exception) {
                                        e.getChannel().sendMessage("How are we going to mute nobody dum dum").queue();
                                        break;
                                    }
                                } else {
                                    try {
                                        e.getGuild().addRoleToMember(user.getId(), Objects.requireNonNull(e.getGuild().getRolesByName("Muted", true).get(0))).queue();
                                    } catch (Exception exception) {
                                        e.getChannel().sendMessage("Whoops!  I could not mute them!  Please check that I have `Manage Roles` and that the `Muted` role exists and is below my highest role.").queue();
                                        break;
                                    }
                                }
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                        .setTitle("__**Mute Info**__");
                                e.getChannel().sendMessage(":zipper_mouth: " + targetPing + " was successfully muted!").queue();
                                try {
                                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                    textChannel.sendMessage(builder.build()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You do not have a channel named \"moderation-log\", the mute was not logged.").queue();
                                }
                                Objects.requireNonNull(e.getJDA().getUserById(target)).openPrivateChannel().complete().sendMessage("You were violatized in " + e.getGuild().getName() + " for " + reason + "!").queue();

                            }
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                    }
                    case "unmute" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.MESSAGE_MANAGE) || isDev) {
                            User user = null;
                            try {
                                user = e.getJDA().getUserById(target);
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You tried to unmute a non-existant user.  What a waste of your time.").queue();
                                break;
                            }
                            if (server.equals("670725611207262219")) {
                                try {
                                    e.getGuild().removeRoleFromMember(user.getId(), Objects.requireNonNull(e.getGuild().getRoleById("734697394389778462"))).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("How are we going to unmute nobody dum dum").queue();
                                    break;
                                }
                            } else {
                                try {
                                    e.getGuild().removeRoleFromMember(user.getId(), Objects.requireNonNull(e.getGuild().getRolesByName("Muted", true).get(0))).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("Whoops!  I could not unmute them!  Please check that I have `Manage Roles` and that the `Muted` role exists and is below my highest role and that the user is actually muted.").queue();
                                    break;
                                }
                            }
                            e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                    .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator(), false)
                                    .setTitle("__**Unmute Info**__");
                            e.getChannel().sendMessage(":white_check_mark: " + targetPing + " was successfully unmuted!").queue();
                            try {
                                TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                textChannel.sendMessage(builder.build()).queue();
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You do not have a channel named \"moderation-log\", the mute was not logged.").queue();
                            }
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                    }
                    case "kick" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.KICK_MEMBERS) || isDev) {
                            StringBuilder reason = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                reason.append(message[i]).append(" ");
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to kick yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot kick me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                User user = null;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried to kick a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                user.openPrivateChannel().complete().sendMessage("You were kicked from " + e.getGuild().getName() + " for " + reason + "!").queue();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getId() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                        .setTitle("__**Kick Info**__");
                                try {
                                    e.getGuild().kick((Member) user, reason.toString()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You failed to mention a specific user to kick, what are you, dumb?").queue();
                                    throw new IllegalArgumentException();
                                }
                                try {
                                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                    textChannel.sendMessage(builder.build()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("The kick was not logged, you must have a channel named \"moderation log\"").queue();
                                }
                                e.getChannel().sendMessage(":boot: " + targetPing + " was successfully kicked!").queue();
                            }
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                    }
                    case "ban" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.BAN_MEMBERS) || isDev) {
                            StringBuilder reason = new StringBuilder();
                            try {
                                for (int i = 2; i < message.length; i++) {
                                    reason.append(message[i]).append(" ");
                                }
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You must provide a reason!").queue();
                                throw new IllegalArgumentException();
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to ban yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot ban me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                User user = null;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried ban kick a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                Objects.requireNonNull(e.getJDA().getUserById(target)).openPrivateChannel().complete().sendMessage("You were banned from " + e.getGuild().getName() + " for " + reason + "!").queue();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getId() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                        .setTitle("__**BAN Info**__");
                                try {
                                    e.getGuild().ban((Member) user, 0, reason.toString()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You failed to mention a specific user to ban, what are you, dumb?").queue();
                                    throw new IllegalArgumentException();
                                }

                                try {
                                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                    textChannel.sendMessage(builder.build()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("The ban was not logged, you must have a channel named \"moderation log\"").queue();
                                }
                                e.getChannel().sendMessage(":hammer: **THE BAN HAMMER HAS SPOKEN** :hammer:\n" + targetPing + " was successfully banned!").queue();
                            }
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                    }
                    case "suggest" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Reporting a bug or requesting a new command?  Go to <https://github.com/Poseidon-I/ling-ling/wiki/This-Repository-is-for-Submitting-Bugs,-Feature-Requests,-or-Command-Requests> \n\nSuggesting a new autoresponse, command response, or word to blacklist?  Go to <https://forms.gle/LTqhVNdu7CgsrQzf7>").queue();
                    }
                    case "support" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Join the support server at discord.gg/gNfPwa8").queue();
                    }
                    case "fakeban" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage(":white_check_mark: " + targetPing + " was successfully banned!").queue();
                        try {
                            User send = e.getJDA().getUserById(target);
                            send.openPrivateChannel().complete().sendMessage("You were banned from " + e.getGuild().getName() + "!").queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                        }
                    }
                    case "fakemute" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage(":white_check_mark: " + targetPing + " was successfully muted!").queue();
                        try {
                            User send = e.getJDA().getUserById(target);
                            send.openPrivateChannel().complete().sendMessage("You were muted in " + e.getGuild().getName() + "!").queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                        }
                    }
                    case "fakekick" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage(":white_check_mark: " + targetPing + " was successfully kicked!").queue();
                        try {
                            User send = e.getJDA().getUserById(target);
                            send.openPrivateChannel().complete().sendMessage("You were kicked from " + e.getGuild().getName() + "!").queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                        }
                    }
                    case "fakewarn" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage(":white_check_mark: " + targetPing + " was successfully warned!").queue();
                        try {
                            User send = e.getJDA().getUserById(target);
                            send.openPrivateChannel().complete().sendMessage("You were warned in " + e.getGuild().getName() + "!").queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                        }
                    }
                    case "checkdm" -> {
                        e.getChannel().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        try {
                            User send = e.getJDA().getUserById(target);
                            send.openPrivateChannel().complete().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                        }
                    }
                    case "wound" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        if (e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&")) {
                            e.getChannel().sendMessage("Nice try but no").queue();
                            throw new IllegalArgumentException();
                        }
                        int i = random.nextInt(23);
                        if (i == 0) {
                            e.getChannel().sendMessage(targetPing + " thought it was a good idea to play the sousaphone after eating chili pepper").queue();
                        } else if (i == 1) {
                            e.getChannel().sendMessage(targetPing + "'s ears were blasted apart with a piccolo playing 10000 Hz at fortissimo").queue();
                        } else if (i == 2) {
                            e.getChannel().sendMessage("Ling Ling deemed " + targetPing + " unworthy of violin so he (she?) forced " + targetPing + " to play the viola.  Their ego was permanently damaged.").queue();
                        } else if (i == 3) {
                            e.getChannel().sendMessage("Ling Ling is a benevolent violinist god so nice try but no").queue();
                        } else if (i == 4) {
                            e.getChannel().sendMessage(targetPing + " was blinded because they used light mode").queue();
                        } else if (i == 5) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " started punching " + targetPing + ".").queue();
                        } else if (i == 6) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " somehow got " + targetPing + " into their Minecraft world and then " + targetPing + " was dumb enough to light TNT.").queue();
                        } else if (i == 7) {
                            e.getChannel().sendMessage(targetPing + " stuffed too much chili pepper down their throat and the results were rather explosive").queue();
                        } else if (i == 8) {
                            e.getChannel().sendMessage("A bomb detonated over " + targetPing + "'s head").queue();
                        } else if (i == 9) {
                            e.getChannel().sendMessage(targetPing + " destroyed Jacqueline's Stradivarius so Jacqueline destroyed " + target).queue();
                        } else if (i == 10) {
                            e.getChannel().sendMessage(targetPing + " tripped over a tripwire and fell into the Void").queue();
                        } else if (i == 11) {
                            e.getChannel().sendMessage(targetPing + " went to the zoo and got trampled by 20 elephants").queue();
                        } else if (i == 12) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " played a very high harmonic on their violin and exploded " + targetPing + "'s eardrums.").queue();
                        } else if (i == 13) {
                            e.getChannel().sendMessage(targetPing + " was run over by " + e.getAuthor().getName() + " because " + targetPing + " was crossing the street while playing a sousaphone.").queue();
                        } else if (i == 14) {
                            e.getChannel().sendMessage(targetPing + " fell off a cliff because they were not paying attention to where they were going.  Reason?  Using Simply Piano app (sacrilegious)").queue();
                        } else if (i == 15) {
                            e.getChannel().sendMessage(targetPing + " was whacked on the head with a viola for displeasing the conductor.").queue();
                        } else if (i == 16) {
                            e.getChannel().sendMessage(targetPing + " tried to steal 2000 pounds worth of gold from " + e.getAuthor().getName() + " but they were caught and ended up crushing themselves under the gold.").queue();
                        } else if (i == 17) {
                            e.getChannel().sendMessage(targetPing + " was attacked by " + e.getAuthor().getName() + ".  It was that simple").queue();
                        } else if (i == 18) {
                            e.getChannel().sendMessage("Why would you want to wound " + targetPing + " they haven't done anything wrong").queue();
                        } else if (i == 19) {
                            e.getChannel().sendMessage(targetPing + " tripped over " + e.getAuthor().getName() + "'s foot while reading music and walking").queue();
                        } else if (i == 20) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " clobbers " + targetPing + " with a clarinet because " + targetPing + " was being dumb").queue();
                        } else if (i == 21) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " slapped " + targetPing + " for playing the VIOLA").queue();
                        } else {
                            e.getChannel().sendMessage(targetPing + " stepped on a landmine and suffered major injuries.").queue();
                        }
                    }
                    case "insult" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        int i = random.nextInt(11);
                        if (i == 0) {
                            e.getChannel().sendMessage(targetPing + "'s violining skills are so bad even the violists were allowed to laugh at them").queue();
                        } else if (i == 1) {
                            e.getChannel().sendMessage("Why would you insult " + targetPing + ", they seem very nice").queue();
                        } else if (i == 2) {
                            e.getChannel().sendMessage(targetPing + " tried conducting an orchestra but their skills were so shitty not even Ling Ling could figure out what was going on").queue();
                        } else if (i == 3) {
                            e.getChannel().sendMessage(targetPing + " auditioned for Seattle Symphony but the audition panel just laughed at their horrible intonation").queue();
                        } else if (i == 4) {
                            e.getChannel().sendMessage(targetPing + " is so bad that they were forced to play the viola").queue();
                        } else if (i == 5) {
                            e.getChannel().sendMessage(targetPing + " played out of tune in front of the Berlin Philharmonic and they were ridiculed").queue();
                        } else if (i == 6) {
                            e.getChannel().sendMessage("During a music test " + targetPing + " was called out for cheating").queue();
                        } else if (i == 7) {
                            e.getChannel().sendMessage(targetPing + " abused a violin and Ling Ling came and punished them for their smol brane").queue();
                        } else if (i == 8) {
                            e.getChannel().sendMessage(targetPing + " ruined the annual orchestra concert and was ridiculed for playing a wrong note.").queue();
                        } else if (i == 9) {
                            e.getChannel().sendMessage(targetPing + " played the violin out of tune.  Ling Ling harassed them for the sin.").queue();
                        } else {
                            e.getChannel().sendMessage(targetPing + " tried playing their piccolo solo but it broke so many eardrums that the conductor threw them off the stage into the audience headfirst.  Everyone laughed.").queue();
                        }
                    }
                    case "joke" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        if (e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&")) {
                            e.getChannel().sendMessage("Nice try but no").queue();
                            throw new IllegalArgumentException();
                        }
                        int i = random.nextInt(23);
                        if (i == 0) {
                            e.getChannel().sendMessage("Q: What do a viola and a lawsuit have in common?\nA: ||Everyone is happy when the case is closed.||").queue();
                        } else if (i == 1) {
                            e.getChannel().sendMessage("Q: What do you get when a viola gets run over by a car?\nA:|| Peace||").queue();
                        } else if (i == 2) {
                            e.getChannel().sendMessage("Q: What's the difference between a violin and a viola?\nA1: ||A viola burns longer.||\nA2: ||You can tune a violin.||").queue();
                        } else if (i == 3) {
                            e.getChannel().sendMessage("Q: What's the definition of a minor second?\nA: ||Two violists playing in unison.||").queue();
                        } else if (i == 4) {
                            e.getChannel().sendMessage("Q: How was the canon invented?\nA: ||Two violists were trying to play the same passage together.||").queue();
                        } else if (i == 5) {
                            e.getChannel().sendMessage("Q: How can you tell when a violist is playing out of tune?\nA: ||The bow is moving.||").queue();
                        } else if (i == 6) {
                            e.getChannel().sendMessage("Q: What's the difference between a washing machine and a violist?\nA: ||Vibrato.||").queue();
                        } else if (i == 7) {
                            e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA: ||Play in the low register with a lot of wrong notes.||").queue();
                        } else if (i == 8) {
                            e.getChannel().sendMessage("Q: What is the range of a viola?\nA: ||As far as you can kick it.||").queue();
                        } else if (i == 9) {
                            e.getChannel().sendMessage("A violist and a cellist were standing on a sinking ship. \"Help!\" cried the cellist, \"I can't swim!\"\n\"Don't worry,\" said the violist, \"||Just fake it.||\"").queue();
                        } else if (i == 10) {
                            e.getChannel().sendMessage("Q: What's the difference between the first and last desk of the viola section?\nA: ||About half a bar.||").queue();
                        } else if (i == 11) {
                            e.getChannel().sendMessage("Q: How do you get two violists to play in tune with each other?\nA: ||Ask one of them to leave.||").queue();
                        } else if (i == 12) {
                            e.getChannel().sendMessage("A group of terrorists hijacked a plane full of violists. They called down to ground control with their list of demands and added that if their demands weren't met, they would ||release one violist every hour.||").queue();
                        } else if (i == 13) {
                            e.getChannel().sendMessage("Q: What's the difference between a viola and a trampoline?\nA: ||You take your shoes off to jump on a trampoline.||").queue();
                        } else if (i == 14) {
                            e.getChannel().sendMessage("Q: What's the difference between a viola and an onion?\nA: ||No one cries when you cut up a viola.||").queue();
                        } else if (i == 15) {
                            e.getChannel().sendMessage("Q: Anything\nA: Viola.\n||*intense wheezing*||").queue();
                        } else if (i == 16) {
                            e.getChannel().sendMessage("Q: What's the difference between a pizza and a violist? \nA: ||A pizza can feed a family of four.||").queue();
                        } else if (i == 17) {
                            e.getChannel().sendMessage("Q: Why don't violists play hide and seek? \nA: ||Because no one would look for them.||").queue();
                        } else if (i == 18) {
                            e.getChannel().sendMessage("Q : Why bAss and not viola?\nA: ||Because viola isn't badAss.||").queue();
                        } else if (i == 19) {
                            e.getChannel().sendMessage("Q: Did you hear about the violist who played in tune?\nA: ||Neither did I.||").queue();
                        } else if (i == 20) {
                            e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA1: ||Sit in the back and don't play.||\nA2: ||Play in the front with a lot of wrong notes.||").queue();
                        } else if (i == 21) {
                            e.getChannel().sendMessage("Q: Why is viola illegal?\nA: ||It viola-ted the Terms of Service of the musician world.||").queue();
                        } else {
                            e.getChannel().sendMessage("Q: How make someone practice the viola?\nA: ||Tell them a violinist is getting better than them.||").queue();
                        }
                    }
                    case "poll" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        int i = 7;
                        StringBuilder send = new StringBuilder("**POLL:  ");
                        try {
                            while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                                send.append(fullMessage.charAt(i));
                                i++;
                            }
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("You need to end your title with a `\"`, or you did not properly start your title with `\"`").queue();
                            break;
                        }
                        send.append("**\n`A:` ");
                        i += 3;
                        int options = 1;
                        char character = 'A';
                        try {
                            while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                                if (fullMessage.charAt(i) == ';') {
                                    i += 2;
                                    options++;
                                    character++;
                                    send.append("\n`").append(character).append(":` ");
                                } else {
                                    send.append(fullMessage.charAt(i));
                                    i++;
                                }
                            }
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("You need to end your options portion with a `\"`, or you did not properly start your options portion with `\"`").queue();
                            break;
                        }
                        if (options > 20) {
                            e.getChannel().sendMessage("because discord we cannot allow messages with more than 20 options").queue();
                        } else {
                            send.insert(0, options + " ");
                            e.getChannel().sendMessage(send.toString()).queue();
                        }
                    }
                    case "emojify" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        StringBuilder convert = new StringBuilder("> ");
                        if (message.length == 1) {
                            e.getChannel().sendMessage("how are we going to emojify nothing dum dum").queue();
                        }
                        for (int i = 1; i < message.length; i++) {
                            if (i + 1 == message.length) {
                                convert.append(message[i]);
                            } else {
                                convert.append(message[i]).append(" ");
                            }
                        }
                        convert = new StringBuilder(convert.toString().toLowerCase());
                        StringBuilder send = new StringBuilder();
                        for (int i = 2; i < convert.length(); i++) {
                            char cur = convert.charAt(i);
                            if (cur == ' ') {
                                send.append("<:linglingclock:747499551451250730> ");
                            } else if (cur == '1') {
                                send.append(":one: ");
                            } else if (cur == '2') {
                                send.append(":two: ");
                            } else if (cur == '3') {
                                send.append(":three: ");
                            } else if (cur == '4') {
                                send.append(":four: ");
                            } else if (cur == '5') {
                                send.append(":five: ");
                            } else if (cur == '6') {
                                send.append(":six: ");
                            } else if (cur == '7') {
                                send.append(":seven: ");
                            } else if (cur == '8') {
                                send.append(":eight: ");
                            } else if (cur == '9') {
                                send.append(":nine: ");
                            } else if (cur == '0') {
                                send.append(":zero: ");
                            } else if (cur == '?') {
                                send.append(":grey_question: ");
                            } else if (cur == '!') {
                                send.append(":grey_exclamation: ");
                            } else {
                                send.append(":regional_indicator_").append(cur).append(": ");
                            }
                        }
                        send.append("\n<@").append(e.getAuthor().getId()).append(">");
                        try {
                            e.getChannel().sendMessage(send.toString()).queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Your message ended up being over 2000 characters, try shortening it.").queue();
                        }
                    }
                    case "invite" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        EmbedBuilder builder = new EmbedBuilder()
                                .setColor(Color.BLUE)
                                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                .addField("__How to invite the bot to your server.__", "You can add the bot to your server using the below link:\nhttps://discord.com/api/oauth2/authorize?client_id=733409243222507670&permissions=335899718&scope=bot", false);
                        e.getChannel().sendMessage(builder.build()).queue();
                    }
                    case "rules" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("```LING LING BOT RULES```\n" +
                                "1. Do not quickly spam commands.  Please wait for the bot to respond before trying to run the command again.  If you spam, we warn.\n" +
                                "2. Do not test triggers ANYWHERE except for a channel designated as #\uD83E\uDD2Cspam.  Testing triggers also includes spamming known triggers over and over and can result in a mute.\n" +
                                "3. Commands that DM users like `!fakeban` should ONLY BE USED ONCE IN A WHILE **AS A JOKE**.  Spamming these commands to spam a user's DMs will also result in a mute.\n" +
                                "4. Do not come to me complaining about DMs from the bot unless the user is deliberately spamming the command.\n" +
                                "5. Yes, the bot does have a little bit of swearing and some minor annoying aspects but if you can't take an occasional DM or f-bomb then you really are a wimp.").queue();
                    }
                    case "vote" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("You can vote for the bot here: https://top.gg/bot/733409243222507670/vote.  Voting currently does not offer rewards but as soon as top.gg API is figured out, voting will unlock access to one use of `!rehearse`.\n\nWant to vote for the support server?  Vote at https://top.gg/servers/670725611207262219/vote for a 10% boost to `!practice`, `!rehearse`, and `!perform` in the support server!").queue();
                    }
                }
            }
        }
        String[] data = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt"));
            data = br.readLine().split(" ");
            br.close();
        } catch (Exception exception) {
            //nothing here lol
        }
        if (e.getChannel().getName().contains("announcements") && data[1].equals("true")) {
            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1FB").queue();
            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EE").queue();
            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F4").queue();
            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F1").queue();
            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1E6").queue();
        } else {
            if (random.nextDouble() <= 0.025) {
                if (server.equals("679237495333847060")) {
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F7").queue(); //r
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EE").queue(); //i
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1E8").queue(); //c
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EA").queue(); //e
                } else {
                    try {
                        TextChannel textChannel = e.getGuild().getTextChannelById("734697510517342309");
                        assert textChannel != null;
                        textChannel.sendMessage("annoy<@&734697394389778462>").queue();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                    try {
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1FB").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EE").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F4").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F1").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1E6").queue();
                    } catch (Exception exception1) {
                        e.getChannel().sendMessage("<@" + e.getAuthor().getName() + "> was probably being a pussy and blocked the bot.  Or I happened to try to react to a deleted message.").queue();
                    }
                }
            }
        }
    }
}