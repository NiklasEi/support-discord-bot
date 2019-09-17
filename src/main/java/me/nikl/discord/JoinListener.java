package me.nikl.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class JoinListener extends ListenerAdapter {
    private final String ROLE = "support seeker";
    private Role supportSeekers;

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getMember().getUser().isBot()) return;
        String name = event.getMember().getUser().getName();
        System.out.println(name + " joined...");
        if (supportSeekers == null) grabSupportSeekersRole(event.getGuild());
        if (event.getMember().getRoles().size() > 0) {
            System.out.println(name + " already has a role.");
            return;
        }
        event.getGuild().addRoleToMember(event.getMember(), supportSeekers).submit()
        .whenComplete((s, error) -> {
            if (error != null) error.printStackTrace();
            else System.out.println("Gave " + name + " the role " + supportSeekers.getName());
        });
    }

    private void grabSupportSeekersRole(Guild guild) {
        List<Role> roles = guild.getRolesByName(ROLE, true);
        if (roles == null || roles.isEmpty()) {
            System.out.println("Unable to grab the support seekers role");
            return;
        }
        supportSeekers = roles.get(0);
    }
}
