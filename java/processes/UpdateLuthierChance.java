package processes;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class UpdateLuthierChance {
	public UpdateLuthierChance(MessageReceivedEvent e) {
		File directory = new File("Ling Ling Bot Data\\Settings\\Luthier");
		File[] files = directory.listFiles();
		assert files != null;
		for(File file : files) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			if(file.getName().contains("670725611207262219")) {
				continue;
			}
			try(FileReader reader = new FileReader(file.getAbsolutePath())) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				continue;
			}
			try {
				data.replace("chance", Numbers.LuthierChance(Objects.requireNonNull(e.getJDA().getGuildById(file.getName().substring(0, file.getName().lastIndexOf(".")))).getMemberCount()));
			} catch(Exception exception) {
				continue;
			}
			
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
		if(e.getMessage().getContentRaw().equals("!updateluthierchance")) {
			e.getMessage().reply("Successfully updated Luthier chances for all servers").mentionRepliedUser(false).queue();
		}
	}
}