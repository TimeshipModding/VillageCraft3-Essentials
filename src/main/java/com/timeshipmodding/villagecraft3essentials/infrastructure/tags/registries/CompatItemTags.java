package com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CompatItemTags {
    public static TagKey<Item> AQUACULTURE_HOOK_TOOLTIP = ItemTags.create(ResourceLocation.fromNamespaceAndPath("aquaculture", "tooltip"));
    public static TagKey<Item> FARMERSDELIGHT_KNIVES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"));
}
