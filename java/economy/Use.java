package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Use {
    public Use(GuildMessageReceivedEvent e, String[] data, char prefix) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        long income = Long.parseLong(data[12]);
        long violins = Long.parseLong(data[0]);
        long violinsEarned = Long.parseLong(data[75]);
        switch (message[1]) {
            case "1" -> {
                if (Boolean.parseBoolean(data[9])) {
                    data[11] = "1";
                    e.getChannel().sendMessage("You have activated Plan 1 - Full Security").queue();
                    new SaveData(e, data);
                } else {
                    e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                }
            }
            case "2" -> {
                if (Boolean.parseBoolean(data[10])) {
                    data[11] = "2";
                    e.getChannel().sendMessage("You have activated Plan 2 - Half Security").queue();
                    new SaveData(e, data);
                } else {
                    e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                }
            }
            case "0" -> {
                data[11] = "0";
                e.getChannel().sendMessage("You have deactivated all insurance.  Not a good idea...").queue();
                new SaveData(e, data);
            }
            case "rice" -> {
                if(Long.parseLong(data[51]) <= 0) {
                    e.getChannel().sendMessage("You scourge your pantry but find no rice.  Then you remember you don't have any more.").queue();
                } else {
                    data[51] = String.valueOf(Long.parseLong(data[51]) - 1);
                    data[0] = String.valueOf(violins + income);
                    data[75] = String.valueOf(violinsEarned);
                    new SaveData(e, data);
                    e.getChannel().sendMessage("You ate rice, which turned out to be Blessed Rice.  The God of Rice gave you " + income + ":violin:").queue();
                }
            }
            case "tea" -> {
                if(Long.parseLong(data[62]) <= 0) {
                    e.getChannel().sendMessage("You scourge your pantry but find no more bubble tea.  Then you remember you don't have any more.").queue();
                } else {
                    data[62] = String.valueOf(Long.parseLong(data[62]) - 1);
                    violins += income * 4;
                    violinsEarned += income * 4;
                    e.getChannel().sendMessage("You drank some Bubble Tea.  Brett and Eddy approved and gave you " + income * 4 + ":violin:").queue();
                    data[0] = String.valueOf(violins);
                    data[75] = String.valueOf(violinsEarned);
                    new SaveData(e, data);
                }
            }
            case "blessing" -> {
                if(Long.parseLong(data[63]) <= 0) {
                    e.getChannel().sendMessage("You already used all your blessings, run more commands to get back into Ling Ling's good graces!").queue();
                } else {
                    data[63] = String.valueOf(Long.parseLong(data[63]) - 1);
                    violins += income * 24;
                    e.getChannel().sendMessage("Ling Ling blessed you and you received " + income * 24 + ":violin:").queue();
                    Random random = new Random();
                    double num = random.nextDouble();
                    if (num > 0.5) {
                        data[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                        data[52] = String.valueOf(Long.parseLong(data[52]) + 1);
                        e.getChannel().sendMessage(":trophy: Your performance won third place in the Ling Ling Competition.  You walked away with 1:military_medal: and a third place trophy :third_place:").queue();
                    } else if (num > 0.2) {
                        data[55] = String.valueOf(Long.parseLong(data[55]) + 2);
                        data[53] = String.valueOf(Long.parseLong(data[53]) + 1);
                        e.getChannel().sendMessage(":trophy: Your performance won second place.  You walked away with 2:military_medal: and a second place trophy :second_place:").queue();
                    } else {
                        data[55] = String.valueOf(Long.parseLong(data[55]) + 3);
                        data[54] = String.valueOf(Long.parseLong(data[54]) + 1);
                        e.getChannel().sendMessage(":trophy: Your performance won first place.  Congratulations!  You walked away with **3**:military_medal: and a FIRST place trophy :first_place:").queue();
                    }
                    violins += income * 24;
                    violinsEarned += income * 24;
                    String[] newData = CheckMedals.Medals(e, data);
                    newData[0] = String.valueOf(violins);
                    newData[75] = String.valueOf(violinsEarned);
                    new SaveData(e, newData);
                }
            }
            default -> e.getChannel().sendMessage("You can't use something that doesn't exist, that doesn't make sense.").queue();
        }
    }
}
