package com.timeshipmodding.villagecraft3essentials.infrastructure.itemhandler;

import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class HorseCurrencyArmorItemHandler extends ItemStackHandler {
    public HorseCurrencyArmorItemHandler() {
        super(2);
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
            return stack;
        }

        return super.insertItem(slot, stack, simulate);
    }

    public boolean canPlayerEquip(Player player, ItemStack stack) {
        if (LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get()) && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get()) && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
            return false;

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get()) && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get()) && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
            return false;

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get()) && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get()) && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) {
            return false;
        }

        return true;
    }
}