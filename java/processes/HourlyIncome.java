package processes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class HourlyIncome {
	public static void hourlyIncome() {
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
			long income = (long) data.get("income");
			long time = System.currentTimeMillis();
			if(time > (long) data.get("rosinExpire")) {
				income -= income * 0.25;
			}
			if(time > (long) data.get("stringsExpire")) {
				income -= income * 0.25;
			}
			if(time > (long) data.get("bowHairExpire")) {
				income -= income * 0.25;
			}
			if(time > (long) data.get("serviceExpire")) {
				income -= income * 0.25;
			}
			long violins = (long) data.get("violins");
			long loan = (long) data.get("loan");
			if(loan > income * 400) {
				violins -= income * 0.3;
				loan -= income * 1.3;
			} else if(loan > income * 250) {
				loan -= income;
			} else if(loan > income * 100) {
				violins += income * 0.3;
				loan -= income * 0.7;
			} else if(loan > 0) {
				violins += income * 0.6;
				loan -= income * 0.4;
			} else {
				violins += income;
			}
			if(loan < 0) {
				violins -= loan;
				loan = 0;
			}
			data.replace("violins", violins);
			data.replace("loan", loan);
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
	}
}