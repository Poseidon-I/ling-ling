package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Autoresponse extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        boolean isDev = false;
        if (e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612")) {
            isDev = true;
        }
        Random random = new Random();
        String message = e.getMessage().getContentRaw();
        message = message.toLowerCase();
        String[] array = message.split(" ");
        List<String> array2 = Arrays.asList(array);
        if (message.equals("no u") && !e.getAuthor().isBot()) {
            e.getChannel().sendMessage("no u").queue();
        }
        String[] data = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
            data = br.readLine().split(" ");
            br.close();
        } catch(Exception exception) {
            //nothing here lol
        }
        if(isDev) {
            switch (message) {
                case "hush bot" -> e.getChannel().sendMessage("okay master :(").queue();
                case "right bot?" -> e.getChannel().sendMessage("yes master").queue();
                case "bad bot" -> e.getChannel().sendMessage("sowwy master :cry:").queue();
            }
        }
        if(!e.getChannel().getId().equals("734697507153772604") && !e.getChannel().getId().equals("741709423860514867") && !e.getAuthor().isBot()) {
            assert data != null;
            if (data[0].equals("true")) {
                if (message.contains("viola bad")) {
                    e.getChannel().sendMessage(":clap::clap::clap:").queue();
                } else if (array2.contains("viola") && !e.getChannel().getId().equals("734697524631175168") && !e.getChannel().getId().equals("740085422453162007")) {
                    if (random.nextDouble() < 0.5) {
                        e.getChannel().sendMessage("Woah there that's a banned instrument.  Carry on and you'll find yourself in some serious trouble.").queue();
                    } else {
                        e.getChannel().sendMessage("At least better than davie504 and his bAss").queue();
                    }
                } else if (message.contains("go practice") || array2.contains("practicing")) {
                    e.getChannel().sendMessage("**SPELL YOUR WORDS CORRECTLY**\nThe proper way to spell that word is __practi**s**e__, so do it or else.").queue();
                } else if (array2.contains("jazz") && !e.getChannel().getId().equals("740085422453162007")) {
                    e.getChannel().sendMessage("Jazz is a sacrilegious style where people deliberately play wrong notes and it should be avoided at all costs.").queue();
                } else if (message.contains("pls beg")) {
                    e.getChannel().sendMessage("oh look at <@" + e.getAuthor().getId() + "> begging for coins").queue();
                } else if (message.contains("pls settings passive true")) {
                    e.getChannel().sendMessage("hey everyone come here and look at " + e.getAuthor().getName() + " being such a wimp").queue();
                } else if (array2.contains("ritard")) {
                    e.getChannel().sendMessage("Unless you are referring to the musical term \"ritardando\" then you are in danger").queue();
                } else if (message.contains("ben lee")) {
                    e.getChannel().sendMessage("Don't you DARE utter the name of Sacrilegious Boi").queue();
                } else if (message.contains("flight of the bumblebee") && !e.getChannel().getId().equals("740085422453162007")) {
                    e.getChannel().sendMessage("That piece is not allowed here unless you're playing at the intended tempo.  You *are* playing it at the intended tempo right?").queue();
                } else if (array2.contains("song")) {
                    e.getChannel().sendMessage("**IT'S A PIECE NOT A SONG!!!!!**").queue();
                } else if (message.contains("vov dylan")) {
                    e.getChannel().sendMessage("Don't you DARE utter the name of Sacrilegious boi #2").queue();
                } else if (message.contains("pop music") && !e.getChannel().getId().equals("740085422453162007")) {
                    e.getChannel().sendMessage("Pop music is shitty, don't listen to it.  Instead try the more wholesome Beethoven Symphony No. 9 in D Minor.").queue();
                } else if (message.contains("canon in d") && !e.getChannel().getId().equals("740085422453162007")) {
                    e.getChannel().sendMessage("Why don't you try the less overplayed Beethoven Symphony No. 3 in E-Flat Major?").queue();
                } else if (array2.contains("sax") && !e.getChannel().getId().equals("740085422453162007") || array2.contains("saxophone") && !e.getChannel().getId().equals("740085422453162007")) {
                    e.getChannel().sendMessage("Saxophone isn't an orchestral instrument, why don't you try playing the triangle as a healthy alternative.").queue();
                } else if (array2.contains("band") && !e.getChannel().getId().equals("740085422453162007")) {
                    e.getChannel().sendMessage("Band is a subset of the full orchestra (and too loud as well) now get out of my face if you don't understand this simple fact.").queue();
                } else if (array2.contains("fortnite")) {
                    e.getChannel().sendMessage("**Aiyooooo!  How many times do I need to tell you??  __Ling Ling is practising FORTY hours a day!  DO YOU THINK LING LING HAS TIME TO PLAY *FORTNITE*???  *HUH?!?!?!*  LING LING ALREADY WIN TWENTY COMPETITION, YOU PLAY *FORTNITE*?  *HUUUUUH?!?!?!?!?!?!?!?!?!?!*__**").queue();
                } else if (array2.contains("wheat")) {
                    e.getChannel().sendMessage("Rice is the superior crop, don't you dare start about wheat").queue();
                } else if (message.contains("there are only 24 hours in a day")) {
                    e.getChannel().sendMessage("stop making excuses and go practise").queue();
                } else if (array2.contains("davie") || array2.contains("davie504")) {
                    e.getChannel().sendMessage("go watch the TRUE superior channel at https://www.youtube.com/TwoSetViolin").queue();
                } else if (array2.contains("bass") && !e.getChannel().getId().equals("740085422453162007") && !message.contains("double bass") && !message.contains("string bass") && !message.contains("upright bass")) {
                    e.getChannel().sendMessage("the only bAss that is allowed here is the double bass, any other form is SACRILEGIOUS").queue();
                } else if (message.contains("light mode")) {
                    e.getChannel().sendMessage("light mode is disgusting, go use dark mode").queue();
                }
            }
        }
    }
}