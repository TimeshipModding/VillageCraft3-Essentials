package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.wallet;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.menu.WalletMenu;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record OpenWalletMenuPacket() implements CustomPacketPayload {
    public static final Type<OpenWalletMenuPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "open_wallet_menu"));
    public static final StreamCodec<ByteBuf, OpenWalletMenuPacket> STREAM_CODEC = StreamCodec.unit(new OpenWalletMenuPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(OpenWalletMenuPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
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

            inventoryMenu.broadcastChanges();

            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new SimpleMenuProvider(
                        (containerId, playerInv, p) -> new WalletMenu(containerId, playerInv, p.getData(ModDataAttachments.WALLET)),
                        Component.empty()
                ));
            }
        });
    }
}