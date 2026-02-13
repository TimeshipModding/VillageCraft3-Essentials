package com.timeshipmodding.villagecraft3essentials.content.block.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.block.AtmBlock;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.sound.registries.ModSounds;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VillageCraft3Essentials.MODID);

    // Blocks
    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<DropExperienceBlock> RUBY_ORE = registerBlock("ruby_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7),BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7),BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F)));
    public static final DeferredBlock<Block> AMBER_BLOCK = registerBlock("amber_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<DropExperienceBlock> AMBER_ORE = registerBlock("amber_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7),BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_AMBER_ORE = registerBlock("deepslate_amber_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F)));
    public static final DeferredBlock<AtmBlock> BLACK_ATM = registerBlock("black_atm", () -> new AtmBlock(1, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.BLACK).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> BLUE_ATM = registerBlock("blue_atm", () -> new AtmBlock(2, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.BLUE).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> BROWN_ATM = registerBlock("brown_atm", () -> new AtmBlock(3, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.BROWN).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> CYAN_ATM = registerBlock("cyan_atm", () -> new AtmBlock(4, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.CYAN).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> GRAY_ATM = registerBlock("gray_atm", () -> new AtmBlock(5, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.GRAY).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> GREEN_ATM = registerBlock("green_atm", () -> new AtmBlock(6, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.GREEN).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> LIGHT_BLUE_ATM = registerBlock("light_blue_atm", () -> new AtmBlock(7 ,BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.LIGHT_BLUE).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> LIGHT_GRAY_ATM = registerBlock("light_gray_atm", () -> new AtmBlock(8, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.LIGHT_GRAY).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> LIME_ATM = registerBlock("lime_atm", () -> new AtmBlock(9, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.LIME).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> MAGENTA_ATM = registerBlock("magenta_atm", () -> new AtmBlock(10, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.MAGENTA).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> ORANGE_ATM = registerBlock("orange_atm", () -> new AtmBlock(11, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.ORANGE).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> PINK_ATM = registerBlock("pink_atm", () -> new AtmBlock(12, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.PINK).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> PURPLE_ATM = registerBlock("purple_atm", () -> new AtmBlock(13, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.PURPLE).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> RED_ATM = registerBlock("red_atm", () -> new AtmBlock(14, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.RED).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> WHITE_ATM = registerBlock("white_atm", () -> new AtmBlock(15, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.WHITE).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<AtmBlock> YELLOW_ATM = registerBlock("yellow_atm", () -> new AtmBlock(16, BlockBehaviour.Properties.of().noOcclusion().mapColor(DyeColor.YELLOW).requiresCorrectToolForDrops().strength(1.8F)));
    public static final DeferredBlock<Block> DIAMOND_CORE = registerBlock("diamond_core", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).lightLevel(p_152688_ -> 15).noOcclusion().noLootTable().sound(ModSounds.CORE_BLOCK_SOUNDS)));
    public static final DeferredBlock<Block> RUBY_CORE = registerBlock("ruby_core", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).lightLevel(p_152688_ -> 15).noOcclusion().noLootTable().sound(ModSounds.CORE_BLOCK_SOUNDS)));
    public static final DeferredBlock<Block> AMBER_CORE = registerBlock("amber_core", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F).lightLevel(p_152688_ -> 15).noOcclusion().noLootTable().sound(ModSounds.CORE_BLOCK_SOUNDS)));

    // Register Methods
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}