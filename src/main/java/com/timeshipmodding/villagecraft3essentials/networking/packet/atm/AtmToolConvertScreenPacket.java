package com.timeshipmodding.villagecraft3essentials.networking.packet.atm;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.AtmBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AtmToolConvertScreenPacket(BlockPos pos, boolean data) implements CustomPacketPayload {
    public static final Type<AtmToolConvertScreenPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_tool_convert_screen"));

    public static final StreamCodec<FriendlyByteBuf, AtmToolConvertScreenPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, AtmToolConvertScreenPacket::pos,
            ByteBufCodecs.BOOL, AtmToolConvertScreenPacket::data,
            AtmToolConvertScreenPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(AtmToolConvertScreenPacket payload, IPayloadContext context) {
        ServerPlayer sender = (ServerPlayer) context.player();
        Level level = sender.level();
        BlockPos pos = payload.pos();
        BlockEntity be = level.getBlockEntity(pos);

        if (be instanceof AtmBlockEntity myBlockEntity) {
            myBlockEntity.setToolConvertScreen(payload.data());
        }
    }
}