package processes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class HourlyIncome {
	public HourlyIncome() {
		File directory = new File("Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		JSONParser parser = new JSONParser();
		JSONObject data;
		assert files != null;
		for(File file : files) {
			try(FileReader reader = new FileReader(file.getAbsolutePath())) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
				if((boolean) data.get("banned")) {
					continue;
				}
			} catch(Exception exception) {
				System.out.println("Problem File is " + file.getName());
				continue;
			}
			data.replace("violins", (long) data.get("violins") + (long) data.get("income"));
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
	}
}