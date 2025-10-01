package com.timeshipmodding.villagecraft3essentials.util.tags.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static TagKey<Item> MOLE_FOOD = createTag("mole_food");

    private static TagKey<Item> createTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, name));
    }
}
