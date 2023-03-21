import org.json.JSONArray;
import org.json.JSONObject;

public class App {
	public static void main(String[] args) {
		JSONArray array = new JSONArray();
		array.put(100);
		array.put(1000);
		array.put(10000);
		array.put(100000);
		array.put(1000000);
		
		JSONObject object = new JSONObject();
		object.put("requirements", array);
		
		System.out.println(object);
		/*
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
		}));*/
	}
}