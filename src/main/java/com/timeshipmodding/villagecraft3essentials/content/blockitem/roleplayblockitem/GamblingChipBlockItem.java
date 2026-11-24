package com.timeshipmodding.villagecraft3essentials.content.blockitem.roleplayblockitem;

import com.timeshipmodding.villagecraft3essentials.content.block.entity.GamblingChipBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GamblingChipBlockItem extends RoleplayBlockItem {
    private final int stackIndex;

    public GamblingChipBlockItem(String town, int stackIndex, Block block, Properties properties) {
        super(town, block, properties);
        this.stackIndex = stackIndex;
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
        boolean isPlaced = super.placeBlock(context, state);

        if (!context.getLevel().isClientSide && context.getLevel().getBlockEntity(context.getClickedPos()) instanceof GamblingChipBlockEntity gamblingChipBlockEntity) {
            gamblingChipBlockEntity.setStackCount(stackIndex, 1);
        }

        return isPlaced;
    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }
}