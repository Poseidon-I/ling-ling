package eventListeners;

import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import processes.StartBot;

public class Disconnect extends ListenerAdapter {
	public void onDisconnect(DisconnectEvent e) {
		e.getJDA().shutdownNow();
		new StartBot();
	}
}