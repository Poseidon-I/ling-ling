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
			} else {
				earned = (long) (balance * 0.01);
			}
			long max = (long) data.get("storage") * 20000000;
			if(earned + balance > max) {
				earned -= (earned + balance) - max;
			}
			
			long loan = (long) data.get("loan");
			long income = (long) data.get("loan");
			long violins = (long) data.get("violins");
			if(loan > income * 400) {
				violins -= earned * 4;
				loan -= earned * 5;
			} else if(loan > income * 250) {
				violins -= earned;
				loan -= earned * 2;
			} else if(loan > income * 100) {
				loan -= earned;
			} else if(loan > 0) {
				balance += earned * 0.5;
				loan -= earned * 0.5;
			} else {
				balance += earned;
			}
			if(loan < 0) {
				balance -= loan;
				loan = 0;
			}
			data.replace("violins", violins);
			data.replace("loan", loan);
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