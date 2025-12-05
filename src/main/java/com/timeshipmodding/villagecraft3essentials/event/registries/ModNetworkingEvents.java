package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.networking.packet.AtmReturnItemPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModNetworkingEvents {
    @SubscribeEvent
    private static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(VillageCraft3Essentials.MODID).versioned("1.0.0"); // Use a versioned registrar

        registrar.playToServer(
                AtmReturnItemPacket.TYPE,
                AtmReturnItemPacket.STREAM_CODEC,
                AtmReturnItemPacket.Handler::handle
        );
    }
}