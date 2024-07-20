import processes.StartBot;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		/*long pid = ProcessHandle.current().pid();
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
		}*/
		//DatabaseManager.connectToDatabase();
		StartBot.startBot();
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("stop")) {
			System.out.println("Exiting...");
			System.exit(0);
		}
	}
}