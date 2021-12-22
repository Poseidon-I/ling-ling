package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class Start {
	public Start(GuildMessageReceivedEvent e, String id, boolean ban) {
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
		if(file.exists()) {
			e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
			throw new IllegalArgumentException();
		}
		JSONObject data = new JSONObject();
		data.put("violins", 140737488355327L);
		data.put("bank", 0);
		data.put("loan", 0);
		data.put("medals", 4095);
		data.put("thirdPlace", 0);
		data.put("secondPlace", 0);
		data.put("firstPlace", 0);
		
		data.put("rice", 10);
		data.put("tea", 10);
		data.put("blessings", 10);
		data.put("voteBox", 10);
		data.put("giftBox", 10);
		data.put("kits", 10);
		data.put("linglingBox", 10);
		data.put("crazyBox", 10);
		
		data.put("income", 0);
		data.put("streak", 0);
		data.put("earnings", 0);
		data.put("winnings", 0);
		data.put("millions", 0);
		data.put("robbed", 0);
		data.put("lostToRob", 0);
		data.put("scalesPlayed", 0);
		data.put("hoursPractised", 0.0);
		data.put("rehearsals", 0);
		data.put("performances", 0);
		data.put("hoursTaught", 0.0);
		data.put("maxStreak", 0);
		data.put("votes", 0);
		data.put("luthiers", 0);
		data.put("giftsGiven", 0);
		data.put("giftsReceived", 0);
		data.put("interestEarned", 0);
		data.put("penaltiesIncurred", 0);
		
		data.put("betCD", 0);
		data.put("scaleCD", 0);
		data.put("practiceCD", 0);
		data.put("teachCD", 0);
		data.put("rehearseCD", 0);
		data.put("performCD", 0);
		data.put("robCD", 0);
		data.put("voteCD", 0);
		data.put("hadDailyToday", false);
		data.put("hadGiftToday", false);
		
		data.put("efficiency", 0);
		data.put("luck", 0);
		data.put("sophistication", 0);
		data.put("insurance", false);
		data.put("timeCrunch", false);
		
		data.put("violinQuality", 0);
		data.put("skills", 0);
		data.put("lessonQuality", 0);
		data.put("stringQuality", 0);
		data.put("bowQuality", 0);
		data.put("math", false);
		
		data.put("orchestra", false);
		
		data.put("piccolo", false);
		data.put("flute", 0);
		data.put("oboe", 0);
		data.put("clarinet", 0);
		data.put("bassoon", 0);
		data.put("contraBassoon", false);
		
		data.put("horn", 0);
		data.put("trumpet", 0);
		data.put("trombone", 0);
		data.put("tuba", 0);
		data.put("timpani", 0);
		data.put("percussion", 0);
		
		data.put("violin1", 1);
		data.put("violin2", 1);
		data.put("cello", 0);
		data.put("doubleBass", 0);
		data.put("piano", 0);
		data.put("harp", false);
		
		data.put("soprano", 0);
		data.put("alto", 0);
		data.put("tenor", 0);
		data.put("bass", 0);
		data.put("soloist", 0);
		
		data.put("hall", 0);
		data.put("conductor", 0);
		data.put("tickets", 0);
		data.put("advertising", 0);
		
		data.put("certificate", false);
		data.put("students", 0);
		data.put("lessonCharge", 0);
		data.put("training", 0);
		data.put("studio", false);
		data.put("longerLessons", false);
		
		data.put("moreIncome", 0);
		data.put("moreCommandIncome", 0);
		data.put("moreMulti", 0);
		data.put("moreRob", 0);
		data.put("shield", false);
		data.put("duplicator", false);
		
		data.put("storage", 0);
		data.put("moreInterest", false);
		data.put("lessPenalty", false);
		
		data.put("banned", ban);
		data.put("perms", 0);
		data.put("medalToday", false);
		data.put("retainDaily", true);
		data.put("isBooster", false);
		data.put("serverLevel", 1.0);
		
		try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
			writer.write(data.toJSONString());
			writer.close();
			if(!ban) {
				e.getChannel().sendMessage("Your profile has been created!  Run `!help 3` for a list of economy commands!").queue();
			}
		} catch(Exception exception) {
			e.getChannel().sendMessage("Something went wrong creating the file.  Run `!support` for a link to the support server to get in contact with the developer.").queue();
		}
	}
}