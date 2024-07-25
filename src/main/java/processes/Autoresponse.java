package processes;

import eventListeners.GenericDiscordEvent;

import java.util.Collections;
import java.util.List;
import java.util.Random;
// BEETHOVEN-ONLY CLASS

public class Autoresponse {
    public static void autoresponse(GenericDiscordEvent e) {
        String fullMessage = e.getMessage().getContentRaw().toLowerCase();
        List<String> array2 = Collections.singletonList(fullMessage);
        Random random = new Random();
        if(fullMessage.equals("no u")) {
            e.sendMessage("no u");
        }
        if(!e.getChannel().getId().equals("763099851550097408") && !e.getChannel().getId().equals("763196633545441291")) {
            if(fullMessage.contains("viola bad")) {
                e.sendMessage(":clap::clap::clap:");
            } else if(array2.contains("viola")) {
                if(random.nextDouble() < 0.5) {
                    e.sendMessage("Woah there that's a banned instrument.  Carry on and you'll find yourself in some serious trouble.");
                } else {
                    e.sendMessage("At least better than Davie504 and his bAss");
                }
            } else if(fullMessage.contains("pls kill")) {
                if(fullMessage.contains("strad") || fullMessage.contains("dev") || fullMessage.contains("jacqueline")
                        || fullMessage.contains("619989388109152256") || fullMessage.contains("488487157372157962")) {
                    e.sendMessage("**Imagine trying to kill the devs smh**");
                }
            } else if(array2.contains("practice")) {
                e.sendMessage("**SPELL YOUR WORDS CORRECTLY**\nThe proper way to spell that word is __practi**s**e__, so do it or else.");
            } else if(array2.contains("jazz") && !e.getChannel().getId().equals("740085422453162007")) {
                e.sendMessage("Jazz is a sacrilegious style where people deliberately play wrong notes and it should be avoided at all costs.");
            } else if(fullMessage.contains("pls beg")) {
                e.sendMessage("oh look at <@" + e.getAuthor().getId() + "> begging for coins");
            } else if(fullMessage.contains("pls settings passive true")) {
                e.sendMessage("hey everyone come here and look at " + e.getAuthor().getEffectiveName() + " being such a wimp");
            } else if(array2.contains("ritard")) {
                e.sendMessage("Unless you are referring to the musical term \"ritardando\" then you are in danger");
            } else if(fullMessage.contains("ben lee")) {
                e.sendMessage("Don't you DARE utter the name of Sacrilegious Boi");
            } else if(fullMessage.contains("flight of the bumblebee")) {
                e.sendMessage("That piece is not allowed here unless you're playing at the intended tempo.  You *are* playing it at the intended tempo right?");
            } else if(array2.contains("song")) {
                e.sendMessage("**IT'S A PIECE NOT A SONG!!!!!**");
            } else if(fullMessage.contains("vov dylan")) {
                e.sendMessage("Don't you DARE utter the name of Sacrilegious boi #2");
            } else if(fullMessage.contains("pop music")) {
                e.sendMessage("Pop music is shitty, don't listen to it.  Instead try my more wholesome Symphony No. 9 in D Minor.");
            } else if(fullMessage.contains("canon in d")) {
                e.sendMessage("Why don't you try my less overplayed Symphony No. 3 in E-Flat Major?");
            } else if(array2.contains("sax") || array2.contains("saxophone")) {
                e.sendMessage("Saxophone isn't an orchestral instrument, why don't you try playing the triangle as a healthy alternative.");
            } else if(array2.contains("band")) {
                e.sendMessage("Band is a subset of the full orchestra (and too loud as well) now get out of my face if you don't understand this simple fact.");
            } else if(array2.contains("fortnite")) {
                e.sendMessage("**Aiyooooo!  How many times do I need to tell you??  __Ling Ling is practising FORTY hours a day!  DO YOU THINK LING LING HAS TIME TO PLAY *FORTNITE*???  " +
                        "*HUH?!?!?!*  LING LING ALREADY WIN TWENTY COMPETITION, YOU PLAY *FORTNITE*?  *HUUUUUH?!?!?!?!?!?!?!?!?!?!*__**");
            } else if(array2.contains("val") || fullMessage.contains("valorant")) {
                e.sendMessage("**Aiyooooo!  How many times do I need to tell you??  __Ling Ling is practising FORTY hours a day!  DO YOU THINK LING LING HAS TIME TO PLAY *VALORANT*???  " +
                        "*HUH?!?!?!*  LING LING ALREADY WIN TWENTY COMPETITION, YOU PLAY *VALORANT*?  *HUUUUUH?!?!?!?!?!?!?!?!?!?!*__**");
            } else if(array2.contains("wheat")) {
                e.sendMessage("Rice is the superior crop, don't you dare start about wheat");
            } else if(fullMessage.contains("there are only 24 hours in a day")) {
                e.sendMessage("stop making excuses and go practise");
            } else if(array2.contains("davie") || array2.contains("davie504")) {
                e.sendMessage("go watch the TRUE superior channel at https://www.youtube.com/TwoSetViolin");
            } else if(array2.contains("bass") && !fullMessage.contains("double bass") && !fullMessage.contains("string bass") && !fullMessage.contains("upright bass")) {
                e.sendMessage("the only bAss that is allowed here is the double bass, any other form is SACRILEGIOUS");
            } else if(array2.contains("hate")) {
                e.sendMessage("Hate is a strong word!  Try using a healthier alternative like \"don't like\"");
            } else if(fullMessage.contains("light mode")) {
                e.sendMessage("light mode is disgusting, go use dark mode");
            }
        }
    }
}