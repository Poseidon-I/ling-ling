package processes;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Objects;

public class ResetDaily {
	public ResetDaily(GuildMessageReceivedEvent e) {
		File directory = new File("Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		assert files != null;
		for(File file : files) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader(file.getAbsolutePath())) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				continue;
			}
			if((boolean) data.get("hadDailyToday")) {
				data.replace("retainDaily", true);
			} else {
				data.replace("retainDaily", false);
			}
			data.replace("hadDailyToday", false);
			data.replace("hadGiftToday", false);
			data.replace("medalToday", false);
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
		
		new UpdateLuthierChance(e);
		
		if(e.getJDA().getSelfUser().getId().equals("733409243222507670")) {
			int users = files.length;
			VoiceChannel channel = Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getVoiceChannelById("839877827838476289");
			assert channel != null;
			channel.getManager().setName(users + " Ling Ling Wannabes").queue();
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Daily cooldowns reset!").queue();
		}
	}
}