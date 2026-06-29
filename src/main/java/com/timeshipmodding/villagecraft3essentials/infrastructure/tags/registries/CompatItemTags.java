package com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CompatItemTags {
    public static TagKey<Item> AQUACULTURE_HOOK_TOOLTIP = ItemTags.create(ResourceLocation.fromNamespaceAndPath("aquaculture", "tooltip"));
    public static TagKey<Item> FARMERSDELIGHT_KNIVES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"));
    public static TagKey<Item> RPG_SERIES_LOOT_TIER_TIER_2_WEAPONS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("rpg_series", "loot_tier/tier_2_weapons"));
    public static TagKey<Item> RPG_SERIES_WEAPON_TYPE_SPEAR = ItemTags.create(ResourceLocation.fromNamespaceAndPath("rpg_series", "weapon_type/spear"));
    public static TagKey<Item> RPG_SERIES_ARCHETYPE_MELEE_DAMAGE_WEAPON = ItemTags.create(ResourceLocation.fromNamespaceAndPath("rpg_series", "archetype/melee_damage_weapon"));
    public static TagKey<Item> SPELL_ENGINE_HANDHELD = ItemTags.create(ResourceLocation.fromNamespaceAndPath("spell_engine", "handheld"));
    public static TagKey<Item> ARCHERS_SPEARS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("archers", "spears"));
}
