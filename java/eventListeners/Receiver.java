package eventListeners;

import leveling.Leveling;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.*;

import java.io.FileReader;
import java.util.Objects;
import java.util.Random;

// BEETHOVEN-ONLY CLASS
public class Receiver extends ListenerAdapter {
	public void onMessageReceived(@NotNull MessageReceivedEvent e) {
		GenericDiscordEvent e1 = new GenericDiscordEvent(e);
		e.getGuild().loadMembers();
		if(e.getChannel().getId().equals("863135059712409632") || e.getChannel().getId().equals("734697496688853012")) {
			CheckGiveaways.checkGiveaways(e1);
		}
		if(e.getChannel().getId().equals("734697521758339163")) {
			CheckCounting.checkCounting(e1);
		}
		if(e.getChannel().getId().equals("836710100127318057")) {
			CheckReply.checkReply(e1);
		}
		if(e.getChannel().getId().equals("734697496688853012") && e.getAuthor().getId().equals("235148962103951360")) {
			TimeOut.timeOut(e1);
		}
		if(e.getChannel().getId().equals("798751619344367626") && e.getAuthor().getId().equals("235148962103951360") && Objects.requireNonNull(e.getMessage().getEmbeds().get(0).getTitle()).contains("Suggestion")) {
			e.getChannel().sendMessage("<@&930189721685094491>").queue();
		}
		if(e.getChannel().getId().equals("759092196976099348") && e.getAuthor().isBot() && e.getMessage().getContentRaw().contains("POLL:")) {
			e.getChannel().sendMessage("<@&747954053660540928> <@&750876814842527754>").queue();
		}

		if(!e.getAuthor().isBot()) {
			// Beethoven Commands
			Autoresponse.autoresponse(e1);
				if(!Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("736287976224587838"))) {
				WordAutomod.wordAutomod(e1);
			}
			if(e.getMessage().getContentRaw().toLowerCase().split(" ")[0].equals("beethoven")) {
				Commands.commands(e1);
			}
			double multipler = 0.0;
			switch(e.getChannel().getId()) {
				case "734697505543159879", "845122401352417350", "845397074156847124", "1086924606352404550", "1101190200438300756" ->
						multipler = 1.0;
				case "834897888340869151", "748059191440179204", "734697521758339163", "836710100127318057" ->
						multipler = 0.5;
			}
			if(multipler != 0.0) {
				Leveling.leveling(e1, multipler);
			}

			if(e1.getChannel().getId().equals("763099851550097408")) {
				JSONParser parser = new JSONParser();
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".json")) {
					Luthier.luthier(e1, (JSONObject) parser.parse(reader), e1.getMessage().getContentRaw());
					reader.close();
				} catch(Exception exception) {
					exception.printStackTrace();
				}
			}

			// Ling Ling Commands
			String[] message = e.getMessage().getContentRaw().toLowerCase().split(" ");
			if(message[0].charAt(0) == '!') {
				message[0] = message[0].substring(1);
				String[] realMessage = new String[message.length + 1];
				realMessage[0] = "<@733409243222507670>";
				System.arraycopy(message, 0, realMessage, 1, message.length);
				OldReceiver.runLingLingCommand(e1, realMessage);
			}
		}
		Random random = new Random();
		if(e.getChannel().getName().contains("announcement") || random.nextDouble() <= 0.025) {
			if(!e.getAuthor().getId().equals("733409243222507670") || e.getAuthor().getId().equals("733409243222507670") && !e.getMessage().getContentRaw().toLowerCase().contains("poll")) {
				e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), Emoji.fromUnicode("U+1F1FB")).queue();
				e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), Emoji.fromUnicode("U+1F1EE")).queue();
				e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), Emoji.fromUnicode("U+1F1F4")).queue();
				e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), Emoji.fromUnicode("U+1F1F1")).queue();
				e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), Emoji.fromUnicode("U+1F1E6")).queue();
			}
		}
	}
}