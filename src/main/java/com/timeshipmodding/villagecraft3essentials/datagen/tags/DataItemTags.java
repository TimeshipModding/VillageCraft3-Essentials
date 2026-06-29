package com.timeshipmodding.villagecraft3essentials.datagen.tags;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;
import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;
import static com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags.*;
import static com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.CommonItemTags.*;
import static com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.CompatItemTags.*;

import static net.minecraft.tags.ItemTags.*;
import static net.minecraft.world.item.Items.*;

public class DataItemTags extends ItemTagsProvider {
    public DataItemTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, VillageCraft3Essentials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Minecraft Tags
        tag(TRIMMABLE_ARMOR)
                .add(RUBY_HELMET.get())
                .add(RUBY_CHESTPLATE.get())
                .add(RUBY_LEGGINGS.get())
                .add(RUBY_BOOTS.get())
                .add(AMBER_HELMET.get())
                .add(AMBER_CHESTPLATE.get())
                .add(AMBER_LEGGINGS.get())
                .add(AMBER_BOOTS.get());
        tag(TRIM_MATERIALS)
                .add(RUBY.get())
                .add(AMBER.get());
        tag(BEACON_PAYMENT_ITEMS)
                .add(RUBY.get())
                .add(AMBER.get());
        tag(SHOVELS)
                .add(RUBY_SHOVEL.get())
                .add(AMBER_SHOVEL.get());
        tag(PICKAXES)
                .add(RUBY_PICKAXE.get())
                .add(AMBER_PICKAXE.get());
        tag(AXES)
                .add(RUBY_AXE.get())
                .add(AMBER_AXE.get());
        tag(HOES)
                .add(RUBY_HOE.get())
                .add(AMBER_HOE.get());
        tag(SWORDS)
                .add(RUBY_SWORD.get())
                .add(AMBER_SWORD.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));
        tag(FOOT_ARMOR)
                .add(RUBY_BOOTS.get())
                .add(AMBER_BOOTS.get());
        tag(LEG_ARMOR)
                .add(RUBY_LEGGINGS.get())
                .add(AMBER_LEGGINGS.get());
        tag(CHEST_ARMOR)
                .add(RUBY_CHESTPLATE.get())
                .add(AMBER_CHESTPLATE.get());
        tag(HEAD_ARMOR)
                .add(RUBY_HELMET.get())
                .add(AMBER_HELMET.get());
        tag(BREAKS_DECORATED_POTS)
                .add(POLICE_BATON.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));
        tag(FIRE_ASPECT_ENCHANTABLE)
                .add(POLICE_BATON.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));
        tag(WEAPON_ENCHANTABLE)
                .add(POLICE_BATON.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));;
        tag(MACE_ENCHANTABLE)
                .add(POLICE_BATON.get());
        tag(DURABILITY_ENCHANTABLE)
                .add(POLICE_BATON.get())
                .add(WORM_ON_A_STICK.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_fishing_rod"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_fishing_rod"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_brush"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_brush"));
        tag(FISHING_ENCHANTABLE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_fishing_rod"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_fishing_rod"));
        tag(VANISHING_ENCHANTABLE)
                .add(WORM_ON_A_STICK.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_brush"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_brush"));
        tag(CLUSTER_MAX_HARVESTABLES)
                .add(RUBY_PICKAXE.get())
                .add(AMBER_PICKAXE.get());
        tag(MEAT)
                .add(WORM.get())
                .add(COOKED_WORM.get());
        tag(SHARP_WEAPON_ENCHANTABLE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));

        // Common Tags
        tag(TOOLS_FISHING_ROD)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_fishing_rod"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_fishing_rod"));
        tag(TOOLS_BRUSH)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_brush"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_brush"));
        tag(STORAGE_BLOCKS)
                .add(RUBY_BLOCK.asItem())
                .add(AMBER_BLOCK.asItem());
        tag(RUBY_STORAGE_BLOCKS)
                .add(RUBY_BLOCK.asItem());
        tag(AMBER_STORAGE_BLOCKS)
                .add(AMBER_BLOCK.asItem());
        tag(ORES_RUBY)
                .add(RUBY_ORE.asItem())
                .add(DEEPSLATE_RUBY_ORE.asItem());
        tag(ORES_AMBER)
                .add(AMBER_ORE.asItem())
                .add(DEEPSLATE_AMBER_ORE.asItem());
        tag(ORES_IN_GROUND_STONE)
                .add(RUBY_ORE.asItem())
                .add(AMBER_ORE.asItem());
        tag(ORES_IN_GROUND_DEEPSLATE)
                .add(DEEPSLATE_RUBY_ORE.asItem())
                .add(DEEPSLATE_AMBER_ORE.asItem());
        tag(ORE_RATES_SINGULAR)
                .add(RUBY_ORE.asItem())
                .add(DEEPSLATE_RUBY_ORE.asItem())
                .add(AMBER_ORE.asItem())
                .add(DEEPSLATE_AMBER_ORE.asItem());
        tag(ORES)
                .add(RUBY_ORE.asItem())
                .add(DEEPSLATE_RUBY_ORE.asItem())
                .add(AMBER_ORE.asItem())
                .add(DEEPSLATE_AMBER_ORE.asItem());
        tag(TOOLS_MINING_TOOL)
                .add(RUBY_PICKAXE.get())
                .add(AMBER_PICKAXE.get());
        tag(TOOLS_MELEE_WEAPON)
                .add(RUBY_AXE.get())
                .add(RUBY_SWORD.get())
                .add(AMBER_AXE.get())
                .add(AMBER_SWORD.get())
                .add(POLICE_BATON.get());
        tag(TOOLS_MACE)
                .add(POLICE_BATON.get());
        tag(FOODS_FOOD_POISONING)
                .add(WORM.get());
        tag(FOODS_RAW_MEAT)
                .add(WORM.get());
        tag(FOODS_COOKED_MEAT)
                .add(COOKED_WORM.get());
        tag(ANIMAL_FOODS)
                .add(WORM.get())
                .add(COOKED_WORM.get());
        tag(GEMS)
                .add(RUBY.get())
                .add(AMBER.get());
        tag(RUBY_GEMS)
                .add(RUBY.get());
        tag(AMBER_GEMS)
                .add(AMBER.get());

        // VillageCraft 3 Essentials Tags
        tag(MOLE_FOOD)
                .add(WORM.get())
                .add(COOKED_WORM.get());
        tag(DIAMOND_CONVERTIBLE_TOOLS)
                .add(DIAMOND_SWORD)
                .add(DIAMOND_SHOVEL)
                .add(DIAMOND_PICKAXE)
                .add(DIAMOND_AXE)
                .add(DIAMOND_HOE)
                .add(DIAMOND_HELMET)
                .add(DIAMOND_CHESTPLATE)
                .add(DIAMOND_LEGGINGS)
                .add(DIAMOND_BOOTS)
                .add(DIAMOND_HORSE_ARMOR)
                .addOptional(ResourceLocation.fromNamespaceAndPath("archers", "diamond_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("farmersdelight", "diamond_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("aquaculture", "diamond_fillet_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("aquaculture", "diamond_fishing_rod"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("aquaculture", "diamond_hook"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush"));
        tag(RUBY_CONVERTIBLE_TOOLS)
                .add(RUBY_SWORD.get())
                .add(RUBY_SHOVEL.get())
                .add(RUBY_PICKAXE.get())
                .add(RUBY_AXE.get())
                .add(RUBY_HOE.get())
                .add(RUBY_HELMET.get())
                .add(RUBY_CHESTPLATE.get())
                .add(RUBY_LEGGINGS.get())
                .add(RUBY_BOOTS.get())
                .add(RUBY_HORSE_ARMOR.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_fillet_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_fishing_rod"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_hook"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_brush"));
        tag(AMBER_CONVERTIBLE_TOOLS)
                .add(AMBER_SWORD.get())
                .add(AMBER_SHOVEL.get())
                .add(AMBER_PICKAXE.get())
                .add(AMBER_AXE.get())
                .add(AMBER_HOE.get())
                .add(AMBER_HELMET.get())
                .add(AMBER_CHESTPLATE.get())
                .add(AMBER_LEGGINGS.get())
                .add(AMBER_BOOTS.get())
                .add(AMBER_HORSE_ARMOR.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_fillet_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_fishing_rod"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_hook"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_brush"));
        tag(CURRENCY_GEMS)
                .add(DIAMOND)
                .add(RUBY.get())
                .add(AMBER.get());
        tag(WALLET_ACCEPTED_ITEMS)
                .add(DIAMOND)
                .add(DIAMOND_BLOCK)
                .add(RUBY.get())
                .add(RUBY_BLOCK.get().asItem())
                .add(AMBER.get())
                .add(AMBER_BLOCK.get().asItem())
                .add(EMERALD)
                .add(EMERALD_BLOCK);

        // Farmer's Delight Tags
        tag(FARMERSDELIGHT_KNIVES)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_knife"));
        tag(KNIFE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_knife"));

        // Aquaculture 2 Tags
        tag(AQUACULTURE_HOOK_TOOLTIP)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_hook"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_hook"));
        tag(KNIFE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_fillet_knife"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_fillet_knife"));

        // RPG Series Tags
        tag(RPG_SERIES_LOOT_TIER_TIER_2_WEAPONS)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));
        tag(RPG_SERIES_WEAPON_TYPE_SPEAR)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));
        tag(RPG_SERIES_ARCHETYPE_MELEE_DAMAGE_WEAPON)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));

        // Spell Engine Tags
        tag(SPELL_ENGINE_HANDHELD)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));

        // Archers Tags
        tag(ARCHERS_SPEARS)
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_spear"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_spear"));
    }
}