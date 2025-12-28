package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.button;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AtmRefreshSlotsPacket(BlockPos pos) implements CustomPacketPayload {
    public static final Type<AtmRefreshSlotsPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_refresh_slots"));
    public static final StreamCodec<FriendlyByteBuf, AtmRefreshSlotsPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            AtmRefreshSlotsPacket::pos,
            AtmRefreshSlotsPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Handler {
        public static void handle(final AtmRefreshSlotsPacket payload, final IPayloadContext context) {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer player) {
                    if (player.containerMenu instanceof AtmMenu atmMenu) {
                        atmMenu.refreshSlots();
                    }
                }
            });
        }
    }
}