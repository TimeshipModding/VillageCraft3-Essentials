package com.timeshipmodding.villagecraft3essentials.content.item.registries;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier RUBY = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1561, 8.0F, 3.0F, 10, () -> Ingredient.of(ModItems.RUBY));
    public static final Tier AMBER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1561, 8.0F, 3.0F, 10, () -> Ingredient.of(ModItems.AMBER));
}
