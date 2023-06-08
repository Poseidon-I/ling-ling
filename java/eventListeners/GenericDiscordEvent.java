package eventListeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GenericDiscordEvent {
	User author;
	Message message;
	MessageChannel channel;
	JDA jda;
	Guild guild;
	SlashCommandInteractionEvent slashEvent = null;
	public GenericDiscordEvent(SlashCommandInteractionEvent e) {
		this.author = e.getUser();
		this.channel = e.getChannel();
		this.jda = e.getJDA();
		this.guild = e.getGuild();
		this.slashEvent = e;
	}

	public GenericDiscordEvent(MessageReceivedEvent e) {
		this.author = e.getAuthor();
		this.channel = e.getChannel();
		this.jda = e.getJDA();
		this.message = e.getMessage();
		this.guild = e.getGuild();
	}

	public User getAuthor() {
		return author;
	}

	public Message getMessage() {
		return message;
	}

	public MessageChannel getChannel() {
		return channel;
	}

	public JDA getJDA() {
		return jda;
	}

	public Guild getGuild() {
		return guild;
	}

	public boolean isSlashCommand() {
		return slashEvent != null;
	}

	public void reply(String message) {
		if(isSlashCommand()) {
			this.slashEvent.reply(message).queue();
		} else {
			this.message.reply(message).mentionRepliedUser(false).queue();
		}
	}

	public void replyEmbeds(MessageEmbed embed) {
		if(isSlashCommand()) {
			this.slashEvent.replyEmbeds(embed).queue();
		} else {
			this.message.replyEmbeds(embed).mentionRepliedUser(false).queue();
		}
	}

	public void sendMessage(String message) {
		channel.sendMessage(message).queue();
	}

	public void sendMessageEmbeds(MessageEmbed embed) {
		channel.sendMessageEmbeds(embed).queue();
	}
}