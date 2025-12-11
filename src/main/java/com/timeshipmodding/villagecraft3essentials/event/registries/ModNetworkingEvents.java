package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.networking.packet.atm.*;
import com.timeshipmodding.villagecraft3essentials.networking.packet.atm.button.AtmRandomConvertButtonPressedPacket;
import com.timeshipmodding.villagecraft3essentials.networking.packet.atm.button.AtmRefreshSlotsPacket;
import com.timeshipmodding.villagecraft3essentials.networking.packet.atm.button.AtmSyncSlotPositionsPacket;
import com.timeshipmodding.villagecraft3essentials.networking.packet.atm.button.AtmToolConvertButtonPressedPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModNetworkingEvents {
    @SubscribeEvent
    private static void registerPackets(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(VillageCraft3Essentials.MODID).versioned("1.0.0");

        registrar.playToClient(
                AtmRandomConversionRatesPacket.TYPE,
                AtmRandomConversionRatesPacket.STREAM_CODEC,
                AtmRandomConversionRatesPacket::handle);
        registrar.playToServer(
                AtmRandomConvertButtonPressedPacket.TYPE,
                AtmRandomConvertButtonPressedPacket.STREAM_CODEC,
                AtmRandomConvertButtonPressedPacket::handle);
        registrar.playToServer(
                AtmRandomConvertScreenPacket.TYPE,
                AtmRandomConvertScreenPacket.STREAM_CODEC,
                AtmRandomConvertScreenPacket::handle);
        registrar.playToServer(
                AtmRefreshSlotsPacket.TYPE,
                AtmRefreshSlotsPacket.STREAM_CODEC,
                AtmRefreshSlotsPacket.Handler::handle);
        registrar.playToServer(
                AtmReturnItemPacket.TYPE,
                AtmReturnItemPacket.STREAM_CODEC,
                AtmReturnItemPacket.Handler::handle);
        registrar.playToClient(
                AtmSyncSlotPositionsPacket.TYPE,
                AtmSyncSlotPositionsPacket.STREAM_CODEC,
                AtmSyncSlotPositionsPacket.Handler::handle);
        registrar.playToServer(
                AtmToolConvertButtonPressedPacket.TYPE,
                AtmToolConvertButtonPressedPacket.STREAM_CODEC,
                AtmToolConvertButtonPressedPacket::handle);
        registrar.playToServer(
                AtmToolConvertScreenPacket.TYPE,
                AtmToolConvertScreenPacket.STREAM_CODEC,
                AtmToolConvertScreenPacket::handle);
    }
}