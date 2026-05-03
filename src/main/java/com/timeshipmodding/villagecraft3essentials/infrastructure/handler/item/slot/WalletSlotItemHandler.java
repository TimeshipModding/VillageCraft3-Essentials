package com.timeshipmodding.villagecraft3essentials.infrastructure.handler.item.slot;

import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class WalletSlotItemHandler extends SlotItemHandler {
    public WalletSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(ModItemTags.WALLET_ACCEPTED_ITEMS);
    }

    @Override
    public boolean mayPickup(Player player) {
        return true;
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }
}