package com.timeshipmodding.villagecraft3essentials.util.itemhandler;

import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.util.tags.registries.ModItemTags;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class HorseCurrencyArmorItemHandler extends ItemStackHandler {
    private final Horse horse;

    public HorseCurrencyArmorItemHandler(Horse horse) {
        super(2);
        this.horse = horse;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == 0) {
            return stack.getItem() instanceof AnimalArmorItem;
        }

        return super.isItemValid(slot, stack);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (slot == 0 && stack.getItem() instanceof AnimalArmorItem) {
            if (!canEquipGeneral(horse, stack)) {
                return stack;
            }
        }

        return super.insertItem(slot, stack, simulate);
    }

    private boolean canEquipGeneral(Horse horse, ItemStack armor) {
        return false;
    }

    public boolean canPlayerEquip(Player player, ItemStack stack) {
        if (LuckpermsMethods.isInGroup(player, "villagecraftcity") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(player, "villagecraftcity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
            return false;

        } else if (LuckpermsMethods.isInGroup(player, "grippercity") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(player, "grippercity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
            return false;

        } else if (LuckpermsMethods.isInGroup(player, "ambercaves") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(player, "ambercaves") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) {
            return false;
        }

        return true;
    }
}