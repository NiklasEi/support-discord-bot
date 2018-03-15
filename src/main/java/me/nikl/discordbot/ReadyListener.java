package me.nikl.discordbot;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * @author Niklas Eicker
 */
public class ReadyListener extends ListenerAdapter {
    private final Bot bot;

    public ReadyListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onReady(ReadyEvent event) {
        bot.enable();
    }
}
