import processes.StartBot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class App {
	public static void main(String[] args) {
		long pid = ProcessHandle.current().pid();
		System.out.println(pid);
		File file = new File("Ling Ling Bot Data\\pid.txt");
		try {
			file.delete();
			file.createNewFile();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\pid.txt")));
			writer.print(pid);
			writer.close();
		} catch(Exception exception1) {
			//nothing here lol
		}
		new StartBot();
	}
}