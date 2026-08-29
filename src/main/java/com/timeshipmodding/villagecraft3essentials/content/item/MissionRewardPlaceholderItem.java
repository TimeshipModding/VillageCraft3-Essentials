package com.timeshipmodding.villagecraft3essentials.content.item;

import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

public class MissionRewardPlaceholderItem extends Item {
    public MissionRewardPlaceholderItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (ModList.get().isLoaded("luckperms") && ModList.get().isLoaded("brassworksmissions") && ServerConfig.ENABLE_BRASSWORKS_MISSIONS_OVERRIDE.get()) {
            if (!level.isClientSide && entity instanceof Player player) {
                if (ServerConfig.ENABLE_BRASSWORKS_MISSIONS_OVERRIDE.get()) {
                    player.getInventory().setItem(slotId, ItemStack.EMPTY);
                }
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if (ModList.get().isLoaded("luckperms") && ModList.get().isLoaded("brassworksmissions")) {
            if (!entity.level().isClientSide) {
                if (ServerConfig.ENABLE_BRASSWORKS_MISSIONS_OVERRIDE.get()) {
                    entity.discard();
                }
            }
        }

        return true;
    }
}