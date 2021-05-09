package processes;

import economy.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;

public class Economy {
    public static void leaderboard(String emoji, String what, GuildMessageReceivedEvent e, int dataPosition, long userNum) {
        BufferedReader reader;
        File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        String[] entry = new String[0];
        long place = 1;
        if (files != null) {
            entry = new String[]{e.getAuthor().getAsMention() + ": " + userNum + " " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n"};
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
                long num;
                try {
                    num = Long.parseLong(temp[dataPosition]);
                } catch (Exception exception) {
                    num = (long) Double.parseDouble(temp[dataPosition]);
                }
                if (num == 0) {
                    continue;
                }
                for (int i = 0; i < 10; i++) {
                    if (num > Long.parseLong(entry[i].split(" ")[1]) && !user.equals(e.getAuthor().getId())) {
                        System.arraycopy(entry, i, entry, i + 1, 9 - i);
                        entry[i] = "<@" + user + ">: " + num + " " + emoji + "\n";
                        if (num > userNum) {
                            place++;
                        }
                        break;
                    }
                }
            }
        }
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (entry[i].contains("<@0>")) {
                break;
            }
            try {
                String id = entry[i].split(" ")[0];
                User user = e.getJDA().getUserById(id.substring(2, id.length() - 2));
                assert user != null;
                board.append("**").append(i + 1).append(". ").append(user.getName()).append("**#").append(user.getDiscriminator()).append(" `").append(user.getId()).append("`: ").append(entry[i].split(" ")[1]).append(" ").append(emoji).append("\n");
            } catch (Exception exception) {
                board.append(entry[i]);
            }
        }
        if (place >= 11) {
            board.append("\n**").append(place).append(". You** ").append(userNum).append(" ").append(emoji);
        }
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
        builder.addField("**__" + what + " in the World__**", board.toString(), false);
        e.getChannel().sendMessage(builder.build()).queue();
    }

    public Economy(GuildMessageReceivedEvent e, String[] message, char prefix) {
        String[] data = new String[0];
        boolean hasData;
        boolean isBanned = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt"));
            hasData = true;
            data = reader.readLine().split(" ");
            if(data.length == 1 && data[0].equals("BANNED")) {
                isBanned = true;
            }
            reader.close();
        } catch (Exception exception) {
            hasData = false;
            e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
        }
        if (message[0].equals("start") && !hasData && !isBanned) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt");
            try {
                file.createNewFile();
            } catch (Exception exception) {
                e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
            }
            try {
                PrintWriter newData = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                long time = System.currentTimeMillis();
                newData.print("0 " + time + " 0 " + time + " 0 " + time + " 0 " + time + " " + time + " false false 0 0 0 0 0 0 0 false false false 0 0 0 0 false 0 0 0 0 0 0 1 1 0 0 0 false 0 0 0 0 0 0 0 0 0 0 " + time + " " + (time + 86400000) + " false 0 0 0 0 0 false false false false false false 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
                newData.close();
                e.getChannel().sendMessage("Your profile has been created!  Run `" + prefix + "help 3` for a list of economy commands!").queue();
            } catch (Exception exception) {
                e.getChannel().sendMessage("Something went wrong creating the file.  Run `" + prefix + "support` for a link to the support server to get in contact with the developer.").queue();
            }
        } else if (message[0].equals("start")) {
            e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
        }
        if(isBanned) {
            e.getChannel().sendMessage("You are banned from using this bot.").queue();
        }
        switch (message[0]) {
            case "upgrades", "up", "u", "shop" -> new Upgrades(e, data, prefix);
            case "buy" -> new Buy(e, data);
            case "orchestra", "o" -> new Orchestra(e, data);
            case "cooldowns", "c" -> new Cooldowns(e, data);
            case "use" -> new Use(e, data, prefix);
            case "scales", "s" -> new Scales(e, data);
            case "practice", "p" -> new Practise(e, data);
            case "rehearse", "r" -> new Rehearse(e, data);
            case "perform", "pf" -> new Perform(e, data);
            case "daily", "d" -> new Daily(e, data);
            case "teach" -> new Teach(e, data);
            case "rob" -> new Rob(e, data);
            case "gamble", "bet" -> new Gamble(e, data);
            case "inventory", "inv" -> new Inventory(e, data);
            case "profile", "balance", "bal", "b" -> new Balance(e, data);
            case "stats" -> new Stats(e, data);
            case "leaderboard", "lb" -> {
                try {
                    switch (message[1]) {
                        case "violins" -> leaderboard(":violin:", "Richest Users", e, 0, Long.parseLong(data[0]));
                        case "streak" -> leaderboard(":calendar:", "Longest Daily Streaks", e, 47, Long.parseLong(data[47]));
                        case "medals" -> leaderboard(":military_medal:", "Most Ling Ling Worthy Users", e, 55, Long.parseLong(data[55]));
                        case "income" -> leaderboard(":violin:/hour", "Highest Hourly Incomes", e, 12, Long.parseLong(data[12]));
                        case "winnings" -> leaderboard(":moneybag:", "Best Gamblers", e, 66, Long.parseLong(data[66]));
                        case "million" -> leaderboard(":tickets:", "Luckiest Users", e, 67, Long.parseLong(data[67]));
                        case "rob" -> leaderboard(":violin:", "Most Heartless Users", e, 68, Long.parseLong(data[68]));
                        case "scales" -> leaderboard(":scales:", "Most Scales Playes", e, 70, Long.parseLong(data[70]));
                        case "hours" -> leaderboard(":clock2:", "Most Hours Practised", e, 71, (long) Double.parseDouble(data[71]));
                        case "rehearsals" -> leaderboard(":musical_score:", "Most Rehearsals Attended", e, 72, Long.parseLong(data[72]));
                        case "performances" -> leaderboard(":microphone:", "Most Performances", e, 73, Long.parseLong(data[73]));
                        case "earnings" -> leaderboard(":violin:", "Most Hardworking Users", e, 75, Long.parseLong(data[75]));
                        case "teach" -> leaderboard(":teacher:", "Most Influential Users", e, 76, Long.parseLong(data[76]));
                        case "luthier" -> leaderboard(":question:", "Best Unscramblers", e, 77, Long.parseLong(data[77]));
                        default -> e.getChannel().sendMessage("You must provide a valid leaderboard type.  Run the command with no arguments for a list of leaderboards.").queue();
                    }
                } catch (Exception exception) {
                    e.getChannel().sendMessage("**__Leaderboard Types__**\n\n`violins`: Richest Users\n`income`: Highest Hourly Incomes\n`streak`: Longest Daily Streaks\n`medals`: Users with Most Ling Ling Medals\n`winnings`: Users with Highest Net Gamble Winnings\n`million`: Users with Most Million Violin Tickets\n`rob`: Users with Highest Violins Robbed\n`scales`: Users with Most Scales Played\n`hours`: Users with Most Hours Practised\n`rehearsals`: Users with Most Rehearsals Attended\n`performances`: Users with Most Performances\n`teach`: Users with the Most Hours Taught\n`earnings`: Users who Earned the Most Violins\n`luthier`: Users with Most Luthier Unscrambles").queue();
                }
            }
        }
    }
}