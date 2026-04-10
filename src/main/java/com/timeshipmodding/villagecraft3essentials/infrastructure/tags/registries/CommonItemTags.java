package com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CommonItemTags {
    public static TagKey<Item> TOOLS_FISHING_ROD = createTag("tools/fishing_rod");
    public static TagKey<Item> TOOLS_BRUSH = createTag("tools/brush");
    public static TagKey<Item> STORAGE_BLOCKS = createTag("storage_blocks");
    public static TagKey<Item> RUBY_STORAGE_BLOCKS = createTag("storage_blocks/ruby");
    public static TagKey<Item> AMBER_STORAGE_BLOCKS = createTag("storage_blocks/amber");
    public static TagKey<Item> ORES_RUBY = createTag("ores/ruby");
    public static TagKey<Item> ORES_AMBER = createTag("ores/amber");
    public static TagKey<Item> ORES_IN_GROUND_STONE = createTag("ores_in_ground/stone");
    public static TagKey<Item> ORES_IN_GROUND_DEEPSLATE = createTag("ores_in_ground/deepslate");
    public static TagKey<Item> ORE_RATES_SINGULAR = createTag("ore_rates/singular");
    public static TagKey<Item> ORES = createTag("ores");
    public static TagKey<Item> TOOLS_MINING_TOOL = createTag("tools/mining_tool");
    public static TagKey<Item> TOOLS_MELEE_WEAPON = createTag("tools/melee_weapon");
    public static TagKey<Item> TOOLS_MACE = createTag("tools/mace");
    public static TagKey<Item> FOODS_FOOD_POISONING = createTag("foods/food_poisoning");
    public static TagKey<Item> FOODS_RAW_MEAT = createTag("foods/raw_meat");
    public static TagKey<Item> FOODS_COOKED_MEAT = createTag("foods/cooked_meat");
    public static TagKey<Item> ANIMAL_FOODS = createTag("animal_foods");
    public static TagKey<Item> GEMS = createTag("gems");
    public static TagKey<Item> RUBY_GEMS = createTag("gems/ruby");
    public static TagKey<Item> AMBER_GEMS = createTag("gems/amber");

    private static TagKey<Item> createTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }
}