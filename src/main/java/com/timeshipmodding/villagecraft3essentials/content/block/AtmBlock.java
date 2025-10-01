package com.timeshipmodding.villagecraft3essentials.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AtmBlock extends HorizontalDirectionalBlock {
    public Boolean pHalf;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    // Voxel Shapes
    protected static final VoxelShape NORTH_LOWER_SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 2, 2, 16, 16, 16));

    protected static final VoxelShape NORTH_UPPER_SHAPE = Shapes.or(
            Block.box(1, 0, 9, 15, 13, 16),
            Block.box(0, 13, 2, 16, 16, 16),
            Block.box(1, 0, 1, 15, 4, 15),
            Block.box(15, 0, 2, 16, 13, 15),
            Block.box(0, 0, 15, 16, 13, 16),
            Block.box(0, 0, 2, 1, 13, 15));

    protected static final VoxelShape WEST_LOWER_SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 0, 16, 16, 16));

    protected static final VoxelShape WEST_UPPER_SHAPE = Shapes.or(
            Block.box(8, 0, 1, 15, 13, 15),
            Block.box(2, 13, 0, 16, 16, 16),
            Block.box(1, 0, 1, 15, 4, 15),
            Block.box(2, 0, 0, 15, 13, 1),
            Block.box(15, 0, 0, 16, 13, 16),
            Block.box(2, 0, 15, 15, 13, 16));

    protected static final VoxelShape SOUTH_LOWER_SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 2, 0, 16, 16, 14));

    protected static final VoxelShape SOUTH_UPPER_SHAPE = Shapes.or(
            Block.box(1, 0, 0, 15, 13, 7),
            Block.box(0, 13, 0, 16, 16, 14),
            Block.box(1, 0, 1, 15, 4, 15),
            Block.box(0, 0, 1, 1, 13, 14),
            Block.box(0, 0, 0, 16, 13, 1),
            Block.box(15, 0, 1, 16, 13, 14));

    protected static final VoxelShape EAST_LOWER_SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 2, 0, 14, 16, 16));

    protected static final VoxelShape EAST_UPPER_SHAPE = Shapes.or(
            Block.box(0, 0, 1, 7, 13, 15),
            Block.box(0, 13, 0, 14, 16, 16),
            Block.box(1, 0, 1, 15, 4, 15),
            Block.box(1, 0, 15, 14, 13, 16),
            Block.box(0, 0, 0, 1, 13, 16),
            Block.box(1, 0, 0, 14, 13, 1));

    public AtmBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.LOWER) {
            Direction direction = pState.getValue(FACING);
            return switch (direction) {
                case NORTH -> NORTH_LOWER_SHAPE;
                case SOUTH -> SOUTH_LOWER_SHAPE;
                case WEST -> WEST_LOWER_SHAPE;
                default -> EAST_LOWER_SHAPE;
            };
        } else if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            Direction direction = pState.getValue(FACING);
            return switch (direction) {
                case NORTH -> NORTH_UPPER_SHAPE;
                case SOUTH -> SOUTH_UPPER_SHAPE;
                case WEST -> WEST_UPPER_SHAPE;
                default -> EAST_UPPER_SHAPE;
            };
        }
        return null;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
        if (pFacing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (pFacing == Direction.UP)) {
            return pFacingState.is(this) && pFacingState.getValue(HALF) != doubleblockhalf ? pState.setValue(FACING, pFacingState.getValue(FACING)) : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && pFacing == Direction.DOWN && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }
    }

    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide && pPlayer.isCreative()) {
            DoubleBlock.preventCreativeDropFromBottomPart(pLevel, pPos, pState, pPlayer);
        }

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
        return pState;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(pContext)) {
            boolean flag = level.hasNeighborSignal(blockpos) || level.hasNeighborSignal(blockpos.above());
            return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
        pLevel.setBlock(pPos.above(), pState.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HALF, FACING);
    }
}
