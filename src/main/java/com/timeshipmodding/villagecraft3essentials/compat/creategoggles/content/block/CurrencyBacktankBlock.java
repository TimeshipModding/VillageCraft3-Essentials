package com.timeshipmodding.villagecraft3essentials.compat.creategoggles.content.block;

import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class CurrencyBacktankBlock extends BacktankBlock {
    private final Supplier<BlockEntityType<? extends BacktankBlockEntity>> blockEntityType;

    public CurrencyBacktankBlock(Properties properties, Supplier<BlockEntityType<? extends BacktankBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends BacktankBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}