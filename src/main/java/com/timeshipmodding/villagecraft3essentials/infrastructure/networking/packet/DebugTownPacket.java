package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.cache.DebugTownDataCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record DebugTownPacket(String town)  implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DebugTownPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "debug_town_packet"));

    public static final StreamCodec<FriendlyByteBuf, DebugTownPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, DebugTownPacket::town,
            DebugTownPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final DebugTownPacket payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            DebugTownDataCache.setTown(payload.town());
        });
    }
}