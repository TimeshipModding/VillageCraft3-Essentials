package com.timeshipmodding.villagecraft3essentials.datagen.loot;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.loot.modifier.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;

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
                Blocks.DIAMOND_BLOCK.asItem(), RUBY_BLOCK.get().asItem(), AMBER_BLOCK.get().asItem()));
        add("swap_diamond_ore", new SwapItemLootModifier(new LootItemCondition[]{
                InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIAMOND_ORE)).build()},
                Blocks.DIAMOND_ORE.asItem(), RUBY_ORE.get().asItem(), AMBER_ORE.get().asItem()));
        add("swap_deepslate_diamond_ore", new SwapItemLootModifier(new LootItemCondition[]{
                InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_DIAMOND_ORE)).build()},
                Blocks.DEEPSLATE_DIAMOND_ORE.asItem(), DEEPSLATE_RUBY_ORE.get().asItem(), DEEPSLATE_AMBER_ORE.get().asItem()));
        add("swap_diamond_shovel", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_SHOVEL, RUBY_SHOVEL.get(), AMBER_SHOVEL.get()));
        add("swap_diamond_pickaxe", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_PICKAXE, RUBY_PICKAXE.get(), AMBER_PICKAXE.get()));
        add("swap_diamond_axe", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_AXE, RUBY_AXE.get(), AMBER_AXE.get()));
        add("swap_diamond_hoe", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_HOE, RUBY_HOE.get(), AMBER_HOE.get()));
        add("swap_diamond_sword", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_SWORD, RUBY_SWORD.get(), AMBER_SWORD.get()));
        add("swap_diamond_helmet", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_HELMET, RUBY_HELMET.get(), AMBER_HELMET.get()));
        add("swap_diamond_chestplate", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_CHESTPLATE, RUBY_CHESTPLATE.get(), AMBER_CHESTPLATE.get()));
        add("swap_diamond_leggings", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_LEGGINGS, RUBY_LEGGINGS.get(), AMBER_LEGGINGS.get()));
        add("swap_diamond_boots", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_BOOTS, RUBY_BOOTS.get(), AMBER_BOOTS.get()));
        add("swap_diamond_horse_armor", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND_HORSE_ARMOR, RUBY_HORSE_ARMOR.get(), AMBER_HORSE_ARMOR.get()));
        add("swap_diamond", new SwapItemLootModifier(new LootItemCondition[]{},
                Items.DIAMOND, RUBY.get(), AMBER.get()));

        // Replace Non Player Drop Item Loot Modifiers
        add("replace_diamond_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIAMOND_ORE).build()}, CRACKED_DIAMOND.get()));
        add("replace_deepslate_diamond_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_DIAMOND_ORE).build()}, CRACKED_DIAMOND.get()));
        add("replace_ruby_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(RUBY_ORE.get()).build()}, CRACKED_RUBY.get()));
        add("replace_deepslate_ruby_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DEEPSLATE_RUBY_ORE.get()).build()}, CRACKED_RUBY.get()));
        add("replace_amber_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(AMBER_ORE.get()).build()}, CRACKED_AMBER.get()));
        add("replace_deepslate_amber_ore_item_drop", new ReplaceNonPlayerItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DEEPSLATE_AMBER_ORE.get()).build()}, CRACKED_AMBER.get()));
    }
}