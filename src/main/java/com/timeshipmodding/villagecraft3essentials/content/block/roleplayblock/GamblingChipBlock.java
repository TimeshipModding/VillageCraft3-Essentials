package com.timeshipmodding.villagecraft3essentials.content.block.roleplayblock;

import com.mojang.serialization.MapCodec;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.GamblingChipBlockEntity;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class GamblingChipBlock extends BaseEntityBlock {
    public GamblingChipBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.5, 0.8125), BooleanOp.OR);
        return shape;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int stackIndex = -1;

        if (stack.is(ModItems.OASIS_GAMBLING_CHIP_1)) {
            stackIndex = 0;

        } else if (stack.is(ModItems.OASIS_GAMBLING_CHIP_2)) {
            stackIndex = 1;

        } else if (stack.is(ModItems.OASIS_GAMBLING_CHIP_3)) {
            stackIndex = 2;

        } else if (stack.is(ModItems.OASIS_GAMBLING_CHIP_4)) {
            stackIndex = 3;
        }

        if (stackIndex == -1) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (!level.isClientSide && level.getBlockEntity(pos) instanceof GamblingChipBlockEntity gamblingChipBlockEntity) {
            if (gamblingChipBlockEntity.stacks[stackIndex] >= 8) {
                return ItemInteractionResult.FAIL;
            }

            gamblingChipBlockEntity.setStackCount(stackIndex, gamblingChipBlockEntity.stacks[stackIndex] + 1);
            stack.consume(1, player);
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof GamblingChipBlockEntity gamblingChipBlockEntity) {
            for (int i = 0; i < 4; i++) {
                if (gamblingChipBlockEntity.stacks[i] == 0) {
                    continue;
                }

                gamblingChipBlockEntity.setStackCount(i, gamblingChipBlockEntity.stacks[i] - 1);
                player.getInventory().add(getGamblingChipItem(i).getDefaultInstance());
                break;
            }

            if (gamblingChipBlockEntity.stacks[0] == 0 && gamblingChipBlockEntity.stacks[1] == 0 && gamblingChipBlockEntity.stacks[2] == 0 && gamblingChipBlockEntity.stacks[3] == 0) {
                level.removeBlock(pos, false);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> drops = new ArrayList<>();

        if (params.getOptionalParameter(LootContextParams.BLOCK_ENTITY) instanceof GamblingChipBlockEntity gamblingChipBlockEntity) {
            for (int i = 0; i < 4; i++) {
                if (gamblingChipBlockEntity.stacks[i] == 0) {
                    continue;
                }

                drops.add(new ItemStack(getGamblingChipItem(i), gamblingChipBlockEntity.stacks[i]));
            }
        }

        return drops;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GamblingChipBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return state;
    }

    private Item getGamblingChipItem(int stackIndex) {
        return switch (stackIndex) {
            case 1 -> ModItems.OASIS_GAMBLING_CHIP_2.get();
            case 2 -> ModItems.OASIS_GAMBLING_CHIP_3.get();
            case 3 -> ModItems.OASIS_GAMBLING_CHIP_4.get();
            default -> ModItems.OASIS_GAMBLING_CHIP_1.get();
        };
    }
}