package com.timeshipmodding.villagecraft3essentials.compat.luckperms.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.util.StringFormatter;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.ArrayList;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModLuckpermsEvents {
    @SubscribeEvent
    public static void onTabListNameFormatEvent(PlayerEvent.TabListNameFormat event) {
        if (ModList.get().isLoaded("luckperms") && ServerConfig.CHAT_TAB_NAME_FORMATTING.get()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                ArrayList<Component> prefixes = LuckpermsMethods.getPlayerFormattedPrefixes(serverPlayer);
                Component name = LuckpermsMethods.getPlayerFormattedName(serverPlayer);
                ArrayList<Component> suffixes = LuckpermsMethods.getPlayerFormattedSuffixes(serverPlayer);
                event.setDisplayName(StringFormatter.getFinalDisplayName(prefixes, name, suffixes));
            }
        }
    }

    @SubscribeEvent
    public static void onNameFormatEvent(PlayerEvent.NameFormat event) {
        if (ModList.get().isLoaded("luckperms") && ServerConfig.CHAT_TAB_NAME_FORMATTING.get()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                ArrayList<Component> prefixes = LuckpermsMethods.getPlayerFormattedPrefixes(serverPlayer);
                Component name = LuckpermsMethods.getPlayerFormattedName(serverPlayer);
                ArrayList<Component> suffixes = LuckpermsMethods.getPlayerFormattedSuffixes(serverPlayer);
                event.setDisplayname(StringFormatter.getFinalDisplayName(prefixes, name, suffixes));
            }
        }
    }
}