package com.timeshipmodding.villagecraft3essentials.event;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.util.itemhandler.HorseCurrencyArmorItemHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModCapabilityEvents {
    public static final EntityCapability<HorseCurrencyArmorItemHandler, Void> HORSE_ARMOR_CAPABILITY =
            EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "horse_armor_handler"), HorseCurrencyArmorItemHandler.class);

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(
                HORSE_ARMOR_CAPABILITY,
                EntityType.HORSE,
                (entity, context) -> new HorseCurrencyArmorItemHandler(entity)
        );
    }
}