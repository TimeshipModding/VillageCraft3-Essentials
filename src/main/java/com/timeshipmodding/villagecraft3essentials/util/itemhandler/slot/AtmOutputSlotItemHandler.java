package com.timeshipmodding.villagecraft3essentials.util.itemhandler.slot;

import com.timeshipmodding.villagecraft3essentials.content.block.entity.AtmBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class AtmOutputSlotItemHandler extends SlotItemHandler {
    public AtmOutputSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, AtmBlockEntity blockEntity) {
        super(itemHandler, index, xPosition, yPosition);
        this.blockEntity = blockEntity;
    }

    private final AtmBlockEntity blockEntity;

    @Override
    public void onTake(Player player, ItemStack stack) {
        if (!player.level().isClientSide) {
            int amount = this.blockEntity.getConvertAmount();
            this.blockEntity.itemStackHandler.extractItem(0, amount, false);
            this.blockEntity.setChanged();
        }
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }
}