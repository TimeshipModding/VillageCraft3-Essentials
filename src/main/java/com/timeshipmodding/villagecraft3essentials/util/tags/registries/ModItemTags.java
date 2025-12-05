package com.timeshipmodding.villagecraft3essentials.util.tags.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static TagKey<Item> MOLE_FOOD = createTag("mole_food");
    public static TagKey<Item> DIAMOND_CONVERTIBLE_TOOLS = createTag("diamond_convertible_tools");
    public static TagKey<Item> RUBY_CONVERTIBLE_TOOLS = createTag("diamond_convertible_tools");
    public static TagKey<Item> AMBER_CONVERTIBLE_TOOLS = createTag("diamond_convertible_tools");

    private static TagKey<Item> createTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, name));
    }
}
