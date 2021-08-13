package processes;

import dev.Luthier;
import dev.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class DevCommands {
    public DevCommands(GuildMessageReceivedEvent e, int permLevel) {
        //1 - mods, 2 - admins, 3 - devs
        String[] message = e.getMessage().getContentRaw().split(" ");
        BufferedReader reader;
        PrintWriter writer;
        switch (message[0]) {
            case "!updateroles" -> {
                if(permLevel == 3) {
                    if(e.getGuild().getId().equals("670725611207262219")) {
                        List<Member> list = e.getGuild().getMembers();
                        for(Member member : list) {
                            List<Role> list2 = member.getRoles();
                            try {
                                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + member.getId() + ".txt"));
                                String[] data = reader.readLine().split(" ");
                                reader.close();
                                if (list2.contains(e.getGuild().getRoleById("852752096733429781"))) {
                                    data[96] = "true";
                                }
                                if (list2.contains(e.getGuild().getRoleById("734697410273607751"))) {
                                    data[97] = "1.25";
                                } else if (list2.contains(e.getGuild().getRoleById("845121274958184499"))) {
                                    data[97] = "1.2";
                                } else if (list2.contains(e.getGuild().getRoleById("845121187741958166"))) {
                                    data[97] = "1.15";
                                } else if (list2.contains(e.getGuild().getRoleById("734697411074719765"))) {
                                    data[97] = "1.11";
                                } else if (list2.contains(e.getGuild().getRoleById("734697411783688245"))) {
                                    data[97] = "1.075";
                                } else if (list2.contains(e.getGuild().getRoleById("734697412865818645"))) {
                                    data[97] = "1.045";
                                } else if (list2.contains(e.getGuild().getRoleById("734697413901680691"))) {
                                    data[97] = "1.02";
                                } else {
                                    data[97] = "1";
                                }
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + member.getId() + ".txt")));
                                writer.print(data[0]);
                                for(int i = 1; i < data.length; i ++) {
                                    writer.print(" " + data[i]);
                                }
                                writer.close();
                            } catch(Exception exception) {
                                //nothing here lol
                            }
                        }
                        e.getChannel().sendMessage("Successfully force-updated role multipliers!").queue();
                    } else {
                        e.getChannel().sendMessage("You can't use this command here!  Go to the support server smh.").queue();
                    }
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!setpermlevel" -> {
                if(permLevel == 3) {
                    String target = message[1];
                    int newRank = Integer.parseInt(message[2]);
                    if (newRank >= 0 && newRank <= 2) {
                        try {
                            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                            String[] data = reader.readLine().split(" ");
                            reader.close();
                            data[65] = String.valueOf(newRank);
                            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt")));
                            writer.print(data[0]);
                            for (int i = 1; i < data.length; i++) {
                                writer.print(" " + data[i]);
                            }
                            writer.close();
                            e.getChannel().sendMessage("Set the permission level for <@" + target + "> to `" + newRank + "`").queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Invalid user provided!").queue();
                        }
                    } else {
                        e.getChannel().sendMessage("Invalid level provided!\n\nValid levels:\n`0`: No Permissions\n`1`: Mod Permissions\n`2`: Admin Permissions").queue();
                    }
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not nave permission to run this command.").queue();
                }
            }
            case "!status" -> {
                if(permLevel == 3) {
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
                if(permLevel == 3) {
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
                if(permLevel >= 1) {
                    String userData;
                    String target = message[1];
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
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
                if(permLevel >= 2) {
                    String id = message[1];
                    StringBuilder userData = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        userData.append(message[i]).append(" ");
                    }
                    userData.deleteCharAt(userData.length() - 1);
                    try {
                        User user = e.getJDA().getUserById(id);
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt"));
                        String oldData = reader.readLine();
                        reader.close();
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
                        writer.print(userData);
                        writer.close();
                        e.getChannel().sendMessage("Successfully edited the data of " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                        assert user != null;
                        EmbedBuilder builder = new EmbedBuilder()
                                .setColor(Color.BLUE)
                                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nOld Data: " + oldData + "\nNew Data: " + userData, false)
                                .setTitle("__**Save File Direct Edit Info**__");
                        Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This user save doesn't exist!").queue();
                    }
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!give" -> {
                if(permLevel >= 1) {
                    new Give(e);
                } else {
                    e.getChannel().sendMessage("**403 FORBIDDEN**\nYou do not have permission to run this command.").queue();
                }
            }
            case "!luthier" -> {
                if(permLevel >= 2) {
                    new Luthier(e);
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
            case "!resetincomes" -> {
                if(permLevel >= 2) {
                    new ResetIncomes(e);
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
                            if(i == 92) {
                                writer.print(" 0");
                            } else {
                                writer.print(" " + data[i]);
                            }
                        }
                        writer.close();
                    } catch (Exception exception) {
                        System.out.println();
                    }
                }
                e.getChannel().sendMessage("Custom command completed!").queue();
            }*/
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
            case "!restart" -> {
                if(permLevel == 3) {
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