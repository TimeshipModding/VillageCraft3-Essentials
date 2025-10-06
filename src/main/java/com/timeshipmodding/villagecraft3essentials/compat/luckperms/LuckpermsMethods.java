package com.timeshipmodding.villagecraft3essentials.compat.luckperms;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.minecraft.world.entity.player.Player;

public class LuckpermsMethods {
    public static void AddJailedGroup(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        Group group = luckPerms.getGroupManager().getGroup("jailed");

        if (group != null) {
            User user = luckPerms.getUserManager().getUser(player.getUUID());
            assert user != null;
            user.data().add(Node.builder("group.jailed").build());
            luckPerms.getUserManager().saveUser(user);
        }
    }

    public static void RemoveJailedGroup(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        Group group = luckPerms.getGroupManager().getGroup("jailed");

        if (group != null) {
            User user = luckPerms.getUserManager().getUser(player.getUUID());
            assert user != null;
            user.data().remove(Node.builder("group.jailed").build());
            luckPerms.getUserManager().saveUser(user);
        }
    }
}
