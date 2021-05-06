package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Buy {
    public static void ProcessBooleanUpgrade(GuildMessageReceivedEvent e, long cost, int index, String item, String[] data) {
        long violins = Long.parseLong(data[0]);
        if(Boolean.parseBoolean(data[index])) {
            e.getChannel().sendMessage("You already purchased `" + item + "`!").queue();
        } else if(violins < cost) {
            e.getChannel().sendMessage("You do not have enough violins to purchase `" + item + "`!\nYou need " + cost + ":violin:, you only have " + violins + ":violin:").queue();
        } else {
            data[0] = String.valueOf(violins - cost);
            data[index] = "true";
            e.getChannel().sendMessage("Successfully purchased item `" + item + "` for `" + cost + "`:violin:\nYou have `" + data[0] + "`:violin: left.").queue();
            new SaveData(e, data);
        }
    }

    public static void ProcessUpgrade(GuildMessageReceivedEvent e, long cost, int index, long maxAmount, String item, String[] data) {
        long violins = Long.parseLong(data[0]);
        if (Long.parseLong(data[index]) == maxAmount) {
            e.getChannel().sendMessage("You already purchased the maximum amount of `" + item + "`!").queue();
        } else if (violins < cost) {
            e.getChannel().sendMessage("You do not have enough violins to purchase `" + item + "`!\nYou need " + cost + ":violin:, you only have " + data[0] + ":violin:").queue();
        } else {
            data[0] = String.valueOf(violins - cost);
            data[index] = String.valueOf(Long.parseLong(data[index]) + 1);
            e.getChannel().sendMessage("Successfully purchased item `" + item + " #" + data[index] + "` for `" + cost + "`:violin:\nYou have `" + data[0] + "`:violin: left.").queue();
            new SaveData(e, data);
        }
    }

    public Buy(GuildMessageReceivedEvent e, String[] data) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        boolean boughtItem = false;
        boolean hasOrchestra = Boolean.parseBoolean(data[19]);
        if (hasOrchestra) {
            boughtItem = true;
            switch (message[1]) {
                case "piccolo" -> ProcessBooleanUpgrade(e, 300000, 20, "Piccolo", data);
                case "contrabassoon", "cb" -> ProcessBooleanUpgrade(e, 400000, 25, "ContraBassoon", data);
                case "harp" -> ProcessBooleanUpgrade(e, 500000, 37, "Harp", data);
                case "flute" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[21]) + 1), 21, 4, "Flute", data);
                case "oboe" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[22]) + 1), 22, 4, "Oboe", data);
                case "clarinet" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[23]) + 1), 23, 4, "Clarinet", data);
                case "bassoon" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[24]) + 1), 24, 4, "Bassoon", data);
                case "horn" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[26]) + 1), 26, 8, "French Horn", data);
                case "trumpet" -> ProcessUpgrade(e, 300000 * (Long.parseLong(data[27]) + 1), 27, 4, "Trumpet", data);
                case "trombone" -> ProcessUpgrade(e, 250000 * (Long.parseLong(data[28]) + 1), 28, 6, "Trombone", data);
                case "tuba" -> ProcessUpgrade(e, 250000 * (Long.parseLong(data[29]) + 1), 29, 2, "Tuba", data);
                case "timpani" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[30]) + 1), 30, 2, "Timpani", data);
                case "percussion" -> ProcessUpgrade(e, 100000 * (Long.parseLong(data[31]) + 1), 31, 2, "Percussion", data);
                case "first" -> ProcessUpgrade(e, 600000 * (Long.parseLong(data[32]) + 1), 32, 20, "First Violin", data);
                case "second" -> ProcessUpgrade(e, 500000 * (Long.parseLong(data[33]) + 1), 33, 20, "Second Violin", data);
                case "cello" -> ProcessUpgrade(e, 500000 * (Long.parseLong(data[34]) + 1), 34, 15, "Cello", data);
                case "db", "doublebass" -> ProcessUpgrade(e, 500000 * (Long.parseLong(data[35]) + 1), 35, 5, "Double Bass", data);
                case "piano" -> ProcessUpgrade(e, 1000000 * (Long.parseLong(data[36]) + 1), 36, 2, "Piano", data);
                case "soprano" -> ProcessUpgrade(e, 100000 * (Long.parseLong(data[38]) + 1), 38, 20, "Soprano Vocalist", data);
                case "alto" -> ProcessUpgrade(e, 75000 * (Long.parseLong(data[39]) + 1), 39, 20, "Alto Vocalist", data);
                case "tenor" -> ProcessUpgrade(e, 75000 * (Long.parseLong(data[40]) + 1), 40, 20, "Tenor Vocalist", data);
                case "bass" -> ProcessUpgrade(e, 75000 * (Long.parseLong(data[41]) + 1), 41, 4, "Bass Vocalist", data);
                case "soloist" -> ProcessUpgrade(e, 400000 * (Long.parseLong(data[42]) + 1), 42, 4, "Solo Vocalist", data);
                case "conductor" -> ProcessUpgrade(e, (long) (Math.pow(5, Integer.parseInt(data[44]) * 100000)), 44, 5, "Conductor Musicality", data);
                case "advertisement", "ad" -> ProcessUpgrade(e, 100000 * (Long.parseLong(data[45])), 45, 20, "Advertising", data);
                case "tickets" -> ProcessUpgrade(e, (long) (Math.pow(2, Integer.parseInt(data[46])) * 2000000), 46, 5, "Ticket Cost", data);
                default -> boughtItem = false;
            }
        }
        if(!boughtItem) {
            boughtItem = true;
            switch (message[1]) {
                case "1" -> ProcessBooleanUpgrade(e, 3000000, 9, "Ling Ling Insurance - Plan 1 - Full Security", data);
                case "2" -> ProcessBooleanUpgrade(e, 3000000, 10, "Ling Ling Insurance - Plan 2 - Partial Security", data);
                case "timecrunch", "tc" -> ProcessBooleanUpgrade(e, 150000000, 50, "Time Crunch", data);
                case "efficiency", "ep" -> ProcessUpgrade(e, (long) (Math.pow(1.125, Integer.parseInt(data[2])) * 250), 2, 100, "Efficient Practising", data);
                case "lucky", "lm" -> ProcessUpgrade(e, (long) (Math.pow(1.25, Integer.parseInt(data[4])) * 1000), 4, 50, "Lucky Musician", data);
                case "robbing", "sr" -> ProcessUpgrade(e, (long) (Math.pow(1.4, Integer.parseInt(data[6])) * 5000), 6, 30, "Sophisticated Robbing", data);
                case "violin", "v" -> ProcessUpgrade(e, (long) (Math.pow(3.5, Integer.parseInt(data[13])) * 1000), 13, 10, "Violin Quality", data);
                case "skill", "s" -> ProcessUpgrade(e, (long) (Math.pow(2.25, Integer.parseInt(data[14])) * 500), 14, 15, "Skill Level", data);
                case "lesson", "l" -> ProcessUpgrade(e, (long) (Math.pow(2.75, Integer.parseInt(data[15])) * 700), 15, 12, "Lesson Quality", data);
                case "string", "str" -> ProcessUpgrade(e, (long) (Math.pow(1.8, Integer.parseInt(data[16])) * 400), 16, 20, "String Quality", data);
                case "bow", "b" -> ProcessUpgrade(e, (long) (Math.pow(3.5, Integer.parseInt(data[17])) * 500), 17, 10, "Bow Quality", data);
                case "math" -> ProcessBooleanUpgrade(e, 10000000, 18, "Math Tutoring", data);
                case "orchestra", "o" -> {
                    if (Long.parseLong(data[12]) < 7500) {
                        e.getChannel().sendMessage("You do not have enough hourly income to hire an orchestra!").queue();
                    } else {
                        ProcessBooleanUpgrade(e, 25000000, 19, "Orchestra", data);
                    }
                }
                case "concert", "hall" -> {
                    if (!hasOrchestra) {
                        ProcessUpgrade(e, (long) (Math.pow(10, Integer.parseInt(data[43])) * 10000), 43, 2, "Concert Hall Quality", data);
                    } else {
                        ProcessUpgrade(e, (long) (Math.pow(10, Integer.parseInt(data[43])) * 10000), 43, 5, "Concert Hall Quality", data);
                    }
                }
                default -> boughtItem = false;
            }
        }
        if(!boughtItem) {
            e.getChannel().sendMessage("You can't buy something that's not for sale, that would be quite a waste of time and violins.").queue();
        }
    }
}
