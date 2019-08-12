package me.nikl.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildManager;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class JoinListener extends ListenerAdapter {
    private Role supportSeekers;
    private GuildManager guildController;

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getMember().getUser().isBot()) return;
        System.out.println(event.getMember().getNickname() + " Joined...");
        if (supportSeekers == null) grabSupportSeekersRole(event.getGuild());
        if (event.getMember().getRoles().size() > 0) {
            System.out.println(event.getMember().getNickname() + " already has a role.");
            return;
        }
        guildController.getGuild().addRoleToMember(event.getMember(), supportSeekers).queue();
        System.out.println("Gave " + event.getMember().getUser().getName() + " the role " + supportSeekers.getName());
    }

    private void grabSupportSeekersRole(Guild guild) {
        this.guildController = guild.getManager();
        List<Role> roles = guild.getRolesByName("support seekers", true);
        if (roles == null || roles.isEmpty()) {
            System.out.println("Unable to grab the support seekers role");
            return;
        }
        supportSeekers = roles.get(0);
    }
}
