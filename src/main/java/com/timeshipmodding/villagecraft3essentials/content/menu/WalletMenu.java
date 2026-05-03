package com.timeshipmodding.villagecraft3essentials.content.menu;

import com.timeshipmodding.villagecraft3essentials.content.menu.registries.ModMenus;
import com.timeshipmodding.villagecraft3essentials.infrastructure.helper.CraftingMenuHelper;
import com.timeshipmodding.villagecraft3essentials.infrastructure.handler.item.slot.WalletSlotItemHandler;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.items.ItemStackHandler;

public class WalletMenu extends RecipeBookMenu<CraftingInput, CraftingRecipe>  {
    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 2, 2);
    private final ResultContainer resultSlots = new ResultContainer();
    private final Player owner;

    public WalletMenu(int containerId, Inventory playerInventory, FriendlyByteBuf byteBuf) {
        this(containerId, playerInventory, new ItemStackHandler(21));
    }

    public WalletMenu(int containerId, Inventory playerInv, ItemStackHandler handler) {
        super(ModMenus.WALLET_MENU.get(), containerId);
        this.owner = playerInv.player;
        this.addSlot(new ResultSlot(playerInv.player, this.craftSlots, this.resultSlots, 0, 154, 28));

        // Crafting Slots
        for (int row = 0; row < 2; ++row) {
            for (int col = 0; col < 2; ++col) {
                this.addSlot(new Slot(this.craftSlots, col + row * 2, 98 + col * 18, 18 + row * 18));
            }
        }

        // Wallet Slots
        for (int row = 0; row < 4; ++row) {
            for (int col = 0; col < 4; ++col) {
                this.addSlot(new WalletSlotItemHandler(handler, col + row * 4, 8 + (col * 18), 8 + (row * 18)));
            }
        }

        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents contents) {
        this.owner.getInventory().fillStackedContents(contents);

        for (int i = 5; i <= 20; i++) {
            ItemStack stack = this.getSlot(i).getItem();

            if (!stack.isEmpty()) {
                contents.accountStack(stack);
            }
        }
    }

    @Override
    public void handlePlacement(boolean placeAll, RecipeHolder<?> recipe, ServerPlayer player) {
        if (recipe.value() instanceof CraftingRecipe craftingRecipe) {
            NonNullList<Ingredient> ingredients = craftingRecipe.getIngredients();

            for (int i = 0; i < ingredients.size(); i++) {
                Ingredient ingredient = ingredients.get(i);

                if (ingredient.isEmpty()) {
                    continue;
                }

                Slot gridSlot = this.getSlot(i + 1);

                if (gridSlot.hasItem()) {
                    continue;
                }

                for (int walletIdx = 5; walletIdx <= 20; walletIdx++) {
                    Slot walletSlot = this.getSlot(walletIdx);
                    ItemStack walletStack = walletSlot.getItem();

                    if (!walletStack.isEmpty() && ingredient.test(walletStack)) {
                        gridSlot.set(walletStack.copyWithCount(1));
                        walletStack.shrink(1);
                        walletSlot.setChanged();
                        gridSlot.setChanged();
                        break;
                    }
                }
            }
        }

        new net.minecraft.recipebook.ServerPlaceRecipe<>(this).recipeClicked(player, (RecipeHolder<CraftingRecipe>) recipe, placeAll);
        this.broadcastChanges();
    }

    @Override
    public void clearCraftingContent() {
        this.resultSlots.clearContent();
        this.craftSlots.clearContent();
    }

    @Override
    public boolean recipeMatches(RecipeHolder<CraftingRecipe> recipeHolder) {
        return (recipeHolder.value()).matches(this.craftSlots.asCraftInput(), this.owner.level());
    }

    @Override
    public void slotsChanged(Container inventory) {
        CraftingMenuHelper.getSlotChangedCraftingGrid(this, this.owner.level(), this.owner, this.craftSlots, this.resultSlots, null);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.resultSlots.clearContent();

        if (!player.level().isClientSide) {
            this.clearContainer(player, this.craftSlots);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 21, 57, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }

            if (index >= 1 && index <= 4) {
                if (!this.moveItemStackTo(itemstack1, 21, 57, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (index >= 5 && index <= 20) {
                if (!this.moveItemStackTo(itemstack1, 21, 57, false)) {
                    return ItemStack.EMPTY;
                }
            }

            else if (index >= 21 && index <= 56) {
                if (itemstack1.is(ModItemTags.WALLET_ACCEPTED_ITEMS)) {
                    if (!this.moveItemStackTo(itemstack1, 5, 21, false)) {
                        return ItemStack.EMPTY;
                    }
                }

                else if (index < 48) {
                    if (!this.moveItemStackTo(itemstack1, 48, 57, false)) return ItemStack.EMPTY;

                } else {
                    if (!this.moveItemStackTo(itemstack1, 21, 48, false)) return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);

            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);

            if (index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    @Override
    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    public boolean shouldMoveToInventory(int slotIndex) {
        return slotIndex != this.getResultSlotIndex();
    }

    private void addPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInv) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }
}