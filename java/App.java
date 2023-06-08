import processes.StartBot;

import java.io.*;

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
		StartBot.startBot();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				Runtime.getRuntime().exec("cmd /c start \"\" lingling.bat");
				Runtime.getRuntime().halt(0);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}));
	}
}