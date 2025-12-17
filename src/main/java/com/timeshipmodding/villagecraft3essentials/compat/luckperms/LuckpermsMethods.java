package com.timeshipmodding.villagecraft3essentials.compat.luckperms;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.query.QueryOptions;
import net.luckperms.api.util.Tristate;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.player.Player;

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

    public static String getPlayerColour(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        char oldSymbol = '&';
        char newSymbol = '§';
        String originalGroupColour = user.getCachedData().getMetaData().getMetaValue("group-colour");

        if (originalGroupColour != null) {
            return originalGroupColour.replace(oldSymbol, newSymbol);
        }

        return "";
    }

    /*public static ChatFormatting getPlayerColour(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        String nameColourString = user.getCachedData().getMetaData().getMetaValue("group-colour");
        ChatFormatting nameColour;

        if (nameColourString != null) {
            switch (nameColourString) {
                default -> nameColour = ChatFormatting.WHITE;
                case "black" -> nameColour = ChatFormatting.RED;
                case "dark_blue" -> nameColour = ChatFormatting.DARK_BLUE;
                case "dark_green" -> nameColour = ChatFormatting.DARK_GREEN;
                case "dark_aqua" -> nameColour = ChatFormatting.DARK_AQUA;
                case "dark_red" -> nameColour = ChatFormatting.DARK_RED;
                case "dark_purple" -> nameColour = ChatFormatting.DARK_PURPLE;
                case "gold" -> nameColour = ChatFormatting.GOLD;
                case "gray" -> nameColour = ChatFormatting.GRAY;
                case "dark gray" -> nameColour = ChatFormatting.DARK_GRAY;
                case "blue" -> nameColour = ChatFormatting.BLUE;
                case "green" -> nameColour = ChatFormatting.GREEN;
                case "aqua" -> nameColour = ChatFormatting.AQUA;
                case "red" -> nameColour = ChatFormatting.RED;
                case "light_purple" -> nameColour = ChatFormatting.LIGHT_PURPLE;
                case "yellow" -> nameColour = ChatFormatting.YELLOW;
            }

            return nameColour;

        } else {
            return ChatFormatting.WHITE;
        }
    } */

    public static ChatFormatting getPlayerStyling(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        String nameColourString = user.getCachedData().getMetaData().getMetaValue("group-colour");
        ChatFormatting nameColour;

        if (nameColourString != null) {
            switch (nameColourString) {
                default -> nameColour = ChatFormatting.WHITE;
                case "black" -> nameColour = ChatFormatting.RED;
                case "dark_blue" -> nameColour = ChatFormatting.DARK_BLUE;
                case "dark_green" -> nameColour = ChatFormatting.DARK_GREEN;
                case "dark_aqua" -> nameColour = ChatFormatting.DARK_AQUA;
                case "dark_red" -> nameColour = ChatFormatting.DARK_RED;
                case "dark_purple" -> nameColour = ChatFormatting.DARK_PURPLE;
                case "gold" -> nameColour = ChatFormatting.GOLD;
                case "gray" -> nameColour = ChatFormatting.GRAY;
                case "dark gray" -> nameColour = ChatFormatting.DARK_GRAY;
                case "blue" -> nameColour = ChatFormatting.BLUE;
                case "green" -> nameColour = ChatFormatting.GREEN;
                case "aqua" -> nameColour = ChatFormatting.AQUA;
                case "red" -> nameColour = ChatFormatting.RED;
                case "light_purple" -> nameColour = ChatFormatting.LIGHT_PURPLE;
                case "yellow" -> nameColour = ChatFormatting.YELLOW;
            }

            return nameColour;

        } else {
            return ChatFormatting.WHITE;
        }
    }

    public static String getGroupColour(String groupString) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        Group group = luckPermsApi.getGroupManager().getGroup(groupString);
        assert group != null;
        char oldSymbol = '&';
        char newSymbol = '§';
        String originalGroupColour = group.getCachedData().getMetaData().getMetaValue("group-colour");

        if (originalGroupColour != null) {
            return originalGroupColour.replace(oldSymbol, newSymbol);
        }

        return "";
    }

    public static String getPlayerPrefix(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        char oldSymbol = '&';
        char newSymbol = '§';
        String prefix = user.getCachedData().getMetaData().getPrefix();

        if (prefix != null) {
            return prefix.replace(oldSymbol, newSymbol);
        }

        return "";
    }

    public static String getPlayerSuffix(Player player) {
        LuckPerms luckPermsApi = LuckPermsProvider.get();
        User user = luckPermsApi.getUserManager().getUser(player.getUUID());
        assert user != null;
        char oldSymbol = '&';
        char newSymbol = '§';
        String suffix = user.getCachedData().getMetaData().getSuffix();

        if (suffix != null) {
            return suffix.replace(oldSymbol, newSymbol);
        }

        return "";
    }
}