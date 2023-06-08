package eventListeners;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.FileReader;

public class ILoveJava extends ListenerAdapter {
	public static void forceRestart() {
		try(BufferedReader reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\pid.txt"))) {
			String pid = reader.readLine();
			reader.close();
			Runtime.getRuntime().exec("taskkill /F /PID " + pid);
			System.out.println("Process successfully killed!");
			
			Runtime.getRuntime().exec("cmd /c start \"\" lingling.bat");
			System.out.println("Ling Ling successfully restarted!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/*public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent e) {
		if(e.getMember().getId().equals("733409243222507670") && e.getNewOnlineStatus().equals(OnlineStatus.OFFLINE)) {
			forceRestart();
		}
	}*/
}