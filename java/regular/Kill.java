package regular;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Kill {
	public Kill(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder targetBuilder = new StringBuilder();
		for(int i = 1; i < message.length; i++) {
			if(!message[i].contains("<@")) {
				targetBuilder.append(message[i]).append(" ");
			} else {
				e.getChannel().sendMessage("Don't ping anybody, you idiot, go annoy yourself").queue();
				throw new IllegalArgumentException();
			}
		}
		String target = targetBuilder.deleteCharAt(targetBuilder.length() - 1).toString();
		String author = e.getAuthor().getName();
		if(author.contains("@everyone") || author.contains("@here")) {
			e.getChannel().sendMessage("imagine trying to abuse me to cause a mass ping, shame on you").queue();
		} else if(target.equalsIgnoreCase("i") || target.toLowerCase().contains("ling ling") || target.toLowerCase().contains("lingling") || target.contains("733409243222507670")) {
			e.getChannel().sendMessage("Nice try but you cannot kill me").queue();
		} else if(target.toLowerCase().contains("strad") || target.toLowerCase().contains("dev") || target.toLowerCase().contains("jacqueline") || target.contains("619989388109152256") || target.contains("488487157372157962")) {
			e.getChannel().sendMessage("Imagine trying to kill the devs smh").queue();
		} else {
			Random random = new Random();
			int i = random.nextInt(68);
			switch(i) {
				case 0 -> e.getChannel().sendMessage("death.fell.accident.water").queue();
				case 1 -> e.getChannel().sendMessage(target + "'s ears were blasted apart with a piccolo playing 10000 Hz at fortissimo.").queue();
				case 2 -> e.getChannel().sendMessage("Ling Ling deemed " + target + " unworthy of violin so he (she?) forced " + target + " to play the viola.  Their ego was permanently damaged.").queue();
				case 3 -> e.getChannel().sendMessage(target + " thought it was a good idea to play the sousaphone after eating chili pepper.").queue();
				case 4 -> e.getChannel().sendMessage(target + " was blinded because they used light mode.").queue();
				case 5 -> e.getChannel().sendMessage(author + " started punching " + target + ".").queue();
				case 6 -> e.getChannel().sendMessage(author + " somehow got " + target + " into their Minecraft world and then " + target + " was dumb enough to light TNT.").queue();
				case 7 -> e.getChannel().sendMessage(target + " stuffed too much chili pepper down their throat and the results were rather explosive.").queue();
				case 8 -> e.getChannel().sendMessage("A bomb detonated over " + target + "'s head.").queue();
				case 9 -> e.getChannel().sendMessage(target + " destroyed Jacqueline's Stradivarius so Jacqueline destroyed " + target).queue();
				case 10 -> e.getChannel().sendMessage(target + " tripped over a tripwire and fell into the Void.").queue();
				case 11 -> e.getChannel().sendMessage(target + " went to the zoo and got trampled by 20 elephants.").queue();
				case 12 -> e.getChannel().sendMessage(author + " played a very high harmonic on their violin and exploded " + target + "'s eardrums.").queue();
				case 13 -> e.getChannel().sendMessage(target + " was run over by " + author + " because " + target + " was crossing the street while playing a sousaphone.").queue();
				case 14 -> e.getChannel().sendMessage(target + " fell off a cliff because they were not paying attention to where they were going.  Reason?  Using Simply Piano app (sacrilegious).").queue();
				case 15 -> e.getChannel().sendMessage(target + " was brutally murdered with a viola for displeasing the conductor.").queue();
				case 16 -> e.getChannel().sendMessage(target + " tried to steal 2000 pounds worth of gold from " + author + " but they were caught and ended up crushing themselves under the gold.").queue();
				case 17 -> e.getChannel().sendMessage(target + " was sneak-attacked by " + author + ".").queue();
				case 18 -> e.getChannel().sendMessage(target + " tripped over " + author + "'s foot while reading music and walking.").queue();
				case 19 -> e.getChannel().sendMessage(author + " clobbers " + target + " with a clarinet because " + target + " was being dumb.").queue();
				case 20 -> e.getChannel().sendMessage(target + " abused bugs on Tacoshack for two years.  Cole just found out and their $500 000/hour Shack was deleted.").queue();
				case 21 -> e.getChannel().sendMessage(target + " decided to fight the Ender Dragon with nothing but their fists.").queue();
				case 22 -> e.getChannel().sendMessage(target + " hit a Zombified Piglin in an area swarming with them.  They had only an axe and got overwhelmed.").queue();
				case 23 -> e.getChannel().sendMessage(target + " ventured into an Abandoned Mineshaft and came across 3 Dungeons and a Cave Spider spawner.  They didn't have torches and were dumb enough not to break them.").queue();
				case 24 -> e.getChannel().sendMessage(target + " was killed by Ling Ling because they thought Axes were better than Swords.").queue();
				case 25 -> e.getChannel().sendMessage(target + " was playing with lava.").queue();
				case 26 -> e.getChannel().sendMessage(target + " fought Ling Ling.  They lost to his (her?) Sharpness 32767 Sword.").queue();
				case 27 -> e.getChannel().sendMessage(target + " attacked Beethoven, who had Throns 32767 armor.").queue();
				case 28 -> e.getChannel().sendMessage(target + " was squashed by a falling Anvil whilst attempting to escape Herobrine").queue();
				case 29 -> e.getChannel().sendMessage(target + " fell down a cliff.").queue();
				case 30 -> e.getChannel().sendMessage(target + " flew into unloaded chunks and died for no reason.").queue();
				case 31 -> e.getChannel().sendMessage(target + " thought Creepers were cute and invited one into their house.").queue();
				case 32 -> e.getChannel().sendMessage(target + " attacked an Admin and got the ban hammer.").queue();
				case 33 -> e.getChannel().sendMessage(target + " tried killing an iron golem.  The iron golem got the upper hand").queue();
				case 34 -> e.getChannel().sendMessage(target + " played out of tune in front of the Berlin Philharmonic and they were ridiculed to death by the Publik.").queue();
				case 35 -> e.getChannel().sendMessage("During a music test " + target + " was called out for cheating.").queue();
				case 36 -> e.getChannel().sendMessage(target + " is so bad at Minecraft they managed to die in one second in Peaceful Mode.  On a Superflat world.  Full of water.").queue();
				case 37 -> e.getChannel().sendMessage(target + " tried playing their piccolo solo but it broke so many eardrums that the conductor threw them off the stage into the audience headfirst.  Everyone laughed.").queue();
				case 38 -> e.getChannel().sendMessage(target + " was shot by " + author + " using [ 15 Notes a Second woB-X ]").queue();
				case 39 -> e.getChannel().sendMessage(target + " walked into a cactus whilst trying to escape " + author).queue();
				case 40 -> e.getChannel().sendMessage(target + " experienced Kinetic Energy").queue();
				case 41 -> e.getChannel().sendMessage(target + " died.  While flying over unloaded chunks.").queue();
				case 42 -> e.getChannel().sendMessage(target + " was blown up by " + author + " using Ling Ling's Wrath").queue();
				case 43 -> e.getChannel().sendMessage(target + " was killed by Intentional Game Design [MCPE-28723]").queue();
				case 44 -> e.getChannel().sendMessage(target + " was squashed by a falling anvil").queue();
				case 45 -> e.getChannel().sendMessage(target + " went off with a bang due to a firework fired from [ 15 Notes a Second woB-X ] by " + author).queue();
				case 46 -> e.getChannel().sendMessage(target + " tried to swim in lava").queue();
				case 47 -> e.getChannel().sendMessage(target + " was struck by lightning").queue();
				case 48 -> e.getChannel().sendMessage(target + " discovered the floor was lava").queue();
				case 49 -> e.getChannel().sendMessage(target + " walked into danger zone due to " + author).queue();
				case 50 -> e.getChannel().sendMessage(target + " was squashed by " + author).queue();
				case 51 -> e.getChannel().sendMessage(target + " was poked to death by a sweet berry bush whilst trying to escape " + author).queue();
				case 52 -> e.getChannel().sendMessage(target + " was killed by [ 15 Notes a Second Chestplate ] trying to hurt " + author).queue();
				case 53 -> e.getChannel().sendMessage(target + " was impaled by " + author + " with [ Zeus's Trident ]").queue();
				case 54 -> e.getChannel().sendMessage(target + " didn't want to live in the same world as " + author).queue();
				case 55 -> e.getChannel().sendMessage(target + " was roasted in dragon breath").queue();
				case 56 -> e.getChannel().sendMessage(target + " was killed by even more magic").queue();
				case 57 -> e.getChannel().sendMessage(target + " was too soft for this world (" + author + " helped)").queue();
				case 58 -> e.getChannel().sendMessage(target + " stepped on a landmine and suffered major injuries.").queue();
				case 59 -> e.getChannel().sendMessage(author + " backstabbed " + target + ".  " + target + " was foolishly holding a shield and died.").queue();
				case 60 -> e.getChannel().sendMessage(target + " forgot to equip a Totem of Undying in a PVP battle against " + author + ".").queue();
				case 61 -> e.getChannel().sendMessage(target + " was yeeted by the Ender Dragon.  They failed a waterbucket MLG.").queue();
				case 62 -> e.getChannel().sendMessage(target + " forgot to put on their elytra and plunged to their death to a lava pool.").queue();
				case 63 -> e.getChannel().sendMessage(target + " forgot to put on their armor before venturing out, and got blown up by a Creeper.").queue();
				case 64 -> e.getChannel().sendMessage(target + " was roasted in dragon breath").queue();
				case 65 -> e.getChannel().sendMessage(":hammer: " + target + " was successfully banned!").queue();
				case 66 -> e.getChannel().sendMessage(target + " died and lost 9 223 372 036 854 775 807:violin:").queue();
				default -> e.getChannel().sendMessage("Ling Ling is a benevolent violinist god so nice try but no.").queue();
			}
		}
	}
}