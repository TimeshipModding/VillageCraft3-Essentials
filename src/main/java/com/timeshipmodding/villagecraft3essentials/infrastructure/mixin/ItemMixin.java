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
            if (stack.getItem() instanceof AnimalArmorItem || !(stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || stack.is(ModItemTags.JADE_CONVERTIBLE_TOOLS) || stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS) || stack.is(ModItemTags.SILICON_CONVERTIBLE_TOOLS) || stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS))) {
                return;
            }

            if (!level.isClientSide() && entity instanceof Player player && ModList.get().isLoaded("luckperms") && stack.getItem() instanceof ArmorItem armorItem) {
                ItemStack equippedStack = player.getItemBySlot(armorItem.getType().getSlot());
                boolean inVillageCraftCity = LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
                boolean inOasis = LuckpermsMethods.isInGroup(player, ServerConfig.OASIS_GROUP_NAME.get());
                boolean inSwedishFurnitureStore = LuckpermsMethods.isInGroup(player, ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get());
                boolean inLondon = LuckpermsMethods.isInGroup(player, ServerConfig.LONDON_GROUP_NAME.get());
                boolean inBlackWater = LuckpermsMethods.isInGroup(player, ServerConfig.BLACKWATER_GROUP_NAME.get());

                if (inVillageCraftCity && equippedStack == stack  && !equippedStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS)) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);

                } else if (inOasis && equippedStack == stack && !equippedStack.is(ModItemTags.JADE_CONVERTIBLE_TOOLS)) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);

                } else if (inSwedishFurnitureStore && equippedStack == stack && !equippedStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);

                } else if (inLondon && equippedStack == stack && !equippedStack.is(ModItemTags.SILICON_CONVERTIBLE_TOOLS)) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);

                } else if (inBlackWater && equippedStack == stack && !equippedStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) {
                    player.displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    player.setItemSlot(armorItem.getType().getSlot(), ItemStack.EMPTY);
                    player.drop(stack, true);
                }
            }
        }
    }
}