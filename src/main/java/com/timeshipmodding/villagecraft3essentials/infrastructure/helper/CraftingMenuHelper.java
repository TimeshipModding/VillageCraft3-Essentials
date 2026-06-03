package com.timeshipmodding.villagecraft3essentials.infrastructure.helper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class CraftingMenuHelper extends CraftingMenu {
    public CraftingMenuHelper(int containerId, Inventory playerInventory) {
        super(containerId, playerInventory);
    }

    public static void getSlotChangedCraftingGrid(AbstractContainerMenu menu, Level level, Player player, CraftingContainer craftSlots, ResultContainer resultSlots, @Nullable RecipeHolder<CraftingRecipe> recipe) {
        CraftingMenu.slotChangedCraftingGrid(menu, level, player, craftSlots, resultSlots, recipe);
    }
}