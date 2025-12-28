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

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModLuckpermsEvents {
    @SubscribeEvent
    public static void onTabListNameFormatEvent(PlayerEvent.TabListNameFormat event) {
        if (ModList.get().isLoaded("luckperms") && ServerConfig.CHAT_TAB_NAME_FORMATTING.get()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                Component prefix1 = LuckpermsMethods.getPlayerFormattedPrefix(serverPlayer);
                Component name = LuckpermsMethods.getPlayerFormattedName(serverPlayer);
                Component suffix = LuckpermsMethods.getPlayerFormattedSuffix(serverPlayer);
                event.setDisplayName(StringFormatter.getFinalDisplayName(prefix1, name, suffix));
            }
        }
    }

    @SubscribeEvent
    public static void onNameFormatEvent(PlayerEvent.NameFormat event) {
        if (ModList.get().isLoaded("luckperms") && ServerConfig.CHAT_TAB_NAME_FORMATTING.get()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                Component prefix = LuckpermsMethods.getPlayerFormattedPrefix(serverPlayer);
                Component name = LuckpermsMethods.getPlayerFormattedName(serverPlayer);
                Component suffix = LuckpermsMethods.getPlayerFormattedSuffix(serverPlayer);
                event.setDisplayname(StringFormatter.getFinalDisplayName(prefix, name, suffix));
            }
        }
    }
}