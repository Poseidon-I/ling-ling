package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.Objects;

public class Give {
    public Give(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        String id = message[1];
        long add;
        try {
            add = Long.parseLong(message[3]);
        } catch (Exception exception) {
            e.getChannel().sendMessage("You must specify which type of item to give, and the amount given must be a number.").queue();
            throw new IllegalArgumentException();
        }
        long newValue = -1;
        String userData;
        int index = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt"));
            userData = reader.readLine();
            reader.close();
        } catch (Exception exception) {
            e.getChannel().sendMessage("This user save doesn't exist!").queue();
            throw new IllegalArgumentException();
        }
        switch (message[2]) {
            case "violin" -> {
                index = 0;
                newValue = Long.parseLong(userData.split(" ")[0]) + add;
                e.getChannel().sendMessage("Successfully gave " + add + ":violin: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + ".  New amount: " + newValue).queue();
            }
            case "rice" -> {
                index = 51;
                newValue = Long.parseLong(userData.split(" ")[51]) + add;
                e.getChannel().sendMessage("Successfully gave " + add + ":rice: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + ".  New amount: " + newValue).queue();
            }
            case "medal" -> {
                index = 55;
                newValue = Long.parseLong(userData.split(" ")[55]) + add;
                e.getChannel().sendMessage("Successfully gave " + add + ":military_medal: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + ".  New amount: " + newValue).queue();
            }
            case "tea" -> {
                index = 62;
                newValue = Long.parseLong(userData.split(" ")[62]) + add;
                e.getChannel().sendMessage("Successfully gave " + add + ":bubble_tea: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + ".  New amount: " + newValue).queue();
            }
            case "blessing" -> {
                index = 63;
                newValue = Long.parseLong(userData.split(" ")[63]) + add;
                e.getChannel().sendMessage("Successfully gave " + add + ":angel: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + ".  New amount: " + newValue).queue();
            }
            default -> e.getChannel().sendMessage("Invalid currency/item.  Valid items: `violin` `rice` `medal` `tea` `blessing`").queue();
        }
        StringBuilder newData = new StringBuilder();
        for (int i = 0; i < userData.split(" ").length; i++) {
            if(i == index) {
                newData.append(newValue).append(" ");
            } else {
                newData.append(userData.split(" ")[i]).append(" ");
            }
        }
        newData.deleteCharAt(newData.length() - 1);
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
            writer.print(newData);
            writer.close();
        } catch (Exception exception) {
            //nothing here lol
        }
    }
}