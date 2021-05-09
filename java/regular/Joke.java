package regular;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Joke {
    public Joke(GuildMessageReceivedEvent e) {
        Random random = new Random();
        int i = random.nextInt(23);
        if (i == 0) {
            e.getChannel().sendMessage("Q: What do a viola and a lawsuit have in common?\nA: ||Everyone is happy when the case is closed.||").queue();
        } else if (i == 1) {
            e.getChannel().sendMessage("Q: What do you get when a viola gets run over by a car?\nA:|| Peace||").queue();
        } else if (i == 2) {
            e.getChannel().sendMessage("Q: What's the difference between a violin and a viola?\nA1: ||A viola burns longer.||\nA2: ||You can tune a violin.||").queue();
        } else if (i == 3) {
            e.getChannel().sendMessage("Q: What's the definition of a minor second?\nA: ||Two violists playing in unison.||").queue();
        } else if (i == 4) {
            e.getChannel().sendMessage("Q: How was the canon invented?\nA: ||Two violists were trying to play the same passage together.||").queue();
        } else if (i == 5) {
            e.getChannel().sendMessage("Q: How can you tell when a violist is playing out of tune?\nA: ||The bow is moving.||").queue();
        } else if (i == 6) {
            e.getChannel().sendMessage("Q: What's the difference between a washing machine and a violist?\nA: ||Vibrato.||").queue();
        } else if (i == 7) {
            e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA: ||Play in the low register with a lot of wrong notes.||").queue();
        } else if (i == 8) {
            e.getChannel().sendMessage("Q: What is the range of a viola?\nA: ||As far as you can kick it.||").queue();
        } else if (i == 9) {
            e.getChannel().sendMessage("A violist and a cellist were standing on a sinking ship. \"Help!\" cried the cellist, \"I can't swim!\"\n\"Don't worry,\" said the violist, \"||Just fake it.||\"").queue();
        } else if (i == 10) {
            e.getChannel().sendMessage("Q: What's the difference between the first and last desk of the viola section?\nA: ||About half a bar.||").queue();
        } else if (i == 11) {
            e.getChannel().sendMessage("Q: How do you get two violists to play in tune with each other?\nA: ||Ask one of them to leave.||").queue();
        } else if (i == 12) {
            e.getChannel().sendMessage("A group of terrorists hijacked a plane full of violists. They called down to ground control with their list of demands and added that if their demands weren't met, they would ||release one violist every hour.||").queue();
        } else if (i == 13) {
            e.getChannel().sendMessage("Q: What's the difference between a viola and a trampoline?\nA: ||You take your shoes off to jump on a trampoline.||").queue();
        } else if (i == 14) {
            e.getChannel().sendMessage("Q: What's the difference between a viola and an onion?\nA: ||No one cries when you cut up a viola.||").queue();
        } else if (i == 15) {
            e.getChannel().sendMessage("Q: Anything\nA: Viola.\n||*intense wheezing*||").queue();
        } else if (i == 16) {
            e.getChannel().sendMessage("Q: What's the difference between a pizza and a violist? \nA: ||A pizza can feed a family of four.||").queue();
        } else if (i == 17) {
            e.getChannel().sendMessage("Q: Why don't violists play hide and seek? \nA: ||Because no one would look for them.||").queue();
        } else if (i == 18) {
            e.getChannel().sendMessage("Q : Why bAss and not viola?\nA: ||Because viola isn't badAss.||").queue();
        } else if (i == 19) {
            e.getChannel().sendMessage("Q: Did you hear about the violist who played in tune?\nA: ||Neither did I.||").queue();
        } else if (i == 20) {
            e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA1: ||Sit in the back and don't play.||\nA2: ||Play in the front with a lot of wrong notes.||").queue();
        } else if (i == 21) {
            e.getChannel().sendMessage("Q: Why is viola illegal?\nA: ||It viola-ted the Terms of Service of the musician world.||").queue();
        } else {
            e.getChannel().sendMessage("Q: How make someone practice the viola?\nA: ||Tell them a violinist is getting better than them.||").queue();
        }
    }
}
