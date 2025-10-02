package com.timeshipmodding.villagecraft3essentials.content.item.registries;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties WORM = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05f).effect(() -> new MobEffectInstance(MobEffects.POISON, 200, 0), 0.8f).build();
    public static final FoodProperties COOKED_WORM = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).build();
}
