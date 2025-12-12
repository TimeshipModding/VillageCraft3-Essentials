package com.timeshipmodding.villagecraft3essentials.content.block;

import com.mojang.serialization.MapCodec;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.AtmBlockEntity;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AtmBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    private final int guiTextureIndex;

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

    public AtmBlock(int guiTextureIndex, Properties pProperties) {
        super(pProperties);
        this.guiTextureIndex = guiTextureIndex;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.LOWER) {
            Direction direction = state.getValue(FACING);
            return switch (direction) {
                case NORTH -> NORTH_LOWER_SHAPE;
                case SOUTH -> SOUTH_LOWER_SHAPE;
                case WEST -> WEST_LOWER_SHAPE;
                default -> EAST_LOWER_SHAPE;
            };
        } else if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            Direction direction = state.getValue(FACING);
            return switch (direction) {
                case NORTH -> NORTH_UPPER_SHAPE;
                case SOUTH -> SOUTH_UPPER_SHAPE;
                case WEST -> WEST_UPPER_SHAPE;
                default -> EAST_UPPER_SHAPE;
            };
        }

        return null;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);

            if(entity instanceof AtmBlockEntity atmBlockEntity) {
                atmBlockEntity.setRandomConvertScreen(true);
                atmBlockEntity.setToolConvertScreen(false);
                atmBlockEntity.setRandomConvertButtonPressed(0);
                atmBlockEntity.setToolConvertButtonPressed(0);
                atmBlockEntity.setGuiTextureIndex(guiTextureIndex);
                level.sendBlockUpdated(pos, state, state, 2);
                atmBlockEntity.setChanged();
                ServerPlayer serverPlayer = (ServerPlayer) player;
                serverPlayer.openMenu(new SimpleMenuProvider(atmBlockEntity, Component.empty()), pos);
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);

        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf ? state.setValue(FACING, facingState.getValue(FACING)) : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
    }

    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
            DoubleBlock.preventCreativeDropFromBottomPart(level, pos, state, player);
        }

        super.playerWillDestroy(level, pos, state, player);
        return state;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();

        if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity livingEntity, ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AtmBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.ATM_BLOCKENTITY.get(), (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}