package com.timeshipmodding.villagecraft3essentials.networking.packet.atm.button;

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

public record AtmRandomConvertButtonPressedPacket(BlockPos pos, int data) implements CustomPacketPayload {
    public static final Type<AtmRandomConvertButtonPressedPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_random_convert_button_pressed"));

    public static final StreamCodec<FriendlyByteBuf, AtmRandomConvertButtonPressedPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, AtmRandomConvertButtonPressedPacket::pos,
            ByteBufCodecs.INT, AtmRandomConvertButtonPressedPacket::data,
            AtmRandomConvertButtonPressedPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(AtmRandomConvertButtonPressedPacket payload, IPayloadContext context) {
        ServerPlayer sender = (ServerPlayer) context.player();
        Level level = sender.level();
        BlockPos pos = payload.pos();
        BlockEntity be = level.getBlockEntity(pos);

        if (be instanceof AtmBlockEntity myBlockEntity) {
            myBlockEntity.setRandomConvertButtonPressed(payload.data());
        }
    }
}