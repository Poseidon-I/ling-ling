package regular;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Kill {
	public Kill(GuildMessageReceivedEvent e, String targetPing) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		e.getChannel().sendMessage(e.getMessage().getContentRaw());
		StringBuilder targetPingBuilder;
		int i;
		if(e.getMessage().getMentionedUsers().size() == 0) {
			i = 1;
			targetPingBuilder = new StringBuilder();
		} else {
			i = 2;
			targetPingBuilder = new StringBuilder(targetPing).append(" ");
		}
		while(i < message.length) {
			targetPingBuilder.append(message[i]).append(" ");
			i++;
		}
		targetPing = targetPingBuilder.deleteCharAt(targetPingBuilder.length() - 1).toString();
		if(e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&")) {
			e.getChannel().sendMessage("Nice try but no").queue();
		} else if(targetPing.equalsIgnoreCase("i") || targetPing.toLowerCase().contains("ling ling") || targetPing.toLowerCase().contains("lingling") || targetPing.contains("733409243222507670")) {
			e.getChannel().sendMessage("Nice try but you cannot kill me").queue();
		} else if(targetPing.toLowerCase().contains("strad") || targetPing.toLowerCase().contains("dev") || targetPing.toLowerCase().contains("jacqueline") || targetPing.contains("619989388109152256") || targetPing.contains("488487157372157962")) {
			e.getChannel().sendMessage("Imagine trying to kill the devs smh").queue();
		} else {
			Random random = new Random();
			i = random.nextInt(73);
			if(i == 0) {
				e.getChannel().sendMessage(targetPing + " thought it was a good idea to play the sousaphone after eating chili pepper").queue();
			} else if(i == 1) {
				e.getChannel().sendMessage(targetPing + "'s ears were blasted apart with a piccolo playing 10000 Hz at fortissimo").queue();
			} else if(i == 2) {
				e.getChannel().sendMessage("Ling Ling deemed " + targetPing + " unworthy of violin so he (she?) forced " + targetPing + " to play the viola.  Their ego was permanently damaged.").queue();
			} else if(i == 3) {
				e.getChannel().sendMessage("Ling Ling is a benevolent violinist god so nice try but no").queue();
			} else if(i == 4) {
				e.getChannel().sendMessage(targetPing + " was blinded because they used light mode").queue();
			} else if(i == 5) {
				e.getChannel().sendMessage(e.getAuthor().getName() + " started punching " + targetPing + ".").queue();
			} else if(i == 6) {
				e.getChannel().sendMessage(e.getAuthor().getName() + " somehow got " + targetPing + " into their Minecraft world and then " + targetPing + " was dumb enough to light TNT.").queue();
			} else if(i == 7) {
				e.getChannel().sendMessage(targetPing + " stuffed too much chili pepper down their throat and the results were rather explosive").queue();
			} else if(i == 8) {
				e.getChannel().sendMessage("A bomb detonated over " + targetPing + "'s head").queue();
			} else if(i == 9) {
				e.getChannel().sendMessage(targetPing + " destroyed Jacqueline's Stradivarius so Jacqueline destroyed " + targetPing).queue();
			} else if(i == 10) {
				e.getChannel().sendMessage(targetPing + " tripped over a tripwire and fell into the Void").queue();
			} else if(i == 11) {
				e.getChannel().sendMessage(targetPing + " went to the zoo and got trampled by 20 elephants").queue();
			} else if(i == 12) {
				e.getChannel().sendMessage(e.getAuthor().getName() + " played a very high harmonic on their violin and exploded " + targetPing + "'s eardrums.").queue();
			} else if(i == 13) {
				e.getChannel().sendMessage(targetPing + " was run over by " + e.getAuthor().getName() + " because " + targetPing + " was crossing the street while playing a sousaphone.").queue();
			} else if(i == 14) {
				e.getChannel().sendMessage(targetPing + " fell off a cliff because they were not paying attention to where they were going.  Reason?  Using Simply Piano app (sacrilegious)").queue();
			} else if(i == 15) {
				e.getChannel().sendMessage(targetPing + " was whacked on the head with a viola for displeasing the conductor.").queue();
			} else if(i == 16) {
				e.getChannel().sendMessage(targetPing + " tried to steal 2000 pounds worth of gold from " + e.getAuthor().getName() + " but they were caught and ended up crushing themselves under the gold.").queue();
			} else if(i == 17) {
				e.getChannel().sendMessage(targetPing + " was attacked by " + e.getAuthor().getName() + ".  It was that simple").queue();
			} else if(i == 18) {
				e.getChannel().sendMessage("Why would you want to wound " + targetPing + " they haven't done anything wrong").queue();
			} else if(i == 19) {
				e.getChannel().sendMessage(targetPing + " tripped over " + e.getAuthor().getName() + "'s foot while reading music and walking").queue();
			} else if(i == 20) {
				e.getChannel().sendMessage(e.getAuthor().getName() + " clobbers " + targetPing + " with a clarinet because " + targetPing + " was being dumb").queue();
			} else if(i == 21) {
				e.getChannel().sendMessage(e.getAuthor().getName() + " slapped " + targetPing + " for playing the VIOLA").queue();
			} else if(i == 22) {
				e.getChannel().sendMessage(targetPing + " was playing on CHIMPS mode and lost due to a Camo Regrow Red on Round 100.  The rest is obvious.").queue();
			} else if(i == 23) {
				e.getChannel().sendMessage(targetPing + " displeased the Avatar of the Vengeful Monkey, who decided to crush " + targetPing + " with 999 BADs.").queue();
			} else if(i == 24) {
				e.getChannel().sendMessage(e.getAuthor().getName() + " sacrificed all of " + targetPing + "'s towers to a Sun Temple, which was then sold, causing " + targetPing + " to lose.").queue();
			} else if(i == 25) {
				e.getChannel().sendMessage(targetPing + " abused bugs on Tacoshack for two years.  Cole just found out and their $500 000/hour Shack was deleted.").queue();
			} else if(i == 26) {
				e.getChannel().sendMessage(targetPing + " decided to fight the Ender Dragon with nothing but their fists.  They were thrown into the Void.").queue();
			} else if(i == 27) {
				e.getChannel().sendMessage(targetPing + " hit a Zombified Piglin in an area swarming with them.  They had only an axe and got overwhelmed.").queue();
			} else if(i == 28) {
				e.getChannel().sendMessage(targetPing + " ventured into an Abandoned Mineshaft and came across 3 Dungeons and a Cave Spider spawner.  They didn't have torches and were dumb enough not to break them.").queue();
			} else if(i == 29) {
				e.getChannel().sendMessage(targetPing + " was killed by Ling Ling because they thought Axes were better than Swords.").queue();
			} else if(i == 30) {
				e.getChannel().sendMessage(targetPing + " was playing with lava.").queue();
			} else if(i == 31) {
				e.getChannel().sendMessage(targetPing + " fought Ling Ling.  They lost to his (her?) Sharpness 32767 Sword.").queue();
			} else if(i == 32) {
				e.getChannel().sendMessage(targetPing + " attacked Beethoven, who had Throns 32767 armor.").queue();
			} else if(i == 33) {
				e.getChannel().sendMessage(targetPing + " was squashed by a falling Anvil whilst attempting to escape Herobrine.").queue();
			} else if(i == 34) {
				e.getChannel().sendMessage(targetPing + " fell down a cliff.").queue();
			} else if(i == 35) {
				e.getChannel().sendMessage(targetPing + " flew into unloaded chunks and died for no reason.").queue();
			} else if(i == 36) {
				e.getChannel().sendMessage(targetPing + " thought Creepers were cute and invited one into their house.").queue();
			} else if(i == 37) {
				e.getChannel().sendMessage(targetPing + " attacked MsCandy44 and got the ban hammer.").queue();
			} else if(i == 38) {
				e.getChannel().sendMessage(targetPing + " killed Phil.  MsCandy44 then killed " + targetPing).queue();
			} else if(i == 39) {
				e.getChannel().sendMessage(targetPing + "'s violining skills are so bad even the violists were allowed to laugh at them").queue();
			} else if(i == 40) {
				e.getChannel().sendMessage(targetPing + " tried conducting an orchestra but their skills were so shitty not even Ling Ling could figure out what was going on").queue();
			} else if(i == 41) {
				e.getChannel().sendMessage(targetPing + " auditioned for Seattle Symphony but the audition panel just laughed at their horrible intonation").queue();
			} else if(i == 42) {
				e.getChannel().sendMessage(targetPing + " is so bad that they were forced to play the viola").queue();
			} else if(i == 43) {
				e.getChannel().sendMessage(targetPing + " played out of tune in front of the Berlin Philharmonic and they were ridiculed").queue();
			} else if(i == 44) {
				e.getChannel().sendMessage("During a music test " + targetPing + " was called out for cheating").queue();
			} else if(i == 45) {
				e.getChannel().sendMessage(targetPing + " abused a violin and Ling Ling came and punished them for their smol brane").queue();
			} else if(i == 46) {
				e.getChannel().sendMessage(targetPing + " ruined the annual orchestra concert and was ridiculed for playing a wrong note.").queue();
			} else if(i == 47) {
				e.getChannel().sendMessage(targetPing + " played the violin out of tune.  Ling Ling harassed them for the sin.").queue();
			} else if(i == 48) {
				e.getChannel().sendMessage(targetPing + " lost Easy mode on Round 1.  How embarassing.").queue();
			} else if(i == 49) {
				e.getChannel().sendMessage(targetPing + " was so bad at Bloons that even super slow Red Bloons could get by their poorly planned defense.").queue();
			} else if(i == 50) {
				e.getChannel().sendMessage(targetPing + " is so bad at minecraft they managed to die in one second in Peaceful Mode.  On a Superflat world.").queue();
			} else if(i == 51) {
				e.getChannel().sendMessage(targetPing + " tried playing their piccolo solo but it broke so many eardrums that the conductor threw them off the stage into the audience headfirst.  Everyone laughed.").queue();
			} else if(i == 52) {
				e.getChannel().sendMessage(targetPing + " was shot by " + e.getAuthor().getName() + " using [ 15 Notes a Second woB-X ].").queue();
			} else if(i == 53) {
				e.getChannel().sendMessage(targetPing + " walked into a cactus whilst trying to escape Chicken.").queue();
			} else if(i == 54) {
				e.getChannel().sendMessage(targetPing + " experienced Kinetic Energy.").queue();
			} else if(i == 55) {
				e.getChannel().sendMessage(targetPing + " died.  While flying over unloaded chunks.").queue();
			} else if(i == 56) {
				e.getChannel().sendMessage(targetPing + " was blown up by " + e.getAuthor().getName() + " using Ling Ling's Wrath.").queue();
			} else if(i == 57) {
				e.getChannel().sendMessage(targetPing + " was killed by Intentional Game Design [MCPE-28723]").queue();
			} else if(i == 58) {
				e.getChannel().sendMessage(targetPing + " was squashed by a falling anvil.").queue();
			} else if(i == 59) {
				e.getChannel().sendMessage(targetPing + " went off with a bang due to a firework fired from [ 15 Notes a Second woB-X ] by " + e.getAuthor().getName()).queue();
			} else if(i == 60) {
				e.getChannel().sendMessage(targetPing + " tried to swim in lava.").queue();
			} else if(i == 61) {
				e.getChannel().sendMessage(targetPing + " was struck by lightning.").queue();
			} else if(i == 62) {
				e.getChannel().sendMessage(targetPing + " discovered the floor was lava.").queue();
			} else if(i == 63) {
				e.getChannel().sendMessage(targetPing + " walked into danger zone due to " + e.getAuthor().getName()).queue();
			} else if(i == 64) {
				e.getChannel().sendMessage(targetPing + " was squashed by " + e.getAuthor().getName()).queue();
			} else if(i == 65) {
				e.getChannel().sendMessage(targetPing + " was poked to death by a sweet berry bush whilst trying to escape " + e.getAuthor().getName()).queue();
			} else if(i == 66) {
				e.getChannel().sendMessage(targetPing + " was killed by [ 15 Notes a Second Chestplate ] trying to hurt " + e.getAuthor().getName()).queue();
			} else if(i == 67) {
				e.getChannel().sendMessage(targetPing + " was impaled by " + e.getAuthor().getName() + " with [ Zeus's Trident ]").queue();
			} else if(i == 68) {
				e.getChannel().sendMessage(targetPing + " didn't want to live in the same world as " + e.getAuthor().getName()).queue();
			} else if(i == 69) {
				e.getChannel().sendMessage(targetPing + " was roasted in dragon breath.").queue();
			} else if(i == 70) {
				e.getChannel().sendMessage(targetPing + " was killed by even more magic.").queue();
			} else if(i == 71) {
				e.getChannel().sendMessage(targetPing + " was too soft for this world (" + e.getAuthor().getName() + " helped)").queue();
			} else {
				e.getChannel().sendMessage(targetPing + " stepped on a landmine and suffered major injuries.").queue();
			}
		}
	}
}
