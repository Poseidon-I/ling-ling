package eventListeners;

import net.dv8tion.jda.api.events.session.SessionDisconnectEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import processes.StartBot;

public class Disconnect extends ListenerAdapter {
	public void onDisconnect(@NotNull SessionDisconnectEvent e) {
		e.getJDA().shutdownNow();
		StartBot.startBot();
	}
}