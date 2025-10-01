package com.timeshipmodding.villagecraft3essentials.datagen.loot;

import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;

public class DataBlockLootTables extends BlockLootSubProvider {
    public DataBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        // Drop Self
        dropSelf(RUBY_BLOCK.get());
        dropSelf(AMBER_BLOCK.get());

        // Ore Drops
        this.add(RUBY_ORE.get(), block -> createOreDrop(RUBY_ORE.get(), RUBY.get()));
        this.add(DEEPSLATE_RUBY_ORE.get(), block -> createOreDrop(DEEPSLATE_RUBY_ORE.get(), RUBY.get()));
        this.add(AMBER_ORE.get(), block -> createOreDrop(AMBER_ORE.get(), AMBER.get()));
        this.add(DEEPSLATE_AMBER_ORE.get(), block -> createOreDrop(DEEPSLATE_AMBER_ORE.get(), AMBER.get()));

        // ATM drops
        add(BLACK_ATM.get(), block -> createATMTable(BLACK_ATM.get()));
        add(BLUE_ATM.get(), block -> createATMTable(BLUE_ATM.get()));
        add(BROWN_ATM.get(), block -> createATMTable(BROWN_ATM.get()));
        add(CYAN_ATM.get(), block -> createATMTable(CYAN_ATM.get()));
        add(GRAY_ATM.get(), block -> createATMTable(GRAY_ATM.get()));
        add(GREEN_ATM.get(), block -> createATMTable(GREEN_ATM.get()));
        add(LIGHT_BLUE_ATM.get(), block -> createATMTable(LIGHT_BLUE_ATM.get()));
        add(LIME_ATM.get(), block -> createATMTable(LIME_ATM.get()));
        add(LIGHT_GRAY_ATM.get(), block -> createATMTable(LIGHT_GRAY_ATM.get()));
        add(MAGENTA_ATM.get(), block -> createATMTable(MAGENTA_ATM.get()));
        add(ORANGE_ATM.get(), block -> createATMTable(ORANGE_ATM.get()));
        add(PINK_ATM.get(), block -> createATMTable(PINK_ATM.get()));
        add(PURPLE_ATM.get(), block -> createATMTable(PURPLE_ATM.get()));
        add(RED_ATM.get(), block -> createATMTable(RED_ATM.get()));
        add(WHITE_ATM.get(), block -> createATMTable(WHITE_ATM.get()));
        add(YELLOW_ATM.get(), block -> createATMTable(YELLOW_ATM.get()));
    }

    protected LootTable.Builder createATMTable(Block doorBlock) {
        return this.createSinglePropConditionTable(doorBlock, DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
