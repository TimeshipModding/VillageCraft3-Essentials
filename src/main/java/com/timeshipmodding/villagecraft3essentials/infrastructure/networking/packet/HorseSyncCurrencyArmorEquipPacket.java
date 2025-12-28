package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.handler.client.HorseCurrencyArmorClientHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record HorseSyncCurrencyArmorEquipPacket(boolean canEquip) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<HorseSyncCurrencyArmorEquipPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "horse_sync_currency_armor_equip"));

    public static final StreamCodec<RegistryFriendlyByteBuf, HorseSyncCurrencyArmorEquipPacket> STREAM_CODEC = StreamCodec.of(
                    HorseSyncCurrencyArmorEquipPacket::encode,
                    HorseSyncCurrencyArmorEquipPacket::decode
            );

    public static void encode(FriendlyByteBuf buffer, HorseSyncCurrencyArmorEquipPacket packet) {
        buffer.writeBoolean(packet.canEquip);
    }

    public static HorseSyncCurrencyArmorEquipPacket decode(FriendlyByteBuf buffer) {
        return new HorseSyncCurrencyArmorEquipPacket(buffer.readBoolean());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(HorseSyncCurrencyArmorEquipPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            HorseCurrencyArmorClientHandler.setCanEquipState(message.canEquip);
        });
    }
}