package com.timeshipmodding.villagecraft3essentials;

import com.timeshipmodding.villagecraft3essentials.compat.aquaculture.registries.ModAquaRegistry;
import com.timeshipmodding.villagecraft3essentials.compat.archers.event.registries.ModArchersEvents;
import com.timeshipmodding.villagecraft3essentials.compat.bluemap.event.registries.ModBluemapEvents;
import com.timeshipmodding.villagecraft3essentials.compat.brassworksmissions.event.registries.ModBrassworksMissionsEvents;
import com.timeshipmodding.villagecraft3essentials.compat.creategoggles.event.registries.ModCGEvents;
import com.timeshipmodding.villagecraft3essentials.compat.creategoggles.registries.ModCGRegistry;
import com.timeshipmodding.villagecraft3essentials.compat.farmersdelight.registries.ModFDRegistry;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.event.registries.ModLuckpermsEvents;
import com.timeshipmodding.villagecraft3essentials.compat.vanillabackport.registries.ModVanillaBackportRegistry;
import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.registries.ModBlockEntities;
import com.timeshipmodding.villagecraft3essentials.content.creativetab.EssentialsTab;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModArmorMaterials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.loot.modifier.registries.ModLootModifiers;
import com.timeshipmodding.villagecraft3essentials.content.menu.registries.ModMenus;
import com.timeshipmodding.villagecraft3essentials.content.sound.registries.ModSounds;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
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
        ModLootModifiers.GLOBAL_LOOT_MODIFIERS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModDataAttachments.ATTACHMENT_TYPES.register(modEventBus);

        // Register creative mode tab
        EssentialsTab.CREATIVE_MODE_TABS.register(modEventBus);

        // Register mod server config
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);

        // Register compat registries and events
        if (ModList.get().isLoaded("archers")) {
            ModArchersEvents.registerEvents();
        }

        if (ModList.get().isLoaded("aquaculture")) {
            ModAquaRegistry.register(modEventBus);
        }

        if (ModList.get().isLoaded("bluemap")) {
            ModBluemapEvents.registerEvents();
        }

        if (ModList.get().isLoaded("brassworksmissions")) {
            ModBrassworksMissionsEvents.registerEvents();
        }

        if (ModList.get().isLoaded("creategoggles")) {
            ModCGRegistry.register(modEventBus);
            ModCGEvents.registerEvents(modEventBus);
        }

        if (ModList.get().isLoaded("farmersdelight")) {
            ModFDRegistry.register(modEventBus);
        }

        if (ModList.get().isLoaded("luckperms")) {
            ModLuckpermsEvents.registerEvents();
        }

        if (ModList.get().isLoaded("vanillabackport")) {
            ModVanillaBackportRegistry.register(modEventBus);
        }
    }
}