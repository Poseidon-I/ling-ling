package regular;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class Kill {
	public static void kill(@NotNull SlashCommandInteractionEvent e) {
		String target = Objects.requireNonNull(e.getOption("target")).getAsString();
		String check = target.toLowerCase();
		String author = e.getUser().getName();
		for(int i = 0; i < check.length(); i ++) {
			int character = check.charAt(i);
			if(character < 32 || character > 32 && character <  97 || character > 122) {
				e.reply("nope, i fixed this loophole, no more non-letter characters").queue();
				return;
			}
		}
		if(author.contains("@everyone") || author.contains("@here")) {
			e.reply("imagine trying to abuse me to cause a mass ping, shame on you").queue();
		} else if(check.equalsIgnoreCase("i") || check.contains("ling") || check.contains("iing") || check.contains("llng") || check.contains("733409243222507670")) {
			e.reply("Nice try but you cannot kill me").queue();
		} else if(check.contains("strad") || check.contains("straf") || check.contains("dev") || check.contains("jacqueline") || check.contains("619989388109152256") || check.contains("488487157372157962")) {
			e.reply("Imagine trying to kill the devs smh").queue();
		} else if(check.contains(author.toLowerCase()) || author.toLowerCase().contains(check) || check.contains("self")) {
			e.reply("Do not harm yourself.  I will not allow it.").queue();
		} else if(check.contains("ludwig") || check.contains("beethoven")) {
			e.reply("killing the best composer ever is ILLEGAL.  i am banning you.").queue();
		} else {
			Random random = new Random();
			int i = random.nextInt(71);
			switch(i) {
				case 0 -> e.reply("death.fell.accident.water").queue();
				case 1 -> e.reply(target + "'s ears were blasted apart with a piccolo playing 10000 Hz at fortissimo.").queue();
				case 2 -> e.reply("Ling Ling deemed " + target + " unworthy of violin so he (she?) forced " + target + " to play the viola.  Their ego was permanently damaged.").queue();
				case 3 -> e.reply(target + " thought it was a good idea to play the sousaphone after eating chili pepper.").queue();
				case 4 -> e.reply(target + " was blinded because they used light mode.").queue();
				case 5 -> e.reply(author + " started punching " + target + ".").queue();
				case 6 -> e.reply(author + " somehow got " + target + " into their Minecraft world and then " + target + " was dumb enough to light TNT.").queue();
				case 7 -> e.reply(target + " stuffed too much chili pepper down their throat and the results were rather explosive.").queue();
				case 8 -> e.reply("A bomb detonated over " + target + "'s head.").queue();
				case 9 -> e.reply(target + " destroyed Jacqueline's Stradivarius so Jacqueline destroyed " + target).queue();
				case 10 -> e.reply(target + " tripped over a tripwire and fell into the Void.").queue();
				case 11 -> e.reply(target + " went to the zoo and got trampled by 20 elephants.").queue();
				case 12 -> e.reply(author + " played a very high harmonic on their violin and exploded " + target + "'s eardrums.").queue();
				case 13 -> e.reply(target + " was run over by " + author + " because " + target + " was crossing the street while playing a sousaphone.").queue();
				case 14 -> e.reply(target + " fell off a cliff because they were not paying attention to where they were going.  Reason?  Using Simply Piano app (sacrilegious).").queue();
				case 15 -> e.reply(target + " was brutally murdered with a viola for displeasing the conductor.").queue();
				case 16 -> e.reply(target + " tried to steal 2000 pounds worth of gold from " + author + " but they were caught and ended up crushing themselves under the gold.").queue();
				case 17 -> e.reply(target + " was sneak-attacked by " + author + ".").queue();
				case 18 -> e.reply(target + " tripped over " + author + "'s foot while reading music and walking.").queue();
				case 19 -> e.reply(author + " clobbers " + target + " with a clarinet because " + target + " was being dumb.").queue();
				case 20 -> e.reply(target + " abused bugs on Tacoshack for two years.  Cole just found out and their $500 000/hour Shack was deleted.").queue();
				case 21 -> e.reply(target + " decided to fight the Ender Dragon with nothing but their fists.").queue();
				case 22 -> e.reply(target + " hit a Zombified Piglin in an area swarming with them.  They had only an axe and got overwhelmed.").queue();
				case 23 -> e.reply(target + " ventured into an Abandoned Mineshaft and came across 3 Dungeons and a Cave Spider spawner.  They didn't have torches and were dumb enough not to break them.").queue();
				case 24 -> e.reply(target + " was killed by Ling Ling because they thought Axes were better than Swords.").queue();
				case 25 -> e.reply(target + " was playing with lava.").queue();
				case 26 -> e.reply(target + " fought Ling Ling.  They lost to his (her?) Sharpness 32767 Sword.").queue();
				case 27 -> e.reply(target + " attacked Beethoven, who had Throns 32767 armor.").queue();
				case 28 -> e.reply(target + " was squashed by a falling Anvil whilst attempting to escape Herobrine").queue();
				case 29 -> e.reply(target + " fell down a cliff.").queue();
				case 30 -> e.reply(target + " flew into unloaded chunks and died for no reason.").queue();
				case 31 -> e.reply(target + " thought Creepers were cute and invited one into their house.").queue();
				case 32 -> e.reply(target + " attacked an Admin and got the ban hammer.").queue();
				case 33 -> e.reply(target + " tried killing an iron golem.  The iron golem got the upper hand").queue();
				case 34 -> e.reply(target + " played out of tune in front of the Berlin Philharmonic and they were ridiculed to death by the Publik.").queue();
				case 35 -> e.reply("During a music test " + target + " was called out for cheating.").queue();
				case 36 -> e.reply(target + " is so bad at Minecraft they managed to die in one second in Peaceful Mode.  On a Superflat world.  Full of water.").queue();
				case 37 -> e.reply(target + " tried playing their piccolo solo but it broke so many eardrums that the conductor threw them off the stage into the audience headfirst.  Everyone laughed.").queue();
				case 38 -> e.reply(target + " was shot by " + author + " using [ 15 Notes a Second woB-X ]").queue();
				case 39 -> e.reply(target + " walked into a cactus whilst trying to escape " + author).queue();
				case 40 -> e.reply(target + " experienced Kinetic Energy").queue();
				case 41 -> e.reply(target + " died.  While flying over unloaded chunks.").queue();
				case 42 -> e.reply(target + " was blown up by " + author + " using Ling Ling's Wrath").queue();
				case 43 -> e.reply(target + " was killed by Intentional Game Design [MCPE-28723]").queue();
				case 44 -> e.reply(target + " was squashed by a falling anvil").queue();
				case 45 -> e.reply(target + " went off with a bang due to a firework fired from [ 15 Notes a Second woB-X ] by " + author).queue();
				case 46 -> e.reply(target + " tried to swim in lava").queue();
				case 47 -> e.reply(target + " was struck by lightning").queue();
				case 48 -> e.reply(target + " discovered the floor was lava").queue();
				case 49 -> e.reply(target + " walked into danger zone due to " + author).queue();
				case 50 -> e.reply(target + " was squashed by " + author).queue();
				case 51 -> e.reply(target + " was poked to death by a sweet berry bush whilst trying to escape " + author).queue();
				case 52 -> e.reply(target + " was killed by [ 15 Notes a Second Chestplate ] trying to hurt " + author).queue();
				case 53 -> e.reply(target + " was impaled by " + author + " with [ Zeus's Trident ]").queue();
				case 54 -> e.reply(target + " didn't want to live in the same world as " + author).queue();
				case 55 -> e.reply(target + " was roasted in dragon breath").queue();
				case 56 -> e.reply(target + " was killed by even more magic").queue();
				case 57 -> e.reply(target + " was too soft for this world (" + author + " helped)").queue();
				case 58 -> e.reply(target + " stepped on a landmine and suffered major injuries.").queue();
				case 59 -> e.reply(author + " backstabbed " + target + ".  " + target + " was foolishly holding a shield and died.").queue();
				case 60 -> e.reply(target + " forgot to equip a Totem of Undying in a PVP battle against " + author + ".").queue();
				case 61 -> e.reply(target + " was yeeted by the Ender Dragon.  They failed a waterbucket MLG.").queue();
				case 62 -> e.reply(target + " forgot to put on their elytra and plunged to their death to a lava pool.").queue();
				case 63 -> e.reply(target + " forgot to put on their armor before venturing out, and got blown up by a Creeper.").queue();
				case 64 -> e.reply(target + " was roasted in dragon breath").queue();
				case 65 -> e.reply(":hammer: " + target + " was successfully banned!").queue();
				case 66 -> e.reply(target + " died and lost 9 223 372 036 854 775 807:violin:").queue();
				case 67 -> e.reply(target + " stepped on a lego.  Just a note for those who don't know: The difference between a lego and a landmine is that one kills you and the other is a landmine.").queue();
				case 68 -> e.reply(target + " suffered extreme lag, and got comboed and crit out by " + author).queue();
				case 69 -> e.reply(target + " was killed by a fish in the shadow realm.").queue();
				case 70 -> e.reply(target + " only had 7 Fairy Souls and didn't put Hot Potato Books on their Shadow Assassin Armor.  Needless to say they got one-tapped by the first mob in F1.").queue();
				default -> e.reply("Ling Ling is a benevolent violinist god so nice try but no.").queue();
			}
		}
	}
}