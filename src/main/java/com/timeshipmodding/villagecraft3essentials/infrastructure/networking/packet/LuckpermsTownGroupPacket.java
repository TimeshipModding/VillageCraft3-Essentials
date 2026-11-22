package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.cache.LuckpermsTownGroupCache;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public record LuckpermsTownGroupPacket(UUID playerUuid, boolean inVillageCraftCity, boolean inOasis, boolean inSwedishFurnitureStore, boolean inLondon, boolean inBlackWater) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LuckpermsTownGroupPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "luckperms_town_group_packet"));

    public static final StreamCodec<FriendlyByteBuf, LuckpermsTownGroupPacket> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, LuckpermsTownGroupPacket::playerUuid,
            ByteBufCodecs.BOOL, LuckpermsTownGroupPacket::inVillageCraftCity,
            ByteBufCodecs.BOOL, LuckpermsTownGroupPacket::inOasis,
            ByteBufCodecs.BOOL, LuckpermsTownGroupPacket::inSwedishFurnitureStore,
            ByteBufCodecs.BOOL, LuckpermsTownGroupPacket::inLondon,
            ByteBufCodecs.BOOL, LuckpermsTownGroupPacket::inBlackWater,
            LuckpermsTownGroupPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final LuckpermsTownGroupPacket payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            LuckpermsTownGroupCache.setPlayerStatus(payload.playerUuid(), payload.inVillageCraftCity(), payload.inOasis(), payload.inSwedishFurnitureStore(), payload.inLondon(), payload.inBlackWater());
        });
    }
}
