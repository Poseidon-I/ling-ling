package regular;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class Kill {
	public Kill(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder targetBuilder = new StringBuilder();
		for(int i = 1; i < message.length; i++) {
			if(!message[i].contains("<@")) {
				targetBuilder.append(message[i]).append(" ");
			} else {
				e.getMessage().reply("Don't ping anybody, you idiot, go annoy yourself").mentionRepliedUser(false).queue();
				return;
			}
		}
		String target = targetBuilder.deleteCharAt(targetBuilder.length() - 1).toString();
		String author = e.getAuthor().getName();
		if(author.contains("@everyone") || author.contains("@here")) {
			e.getMessage().reply("imagine trying to abuse me to cause a mass ping, shame on you").mentionRepliedUser(false).queue();
		} else if(target.equalsIgnoreCase("i") || target.toLowerCase().contains("ling ling") || target.toLowerCase().contains("lingling") || target.contains("733409243222507670")) {
			e.getMessage().reply("Nice try but you cannot kill me").mentionRepliedUser(false).queue();
		} else if(target.toLowerCase().contains("strad") || target.toLowerCase().contains("dev") || target.toLowerCase().contains("jacqueline") || target.contains("619989388109152256") || target.contains("488487157372157962")) {
			e.getMessage().reply("Imagine trying to kill the devs smh").mentionRepliedUser(false).queue();
		} else {
			Random random = new Random();
			int i = random.nextInt(68);
			switch(i) {
				case 0 -> e.getMessage().reply("death.fell.accident.water").mentionRepliedUser(false).queue();
				case 1 -> e.getMessage().reply(target + "'s ears were blasted apart with a piccolo playing 10000 Hz at fortissimo.").mentionRepliedUser(false).queue();
				case 2 -> e.getMessage().reply("Ling Ling deemed " + target + " unworthy of violin so he (she?) forced " + target + " to play the viola.  Their ego was permanently damaged.").mentionRepliedUser(false).queue();
				case 3 -> e.getMessage().reply(target + " thought it was a good idea to play the sousaphone after eating chili pepper.").mentionRepliedUser(false).queue();
				case 4 -> e.getMessage().reply(target + " was blinded because they used light mode.").mentionRepliedUser(false).queue();
				case 5 -> e.getMessage().reply(author + " started punching " + target + ".").mentionRepliedUser(false).queue();
				case 6 -> e.getMessage().reply(author + " somehow got " + target + " into their Minecraft world and then " + target + " was dumb enough to light TNT.").mentionRepliedUser(false).queue();
				case 7 -> e.getMessage().reply(target + " stuffed too much chili pepper down their throat and the results were rather explosive.").mentionRepliedUser(false).queue();
				case 8 -> e.getMessage().reply("A bomb detonated over " + target + "'s head.").mentionRepliedUser(false).queue();
				case 9 -> e.getMessage().reply(target + " destroyed Jacqueline's Stradivarius so Jacqueline destroyed " + target).mentionRepliedUser(false).queue();
				case 10 -> e.getMessage().reply(target + " tripped over a tripwire and fell into the Void.").mentionRepliedUser(false).queue();
				case 11 -> e.getMessage().reply(target + " went to the zoo and got trampled by 20 elephants.").mentionRepliedUser(false).queue();
				case 12 -> e.getMessage().reply(author + " played a very high harmonic on their violin and exploded " + target + "'s eardrums.").mentionRepliedUser(false).queue();
				case 13 -> e.getMessage().reply(target + " was run over by " + author + " because " + target + " was crossing the street while playing a sousaphone.").mentionRepliedUser(false).queue();
				case 14 -> e.getMessage().reply(target + " fell off a cliff because they were not paying attention to where they were going.  Reason?  Using Simply Piano app (sacrilegious).").mentionRepliedUser(false).queue();
				case 15 -> e.getMessage().reply(target + " was brutally murdered with a viola for displeasing the conductor.").mentionRepliedUser(false).queue();
				case 16 -> e.getMessage().reply(target + " tried to steal 2000 pounds worth of gold from " + author + " but they were caught and ended up crushing themselves under the gold.").mentionRepliedUser(false).queue();
				case 17 -> e.getMessage().reply(target + " was sneak-attacked by " + author + ".").mentionRepliedUser(false).queue();
				case 18 -> e.getMessage().reply(target + " tripped over " + author + "'s foot while reading music and walking.").mentionRepliedUser(false).queue();
				case 19 -> e.getMessage().reply(author + " clobbers " + target + " with a clarinet because " + target + " was being dumb.").mentionRepliedUser(false).queue();
				case 20 -> e.getMessage().reply(target + " abused bugs on Tacoshack for two years.  Cole just found out and their $500 000/hour Shack was deleted.").mentionRepliedUser(false).queue();
				case 21 -> e.getMessage().reply(target + " decided to fight the Ender Dragon with nothing but their fists.").mentionRepliedUser(false).queue();
				case 22 -> e.getMessage().reply(target + " hit a Zombified Piglin in an area swarming with them.  They had only an axe and got overwhelmed.").mentionRepliedUser(false).queue();
				case 23 -> e.getMessage().reply(target + " ventured into an Abandoned Mineshaft and came across 3 Dungeons and a Cave Spider spawner.  They didn't have torches and were dumb enough not to break them.").mentionRepliedUser(false).queue();
				case 24 -> e.getMessage().reply(target + " was killed by Ling Ling because they thought Axes were better than Swords.").mentionRepliedUser(false).queue();
				case 25 -> e.getMessage().reply(target + " was playing with lava.").mentionRepliedUser(false).queue();
				case 26 -> e.getMessage().reply(target + " fought Ling Ling.  They lost to his (her?) Sharpness 32767 Sword.").mentionRepliedUser(false).queue();
				case 27 -> e.getMessage().reply(target + " attacked Beethoven, who had Throns 32767 armor.").mentionRepliedUser(false).queue();
				case 28 -> e.getMessage().reply(target + " was squashed by a falling Anvil whilst attempting to escape Herobrine").mentionRepliedUser(false).queue();
				case 29 -> e.getMessage().reply(target + " fell down a cliff.").mentionRepliedUser(false).queue();
				case 30 -> e.getMessage().reply(target + " flew into unloaded chunks and died for no reason.").mentionRepliedUser(false).queue();
				case 31 -> e.getMessage().reply(target + " thought Creepers were cute and invited one into their house.").mentionRepliedUser(false).queue();
				case 32 -> e.getMessage().reply(target + " attacked an Admin and got the ban hammer.").mentionRepliedUser(false).queue();
				case 33 -> e.getMessage().reply(target + " tried killing an iron golem.  The iron golem got the upper hand").mentionRepliedUser(false).queue();
				case 34 -> e.getMessage().reply(target + " played out of tune in front of the Berlin Philharmonic and they were ridiculed to death by the Publik.").mentionRepliedUser(false).queue();
				case 35 -> e.getMessage().reply("During a music test " + target + " was called out for cheating.").mentionRepliedUser(false).queue();
				case 36 -> e.getMessage().reply(target + " is so bad at Minecraft they managed to die in one second in Peaceful Mode.  On a Superflat world.  Full of water.").mentionRepliedUser(false).queue();
				case 37 -> e.getMessage().reply(target + " tried playing their piccolo solo but it broke so many eardrums that the conductor threw them off the stage into the audience headfirst.  Everyone laughed.").mentionRepliedUser(false).queue();
				case 38 -> e.getMessage().reply(target + " was shot by " + author + " using [ 15 Notes a Second woB-X ]").mentionRepliedUser(false).queue();
				case 39 -> e.getMessage().reply(target + " walked into a cactus whilst trying to escape " + author).mentionRepliedUser(false).queue();
				case 40 -> e.getMessage().reply(target + " experienced Kinetic Energy").mentionRepliedUser(false).queue();
				case 41 -> e.getMessage().reply(target + " died.  While flying over unloaded chunks.").mentionRepliedUser(false).queue();
				case 42 -> e.getMessage().reply(target + " was blown up by " + author + " using Ling Ling's Wrath").mentionRepliedUser(false).queue();
				case 43 -> e.getMessage().reply(target + " was killed by Intentional Game Design [MCPE-28723]").mentionRepliedUser(false).queue();
				case 44 -> e.getMessage().reply(target + " was squashed by a falling anvil").mentionRepliedUser(false).queue();
				case 45 -> e.getMessage().reply(target + " went off with a bang due to a firework fired from [ 15 Notes a Second woB-X ] by " + author).mentionRepliedUser(false).queue();
				case 46 -> e.getMessage().reply(target + " tried to swim in lava").mentionRepliedUser(false).queue();
				case 47 -> e.getMessage().reply(target + " was struck by lightning").mentionRepliedUser(false).queue();
				case 48 -> e.getMessage().reply(target + " discovered the floor was lava").mentionRepliedUser(false).queue();
				case 49 -> e.getMessage().reply(target + " walked into danger zone due to " + author).mentionRepliedUser(false).queue();
				case 50 -> e.getMessage().reply(target + " was squashed by " + author).mentionRepliedUser(false).queue();
				case 51 -> e.getMessage().reply(target + " was poked to death by a sweet berry bush whilst trying to escape " + author).mentionRepliedUser(false).queue();
				case 52 -> e.getMessage().reply(target + " was killed by [ 15 Notes a Second Chestplate ] trying to hurt " + author).mentionRepliedUser(false).queue();
				case 53 -> e.getMessage().reply(target + " was impaled by " + author + " with [ Zeus's Trident ]").mentionRepliedUser(false).queue();
				case 54 -> e.getMessage().reply(target + " didn't want to live in the same world as " + author).mentionRepliedUser(false).queue();
				case 55 -> e.getMessage().reply(target + " was roasted in dragon breath").mentionRepliedUser(false).queue();
				case 56 -> e.getMessage().reply(target + " was killed by even more magic").mentionRepliedUser(false).queue();
				case 57 -> e.getMessage().reply(target + " was too soft for this world (" + author + " helped)").mentionRepliedUser(false).queue();
				case 58 -> e.getMessage().reply(target + " stepped on a landmine and suffered major injuries.").mentionRepliedUser(false).queue();
				case 59 -> e.getMessage().reply(author + " backstabbed " + target + ".  " + target + " was foolishly holding a shield and died.").mentionRepliedUser(false).queue();
				case 60 -> e.getMessage().reply(target + " forgot to equip a Totem of Undying in a PVP battle against " + author + ".").mentionRepliedUser(false).queue();
				case 61 -> e.getMessage().reply(target + " was yeeted by the Ender Dragon.  They failed a waterbucket MLG.").mentionRepliedUser(false).queue();
				case 62 -> e.getMessage().reply(target + " forgot to put on their elytra and plunged to their death to a lava pool.").mentionRepliedUser(false).queue();
				case 63 -> e.getMessage().reply(target + " forgot to put on their armor before venturing out, and got blown up by a Creeper.").mentionRepliedUser(false).queue();
				case 64 -> e.getMessage().reply(target + " was roasted in dragon breath").mentionRepliedUser(false).queue();
				case 65 -> e.getMessage().reply(":hammer: " + target + " was successfully banned!").mentionRepliedUser(false).queue();
				case 66 -> e.getMessage().reply(target + " died and lost 9 223 372 036 854 775 807:violin:").mentionRepliedUser(false).queue();
				default -> e.getMessage().reply("Ling Ling is a benevolent violinist god so nice try but no.").mentionRepliedUser(false).queue();
			}
		}
	}
}