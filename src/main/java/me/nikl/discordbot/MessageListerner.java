package me.nikl.discordbot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * @author Niklas Eicker
 */
public class MessageListerner extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        event.getChannel().sendMessage("Hi :)").queue();
        event.getAuthor().openPrivateChannel().queue( (channel) -> channel.sendMessage("private message").queue() );
    }
}
