package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.entity.MoleEntity;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.models.MoleModel;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModModelLayers;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModRegisterEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MOLE, MoleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MOLE_SADDLE, MoleModel::createSaddleLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MOLE.get(), MoleEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.MOLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MoleEntity::checkMoleSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}