package me.nikl.discordbot;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class JoinListener extends ListenerAdapter {
    private Role supportSeekers;
    private GuildController guildController;

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getMember().getUser().isBot()) return;
        if (supportSeekers == null) grabSupportSeekersRole(event.getGuild());
        if (event.getMember().getRoles().size() > 0) return;
        guildController.addSingleRoleToMember(event.getMember(), supportSeekers).queue();
    }

    private void grabSupportSeekersRole(Guild guild) {
        this.guildController = guild.getController();
        List<Role> roles = guild.getRolesByName("support seekers", true);
        if (roles == null || roles.isEmpty()) {
            System.out.println("Unable to grab the support seekers role");
            return;
        }
        supportSeekers = roles.get(0);
    }
}
