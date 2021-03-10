package processes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Random;

public class Economy {
    public static void leaderboard(String type, GuildMessageReceivedEvent e, int dataPosition, boolean global, long userNum) {
        BufferedReader reader;
        File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        String[] entry = new String[0];
        long place = 1;
        if (files != null) {
            if (global) {
                entry = new String[]{e.getAuthor().getAsMention() + ": " + userNum + " " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n"};
                for (File file : files) {
                    String currentData;
                    String user;
                    int pos;
                    try {
                        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                        currentData = reader.readLine();
                        reader.close();
                    } catch (Exception exception) {
                        continue;
                    }
                    user = file.getName();
                    pos = user.lastIndexOf(".");
                    user = user.substring(0, pos);
                    assert currentData != null;
                    String[] temp = currentData.split(" ");
                    long num = Long.parseLong(temp[dataPosition]);
                    if (num == 0) {
                        continue;
                    }
                    for (int i = 0; i < 10; i++) {
                        if (num > Long.parseLong(entry[i].split(" ")[1]) && !user.equals(e.getAuthor().getId())) {
                            System.arraycopy(entry, i, entry, i + 1, 9 - i);
                            entry[i] = "<@" + user + ">: " + num + " " + type + "\n";
                            if(num > userNum) {
                                place++;
                            }
                            break;
                        }
                    }
                }
            } else {
                /*entry = new String[]{e.getAuthor().getName().replace(' ', '-') + "#" + e.getAuthor().getDiscriminator() + " `" + e.getAuthor().getId() + "`: " + userNum + " " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n"};
                List<Member> list = e.getGuild().getMembers();
                for (Member user : list) {
                    String currentData;
                    if (user.getUser().isBot()) {
                        continue;
                    }
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt"));
                        currentData = reader.readLine();
                        reader.close();
                    } catch (Exception exception) {
                        continue;
                    }
                    assert currentData != null;
                    String[] temp = currentData.split(" ");
                    long num = Long.parseLong(temp[dataPosition]);
                    if (num == 0) {
                        continue;
                    }
                    for (int i = 0; i < 10; i++) {
                        if (num > Long.parseLong(entry[i].split(" ")[2]) && !user.getId().equals(e.getAuthor().getId())) {
                            System.arraycopy(entry, i, entry, i + 1, 9 - i);
                            String name = user.getUser().getName().replace(' ', '-');
                            entry[i] = name + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: " + num + " " + type + "\n";
                            if(num > userNum) {
                                place++;
                            }
                            break;
                        }
                    }
                }*/
                e.getChannel().sendMessage("Local leaderboards have been temporarily disabled as a fix for crashes is being reviewed.").queue();
                throw new IllegalArgumentException();
            }
        }
        StringBuilder board = new StringBuilder();
        String item = "";
        String measurement = "";
        switch (type) {
            case ":military_medal:" -> {
                measurement = "Ling Ling Medal ";
                item = "Most Recognized Users ";
            }
            case ":clock2:" -> {
                measurement = "Daily Streak ";
                item = "Longest Daily Streaks ";
            }
            case ":violin:" -> {
                measurement = "Violin ";
                item = "Richest Users ";
            }
            case ":violin:/hour" -> {
                measurement = "Income ";
                item = "Highest Hourly Incomes ";
            }
        }
        for (int i = 0; i < 10; i++) {
            if (entry[i].contains("#0000") || entry[i].contains("<@0>")) {
                break;
            }
            board.append("**").append(i + 1).append(".** ").append(entry[i]);
        }
        if(place >= 11) {
            board.append("\n**").append(place).append(". You** ").append(userNum).append(" ").append(type);
        }
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                .setTitle("__**" + measurement + "Leaderboard**__");
        if (global) {
            builder.addField(item + "in the World", board.toString(), false);
        } else {
            builder.addField(item + "in " + e.getGuild().getName(), board.toString(), false);
        }
        e.getChannel().sendMessage(builder.build()).queue();
    }

    public static long calculateAmount(GuildMessageReceivedEvent e, String[] data, long base) {
        if (e.getGuild().getId().equals("670725611207262219")) {
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("755910677075460206"))) {
                base *= 1.1;
            }
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697410273607751"))) {
                base *= 1.1;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697411074719765"))) {
                base *= 1.065;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697411783688245"))) {
                base *= 1.04;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697412865818645"))) {
                base *= 1.02;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697413901680691"))) {
                base *= 1.01;
            }
        }
        if(data[57].equals("true")) {
            base *= 2;
        }
        return base;
    }

    public Economy(GuildMessageReceivedEvent e, String[] message, char prefix, String target) {
        long time = System.currentTimeMillis();
        BufferedReader reader;
        PrintWriter writer;
        boolean hasData;
        Random random = new Random();
        String[] data;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt"));
            hasData = true;
            data = reader.readLine().split(" ");
            reader.close();
        } catch (IOException ioException) {
            hasData = false;
            data = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "false", "0", "0", "0", "0", "0", "0", "0", "false", "false", "false", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "false", "false", "false", "false", "false", "false", "0", "0", "0", "0"};
        }
        long violins = Long.parseLong(data[0]);
        long workC = Long.parseLong(data[1]);
        long workL = Long.parseLong(data[2]);
        long gambleC = Long.parseLong(data[3]);
        long gambleL = Long.parseLong(data[4]);
        long robC = Long.parseLong(data[5]);
        long robL = Long.parseLong(data[6]);
        long rehearseC = Long.parseLong(data[7]);
        long performC = Long.parseLong(data[8]);
        boolean ownInsurance1 = Boolean.parseBoolean(data[9]);
        boolean ownInsurance2 = Boolean.parseBoolean(data[10]);
        long activeInsurance = Long.parseLong(data[11]);
        long hourlyIncome = Long.parseLong(data[12]);
        long violinQuality = Long.parseLong(data[13]);
        long skillLevel = Long.parseLong(data[14]);
        long lessonQuality = Long.parseLong(data[15]);
        long stringQuality = Long.parseLong(data[16]);
        long bowQuality = Long.parseLong(data[17]);
        boolean hasMath = Boolean.parseBoolean(data[18]);
        boolean hasOrchestra = Boolean.parseBoolean(data[19]);
        boolean piccolo = Boolean.parseBoolean(data[20]);
        long flute = Long.parseLong(data[21]);
        long oboe = Long.parseLong(data[22]);
        long clarinet = Long.parseLong(data[23]);
        long bassoon = Long.parseLong(data[24]);
        boolean contrabassoon = Boolean.parseBoolean(data[25]);
        long horn = Long.parseLong(data[26]);
        long trumpet = Long.parseLong(data[27]);
        long trombone = Long.parseLong(data[28]);
        long tuba = Long.parseLong(data[29]);
        long timpani = Long.parseLong(data[30]);
        long percussion = Long.parseLong(data[31]);
        long first = Long.parseLong(data[32]);
        long second = Long.parseLong(data[33]);
        long cello = Long.parseLong(data[34]);
        long stringBass = Long.parseLong(data[35]);
        long piano = Long.parseLong(data[36]);
        boolean harp = Boolean.parseBoolean(data[37]);
        long soprano = Long.parseLong(data[38]);
        long alto = Long.parseLong(data[39]);
        long tenor = Long.parseLong(data[40]);
        long bass = Long.parseLong(data[41]);
        long soloists = Long.parseLong(data[42]);
        long hallLevel = Long.parseLong(data[43]);
        long conductor = Long.parseLong(data[44]);
        long advertising = Long.parseLong(data[45]);
        long tickets = Long.parseLong(data[46]);
        long streak = Long.parseLong(data[47]);
        long dailyC = Long.parseLong(data[48]);
        long dailyExp = Long.parseLong(data[49]);
        boolean faster = Boolean.parseBoolean(data[50]);
        long rice = Long.parseLong(data[51]);
        long thirdP = Long.parseLong(data[52]);
        long secondP = Long.parseLong(data[53]);
        long firstP = Long.parseLong(data[54]);
        long medals = Long.parseLong(data[55]);
        boolean extraIncome = Boolean.parseBoolean(data[56]);
        boolean extraCommandIncome = Boolean.parseBoolean(data[57]);
        boolean higherWinrate = Boolean.parseBoolean(data[58]);
        boolean higherRobrate = Boolean.parseBoolean(data[59]);
        boolean stealShield = Boolean.parseBoolean(data[60]);
        boolean violinDuplicator = Boolean.parseBoolean(data[61]);
        long tea = Long.parseLong(data[62]);
        long blessing = Long.parseLong(data[63]);
        long realIncome = Long.parseLong(data[65]);
        long scaleC = Long.parseLong(data[64]);
        if (message[0].equals("start") && !hasData) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt");
            try {
                file.createNewFile();
            } catch (Exception exception) {
                e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
            }
            PrintWriter newData;
            try {
                newData = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                newData.print("0 " + time + " 0 " + time + " 0 " + time + " 0 " + time + " " + time + " false false 0 0 0 0 0 0 0 false false false 0 0 0 0 false 0 0 0 0 0 0 1 1 0 0 0 false 0 0 0 0 0 0 0 0 0 0 " + time + " " + (time + 86400000) + " false 0 0 0 0 0 false false false false false false 0 0");
                newData.close();
                e.getChannel().sendMessage("Your profile has been created!  Run `" + prefix + "help 3` for a list of economy commands!").queue();
            } catch (Exception exception) {
                e.getChannel().sendMessage("Something went wrong creating the file.").queue();
            }
        } else if (message[0].equals("start") && hasData) {
            e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
        }
        long workCost = (long) (Math.pow(1.125, workL) * 250);
        long gambleCost = (long) (Math.pow(1.25, gambleL) * 1000);
        long robCost = (long) (Math.pow(1.4, robL) * 5000);
        long violinCost = (long) (Math.pow(3.5, violinQuality) * 1000);
        long skillCost = (long) (Math.pow(2.25, skillLevel) * 500);
        long lessonCost = (long) (Math.pow(2.75, lessonQuality) * 700);
        long stringCost = (long) (Math.pow(1.8, stringQuality) * 400);
        long bowCost = (long) (Math.pow(3.5, bowQuality) * 500);
        long hallCost = (long) (Math.pow(10, hallLevel) * 10000);
        long conductorCost = (long) (Math.pow(5, conductor) * 100000);
        long ticketCost = (long) (Math.pow(2, tickets) * 2000000);
        long fluteCost = 400000L * (flute + 1);
        long oboeCost = 400000L * (oboe + 1);
        long clarinetCost = 400000L * (clarinet + 1);
        long bassoonCost = 400000L * (bassoon + 1);
        long hornCost = 400000L * (horn + 1);
        long trumpetCost = 300000L * (trumpet + 1);
        long tromboneCost = 250000L * (trombone + 1);
        long tubaCost = 250000L * (tuba + 1);
        long timpaniCost = 400000L * (timpani + 1);
        long percussionCost = 100000L * (percussion + 1);
        long firstCost = 600000L * first;
        long secondCost = 500000L * second;
        long celloCost = 500000L * (cello + 1);
        long doubleCost = 500000L * (stringBass + 1);
        long pianoCost = 1000000L * (piano + 1);
        long sopranoCost = 100000L * (soprano + 1);
        long altoCost = 75000L * (alto + 1);
        long tenorCost = 75000L * (tenor + 1);
        long bassCost = 75000L * (bass + 1);
        long soloistCost = 400000L * (soloists + 1);
        long advertisingCost = 100000L * (advertising + 1);
        switch (message[0]) {
            case "upgrades", "up", "u", "shop" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                try {
                    if(!hasOrchestra) {
                        switch (message[1]) {
                            case "1" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Violin Quality (" + violinQuality + "/10)", "Price: " + violinCost + "\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
                                        .addField("Skill Level (" + skillLevel + "/15)", "Price: " + skillCost + "\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
                                        .addField("Lesson Quality (" + lessonQuality + "/12)", "Price: " + lessonCost + "\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
                                        .addField("String Quality (" + stringQuality + "/20)", "Price: " + stringCost + "\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
                                        .addField("Bow Quality (" + bowQuality + "/10)", "Price: " + bowCost + "\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
                                        .setTitle("__**Income Upgrades**__");
                                if (hasMath) {
                                    builder.addField("Math Tutoring (1/1)", "Price: 10 000 000\nEffect: +6500:violin:/hour\nID: `math`", false);
                                } else {
                                    builder.addField("Math Tutoring (0/1)", "Price: 10 000 000\nEffect: +6500:violin:/hour\nID: `math`", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "2" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUser `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Concert Hall Quality (" + hallLevel + "/2)", "Price: " + hallCost + "\nEffect: +300:violin:/hour, +20% violins from `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                                        .addField("Orchestra", "Price: 40 000 000\nIncome Requirement: 7 500\nEffect: +3 100:violin:/hour, access to `" + prefix + "rehearse` command\nID:`orchestra`, `o`", false)
                                        .setTitle("__**Miscellaneous Orchestra Items**__");
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "3" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Efficient Practising (" + workL + "/100)", "Price: " + workCost + "\nEffect: Increases your income from `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                                        .addField("Lucky Musician (" + gambleL + "/50)", "Price: " + gambleCost + "\nEffect: Increases your gambling multiplier by 0.5%.\nID: `lucky`, `lm`", false)
                                        .addField("Sophisticated Robbing (" + robL + "/30)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
                                        .setTitle("__**Other Miscellaneous Upgrades**__");
                                if (ownInsurance1) {
                                    builder.addField("Ling Ling Insurance - Plan 1 - Full Security (1/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen.  You pay a 12% insurance cost and the robber is fined 3% of their balance.\nID: `1`", false);
                                } else {
                                    builder.addField("Ling Ling Insurance - Plan 1 - Full Security (0/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen.  You pay a 12% insurance cost and the robber is fined 3% of their balance.\nID: `1`", false);
                                }
                                if (ownInsurance2) {
                                    builder.addField("Ling Ling Insurance - Plan 2 - Half Security (1/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 95% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                } else {
                                    builder.addField("Ling Ling Insurance - Plan 2 - Half Security (0/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 95% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                }
                                if(faster) {
                                    builder.addField("Time Crunch (1/1)", "Price: 150 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `faster`", false);
                                } else {
                                    builder.addField("Time Crunch (0/1)", "Price: 150 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `faster`", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "4" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Medals: " + medals + "\nEarn medals from `" + prefix + "perform`!", e.getJDA().getSelfUser().getAvatarUrl());
                                if(extraIncome) {
                                    builder.addField("Extra Income :white_check_mark:", "Needed: 5:military_medal:\nEffect: +8000:violin:/hour", false);
                                } else {
                                    builder.addField("Extra Income", "Needed: 5:military_medal:\nEffect: +8000:violin:/hour", false);
                                }
                                if(extraCommandIncome) {
                                    builder.addField("Extra Command Income :white_check_mark:", "Needed: 10:military_medal:\nEffect: x2 income from commands", false);
                                } else {
                                    builder.addField("Extra Command Income", "Needed: 10:military_medal:\nEffect: x2 income from commands", false);
                                }
                                if(higherWinrate) {
                                    builder.addField("Higher Gamble Multiplier :white_check_mark:", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
                                } else {
                                    builder.addField("Higher Gamble Multiplier", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
                                }
                                if(higherRobrate) {
                                    builder.addField("Higher Rob Winrate :white_check_mark:", "Needed: 25:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance", false);
                                } else {
                                    builder.addField("Higher Rob Winrate", "Needed: 25:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance", false);
                                }
                                if(stealShield) {
                                    builder.addField("Steal Shield :white_check_mark:", "Needed: 35:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.", false);
                                } else {
                                    builder.addField("Steal Shield", "Needed: 35:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.", false);
                                }
                                if(violinDuplicator) {
                                    builder.addField("Violin Duplicator :white_check_mark:", "Needed: 50:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen", false);
                                } else {
                                    builder.addField("Violin Duplicator", "Needed: 50:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Miscellaneous Orchestra Items\n`3` for Other Miscellaneous Upgrades\n`4` for Medal Upgrades").queue();
                        }
                    } else {
                        switch (message[1]) {
                            case "1" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Violin Quality (" + violinQuality + "/10)", "Price: " + violinCost + "\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
                                        .addField("Skill Level (" + skillLevel + "/15)", "Price: " + skillCost + "\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
                                        .addField("Lesson Quality (" + lessonQuality + "/12)", "Price: " + lessonCost + "\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
                                        .addField("String Quality (" + stringQuality + "/20)", "Price: " + stringCost + "\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
                                        .addField("Bow Quality (" + bowQuality + "/10)", "Price: " + bowCost + "\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
                                        .setTitle("__**Income Upgrades**__");
                                if (hasMath) {
                                    builder.addField("Math Tutoring (1/1)", "Price: 10 000 000\nEffect: +6500:violin:/hour\nID: `math`", false);
                                } else {
                                    builder.addField("Math Tutoring (0/1)", "Price: 10 000 000\nEffect: +6500:violin:/hour\nID: `math`", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "2" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl());
                                if(piccolo) {
                                    builder.addField("Piccolo (1/1)", "Price: 300 000\nEffect: +30:violin:/hour\nID: `piccolo`", false);
                                } else {
                                    builder.addField("Piccolo (0/1)", "Price: 300 000\nEffect: +30:violin:/hour\nID: `piccolo`", false);
                                }
                                builder.addField("Flutes (" + flute + "/4)", "Price: " + fluteCost + "\nEffect: +60:violin:/hour\nID: `flute`", false)
                                        .addField("Oboes (" + oboe + "/4)", "Price: " + oboeCost + "\nEffect: +50:violin:/hour\nID: `oboe`", false)
                                        .addField("Clarinets (" + clarinet + "/4)", "Price: " + clarinetCost + "\nEffect: +40:violin:/hour\nID: `clarinet`", false)
                                        .addField("Bassoons (" + bassoon + "/4)", "Price: " + bassoonCost + "\nEffect: +40:violin:/hour\nID: `bassoon`", false)
                                        .setTitle("__**Woodwinds**__");
                                if (contrabassoon) {
                                    builder.addField("Contrabassoon (1/1)", "Price: 400 000\nEffect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
                                } else {
                                    builder.addField("Contrabassoon (0/1)", "Price: 400 000\nEffect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "3" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("French Horns (" + horn + "/8)", "Price: " + hornCost + "\nEffect: +40:violin:/hour\nID: `horn`", false)
                                        .addField("Trumpet (" + trumpet + "/4)", "Price: " + oboeCost + "\nEffect: +30:violin:/hour\nID: `trumpet`", false)
                                        .addField("Trombone (" + trombone + "/6)", "Price: " + tromboneCost + "\nEffect: +20:violin:/hour\nID: `trombone`", false)
                                        .addField("Tuba (" + tuba + "/2)", "Price: " + tubaCost + "\nEffect: +20:violin:/hour\nID: `tuba`", false)
                                        .addField("Timpani (" + timpani + "/2)", "Price: " + timpaniCost + "\nEffect: +60:violin:/hour\nID: `timpani`", false)
                                        .addField("Percussionists (" + percussion + "/2)", "Price: " + percussionCost + "\nEffect: +10:violin:/hour\nID: `percussion`", false)
                                        .setTitle("__**Brass and Percussion**__");
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "4" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Violin I (" + first + "/20)", "Price: " + firstCost + "\nEffect: +70:violin:/hour\nID: `first`", false)
                                        .addField("Violin II (" + second + "/20)", "Price: " + secondCost + "\nEffect: +60:violin:/hour\nID: `second`", false)
                                        .addField("Cellos (" + cello + "/15)", "Price: " + celloCost + "\nEffect: +50:violin:/hour\nID: `cello`", false)
                                        .addField("Double Basses (" + stringBass + "/5)", "Price: " + doubleCost + "\nEffect: +50:violin:/hour\nID: `double`, `upright`, `doublebass`, `db`", false)
                                        .addField("Pianists (" + piano + "/2)", "Price: " + timpaniCost + "\nEffect: +110:violin:/hour\nID: `piano`", false)
                                        .setTitle("__**Strings**__");
                                if(harp) {
                                    builder.addField("Harp (1/1)", "Price: 500 000\nEffect: +80:violin:/hour\nID: `harp`", false);
                                } else {
                                    builder.addField("Harp (0/1)", "Price: 500 000\nEffect: +80:violin:/hour\nID: `harp`", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "5" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Sopranos (" + soprano + "/20)", "Price: " + sopranoCost + "\nEffect: +30:violin:/hour\nID: `soprano`", false)
                                        .addField("Altos (" + alto + "/20)", "Price: " + altoCost + "\nEffect: +20:violin:/hour\nID: `alto`", false)
                                        .addField("Tenors (" + tenor + "/20)", "Price: " + tenorCost + "\nEffect: +20:violin:/hour\nID: `tenor`", false)
                                        .addField("Basses (" + bass + "/20)", "Price: " + bassCost + "\nEffect: +20:violin:/hour\nID: `bass`", false)
                                        .addField("Vocal Soloists (" + soloists + "/4)", "Price: " + soloistCost + "\nEffect: +60:violin:/hour\nID: `soloist`", false)
                                        .setTitle("__**Choir**__");
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "6" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Concert Hall Quality (" + hallLevel + "/5)", "Price: " + hallCost + "\nEffect: +300:violin:/hour, +20% violins from `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                                        .addField("Conductor Musicality (" + conductor + "/5)", "Price: " + conductorCost + "\nEffect: +200:violin:/hour\nID: `conductor`, `musicality`", false)
                                        .addField("Advertisement (" + advertising + "/20)", "Price: " + advertisingCost + "\nEffect: +100:violin:/hour\nID: `advertisement`, `ad`", false)
                                        .addField("Ticket Price (" + tickets + "/5)", "Price: " + ticketCost + "\nEffect: +1000:violin:/hour\nID: `tickets`", false)
                                        .setTitle("__**Miscellaneous Orchestra Items**__");
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "7" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Efficient Practising (" + workL + "/100)", "Price: " + workCost + "\nEffect: Increases your income from `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                                        .addField("Lucky Musician (" + gambleL + "/50)", "Price: " + gambleCost + "\nEffect: Increases your gambling multiplier by 0.5%.\nID: `lucky`, `lm`", false)
                                        .addField("Sophisticated Robbing (" + robL + "/30)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
                                        .setTitle("__**Other Miscellaneous Upgrades**__");
                                if (ownInsurance1) {
                                    builder.addField("Ling Ling Insurance - Plan 1 - Full Security (1/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen.  You pay a 12% insurance cost and the robber is fined 3% of their balance.\nID: `1`", false);
                                } else {
                                    builder.addField("Ling Ling Insurance - Plan 1 - Full Security (0/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen.  You pay a 12% insurance cost and the robber is fined 3% of their balance.\nID: `1`", false);
                                }
                                if (ownInsurance2) {
                                    builder.addField("Ling Ling Insurance - Plan 2 - Half Security (1/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 95% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                } else {
                                    builder.addField("Ling Ling Insurance - Plan 2 - Half Security (0/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 95% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                }
                                if(faster) {
                                    builder.addField("Time Crunch (1/1)", "Price: 150 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `faster`", false);
                                } else {
                                    builder.addField("Time Crunch (0/1)", "Price: 150 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `faster`", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "8" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Medals: " + medals + "\nEarn medals from `" + prefix + "perform`!", e.getJDA().getSelfUser().getAvatarUrl());
                                if(extraIncome) {
                                    builder.addField("Extra Income :white_check_mark:", "Needed: 5:military_medal:\nEffect: +8000:violin:/hour", false);
                                } else {
                                    builder.addField("Extra Income", "Needed: 5:military_medal:\nEffect: +8000:violin:/hour", false);
                                }
                                if(extraCommandIncome) {
                                    builder.addField("Extra Command Income :white_check_mark:", "Needed: 10:military_medal:\nEffect: x2 income from commands", false);
                                } else {
                                    builder.addField("Extra Command Income", "Needed: 10:military_medal:\nEffect: x2 income from commands", false);
                                }
                                if(higherWinrate) {
                                    builder.addField("Higher Gamble Multiplier :white_check_mark:", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
                                } else {
                                    builder.addField("Higher Gamble Multiplier", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
                                }
                                if(higherRobrate) {
                                    builder.addField("Higher Rob Winrate :white_check_mark:", "Needed: 25:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance", false);
                                } else {
                                    builder.addField("Higher Rob Winrate", "Needed: 25:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance", false);
                                }
                                if(stealShield) {
                                    builder.addField("Steal Shield :white_check_mark:", "Needed: 35:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.", false);
                                } else {
                                    builder.addField("Steal Shield", "Needed: 35:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.", false);
                                }
                                if(violinDuplicator) {
                                    builder.addField("Violin Duplicator :white_check_mark:", "Needed: 50:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen", false);
                                } else {
                                    builder.addField("Violin Duplicator", "Needed: 50:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Other Miscellaneous Upgrades\n`8` for Medal Upgrades").queue();
                        }
                    }
                } catch (Exception exception) {
                    e.getChannel().sendMessage("You must provide a page number!").queue();
                }
            }
            case "buy" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                boolean boughtItem = false;
                if(hasOrchestra) {
                    boughtItem = true;
                    switch(message[1]) {
                        case "piccolo" -> {
                            if (piccolo) {
                                e.getChannel().sendMessage("You already hired hired a piccolo player!").queue();
                            } else if (violins < 300000) {
                                e.getChannel().sendMessage("You are too poor to hire a piccolo player!").queue();
                            } else {
                                violins -= 300000;
                                piccolo = true;
                                e.getChannel().sendMessage("Successfully hired a piccolo player for 300 000:violin:!\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "contrabassoon", "cb" -> {
                            if (contrabassoon) {
                                e.getChannel().sendMessage("You already hired hired a contrabassonist!").queue();
                            } else if (violins < 400000) {
                                e.getChannel().sendMessage("You are too poor to hire a contrabassonist!").queue();
                            } else {
                                violins -= 400000;
                                contrabassoon = true;
                                e.getChannel().sendMessage("Successfully hired a contrabassoonist for 400 000:violin:!\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "harp" -> {
                            if (harp) {
                                e.getChannel().sendMessage("You already hired hired a harpist!").queue();
                            } else if (violins < 500000) {
                                e.getChannel().sendMessage("You are too poor to hire a harpist!").queue();
                            } else {
                                violins -= 500000;
                                harp = true;
                                e.getChannel().sendMessage("Successfully hired a harpist for 500 000:violin:!\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "flute" -> {
                            if (flute == 4) {
                                e.getChannel().sendMessage("You already hired the max amount of flautists!").queue();
                            } else if (violins < fluteCost) {
                                e.getChannel().sendMessage("You are too poor to hire a flautist!").queue();
                            } else {
                                violins -= fluteCost;
                                flute++;
                                e.getChannel().sendMessage("Successfully hired a flautist for " + fluteCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "oboe" -> {
                            if (oboe == 4) {
                                e.getChannel().sendMessage("You already hired the max amount of oboists!").queue();
                            } else if (violins < oboeCost) {
                                e.getChannel().sendMessage("You are too poor to hire a oboist!").queue();
                            } else {
                                violins -= oboeCost;
                                oboe++;
                                e.getChannel().sendMessage("Successfully hired a oboist for " + oboeCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "clarinet" -> {
                            if (clarinet == 4) {
                                e.getChannel().sendMessage("You already hired the max amount of clarinetists!").queue();
                            } else if (violins < clarinetCost) {
                                e.getChannel().sendMessage("You are too poor to hire a clarinetist!").queue();
                            } else {
                                violins -= clarinetCost;
                                clarinet++;
                                e.getChannel().sendMessage("Successfully hired a clarinetist for " + clarinetCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "bassoon" -> {
                            if (bassoon == 4) {
                                e.getChannel().sendMessage("You already hired the max amount of bassoonists!").queue();
                            } else if (violins < bassoonCost) {
                                e.getChannel().sendMessage("You are too poor to hire a bassoonist!").queue();
                            } else {
                                violins -= bassoonCost;
                                bassoon++;
                                e.getChannel().sendMessage("Successfully hired a bassoonist for " + bassoonCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "horn" -> {
                            if (horn == 8) {
                                e.getChannel().sendMessage("You already hired the max amount of hornists!").queue();
                            } else if (violins < hornCost) {
                                e.getChannel().sendMessage("You are too poor to hire a hornist!").queue();
                            } else {
                                violins -= hornCost;
                                horn++;
                                e.getChannel().sendMessage("Successfully hired a hornist for " + hornCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "trumpet" -> {
                            if (trumpet == 4) {
                                e.getChannel().sendMessage("You already hired the max amount of trumpeters!").queue();
                            } else if (violins < trumpetCost) {
                                e.getChannel().sendMessage("You are too poor to hire a trumpeter!").queue();
                            } else {
                                violins -= trumpetCost;
                                trumpet++;
                                e.getChannel().sendMessage("Successfully hired a trumpeter for " + trumpetCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "trombone" -> {
                            if (trombone == 6) {
                                e.getChannel().sendMessage("You already hired the max amount of trombonists!").queue();
                            } else if (violins < tromboneCost) {
                                e.getChannel().sendMessage("You are too poor to hire a trombonist!").queue();
                            } else {
                                violins -= tromboneCost;
                                trombone++;
                                e.getChannel().sendMessage("Successfully hired a trombonist for " + tromboneCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "tuba" -> {
                            if (horn == 2) {
                                e.getChannel().sendMessage("You already hired the max amount of tubists!").queue();
                            } else if (violins < tubaCost) {
                                e.getChannel().sendMessage("You are too poor to hire a tubist!").queue();
                            } else {
                                violins -= tubaCost;
                                tuba++;
                                e.getChannel().sendMessage("Successfully hired a tubist for " + tubaCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "timpani" -> {
                            if (timpani == 2) {
                                e.getChannel().sendMessage("You already hired the max amount of timpanists!").queue();
                            } else if (violins < timpaniCost) {
                                e.getChannel().sendMessage("You are too poor to hire a timpanist!").queue();
                            } else {
                                violins -= timpaniCost;
                                timpani++;
                                e.getChannel().sendMessage("Successfully hired a timpanist for " + timpaniCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "percussion" -> {
                            if (percussion == 2) {
                                e.getChannel().sendMessage("You already hired the max amount of percussionists!").queue();
                            } else if (violins < percussionCost) {
                                e.getChannel().sendMessage("You are too poor to hire a percussionist!").queue();
                            } else {
                                violins -= percussionCost;
                                percussion++;
                                e.getChannel().sendMessage("Successfully hired a percussionist for " + percussionCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "first" -> {
                            if (first == 20) {
                                e.getChannel().sendMessage("You already hired the max amount of first violinists!").queue();
                            } else if (violins < firstCost) {
                                e.getChannel().sendMessage("You are too poor to hire a first violinist!").queue();
                            } else {
                                violins -= firstCost;
                                first++;
                                e.getChannel().sendMessage("Successfully hired a first violinist for " + firstCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "second" -> {
                            if (second == 20) {
                                e.getChannel().sendMessage("You already hired the max amount of second violinists!").queue();
                            } else if (violins < secondCost) {
                                e.getChannel().sendMessage("You are too poor to hire a second violinist!").queue();
                            } else {
                                violins -= secondCost;
                                second++;
                                e.getChannel().sendMessage("Successfully hired a second violinist for " + secondCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "cello" -> {
                            if (cello == 15) {
                                e.getChannel().sendMessage("You already hired the max amount of cellists!").queue();
                            } else if (violins < celloCost) {
                                e.getChannel().sendMessage("You are too poor to hire a cellist!").queue();
                            } else {
                                violins -= celloCost;
                                cello++;
                                e.getChannel().sendMessage("Successfully hired a cellist for " + celloCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "double", "upright", "db", "doublebass" -> {
                            if (stringBass == 5) {
                                e.getChannel().sendMessage("You already hired the max amount of double bassists!").queue();
                            } else if (violins < doubleCost) {
                                e.getChannel().sendMessage("You are too poor to hire a double bassist!").queue();
                            } else {
                                violins -= doubleCost;
                                stringBass++;
                                e.getChannel().sendMessage("Successfully hired a double bassist for " + doubleCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "piano" -> {
                            if (piano == 2) {
                                e.getChannel().sendMessage("You already hired the max amount of pianists!").queue();
                            } else if (violins < pianoCost) {
                                e.getChannel().sendMessage("You are too poor to hire a pianist!").queue();
                            } else {
                                violins -= pianoCost;
                                piano++;
                                e.getChannel().sendMessage("Successfully hired a pianist for " + pianoCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "soprano" -> {
                            if (soprano == 20) {
                                e.getChannel().sendMessage("You already hired the max amount of sopranists!").queue();
                            } else if (violins < sopranoCost) {
                                e.getChannel().sendMessage("You are too poor to hire a sopranist!").queue();
                            } else {
                                violins -= sopranoCost;
                                soprano++;
                                e.getChannel().sendMessage("Successfully hired a sopranist for " + sopranoCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "alto" -> {
                            if (alto == 20) {
                                e.getChannel().sendMessage("You already hired the max amount of altos!").queue();
                            } else if (violins < altoCost) {
                                e.getChannel().sendMessage("You are too poor to hire an alto!").queue();
                            } else {
                                violins -= altoCost;
                                alto++;
                                e.getChannel().sendMessage("Successfully hired an alto for " + altoCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "tenor" -> {
                            if (tenor == 20) {
                                e.getChannel().sendMessage("You already hired the max amount of tenors!").queue();
                            } else if (violins < tenorCost) {
                                e.getChannel().sendMessage("You are too poor to hire a tenor!").queue();
                            } else {
                                violins -= tenorCost;
                                tenor++;
                                e.getChannel().sendMessage("Successfully hired a tenor for " + tenorCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "bass" -> {
                            if (bass == 20) {
                                e.getChannel().sendMessage("You already hired the max amount of bassists!").queue();
                            } else if (violins < bassCost) {
                                e.getChannel().sendMessage("You are too poor to hire a bassist!").queue();
                            } else {
                                violins -= bassCost;
                                bass++;
                                e.getChannel().sendMessage("Successfully hired a bassist for " + bassCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "soloist" -> {
                            if (soloists == 4) {
                                e.getChannel().sendMessage("You already hired the max amount of vocal soloists!").queue();
                            } else if (violins < soloistCost) {
                                e.getChannel().sendMessage("You are too poor to hire a vocal soloist!").queue();
                            } else {
                                violins -= soloistCost;
                                soloists++;
                                e.getChannel().sendMessage("Successfully hired a vocal soloist for " + soloistCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "conductor", "musicality" -> {
                            if (conductor == 5) {
                                e.getChannel().sendMessage("The conductor has been maxed in Musicality!").queue();
                            } else if (violins < conductorCost) {
                                e.getChannel().sendMessage("You are too poor to train the conductor!").queue();
                            } else {
                                violins -= conductorCost;
                                conductor++;
                                e.getChannel().sendMessage("Successfully upgraded Conductor Musicality to Level " + conductor + " for " + conductorCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "advertisement", "ad" -> {
                            if (advertising == 20) {
                                e.getChannel().sendMessage("You can't advertise more!").queue();
                            } else if (violins < advertisingCost) {
                                e.getChannel().sendMessage("You are too poor to buy more advertisements!").queue();
                            } else {
                                violins -= advertisingCost;
                                advertising++;
                                e.getChannel().sendMessage("Successfully upgraded Advertising to Level " + advertising + " for " + advertisingCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "tickets" -> {
                            if (tickets == 5) {
                                e.getChannel().sendMessage("You can't make your tickets more expensive!").queue();
                            } else if (violins < ticketCost) {
                                e.getChannel().sendMessage("You are too poor to make your tickets more expensive!").queue();
                            } else {
                                violins -= ticketCost;
                                tickets++;
                                e.getChannel().sendMessage("Successfully upgraded Ticket Cost to Level " + tickets + " for " + ticketCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        default -> boughtItem = false;
                    }
                }
                switch (message[1]) {
                    case "1" -> {
                        if (ownInsurance1) {
                            e.getChannel().sendMessage("You already bought this insurance!  Run `" + prefix + "use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.").queue();
                        } else if (violins < 3000000) {
                            e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                        } else {
                            ownInsurance1 = true;
                            violins -= 3000000;
                            e.getChannel().sendMessage("You have successfully bought Ling Ling Insurance - Plan 1 - Full Security!  Run `" + prefix + "use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "2" -> {
                        if (ownInsurance2) {
                            e.getChannel().sendMessage("You already bought this insurance!  Run `" + prefix + "use 2` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 1.").queue();
                        } else if (violins < 3000000) {
                            e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                        } else {
                            ownInsurance2 = true;
                            violins -= 3000000;
                            e.getChannel().sendMessage("You have successfully bought Ling Ling Insurance - Plan 2 - Half Security!  Run `" + prefix + "use 2` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 1.\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "time", "crunch", "speed", "faster" -> {
                        if (faster) {
                            e.getChannel().sendMessage("You have already sped up time!").queue();
                        } else if (violins < 150000000) {
                            e.getChannel().sendMessage("You are too poor to speed up time!").queue();
                        } else {
                            faster = true;
                            violins -= 150000000;
                            e.getChannel().sendMessage("You approach Ling Ling and pay him (her?) 150 million violins.  Ling Ling then does magic and you are back in your room.  You feel like you can do things faster, around 25% faster.\n`" + prefix + "practice` cooldown is now 30 minutes.\n`" + prefix + "rehearse` cooldown is now 18 hours.\n`" + prefix + "perform` cooldown is now 2.5 days.\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "efficient", "practising", "ep" -> {
                        if (workL == 100) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < workCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= workCost;
                            workL++;
                            e.getChannel().sendMessage("Successfully upgraded Efficient Practising to Level " + workL + " for " + workCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "lucky", "lm" -> {
                        if (gambleL == 50) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < gambleCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= gambleCost;
                            gambleL++;
                            e.getChannel().sendMessage("Successfully upgraded Lucky Musician to Level " + gambleL + " for " + gambleCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "sophisticated", "robbing", "sr" -> {
                        if (robL == 30) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < robCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= robCost;
                            robL++;
                            e.getChannel().sendMessage("Successfully upgraded Sophisticated Robbing to Level " + robL + " for " + robCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "violin", "v" -> {
                        if (violinQuality == 10) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < violinCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= violinCost;
                            violinQuality++;
                            e.getChannel().sendMessage("Successfully upgraded Violin Quality to Level " + violinQuality + " for " + violinCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "skill", "s" -> {
                        if (skillLevel == 15) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < skillCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= skillCost;
                            skillLevel++;
                            e.getChannel().sendMessage("Successfully upgraded Skill Level to Level " + skillLevel + " for " + skillCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "lesson", "l" -> {
                        if (lessonQuality == 12) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < lessonCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= lessonCost;
                            lessonQuality++;
                            e.getChannel().sendMessage("Successfully upgraded Lesson Quality to Level " + lessonQuality + " for " + lessonCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "string", "str" -> {
                        if (stringQuality == 20) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < stringCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= stringCost;
                            stringQuality++;
                            e.getChannel().sendMessage("Successfully upgraded String Quality to Level " + stringQuality + " for " + stringCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "bow", "b" -> {
                        if (bowQuality == 10) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < bowCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= bowCost;
                            bowQuality++;
                            e.getChannel().sendMessage("Successfully upgraded Bow Quality to Level " + bowQuality + " for " + bowCost + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "math" -> {
                        if (hasMath) {
                            e.getChannel().sendMessage("You already bought this upgrade!").queue();
                        } else if (violins < 10000000) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= 10000000;
                            hasMath = true;
                            e.getChannel().sendMessage("Successfully purchased Math Tutoring!\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "orchestra", "o" -> {
                        if (hasOrchestra) {
                            e.getChannel().sendMessage("You already hired an orchestra!").queue();
                        } else if (hourlyIncome < 7500) {
                            e.getChannel().sendMessage("You don't have enough hourly income to hire an orchestra!").queue();
                        } else if (violins < 40000000) {
                            e.getChannel().sendMessage("You are too poor to hire an orchestra!").queue();
                        } else {
                            violins -= 40000000;
                            hasOrchestra = true;
                            e.getChannel().sendMessage("Successfully hired an Orchestra!  You have automatically gained 1 Violin I and 1 Violin II for free.\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "concert", "hall" -> {
                        if(!hasOrchestra) {
                            if (hallLevel == 2) {
                                e.getChannel().sendMessage("Hire an orchestra to unlock Levels 3-5!").queue();
                            } else if (violins < hallCost) {
                                e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                            } else {
                                violins -= hallCost;
                                hallLevel++;
                                e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + " for " + hallCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        } else {
                            if (hallLevel == 5) {
                                e.getChannel().sendMessage("You can't buy more of this upgrade!").queue();
                            } else if (violins < hallCost) {
                                e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                            } else {
                                violins -= hallCost;
                                hallLevel++;
                                e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + " for " + hallCost + ":violin:\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                    }
                    default ->  {
                        if(!boughtItem) {
                            e.getChannel().sendMessage("You can't buy something that's not for sale, that would be quite a waste of time.").queue();
                        }
                    }
                }
            }
            case "orchestra", "o" -> {
                if(!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                if(hasOrchestra) {
                    long temp1 = 0;
                    long temp2 = 0;
                    long temp3 = 0;
                    if(piccolo) {
                        temp1 = 1;
                    }
                    if(contrabassoon) {
                        temp2 = 1;
                    }
                    if(harp) {
                        temp3 = 1;
                    }
                    EmbedBuilder builder = new EmbedBuilder()
                            .setTitle("**__Orchestra Stats__**")
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .addField("**Woodwinds**", temp1 + " Piccolo\n" + flute + " Flutes\n" + oboe + " Oboes\n" + clarinet + " Clarinets\n" + bassoon + " Bassoons\n" + temp2 + " Contrabassoon", true)
                            .addField("**Brass**", trumpet + " Trumpets\n" + horn + " French Horns\n" + trombone + " Trombones\n" + tuba + " Tubas", true)
                            .addField("**Strings**", first + " Violin I\n" + second + " Violin II\n" + cello + " Celli\n" + stringBass + " Double Basses\n" + piano + " Pianists", true)
                            .addField("**Choir**", soprano + " Sopranos\n" + alto + " Altos\n" + tenor + " Tenors\n" + bass + " Basses\n" + soloists + " Vocal Soloists", true)
                            .addField("**Other**", temp3 + " Harp\n" + percussion + " Percussionists\n" + timpani + " Timpanists",true)
                            .setColor(Color.BLUE);
                    e.getChannel().sendMessage(builder.build()).queue();
                } else {
                    e.getChannel().sendMessage("You don't have an orchestra yet!  Run `" + prefix + "buy orchestra` to get one!  Remember that you need 7 500:violin:/hour and 40 000 000:violin: to hire one!").queue();
                }
            }
            case "cooldowns", "c" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                EmbedBuilder builder = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
                //rob
                long milliseconds = robC - time;
                long seconds;
                long minutes;
                long hours;
                long days;
                if (milliseconds < 0) {
                    builder.addField("**Rob**", ":white_check_mark:", false);
                } else {
                    hours = milliseconds / 3600000;
                    milliseconds -= hours * 3600000;
                    minutes = milliseconds / 60000;
                    milliseconds -= minutes * 60000;
                    seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    builder.addField("**Rob**", hours + "h " + minutes + "m " + seconds + "s " + milliseconds + "ms", false);
                }

                //practice
                milliseconds = workC - time;
                if (milliseconds < 0) {
                    builder.addField("**Practice**", ":white_check_mark:", false);
                } else {
                    minutes = milliseconds / 60000;
                    milliseconds -= minutes * 60000;
                    seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    builder.addField("**Practice**", minutes + "m " + seconds + "s " + milliseconds + "ms", false);
                }

                //rehearse
                if(hasOrchestra) {
                    milliseconds = rehearseC - time;
                    if (milliseconds < 0) {
                        builder.addField("**Rehearse**", ":white_check_mark:", false);
                    } else {
                        hours = milliseconds / 3600000;
                        milliseconds -= hours * 3600000;
                        minutes = milliseconds / 60000;
                        milliseconds -= minutes * 60000;
                        seconds = milliseconds / 1000;
                        milliseconds -= seconds * 1000;
                        builder.addField("**Rehearse**", hours + "h " + minutes + "m " + seconds + "s " + milliseconds + "ms", false);
                    }
                }

                //perform
                milliseconds = performC - time;
                if (milliseconds < 0) {
                    builder.addField("**Perform**", ":white_check_mark:", false);
                } else {
                    days = milliseconds / 86400000;
                    milliseconds -= days * 86400000;
                    hours = milliseconds / 3600000;
                    milliseconds -= hours * 3600000;
                    minutes = milliseconds / 60000;
                    milliseconds -= minutes * 60000;
                    seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    builder.addField("**Perform**", days + "d " + hours + "h " + minutes + "m " + seconds + "s " + milliseconds + "ms", false);
                }

                //daily
                milliseconds = dailyC - time;
                if(milliseconds < 0) {
                    builder.addField("**Daily**", ":white_check_mark:", false);
                } else {
                    hours = milliseconds / 3600000;
                    milliseconds -= hours * 3600000;
                    minutes = milliseconds / 60000;
                    milliseconds -= minutes * 60000;
                    seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    builder.addField("**Daily**", hours + "h " + minutes + "m " + seconds + "s " + milliseconds + "ms", false);
                }
                builder.setTitle("__**Cooldowns**__");
                e.getChannel().sendMessage(builder.build()).queue();
            }
            case "use" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                switch (message[1]) {
                    case "1" -> {
                        if (ownInsurance1) {
                            activeInsurance = 1;
                            e.getChannel().sendMessage("You have activated Plan 1 - Full Security").queue();
                        } else {
                            e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                        }
                    }
                    case "2" -> {
                        if (ownInsurance2) {
                            activeInsurance = 2;
                            e.getChannel().sendMessage("You have activated Plan 2 - Half Security").queue();
                        } else {
                            e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                        }
                    }
                    case "0" -> {
                        activeInsurance = 0;
                        e.getChannel().sendMessage("You have deactivated all insurance.  Not a good idea...").queue();
                    }
                    case "rice" -> {
                        if(hourlyIncome == 400000) {
                            e.getChannel().sendMessage("Woah there, take a chill pill!  Your hourly income is already WAY above the hard maximum of 50000!").queue();
                        } else if(rice == 0) {
                            e.getChannel().sendMessage("You look for that last rice piece you had but can't find it.  Then you remember that you don't have any left.").queue();
                        } else if(hourlyIncome == 0) {
                            e.getChannel().sendMessage("The God of Rice prevents you from wasting rice on an hourly income of 0.  Next time remember that multiplying anything by 0 is still 0").queue();
                        } else {
                            rice --;
                            hourlyIncome *= 2;
                            if(hourlyIncome >= 400000) {
                                hourlyIncome = 400000;
                                e.getChannel().sendMessage("You consume a piece of rice.  It turns out to be Blessed Rice but the God of Rice has capped your income to 400 000.").queue();
                            } else {
                                e.getChannel().sendMessage("You consume a piece of rice.  It turns out to be Blessed Rice and God of Rice doubles your hourly income for 1 hour.").queue();
                            }
                        }
                    }
                    case "tea" -> {
                        if(tea == 0) {
                            e.getChannel().sendMessage("You don't have any more bubble tea!").queue();
                        } else {
                            tea --;
                            long base = calculateAmount(e, data, (long) ((random.nextInt(100) + 350) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1))) * 2;
                            violins += base;
                            e.getChannel().sendMessage("You drank some Bubble Tea and wound up with an extra " + base + ":violin:").queue();
                        }
                    }
                    case "blessing" -> {
                        if(blessing == 0) {
                            e.getChannel().sendMessage("You already used all your blessings, run more commands to get back into Ling Ling's good graces!").queue();
                        } else {
                            blessing --;
                            long base = calculateAmount(e, data, (long) ((random.nextInt(1000) + 4500) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                            double num = random.nextDouble();
                            if(num > 0.5) {
                                violins += base;
                                medals ++;
                                thirdP ++;
                                e.getChannel().sendMessage(":trophy: Your performance won third place in the Ling Ling Competition.  You walked away with " + base + ":violin:, 1:military_medal: and a third place trophy :third_place:").queue();
                            } else if(num > 0.2) {
                                violins += base;
                                medals += 2;
                                secondP ++;
                                e.getChannel().sendMessage(":trophy: Your performance won second place.  You walked away with " + base + ":violin:, 2:military_medal: and a second place trophy :second_place:").queue();
                            } else {
                                violins += base;
                                medals += 3;
                                firstP ++;
                                e.getChannel().sendMessage(":trophy: Your performance won first place.  Congratulations!  You walked away with " + base + ":violin:, **3**:military_medal: and a FIRST place trophy :first_place:").queue();
                            }
                            if(!extraIncome && medals >= 5) {
                                e.getChannel().sendMessage("You achieved 5:military_medal:!  Congratulations!  You have received an extra 8 000 hourly income!").queue();
                                extraIncome = true;
                            } else if(!extraCommandIncome && medals >= 10) {
                                e.getChannel().sendMessage("You achieved 10:military_medal:!  Congratulations!  You now have 2x income from most commands!").queue();
                                extraCommandIncome = true;
                            } else if(!higherWinrate && medals >= 15) {
                                e.getChannel().sendMessage("You have achieved 15:military_medal:!  Congratulations!  You now have an extra 5% gambling multiplier!").queue();
                                higherWinrate = true;
                            } else if(!higherRobrate && medals >= 25) {
                                e.getChannel().sendMessage("You have achieved 25:military_medal:!  Congratulations!  You now have a 2.5% greater chance to succeed at robbing another user!  Note that this does **NOT** circumvent Ling Ling Insurance").queue();
                                higherRobrate = true;
                            } else if(!stealShield && medals >= 35) {
                                e.getChannel().sendMessage("You have achieved 35:military_medal:!  Congratulations!  You now have a Steal Shield, which protects an additional 50% of :violin: from being robbed!").queue();
                                stealShield = true;
                            } else if(!violinDuplicator && medals >= 50) {
                                e.getChannel().sendMessage("You have achieved 50:military_medal:!  Congratulations!  You now have a Violin Duplicator, which doubles all violins robbed from other users!").queue();
                                violinDuplicator = true;
                            }
                        }
                    }
                    default -> e.getChannel().sendMessage("You can't use nothing, that doesn't make sense.").queue();
                }
            }
            case "scales", "s" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                if (time < scaleC) {
                    long milliseconds = scaleC - time;
                    long seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    e.getChannel().sendMessage("Chill, you can't play two scales at once!  Wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                } else {
                    long base = calculateAmount(e, data, (long) ((random.nextInt(5) + 8) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                    violins += base;
                    if(faster) {
                        scaleC = time + 64000;
                    } else {
                        scaleC = time + 89000;
                    }
                    e.getChannel().sendMessage("You played your scales and earned " + base + ":violin:").queue();
                }
            }
            case "practice", "p" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                if (time < workC) {
                    long milliseconds = workC - time;
                    long minutes = milliseconds / 60000;
                    milliseconds -= minutes * 60000;
                    long seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    e.getChannel().sendMessage("Take a break, you already practised a lot!  Wait " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                } else {
                    long base = calculateAmount(e, data, (long) ((random.nextInt(101) + 350) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                    double num = random.nextDouble();
                    if(num > 0.75) {
                        violins += base;
                        e.getChannel().sendMessage("You practised for 40 minutes and earned " + base + ":violin:").queue();
                    } else if(num > 0.5) {
                        num = random.nextDouble();
                        if(num > 0.5) {
                            rice ++;
                            e.getChannel().sendMessage("You found 1:rice: while you were Practising but didn't get any violins.").queue();
                        } else if(num > 0.1) {
                            tea ++;
                            e.getChannel().sendMessage("You found 1:bubble_tea: after you Practised.").queue();
                        } else {
                            blessing ++;
                            e.getChannel().sendMessage("Ling Ling enjoyed your Practise session and blessed you.").queue();
                        }
                    } else if(num > 0.2) {
                        num = random.nextDouble();
                        if(num > 0.6) {
                            e.getChannel().sendMessage("Your teacher approved your performance.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.1;
                            violins += base;
                        } else if(num > 0.25) {
                            e.getChannel().sendMessage("Your tiger mom approved your performance.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.5;
                            violins += base;
                        } else if(num > 0.1) {
                            base *= 2;
                            violins += base;
                            e.getChannel().sendMessage("Ling Ling approved of your performance and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                        } else {
                            base *= 5;
                            violins += base;
                            e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
                        }
                    } else {
                        num = random.nextDouble();
                        if(num > 0.75) {
                            violins -= hourlyIncome / 100;
                            e.getChannel().sendMessage("Oh no!  Your E String snapped while you were practising!  You had to go to the store to get it replaced, and were not able to get any practising done.  You earned 0:violin: and had to pay " + (hourlyIncome / 100) + ":violin: for a new E String.").queue();
                        } else if(num > 0.55) {
                            base *= 0.9;
                            violins += base;
                            e.getChannel().sendMessage("Your violin randomly went out of tune while you were practising.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                        } else if(num > 0.4) {
                            base *= 0.95;
                            violins += base;
                            e.getChannel().sendMessage("You had problems with your music stand, and page turning wasn't the best this session.  You earned " + base + ":violin:").queue();
                        } else if(num > 0.25) {
                            base *= 0.5;
                            violins += base;
                            violins -= hourlyIncome / 10;
                            e.getChannel().sendMessage("You hurt your wrist while practising and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + (hourlyIncome / 10) + ":violin: in hospital fees.").queue();
                        } else if(num > 0.15) {
                            base *= 0.2;
                            violins += base;
                            e.getChannel().sendMessage("Your bridge fell off 10 minutes into your session.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
                        } else if(num > 0.05) {
                            violins -= hourlyIncome;
                            e.getChannel().sendMessage("You were caught playing Minecraft while practising.  Your tiger mom took all your earnings, in addition to another " + hourlyIncome + " for being distracted.").queue();
                        } else if(num > 0.015) {
                            e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
                            time += 43200000;
                        } else if(num > 0.005) {
                            e.getChannel().sendMessage("You decided to fake your practise session.  Ling Ling caught you in the act, and fined you " + violins * (hourlyIncome / 200000) + ":violin:").queue();
                            violins -= violins * (hourlyIncome / 200000);
                        } else {
                            e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + violins * (hourlyIncome / 100000) + ":violin: for being careless.").queue();
                            violins -= violins * (hourlyIncome / 100000);
                            time += 86400000;
                            rehearseC += 86400000;
                            performC += 86400000;
                            robC += 86400000;
                        }
                    }
                    if(faster) {
                        workC = time + 1740000;
                    } else {
                        workC = time + 2340000;
                    }
                }
            }
            case "rehearse", "r" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                if(hasOrchestra) {
                    if (time < rehearseC) {
                        long milliseconds = rehearseC - time;
                        long hours = milliseconds / 3600000;
                        milliseconds -= hours * 3600000;
                        long minutes = milliseconds / 60000;
                        milliseconds -= minutes * 60000;
                        long seconds = milliseconds / 1000;
                        milliseconds -= seconds * 1000;
                        e.getChannel().sendMessage("You don't have the time to go to rehearsal that often, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                    } else {
                        long base = calculateAmount(e, data, (long) ((random.nextInt(501) + 1750) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                        double num = random.nextDouble();
                        if(num > 0.75) {
                            violins += base;
                            e.getChannel().sendMessage("You rehearsed with your orchestra and earned " + base + ":violin:").queue();
                        } else if(num > 0.5) {
                            num = random.nextDouble();
                            if(num > 0.5) {
                                rice += 3;
                                e.getChannel().sendMessage("You found 3:rice: after rehearsal but didn't get any violins.").queue();
                            } else if(num > 0.1) {
                                tea += 2;
                                e.getChannel().sendMessage("You found 2:bubble_tea: after you went to rehearsal.").queue();
                            } else {
                                blessing ++;
                                e.getChannel().sendMessage("Ling Ling enjoyed your rehearsal session and blessed you.").queue();
                            }
                        } else if(num > 0.2) {
                            num = random.nextDouble();
                            if(num > 0.6) {
                                e.getChannel().sendMessage("Your teacher approved your performance.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                                base *= 1.1;
                                violins += base;
                            } else if(num > 0.25) {
                                e.getChannel().sendMessage("Your tiger mom approved your performance.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                                base *= 1.5;
                                violins += base;
                            } else if(num > 0.1) {
                                base *= 2;
                                violins += base;
                                e.getChannel().sendMessage("Ling Ling approved of your performance and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                            } else {
                                base *= 5;
                                violins += base;
                                e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
                            }
                        } else {
                            num = random.nextDouble();
                            if(num > 0.75) {
                                violins -= hourlyIncome / 100;
                                base *= 0.95;
                                violins += base;
                                e.getChannel().sendMessage("Oh no!  Your E String snapped during the rehearsal!  You had to borrow the concertmaster's violin, and only earned " + base + ":violin:  You eventually had to pay " + (hourlyIncome / 100) + ":violin: for a new E String.").queue();
                            } else if(num > 0.55) {
                                base *= 0.9;
                                violins += base;
                                e.getChannel().sendMessage("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                            } else if(num > 0.4) {
                                base *= 0.95;
                                e.getChannel().sendMessage("The orchestra had music stand problems, and page turning wasn't the best either.  You only earned " + base + ":violin:").queue();
                            } else if(num > 0.25) {
                                base *= 0.5;
                                violins += base;
                                violins -= hourlyIncome / 10;
                                e.getChannel().sendMessage("You hurt your wrist during the rehearsal and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + (hourlyIncome / 10) + ":violin: in hospital fees.").queue();
                            } else if(num > 0.15) {
                                base *= 0.2;
                                violins += base;
                                e.getChannel().sendMessage("Your bridge fell off during the rehearsal.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
                            } else if(num > 0.05) {
                                violins -= hourlyIncome;
                                e.getChannel().sendMessage("You had a memory blank during rehearsal.  Your tiger mom took all of your earnings, in addition to another " + hourlyIncome + " for shaming yourself.").queue();
                            } else if(num > 0.015) {
                                e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
                                workC += 43200000;
                            } else if(num > 0.005) {
                                e.getChannel().sendMessage("You decided to fake your solo.  Of course it didn't work and Ling Ling fined you " + violins * (hourlyIncome / 150000) + ":violin:").queue();
                                violins -= violins * (hourlyIncome / 150000);
                            } else {
                                e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + violins * (hourlyIncome / 75000) + ":violin: for being careless in public.").queue();
                                violins -= violins * (hourlyIncome / 75000);
                                workC += 86400000;
                                time += 86400000;
                                performC += 86400000;
                                robC += 86400000;
                            }
                        }
                        if(faster) {
                            rehearseC = time + 64740000;
                        } else {
                            rehearseC = time + 86340000;
                        }
                    }
                } else {
                    e.getChannel().sendMessage("You must hire an orchestra to use this command!").queue();
                }
            }
            case "perform" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                if (time < performC) {
                    long milliseconds = performC - time;
                    long days = milliseconds / 86400000;
                    milliseconds -= days * 86400000;
                    long hours = milliseconds / 3600000;
                    milliseconds -= hours * 3600000;
                    long minutes = milliseconds / 60000;
                    milliseconds -= minutes * 60000;
                    long seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    e.getChannel().sendMessage("Don't tire yourself with two performances a week!  Wait " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                } else {
                    long base = calculateAmount(e, data, (long) ((random.nextInt(1001) + 4500) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                    double num = random.nextDouble();
                    if(num > 0.8) {
                        violins += base;
                        e.getChannel().sendMessage("You performed your solo and earned " + base + ":violin:").queue();
                    } else if(num > 0.55) {
                        num = random.nextDouble();
                        if(num > 0.5) {
                            rice += 9;
                            e.getChannel().sendMessage("Your paycheck contained 9:rice: instead of violins.").queue();
                        } else if(num > 0.1) {
                            tea += 4;
                            e.getChannel().sendMessage("You found 4:bubble_tea: after you performed.").queue();
                        } else {
                            blessing ++;
                            e.getChannel().sendMessage("Ling Ling enjoyed your performance and blessed you.").queue();
                        }
                    } else if(num > 0.25) {
                        num = random.nextDouble();
                        if(num > 0.6) {
                            e.getChannel().sendMessage("Your teacher approved your performance.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.1;
                            violins += base;
                        } else if(num > 0.25) {
                            e.getChannel().sendMessage("Your tiger mom approved your performance.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.5;
                            violins += base;
                        } else if(num > 0.1) {
                            base *= 2;
                            violins += base;
                            e.getChannel().sendMessage("Ling Ling approved of your performance and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                        } else if(num > 0.02) {
                            base *= 5;
                            violins += base;
                            e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
                        } else {
                            base *= 25;
                            violins += base;
                            e.getChannel().sendMessage("The Berlin Philharmonic featured your performance on their homepage and you earned " + base + ":violin: from the ads and royalty payments").queue();
                        }
                    } else if(num > 0.05) {
                        num = random.nextDouble();
                        if(num > 0.8) {
                            violins -= hourlyIncome / 100;
                            base *= 0.5;
                            violins += base;
                            e.getChannel().sendMessage("Oh no!  Your E String snapped during the performance!  You couldn't go on, and only earned " + base + ":violin:  You eventually had to pay " + (hourlyIncome / 100) + ":violin: for a new E String.").queue();
                        } else if(num > 0.6) {
                            base *= 0.9;
                            violins += base;
                            e.getChannel().sendMessage("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                        } else if(num > 0.45) {
                            base *= 0.9;
                            e.getChannel().sendMessage("You didn't memorize your piece and you had to use your music.  You only earned " + base + ":violin:").queue();
                        } else if(num > 0.3) {
                            base *= 0.5;
                            violins += base;
                            violins -= hourlyIncome / 10;
                            e.getChannel().sendMessage("You hurt your wrist during the performance and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + (hourlyIncome / 10) + ":violin: in hospital fees.").queue();
                        } else if(num > 0.2) {
                            base *= 0.1;
                            violins += base;
                            e.getChannel().sendMessage("Your bridge fell off during the performance.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
                        } else if(num > 0.1) {
                            violins -= hourlyIncome;
                            e.getChannel().sendMessage("You had a memory blank during the performance.  Your tiger mom took all your earnings, in addition to another " + hourlyIncome + " for shaming yourself.").queue();
                        } else if(num > 0.05) {
                            base *= 0.5;
                            violins -= hourlyIncome;
                            violins += base;
                            e.getChannel().sendMessage("Your performance was for a competition, and you only won Honorable Mention.  Your tiger mom Kung-Paos your chicken and takes half your earnings, in addition to another " + hourlyIncome + " for being so mediocre.").queue();
                        } else if(num > 0.015) {
                            e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
                            workC += 43200000;
                        } else if(num > 0.005) {
                            e.getChannel().sendMessage("You decided to fake your performance.  Of course it didn't work and Ling Ling fined you " + violins * (hourlyIncome / 100000) + ":violin:").queue();
                            violins -= violins * (hourlyIncome / 100000);
                        } else {
                            e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined pretty much your entire balance for being careless on stage.").queue();
                            violins *= 0.02;
                            workC += 86400000;
                            rehearseC += 86400000;
                            time += 86400000;
                            robC += 86400000;
                        }
                    } else {
                        num = random.nextDouble();
                        if(num > 0.5) {
                            base *= 3;
                            violins += base;
                            medals ++;
                            thirdP ++;
                            e.getChannel().sendMessage(":trophy: Your performance won third place in the Ling Ling Competition.  Your earnings were tripled, and you walked away with 1:military_medal: and a third place trophy :third_place:").queue();
                        } else if(num > 0.2) {
                            base *= 15;
                            violins += base;
                            medals += 2;
                            secondP ++;
                            e.getChannel().sendMessage(":trophy: Your performance won second place.  Your earnings were multiplied by 15, and you walked away with 2:military_medal: and a second place trophy :second_place:").queue();
                        } else {
                            base *= 100;
                            violins += base;
                            medals += 3;
                            firstP ++;
                            e.getChannel().sendMessage(":trophy: Your performance won first place.  Congratulations!  Your earnings were multiplied by 100, and you walked away with **3**:military_medal: and a FIRST place trophy :first_place:").queue();
                        }
                        if(!extraIncome && medals >= 5) {
                            e.getChannel().sendMessage("You achieved 5:military_medal:!  Congratulations!  You have received an extra 8 000 hourly income!").queue();
                            extraIncome = true;
                        } else if(!extraCommandIncome && medals >= 10) {
                            e.getChannel().sendMessage("You achieved 10:military_medal:!  Congratulations!  You now have 2x income from most commands!").queue();
                            extraCommandIncome = true;
                        } else if(!higherWinrate && medals >= 15) {
                            e.getChannel().sendMessage("You have achieved 15:military_medal:!  Congratulations!  You now have an extra 5% gambling multiplier!").queue();
                            higherWinrate = true;
                        } else if(!higherRobrate && medals >= 25) {
                            e.getChannel().sendMessage("You have achieved 25:military_medal:!  Congratulations!  You now have a 2.5% greater chance to succeed at robbing another user!  Note that this does **NOT** circumvent Ling Ling Insurance").queue();
                            higherRobrate = true;
                        } else if(!stealShield && medals >= 35) {
                            e.getChannel().sendMessage("You have achieved 35:military_medal:!  Congratulations!  You now have a Steal Shield, which protects an additional 50% of :violin: from being robbed!").queue();
                            stealShield = true;
                        } else if(!violinDuplicator && medals >= 50) {
                            e.getChannel().sendMessage("You have achieved 50:military_medal:!  Congratulations!  You now have a Violin Duplicator, which doubles all violins robbed from other users!").queue();
                            violinDuplicator = true;
                        }
                    }
                    if(faster) {
                        performC = time + 215940000;
                    } else {
                        performC = time + 302340000;
                    }
                }
            }
            case "daily", "d" -> {
                if(!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                }
                if(!e.getMessage().getContentRaw().contains("!d bump")) {
                    if (time < dailyC) {
                        long milliseconds = dailyC - time;
                        long hours = milliseconds / 3600000;
                        milliseconds -= hours * 3600000;
                        long minutes = milliseconds / 60000;
                        milliseconds -= minutes * 60000;
                        long seconds = milliseconds / 1000;
                        milliseconds -= seconds * 1000;
                        e.getChannel().sendMessage("I can't give out violins that fast, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                    } else {
                        if(time > dailyExp) {
                            e.getChannel().sendMessage("Oh no!  Your streak was reset!").queue();
                            streak = 0;
                        }
                        long base = 40000 + (streak * 100);
                        violins += base;
                        dailyC = time + 85500000;
                        dailyExp = time + 172800000;
                        streak++;
                        e.getChannel().sendMessage("You received a total of " + base + ":violin:, with " + (streak - 1) * 100 + ":violin: coming from your " + streak + "-day streak!").queue();
                    }
                }
            }
            case "rob" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                if (time < robC) {
                    long milliseconds = robC - time;
                    long hours = milliseconds / 3600000;
                    milliseconds -= hours * 3600000;
                    long minutes = milliseconds / 60000;
                    milliseconds -= minutes * 60000;
                    long seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    e.getChannel().sendMessage("Hey, Brett and Eddy are still looking for you after your last hit!  Wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                } else {
                    String name;
                    try {
                        name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                    } catch(Exception exception) {
                        e.getChannel().sendMessage("You cannot rob nobody.  That doesn't make sense.").queue();
                        throw new IllegalArgumentException();
                    }
                    if (e.getAuthor().getId().equals(target)) {
                        e.getChannel().sendMessage("Why would you rob yourself, are you actually that dumb?").queue();
                        throw new IllegalArgumentException();
                    }
                    String[] targetdata;
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                        targetdata = reader.readLine().split(" ");
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("You did not mention a user or provide a valid User ID.  Doesn't make sense to rob a non-existant user, does it?").queue();
                        throw new IllegalArgumentException();
                    }
                    long targetViolins;
                    targetViolins = Long.parseLong(targetdata[0]);
                    double failChance = (double) violins / (targetViolins + violins);
                    failChance -= 0.005 * robL;
                    if(higherRobrate) {
                        failChance -= 0.025;
                    }
                    double num = random.nextDouble();
                    long insurance = Long.parseLong(targetdata[11]);
                    boolean opponentShield = Boolean.parseBoolean(targetdata[60]);
                    long baseRob = (long) (targetViolins * 0.2);
                    if(baseRob > 2000000) {
                        baseRob = 2000000;
                    }
                    if (num < failChance) {
                        e.getChannel().sendMessage("Brett and Eddy caught you trying to rob " + name + "!  You paid " + name + " " + baseRob + ":violin: for attempting to rob them.\n*The generator rolled " + num + ", you need at least " + failChance + " to succeed.*").queue();
                        User send = e.getJDA().getUserById(target);
                        send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you but failed!  They paid you " + baseRob + ":violin: in fines.").queue();
                        targetViolins += baseRob;
                        violins -= baseRob;
                    } else {
                        if (insurance == 1) {
                            e.getChannel().sendMessage("You try to rob " + name + " only to notice that Ling Ling Security is present.  You try to escape but you are caught and kicked from the estate.  You are fined " + (long) (violins * 0.03) + ":violin:.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                            User send = e.getJDA().getUserById(target);
                            violins -= violins * 0.03;
                            if(opponentShield) {
                                send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you!  Luckily, insurance protected you and you only paid " + (long) (baseRob * 0.06) + ":violin: in insurance costs.  Your Steal Shield protected " + (long) (baseRob * 0.06) + ":violin: from being paid.").queue();
                                targetViolins -= baseRob * 0.12;
                            } else {
                                send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you!  Luckily, insurance protected you and you only paid " + (long) (baseRob * 0.12) + ":violin: in insurance costs.").queue();
                                targetViolins -= baseRob * 0.06;
                            }
                        } else if (insurance == 2) {
                            User send = e.getJDA().getUserById(target);
                            if(opponentShield) {
                                e.getChannel().sendMessage("You successfully robbed " + name + " but ran into a Steal Shield.  You only managed to get away with " + (long) (baseRob * 0.025) + ":violin: before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.025) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (baseRob * 0.95) + ":violin: and your Steal Shield protected " + (long) (baseRob * 0.025) + ":violin:").queue();
                                targetViolins -= baseRob * 0.025;
                                if(violinDuplicator) {
                                    e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                    violins += baseRob * 0.05;
                                } else {
                                    violins += baseRob * 0.025;
                                }
                            } else {
                                e.getChannel().sendMessage("You successfully robbed " + name + " but only managed to get away with " + (long) (baseRob * 0.05) + ":violin: before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.05) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (baseRob * 0.95) + ":violin:").queue();
                                targetViolins -= baseRob * 0.05;
                                if(violinDuplicator) {
                                    e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                    violins += baseRob * 0.1;
                                } else {
                                    violins += baseRob * 0.05;
                                }
                            }
                        } else {
                            User send = e.getJDA().getUserById(target);
                            if(opponentShield) {
                                e.getChannel().sendMessage("You successfully robbed " + name + " but a Steal Shield stopped your looting halfway through.  Your payout was " + (long) (baseRob * 0.5) + ":violin:\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.5) + ":violin: from you!  Your Steal Shield protected " + (long) (baseRob * 0.5) + ":violin:").queue();
                                targetViolins -= baseRob * 0.5;
                                if(violinDuplicator) {
                                    e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                    violins += baseRob;
                                } else {
                                    violins += baseRob * 0.5;
                                }
                            } else {
                                e.getChannel().sendMessage("You successfully robbed " + name + "!  Your payout was " + baseRob + ":violin:\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + baseRob + ":violin: from you!").queue();
                                targetViolins -= baseRob;
                                if(violinDuplicator) {
                                    e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                    violins += baseRob * 2;
                                } else {
                                    violins += baseRob;
                                }
                            }
                        }
                    }
                    robC = time + 57540000;
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt")));
                        writer.print(targetViolins);
                        for(int i = 1; i < targetdata.length; i ++) {
                            writer.print(" " + targetdata[i]);
                        }
                        writer.close();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                }
            }
            case "gamble", "bet" -> {
                if (!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                long bet;
                try {
                    bet = Long.parseLong(message[2]);
                } catch (Exception exception) {
                    e.getChannel().sendMessage("You must bet something, how smol brane are you???").queue();
                    throw new IllegalArgumentException();
                }
                if (time < gambleC) {
                    long milliseconds = gambleC - time;
                    long seconds = milliseconds / 1000;
                    milliseconds -= seconds * 1000;
                    e.getChannel().sendMessage("Don't bet your violins like Paganini, wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                } else if (bet > violins) {
                    e.getChannel().sendMessage("You can't bet more than you have, don't try to outsmart me.").queue();
                } else if (bet < 0) {
                    e.getChannel().sendMessage("You can't bet a negative number, don't try breaking me.").queue();
                } else if (bet < 4000) {
                    e.getChannel().sendMessage("If you're going to bet less than 4000:violin:, go away and stop wasting my time.").queue();
                } else if(bet > hourlyIncome * 8) {
                    e.getChannel().sendMessage("You cannot bet more than " + hourlyIncome * 8 + ":violin:  To raise this cap, upgrade your hourly income!").queue();
                } else {
                    try {
                        double multi = 0.005 * gambleL;
                        if(higherWinrate) {
                            multi += 0.05;
                        }
                        switch (message[1]) {
                            case "rng" -> {
                                gambleC = time + 29000;
                                double chance = random.nextDouble();
                                if (chance > 0.5) {
                                    violins -= bet;
                                    e.getChannel().sendMessage("You lost " + bet + ":violin:\n*The generator rolled " + chance + ", you need less than 0.5 to win.*\nYou now have " + violins + ":violin:").queue();
                                } else if (chance <= 0.5) {
                                    violins += bet * (1 + multi);
                                    e.getChannel().sendMessage("You won " + bet + ":violin:\nYour " + multi + "% multiplier earned you an extra " + (long) (bet * multi) + ":violin:\n*The generator rolled " + chance + ".*\nYou now have " + violins + ":violin:").queue();
                                }
                            }
                            case "slots" -> {
                                gambleC = time + 29000;
                                long[] slots = {random.nextInt(6), random.nextInt(6), random.nextInt(6)};
                                String[] emojis = new String[3];
                                for(int i = 0; i < slots.length; i ++) {
                                    if(slots[i] == 0) {
                                        emojis[i] = ":trumpet:";
                                    } else if(slots[i] == 1) {
                                        emojis[i] = ":violin:";
                                    } else if(slots[i] == 2) {
                                        emojis[i] = "<:lingling40hrs:688449820410773532>";
                                    } else if(slots[i] == 3) {
                                        emojis[i] = "<:twoset:688452024009883669>";
                                    } else if(slots[i] == 4) {
                                        emojis[i] = "<:linglingclock:747499551451250730>";
                                    } else {
                                        emojis[i] = "<a:StradSpam:772894512154279945>";
                                    }
                                }
                                long payout = bet;
                                if(slots[0] == slots[1] && slots[1] == slots[2]) {
                                    if(slots[0] == 0) {
                                        payout *= 2.5;
                                    } else if(slots[0] == 1) {
                                        payout *= 5;
                                    } else if(slots[0] == 2) {
                                        payout *= 10;
                                    } else if(slots[0] == 3) {
                                        payout *= 15;
                                    } else if(slots[0] == 4) {
                                        payout *= 25;
                                    } else {
                                        payout *= 40;
                                    }
                                } else if(slots[0] == slots[1] || slots[1] == slots[2] || slots[2] == slots[0]) {
                                    payout *= 1;
                                } else {
                                    payout = -1;
                                }
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling Bot", e.getJDA().getSelfUser().getAvatarUrl())
                                        .setTitle("__**Slots for " + e.getAuthor().getName() + "**__");
                                if(payout != -1) {
                                    builder.addField(":arrow_right: " + emojis[0] + " " + emojis[1] + " " + emojis[2] + " :arrow_left:\n_ _", ":white_check_mark: You **win**!  Payout: " + payout + ":violin:\nYour " + multi * 100 + "% multiplier earned you an extra " + (long) (payout * multi) + ":violin:", false);
                                    violins += payout * (1 + multi);
                                } else {
                                    builder.addField(":arrow_right: " + emojis[0] + " " + emojis[1] + " " + emojis[2] + " :arrow_left:\n_ _", ":x: You **lose**!  You lost " + bet + ":violin:", false);
                                    violins -= bet;
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "scratch" -> {
                                gambleC = time + 29000;
                                long[] payouts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                                if (bet % 100 != 0) {
                                    e.getChannel().sendMessage("You must bet a multiple of 100 because scratch tickets cost 100:violin: each.").queue();
                                } else {
                                    long numTickets = bet / 100;
                                    long payout = 0;
                                    for (long i = 0; i < numTickets; i++) {
                                        double chance = random.nextDouble();
                                        if (chance > 0.4) {
                                            payout -= 4;
                                            payouts[0] ++;
                                        } else if (chance > 0.2) {
                                            payouts[1] ++;
                                        } else if (chance > 0.1) {
                                            payout += 2;
                                            payouts[2] ++;
                                        } else if (chance > 0.05) {
                                            payout += 5;
                                            payouts[3] ++;
                                        } else if (chance > 0.02) {
                                            payout += 10;
                                            payouts[4] ++;
                                        } else if (chance > 0.01) {
                                            payout += 25;
                                            payouts[5] ++;
                                        } else if (chance > 0.005) {
                                            payout += 50;
                                            payouts[6] ++;
                                        } else if (chance > 0.002) {
                                            payout += 100;
                                            payouts[7] ++;
                                        } else if (chance > 0.001) {
                                            payout += 200;
                                            payouts[8] ++;
                                        } else if (chance > 0.00001) {
                                            payout += 500;
                                            payouts[9] ++;
                                        } else {
                                            payout += 1000000;
                                            e.getChannel().sendMessage("You hit the 1 000 000:violin: jackpot!").queue();
                                        }
                                    }
                                    StringBuilder breakdown = new StringBuilder().append("Lose 4:violin:: ").append(payouts[0]).append("\nNo Prize: ").append(payouts[1]).append("\nGain 2:violin:: ").append(payouts[2]).append("\nGain 5:violin:: ").append(payouts[3]).append("\nGain 10:violin:: ").append(payouts[4]).append("\nGain 25:violin:: ").append(payouts[5]).append("\nGain 50:violin:: ").append(payouts[6]).append("\nGain 100:violin:: ").append(payouts[7]).append("\nGain 200:violin:: ").append(payouts[8]).append("\nGain 500:violin:: ").append(payouts[9]);
                                    if(payout > 0) {
                                        violins += payout * (1 + multi);
                                        e.getChannel().sendMessage("You scratched " + numTickets + " tickets and gained " + payout + ":violin:\nYour " + multi * 100 + "% multiplier earned you an extra " + (long) (payout * multi) + ":violin:\n\n**__Ticket Breakdown__**\n" + breakdown.toString()).queue();
                                    } else {
                                        violins += payout;
                                        e.getChannel().sendMessage("You scratched " + numTickets + " tickets and lost " + payout * -1 + ":violin:\n\n**__Ticket Breakdown__**\n" + breakdown.toString()).queue();
                                    }
                                }
                            }
                            default -> e.getChannel().sendMessage("You must choose one of the three gambling options: `rng`, `scratch`, or `slots`").queue();
                        }
                    } catch(Exception exception) {
                        e.getChannel().sendMessage("You must choose one of the three gambling options: `rng`, `scratch`, or `slots`").queue();
                    }
                }
            }
            case "inventory", "inv" -> {
                if (message.length == 1) {
                    if (!hasData) {
                        e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                        throw new IllegalArgumentException();
                    }
                    EmbedBuilder builder = new EmbedBuilder()
                            .setColor(Color.BLUE)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .setTitle(e.getAuthor().getName() + "'s Inventory")
                            .addField("Rice :rice:", "Count: " + rice + "\nUsage: Doubles your hourly income for one hour.\nCaps at 200 000:violin:/hour\nID: `rice`", false)
                            .addField("Bubble Tea :bubble_tea:", "Count: " + tea + "\nUsage: Runs `" + prefix + "practice` 2 times immediately.  Only gives violins.\nID: `tea`", false)
                            .addField("Ling Ling Blessing :angel:", "Count: " + blessing + "\nUsage: Runs `" + prefix + "perform` 1 time and GUARANTEES a medal.  Does not multiply violins earned.\nID: `blessing`", false);
                    e.getChannel().sendMessage(builder.build()).queue();
                } else {
                    String name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                        String line = reader.readLine();
                        EmbedBuilder builder = new EmbedBuilder()
                                .setColor(Color.BLUE)
                                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                .setTitle(name + "'s Inventory")
                                .addField("Rice :rice:", "Count: " + line.split(" ")[51], false)
                                .addField("Bubble Tea :bubble_tea:", "Count: " + line.split(" ")[62], false)
                                .addField("Ling Ling Blessing :angel:", "Count: " + line.split(" ")[63], false);
                        e.getChannel().sendMessage(builder.build()).queue();
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This save file does not exist!").queue();
                    }
                }
            }
            case "profile", "balance", "bal", "b"-> {
                if(message.length == 1) {
                    if (!hasData) {
                        e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                        throw new IllegalArgumentException();
                    }
                    EmbedBuilder builder = new EmbedBuilder()
                            .setColor(Color.BLUE)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .setTitle(e.getAuthor().getName() + "'s Profile");
                            if(realIncome != hourlyIncome) {
                                builder.addField("General Stats", "Balance: " + violins + ":violin:\nLing Ling Medals: " + medals + ":military_medal:\nHourly Income: " + realIncome + " (:rice: " + hourlyIncome + "):violin:/hour", false);
                            } else {
                                builder.addField("General Stats", "Balance: " + violins + ":violin:\nLing Ling Medals: " + medals + ":military_medal:\nHourly Income: " + realIncome + ":violin:/hour", false);
                            }
                            builder.addField("Medals", ":first_place:" + firstP + "\n:second_place:" + secondP + "\n:third_place:" + thirdP, false);
                    e.getChannel().sendMessage(builder.build()).queue();
                } else {
                    String name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                        String line = reader.readLine();
                        EmbedBuilder builder = new EmbedBuilder()
                                .setColor(Color.BLUE)
                                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                .setTitle(name + "'s Profile")
                                .addField("General Stats", "Balance: " + line.split(" ")[0] + ":violin:\nLing Ling Medals: " + line.split(" ")[55] + ":military_medal:\nHourly Income: " + line.split(" ")[65] + ":violin:/hour", false)
                                .addField("Medals", ":first_place:" + line.split(" ")[54] + "\n:second_place:" + line.split(" ")[53] + "\n:third_place:" + line.split(" ")[52], false);
                        e.getChannel().sendMessage(builder.build()).queue();
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This save file does not exist!").queue();
                    }
                }
            }
            case "leaderboard", "lb" -> {
                try {
                    leaderboard(":violin:", e, 0, message[1].equals("global"), violins);
                } catch(Exception exception) {
                    leaderboard(":violin:", e, 0, false, violins);
                }
            }
            case "streakleaderboard", "streaklb" -> {
                try {
                    leaderboard(":clock2:", e, 47, message[1].equals("global"), streak);
                } catch(Exception exception) {
                    leaderboard(":clock2:", e, 47, false, streak);
                }
            }
            case "medalleaderboard", "medallb" -> {
                try {
                    leaderboard(":military_medal:", e, 55, message[1].equals("global"), medals);
                } catch(Exception exception) {
                    leaderboard(":military_medal:", e, 55, false, medals);
                }
            }
            case "incomeleaderboard", "incomelb" -> {
                try {
                    leaderboard(":violin:/hour", e, 65, message[1].equals("global"), hourlyIncome);
                } catch(Exception exception) {
                    leaderboard(":violin:/hour", e, 65, false, hourlyIncome);
                }
            }
        }
        if (hasData) {
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                writer.print(violins + " " + workC + " " + workL + " " + gambleC + " " + gambleL + " " + robC + " " + robL + " " + rehearseC + " " + performC + " " + ownInsurance1 + " " + ownInsurance2 + " " + activeInsurance + " " + hourlyIncome + " " + violinQuality + " " + skillLevel + " " + lessonQuality + " " + stringQuality + " " + bowQuality + " " + hasMath + " " + hasOrchestra + " " + piccolo + " " + flute + " " + oboe + " " + clarinet + " " + bassoon + " " + contrabassoon + " " + horn + " " + trumpet + " " + trombone + " " + tuba + " " + timpani + " " + percussion + " " + first + " " + second + " " + cello + " " + stringBass + " " + piano + " " + harp + " " + soprano + " " + alto + " " + tenor + " " + bass + " " + soloists + " " + hallLevel + " " + conductor + " " + advertising + " " + tickets + " " + streak + " " + dailyC + " " + dailyExp + " " + faster + " " + rice + " " + thirdP + " " + secondP + " " + firstP + " " + medals + " " + extraIncome + " " + extraCommandIncome + " " + higherWinrate + " " + higherRobrate + " " + stealShield + " " + violinDuplicator + " " + tea + " " + blessing + " " + scaleC + " " + realIncome);
                writer.close();
            } catch (Exception exception) {
                //nothing here lol
            }
        }
    }
}