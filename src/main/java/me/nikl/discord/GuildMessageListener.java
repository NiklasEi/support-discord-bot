package me.nikl.discord;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Niklas Eicker
 */
public class GuildMessageListener extends ListenerAdapter {
    private List<String> blackList = new ArrayList<>();

    public GuildMessageListener(Butler instance, String blackListRaw) {
        System.out.println("Blacklist: ");
        blackList.addAll(Arrays.asList(blackListRaw.split(",")));
        for (String word : blackList) {
            System.out.println("    - " + word);
        }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMember().getUser().isBot()) return;
        //if (event.getMember().getPermissions().contains(Permission.BAN_MEMBERS)) return;
        if (isInBlacklist(event.getMessage().getContentStripped())) {
            System.out.println(event.getMember().getUser().getName() + " send message contained in blacklist!");
            System.out.println("Message was: " + event.getMessage().getContentStripped());
            event.getGuild().getController().ban(event.getAuthor(), 1).queue();
        }
    }

    private boolean isInBlacklist(String message) {
        message = message.toLowerCase();
        for (String word : blackList) {
            if (message.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
