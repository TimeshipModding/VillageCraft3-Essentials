package com.timeshipmodding.villagecraft3essentials.compat.dcintegration;

import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import de.erdbeerbaerlp.dcintegration.common.DiscordIntegration;
import de.erdbeerbaerlp.dcintegration.common.storage.linking.LinkManager;
import de.erdbeerbaerlp.dcintegration.common.storage.linking.PlayerLink;
import de.erdbeerbaerlp.dcintegration.common.storage.linking.PlayerSettings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

public class DiscordIntegrationMethods {
    public static void giveDiscordRole(ServerPlayer player, String roleId) {
        var link = LinkManager.getLink(null, player.getUUID());

        if (link == null) {
            System.out.println("Skipping Discord role addition: Player " + player.getName().getString() + " is not linked.");
            return;
        }

        String discordID = link.discordID;
        String serverID = ServerConfig.DISCORD_SERVER_ID.get();

        if (discordID != null && !serverID.isEmpty() && !roleId.isEmpty()) {
            var jda = DiscordIntegration.INSTANCE.getJDA();

            if (jda == null) {
                return;
            }

            Guild guild = jda.getGuildById(serverID);
            if (guild != null) {
                guild.retrieveMemberById(discordID).queue(member -> {
                    Role roleToGive = guild.getRoleById(roleId);

                    if (roleToGive != null) {
                        guild.addRoleToMember(member, roleToGive).queue();
                    }
                });
            }
        }
    }

    public static void removeDiscordRole(ServerPlayer player, String roleId) {
        var link = LinkManager.getLink(null, player.getUUID());

        if (link == null) {
            System.out.println("Skipping Discord role removal: Player " + player.getName().getString() + " is not linked.");
            return;
        }

        String discordID = link.discordID;
        String serverID = ServerConfig.DISCORD_SERVER_ID.get();

        if (discordID != null && !serverID.isEmpty() && !roleId.isEmpty()) {
            var jda = DiscordIntegration.INSTANCE.getJDA();

            if (jda == null) {
                return;
            }

            Guild guild = jda.getGuildById(serverID);
            if (guild != null) {
                guild.retrieveMemberById(discordID).queue(member -> {
                    Role roleToRemove = guild.getRoleById(roleId);

                    if (roleToRemove != null) {
                        guild.removeRoleFromMember(member, roleToRemove).queue();
                    }
                });
            }
        }
    }

    public static void linkPlayer(UUID playerUUID, String discordID) {
        if (!discordID.isEmpty()) {
            DiscordIntegration.INSTANCE.getDatabaseInterface().removeLink(playerUUID.toString());
            DiscordIntegration.INSTANCE.getDatabaseInterface().addLink(new PlayerLink(discordID, playerUUID.toString(), "", new PlayerSettings()));
            LinkManager.load();
        }
    }

    public static void sendMessage(MutableComponent message) {
        DiscordIntegration.INSTANCE.sendMessage(message.getString());
    }
}
