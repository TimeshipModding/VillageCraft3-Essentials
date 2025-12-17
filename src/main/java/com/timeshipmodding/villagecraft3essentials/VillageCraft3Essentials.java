package com.timeshipmodding.villagecraft3essentials;

import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.registries.ModBlockEntities;
import com.timeshipmodding.villagecraft3essentials.content.creativetab.EssentialsTab;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModArmorMaterials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.registries.ModMenus;
import com.timeshipmodding.villagecraft3essentials.content.sound.registries.ModSounds;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.CommonConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
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
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);

        // Register creative mode tab
        EssentialsTab.CREATIVE_MODE_TABS.register(modEventBus);

        // Register mod configs
        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
    }
}