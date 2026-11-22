package com.timeshipmodding.villagecraft3essentials.compat.creategoggles.registries;

import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.creategoggles.content.block.CurrencyBacktankBlock;
import com.timeshipmodding.villagecraft3essentials.compat.creategoggles.content.item.CurrencyGoggleHelmetItem;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModArmorMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCGRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VillageCraft3Essentials.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VillageCraft3Essentials.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, VillageCraft3Essentials.MODID);

    // Items
    public static final DeferredItem<Item> GOGGLE_JADE_HELMET = ITEMS.register("goggle_jade_helmet", () -> new CurrencyGoggleHelmetItem(ModArmorMaterials.JADE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33)), ResourceLocation.parse(ModArmorMaterials.JADE.getRegisteredName())));
    public static final DeferredItem<Item> GOGGLE_AMBER_HELMET = ITEMS.register("goggle_amber_helmet", () -> new CurrencyGoggleHelmetItem(ModArmorMaterials.AMBER, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33)), ResourceLocation.parse(ModArmorMaterials.AMBER.getRegisteredName())));
    public static final DeferredItem<Item> GOGGLE_SILICON_HELMET = ITEMS.register("goggle_silicon_helmet", () -> new CurrencyGoggleHelmetItem(ModArmorMaterials.SILICON, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33)), ResourceLocation.parse(ModArmorMaterials.SILICON.getRegisteredName())));
    public static final DeferredItem<Item> GOGGLE_RUBY_HELMET = ITEMS.register("goggle_ruby_helmet", () -> new CurrencyGoggleHelmetItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33)), ResourceLocation.parse(ModArmorMaterials.RUBY.getRegisteredName())));
    public static final DeferredItem<Item> JADE_BACKTANK = ITEMS.register("jade_backtank", () -> new BacktankItem(ModArmorMaterials.JADE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33)), ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "jade"), DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "jade_backtank_placeable"))));
    public static final DeferredItem<Item> AMBER_BACKTANK = ITEMS.register("amber_backtank", () -> new BacktankItem(ModArmorMaterials.AMBER, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33)), ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber"), DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber_backtank_placeable"))));
    public static final DeferredItem<Item> SILICON_BACKTANK = ITEMS.register("silicon_backtank", () -> new BacktankItem(ModArmorMaterials.SILICON, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33)), ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "silicon"), DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "silicon_backtank_placeable"))));
    public static final DeferredItem<Item> RUBY_BACKTANK = ITEMS.register("ruby_backtank", () -> new BacktankItem(ModArmorMaterials.RUBY, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33)), ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby"), DeferredHolder.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby_backtank_placeable"))));

    // Blocks
    public static final DeferredBlock<Block> JADE_BACKTANK_BLOCK = BLOCKS.register("jade_backtank", () -> new CurrencyBacktankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(), DeferredHolder.create(Registries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "jade_backtank"))));
    public static final DeferredBlock<Block> AMBER_BACKTANK_BLOCK = BLOCKS.register("amber_backtank", () -> new CurrencyBacktankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(), DeferredHolder.create(Registries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "amber_backtank"))));
    public static final DeferredBlock<Block> SILICON_BACKTANK_BLOCK = BLOCKS.register("silicon_backtank", () -> new CurrencyBacktankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(), DeferredHolder.create(Registries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "silicon_backtank"))));
    public static final DeferredBlock<Block> RUBY_BACKTANK_BLOCK = BLOCKS.register("ruby_backtank", () -> new CurrencyBacktankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(), DeferredHolder.create(Registries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "ruby_backtank"))));

    // Block Items
    public static final DeferredItem<BacktankItem.BacktankBlockItem> JADE_BACKTANK_PLACEABLE = ITEMS.register("jade_backtank_placeable", () -> new BacktankItem.BacktankBlockItem(JADE_BACKTANK_BLOCK.get(), JADE_BACKTANK, new Item.Properties()));
    public static final DeferredItem<BacktankItem.BacktankBlockItem> AMBER_BACKTANK_PLACEABLE = ITEMS.register("amber_backtank_placeable", () -> new BacktankItem.BacktankBlockItem(AMBER_BACKTANK_BLOCK.get(), AMBER_BACKTANK, new Item.Properties()));
    public static final DeferredItem<BacktankItem.BacktankBlockItem> SILICON_BACKTANK_PLACEABLE = ITEMS.register("silicon_backtank_placeable", () -> new BacktankItem.BacktankBlockItem(SILICON_BACKTANK_BLOCK.get(), SILICON_BACKTANK, new Item.Properties()));
    public static final DeferredItem<BacktankItem.BacktankBlockItem> RUBY_BACKTANK_PLACEABLE = ITEMS.register("ruby_backtank_placeable", () -> new BacktankItem.BacktankBlockItem(RUBY_BACKTANK_BLOCK.get(), RUBY_BACKTANK, new Item.Properties()));

    // Block Entities
    public static final Supplier<BlockEntityType<BacktankBlockEntity>> JADE_BACKTANK_BLOCKENTITY = BLOCK_ENTITIES.register("jade_backtank", () -> BlockEntityType.Builder.of(ModCGRegistry::createJadeBacktankBlockentity, JADE_BACKTANK_BLOCK.get()).build(null));
    public static final Supplier<BlockEntityType<BacktankBlockEntity>> AMBER_BACKTANK_BLOCKENTITY = BLOCK_ENTITIES.register("amber_backtank", () -> BlockEntityType.Builder.of(ModCGRegistry::createAmberBacktankBlockentity, AMBER_BACKTANK_BLOCK.get()).build(null));
    public static final Supplier<BlockEntityType<BacktankBlockEntity>> SILICON_BACKTANK_BLOCKENTITY = BLOCK_ENTITIES.register("silicon_backtank", () -> BlockEntityType.Builder.of(ModCGRegistry::createSiliconBacktankBlockentity, SILICON_BACKTANK_BLOCK.get()).build(null));
    public static final Supplier<BlockEntityType<BacktankBlockEntity>> RUBY_BACKTANK_BLOCKENTITY = BLOCK_ENTITIES.register("ruby_backtank", () -> BlockEntityType.Builder.of(ModCGRegistry::createRubyBacktankBlockentity, RUBY_BACKTANK_BLOCK.get()).build(null));

    // Helper methods
    private static BacktankBlockEntity createJadeBacktankBlockentity(BlockPos pos, BlockState state) {
        return new BacktankBlockEntity(JADE_BACKTANK_BLOCKENTITY.get(), pos, state);
    }

    private static BacktankBlockEntity createAmberBacktankBlockentity(BlockPos pos, BlockState state) {
        return new BacktankBlockEntity(AMBER_BACKTANK_BLOCKENTITY.get(), pos, state);
    }

    private static BacktankBlockEntity createSiliconBacktankBlockentity(BlockPos pos, BlockState state) {
        return new BacktankBlockEntity(SILICON_BACKTANK_BLOCKENTITY.get(), pos, state);
    }

    private static BacktankBlockEntity createRubyBacktankBlockentity(BlockPos pos, BlockState state) {
        return new BacktankBlockEntity(RUBY_BACKTANK_BLOCKENTITY.get(), pos, state);
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
    }
}
