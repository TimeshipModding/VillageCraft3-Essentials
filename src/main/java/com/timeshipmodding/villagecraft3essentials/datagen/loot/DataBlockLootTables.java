package com.timeshipmodding.villagecraft3essentials.datagen.loot;

import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

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
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
