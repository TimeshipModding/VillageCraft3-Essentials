package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.button;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AtmSyncSlotPositionsPacket(int inputX, int inputY, int outputX, int outputY) implements CustomPacketPayload {
    public static final Type<AtmSyncSlotPositionsPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_sync_slot_positions"));
    public static final StreamCodec<FriendlyByteBuf, AtmSyncSlotPositionsPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, AtmSyncSlotPositionsPacket::inputX,
            ByteBufCodecs.INT, AtmSyncSlotPositionsPacket::inputY,
            ByteBufCodecs.INT, AtmSyncSlotPositionsPacket::outputX,
            ByteBufCodecs.INT, AtmSyncSlotPositionsPacket::outputY,
            AtmSyncSlotPositionsPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final AtmSyncSlotPositionsPacket payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().player.containerMenu instanceof AtmMenu atmMenu) {
                atmMenu.inputSlot.x = payload.inputX();
                atmMenu.inputSlot.y = payload.inputY();
                atmMenu.outputSlot.x = payload.outputX();
                atmMenu.outputSlot.y = payload.outputY();
            }
        });
    }
}