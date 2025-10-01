package com.timeshipmodding.villagecraft3essentials;

import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.creativetab.EssentialsTab;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModArmorMaterials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.sound.registries.ModSounds;
import com.timeshipmodding.villagecraft3essentials.content.villager.registries.ModVillagers;
import com.timeshipmodding.villagecraft3essentials.util.saveddata.JailSavedData;
import com.timeshipmodding.villagecraft3essentials.util.saveddata.SpawnSavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VillageCraft3Essentials.MODID)
public class VillageCraft3Essentials {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "villagecraft3essentials";
    public static SpawnSavedData spawnSavedData;
    public static JailSavedData jailSavedData;

    public VillageCraft3Essentials(IEventBus modEventBus) {
        // Register registry classes
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        ModVillagers.POI_TYPES.register(modEventBus);
        ModVillagers.VILLAGER_PROFESSIONS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);

        // Register creative mode tab
        EssentialsTab.CREATIVE_MODE_TABS.register(modEventBus);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        VillageCraft3Essentials.spawnSavedData = SpawnSavedData.getData(event.getServer());
        VillageCraft3Essentials.jailSavedData = JailSavedData.getData(event.getServer());
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        VillageCraft3Essentials.spawnSavedData.setDirty(true);
        VillageCraft3Essentials.jailSavedData.setDirty(true);
    }
}
