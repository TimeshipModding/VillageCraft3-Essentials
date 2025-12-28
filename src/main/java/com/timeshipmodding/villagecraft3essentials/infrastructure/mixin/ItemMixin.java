package com.timeshipmodding.villagecraft3essentials.infrastructure.mixin;

import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected, CallbackInfo ci) {
        if (ModList.get().isLoaded("luckperms")) {
            if (stack.getItem() instanceof AnimalArmorItem) {
                return;
            }

            if (!level.isClientSide() && entity instanceof Player player && ModList.get().isLoaded("luckperms") && stack.getItem() instanceof ArmorItem armorItem) {
                ItemStack equippedStack = player.getItemBySlot(armorItem.getType().getSlot());

                if (LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get()) && equippedStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) && equippedStack == stack || LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get()) && equippedStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS) && equippedStack == stack) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);

                } else if (LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get()) && equippedStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) && equippedStack == stack || LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get()) && equippedStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS) && equippedStack == stack) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);

                } else if (LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get()) && equippedStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) && equippedStack == stack || LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get()) && equippedStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) && equippedStack == stack) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);
                }
            }
        }
    }
}