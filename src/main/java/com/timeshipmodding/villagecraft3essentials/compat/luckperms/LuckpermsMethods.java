package com.timeshipmodding.villagecraft3essentials.compat.luckperms;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.minecraft.world.entity.player.Player;

public class LuckpermsMethods {
    public static void addGroup(Player player, String groupName) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        Group group = luckPerms.getGroupManager().getGroup(groupName);

        if (group != null) {
            User user = luckPerms.getUserManager().getUser(player.getUUID());
            assert user != null;
            user.data().add(Node.builder("group." + groupName).build());
            luckPerms.getUserManager().saveUser(user);
        }
    }

    public static void removeGroup(Player player, String groupName) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        Group group = luckPerms.getGroupManager().getGroup(groupName);

        if (group != null) {
            User user = luckPerms.getUserManager().getUser(player.getUUID());
            assert user != null;
            user.data().remove(Node.builder("group." + groupName).build());
            luckPerms.getUserManager().saveUser(user);
        }
    }
}