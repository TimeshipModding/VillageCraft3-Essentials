package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.chat.registries.ModChatUtilities;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Objects;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModLuckpermsEvents {
    /*@SubscribeEvent
    public static void onTabListNameFormatEvent(PlayerEvent.TabListNameFormat event) {
        if (ModList.get().isLoaded("luckperms") && ServerConfig.PLAYER_DISPLAY_NAME_FORMATTING.get()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                String prefix;
                String suffix;

                if (LuckpermsMethods.getGroupPrefix(serverPlayer).isEmpty()) {
                    prefix = "";
                } else {
                    prefix = LuckpermsMethods.getGroupPrefix(serverPlayer) + " ";
                }

                if (LuckpermsMethods.getGroupSuffix(serverPlayer).isEmpty()) {
                    suffix = "";
                } else {
                    suffix = " " + LuckpermsMethods.getGroupSuffix(serverPlayer);
                }

                event.setDisplayName(Component.literal(prefix + LuckpermsMethods.getPlayerColour(serverPlayer) + serverPlayer.getName().getString() + suffix + "§r"));
            }
        }
    } */

    @SubscribeEvent
    public static void onNameFormatEvent(PlayerEvent.NameFormat event) {
        if (ModList.get().isLoaded("luckperms") && ServerConfig.PLAYER_DISPLAY_NAME_FORMATTING.get()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                event.setDisplayname(ModChatUtilities.getFormattedPlayerName(serverPlayer));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                serverPlayer.refreshTabListName();
                serverPlayer.refreshDisplayName();
            }
        }
    }
}