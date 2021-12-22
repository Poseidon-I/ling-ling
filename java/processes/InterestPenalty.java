package processes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class InterestPenalty {
	public InterestPenalty() {
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
			//LOAN PENALTY
			long owed = (long) data.get("loan");
			if((boolean) data.get("lessPenalty")) {
				data.replace("loan", (long) (owed * 1.09));
				data.replace("penaltiesIncurred", (long) data.get("penaltiesIncurred") + (long) (owed * 0.09));
			} else {
				data.replace("loan", (long) (owed * 1.1));
				data.replace("penaltiesIncurred", (long) data.get("penaltiesIncurred") + (long) (owed * 0.1));
			}
			
			//BANK INTEREST
			long earned;
			long balance = (long) data.get("bank");
			if((boolean) data.get("moreInterest")) {
				earned = (long) (balance * 0.02);
				balance *= 1.02;
			} else {
				earned = (long) (balance * 0.01);
				balance *= 1.01;
			}
			long max = (long) data.get("storage") * 15000000;
			if(balance > max) {
				earned -= balance - max;
				balance = max;
			}
			data.replace("bank", balance);
			data.replace("interestEarned", (long) data.get("interestEarned") + earned);
			
			//SAVE DATA
			try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
	}
}