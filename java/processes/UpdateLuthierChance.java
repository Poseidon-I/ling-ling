package processes;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class UpdateLuthierChance {
	public UpdateLuthierChance(GuildMessageReceivedEvent e) {
		File directory = new File("Ling Ling Bot Data\\Settings\\Luthier");
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
			try {
				data.replace("chance", Objects.requireNonNull(e.getJDA().getGuildById(file.getName().substring(0, files.length - 4))).getMemberCount());
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
	}
}