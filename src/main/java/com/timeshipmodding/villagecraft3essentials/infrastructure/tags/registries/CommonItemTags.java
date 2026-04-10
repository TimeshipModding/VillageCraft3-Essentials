package com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CommonItemTags {
    public static TagKey<Item> TOOLS_FISHING_ROD = createTag("tools/fishing_rod");
    public static TagKey<Item> TOOLS_BRUSH = createTag("tools/brush");

    private static TagKey<Item> createTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }
}