package com.timeshipmodding.villagecraft3essentials.datagen;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;

public class DataBlockStates extends BlockStateProvider {
    public DataBlockStates(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VillageCraft3Essentials.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Block with Item
        blockWithItem(RUBY_BLOCK);
        blockWithItem(AMBER_BLOCK);

        // Drop Experience Block With Item
        dropExperienceBlockWithItem(RUBY_ORE);
        dropExperienceBlockWithItem(DEEPSLATE_RUBY_ORE);
        dropExperienceBlockWithItem(AMBER_ORE);
        dropExperienceBlockWithItem(DEEPSLATE_AMBER_ORE);
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void dropExperienceBlockWithItem(DeferredBlock<DropExperienceBlock> dropExperienceBlockRegistryObject) {
        simpleBlockWithItem(dropExperienceBlockRegistryObject.get(), cubeAll(dropExperienceBlockRegistryObject.get()));
    }
}
