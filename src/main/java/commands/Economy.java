package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Economy extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if(!e.getAuthor().isBot()) {
            String[] message = e.getMessage().getContentRaw().split(" ");
            boolean isDev = false;
            try {
                if (e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612")) {
                    isDev = true;
                }
            } catch (Exception exception) {
                //nothing here lol
            }
            /*DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                    .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjczMzQwOTI0MzIyMjUwNzY3MCIsImJvdCI6dHJ1ZSwiaWF0IjoxNjA0NTE2ODUxfQ.O6z9PioR1_v527udPy-FHfkiuLMAJ7fvSdAA0DXTqk8")
                    .botId("733409243222507670")
                    .build();*/
            String server = "";
            try {
                server = e.getGuild().getId();
            } catch (Exception exception) {
                //nothing here lol
            }
            Random random = new Random();
            long violins = 0;
            long workC = 0;
            long workL = 0;
            long gambleC = 0;
            long gambleL = 0;
            long robC = 0;
            long robL = 0;
            long rehearseC = 0;
            long performC = 0;
            boolean ownInsurance1 = false;
            boolean ownInsurance2 = false;
            int activeInsurance = 0;
            long hourlyIncome = 0;
            long violinQuality = 0;
            long skillLevel = 0;
            long lessonQuality = 0;
            long stringQuality = 0;
            long bowQuality = 0;
            boolean hasMath = false;
            long time = System.currentTimeMillis();
            boolean hasData = false;
            String[] dataArray = new String[12];
            char prefix = '!';
            BufferedReader prefixes;
            PrintWriter readPrefix = null;
            try {
                prefixes = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt"));
                prefix = (char) prefixes.read();
                prefixes.close();
            } catch (Exception exception) {
                File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt");
                try {
                    file.createNewFile();
                    readPrefix = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt")));
                } catch (Exception exception1) {
                    //nothing here lol
                }
                assert readPrefix != null;
                readPrefix.print('!');
                readPrefix.close();
            }
            if (isDev) {
                prefix = '!';
            }
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt"));
                hasData = true;
                dataArray = br.readLine().split(" ");
                violins = Long.parseLong(dataArray[0]);
                workC = Long.parseLong(dataArray[1]);
                workL = Long.parseLong(dataArray[2]);
                gambleC = Long.parseLong(dataArray[3]);
                gambleL = Long.parseLong(dataArray[4]);
                robC = Long.parseLong(dataArray[5]);
                robL = Long.parseLong(dataArray[6]);
                rehearseC = Long.parseLong(dataArray[7]);
                performC = Long.parseLong(dataArray[8]);
                ownInsurance1 = Boolean.parseBoolean(dataArray[9]);
                ownInsurance2 = Boolean.parseBoolean(dataArray[10]);
                activeInsurance = Integer.parseInt(dataArray[11]);
                hourlyIncome = Long.parseLong(dataArray[12]);
                violinQuality = Long.parseLong(dataArray[13]);
                skillLevel = Long.parseLong(dataArray[14]);
                lessonQuality = Long.parseLong(dataArray[15]);
                stringQuality = Long.parseLong(dataArray[16]);
                bowQuality = Long.parseLong(dataArray[17]);
                hasMath = Boolean.parseBoolean(dataArray[18]);
                br.close();
            } catch (IOException ioException) {
                hasData = false;
            }
            if (message[0].equals(prefix + "start") && !hasData) {
                File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt");
                try {
                    file.createNewFile();
                } catch (Exception exception) {
                    e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
                }
                PrintWriter newData = null;
                try {
                    newData = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                } catch (Exception exception) {
                    e.getChannel().sendMessage("Something went wrong creating the file.").queue();
                }
                assert newData != null;
                newData.print("0 " + time + " 0 " + time + " 0 " + time + " 0 " + time + " " + time + " false false 0 0 0 0 0 0 0 false");
                newData.close();
                e.getChannel().sendMessage("Your profile has been created!  Run `!help 4` for a list of economy commands!").queue();
                throw new IllegalArgumentException();
            } else if (message[0].equals(prefix + "start") && hasData) {
                e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
            }
            boolean serverVote = false;
            try {
                if (e.getGuild().getId().equals("670725611207262219") && !e.getAuthor().isBot()) {
                    List<Role> roles = Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).getRoles();
                    for (Role role : roles) {
                        if (role.getId().equals("755910677075460206")) {
                            serverVote = true;
                            break;
                        }
                    }
                }
            } catch (Exception exception) {
                //nothing here lol
            }
            if (e.getMessage().getContentRaw().equals("!d bump") && e.getGuild().getId().equals("670725611207262219") && !e.getAuthor().isBot()) {
                String bump;
                BufferedReader bumpReader;
                try {
                    bumpReader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Bump.txt"));
                    bump = bumpReader.readLine();
                    bumpReader.close();
                } catch (Exception exception) {
                    throw new IllegalArgumentException();
                }
                if (time > Long.parseLong(bump)) {
                    violins *= 1.05;
                    e.getChannel().sendMessage("Thank you for bumping!  You have received a 5% interest boost!").queue();
                    PrintWriter bumpWriter;
                    try {
                        bumpWriter = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Bump.txt")));
                    } catch (Exception exception) {
                        throw new IllegalArgumentException();
                    }
                    bumpWriter.print((time + 7200000));
                    bumpWriter.close();
                } else {
                    e.getChannel().sendMessage("The 120 minute timer hasn't ended yet, please refer to Disboard's message for more info.").queue();
                }
            }
            try {
                if (message[0].charAt(0) == prefix) {
                    long workCost = (long) (Math.pow(100 * (workL + 1), 1 + 0.014 * (workL)));
                    long gambleCost = (long) (Math.pow(100 * (gambleL + 1), 1 + 0.03 * (gambleL)));
                    long robCost = (long) (Math.pow(100 * (robL + 1), 1 + 0.06 * (robL)));
                    long violinCost = (long) (Math.pow(10, violinQuality)) * 1000;
                    long skillCost = (long) (Math.pow(3, skillLevel)) * 500;
                    long lessonCost = (long) (Math.pow(4, lessonQuality)) * 700;
                    long stringCost = (long) (Math.pow(2, stringQuality)) * 400;
                    long bowCost = (long) (Math.pow(5, bowQuality)) * 500;
                    message[0] = message[0].substring(1);
                    switch (message[0]) {
                        case "upgrades", "up", "u", "shop" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            try {
                                switch (message[1]) {
                                    case "2" -> {
                                        EmbedBuilder builder = new EmbedBuilder()
                                                .setColor(Color.BLUE)
                                                .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                .addField("Efficient Practising (" + workL + "/75)", "Price: " + workCost + "\nEffect: Increases your income from `!practice`, `!rehearse`, and `!perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                                                .addField("Lucky Musician (" + gambleL + "/40)", "Price: " + gambleCost + "\nEffect: Increases your chance of winning at `!bet` by 1%.\nID: `lucky`, `lm`", false)
                                                .addField("Sophisticated Robbing (" + robL + "/25)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `!rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
                                                .setTitle("__**Miscellaneous Upgrades**__");
                                        if (ownInsurance1) {
                                            builder.addField("Ling Ling Insurance - Plan 1 - Full Security (1/1)", "Price: 1000000\nEffect: When someone uses `!rob` on you, will protect all your violins from being stolen but you will pay 15% of what would have been stolen as insurance cost.\nID: `1`", false);
                                        } else {
                                            builder.addField("Ling Ling Insurance - Plan 1 - Full Security (0/1)", "Price: 1000000\nEffect: When someone uses `!rob` on you, will protect all your violins from being stolen but you will pay 15% of what would have been stolen as insurance cost.\nID: `1`", false);
                                        }
                                        if (ownInsurance2) {
                                            builder.addField("Ling Ling Insurance - Plan 2 - Half Security (1/1)", "Price: 1000000\nEffect: When someone uses `!rob` on you, will protect only 67% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                        } else {
                                            builder.addField("Ling Ling Insurance - Plan 2 - Half Security (0/1)", "Price: 1000000\nEffect: When someone uses `!rob` on you, will protect only 67% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                        }
                                        e.getChannel().sendMessage(builder.build()).queue();
                                    }
                                    case "1" -> {
                                        EmbedBuilder builder = new EmbedBuilder()
                                                .setColor(Color.BLUE)
                                                .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                .addField("Violin Quality (" + violinQuality + "/5)", "Price: " + violinCost + "\nEffect: +600 violins/hour\nID: `violin`, `v`", false)
                                                .addField("Skill Level (" + skillLevel + "/10)", "Price: " + skillCost + "\nEffect: +240 violins/hour\nID: `skill`, `s`", false)
                                                .addField("Lesson Quality (" + lessonQuality + "/8)", "Price: " + lessonCost + "\nEffect: +150 violins/hour\nID: `lesson`, `l`", false)
                                                .addField("String Quality (" + stringQuality + "/15)", "Price: " + stringCost + "\nEffect: +100 violins/hour\nID: `string`, `str`", false)
                                                .addField("Bow Quality (" + bowQuality + "/7)", "Price: " + bowCost + "\nEffect: +200 violins/hour\nID: `bow`, `b`", false)
                                                .setTitle("__**Income Upgrades**__");
                                        if (hasMath) {
                                            builder.addField("Math Tutoring (1/1)", "Price: 5 000 000\nEffect: +2500 violins/hour\nID: `math`", false);
                                        } else {
                                            builder.addField("Math Tutoring (0/1)", "Price: 5 000 000\nEffect: +2500 violins/hour\nID: `math`", false);
                                        }
                                        e.getChannel().sendMessage(builder.build()).queue();
                                    }
                                }
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You must provide a page number!").queue();
                            }
                        }
                        case "buy" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            switch (message[1]) {
                                case "1" -> {
                                    if (ownInsurance1) {
                                        e.getChannel().sendMessage("You already bought this insurance!  Run `!use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.").queue();
                                    } else if (violins < 1000000) {
                                        e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                                    } else {
                                        ownInsurance1 = true;
                                        violins -= 1000000;
                                        e.getChannel().sendMessage("You have successfully bought Ling Ling Insurance - Plan 1 - Full Security!  Run `!use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "2" -> {
                                    if (ownInsurance2) {
                                        e.getChannel().sendMessage("You already bought this insurance!  Run `!use 2` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 1.").queue();
                                    } else if (violins < 1000000) {
                                        e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                                    } else {
                                        ownInsurance2 = true;
                                        violins -= 1000000;
                                        e.getChannel().sendMessage("You have successfully bought Ling Ling Insurance - Plan 2 - Half Security!  Run `!use 2` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 1.\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "efficient", "practising", "ep" -> {
                                    if (workL == 75) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < workCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= workCost;
                                        workL++;
                                        e.getChannel().sendMessage("Successfully upgraded Efficient Practising to Level " + workL + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "lucky", "lm" -> {
                                    if (gambleL == 40) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < gambleCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= gambleCost;
                                        gambleL++;
                                        e.getChannel().sendMessage("Successfully upgraded Lucky Musician to Level " + gambleL + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "sophisticated", "robbing", "sr" -> {
                                    if (robL == 25) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < robCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= robCost;
                                        robL++;
                                        e.getChannel().sendMessage("Successfully upgraded Sophisticated Robbing to Level " + robL + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "violin", "v" -> {
                                    if (violinQuality == 5) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < violinCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= violinCost;
                                        violinQuality++;
                                        hourlyIncome += 600;
                                        e.getChannel().sendMessage("Successfully upgraded Violin Quality to Level " + violinQuality + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "skill", "s" -> {
                                    if (skillLevel == 10) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < skillCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= skillCost;
                                        skillLevel++;
                                        hourlyIncome += 240;
                                        e.getChannel().sendMessage("Successfully upgraded Skill Level to Level " + skillLevel + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "lesson", "l" -> {
                                    if (lessonQuality == 8) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < lessonCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= lessonCost;
                                        lessonQuality++;
                                        hourlyIncome += 150;
                                        e.getChannel().sendMessage("Successfully upgraded Lesson Quality to Level " + lessonQuality + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "string", "str" -> {
                                    if (stringQuality == 15) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < stringCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= stringCost;
                                        stringQuality++;
                                        hourlyIncome += 100;
                                        e.getChannel().sendMessage("Successfully upgraded String Quality to Level " + stringQuality + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "bow", "b" -> {
                                    if (bowQuality == 7) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < bowCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= bowCost;
                                        bowQuality++;
                                        hourlyIncome += 200;
                                        e.getChannel().sendMessage("Successfully upgraded Bow Quality to Level " + bowQuality + "\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "math" -> {
                                    if (hasMath) {
                                        e.getChannel().sendMessage("You already bought this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < 5000000) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= 5000000;
                                        hasMath = true;
                                        hourlyIncome += 2500;
                                        e.getChannel().sendMessage("Successfully purchased Math Tutoring!\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                            }
                        }
                        case "cooldowns", "c" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            //gamble, rob, practise, rehearse, perform
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
                            //gamble
                            long milliseconds = gambleC - time;
                            long seconds;
                            long minutes;
                            long hours;
                            long days;
                            if (milliseconds < 0) {
                                builder.addField("**Gamble**", "This command can be used now!", false);
                            } else {
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Gamble**", seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //rob
                            milliseconds = robC - time;
                            if (milliseconds < 0) {
                                builder.addField("**Rob**", "This command can be used now!", false);
                            } else {
                                hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Rob**", hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //practice
                            milliseconds = workC - time;
                            if (milliseconds < 0) {
                                builder.addField("**Practice**", "This command can be used now!", false);
                            } else {
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Practice**", minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //rehearse
                            milliseconds = rehearseC - time;
                            if (milliseconds < 0) {
                                builder.addField("**Rehearse**", "This command can be used now!", false);
                            } else {
                                hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Rehearse**", hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //perform
                            milliseconds = performC - time;
                            if (milliseconds < 0) {
                                builder.addField("**Perform**", "This command can be used now!", false);
                            } else {
                                days = milliseconds / 86400000;
                                milliseconds -= days * 86400000;
                                hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Perform**", days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }
                            builder.setTitle("__**Cooldowns**__");
                            e.getChannel().sendMessage(builder.build()).queue();
                        }
                        case "use" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            switch (message[1]) {
                                case "1":
                                    if (ownInsurance1) {
                                        activeInsurance = 1;
                                        e.getChannel().sendMessage("You have activated Plan 1 - Full Security").queue();
                                    } else {
                                        e.getChannel().sendMessage("You don't own this insurance!  Run `!shop` to see the details.").queue();
                                    }
                                    break;
                                case "2":
                                    if (ownInsurance2) {
                                        activeInsurance = 2;
                                        e.getChannel().sendMessage("You have activated Plan 2 - Half Security").queue();
                                    } else {
                                        e.getChannel().sendMessage("You don't own this insurance!  Run `!shop` to see the details.").queue();
                                    }
                                    break;
                                case "0":
                                    activeInsurance = 0;
                                    e.getChannel().sendMessage("You have deactivated all insurance.  Not a good idea...").queue();
                                    break;
                            }
                        }
                        case "practice", "p" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
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
                                int base;
                                if (serverVote) {
                                    base = (int) ((random.nextInt(40) + 230) * Math.pow(1.05, workL) * 1.1);
                                } else {
                                    base = (int) ((random.nextInt(40) + 230) * Math.pow(1.05, workL));
                                }
                                violins += base;
                                e.getChannel().sendMessage("You practised for one hour and earned " + base + " violins.").queue();
                                workC = time + 3540000;
                            }
                        }
                        case "rehearse", "r" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            AtomicBoolean hasVotes = new AtomicBoolean(false);
                            /*api.hasVoted(e.getAuthor().getId()).whenComplete((botVote, botVote2) -> {
                                if (botVote) {
                                    hasVotes.set(true);
                                }
                            });*/
                            if (time < rehearseC) {
                                long milliseconds = rehearseC - time;
                                long hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                long minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                long seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                e.getChannel().sendMessage("You don't have the time to go to rehearsal that often, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                            } else /*if (/*Boolean.parseBoolean(hasVotes.toString()) || e.getGuild().getId().equals("670725611207262219"))*/ {
                                int base;
                                if (serverVote) {
                                    base = (int) ((random.nextInt(150) + 525) * Math.pow(1.05, workL) * 1.1);
                                } else {
                                    base = (int) ((random.nextInt(150) + 525) * Math.pow(1.05, workL));
                                }
                                violins += base;
                                e.getChannel().sendMessage("You went to rehearse with the Berlin Philharmonic and earned " + base + " violins.").queue();
                                rehearseC = time + 43140000;
                            /*} else {
                                e.getChannel().sendMessage("You need to either use this command in discord.gg/gNfPwa8 ~~or vote for the bot in order to use this command!~~ <-- Voting currently does not offer rewards, however, you can help the bot grow by voting at https://top.gg/bot/733409243222507670/vote").queue();
                            */}
                        }
                        case "perform" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
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
                            } else if (e.getGuild().getId().equals("670725611207262219")) {
                                int base;
                                if (serverVote) {
                                    base = (int) ((random.nextInt(3000) + 13500) * Math.pow(1.05, workL) * 1.1);
                                } else {
                                    base = (int) ((random.nextInt(3000) + 13500) * Math.pow(1.05, workL));
                                }
                                violins += base;
                                e.getChannel().sendMessage("You performed your solo with the Berlin Philharmonic and earned " + base + " violins.").queue();
                                performC = time + 604740000;
                            } else {
                                int base = (int) ((random.nextInt(1500) + 6250) * Math.pow(1.05, workL));
                                violins += base;
                                e.getChannel().sendMessage("You performed your solo and earned " + base + " violins.").queue();
                                performC = time + 604740000;
                            }
                        }
                        case "rob" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
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
                                String target = "";
                                try {
                                    target = e.getMessage().getMentionedUsers().get(0).getId();
                                } catch (Exception exception) {
                                    try {
                                        target = message[1];
                                    } catch(Exception exception1) {
                                        if(random.nextDouble() < 0.1) {
                                            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F3BB").queue();
                                        }
                                    }
                                }
                                if (e.getAuthor().getId().equals(target)) {
                                    e.getChannel().sendMessage("Why would you rob yourself, are you actually that dumb?").queue();
                                    throw new IllegalArgumentException();
                                }
                                String[] targetDataArray = new String[12];
                                try {
                                    br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                                    targetDataArray = br.readLine().split(" ");
                                    br.close();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You did not mention a user or provide a valid User ID.  Doesn't make sense to rob a non-existant user, does it?").queue();
                                    throw new IllegalArgumentException();
                                }
                                long targetViolins;
                                targetViolins = Long.parseLong(targetDataArray[0]);
                                double failChance = (double) violins / (targetViolins + violins);
                                failChance -= 0.005 * robL;
                                double num = random.nextDouble();
                                int insurance = Integer.parseInt(targetDataArray[11]);
                                if (num < failChance) {
                                    e.getChannel().sendMessage("Brett and Eddy caught you trying to rob <@" + target + ">!  You paid <@" + target + "> " + (long) (Long.parseLong(dataArray[0]) * 0.05) + " violins for attempting to rob them.\n*The generator rolled " + num + ", you need at least " + failChance + " to succeed.*").queue();
                                    targetViolins += Long.parseLong(dataArray[0]) * 0.05;
                                    violins -= Long.parseLong(dataArray[0]) * 0.05;
                                    User send = e.getJDA().getUserById(target);
                                    assert send != null;
                                    send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> tried to rob you but failed!  They paid you " + (long) (Long.parseLong(targetDataArray[0]) * 0.05) + " violins in fines.").queue();
                                } else {
                                    if (insurance == 1) {
                                        e.getChannel().sendMessage("You try to rob <@" + target + "> only to notice that Ling Ling Security is present.  You try to escape but you are caught and kicked from the estate.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                        targetViolins -= Long.parseLong(targetDataArray[0]) * 0.03;
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> tried to rob you!  Luckily, insurance protected you and you only paid " + (long) (Long.parseLong(targetDataArray[0]) * 0.03) + " violins in insurance costs.").queue();
                                    } else if (insurance == 2) {
                                        e.getChannel().sendMessage("You successfully robbed <@" + target + "> but only managed to get away with " + (long) (Long.parseLong(targetDataArray[0]) * 0.067) + " violins before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                        targetViolins -= Long.parseLong(targetDataArray[0]) * 0.067;
                                        violins += Long.parseLong(targetDataArray[0]) * 0.067;
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> just robbed " + (long) (Long.parseLong(targetDataArray[0]) * 0.067) + " violins from you!  Your Ling Ling insurance protected " + (long) (Long.parseLong(targetDataArray[0]) * 0.133) + " violins.").queue();
                                    } else {
                                        e.getChannel().sendMessage("You successfully robbed <@" + target + ">!  Your payout was " + (long) (Long.parseLong(targetDataArray[0]) * 0.2) + " violins!\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                        targetViolins -= Long.parseLong(targetDataArray[0]) * 0.2;
                                        violins += Long.parseLong(targetDataArray[0]) * 0.2;
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> just robbed " + (long) (Long.parseLong(targetDataArray[0]) * 0.2) + " violins from you!").queue();
                                    }
                                }
                                robC = time + 86340000;
                                PrintWriter afterRob = null;
                                try {
                                    afterRob = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt")));
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                                assert afterRob != null;
                                afterRob.print(targetViolins + " " + targetDataArray[1] + " " + targetDataArray[2] + " " + targetDataArray[3] + " " + targetDataArray[4] + " " + targetDataArray[5] + " " + targetDataArray[6] + " " + targetDataArray[7] + " " + targetDataArray[8] + " " + targetDataArray[9] + " " + targetDataArray[10] + " " + targetDataArray[11] + " " + targetDataArray[12] + " " + targetDataArray[13] + " " + targetDataArray[14] + " " + targetDataArray[15] + " " + targetDataArray[16] + " " + targetDataArray[17] + " " + targetDataArray[18]);
                                afterRob.close();
                            }
                        }
                        case "gamble", "bet" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            long bet = 0;
                            try {
                                bet = Integer.parseInt(message[1]);
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You must bet something, how smol brane are you???").queue();
                                throw new IllegalArgumentException();
                            }
                            if (time < gambleC) {
                                long milliseconds = gambleC - time;
                                long seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                e.getChannel().sendMessage("Don't bet your violins like Paganini, wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                                throw new IllegalArgumentException();
                            } else if (bet > violins) {
                                e.getChannel().sendMessage("You can't bet more than you have, don't try to outsmart me.").queue();
                                throw new IllegalArgumentException();
                            } else if (bet < 0) {
                                e.getChannel().sendMessage("You can't bet a negative number, don't try breaking me.").queue();
                                throw new IllegalArgumentException();
                            } else if (bet < 40) {
                                e.getChannel().sendMessage("If you're going to bet less than forty violins, go away and stop wasting my time.").queue();
                                throw new IllegalArgumentException();
                            } else {
                                double chance = random.nextDouble();
                                gambleC = time + 20000;
                                if (chance > 0.4 + 0.01 * gambleL) {
                                    violins -= bet;
                                    e.getChannel().sendMessage("You lost " + bet + " violins!\n*The generator rolled " + chance + ", you need less than " + (0.45 + 0.01 * gambleL) + " to win.*\nYou now have " + violins + " violins.").queue();
                                } else if (chance <= 0.4 + 0.01 * gambleL) {
                                    violins += bet;
                                    e.getChannel().sendMessage("You won " + bet + " violins!\n*The generator rolled " + chance + ".*\nYou now have " + violins + " violins.").queue();
                                }
                                if(violins == 69) {
                                    e.getChannel().sendMessage("I'm not going to let you waste your time trying to get the funny number, I'm taking one violin away.").queue();
                                    violins --;
                                }
                            }
                        }
                        case "balance", "bal", "b" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            if (message.length == 1) {
                                e.getChannel().sendMessage("You have " + Integer.parseInt(dataArray[0]) + " violins.\nHourly income: " + hourlyIncome).queue();
                            } else {
                                String target;
                                try {
                                    target = e.getMessage().getMentionedUsers().get(0).getId();
                                } catch (Exception exception) {
                                    target = message[1];
                                }
                                String name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                                BufferedReader otherData;
                                try {
                                    otherData = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                                    String line = otherData.readLine();
                                    e.getChannel().sendMessage(name + " has " + line.split(" ")[0] + " violins.\nHourly income: " + line.split(" ")[12]).queue();
                                    otherData.close();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("This user profile doesn't exist!").queue();
                                }
                            }
                        }
                        case "leaderboard", "lb" -> {
                            e.getChannel().sendMessage("This command is currently broken.").queue();
                            /*File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                            File[] files = directory.listFiles();
                            String[] entry = {"null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n"};
                            if (files != null) {
                                for (File file : files) {
                                    String currentData = null;
                                    String user;
                                    int pos;
                                    try {
                                        br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                        currentData = br.readLine();
                                        br.close();
                                    } catch (Exception exception) {
                                        //nothing here lol
                                        //entry format: **Position.** user#9999 `userID`: num violins
                                    }
                                    user = file.getName();
                                    pos = user.lastIndexOf(".");
                                    user = user.substring(0, pos);
                                    assert currentData != null;
                                    String[] temp = currentData.split(" ");
                                    int numViolins = Integer.parseInt(temp[0]);
                                    if(numViolins == 0) {
                                        continue;
                                    }
                                    for (int i = 0; i < 10; i++) {
                                        if (numViolins > Integer.parseInt(entry[i].split(" ")[2])) {
                                            System.arraycopy(entry, i, entry, i + 1, 9 - i);
                                            String name = Objects.requireNonNull(e.getJDA().getUserById(user)).getName().replace(' ', '-');
                                            entry[i] = name + "#" + Objects.requireNonNull(e.getJDA().getUserById(user)).getDiscriminator() + " `" + user + "`: " + numViolins + " violins.\n";
                                            break;
                                        }
                                    }
                                }
                            }
                            StringBuilder board = new StringBuilder();
                            for (int i = 0; i < 10; i++) {
                                board.append("**").append(i + 1).append(".** ").append(entry[i]);
                            }
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                    .addField("Richest Users in the World", board.toString(), false)
                                    .setTitle("__**Economy Leaderboard**__");
                            e.getChannel().sendMessage(builder.build()).queue();*/
                        }
                    }
                    PrintWriter pw = null;
                    try {
                        pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("You don't have a save file!  Run `!start` to create one!").queue();
                    }
                    assert pw != null;
                    pw.print(violins + " " + workC + " " + workL + " " + gambleC + " " + gambleL + " " + robC + " " + robL + " " + rehearseC + " " + performC + " " + ownInsurance1 + " " + ownInsurance2 + " " + activeInsurance + " " + hourlyIncome + " " + violinQuality + " " + skillLevel + " " + lessonQuality + " " + stringQuality + " " + bowQuality + " " + hasMath);
                    pw.close();
                }
            } catch (StringIndexOutOfBoundsException exception) {
                //nothing here lol
            }
        }
    }
}