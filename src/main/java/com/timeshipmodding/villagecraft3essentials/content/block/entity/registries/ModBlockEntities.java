package com.timeshipmodding.villagecraft3essentials.content.block.entity.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.AtmBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, VillageCraft3Essentials.MODID);

    public static final Supplier<BlockEntityType<AtmBlockEntity>> ATM_BLOCKENTITY =
            BLOCK_ENTITIES.register("atm_blockentity", () -> BlockEntityType.Builder.of(
                    AtmBlockEntity::new, ModBlocks.BLACK_ATM.get(), ModBlocks.BLUE_ATM.get(), ModBlocks.BROWN_ATM.get(), ModBlocks.CYAN_ATM.get(), ModBlocks.GRAY_ATM.get(), ModBlocks.GREEN_ATM.get(), ModBlocks.LIGHT_BLUE_ATM.get(), ModBlocks.LIME_ATM.get(), ModBlocks.LIGHT_GRAY_ATM.get(), ModBlocks.MAGENTA_ATM.get(), ModBlocks.ORANGE_ATM.get(), ModBlocks.PINK_ATM.get(), ModBlocks.PURPLE_ATM.get(), ModBlocks.RED_ATM.get(), ModBlocks.WHITE_ATM.get(), ModBlocks.YELLOW_ATM.get()
            ).build(null));
}