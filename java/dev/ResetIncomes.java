package dev;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ResetIncomes {
	public static String resetIncomes() {
		File directory = new File("Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		if(files != null) {
			for(File file : files) {
				JSONParser parser = new JSONParser();
				JSONObject data;
				try(FileReader reader = new FileReader(file.getAbsolutePath())) {
					data = (JSONObject) parser.parse(reader);
					reader.close();
					if((Boolean) data.get("banned")) {
						continue;
					}
				} catch(Exception exception) {
					continue;
				}
				long income = 0;
				try {
					income += 600 * (long) data.get("violinQuality");
					income += 250 * (long) data.get("skills");
					income += 200 * (long) data.get("lessonQuality");
					income += 150 * (long) data.get("stringQuality");
					income += 250 * (long) data.get("bowQuality");
					if((Boolean) data.get("math")) {
						income += 6500;
					}

					if((Boolean) data.get("orchestra")) {
						income += 3100;
					} else {
						income -= 130;
					}

					if((Boolean) data.get("piccolo")) {
						income += 30;
					}

					income += 60 * (long) data.get("flute");
					income += 50 * (long) data.get("oboe");
					income += 40 * (long) data.get("clarinet");
					income += 40 * (long) data.get("bassoon");

					if((Boolean) data.get("contraBassoon")) {
						income += 30;
					}

					income += 40 * (long) data.get("horn");
					income += 30 * (long) data.get("trumpet");
					income += 20 * (long) data.get("trombone");
					income += 20 * (long) data.get("tuba");
					income += 60 * (long) data.get("timpani");
					income += 10 * (long) data.get("percussion");

					income += 70 * (long) data.get("violin1");
					income += 60 * (long) data.get("violin2");
					income += 50 * (long) data.get("cello");
					income += 50 * (long) data.get("doubleBass");
					income += 110 * (long) data.get("piano");

					if((Boolean) data.get("harp")) {
						income += 80;
					}

					income += 30 * (long) data.get("soprano");
					income += 20 * (long) data.get("alto");
					income += 20 * (long) data.get("tenor");
					income += 20 * (long) data.get("bass");
					income += 60 * (long) data.get("soloist");

					income += 300 * (long) data.get("hall");
					income += 500 * (long) data.get("conductor");
					income += 1000 * (long) data.get("tickets");
					income += 100 * (long) data.get("advertising");

					if((Boolean) data.get("certificate")) {
						income += 5000;
					}

					income += 2000 * (long) data.get("students");
					income += 3000 * (long) data.get("lessonCharge");
					income += 1000 * (long) data.get("training");

					if((Boolean) data.get("studio")) {
						income += 5000;
					}

					income += 2000 * (long) data.get("moreIncome");
				} catch(Exception exception) {
					System.out.println("Problem file is " + file.getAbsolutePath());
					continue;
				}
				data.replace("income", income);
				try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
					writer.write(data.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
			}
		}
		return "Incomes successfully reset!";
	}
}