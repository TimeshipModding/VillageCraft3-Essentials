package com.timeshipmodding.villagecraft3essentials.datagen.loot;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.loot.modifier.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModCompatItems.*;

public class DataGlobalLootModifiers extends GlobalLootModifierProvider {
    public DataGlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, VillageCraft3Essentials.MODID);
    }

    @Override
    protected void start() {
        // Add Item Loot Modifiers
        add("worm_from_coarse_dirt", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.COARSE_DIRT).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, WORM.get()));
        add("worm_from_dirt", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIRT).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, WORM.get()));
        add("worm_from_grass_block", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS_BLOCK).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, WORM.get()));
        add("worm_from_moss_block", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.MOSS_BLOCK).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, WORM.get()));
        add("worm_from_podzol", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.PODZOL).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, WORM.get()));
        add("worm_from_rooted_dirt", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.ROOTED_DIRT).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, WORM.get()));

        // Swap Item Loot Modifiers
        add("swap_diamond_block", new SwapItemLootModifier(new LootItemCondition[]{
                InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIAMOND_BLOCK)).build()},
                Blocks.DIAMOND_BLOCK.asItem(), JADE_BLOCK.asItem(), AMBER_BLOCK.asItem(), SILICON_BLOCK.asItem(), RUBY_BLOCK.asItem()));
        add("swap_diamond_ore", new SwapItemLootModifier(new LootItemCondition[]{
                InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIAMOND_ORE)).build()},
                Blocks.DIAMOND_ORE.asItem(), JADE_ORE.asItem(), AMBER_ORE.asItem(), SILICON_ORE.asItem(), RUBY_ORE.asItem()));
        add("swap_deepslate_diamond_ore", new SwapItemLootModifier(new LootItemCondition[]{
                InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_DIAMOND_ORE)).build()},
                Blocks.DEEPSLATE_DIAMOND_ORE.asItem(), DEEPSLATE_JADE_ORE.asItem(), DEEPSLATE_AMBER_ORE.asItem(), DEEPSLATE_SILICON_ORE.asItem(), DEEPSLATE_RUBY_ORE.asItem()));
        add("swap_diamond_shovel", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_SHOVEL, JADE_SHOVEL.get(), AMBER_SHOVEL.get(), SILICON_SHOVEL.get(), RUBY_SHOVEL.get()));
        add("swap_diamond_pickaxe", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_PICKAXE, JADE_PICKAXE.get(), AMBER_PICKAXE.get(), SILICON_PICKAXE.get(), RUBY_PICKAXE.get()));
        add("swap_diamond_axe", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_AXE, JADE_AXE.get(), AMBER_AXE.get(), SILICON_AXE.get(), RUBY_AXE.get()));
        add("swap_diamond_hoe", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_HOE, JADE_HOE.get(), AMBER_HOE.get(), SILICON_HOE.get(), RUBY_HOE.get()));
        add("swap_diamond_sword", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_SWORD, JADE_SWORD.get(), AMBER_SWORD.get(), SILICON_SWORD.get(), RUBY_SWORD.get()));
        add("swap_diamond_helmet", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_HELMET, JADE_HELMET.get(), AMBER_HELMET.get(), SILICON_HELMET.get(), RUBY_HELMET.get()));
        add("swap_diamond_chestplate", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_CHESTPLATE, JADE_CHESTPLATE.get(), AMBER_CHESTPLATE.get(), SILICON_CHESTPLATE.get(), RUBY_CHESTPLATE.get()));
        add("swap_diamond_leggings", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_LEGGINGS, JADE_LEGGINGS.get(), AMBER_LEGGINGS.get(), SILICON_LEGGINGS.get(), RUBY_CHESTPLATE.get()));
        add("swap_diamond_boots", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_BOOTS, JADE_BOOTS.get(), AMBER_BOOTS.get(), SILICON_BOOTS.get(), RUBY_BOOTS.get()));
        add("swap_diamond_horse_armor", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_HORSE_ARMOR, JADE_HORSE_ARMOR.get(), AMBER_HORSE_ARMOR.get(), SILICON_HORSE_ARMOR.get(), RUBY_HORSE_ARMOR.get()));
        add("swap_diamond", new SwapItemLootModifier(new LootItemCondition[]{
                InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIAMOND_ORE)).build(),
                InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_DIAMOND_ORE)).build()},
                Items.DIAMOND, JADE.get(), AMBER.get(), SILICON.get(), RUBY.get()));

        // Swap Compat Item Loot Modifiers
        add("swap_diamond_spear", new SwapCompatItemLootModifier(new LootItemCondition[]{},
                        ResourceLocation.fromNamespaceAndPath("archers", "diamond_spear"), BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "jade_spear")), BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "amber_spear")), BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "silicon_spear")), BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "ruby_spear"))),
                List.of(new ModLoadedCondition("archers")));
        add("swap_diamond_knife", new SwapCompatItemLootModifier(new LootItemCondition[]{},
                        ResourceLocation.fromNamespaceAndPath("farmersdelight", "diamond_knife"), JADE_KNIFE.get(), AMBER_KNIFE.get(), SILICON_KNIFE.get(), RUBY_KNIFE.get()),
                List.of(new ModLoadedCondition("farmersdelight")));
        add("swap_diamond_fishing_rod", new SwapCompatItemLootModifier(new LootItemCondition[]{},
                        ResourceLocation.fromNamespaceAndPath("aquaculture", "diamond_fishing_rod"), JADE_FISHING_ROD.get(), AMBER_FISHING_ROD.get(), SILICON_FISHING_ROD.get(), RUBY_FISHING_ROD.get()),
                List.of(new ModLoadedCondition("aquaculture")));
        add("swap_diamond_fillet_knife", new SwapCompatItemLootModifier(new LootItemCondition[]{},
                        ResourceLocation.fromNamespaceAndPath("aquaculture", "diamond_fillet_knife"), JADE_FILLET_KNIFE.get(), AMBER_FILLET_KNIFE.get(), SILICON_FILLET_KNIFE.get(), RUBY_FILLET_KNIFE.get()),
                List.of(new ModLoadedCondition("aquaculture")));
        add("swap_diamond_hook", new SwapCompatItemLootModifier(new LootItemCondition[]{},
                        ResourceLocation.fromNamespaceAndPath("aquaculture", "diamond_hook"), JADE_HOOK.get(), AMBER_HOOK.get(), SILICON_HOOK.get(), RUBY_HOOK.get()),
                List.of(new ModLoadedCondition("aquaculture")));
        add("swap_diamond_brush", new SwapCompatItemLootModifier(new LootItemCondition[]{},
                        ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush"), JADE_BRUSH.get(), AMBER_BRUSH.get(), SILICON_BRUSH.get(), RUBY_BRUSH.get()),
                List.of(new ModLoadedCondition("betterarcheology")));

        // Replace Non Player Drop Item Loot Modifiers
        add("replace_diamond_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIAMOND_ORE).build()}, CRACKED_DIAMOND.get()));
        add("replace_deepslate_diamond_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_DIAMOND_ORE).build()}, CRACKED_DIAMOND.get()));
        add("replace_jade_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(JADE_ORE.get()).build()}, CRACKED_JADE.get()));
        add("replace_deepslate_jade_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DEEPSLATE_JADE_ORE.get()).build()}, CRACKED_JADE.get()));
        add("replace_amber_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(AMBER_ORE.get()).build()}, CRACKED_AMBER.get()));
        add("replace_deepslate_amber_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DEEPSLATE_AMBER_ORE.get()).build()}, CRACKED_AMBER.get()));
        add("replace_silicon_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(SILICON_ORE.get()).build()}, CRACKED_SILICON.get()));
        add("replace_deepslate_silicon_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DEEPSLATE_SILICON_ORE.get()).build()}, CRACKED_SILICON.get()));
        add("replace_ruby_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(RUBY_ORE.get()).build()}, CRACKED_RUBY.get()));
        add("replace_deepslate_ruby_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DEEPSLATE_RUBY_ORE.get()).build()}, CRACKED_RUBY.get()));
    }
}