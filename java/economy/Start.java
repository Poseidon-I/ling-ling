package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Prefix;

import java.io.File;
import java.io.FileWriter;

public class Start {
	public Start(MessageReceivedEvent e, String id, boolean ban) {
		try {
			File file = new File("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
			if(file.exists()) {
				e.getMessage().reply("You already have a save, don't try to outsmart me").mentionRepliedUser(false).queue();
				return;
			}
			JSONObject data = new JSONObject();
			data.put("violins", 0L);
			data.put("bank", 0L);
			data.put("loan", 0L);
			data.put("medals", 0L);
			data.put("magicFind", 0L);
			data.put("thirdPlace", 0L);
			data.put("secondPlace", 0L);
			data.put("firstPlace", 0L);
			
			data.put("rice", 0L);
			data.put("tea", 0L);
			data.put("blessings", 0L);
			data.put("voteBox", 0L);
			data.put("giftBox", 0L);
			data.put("kits", 0L);
			data.put("linglingBox", 0L);
			data.put("crazyBox", 0L);
			data.put("RNGesusBox", 0L);
			
			data.put("income", 0L);
			data.put("streak", 0L);
			data.put("earnings", 0L);
			data.put("winnings", 0L);
			data.put("millions", 0L);
			data.put("robbed", 0L);
			data.put("lostToRob", 0L);
			data.put("scalesPlayed", 0L);
			data.put("hoursPractised", 0.0);
			data.put("rehearsals", 0L);
			data.put("performances", 0L);
			data.put("hoursTaught", 0.0);
			data.put("maxStreak", 0L);
			data.put("votes", 0L);
			data.put("luthiers", 0L);
			data.put("giftsGiven", 0L);
			data.put("giftsReceived", 0L);
			data.put("interestEarned", 0L);
			data.put("penaltiesIncurred", 0L);
			data.put("RNGesusWeight", 0L);
			
			data.put("betCD", 0L);
			data.put("scaleCD", 0L);
			data.put("practiceCD", 0L);
			data.put("teachCD", 0L);
			data.put("rehearseCD", 0L);
			data.put("performCD", 0L);
			data.put("robCD", 0L);
			data.put("voteCD", 0L);
			data.put("hadDailyToday", false);
			data.put("hadGiftToday", false);
			
			data.put("efficiency", 0L);
			data.put("luck", 0L);
			data.put("sophistication", 0L);
			data.put("insurance", false);
			data.put("timeCrunch", false);
			data.put("magicFindViolins", 0L);
			
			data.put("violinQuality", 0L);
			data.put("skills", 0L);
			data.put("lessonQuality", 0L);
			data.put("stringQuality", 0L);
			data.put("bowQuality", 0L);
			data.put("math", false);
			
			data.put("orchestra", false);
			
			data.put("piccolo", false);
			data.put("flute", 0L);
			data.put("oboe", 0L);
			data.put("clarinet", 0L);
			data.put("bassoon", 0L);
			data.put("contraBassoon", false);
			
			data.put("horn", 0L);
			data.put("trumpet", 0L);
			data.put("trombone", 0L);
			data.put("tuba", 0L);
			data.put("timpani", 0L);
			data.put("percussion", 0L);
			
			data.put("violin1", 1);
			data.put("violin2", 1);
			data.put("cello", 0L);
			data.put("doubleBass", 0L);
			data.put("piano", 0L);
			data.put("harp", false);
			
			data.put("soprano", 0L);
			data.put("alto", 0L);
			data.put("tenor", 0L);
			data.put("bass", 0L);
			data.put("soloist", 0L);
			
			data.put("hall", 0L);
			data.put("conductor", 0L);
			data.put("tickets", 0L);
			data.put("advertising", 0L);
			
			data.put("certificate", false);
			data.put("students", 0L);
			data.put("lessonCharge", 0L);
			data.put("training", 0L);
			data.put("studio", false);
			data.put("longerLessons", false);
			
			data.put("moreIncome", 0L);
			data.put("moreCommandIncome", 0L);
			data.put("moreMulti", 0L);
			data.put("moreRob", 0L);
			data.put("shield", false);
			data.put("duplicator", false);
			data.put("magicFindMedals", 0L);
			
			data.put("storage", 0L);
			data.put("moreInterest", false);
			data.put("lessPenalty", false);
			
			data.put("color", "#0000FF");
			data.put("DMs", true);
			data.put("extraInfo", false);
			
			data.put("banned", ban);
			data.put("perms", 0L);
			data.put("medalToday", false);
			data.put("retainDaily", true);
			data.put("isBooster", false);
			data.put("serverLevel", 1.0);
			data.put("mfLLBox", false);
			data.put("mfCrazyBox", false);
			data.put("mfRNGesusBox", false);
			
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
				if(!ban) {
					e.getMessage().reply("Your profile has been created!  Run `" + Prefix.GetPrefix(e) + "help 3` for a list of economy commands!").mentionRepliedUser(false).queue();
				}
			} catch(Exception exception) {
				e.getMessage().reply("Something went wrong creating the file.  Run `" + Prefix.GetPrefix(e) + "support` for a link to the support server to get in contact with the developer.").mentionRepliedUser(false).queue();
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}