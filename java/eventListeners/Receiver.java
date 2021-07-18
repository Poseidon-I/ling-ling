package eventListeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import processes.DevCommands;
import processes.HourlyIncome;
import processes.Luthier;
import processes.RegularCommands;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Receiver extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        boolean isBanned = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt"));
            String[] data = reader.readLine().split(" ");
            if (data.length == 1 && data[0].equals("BANNED")) {
                isBanned = true;
            }
            reader.close();
        } catch (Exception exception) {
            //nothing here lol
        }
        Random random = new Random();
        BufferedReader reader;
        PrintWriter writer;
        String[] message = e.getMessage().getContentRaw().toLowerCase().split(" ");
        String[] data;
        boolean isDev = e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612");

        //LOAD SERVER MEMBERS ONLY ONCE
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\loadedservers.txt"));
            String line = reader.readLine();
            reader.close();
            try {
                List<String> loaded = Arrays.asList(line.split(" "));
                if (!loaded.contains(e.getGuild().getId()) || e.getJDA().getSelfUser().getId().equals("772582345944334356")) {
                    e.getGuild().loadMembers();
                    line += " " + e.getGuild().getId();
                }
            } catch (Exception exception) {
                e.getGuild().loadMembers();
                line = e.getGuild().getId();
            }
            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\loadedservers.txt")));
            writer.print(line);
            writer.close();
        } catch (Exception exception1) {
            //nothing here lol
        }

        //HOURLY
        long time = 0;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\hourly.txt"));
            time = Long.parseLong(reader.readLine());
            reader.close();
        } catch (Exception exception) {
            //nothing here lol
        }
        if (System.currentTimeMillis() > time) {
            time += 3600000;
            new HourlyIncome();
            Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Hourly Incomes Sent!").queue();
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\hourly.txt")));
                pw.print(time);
                pw.close();
            } catch (Exception exception) {
                //nothing here lol
            }
            if (time % 86400000 == 0) {
                File directory = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
                File[] files = directory.listFiles();
                assert files != null;
                for(File file : files) {
                    try {
                        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                        data = reader.readLine().split(" ");
                        reader.close();
                    } catch (Exception exception) {
                        continue;
                    }
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                        data[48] = "false";
                        data[49] = "false";
                        data[92] = "false";
                        for (String datum : data) {
                            writer.print(datum + " ");
                        }
                        writer.close();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                }
                Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Daily and Gifts Reset!").queue();
            }
        }
        if (!isBanned) {
            //LUTHIER
            if (!e.getAuthor().isBot()) {
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                    data = reader.readLine().split(" ");
                    reader.close();
                    new Luthier(e, data);
                } catch (Exception exception) {
                    //nothing here lol
                }
            }

            //DEV COMMANDS
            if (isDev) {
                new DevCommands(e, message, 2);
            } else if (e.getAuthor().getId().equals("497916210315264014")) {
                new DevCommands(e, message, 1);
            } else if (e.getAuthor().getId().equals("433715464674476034")) {
                new DevCommands(e, message, 0);
            } else {
                new DevCommands(e, message, -1);
            }

            //ALL COMMANDS
            if (!e.getAuthor().isBot()) {
                String server = "";
                char prefix = '!';
                if (!isDev) {
                    try {
                        server = e.getGuild().getId();
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt"));
                        prefix = (char) reader.read();
                        reader.close();
                    } catch (Exception exception) {
                        File file = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt");
                        try {
                            file.createNewFile();
                            writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                            writer.print('!');
                            writer.close();
                        } catch (Exception exception1) {
                            //nothing here lol
                        }
                    }
                }
                try {
                    if (e.getMessage().getContentRaw().equals("!prefix") || e.getMessage().getMentionedUsers().get(0).getId().equals("733409243222507670")) {
                        e.getChannel().sendMessage("Hello!  My prefix in this server is `" + prefix + "`\nIf you have other issues, run `" + prefix + "support` to get an invite to the support server!").queue();
                    }
                } catch (Exception exception) {
                    //nothing here lol
                }
                try {
                    if (message[0].charAt(0) == prefix) {
                        String target = "";
                        String targetPing = "";
                        try {
                            target = e.getMessage().getMentionedUsers().get(0).getId();
                            targetPing = e.getMessage().getMentionedUsers().get(0).getName();
                        } catch (Exception exception) {
                            try {
                                target = message[1];
                                targetPing = message[1];
                            } catch (Exception exception1) {
                                if (random.nextDouble() < 0.025) {
                                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F3BB").queue();
                                }
                            }
                        }
                        if (e.getMessage().getContentRaw().contains("@everyone") || e.getMessage().getContentRaw().contains("@here") || e.getMessage().getContentRaw().contains("<@&")) {
                            e.getChannel().sendMessage("why the hell did you ping here, everyone, or a role dumbass").queue();
                            throw new IllegalArgumentException();
                        } else if (e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&")) {
                            e.getChannel().sendMessage("Nice try but no").queue();
                            throw new IllegalArgumentException();
                        } else if (targetPing.contains("@everyone") || targetPing.contains("@here") || targetPing.contains("<@&")) {
                            e.getChannel().sendMessage("Nice try but no").queue();
                            throw new IllegalArgumentException();
                        }
                        message[0] = message[0].substring(1);
                        new RegularCommands(e, message, prefix, isDev, target, targetPing);
                    }
                } catch (Exception exception) {
                    //nothing here lol
                }
            }
        }
    }
}