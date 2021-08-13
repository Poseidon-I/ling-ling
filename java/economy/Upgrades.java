package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Upgrades {
    public static void IncomeUpgrades(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                .addField("Violin Quality (" + data[13] + ")", "Price: " + (long) (Math.pow(3, Long.parseLong(data[13])) * 1000) + ":violin:\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
                .addField("Skill Level (" + data[14] + ")", "Price: " + (long) (Math.pow(2, Long.parseLong(data[14])) * 500) + ":violin:\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
                .addField("Lesson Quality (" + data[15] + ")", "Price: " + (long) (Math.pow(2.5, Long.parseLong(data[15])) * 750) + ":violin:\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
                .addField("String Quality (" + data[16] + ")", "Price: " + (long) (Math.pow(1.75, Long.parseLong(data[16])) * 500) + ":violin:\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
                .addField("Bow Quality (" + data[17] + ")", "Price: " + (long) (Math.pow(3, Long.parseLong(data[17])) * 750) + ":violin:\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
                .setTitle("__**Income Upgrades**__");
        if (Boolean.parseBoolean(data[18])) {
            builder.addField("Math Tutoring (1/1)", "Price: 10 000 000\nEffect: +6500:violin:/hour\nID: `math`", false);
        } else {
            builder.addField("Math Tutoring (0/1)", "Price: 10 000 000\nEffect: +6500:violin:/hour\nID: `math`", false);
        }
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void OrchMiscUpgrades(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder;
        if(!Boolean.parseBoolean(data[19])) {
            builder = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setFooter("User balance: " + data[0] + "\nUser `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                    .addField("Concert Hall Quality (" + data[43] + "/2)", "Price: " + (long) (Math.pow(5, Long.parseLong(data[43])) * 50000) + ":violin:\nEffect: +300:violin:/hour, +10% violins from `" + prefix + "scales`, `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                    .addField("Orchestra", "Price: 25 000 000\nIncome Requirement: 7 500\nEffect: +3 100:violin:/hour, access to `" + prefix + "rehearse` command\nID:`orchestra`, `o`", false)
                    .setTitle("__**Miscellaneous Orchestra Items**__");
        } else if(!Boolean.parseBoolean(data[78])){
            builder = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                    .addField("Concert Hall Quality (" + data[43] + ")", "Price: " + (long) (Math.pow(5, Long.parseLong(data[43])) * 50000) + ":violin:\nEffect: +300:violin:/hour, +10% violins from `" + prefix + "scales`, `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                    .addField("Conductor Musicality (" + data[44] + ")", "Price: " + (long) (Math.pow(4, Long.parseLong(data[44])) * 100000) + ":violin:\nEffect: +200:violin:/hour\nID: `conductor`, `musicality`", false)
                    .addField("Ticket Price (" + data[46] + ")", "Price: " + (long) (Math.pow(2, Long.parseLong(data[46])) * 1000000) + ":violin:\nEffect: +1000:violin:/hour\nID: `tickets`", false)
                    .addField("Advertising (" + data[45] + "/20)", "Price: " + 100000 * (Long.parseLong(data[45]) + 1) + ":violin:\nEffect: +100:violin:/hour\nID: `advertisement`, `ad`", false)
                    .addField("Teaching Certificate", "Price: 200 000 000\nIncome Requirement: 40 000:violin:/hr\nEffect: +5 000:violin:/hr, access to `" + prefix + "teach` command\nID: `certificate`", false)
                    .setTitle("__**Miscellaneous Orchestra Items**__");
        } else {
            builder = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                    .addField("Concert Hall Quality (" + data[43] + ")", "Price: " + (long) (Math.pow(5, Long.parseLong(data[43])) * 50000) + ":violin:\nEffect: +300:violin:/hour, +10% violins from `" + prefix + "scales`, `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                    .addField("Conductor Musicality (" + data[44] + ")", "Price: " + (long) (Math.pow(4, Long.parseLong(data[44])) * 100000) + ":violin:\nEffect: +200:violin:/hour\nID: `conductor`, `musicality`", false)
                    .addField("Ticket Price (" + data[46] + ")", "Price: " + (long) (Math.pow(2, Long.parseLong(data[46])) * 1000000) + ":violin:\nEffect: +1000:violin:/hour\nID: `tickets`", false)
                    .addField("Advertising (" + data[45] + "/20)", "Price: " + 100000 * (Long.parseLong(data[45]) + 1) + ":violin:\nEffect: +100:violin:/hour\nID: `advertisement`, `ad`", false)
                    .setTitle("__**Miscellaneous Orchestra Items**__");
        }
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void OtherMiscUpgrades(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                .addField("Efficient Practising (" + data[2] + ")", "Price: " + (long) (Math.pow(1.1, Long.parseLong(data[2])) * 400) + ":violin:\nEffect: Increases your income from `" + prefix + "scales`, `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 5%.\nID: `efficient`, `practising`, `ep`", false)
                .addField("Lucky Musician (" + data[4] + "/50)", "Price: " + (long) (Math.pow(1.25, Long.parseLong(data[4])) * 1000) + ":violin:\nEffect: Increases your gambling multiplier by 0.5%.\nID: `lucky`, `lm`", false)
                .addField("Sophisticated Robbing (" + data[6] + "/30)", "Price: " + (long) (Math.pow(1.4, Long.parseLong(data[6])) * 5000) + ":violin:\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.4%.\nID: `sophisticated`, `robbing`, `sr`", false)
                .setTitle("__**Other Miscellaneous Upgrades**__");
        if (Boolean.parseBoolean(data[9])) {
            builder.addField("Ling Ling Insurance - Plan 1 - Full Security (1/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen.  You pay a 15% insurance cost.\nID: `1`", false);
        } else {
            builder.addField("Ling Ling Insurance - Plan 1 - Full Security (0/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen.  You pay a 15% insurance cost.\nID: `1`", false);
        }
        if (Boolean.parseBoolean(data[10])) {
            builder.addField("Ling Ling Insurance - Plan 2 - Half Security (1/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 90% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
        } else {
            builder.addField("Ling Ling Insurance - Plan 2 - Half Security (0/1)", "Price: 3 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 90% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
        }
        if(Boolean.parseBoolean(data[50])) {
            builder.addField("Time Crunch (1/1)", "Price: 120 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `tc`, `timecrunch`", false);
        } else {
            builder.addField("Time Crunch (0/1)", "Price: 120 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `tc`, `timecrunch`", false);
        }
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void MedalUpgrades(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Medals: " + data[55] + "\nEarn medals from `" + prefix + "perform`, Blessings, and Lootboxes!", e.getJDA().getSelfUser().getAvatarUrl())
                .setTitle("**__Medal Upgrades__**")
                .addField("Extra Income (" + data[56] + ")", "Price: " + (Long.parseLong(data[56]) + 1) + ":military_medal:\nEffect: +2500:violin:/hour\nID: `income`", false)
                .addField("Extra Command Income (" + data[57] + ")", "Price: " + (long) (Math.pow(2, Long.parseLong(data[57]))) + ":military_medal:\nEffect: Increases income from all commands except `!teach` by 30%\nID: `commandincome`", false)
                .addField("Higher Gamble Limit (" + data[58] + ")", "Price: " + (Long.parseLong(data[58]) + 1) + ":military_medal:\nEffect: +1x Gamble Cap\nID: `gamblelimit`", false)
                .addField("Higher Rob Success Rate (" + data[59] + ")", "Price: " + (long) (Math.pow(2, Long.parseLong(data[59]))) + ":military_medal:\nEffect: Increases the chance of successfully robbing someone by 0.4%\nID: `robsuccess`", false);
        if(Boolean.parseBoolean(data[60])) {
            builder.addField("Steal Shield :white_check_mark:", "Effect: Advanced technology takes back 50% of violins when you get robbed", false);
        } else {
            builder.addField("Steal Shield", "Price: 10:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed\nID: `shield`", false);
        }
        if(Boolean.parseBoolean(data[61])) {
            builder.addField("Violin Duplicator :white_check_mark:", "Effect: The Vengeful God of Ben Lee duplicates all violins stolen", false);
        } else {
            builder.addField("Violin Duplicator", "Price: 15:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen\nID: `duplicator`", false);
        }
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void Woodwinds(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl());
        if(Boolean.parseBoolean(data[20])) {
            builder.addField("Piccolo (1/1)", "Price: 250 000\nEffect: +30:violin:/hour\nID: `piccolo`", false);
        } else {
            builder.addField("Piccolo (0/1)", "Price: 250 000\nEffect: +30:violin:/hour\nID: `piccolo`", false);
        }
        builder.addField("Flutes (" + data[21] + "/4)", "Price: " + 300000 * (Long.parseLong(data[21]) + 1) + ":violin:\nEffect: +60:violin:/hour\nID: `flute`", false)
                .addField("Oboes (" + data[22] + "/4)", "Price: " + 300000 * (Long.parseLong(data[22]) + 1) + ":violin:\nEffect: +50:violin:/hour\nID: `oboe`", false)
                .addField("Clarinets (" + data[23] + "/4)", "Price: " + 250000 * (Long.parseLong(data[23]) + 1) + ":violin:\nEffect: +40:violin:/hour\nID: `clarinet`", false)
                .addField("Bassoons (" + data[24] + "/4)", "Price: " + 250000 * (Long.parseLong(data[24]) + 1) + ":violin:\nEffect: +40:violin:/hour\nID: `bassoon`", false)
                .setTitle("__**Woodwinds**__");
        if (Boolean.parseBoolean(data[25])) {
            builder.addField("Contrabassoon (1/1)", "Price: 300 000\nEffect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
        } else {
            builder.addField("Contrabassoon (0/1)", "Price: 300 000\nEffect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
        }
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void Brass(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                .addField("French Horns (" + data[26] + "/8)", "Price: " + 250000 * (Long.parseLong(data[26]) + 1) + ":violin:\nEffect: +40:violin:/hour\nID: `horn`", false)
                .addField("Trumpet (" + data[27] + "/4)", "Price: " + 300000 * (Long.parseLong(data[27]) + 1) + ":violin:\nEffect: +30:violin:/hour\nID: `trumpet`", false)
                .addField("Trombone (" + data[28] + "/6)", "Price: " + 200000 * (Long.parseLong(data[28]) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `trombone`", false)
                .addField("Tuba (" + data[29] + "/2)", "Price: " + 200000 * (Long.parseLong(data[29]) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `tuba`", false)
                .addField("Timpani (" + data[30] + "/2)", "Price: " + 300000 * (Long.parseLong(data[30]) + 1) + ":violin:\nEffect: +60:violin:/hour\nID: `timpani`", false)
                .addField("Percussionists (" + data[31] + "/2)", "Price: " + 100000 * (Long.parseLong(data[31]) + 1) + ":violin:\nEffect: +10:violin:/hour\nID: `percussion`", false)
                .setTitle("__**Brass and Percussion**__");
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void Strings(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                .addField("Violin I (" + data[32] + "/20)", "Price: " + 500000 * Long.parseLong(data[32]) + ":violin:\nEffect: +70:violin:/hour\nID: `first`", false)
                .addField("Violin II (" + data[33] + "/20)", "Price: " + 400000 * Long.parseLong(data[33]) + ":violin:\nEffect: +60:violin:/hour\nID: `second`", false)
                .addField("Cellos (" + data[34] + "/15)", "Price: " + 400000 * (Long.parseLong(data[34]) + 1) + ":violin:\nEffect: +50:violin:/hour\nID: `cello`", false)
                .addField("Double Basses (" + data[35] + "/5)", "Price: " + 400000 * (Long.parseLong(data[35]) + 1) + ":violin:\nEffect: +50:violin:/hour\nID: `doublebass`, `db`", false)
                .addField("Pianists (" + data[36] + "/2)", "Price: " + 750000 * (Long.parseLong(data[36]) + 1) + ":violin:\nEffect: +110:violin:/hour\nID: `piano`", false)
                .setTitle("__**Strings**__");
        if(Boolean.parseBoolean(data[37])) {
            builder.addField("Harp (1/1)", "Price: 400 000\nEffect: +80:violin:/hour\nID: `harp`", false);
        } else {
            builder.addField("Harp (0/1)", "Price: 400 000\nEffect: +80:violin:/hour\nID: `harp`", false);
        }
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void Choir(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                .addField("Sopranos (" + data[38] + "/20)", "Price: " + 100000 * (Long.parseLong(data[38]) + 1) + ":violin:\nEffect: +30:violin:/hour\nID: `soprano`", false)
                .addField("Altos (" + data[39] + "/20)", "Price: " + 75000 * (Long.parseLong(data[39]) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `alto`", false)
                .addField("Tenors (" + data[40] + "/20)", "Price: " + 75000 * (Long.parseLong(data[40]) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `tenor`", false)
                .addField("Basses (" + data[41] + "/20)", "Price: " + 75000 * (Long.parseLong(data[41]) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `bass`", false)
                .addField("Vocal Soloists (" + data[42] + "/4)", "Price: " + 300000 * (Long.parseLong(data[42]) + 1) + ":violin:\nEffect: +60:violin:/hour\nID: `soloist`", false)
                .setTitle("__**Choir**__");
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public static void TeacherUpgrades(GuildMessageReceivedEvent e, String[] data, char prefix) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("User balance: " + data[0] + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                .addField("More Students (" + data[81] + ")", "Price: " + (long) (Math.pow(2, Long.parseLong(data[81])) * 1000000) + ":violin:\nEffect: +2 000:violin:/hour, +15% violins from `" + prefix + "teach`\nID: `students`", false)
                .addField("Higher Lesson Rates (" + data[82] + "/5)", "Price: " + 2000000 * (Long.parseLong(data[82]) + 1) + ":violin:\nEffect: +3 000:violin:/hour, +10% violins from `" + prefix + "teach`\nID: `pricing`", false)
                .addField("Teacher Training (" + data[80] + "/10)", "Price: " + 1250000 * (Long.parseLong(data[80]) + 1) + ":violin:\nEffect: +1 000:violin:/hour, +5% violins from `" + prefix + "teach`\nID: `training`", false);
        if (Boolean.parseBoolean(data[83])) {
            builder.addField("Teaching Studio (1/1)", "Price: 25 000 000\nEffect: +5 000:violin:/hour\nID: `studio`", false);
        } else {
            builder.addField("Teaching Studio (0/1)", "Price: 25 000 000\nEffect: +5 000:violin:/hour\nID: `studio`", false);
        }
        if (Boolean.parseBoolean(data[84])) {
            builder.addField("Longer Lessons (1/1)", "Price: 25 000 000\nEffect: +100% violins from `" + prefix + "teach`\nID: `longer`", false);
        } else {
            builder.addField("Longer Lessons (0/1)", "Price: 25 000 000\nEffect: +100% violins from `" + prefix + "teach`\nID: `longer`", false);
        }
        builder.setTitle("__**Teacher Upgrades**__");
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public Upgrades(GuildMessageReceivedEvent e, String[] data, char prefix) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        if(message.length == 0) {
            e.getChannel().sendMessage("You must provide a page number!").queue();
        } else {
            try {
                if (!Boolean.parseBoolean(data[19])) {
                    switch (message[1]) {
                        case "1" -> IncomeUpgrades(e, data, prefix);
                        case "2" -> OrchMiscUpgrades(e, data, prefix);
                        case "3" -> OtherMiscUpgrades(e, data, prefix);
                        case "4" -> MedalUpgrades(e, data, prefix);
                        default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Miscellaneous Orchestra Items\n`3` for Other Miscellaneous Upgrades\n`4` for Medal Upgrades").queue();
                    }
                } else if (!Boolean.parseBoolean(data[78])) {
                    switch (message[1]) {
                        case "1" -> IncomeUpgrades(e, data, prefix);
                        case "2" -> Woodwinds(e, data, prefix);
                        case "3" -> Brass(e, data, prefix);
                        case "4" -> Strings(e, data, prefix);
                        case "5" -> Choir(e, data, prefix);
                        case "6" -> OrchMiscUpgrades(e, data, prefix);
                        case "7" -> OtherMiscUpgrades(e, data, prefix);
                        case "8" -> MedalUpgrades(e, data, prefix);
                        default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Other Miscellaneous Upgrades\n`8` for Medal Upgrades").queue();
                    }
                } else {
                    switch (message[1]) {
                        case "1" -> IncomeUpgrades(e, data, prefix);
                        case "2" -> Woodwinds(e, data, prefix);
                        case "3" -> Brass(e, data, prefix);
                        case "4" -> Strings(e, data, prefix);
                        case "5" -> Choir(e, data, prefix);
                        case "6" -> OrchMiscUpgrades(e, data, prefix);
                        case "7" -> TeacherUpgrades(e, data, prefix);
                        case "8" -> OtherMiscUpgrades(e, data, prefix);
                        case "9" -> MedalUpgrades(e, data, prefix);
                        default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Teacher Upgrades\n`8` for Other Miscellaneous Upgrades\n`9` for Medal Upgrades").queue();
                    }
                }
            } catch (Exception exception) {
                e.getChannel().sendMessage("You have to provide a page number for this to work.  Try `" + prefix + "upgrades 1`!").queue();
            }
        }
    }
}