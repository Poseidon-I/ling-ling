package processes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LevelCommands {
    public LevelCommands(GuildMessageReceivedEvent e, String[] message) {
        BufferedReader reader;
        switch (message[0]) {
            case "rank" -> {
                if (message.length == 1) {
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt"));
                        String[] data = reader.readLine().split(" ");
                        e.getChannel().sendMessage("**__" + e.getAuthor().getName() + "'s Stats__**\nLevel " + data[0] + "\n" + data[1] + "/" + (Integer.parseInt(data[0]) + 1) * 100 + " XP").queue();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                } else {
                    String id = "";
                    try {
                        id = e.getMessage().getMentionedUsers().get(0).getId();
                    } catch(Exception exception) {
                        id = message[1];
                    }
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + id + ".txt"));
                        String[] data = reader.readLine().split(" ");
                        e.getChannel().sendMessage("**__" + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + "'s Stats__**\nLevel " + data[0] + "\n" + data[1] + "/" + (Integer.parseInt(data[0]) + 1) * 100 + " XP").queue();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This save file does not exist!").queue();
                    }
                }
            }
            case "levels", "levellb" -> {
                String[] entry = new String[]{"null#0000 `0`: Level 0 - 0 XP \n", "null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n","null#0000 `0`: Level 0 - 0 XP \n"};
                List<Member> list = e.getGuild().getMembers();
                for (Member user : list) {
                    String currentData;
                    if (user.getUser().isBot()) {
                        continue;
                    }
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + user.getId() + ".txt"));
                        currentData = reader.readLine();
                        reader.close();
                    } catch (Exception exception) {
                        continue;
                    }
                    assert currentData != null;
                    String[] temp = currentData.split(" ");
                    int level = Integer.parseInt(temp[0]);
                    int xp = Integer.parseInt(temp[1]);
                    if (level == 0 && xp == 0) {
                        continue;
                    }
                    for (int i = 0; i < 10; i++) {
                        if (level > Integer.parseInt(entry[i].split(" ")[3])) {
                            System.arraycopy(entry, i, entry, i + 1, 9 - i);
                            String name = user.getUser().getName().replace(' ', '-');
                            entry[i] = name + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: Level " + level + " - " + xp + " XP\n";
                            break;
                        } else if(level == Integer.parseInt(entry[i].split(" ")[3]) && xp > Integer.parseInt(entry[i].split(" ")[5])) {
                            System.arraycopy(entry, i, entry, i + 1, 9 - i);
                            String name = user.getUser().getName().replace(' ', '-');
                            entry[i] = name + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: Level " + level + " - " + xp + " XP\n";
                            break;
                        }
                    }
                }
                StringBuilder board = new StringBuilder();
                for (int i = 0; i < 10; i++) {
                    if (entry[i].contains("#0000") || entry[i].contains("<@0>")) {
                        break;
                    }
                    board.append("**").append(i + 1).append(".** ").append(entry[i]);
                }
                EmbedBuilder builder = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                        .setTitle("__**XP Leaderboard**__")
                        .addField("Most Active Users in " + e.getGuild().getName(), board.toString(), false);
                e.getChannel().sendMessage(builder.build()).queue();
            }
            case "rolerewards" -> {
                if(message.length == 1) {
                    String[] data;
                    HashMap<Integer, Role> map = new HashMap<>();
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "rewards.txt"));
                        while (true) {
                            try {
                                data = reader.readLine().split(" ");
                                map.put(Integer.parseInt(data[0]), e.getGuild().getRoleById(data[1]));
                            } catch (Exception exception) {
                                break;
                            }
                        }
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("Something went wrong when creating the file, please turn leveling off and back on.  Your data will not be lost.").queue();
                        throw new IllegalArgumentException();
                    }
                    EmbedBuilder builder = new EmbedBuilder()
                            .setFooter("Ling Ling Bot", e.getJDA().getSelfUser().getAvatarUrl())
                            .setColor(Color.BLUE);
                    StringBuilder string = new StringBuilder();
                    for(Map.Entry<Integer, Role> entry : map.entrySet()) {
                        string.append("Level ").append(entry.getKey()).append(" - ").append(entry.getValue().getAsMention()).append("\n");
                    }
                    builder.addField("**__Role Rewards for " + e.getGuild().getName() + "__**", string.toString(), false);
                    e.getChannel().sendMessage(builder.build()).queue();
                }
            }
        }
    }
}