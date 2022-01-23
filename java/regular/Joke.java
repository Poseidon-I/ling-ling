package regular;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class Joke {
	public Joke(MessageReceivedEvent e) {
		Random random = new Random();
		int i = random.nextInt(23);
		switch(i) {
			case 0 -> e.getMessage().reply("Q: What do a viola and a lawsuit have in common?\nA: ||Everyone is happy when the case is closed.||").mentionRepliedUser(false).queue();
			case 1 -> e.getMessage().reply("Q: What do you get when a viola gets run over by a car?\nA:|| Peace||").mentionRepliedUser(false).queue();
			case 2 -> e.getMessage().reply("Q: What's the difference between a violin and a viola?\nA1: ||A viola burns longer.||\nA2: ||You can tune a violin.||").mentionRepliedUser(false).queue();
			case 3 -> e.getMessage().reply("Q: What's the definition of a minor second?\nA: ||Two violists playing in unison.||").mentionRepliedUser(false).queue();
			case 4 -> e.getMessage().reply("Q: How was the canon invented?\nA: ||Two violists were trying to play the same passage together.||").mentionRepliedUser(false).queue();
			case 5 -> e.getMessage().reply("Q: How can you tell when a violist is playing out of tune?\nA: ||The bow is moving.||").mentionRepliedUser(false).queue();
			case 6 -> e.getMessage().reply("Q: What's the difference between a washing machine and a violist?\nA: ||Vibrato.||").mentionRepliedUser(false).queue();
			case 7 -> e.getMessage().reply("Q: How do you get a violin to sound like a viola?\nA: ||Play in the low register with a lot of wrong notes.||").mentionRepliedUser(false).queue();
			case 8 -> e.getMessage().reply("Q: What is the range of a viola?\nA: ||As far as you can kick it.||").mentionRepliedUser(false).queue();
			case 9 -> e.getMessage().reply("A violist and a cellist were standing on a sinking ship. \"Help!\" cried the cellist, \"I can't swim!\"\n\"Don't worry,\" said the violist, \"||Just fake it.||\"").mentionRepliedUser(false).queue();
			case 10 -> e.getMessage().reply("Q: What's the difference between the first and last desk of the viola section?\nA: ||About half a bar.||").mentionRepliedUser(false).queue();
			case 11 -> e.getMessage().reply("Q: How do you get two violists to play in tune with each other?\nA: ||Ask one of them to leave.||").mentionRepliedUser(false).queue();
			case 12 -> e.getMessage().reply("A group of terrorists hijacked a plane full of violists. They called down to ground control with their list of demands and added that if their demands weren't met, they would ||release one violist every hour.||").mentionRepliedUser(false).queue();
			case 13 -> e.getMessage().reply("Q: What's the difference between a viola and a trampoline?\nA: ||You take your shoes off to jump on a trampoline.||").mentionRepliedUser(false).queue();
			case 14 -> e.getMessage().reply("Q: What's the difference between a viola and an onion?\nA: ||No one cries when you cut up a viola.||").mentionRepliedUser(false).queue();
			case 15 -> e.getMessage().reply("Q: Anything\nA: Viola.\n||*intense wheezing*||").mentionRepliedUser(false).queue();
			case 16 -> e.getMessage().reply("Q: What's the difference between a pizza and a violist? \nA: ||A pizza can feed a family of four.||").mentionRepliedUser(false).queue();
			case 17 -> e.getMessage().reply("Q: Why don't violists play hide and seek? \nA: ||Because no one would look for them.||").mentionRepliedUser(false).queue();
			case 18 -> e.getMessage().reply("Q : Why bAss and not viola?\nA: ||Because viola isn't badAss.||").mentionRepliedUser(false).queue();
			case 19 -> e.getMessage().reply("Q: Did you hear about the violist who played in tune?\nA: ||Neither did I.||").mentionRepliedUser(false).queue();
			case 20 -> e.getMessage().reply("Q: How do you get a violin to sound like a viola?\nA1: ||Sit in the back and don't play.||\nA2: ||Play in the front with a lot of wrong notes.||").mentionRepliedUser(false).queue();
			case 21 -> e.getMessage().reply("Q: Why is viola illegal?\nA: ||It viola-ted the Terms of Service of the musician world.||").mentionRepliedUser(false).queue();
			default -> e.getMessage().reply("Q: How make someone practice the viola?\nA: ||Tell them a violinist is getting better than them.||").mentionRepliedUser(false).queue();
		}
	}
}
