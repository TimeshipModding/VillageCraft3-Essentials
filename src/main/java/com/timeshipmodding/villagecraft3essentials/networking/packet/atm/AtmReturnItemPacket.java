package com.timeshipmodding.villagecraft3essentials.networking.packet.atm;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AtmReturnItemPacket() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<AtmReturnItemPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_return_item"));
    public static final StreamCodec<FriendlyByteBuf, AtmReturnItemPacket> STREAM_CODEC = StreamCodec.unit(new AtmReturnItemPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Handler {
        public static void handle(final AtmReturnItemPacket payload, final IPayloadContext context) {
            context.enqueueWork(() -> {
                ServerPlayer player = (ServerPlayer) context.player();

                if (player.containerMenu instanceof AtmMenu serverMenu) {
                    serverMenu.returnItemsToPlayer(player);
                }
            });
        }
    }
}