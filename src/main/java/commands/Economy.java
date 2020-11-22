package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
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

            long time = System.currentTimeMillis();
            boolean hasData;
            String[] dataArray;
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
                br.close();
            } catch (IOException ioException) {
                hasData = false;
                dataArray = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "false", "0", "0", "0", "0", "0", "0", "0", "false", "false", "false", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
            }
            long violins = Long.parseLong(dataArray[0]);
            long workC = Long.parseLong(dataArray[1]);
            int workL = Integer.parseInt(dataArray[2]);
            long gambleC = Long.parseLong(dataArray[3]);
            int gambleL = Integer.parseInt(dataArray[4]);
            long robC = Long.parseLong(dataArray[5]);
            int robL = Integer.parseInt(dataArray[6]);
            long rehearseC = Long.parseLong(dataArray[7]);
            long performC = Long.parseLong(dataArray[8]);
            boolean ownInsurance1 = Boolean.parseBoolean(dataArray[9]);
            boolean ownInsurance2 = Boolean.parseBoolean(dataArray[10]);
            int activeInsurance = Integer.parseInt(dataArray[11]);
            long hourlyIncome = Long.parseLong(dataArray[12]);
            int violinQuality = Integer.parseInt(dataArray[13]);
            int skillLevel = Integer.parseInt(dataArray[14]);
            int lessonQuality = Integer.parseInt(dataArray[15]);
            int stringQuality = Integer.parseInt(dataArray[16]);
            int bowQuality = Integer.parseInt(dataArray[17]);
            boolean hasMath = Boolean.parseBoolean(dataArray[18]);
            boolean hasOrchestra = Boolean.parseBoolean(dataArray[19]);
            boolean piccolo = Boolean.parseBoolean(dataArray[20]);
            int flute = Integer.parseInt(dataArray[21]);
            int oboe = Integer.parseInt(dataArray[22]);
            int clarinet = Integer.parseInt(dataArray[23]);
            int bassoon = Integer.parseInt(dataArray[24]);
            boolean contrabassoon = Boolean.parseBoolean(dataArray[25]);
            int horn = Integer.parseInt(dataArray[26]);
            int trumpet = Integer.parseInt(dataArray[27]);
            int trombone = Integer.parseInt(dataArray[28]);
            int tuba = Integer.parseInt(dataArray[29]);
            int timpani = Integer.parseInt(dataArray[30]);
            int percussion = Integer.parseInt(dataArray[31]);
            int first = Integer.parseInt(dataArray[32]);
            int second = Integer.parseInt(dataArray[33]);
            int cello = Integer.parseInt(dataArray[34]);
            int stringBass = Integer.parseInt(dataArray[35]);
            int piano = Integer.parseInt(dataArray[36]);
            boolean harp = Boolean.parseBoolean(dataArray[37]);
            int soprano = Integer.parseInt(dataArray[38]);
            int alto = Integer.parseInt(dataArray[39]);
            int tenor = Integer.parseInt(dataArray[40]);
            int bass = Integer.parseInt(dataArray[41]);
            int soloists = Integer.parseInt(dataArray[42]);
            int hallLevel = Integer.parseInt(dataArray[43]);
            int conductor = Integer.parseInt(dataArray[44]);
            int advertising = Integer.parseInt(dataArray[45]);
            int tickets = Integer.parseInt(dataArray[46]);
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
                newData.print("0 " + time + " 0 " + time + " 0 " + time + " 0 " + time + " " + time + " false false 0 0 0 0 0 0 0 false false false 0 0 0 0 false 0 0 0 0 0 0 1 1 0 0 0 false 0 0 0 0 0 0 0 0 0 ");
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
                    long workCost = (long) (Math.pow(1.2, workL)) * 100;
                    long gambleCost = (long) (Math.pow(1.4, gambleL)) * 200;
                    long robCost = (long) (Math.pow(1.75, robL)) * 500;
                    long violinCost = (long) (Math.pow(10, violinQuality)) * 1000;
                    long skillCost = (long) (Math.pow(3, skillLevel)) * 500;
                    long lessonCost = (long) (Math.pow(4, lessonQuality)) * 700;
                    long stringCost = (long) (Math.pow(2, stringQuality)) * 400;
                    long bowCost = (long) (Math.pow(5, bowQuality)) * 500;
                    long hallCost = (long) (Math.pow(10, hallLevel)) * 10000;
                    long conductorCost = (long) (Math.pow(5, conductor)) * 100000;
                    long ticketCost = (long) (Math.pow(2, tickets)) * 2000000;
                    long fluteCost = 400000 * (flute + 1);
                    long oboeCost = 400000 * (oboe + 1);
                    long clarinetCost = 400000 * (clarinet + 1);
                    long bassoonCost = 400000 * (bassoon + 1);
                    long hornCost = 400000 * (horn + 1);
                    long trumpetCost = 300000 * (trumpet + 1);
                    long tromboneCost = 250000 * (trombone + 1);
                    long tubaCost = 250000 * (tuba + 1);
                    long timpaniCost = 400000 * (timpani + 1);
                    long percussionCost = 100000 * (percussion + 1);
                    long firstCost = 600000 * first;
                    long secondCost = 500000 * second;
                    long celloCost = 500000 * (cello + 1);
                    long doubleCost = 500000 * (stringBass + 1);
                    long pianoCost = 1000000 * (piano + 1);
                    long sopranoCost = 100000 * (soprano + 1);
                    long altoCost = 75000 * (alto + 1);
                    long tenorCost = 75000 * (tenor + 1);
                    long bassCost = 75000 * (tenor + 1);
                    long soloistCost = 400000 * (soloists + 1);
                    long advertisingCost = 100000 * (advertising + 1);
                    message[0] = message[0].substring(1);
                    switch (message[0]) {
                        case "upgrades", "up", "u", "shop" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            try {
                                if(!hasOrchestra) {
                                    switch (message[1]) {
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
                                        case "2" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("Uesr balance: " + violins + "\nUser `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Concert Hall Quality (" + hallLevel + "/2)", "Price: " + hallCost + "\nEffect: +300 violins/hour, +20% violins from `!perform`\nID: `concert`, `hall`", false)
                                                    .addField("", "Buy the orchestra to unlock more upgrades!", false)
                                                    .setTitle("__**Miscellaneous Orchestra Items**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "3" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Efficient Practising (" + workL + "/75)", "Price: " + workCost + "\nEffect: Increases your income from `!practice`, `!rehearse`, and `!perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                                                    .addField("Lucky Musician (" + gambleL + "/40)", "Price: " + gambleCost + "\nEffect: Increases your chance of winning at `!bet` by 1%.\nID: `lucky`, `lm`", false)
                                                    .addField("Sophisticated Robbing (" + robL + "/25)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `!rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
                                                    .setTitle("__**Other Miscellaneous Upgrades**__");
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
                                        default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Miscellaneous Orchestra Items\n`3` for Other Miscellaneous Upgrades").queue();
                                    }
                                } else {
                                    switch (message[1]) {
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
                                        case "2" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl());
                                            if(piccolo) {
                                                builder.addField("Piccolo (1/1)", "Price: 300 000\nEffect: +30 violins/hour\nID: `piccolo`", false);
                                            } else {
                                                builder.addField("Piccolo (0/1)", "Price: 300 000\nEffect: +30 violins/hour\nID: `piccolo`", false);
                                            }
                                            builder.addField("Flutes (" + flute + "/4)", "Price: " + fluteCost + "\nEffect: +60 violins/hour\nID: `flute`", false)
                                                    .addField("Oboes (" + oboe + "/4)", "Price: " + oboeCost + "\nEffect: +50 violins/hour\nID: `oboe`", false)
                                                    .addField("Clarinets (" + clarinet + "/4)", "Price: " + clarinetCost + "\nEffect: +40 violins/hour\nID: `clarinet`", false)
                                                    .addField("Bassoons (" + bassoon + "/4)", "Price: " + bassoonCost + "\nEffect: +40 violins/hour\nID: `bassoon`", false)
                                                    .setTitle("__**Woodwinds**__");
                                            if (contrabassoon) {
                                                builder.addField("Contrabassoon (1/1)", "Price: 400 000\nEffect: +30 violins/hour\nID: `contrabassoon`, `cb`", false);
                                            } else {
                                                builder.addField("Contrabassoon (0/1)", "Price: 400 000\nEffect: +30 violins/hour\nID: `contrabassoon`, `cb`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "3" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("French Horns (" + horn + "/8)", "Price: " + hornCost + "\nEffect: +40 violins/hour\nID: `horn`", false)
                                                    .addField("Trumpet (" + trumpet + "/4)", "Price: " + oboeCost + "\nEffect: +30 violins/hour\nID: `trumpet`", false)
                                                    .addField("Trombone (" + trombone + "/6)", "Price: " + clarinetCost + "\nEffect: +20 violins/hour\nID: `clarinet`", false)
                                                    .addField("Tuba (" + tuba + "/2)", "Price: " + tubaCost + "\nEffect: +20 violins/hour\nID: `tuba`", false)
                                                    .addField("Timpani (" + timpani + "/2)", "Price: " + timpaniCost + "\nEffect: +60 violins/hour\nID: `timpani`", false)
                                                    .addField("Percussionists (" + percussion + "/2)", "Price: " + percussionCost + "\nEffect: +10 violins/hour\nID: `percussion`", false)
                                                    .setTitle("__**Brass and Percussion**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "4" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Violin I (" + first + "/20)", "Price: " + firstCost + "\nEffect: +70 violins/hour\nID: `first`", false)
                                                    .addField("Violin II (" + second + "/20)", "Price: " + secondCost + "\nEffect: +60 violins/hour\nID: `second`", false)
                                                    .addField("Cellos (" + cello + "/15)", "Price: " + celloCost + "\nEffect: +50 violins/hour\nID: `cello`", false)
                                                    .addField("Double Basses (" + stringBass + "/5)", "Price: " + doubleCost + "\nEffect: +50 violins/hour\nID: `double`, `upright`, `doublebass`, `db`", false)
                                                    .addField("Pianists (" + piano + "/2)", "Price: " + timpaniCost + "\nEffect: +110 violins/hour\nID: `piano`", false)
                                                    .setTitle("__**Strings**__");
                                            if(harp) {
                                                builder.addField("Harp (1/1)", "Price: 500 000\nEffect: +80 violins/hour\nID: `harp`", false);
                                            } else {
                                                builder.addField("Harp (0/1)", "Price: 500 000\nEffect: +80 violins/hour\nID: `harp`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "5" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Sopranos (" + soprano + "/20)", "Price: " + sopranoCost + "\nEffect: +30 violins/hour\nID: `soprano`", false)
                                                    .addField("Altos (" + alto + "/20)", "Price: " + altoCost + "\nEffect: +20 violins/hour\nID: `alto`", false)
                                                    .addField("Tenors (" + tenor + "/20)", "Price: " + tenorCost + "\nEffect: +20 violins/hour\nID: `tenor`", false)
                                                    .addField("Basses (" + bass + "/20)", "Price: " + bassCost + "\nEffect: +20 violins/hour\nID: `bass`", false)
                                                    .addField("Vocal Soloists (" + soloists + "/4)", "Price: " + soloistCost + "\nEffect: +60 violins/hour\nID: `soloist`", false)
                                                    .setTitle("__**Choir**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "6" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Concert Hall Quality (" + hallLevel + "/5)", "Price: " + hallCost + "\nEffect: +300 violins/hour\nID: `concert`, `hall`", false)
                                                    .addField("Conductor Musicality (" + conductor + "/5)", "Price: " + conductorCost + "\nEffect: +200 violins/hour\nID: `conductor`, `musicality`", false)
                                                    .addField("Advertisement (" + advertising + "/20)", "Price: " + advertisingCost + "\nEffect: +100 violins/hour\nID: `advertisement`, `ad`", false)
                                                    .addField("Ticket Price (" + tickets + "/5)", "Price: " + ticketCost + "\nEffect: +1000 violins/hour\nID: `tickets`", false)
                                                    .setTitle("__**Miscellaneous Orchestra Items**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "7" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `!buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Efficient Practising (" + workL + "/75)", "Price: " + workCost + "\nEffect: Increases your income from `!practice`, `!rehearse`, and `!perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                                                    .addField("Lucky Musician (" + gambleL + "/40)", "Price: " + gambleCost + "\nEffect: Increases your chance of winning at `!bet` by 1%.\nID: `lucky`, `lm`", false)
                                                    .addField("Sophisticated Robbing (" + robL + "/25)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `!rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
                                                    .setTitle("__**Other Miscellaneous Upgrades**__");
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
                                        default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Other Miscellaneous Upgrades").queue();
                                    }
                                }
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You must provide a page number!").queue();
                            }
                        }
                        case "buy" -> {
                            if (!hasData) {
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
                                case "orchestra" -> {
                                    if (hasOrchestra) {
                                        e.getChannel().sendMessage("You already hired an orchestra!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (hourlyIncome < 9500) {
                                        e.getChannel().sendMessage("You don't have enough hourly income to hire an orchestra!").queue();
                                    } else if (violins < 40000000) {
                                        e.getChannel().sendMessage("You are too poor to hire an orchestra!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= 40000000;
                                        hasOrchestra = true;
                                        hourlyIncome += 3130;
                                        e.getChannel().sendMessage("Successfully hired an Orchestra!  You have automatically gained 1 Violin I and 1 Violin II for free.\nYou have " + violins + " violins left.").queue();
                                    }
                                }
                                case "concert", "hall" -> {
                                    if(!hasOrchestra) {
                                        if (hallLevel == 2) {
                                            e.getChannel().sendMessage("Hire an orchestra to unlock Levels 3-5!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < hallCost) {
                                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= hallCost;
                                            hallLevel++;
                                            hourlyIncome += 300;
                                            e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + "!\nYou have " + violins + " violins left.").queue();
                                        }
                                    } else {
                                        if (hallLevel == 5) {
                                            e.getChannel().sendMessage("You can't buy more of this upgrade!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < hallCost) {
                                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= hallCost;
                                            hallLevel++;
                                            hourlyIncome += 300;
                                            e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + "!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                }
                            }
                            if(hasOrchestra) {
                                switch(message[1]) {
                                    case "piccolo" -> {
                                        if (piccolo) {
                                            e.getChannel().sendMessage("You already hired hired a piccolo player!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < 300000) {
                                            e.getChannel().sendMessage("You are too poor to hire a piccolo player!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= 300000;
                                            piccolo = true;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a piccolo player!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "contrabassoon", "cb" -> {
                                        if (contrabassoon) {
                                            e.getChannel().sendMessage("You already hired hired a contrabassonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < 400000) {
                                            e.getChannel().sendMessage("You are too poor to hire a contrabassonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= 400000;
                                            contrabassoon = true;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a contrabassoonist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "harp" -> {
                                        if (harp) {
                                            e.getChannel().sendMessage("You already hired hired a harpist!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < 500000) {
                                            e.getChannel().sendMessage("You are too poor to hire a harpist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= 500000;
                                            harp = true;
                                            hourlyIncome += 80;
                                            e.getChannel().sendMessage("Successfully hired a harpist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "flute" -> {
                                        if (flute == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of flautists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < fluteCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a flautist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= fluteCost;
                                            flute++;
                                            hourlyIncome += 60;
                                            e.getChannel().sendMessage("Successfully hired a flautist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "oboe" -> {
                                        if (oboe == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of oboists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < oboeCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a oboist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= oboeCost;
                                            oboe++;
                                            hourlyIncome += 50;
                                            e.getChannel().sendMessage("Successfully hired a oboist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "clarinet" -> {
                                        if (clarinet == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of clarinetists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < clarinetCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a clarinetist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= clarinetCost;
                                            clarinet++;
                                            hourlyIncome += 40;
                                            e.getChannel().sendMessage("Successfully hired a clarinetist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "bassoon" -> {
                                        if (bassoon == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of bassoonists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < bassoonCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a bassoonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= bassoonCost;
                                            bassoon++;
                                            hourlyIncome += 40;
                                            e.getChannel().sendMessage("Successfully hired a bassoonist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "horn" -> {
                                        if (horn == 8) {
                                            e.getChannel().sendMessage("You already hired the max amount of hornists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < hornCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a hornist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= hornCost;
                                            horn++;
                                            hourlyIncome += 40;
                                            e.getChannel().sendMessage("Successfully hired a hornist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "trumpet" -> {
                                        if (trumpet == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of trumpeters!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < trumpetCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a trumpeter!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= trumpetCost;
                                            trumpet++;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a trumpeter!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "trombone" -> {
                                        if (trombone == 6) {
                                            e.getChannel().sendMessage("You already hired the max amount of trombonists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < tromboneCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a trombonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= tromboneCost;
                                            trombone++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a trombonist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "tuba" -> {
                                        if (horn == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of tubists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < tubaCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a tubist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= tubaCost;
                                            tuba++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a tubist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "timpani" -> {
                                        if (timpani == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of timpanists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < timpaniCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a timpanist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= timpaniCost;
                                            timpani++;
                                            hourlyIncome += 60;
                                            e.getChannel().sendMessage("Successfully hired a timpanist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "percussion" -> {
                                        if (percussion == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of percussionists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < percussionCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a percussionist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= percussionCost;
                                            percussion++;
                                            hourlyIncome += 10;
                                            e.getChannel().sendMessage("Successfully hired a percussionist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "first" -> {
                                        if (first == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of first violinists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < firstCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a first violinist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= firstCost;
                                            first++;
                                            hourlyIncome += 70;
                                            e.getChannel().sendMessage("Successfully hired a first violinist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "second" -> {
                                        if (second == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of second violinists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < secondCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a second violinist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= secondCost;
                                            second++;
                                            hourlyIncome += 60;
                                            e.getChannel().sendMessage("Successfully hired a second violinist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "cello" -> {
                                        if (cello == 15) {
                                            e.getChannel().sendMessage("You already hired the max amount of cellists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < celloCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a cellist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= celloCost;
                                            cello++;
                                            hourlyIncome += 50;
                                            e.getChannel().sendMessage("Successfully hired a cellist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "double", "upright", "db", "doublebass" -> {
                                        if (stringBass == 5) {
                                            e.getChannel().sendMessage("You already hired the max amount of double bassists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < doubleCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a double bassist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= doubleCost;
                                            stringBass++;
                                            hourlyIncome += 50;
                                            e.getChannel().sendMessage("Successfully hired a double bassist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "piano" -> {
                                        if (piano == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of pianists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < pianoCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a pianist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= pianoCost;
                                            piano++;
                                            hourlyIncome += 110;
                                            e.getChannel().sendMessage("Successfully hired a pianists!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "soprano" -> {
                                        if (soprano == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of sopranists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < sopranoCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a sopranist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= sopranoCost;
                                            soprano++;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a sopranist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "alto" -> {
                                        if (alto == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of altos!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < altoCost) {
                                            e.getChannel().sendMessage("You are too poor to hire an alto!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= altoCost;
                                            alto++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired an alto!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "tenor" -> {
                                        if (tenor == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of tenors!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < tenorCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a tenor!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= tenorCost;
                                            tenor++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a tenor!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "bass" -> {
                                        if (bass == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of bassists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < bassCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a bassist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= bassCost;
                                            bass++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a bassist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "soloist" -> {
                                        if (soloists == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of soloists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < soloistCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a soloist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= soloistCost;
                                            soloists++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a soloist!\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "conductor", "musicality" -> {
                                        if (conductor == 5) {
                                            e.getChannel().sendMessage("The conductor has been maxed in Musicality!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < conductorCost) {
                                            e.getChannel().sendMessage("You are too poor to train the conductor!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= conductorCost;
                                            conductor++;
                                            hourlyIncome += 200;
                                            e.getChannel().sendMessage("Successfully upgraded Conductor Musicality to Level " + conductor + "\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "advertisement", "ad" -> {
                                        if (advertising == 20) {
                                            e.getChannel().sendMessage("You can't advertise more!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < conductorCost) {
                                            e.getChannel().sendMessage("You are too poor to buy more advertisements!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= advertisingCost;
                                            advertising++;
                                            hourlyIncome += 100;
                                            e.getChannel().sendMessage("Successfully upgraded Advertising to Level " + advertising + "\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                    case "tickets" -> {
                                        if (tickets == 5) {
                                            e.getChannel().sendMessage("You can't make your tickets more expensive!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < conductorCost) {
                                            e.getChannel().sendMessage("You are too poor to make your tickets more expensive!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= ticketCost;
                                            tickets++;
                                            hourlyIncome += 1000;
                                            e.getChannel().sendMessage("Successfully upgraded Ticket Cost to Level " + conductor + "\nYou have " + violins + " violins left.").queue();
                                        }
                                    }
                                }
                            }
                        }
                        case "orchestra", "o" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
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
                                e.getChannel().sendMessage("You don't have an orchestra yet!  Run `!buy orchestra` to get one!  Remember that you need 9 500 income per hour and 40 Million violins to hire one!").queue();
                            }
                        }
                        case "cooldowns", "c" -> {
                            if (!hasData) {
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
                            if (!hasData) {
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
                            if (!hasData) {
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
                                    base = (int) ((random.nextInt(40) + 280) * Math.pow(1.05, workL) * 1.1);
                                } else {
                                    base = (int) ((random.nextInt(40) + 280) * Math.pow(1.05, workL));
                                }
                                violins += base;
                                e.getChannel().sendMessage("You practised for one hour and earned " + base + " violins.").queue();
                                workC = time + 2640000;
                            }
                        }
                        case "rehearse", "r" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
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
                                    int base;
                                    if (serverVote) {
                                        base = (int) ((random.nextInt(100) + 1200) * Math.pow(1.05, workL) * 1.1);
                                    } else {
                                        base = (int) ((random.nextInt(100) + 1200) * Math.pow(1.05, workL));
                                    }
                                    violins += base;
                                    e.getChannel().sendMessage("You went to rehearse with the Berlin Philharmonic and earned " + base + " violins.").queue();
                                    rehearseC = time + 86340000;
                                }
                            } else {
                                e.getChannel().sendMessage("You must hire an orchestra to use this command!").queue();
                            }
                        }
                        case "perform" -> {
                            if (!hasData) {
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
                            } else {
                                int base = (int) ((random.nextInt(1500) + 6250) * Math.pow(1.05, workL) * (1.2 * hallLevel));
                                if(serverVote) {
                                    base *= 1.1;
                                }
                                if(hasOrchestra) {
                                    base *= 2;
                                }
                                violins += base;
                                e.getChannel().sendMessage("You performed your solo and earned " + base + " violins.").queue();
                                performC = time + 604740000;
                            }
                        }
                        case "rob" -> {
                            if (!hasData) {
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
                                    } catch (Exception exception1) {
                                        if (random.nextDouble() < 0.1) {
                                            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F3BB").queue();
                                        }
                                    }
                                }
                                if (e.getAuthor().getId().equals(target)) {
                                    e.getChannel().sendMessage("Why would you rob yourself, are you actually that dumb?").queue();
                                    throw new IllegalArgumentException();
                                }
                                String[] targetDataArray;
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
                                    User send = e.getJDA().getUserById(target);
                                    assert send != null;
                                    send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> tried to rob you but failed!  They paid you " + (long) (Long.parseLong(targetDataArray[0]) * 0.05) + " violins in fines.").queue();
                                    targetViolins += Long.parseLong(dataArray[0]) * 0.05;
                                    violins -= Long.parseLong(dataArray[0]) * 0.05;
                                } else {
                                    if (insurance == 1) {
                                        e.getChannel().sendMessage("You try to rob <@" + target + "> only to notice that Ling Ling Security is present.  You try to escape but you are caught and kicked from the estate.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> tried to rob you!  Luckily, insurance protected you and you only paid " + (long) (Long.parseLong(targetDataArray[0]) * 0.03) + " violins in insurance costs.").queue();
                                        targetViolins -= Long.parseLong(targetDataArray[0]) * 0.03;
                                    } else if (insurance == 2) {
                                        e.getChannel().sendMessage("You successfully robbed <@" + target + "> but only managed to get away with " + (long) (Long.parseLong(targetDataArray[0]) * 0.067) + " violins before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> just robbed " + (long) (Long.parseLong(targetDataArray[0]) * 0.067) + " violins from you!  Your Ling Ling insurance protected " + (long) (Long.parseLong(targetDataArray[0]) * 0.133) + " violins.").queue();
                                        targetViolins -= Long.parseLong(targetDataArray[0]) * 0.067;
                                        violins += Long.parseLong(targetDataArray[0]) * 0.067;
                                    } else {
                                        e.getChannel().sendMessage("You successfully robbed <@" + target + ">!  Your payout was " + (long) (Long.parseLong(targetDataArray[0]) * 0.2) + " violins!\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> just robbed " + (long) (Long.parseLong(targetDataArray[0]) * 0.2) + " violins from you!").queue();
                                        targetViolins -= Long.parseLong(targetDataArray[0]) * 0.2;
                                        violins += Long.parseLong(targetDataArray[0]) * 0.2;
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
                                afterRob.print(targetViolins);
                                for(int i = 1; i < targetDataArray.length; i ++) {
                                    afterRob.print(" " + targetDataArray[i]);
                                }
                                afterRob.close();
                            }
                        }
                        case "gamble", "bet" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `!start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            long bet;
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
                                    e.getChannel().sendMessage("You lost " + bet + " violins!\n*The generator rolled " + chance + ", you need less than " + (0.4 + 0.01 * gambleL) + " to win.*\nYou now have " + violins + " violins.").queue();
                                } else if (chance <= 0.4 + 0.01 * gambleL) {
                                    violins += bet;
                                    e.getChannel().sendMessage("You won " + bet + " violins!\n*The generator rolled " + chance + ".*\nYou now have " + violins + " violins.").queue();
                                }
                                if (violins == 69) {
                                    e.getChannel().sendMessage("I'm not going to let you waste your time trying to get the funny number, I'm taking one violin away.").queue();
                                    violins--;
                                }
                            }
                        }
                        case "balance", "bal", "b" -> {
                            if (!hasData) {
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
                            File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                            File[] files = directory.listFiles();
                            String[] entry = new String[0];
                            boolean isGlobal = false;
                            if (files != null) {
                                String temp2 = "REEEEE";
                                try {
                                    temp2 = message[1];
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                                if (temp2.equals("global")) {
                                    isGlobal = true;
                                    entry = new String[]{"<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n"};
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
                                        if (numViolins == 0) {
                                            continue;
                                        }
                                        for (int i = 0; i < 10; i++) {
                                            if (numViolins > Integer.parseInt(entry[i].split(" ")[1])) {
                                                System.arraycopy(entry, i, entry, i + 1, 9 - i);
                                                entry[i] = "<@" + user + ">: " + numViolins + " violins.\n";
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    entry = new String[]{"null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n"};
                                    List<Member> userlist = e.getGuild().getMembers();
                                    for (Member user : userlist) {
                                        String currentData;
                                        if (user.getUser().isBot()) {
                                            continue;
                                        }
                                        try {
                                            br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt"));
                                            currentData = br.readLine();
                                            br.close();
                                        } catch (Exception exception) {
                                            //nothing here lol
                                            //entry format: **Position.** user#9999 `userID`: num violins
                                            continue;
                                        }
                                        assert currentData != null;
                                        String[] temp = currentData.split(" ");
                                        int numViolins = Integer.parseInt(temp[0]);
                                        if (numViolins == 0) {
                                            continue;
                                        }
                                        for (int i = 0; i < 10; i++) {
                                            if (numViolins > Integer.parseInt(entry[i].split(" ")[2])) {
                                                System.arraycopy(entry, i, entry, i + 1, 9 - i);
                                                String name = user.getUser().getName().replace(' ', '-');
                                                entry[i] = name + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: " + numViolins + " violins.\n";
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            StringBuilder board = new StringBuilder();
                            for (int i = 0; i < 10; i++) {
                                if (entry[i].contains("#0000")) {
                                    break;
                                }
                                board.append("**").append(i + 1).append(".** ").append(entry[i]);
                            }
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                    .setTitle("__**Economy Leaderboard**__");
                            if (isGlobal) {
                                builder.addField("Richest Users in the World", board.toString(), false);
                            } else {
                                builder.addField("Richest Users in " + e.getGuild().getName(), board.toString(), false);
                            }
                            e.getChannel().sendMessage(builder.build()).queue();
                        }
                    }
                    if (hasData) {
                        PrintWriter pw = null;
                        try {
                            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                        } catch (Exception exception) {
                            //nothing here lol
                        }
                        assert pw != null;
                        pw.print(violins + " " + workC + " " + workL + " " + gambleC + " " + gambleL + " " + robC + " " + robL + " " + rehearseC + " " + performC + " " + ownInsurance1 + " " + ownInsurance2 + " " + activeInsurance + " " + hourlyIncome + " " + violinQuality + " " + skillLevel + " " + lessonQuality + " " + stringQuality + " " + bowQuality + " " + hasMath + " " + hasOrchestra + " " + piccolo + " " + flute + " " + oboe + " " + clarinet + " " + bassoon + " " + contrabassoon + " " + horn + " " + trumpet + " " + trombone + " " + tuba + " " + timpani + " " + percussion + " " + first + " " + second + " " + cello + " " + stringBass + " " + piano + " " + harp + " " + soprano + " " + alto + " " + tenor + " " + bass + " " + soloists + " " + hallLevel + " " + conductor + " " + advertising + " " + tickets);
                        pw.close();
                    }
                }
            } catch (Exception exception) {
                //nothing here lol
            }
        }
    }
}