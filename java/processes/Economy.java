package processes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Economy {
    public static void leaderboard(String type, GuildMessageReceivedEvent e, int dataPosition, boolean global) {
        BufferedReader reader;
        File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        String[] entry = new String[0];
        boolean isGlobal = false;
        if (files != null) {
            if (global) {
                isGlobal = true;
                entry = new String[]{"<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n", "<@0>: 0 " + type + "\n"};
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
                    int num = Integer.parseInt(temp[dataPosition]);
                    if (num == 0) {
                        continue;
                    }
                    for (int i = 0; i < 10; i++) {
                        if (num > Integer.parseInt(entry[i].split(" ")[1])) {
                            System.arraycopy(entry, i, entry, i + 1, 9 - i);
                            entry[i] = "<@" + user + ">: " + num + " " + type + "\n";
                            break;
                        }
                    }
                }
            } else {
                entry = new String[]{"null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n", "null#0000 `0`: 0 " + type + "\n"};
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
                    int num = Integer.parseInt(temp[dataPosition]);
                    if (num == 0) {
                        continue;
                    }
                    for (int i = 0; i < 10; i++) {
                        if (num > Integer.parseInt(entry[i].split(" ")[2])) {
                            System.arraycopy(entry, i, entry, i + 1, 9 - i);
                            String name = user.getUser().getName().replace(' ', '-');
                            entry[i] = name + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: " + num + " " + type + "\n";
                            break;
                        }
                    }
                }
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
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                .setTitle("__**" + measurement + "Leaderboard**__");
        if (isGlobal) {
            builder.addField(item + "in the World", board.toString(), false);
        } else {
            builder.addField(item + "in " + e.getGuild().getName(), board.toString(), false);
        }
        e.getChannel().sendMessage(builder.build()).queue();
    }

    public static int calculateAmount(GuildMessageReceivedEvent e, String[] data, int base) {
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
        if(data[19].equals("true")) {
            base *= 2;
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
            data = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "false", "0", "0", "0", "0", "0", "0", "0", "false", "false", "false", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "false", "false", "false", "false", "false", "false", "0", "0"};
        }
        long violins = Long.parseLong(data[0]);
        long workC = Long.parseLong(data[1]);
        int workL = Integer.parseInt(data[2]);
        long gambleC = Long.parseLong(data[3]);
        int gambleL = Integer.parseInt(data[4]);
        long robC = Long.parseLong(data[5]);
        int robL = Integer.parseInt(data[6]);
        long rehearseC = Long.parseLong(data[7]);
        long performC = Long.parseLong(data[8]);
        boolean ownInsurance1 = Boolean.parseBoolean(data[9]);
        boolean ownInsurance2 = Boolean.parseBoolean(data[10]);
        int activeInsurance = Integer.parseInt(data[11]);
        long hourlyIncome = Long.parseLong(data[12]);
        int violinQuality = Integer.parseInt(data[13]);
        int skillLevel = Integer.parseInt(data[14]);
        int lessonQuality = Integer.parseInt(data[15]);
        int stringQuality = Integer.parseInt(data[16]);
        int bowQuality = Integer.parseInt(data[17]);
        boolean hasMath = Boolean.parseBoolean(data[18]);
        boolean hasOrchestra = Boolean.parseBoolean(data[19]);
        boolean piccolo = Boolean.parseBoolean(data[20]);
        int flute = Integer.parseInt(data[21]);
        int oboe = Integer.parseInt(data[22]);
        int clarinet = Integer.parseInt(data[23]);
        int bassoon = Integer.parseInt(data[24]);
        boolean contrabassoon = Boolean.parseBoolean(data[25]);
        int horn = Integer.parseInt(data[26]);
        int trumpet = Integer.parseInt(data[27]);
        int trombone = Integer.parseInt(data[28]);
        int tuba = Integer.parseInt(data[29]);
        int timpani = Integer.parseInt(data[30]);
        int percussion = Integer.parseInt(data[31]);
        int first = Integer.parseInt(data[32]);
        int second = Integer.parseInt(data[33]);
        int cello = Integer.parseInt(data[34]);
        int stringBass = Integer.parseInt(data[35]);
        int piano = Integer.parseInt(data[36]);
        boolean harp = Boolean.parseBoolean(data[37]);
        int soprano = Integer.parseInt(data[38]);
        int alto = Integer.parseInt(data[39]);
        int tenor = Integer.parseInt(data[40]);
        int bass = Integer.parseInt(data[41]);
        int soloists = Integer.parseInt(data[42]);
        int hallLevel = Integer.parseInt(data[43]);
        int conductor = Integer.parseInt(data[44]);
        int advertising = Integer.parseInt(data[45]);
        int tickets = Integer.parseInt(data[46]);
        int streak = Integer.parseInt(data[47]);
        long dailyC = Long.parseLong(data[48]);
        long dailyExp = Long.parseLong(data[49]);
        boolean faster = Boolean.parseBoolean(data[50]);
        int rice = Integer.parseInt(data[51]);
        int thirdP = Integer.parseInt(data[52]);
        int secondP = Integer.parseInt(data[53]);
        int firstP = Integer.parseInt(data[54]);
        int medals = Integer.parseInt(data[55]);
        boolean extraIncome = Boolean.parseBoolean(data[56]);
        boolean extraCommandIncome = Boolean.parseBoolean(data[57]);
        boolean higherWinrate = Boolean.parseBoolean(data[58]);
        boolean higherRobrate = Boolean.parseBoolean(data[59]);
        boolean stealShield = Boolean.parseBoolean(data[60]);
        boolean violinDuplicator = Boolean.parseBoolean(data[61]);
        int tea = Integer.parseInt(data[62]);
        int blessing = Integer.parseInt(data[63]);
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
        long workCost = (long) (Math.pow(1.2, workL)) * 75;
        long gambleCost = (long) (Math.pow(1.35, gambleL)) * 800;
        long robCost = (long) (Math.pow(1.5, robL)) * 5000;
        long violinCost = (long) (Math.pow(10, violinQuality)) * 1000;
        long skillCost = (long) (Math.pow(3, skillLevel)) * 500;
        long lessonCost = (long) (Math.pow(4, lessonQuality)) * 700;
        long stringCost = (long) (Math.pow(2, stringQuality)) * 400;
        long bowCost = (long) (Math.pow(5, bowQuality)) * 500;
        long hallCost = (long) (Math.pow(10, hallLevel)) * 10000;
        long conductorCost = (long) (Math.pow(5, conductor)) * 100000;
        long ticketCost = (long) (Math.pow(2, tickets)) * 2000000;
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
        long bassCost = 75000L * (tenor + 1);
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
                                        .addField("Violin Quality (" + violinQuality + "/5)", "Price: " + violinCost + "\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
                                        .addField("Skill Level (" + skillLevel + "/10)", "Price: " + skillCost + "\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
                                        .addField("Lesson Quality (" + lessonQuality + "/8)", "Price: " + lessonCost + "\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
                                        .addField("String Quality (" + stringQuality + "/15)", "Price: " + stringCost + "\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
                                        .addField("Bow Quality (" + bowQuality + "/7)", "Price: " + bowCost + "\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
                                        .setTitle("__**Income Upgrades**__");
                                if (hasMath) {
                                    builder.addField("Math Tutoring (1/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
                                } else {
                                    builder.addField("Math Tutoring (0/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "2" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Uesr balance: " + violins + "\nUser `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Concert Hall Quality (" + hallLevel + "/2)", "Price: " + hallCost + "\nEffect: +300:violin:/hour, +20% violins from `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                                        .addField("Orchestra", "Price: 40 000 000\nIncome Requirement: 7 500\nEffect: +3000:violin:/hour, x2 violins from `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID:`orchestra`, `o`", false)
                                        .setTitle("__**Miscellaneous Orchestra Items**__");
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "3" -> {
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Efficient Practising (" + workL + "/75)", "Price: " + workCost + "\nEffect: Increases your income from `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                                        .addField("Lucky Musician (" + gambleL + "/40)", "Price: " + gambleCost + "\nEffect: Increases your chance of winning at `" + prefix + "bet` by 0.75%.\nID: `lucky`, `lm`", false)
                                        .addField("Sophisticated Robbing (" + robL + "/25)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
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
                                    builder.addField("Higher Gamble Winrate :white_check_mark:", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
                                } else {
                                    builder.addField("Higher Gamble Winrate", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
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
                                        .addField("Violin Quality (" + violinQuality + "/5)", "Price: " + violinCost + "\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
                                        .addField("Skill Level (" + skillLevel + "/10)", "Price: " + skillCost + "\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
                                        .addField("Lesson Quality (" + lessonQuality + "/8)", "Price: " + lessonCost + "\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
                                        .addField("String Quality (" + stringQuality + "/15)", "Price: " + stringCost + "\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
                                        .addField("Bow Quality (" + bowQuality + "/7)", "Price: " + bowCost + "\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
                                        .setTitle("__**Income Upgrades**__");
                                if (hasMath) {
                                    builder.addField("Math Tutoring (1/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
                                } else {
                                    builder.addField("Math Tutoring (0/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
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
                                        .addField("Efficient Practising (" + workL + "/75)", "Price: " + workCost + "\nEffect: Increases your income from `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                                        .addField("Lucky Musician (" + gambleL + "/40)", "Price: " + gambleCost + "\nEffect: Increases your chance of winning at `" + prefix + "bet` by 0.75%.\nID: `lucky`, `lm`", false)
                                        .addField("Sophisticated Robbing (" + robL + "/25)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
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
                                    builder.addField("Higher Gamble Winrate :white_check_mark:", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
                                } else {
                                    builder.addField("Higher Gamble Winrate", "Needed: 15:military_medal:\nEffect: +5% `" + prefix + "gamble` multiplier", false);
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
                switch (message[1]) {
                    case "1" -> {
                        if (ownInsurance1) {
                            e.getChannel().sendMessage("You already bought this insurance!  Run `" + prefix + "use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.").queue();
                        } else if (violins < 2500000) {
                            e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                        } else {
                            ownInsurance1 = true;
                            violins -= 2000000;
                            e.getChannel().sendMessage("You have successfully bought Ling Ling Insurance - Plan 1 - Full Security!  Run `" + prefix + "use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "2" -> {
                        if (ownInsurance2) {
                            e.getChannel().sendMessage("You already bought this insurance!  Run `" + prefix + "use 2` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 1.").queue();
                        } else if (violins < 2500000) {
                            e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                        } else {
                            ownInsurance2 = true;
                            violins -= 2500000;
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
                            e.getChannel().sendMessage("You approach Ling Ling and pay him (her?) 200 million violins.  Ling Ling then does magic and you are back in your room.  You feel like you can do things faster, around 25% faster.\n`" + prefix + "practice` cooldown is now 30 minutes.\n`" + prefix + "rehearse` cooldown is now 18 hours.\n`" + prefix + "perform` cooldown is now 2.5 days.\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "efficient", "practising", "ep" -> {
                        if (workL == 75) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < workCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= workCost;
                            workL++;
                            e.getChannel().sendMessage("Successfully upgraded Efficient Practising to Level " + workL + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "lucky", "lm" -> {
                        if (gambleL == 40) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < gambleCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= gambleCost;
                            gambleL++;
                            e.getChannel().sendMessage("Successfully upgraded Lucky Musician to Level " + gambleL + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "sophisticated", "robbing", "sr" -> {
                        if (robL == 25) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < robCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= robCost;
                            robL++;
                            e.getChannel().sendMessage("Successfully upgraded Sophisticated Robbing to Level " + robL + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "violin", "v" -> {
                        if (violinQuality == 5) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < violinCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= violinCost;
                            violinQuality++;
                            hourlyIncome += 600;
                            e.getChannel().sendMessage("Successfully upgraded Violin Quality to Level " + violinQuality + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "skill", "s" -> {
                        if (skillLevel == 10) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < skillCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= skillCost;
                            skillLevel++;
                            hourlyIncome += 240;
                            e.getChannel().sendMessage("Successfully upgraded Skill Level to Level " + skillLevel + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "lesson", "l" -> {
                        if (lessonQuality == 8) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < lessonCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= lessonCost;
                            lessonQuality++;
                            hourlyIncome += 150;
                            e.getChannel().sendMessage("Successfully upgraded Lesson Quality to Level " + lessonQuality + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "string", "str" -> {
                        if (stringQuality == 15) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < stringCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= stringCost;
                            stringQuality++;
                            hourlyIncome += 100;
                            e.getChannel().sendMessage("Successfully upgraded String Quality to Level " + stringQuality + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "bow", "b" -> {
                        if (bowQuality == 7) {
                            e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                        } else if (violins < bowCost) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= bowCost;
                            bowQuality++;
                            hourlyIncome += 200;
                            e.getChannel().sendMessage("Successfully upgraded Bow Quality to Level " + bowQuality + "\nYou have " + violins + ":violin: left.").queue();
                        }
                    }
                    case "math" -> {
                        if (hasMath) {
                            e.getChannel().sendMessage("You already bought this upgrade!").queue();
                        } else if (violins < 5000000) {
                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                        } else {
                            violins -= 5000000;
                            hasMath = true;
                            hourlyIncome += 2500;
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
                            hourlyIncome += 3130;
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
                                hourlyIncome += 300;
                                e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + "!\nYou have " + violins + ":violin: left.").queue();
                            }
                        } else {
                            if (hallLevel == 5) {
                                e.getChannel().sendMessage("You can't buy more of this upgrade!").queue();
                            } else if (violins < hallCost) {
                                e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                            } else {
                                violins -= hallCost;
                                hallLevel++;
                                hourlyIncome += 300;
                                e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + "!\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                    }
                    default ->  {
                        if(!hasOrchestra) {
                            e.getChannel().sendMessage("You can't buy nothing, that would be quite redundant.").queue();
                        }
                    }
                }
                if(hasOrchestra) {
                    switch(message[1]) {
                        case "piccolo" -> {
                            if (piccolo) {
                                e.getChannel().sendMessage("You already hired hired a piccolo player!").queue();
                            } else if (violins < 300000) {
                                e.getChannel().sendMessage("You are too poor to hire a piccolo player!").queue();
                            } else {
                                violins -= 300000;
                                piccolo = true;
                                hourlyIncome += 30;
                                e.getChannel().sendMessage("Successfully hired a piccolo player!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 30;
                                e.getChannel().sendMessage("Successfully hired a contrabassoonist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 80;
                                e.getChannel().sendMessage("Successfully hired a harpist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 60;
                                e.getChannel().sendMessage("Successfully hired a flautist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 50;
                                e.getChannel().sendMessage("Successfully hired a oboist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 40;
                                e.getChannel().sendMessage("Successfully hired a clarinetist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 40;
                                e.getChannel().sendMessage("Successfully hired a bassoonist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 40;
                                e.getChannel().sendMessage("Successfully hired a hornist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 30;
                                e.getChannel().sendMessage("Successfully hired a trumpeter!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 20;
                                e.getChannel().sendMessage("Successfully hired a trombonist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 20;
                                e.getChannel().sendMessage("Successfully hired a tubist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 60;
                                e.getChannel().sendMessage("Successfully hired a timpanist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 10;
                                e.getChannel().sendMessage("Successfully hired a percussionist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 70;
                                e.getChannel().sendMessage("Successfully hired a first violinist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 60;
                                e.getChannel().sendMessage("Successfully hired a second violinist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 50;
                                e.getChannel().sendMessage("Successfully hired a cellist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 50;
                                e.getChannel().sendMessage("Successfully hired a double bassist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 110;
                                e.getChannel().sendMessage("Successfully hired a pianists!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 30;
                                e.getChannel().sendMessage("Successfully hired a sopranist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 20;
                                e.getChannel().sendMessage("Successfully hired an alto!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 20;
                                e.getChannel().sendMessage("Successfully hired a tenor!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 20;
                                e.getChannel().sendMessage("Successfully hired a bassist!\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        case "soloist" -> {
                            if (soloists == 4) {
                                e.getChannel().sendMessage("You already hired the max amount of soloists!").queue();
                            } else if (violins < soloistCost) {
                                e.getChannel().sendMessage("You are too poor to hire a soloist!").queue();
                            } else {
                                violins -= soloistCost;
                                soloists++;
                                hourlyIncome += 20;
                                e.getChannel().sendMessage("Successfully hired a soloist!\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 200;
                                e.getChannel().sendMessage("Successfully upgraded Conductor Musicality to Level " + conductor + "\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 100;
                                e.getChannel().sendMessage("Successfully upgraded Advertising to Level " + advertising + "\nYou have " + violins + ":violin: left.").queue();
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
                                hourlyIncome += 1000;
                                e.getChannel().sendMessage("Successfully upgraded Ticket Cost to Level " + tickets + "\nYou have " + violins + ":violin: left.").queue();
                            }
                        }
                        default -> e.getChannel().sendMessage("You can't buy nothing, that would be quite redundant.").queue();
                    }
                }
            }
            case "orchestra", "o" -> {
                if(!hasData) {
                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                    throw new IllegalArgumentException();
                }
                if(hasOrchestra) {
                    int temp1 = 0;
                    int temp2 = 0;
                    int temp3 = 0;
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
                            .addField("**Woodwinds**", temp1 + " Piccolo\n" + flute + " Flutes\n" + oboe + " Oboes\n" + clarinet + " Clarinets\n" + bassoon + " Bassoons\n" + temp2 + " Contrabassoon", false)
                            .addField("**Brass**", trumpet + " Trumpets\n" + horn + " French Horns\n" + trombone + " Trombones\n" + tuba + " Tubas", false)
                            .addField("**Strings**", first + " Violin I\n" + second + " Violin II\n" + cello + " Celli\n" + stringBass + " Double Basses\n" + piano + " Pianists", false)
                            .addField("**Choir**", soprano + " Sopranos\n" + alto + " Altos\n" + tenor + " Tenors\n" + bass + " Basses\n" + soloists + " Vocal Soloists", false)
                            .addField("**Other**", temp3 + " Harp\n" + percussion + " Percussionists\n" + timpani + " Timpanists",false)
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
                        if(hourlyIncome == 150000) {
                            e.getChannel().sendMessage("Woah there, take a chill pill!  Your hourly income is already WAY above the hard maximum of 40 000!").queue();
                        } else if(rice == 0) {
                            e.getChannel().sendMessage("You look for that last rice piece you had but can't find it.  Then you remember that you don't have any left.").queue();
                        } else if(hourlyIncome == 0) {
                            e.getChannel().sendMessage("The God of Rice prevents you from wasting rice on an hourly income of 0.  Next time remember that multiplying anything by 0 is still 0").queue();
                        } else {
                            rice --;
                            hourlyIncome *= 2;
                            if(hourlyIncome > 150000) {
                                hourlyIncome = 150000;
                                e.getChannel().sendMessage("You consume a piece of rice.  It turns out to be Blessed Rice but the God of Rice has capped your income to 150 000.").queue();
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
                            int base = calculateAmount(e, data, (int) ((random.nextInt(100) + 350) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1))) * 2;
                            violins += base;
                            e.getChannel().sendMessage("You drank some Bubble Tea and wound up with an extra " + base + ":violin:").queue();
                        }
                    }
                    case "blessing" -> {
                        if(blessing == 0) {
                            e.getChannel().sendMessage("You already used all your blessings, run more commands to get back into Ling Ling's good graces!").queue();
                        } else {
                            blessing --;
                            int base = calculateAmount(e, data, (int) ((random.nextInt(2000) + 4000) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
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
                    e.getChannel().sendMessage("Chill, you already practised this hour!  Wait " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                } else {
                    int base = calculateAmount(e, data, (int) ((random.nextInt(100) + 350) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                    double num = random.nextDouble();
                    if(num > 0.75) {
                        violins += base;
                        e.getChannel().sendMessage("You practised for 40 minutes and earned " + base + ":violin:").queue();
                    } else if(num > 0.5) {
                        num = random.nextDouble();
                        if(num > 0.05) {
                            rice ++;
                            e.getChannel().sendMessage("You found 1:rice: while you were Practising but didn't get any violins.").queue();
                        } else if(num > 0.005) {
                            tea ++;
                            e.getChannel().sendMessage("You found 1:bubble_tea: after you Practised.").queue();
                        } else {
                            blessing ++;
                            e.getChannel().sendMessage("Ling Ling enjoyed your Practise session and blessed you.").queue();
                        }
                    } else if(num > 0.25) {
                        num = random.nextDouble();
                        if(num > 0.6) {
                            e.getChannel().sendMessage("Your teacher your performance.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.1;
                            violins += base;
                        } else if(num > 0.25) {
                            e.getChannel().sendMessage("Your tiger mom approved your performance.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.5;
                            violins += base;
                        } else if(num > 0.1) {
                            base *= 2;
                            e.getChannel().sendMessage("Ling Ling approved of your performance and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                        } else {
                            base *= 5;
                            e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
                        }
                    } else {
                        num = random.nextDouble();
                        if(num > 0.75) {
                            violins -= hourlyIncome / 100;
                            e.getChannel().sendMessage("Oh no!  Your E String snapped while you were practising!  You had to go to the store to get it replaced, and were not able to get any practising done.  You earned 0:violin: and had to pay " + (int) (hourlyIncome / 100) + ":violin: for a new E String.").queue();
                        } else if(num > 0.55) {
                            base *= 0.9;
                            violins += base;
                            e.getChannel().sendMessage("Your violin randomly went out of tune while you were practising.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                        } else if(num > 0.4) {
                            base *= 0.95;
                            e.getChannel().sendMessage("You had problems with your music stand, and page turning wasn't the best this session.  You earned " + base + ":violin:").queue();
                        } else if(num > 0.25) {
                            base *= 0.5;
                            violins += base;
                            violins -= hourlyIncome / 10;
                            e.getChannel().sendMessage("You hurt your wrist while practising and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + (int) (hourlyIncome / 10) + ":violin: in hospital fees.").queue();
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
                        int base = calculateAmount(e, data, (int) ((random.nextInt(1000) + 1500) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                        double num = random.nextDouble();
                        if(num > 0.75) {
                            violins += base;
                            e.getChannel().sendMessage("You rehearsed with your orchestra and earned " + base + ":violin:").queue();
                        } else if(num > 0.5) {
                            num = random.nextDouble();
                            if(num > 0.05) {
                                rice += 3;
                                e.getChannel().sendMessage("You found 3:rice: after rehearsal but didn't get any violins.").queue();
                            } else if(num > 0.005) {
                                tea += 2;
                                e.getChannel().sendMessage("You found 2:bubble_tea: after you Practised.").queue();
                            } else {
                                blessing ++;
                                e.getChannel().sendMessage("Ling Ling enjoyed your Practise session and blessed you.").queue();
                            }
                        } else if(num > 0.25) {
                            num = random.nextDouble();
                            if(num > 0.6) {
                                e.getChannel().sendMessage("Your teacher your performance.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                                base *= 1.1;
                                violins += base;
                            } else if(num > 0.25) {
                                e.getChannel().sendMessage("Your tiger mom approved your performance.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                                base *= 1.5;
                                violins += base;
                            } else if(num > 0.1) {
                                base *= 2;
                                e.getChannel().sendMessage("Ling Ling approved of your performance and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                            } else {
                                base *= 5;
                                e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
                            }
                        } else {
                            num = random.nextDouble();
                            if(num > 0.75) {
                                violins -= hourlyIncome / 100;
                                base *= 0.95;
                                violins += base;
                                e.getChannel().sendMessage("Oh no!  Your E String snapped during the rehearsal!  You had to borrow the concertmaster's violin, and only earned " + base + ":violin:  You eventually had to pay " + (int) (hourlyIncome / 100) + ":violin: for a new E String.").queue();
                            } else if(num > 0.55) {
                                base *= 0.9;
                                violins += base;
                                e.getChannel().sendMessage("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                            } else if(num > 0.4) {
                                base *= 0.95;
                                e.getChannel().sendMessage("The orchestra had music stand problems, and page turning wasn't the best either.  You only earned " + base + ":violin:").queue();
                            } else if(num > 0.25) {
                                base *= hourlyIncome;
                                violins += base;
                                violins -= hourlyIncome / 10;
                                e.getChannel().sendMessage("You hurt your wrist during the rehearsal and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + (int) (hourlyIncome / 10) + ":violin: in hospital fees.").queue();
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
                    int base = calculateAmount(e, data, (int) ((random.nextInt(2000) + 4000) * Math.pow(1.05, workL) * ((0.2 * hallLevel) + 1)));
                    double num = random.nextDouble();
                    if(num > 0.8) {
                        violins += base;
                        e.getChannel().sendMessage("You performed your solo and earned " + base + ":violin:").queue();
                    } else if(num > 0.55) {
                        num = random.nextDouble();
                        if(num > 0.05) {
                            rice += 9;
                            e.getChannel().sendMessage("Your paycheck contained 9:rice: instead of violins.").queue();
                        } else if(num > 0.005) {
                            tea += 4;
                            e.getChannel().sendMessage("You found 4:bubble_tea: after you Practised.").queue();
                        } else {
                            blessing ++;
                            e.getChannel().sendMessage("Ling Ling enjoyed your Practise session and blessed you.").queue();
                        }
                    } else if(num > 0.3) {
                        num = random.nextDouble();
                        if(num > 0.6) {
                            e.getChannel().sendMessage("Your teacher your performance.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.1;
                            violins += base;
                        } else if(num > 0.25) {
                            e.getChannel().sendMessage("Your tiger mom approved your performance.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                            base *= 1.5;
                            violins += base;
                        } else if(num > 0.1) {
                            base *= 2;
                            e.getChannel().sendMessage("Ling Ling approved of your performance and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                        } else if(num > 0.02) {
                            base *= 5;
                            e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
                        } else {
                            base *= 25;
                            e.getChannel().sendMessage("The Berlin Philharmonic featured your performance on their homepage and you earned " + base + ":violin: from the ads and royalty payments").queue();
                        }
                    } else if(num > 0.05) {
                        num = random.nextDouble();
                        if(num > 0.8) {
                            violins -= hourlyIncome / 100;
                            base *= 0.5;
                            violins += base;
                            e.getChannel().sendMessage("Oh no!  Your E String snapped during the performance!  You couldn't go on, and only earned " + base + ":violin:  You eventually had to pay " + (int) (hourlyIncome / 100) + ":violin: for a new E String.").queue();
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
                            e.getChannel().sendMessage("You hurt your wrist during the performance and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + (int) (hourlyIncome / 10) + ":violin: in hospital fees.").queue();
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
                        int base = 4000 + (streak * 40);
                        violins += base;
                        dailyC = time + 85500000;
                        dailyExp = time + 172800000;
                        streak++;
                        e.getChannel().sendMessage("You received a total of " + base + ":violin:, with " + (streak - 1) * 40 + ":violin: coming from your " + streak + "-day streak!").queue();
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
                    int insurance = Integer.parseInt(targetdata[11]);
                    boolean opponentShield = Boolean.parseBoolean(targetdata[60]);
                    long baseRob = (long) (targetViolins * 0.2);
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
                    bet = Integer.parseInt(message[2]);
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
                        gambleC = time + 59000;
                        double multi = 0.005 * gambleL;
                        if(higherWinrate) {
                            multi += 0.05;
                        }
                        switch (message[1]) {
                            case "rng" -> {
                                double chance = random.nextDouble();
                                if (chance > 0.5) {
                                    violins -= bet;
                                    e.getChannel().sendMessage("You lost " + bet + ":violin:\n*The generator rolled " + chance + ", you need less than 0.5 to win.*\nYou now have " + violins + ":violin:").queue();
                                } else if (chance <= 0.5) {
                                    violins += bet * (1 + multi);
                                    e.getChannel().sendMessage("You won " + bet + ":violin:\nYour " + multi + "% multiplier earned you an extra " + (int) (bet * multi) + ":violin:\n*The generator rolled " + chance + ".*\nYou now have " + violins + ":violin:").queue();
                                }
                            }
                            case "slots" -> {
                                int[] slots = {random.nextInt(6), random.nextInt(6), random.nextInt(6)};
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
                                    payout *= 2;
                                } else {
                                    payout = -1;
                                }
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling Bot", e.getJDA().getSelfUser().getAvatarUrl())
                                        .setTitle("__**Slots for " + e.getAuthor().getName() + "**__");
                                if(payout != -1) {
                                    builder.addField(":arrow_right: " + emojis[0] + " " + emojis[1] + " " + emojis[2] + " :arrow_left:\n_ _", ":white_check_mark: You **win**!  Payout: " + payout + ":violin:\nYour " + multi * 100 + "% multiplier earned you an extra " + (int) (payout * multi) + ":violin:", false);
                                    violins += payout * (1 + multi);
                                } else {
                                    builder.addField(":arrow_right: " + emojis[0] + " " + emojis[1] + " " + emojis[2] + " :arrow_left:\n_ _", ":x: You **lose**!  You lost " + bet + ":violin:", false);
                                    violins -= bet;
                                }
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                            case "scratch" -> {
                                if (bet % 100 != 0) {
                                    e.getChannel().sendMessage("You must bet a multiple of 100 because scratch tickets cost 100:violin: each.").queue();
                                } else {
                                    int numTickets = (int) bet / 100;
                                    int payout = 0;
                                    for (int i = 0; i < numTickets; i++) {
                                        double chance = random.nextDouble();
                                        if (chance > 0.4) {
                                            payout -= 4;
                                        } else if (chance <= 0.2 && chance > 0.1) {
                                            payout += 2;
                                        } else if (chance > 0.05) {
                                            payout += 5;
                                        } else if (chance > 0.02) {
                                            payout += 10;
                                        } else if (chance > 0.01) {
                                            payout += 25;
                                        } else if (chance > 0.005) {
                                            payout += 50;
                                        } else if (chance > 0.002) {
                                            payout += 100;
                                        } else if (chance > 0.001) {
                                            payout += 200;
                                        } else if (chance > 0.00001) {
                                            payout += 500;
                                        } else {
                                            payout += 1000000;
                                            e.getChannel().sendMessage("You hit the 1 000 000:violin: jackpot!").queue();
                                        }
                                    }
                                    if(payout > 0) {
                                        violins += payout * (1 + multi);
                                        e.getChannel().sendMessage("You scratched " + numTickets + " tickets and gained " + payout + ":violin:\nYour " + multi * 100 + "% multiplier earned you an extra " + (int) (payout * multi) + ":violin:").queue();
                                    } else {
                                        violins += payout;
                                        e.getChannel().sendMessage("You scratched " + numTickets + " tickets and lost " + payout + ":violin:").queue();
                                    }
                                }
                            }
                        }
                    } catch(Exception exception) {
                        e.getChannel().sendMessage("You must choose one of the three gambling options: `rng`, `scratch`, or `slots`").queue();
                    }
                }
            }
            case "balance", "bal", "b" -> {
                if (message.length == 1) {
                    if (!hasData) {
                        e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                        throw new IllegalArgumentException();
                    }
                    e.getChannel().sendMessage("You have " + Integer.parseInt(data[0]) + ":violin:\nHourly income: " + hourlyIncome + "\nLing Ling Medals: " + medals + ":military_medal:").queue();
                } else {
                    String name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                        String line = reader.readLine();
                        e.getChannel().sendMessage(name + " has " + line.split(" ")[0] + ":violin:\nHourly income: " + line.split(" ")[12] + "\nLing Ling Medals: " + line.split(" ")[55] + ":military_medal:").queue();
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This save file does not exist!").queue();
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
                            .addField("Rice :rice:", "Count: " + rice + "\nUsage: Doubles your hourly income for one hour.\nCaps at 150 000:violin:/hour\nID: `rice`", false)
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
            case "profile" -> {
                if(message.length == 1) {
                    if (!hasData) {
                        e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                        throw new IllegalArgumentException();
                    }
                    EmbedBuilder builder = new EmbedBuilder()
                            .setColor(Color.BLUE)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .setTitle(e.getAuthor().getName() + "'s Profile")
                            .addField("General Stats", "Balance: " + violins + ":violin:\nLing Ling Medals: " + medals + ":military_medal:\nHourly Income: " + hourlyIncome + ":violin:/hour", false)
                            .addField("Medals", ":first_place:" + firstP + "\n:second_place:" + secondP + "\n:third_place:" + thirdP, false);
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
                                .addField("General Stats", "Balance: " + line.split(" ")[0] + ":violin:\nLing Ling Medals: " + line.split(" ")[55] + ":military_medal:\nHourly Income: " + line.split(" ")[12] + ":violin:/hour", false)
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
                    leaderboard(":violin:", e, 0, message[1].equals("global"));
                } catch(Exception exception) {
                    leaderboard(":violin:", e, 0, false);
                }
            }
            case "streakleaderboard", "streaklb" -> {
                try {
                    leaderboard(":clock2:", e, 47, message[1].equals("global"));
                } catch(Exception exception) {
                    leaderboard(":clock2:", e, 47, false);
                }
            }
            case "medalleaderboard", "medallb" -> {
                try {
                    leaderboard(":military_medal:", e, 55, message[1].equals("global"));
                } catch(Exception exception) {
                    leaderboard(":military_medal:", e, 55, false);
                }
            }
            case "incomeleaderboard", "incomelb" -> {
                try {
                    leaderboard(":violin:/hour", e, 12, message[1].equals("global"));
                } catch(Exception exception) {
                    leaderboard(":violin:/hour", e, 12, false);
                }
            }
        }
        if (hasData) {
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                writer.print(violins + " " + workC + " " + workL + " " + gambleC + " " + gambleL + " " + robC + " " + robL + " " + rehearseC + " " + performC + " " + ownInsurance1 + " " + ownInsurance2 + " " + activeInsurance + " " + hourlyIncome + " " + violinQuality + " " + skillLevel + " " + lessonQuality + " " + stringQuality + " " + bowQuality + " " + hasMath + " " + hasOrchestra + " " + piccolo + " " + flute + " " + oboe + " " + clarinet + " " + bassoon + " " + contrabassoon + " " + horn + " " + trumpet + " " + trombone + " " + tuba + " " + timpani + " " + percussion + " " + first + " " + second + " " + cello + " " + stringBass + " " + piano + " " + harp + " " + soprano + " " + alto + " " + tenor + " " + bass + " " + soloists + " " + hallLevel + " " + conductor + " " + advertising + " " + tickets + " " + streak + " " + dailyC + " " + dailyExp + " " + faster + " " + rice + " " + thirdP + " " + secondP + " " + firstP + " " + medals + " " + extraIncome + " " + extraCommandIncome + " " + higherWinrate + " " + higherRobrate + " " + stealShield + " " + violinDuplicator + " " + tea + " " + blessing);
                writer.close();
            } catch (Exception exception) {
                //nothing here lol
            }
        }
    }
}