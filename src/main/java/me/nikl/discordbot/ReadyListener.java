package me.nikl.discordbot;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;

/**
 * @author Niklas Eicker
 */
public class ReadyListener implements EventListener {
    private final Bot bot;

    public ReadyListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            System.out.println("API is ready!");
            bot.enable();
        }
    }
}
