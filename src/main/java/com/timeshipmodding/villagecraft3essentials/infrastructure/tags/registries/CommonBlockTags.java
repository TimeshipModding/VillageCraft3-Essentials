package com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CommonBlockTags {
    public static TagKey<Block> STORAGE_BLOCKS = createTag("storage_blocks");
    public static TagKey<Block> RUBY_STORAGE_BLOCKS = createTag("storage_blocks/ruby");
    public static TagKey<Block> AMBER_STORAGE_BLOCKS = createTag("storage_blocks/amber");
    public static TagKey<Block> ORES_RUBY = createTag("ores/ruby");
    public static TagKey<Block> ORES_AMBER = createTag("ores/amber");
    public static TagKey<Block> ORES_IN_GROUND_STONE = createTag("ores_in_ground/stone");
    public static TagKey<Block> ORES_IN_GROUND_DEEPSLATE = createTag("ores_in_ground/deepslate");
    public static TagKey<Block> ORE_RATES_SINGULAR = createTag("ore_rates/singular");
    public static TagKey<Block> ORES = createTag("ores");

    private static TagKey<Block> createTag(String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }
}