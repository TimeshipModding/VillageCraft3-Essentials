package com.timeshipmodding.villagecraft3essentials.datagen.loot;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.loot.modifier.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class DataGlobalLootModifiers extends GlobalLootModifierProvider {
    public DataGlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, VillageCraft3Essentials.MODID);
    }

    @Override
    protected void start() {
        // Add Item Loot Modifiers
        add("worm_from_coarse_dirt", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.COARSE_DIRT).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, ModItems.WORM.get()));
        add("worm_from_dirt", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIRT).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, ModItems.WORM.get()));
        add("worm_from_grass_block", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS_BLOCK).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, ModItems.WORM.get()));
        add("worm_from_moss_block", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.MOSS_BLOCK).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, ModItems.WORM.get()));
        add("worm_from_podzol", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.PODZOL).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, ModItems.WORM.get()));
        add("worm_from_rooted_dirt", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.ROOTED_DIRT).build(),
                LootItemRandomChanceCondition.randomChance(0.0225f).build()}, ModItems.WORM.get()));

        // Swap Item Loot Modifiers
        add("swap_diamond_block", new SwapItemLootModifier(new LootItemCondition[]{}, Blocks.DIAMOND_BLOCK.asItem(), ModBlocks.RUBY_BLOCK.get().asItem(), ModBlocks.AMBER_BLOCK.get().asItem()));
        add("swap_diamond_ore", new SwapItemLootModifier(new LootItemCondition[]{}, Blocks.DIAMOND_ORE.asItem(), ModBlocks.RUBY_ORE.get().asItem(), ModBlocks.AMBER_ORE.get().asItem()));
        add("swap_deepslate_diamond_ore", new SwapItemLootModifier(new LootItemCondition[]{}, Blocks.DEEPSLATE_DIAMOND_ORE.asItem(), ModBlocks.DEEPSLATE_RUBY_ORE.get().asItem(), ModBlocks.DEEPSLATE_AMBER_ORE.get().asItem()));
        add("swap_diamond_shovel", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_SHOVEL, ModItems.RUBY_SHOVEL.get(), ModItems.AMBER_SHOVEL.get()));
        add("swap_diamond_pickaxe", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_PICKAXE, ModItems.RUBY_PICKAXE.get(), ModItems.AMBER_PICKAXE.get()));
        add("swap_diamond_axe", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_AXE, ModItems.RUBY_AXE.get(), ModItems.AMBER_AXE.get()));
        add("swap_diamond_hoe", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_HOE, ModItems.RUBY_HOE.get(), ModItems.AMBER_HOE.get()));
        add("swap_diamond_sword", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_SWORD, ModItems.RUBY_SWORD.get(), ModItems.AMBER_SWORD.get()));
        add("swap_diamond_helmet", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_HELMET, ModItems.RUBY_HELMET.get(), ModItems.AMBER_HELMET.get()));
        add("swap_diamond_chestplate", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_CHESTPLATE, ModItems.RUBY_CHESTPLATE.get(), ModItems.AMBER_CHESTPLATE.get()));
        add("swap_diamond_leggings", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_LEGGINGS, ModItems.RUBY_LEGGINGS.get(), ModItems.AMBER_LEGGINGS.get()));
        add("swap_diamond_boots", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_BOOTS, ModItems.RUBY_BOOTS.get(), ModItems.AMBER_BOOTS.get()));
        add("swap_diamond_horse_armor", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND_HORSE_ARMOR, ModItems.RUBY_HORSE_ARMOR.get(), ModItems.AMBER_HORSE_ARMOR.get()));
        add("swap_diamond", new SwapItemLootModifier(new LootItemCondition[]{}, Items.DIAMOND, ModItems.RUBY.get(), ModItems.AMBER.get()));
    }
}