package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.wallet;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CloseWalletMenuPacket() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CloseWalletMenuPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "close_wallet_menu"));
    public static final StreamCodec<ByteBuf, CloseWalletMenuPacket> STREAM_CODEC = StreamCodec.unit(new CloseWalletMenuPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Handler {
        public static void handle(final CloseWalletMenuPacket payload, final IPayloadContext context) {
            context.enqueueWork(() -> {
                ServerPlayer player = (ServerPlayer) context.player();
                InventoryMenu inventoryMenu = player.inventoryMenu;

                for (int i = 1; i <= 4; i++) {
                    Slot slot = inventoryMenu.getSlot(i);

                    if (slot.hasItem()) {
                        ItemStack stack = slot.getItem();

                        if (!player.getInventory().add(stack)) {
                            player.drop(stack, false);
                        }

                        slot.set(ItemStack.EMPTY);
                    }
                }

                inventoryMenu.getSlot(0).set(ItemStack.EMPTY);
                inventoryMenu.sendAllDataToRemote();
            });
        }
    }
}