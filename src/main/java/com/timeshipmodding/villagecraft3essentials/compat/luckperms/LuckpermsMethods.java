package com.timeshipmodding.villagecraft3essentials.compat.luckperms;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.query.QueryOptions;
import net.luckperms.api.util.Tristate;
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
}