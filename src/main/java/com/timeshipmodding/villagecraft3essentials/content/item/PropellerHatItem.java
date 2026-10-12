package com.timeshipmodding.villagecraft3essentials.content.item;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class PropellerHatItem extends AccessoryItem {
    public PropellerHatItem(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (reference.entity() instanceof Player player) {
            Random random = new Random();
            Vec3 currentMovement = player.getDeltaMovement();
            double x = currentMovement.x + random.nextDouble(-0.2, 0.21);
            double y = currentMovement.y + random.nextDouble(0, 0.126);
            double z = currentMovement.z + random.nextDouble(-0.2, 0.21);
            player.setDeltaMovement(x, y, z);
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (reference.entity() instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, -1, 0, false, false));
        }

        super.onEquip(stack, reference);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (reference.entity() instanceof Player player) {
            player.removeEffect(MobEffects.SLOW_FALLING);
        }

        super.onUnequip(stack, reference);
    }
}