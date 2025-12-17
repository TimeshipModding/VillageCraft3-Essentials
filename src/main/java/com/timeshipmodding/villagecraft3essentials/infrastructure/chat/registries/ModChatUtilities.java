package com.timeshipmodding.villagecraft3essentials.infrastructure.chat.registries;

import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.chat.TextFormatter;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class ModChatUtilities {
    public static MutableComponent getFormattedPlayerName(Player player) {
        return TextFormatter.stringToFormattedText(getRawPreferredPlayerName(player));
    }

    public static String getRawPreferredPlayerName(Player player) {
        String prefix;
        String suffix;

        if (LuckpermsMethods.getPlayerPrefix(player).isEmpty()) {
            prefix = "";
        } else {
            prefix = LuckpermsMethods.getPlayerPrefix(player) + " ";
        }

        if (LuckpermsMethods.getPlayerSuffix(player).isEmpty()) {
            suffix = "";
        } else {
            suffix = " " + LuckpermsMethods.getPlayerSuffix(player);
        }

        return prefix + LuckpermsMethods.getPlayerColour(player) + player.getName().getString() + suffix;
    }
}
