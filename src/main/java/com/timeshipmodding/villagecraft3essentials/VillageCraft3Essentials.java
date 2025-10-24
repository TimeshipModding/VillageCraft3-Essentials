package com.timeshipmodding.villagecraft3essentials;

import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.creativetab.EssentialsTab;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModArmorMaterials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.sound.registries.ModSounds;
import com.timeshipmodding.villagecraft3essentials.content.villager.registries.ModVillagers;
import com.timeshipmodding.villagecraft3essentials.util.Config;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VillageCraft3Essentials.MODID)
public class VillageCraft3Essentials {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "villagecraft3essentials";

    public VillageCraft3Essentials(IEventBus modEventBus, ModContainer modContainer) {
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

        // Listener to Common Setup
        modEventBus.addListener(this::commonSetup);

        // Register mod config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            LOGGER.info("Luckperms is installed. Enabled luckperms features within commands.");
        } else {
            LOGGER.info("Luckperms is not installed. Disabled luckperms features within commands.");
        }
    }
}