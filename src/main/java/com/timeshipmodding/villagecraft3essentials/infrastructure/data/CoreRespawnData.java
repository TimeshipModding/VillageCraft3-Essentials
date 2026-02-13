package com.timeshipmodding.villagecraft3essentials.infrastructure.data;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public record CoreRespawnData(BlockPos pos, BlockState state, long respawnTime) {
}
