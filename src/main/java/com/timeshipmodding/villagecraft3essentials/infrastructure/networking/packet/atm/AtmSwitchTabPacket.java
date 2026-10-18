package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AtmSwitchTabPacket(BlockPos pos, boolean isRandomConvert) implements CustomPacketPayload {
    public static final Type<AtmSwitchTabPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_switch_tab"));

    public static final StreamCodec<FriendlyByteBuf, AtmSwitchTabPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, AtmSwitchTabPacket::pos,
            ByteBufCodecs.BOOL, AtmSwitchTabPacket::isRandomConvert,
            AtmSwitchTabPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final AtmSwitchTabPacket payload, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();

        if (player.containerMenu instanceof AtmMenu atmMenu) {
            atmMenu.returnItemsToPlayer(player);
            System.out.println(payload.isRandomConvert());
            atmMenu.blockEntity.setRandomConvertScreen(payload.isRandomConvert());
            atmMenu.blockEntity.setToolConvertScreen(!payload.isRandomConvert());

            context.enqueueWork(() -> {
                atmMenu.returnItemsToPlayer(player);
                atmMenu.refreshSlots();
            });
        }
    }
}
