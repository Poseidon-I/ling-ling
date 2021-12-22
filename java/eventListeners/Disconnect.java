package eventListeners;

import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import processes.StartBot;

public class Disconnect extends ListenerAdapter {
	public void onDisconnect(@NotNull DisconnectEvent e) {
		e.getJDA().shutdownNow();
		new StartBot();
	}
}