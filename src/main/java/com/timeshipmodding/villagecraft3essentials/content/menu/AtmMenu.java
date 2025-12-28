package com.timeshipmodding.villagecraft3essentials.content.menu;

import com.timeshipmodding.villagecraft3essentials.content.block.entity.AtmBlockEntity;
import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.registries.ModMenus;
import com.timeshipmodding.villagecraft3essentials.content.sound.registries.ModSounds;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.button.AtmSyncSlotPositionsPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.itemhandler.slot.AtmOutputSlotItemHandler;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.network.PacketDistributor;

public class AtmMenu extends AbstractContainerMenu {
    public final AtmBlockEntity blockEntity;
    private final Level level;
    private final Inventory inv;
    public final Slot inputSlot;
    public final Slot outputSlot;

    public AtmMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public AtmMenu(int containerId, Inventory inv, BlockEntity entity) {
        super(ModMenus.ATM_MENU.get(), containerId);
        blockEntity = ((AtmBlockEntity) entity);
        this.level = inv.player.level();
        this.inv = inv;
        this.inputSlot = this.addSlot(new SlotItemHandler(this.blockEntity.itemStackHandler, 0, 26, 24));
        this.outputSlot = this.addSlot(new AtmOutputSlotItemHandler(this.blockEntity.itemStackHandler, 1, 26, 75, this.blockEntity));
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    public void refreshSlots() {
        int ix = 0, iy = 0, ox = 0, oy = 0;

        if (this.blockEntity.getRandomConvertScreen()) {
            ix = 26; iy = 24; ox = 26; oy = 75;

        } else if (this.blockEntity.getToolConvertScreen()){
            ix = 53; iy = 32; ox = 107; oy = 32;
        }

        this.inputSlot.x = ix;
        this.inputSlot.y = iy;
        this.outputSlot.x = ox;
        this.outputSlot.y = oy;
        this.broadcastChanges();

        if (!this.blockEntity.getLevel().isClientSide && inv.player instanceof ServerPlayer) {
            PacketDistributor.sendToPlayer((ServerPlayer) inv.player, new AtmSyncSlotPositionsPacket(ix, iy, ox, oy));
        }
    }

    public void returnItemsToPlayer(Player player) {
        if (player instanceof ServerPlayer) {
            for (int i = 0; i < blockEntity.itemStackHandler.getSlots(); i++){
                ItemStack stack = blockEntity.itemStackHandler.extractItem(i, 64, false);

                if (!stack.isEmpty()) {
                    if (!player.getInventory().add(stack)) {
                        player.drop(stack, false);
                    }
                }
            }

            ItemStack stack = this.getCarried();

            if (!stack.isEmpty()) {
                if (player.isAlive() && !((ServerPlayer)player).hasDisconnected()) {
                    player.getInventory().placeItemBackInInventory(stack);

                } else {
                    player.drop(stack, false);
                }

                this.setCarried(ItemStack.EMPTY);
            }
        }
    }

    public void playAtmSound() {
        RandomSource randomsource = this.level.random;
        this.blockEntity.getLevel().playLocalSound(this.blockEntity.getBlockPos(), ModSounds.ATM_USE.get(), SoundSource.BLOCKS, 0.4F, randomsource.nextFloat() * 0.1F + 1.0F, false);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        ItemStack quickMovedStack = ItemStack.EMPTY;
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        if (quickMovedSlot.hasItem()) {
            ItemStack rawStack = quickMovedSlot.getItem();
            quickMovedStack = rawStack.copy();

            if (quickMovedSlotIndex == 1) {
                if (this.blockEntity.getRandomConvertScreen()) {
                    ItemStack outputStackTemplate = blockEntity.getRandomConvertRecipe()[1];
                    int randomConvertButtonPressed = this.blockEntity.getRandomConvertButtonPressed();
                    int totalOutputCount = this.blockEntity.quickRandomConvertCurrency(outputStackTemplate, randomConvertButtonPressed);
                    ItemStack itemsToMove = outputStackTemplate.copy();
                    itemsToMove.setCount(totalOutputCount);

                    if (!this.insertItemStacked(itemsToMove, 2, 38)) {
                        return ItemStack.EMPTY;
                    }

                    quickMovedSlot.setByPlayer(ItemStack.EMPTY);
                }

            } else if (quickMovedSlotIndex == 0) {
                if (!this.moveItemStackTo(rawStack, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }

            } else if (this.blockEntity.getRandomConvertScreen()) {
                if (rawStack.getItem() == Items.DIAMOND || rawStack.getItem() == ModItems.RUBY.get() || rawStack.getItem() == ModItems.AMBER.get()) {
                    if (!this.moveItemStackTo(rawStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }

            } else if (this.blockEntity.getToolConvertScreen()) {
                if (rawStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || rawStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) || rawStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                    if (!this.moveItemStackTo(rawStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }

            } else if (quickMovedSlotIndex >= 2 && quickMovedSlotIndex < 29) {
                if (!this.moveItemStackTo(rawStack, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }

            } else if (quickMovedSlotIndex >= 29 && quickMovedSlotIndex < 38 && !this.moveItemStackTo(rawStack, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (rawStack.isEmpty()) {
                quickMovedSlot.setByPlayer(ItemStack.EMPTY);
            }

            quickMovedSlot.setChanged();

            if (rawStack.getCount() == quickMovedStack.getCount()) {
                return ItemStack.EMPTY;
            }

            quickMovedSlot.onTake(player, rawStack);
        }

        return quickMovedStack;
    }

    protected boolean insertItemStacked(ItemStack stack, int startIndex, int endIndex) {
        if (stack.isEmpty()) return false;

        for (int i = startIndex; i < endIndex; i++) {
            Slot slot = this.slots.get(i);

            if (slot.hasItem() && ItemStack.isSameItemSameComponents(slot.getItem(), stack)) {
                int currentStackSize = slot.getItem().getCount();
                int maxSize = Math.min(stack.getMaxStackSize(), slot.getMaxStackSize());
                int amountToTransfer = Math.min(stack.getCount(), maxSize - currentStackSize);

                if (amountToTransfer > 0) {
                    slot.getItem().grow(amountToTransfer);
                    stack.shrink(amountToTransfer);
                    slot.setChanged();
                }

                if (stack.isEmpty()) return true;
            }
        }

        for (int i = startIndex; i < endIndex; i++) {
            Slot slot = this.slots.get(i);

            if (!slot.hasItem()) {
                int maxSize = Math.min(stack.getMaxStackSize(), slot.getMaxStackSize());
                int amountToTransfer = Math.min(stack.getCount(), maxSize);

                if (amountToTransfer > 0) {
                    slot.setByPlayer(stack.split(amountToTransfer));
                    slot.setChanged();
                }

                if (stack.isEmpty()) return true;
            }
        }

        return stack.isEmpty();
    }

    @Override
    public boolean stillValid(Player player) {
        int guiTextureIndex = blockEntity.getGuiTextureIndex();
        Block ATM;

        switch (guiTextureIndex) {
            default -> ATM = ModBlocks.BLACK_ATM.get();
            case 2 -> ATM = ModBlocks.BLUE_ATM.get();
            case 3 -> ATM = ModBlocks.BROWN_ATM.get();
            case 4 -> ATM = ModBlocks.CYAN_ATM.get();
            case 5 -> ATM = ModBlocks.GRAY_ATM.get();
            case 6 -> ATM = ModBlocks.GREEN_ATM.get();
            case 7 -> ATM = ModBlocks.LIGHT_BLUE_ATM.get();
            case 8 -> ATM = ModBlocks.LIGHT_GRAY_ATM.get();
            case 9 -> ATM = ModBlocks.LIME_ATM.get();
            case 10 -> ATM = ModBlocks.MAGENTA_ATM.get();
            case 11 -> ATM = ModBlocks.ORANGE_ATM.get();
            case 12 -> ATM = ModBlocks.PINK_ATM.get();
            case 13 -> ATM = ModBlocks.PURPLE_ATM.get();
            case 14 -> ATM = ModBlocks.RED_ATM.get();
            case 15 -> ATM = ModBlocks.WHITE_ATM.get();
            case 16 -> ATM = ModBlocks.YELLOW_ATM.get();
        }

        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ATM);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        blockEntity.setRandomConvertScreen(true);
        blockEntity.setToolConvertScreen(false);

        if (player instanceof ServerPlayer) {
            for (int i = 0; i < blockEntity.itemStackHandler.getSlots(); i++){
                ItemStack itemstack = blockEntity.itemStackHandler.extractItem(i, 64, false);

                if (!itemstack.isEmpty()) {
                    if (!player.getInventory().add(itemstack)) {
                        player.drop(itemstack, false);
                    }
                }
            }
        }
    }

    private void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 121 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 179));
        }
    }
}