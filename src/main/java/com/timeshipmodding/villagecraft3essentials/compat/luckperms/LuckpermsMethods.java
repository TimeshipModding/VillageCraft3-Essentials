package com.timeshipmodding.villagecraft3essentials.compat.luckperms;

import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.util.StringFormatter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.query.QueryOptions;
import net.luckperms.api.util.Tristate;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;

import java.util.Objects;

public class LuckpermsMethods {
    public static void addGroup(Player  player, String groupName) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        Group group = luckPermsApi.getGroupManager().getGroup(groupName);

        if (group != null) {
            User user = luckPermsApi.getUserManager().getUser(player.getUUID());
            assert user != null;
            user.data().add(Node.builder("group." + groupName).build());
            luckPermsApi.getUserManager().saveUser(user);
        }
    }

    public static void removeGroup(Player player, String groupName) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        Group group = luckPermsApi.getGroupManager().getGroup(groupName);

        if (group != null) {
            User user = luckPermsApi.getUserManager().getUser(player.getUUID());
            assert user != null;
            user.data().remove(Node.builder("group." + groupName).build());
            luckPermsApi.getUserManager().saveUser(user);
        }
    }

    public static boolean isInGroup(Player player, String groupName) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        Group group = luckPermsApi.getGroupManager().getGroup(groupName);

        if (group != null) {
            User user = luckPermsApi.getUserManager().getUser(player.getUUID());
            assert user != null;
            CachedPermissionData permissionData = user.getCachedData().getPermissionData(QueryOptions.contextual(user.getQueryOptions().context()));
            Tristate checkResult = permissionData.checkPermission("group." + groupName);
            return checkResult.asBoolean();
        }

        return false;
    }

    public static Component getPlayerFormattedName(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        String groupColour = user.getCachedData().getMetaData().getMetaValue("group-colour");

        if (groupColour != null) {
            String colourCode = groupColour.replace("&", "").replace("§", "");
            ChatFormatting style = ChatFormatting.getByCode(colourCode.charAt(0));

            if (style != null) {
                return Component.literal(player.getName().getString()).withStyle(style);
            }
        }

        return Component.literal(player.getName().getString()).withStyle(ChatFormatting.WHITE);
    }

    public static Component getPlayerFormattedPrefix(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        String prefix = user.getCachedData().getMetaData().getPrefix();
        return StringFormatter.formatString(prefix);
    }

    public static Component getPlayerFormattedSuffix(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        String suffix = user.getCachedData().getMetaData().getSuffix();
        return StringFormatter.formatString(suffix);
    }

    public static ChatFormatting getGroupStyling(String groupName) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        Group group = luckPermsApi.getGroupManager().getGroup(groupName);
        assert group != null;
        String groupStyling = group.getCachedData().getMetaData().getMetaValue("group-styling");

        if (groupStyling != null) {
            String colourCode = groupStyling.replace("&", "").replace("§", "");
            ChatFormatting style = ChatFormatting.getByCode(colourCode.charAt(0));

            if (style != null) {
                return style;
            }
        }

        return ChatFormatting.WHITE;
    }

    public static void updatePlayerTeam(ServerPlayer player) {
        ServerScoreboard scoreboard = Objects.requireNonNull(player.getServer()).getScoreboard();
        String teamName = "";

        if (isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
            if (isInGroup(player, ServerConfig.AMBERCAVES_GOVERNMENT_GROUP_NAME.get())) {
                if (isInGroup(player, ServerConfig.MAYOR_GROUP_NAME.get())) {
                    teamName = "011 _" + ServerConfig.MAYOR_GROUP_NAME.get();

                } else if (isInGroup(player, ServerConfig.SHERIFF_NAME.get())) {
                    teamName = "012 _" + ServerConfig.SHERIFF_NAME.get();

                } else if (isInGroup(player, ServerConfig.TREASURER_GROUP_NAME.get())) {
                    teamName = "013 _" + ServerConfig.TREASURER_GROUP_NAME.get();
                }

            } else {
                teamName = "014 _" + ServerConfig.AMBERCAVES_GROUP_NAME.get();
            }

        } else if (isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
            if (isInGroup(player, ServerConfig.GRIPPERCITY_GOVERNMENT_GROUP_NAME.get())) {
                if (isInGroup(player, ServerConfig.MAYOR_GROUP_NAME.get())) {
                    teamName = "011 _" + ServerConfig.MAYOR_GROUP_NAME.get();

                } else if (isInGroup(player, ServerConfig.SHERIFF_NAME.get())) {
                    teamName = "022 _" + ServerConfig.SHERIFF_NAME.get();

                } else if (isInGroup(player, ServerConfig.TREASURER_GROUP_NAME.get())) {
                    teamName = "023 _" + ServerConfig.TREASURER_GROUP_NAME.get();
                }

            } else {
                teamName = "024 _" + ServerConfig.GRIPPERCITY_GROUP_NAME.get();
            }

        } else if (isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
            if (isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GOVERNMENT_GROUP_NAME.get())) {
                if (isInGroup(player, ServerConfig.MAYOR_GROUP_NAME.get())) {
                    teamName = "031 _" + ServerConfig.MAYOR_GROUP_NAME.get();

                } else if (isInGroup(player, ServerConfig.SHERIFF_NAME.get())) {
                    teamName = "032 _" + ServerConfig.SHERIFF_NAME.get();

                } else if (isInGroup(player, ServerConfig.TREASURER_GROUP_NAME.get())) {
                    teamName = "033 _" + ServerConfig.TREASURER_GROUP_NAME.get();
                }

            } else {
                teamName = "034 _" + ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get();
            }
        }

        PlayerTeam team = scoreboard.getPlayerTeam(teamName);
        PlayerTeam currentTeam = scoreboard.getPlayersTeam(player.getScoreboardName());

        if (team == null) {
            team = scoreboard.addPlayerTeam(teamName);
        }

        if (currentTeam == null || !currentTeam.equals(team)) {
            scoreboard.addPlayerToTeam(player.getScoreboardName(), team);
        }
    }
}