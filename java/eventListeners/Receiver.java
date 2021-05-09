package eventListeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import processes.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ConstantConditions")
public class Receiver extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Random random = new Random();
        BufferedReader reader;
        PrintWriter writer;
        String[] message = e.getMessage().getContentRaw().toLowerCase().split(" ");
        String[] data = new String[0];
        boolean isDev = e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612") || e.getAuthor().getId().equals("733409243222507670");

        //LOAD SERVER MEMBERS ONLY ONCE
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\loadedservers.txt"));
            String line = reader.readLine();
            reader.close();
            try {
                List<String> loaded = Arrays.asList(line.split(" "));
                if(!loaded.contains(e.getGuild().getId()) || e.getJDA().getSelfUser().getId().equals("772582345944334356")) {
                    e.getGuild().loadMembers();
                    line += " " + e.getGuild().getId();
                }
            } catch(Exception exception) {
                e.getGuild().loadMembers();
                line = e.getGuild().getId();
            }
            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\loadedservers.txt")));
            writer.print(line);
            writer.close();
        } catch (Exception exception1) {
            //nothing here lol
        }

        //HOURLY
        long time = 0;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\hourly.txt"));
            time = Long.parseLong(reader.readLine());
            reader.close();
        } catch (Exception exception) {
            //nothing here lol
        }
        if (System.currentTimeMillis() > time) {
            time += 3600000;
            new HourlyIncome();
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\hourly.txt")));
                pw.print(time);
                pw.close();
            } catch (Exception exception) {
                //nothing here lol
            }
            if(time % 86400000 == 0) {
                e.getJDA().getGuildById("709632179340312597").getTextChannelById("717484601328533565").sendMessage("Bot Restarting!").queue();
                e.getJDA().shutdownNow();
                new StartBot();
            }
        }

        //LUTHIER
        if(!e.getAuthor().isBot()) {
            try {
                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                data = reader.readLine().split(" ");
                reader.close();
                new Luthier(e, data);
            } catch (Exception exception) {
                //nothing here lol
            }
        }

        //DEV COMMANDS
        if(isDev) {
            new DevCommands(e, message, 2);
        } else {
            try {
                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Admins.txt"));
                String admins = reader.readLine();
                reader.close();
                if(admins.contains(e.getAuthor().getId())) {
                    new DevCommands(e, message, 1);
                } else {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Mods.txt"));
                    String mods = reader.readLine();
                    reader.close();
                    if(mods.contains(e.getAuthor().getId())) {
                        new DevCommands(e, message, 0);
                    } else {
                        new DevCommands(e, message, -1);
                    }
                }
            } catch(Exception exception) {
                //nothing here lol
            }
        }

        //ALL COMMANDS
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
            data = reader.readLine().split(" ");
            reader.close();
        } catch (Exception exception) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt");
            try {
                file.createNewFile();
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                pw.print("true true false");
                pw.close();
            } catch (Exception exception1) {
                //nothing here lol
            }
        }
        if (!e.getAuthor().isBot()) {
            if (data[2].equals("true") && !e.getAuthor().isBot()) {
                new Leveling(e);
            }
            if (data[0].equals("true") && !e.getAuthor().isBot()) {
                new Autoresponse(e);
            }
            String server = "";
            char prefix = '!';
            if(!isDev) {
                try {
                    server = e.getGuild().getId();
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt"));
                    prefix = (char) reader.read();
                    reader.close();
                } catch (Exception exception) {
                    File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt");
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
                if (e.getMessage().getMentionedUsers().get(0).getId().equals("733409243222507670")) {
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
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt"));
                        data = reader.readLine().split(" ");
                        reader.close();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                    try {
                        target = e.getMessage().getMentionedUsers().get(0).getId();
                        targetPing = e.getMessage().getMentionedUsers().get(0).getName();
                    } catch (Exception exception) {
                        try {
                            target = message[1];
                            targetPing = message[1];
                        } catch (Exception exception1) {
                            if (data[1].equals("true") && random.nextDouble() < 0.025) {
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
                    new RegularCommands(e, message, prefix, isDev, target, targetPing, data);
                }
            } catch(Exception exception) {
                //nothing here lol
            }
        }
        //V I O L A
        /*assert data != null;
        if (data[1].equals("true")) {
            if (e.getChannel().getName().contains("announcement")) {
                try {
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1FB").queue();
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EE").queue();
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F4").queue();
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F1").queue();
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1E6").queue();
                } catch(Exception exception) {
                    //nothing here lol
                }
            } else {
                try {
                    if (random.nextDouble() <= 0.025) {
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1FB").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EE").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F4").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F1").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1E6").queue();
                    }
                }catch(Exception exception) {
                    //nothing here lol
                }
            }
        }*/
    }
}