package me.nikl.discord;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author Niklas Eicker
 */
public class ReadyListener extends ListenerAdapter {
    private final Butler butler;

    public ReadyListener(Butler butler) {
        this.butler = butler;
    }

    @Override
    public void onReady(ReadyEvent event) {
        butler.enable();
    }
}
