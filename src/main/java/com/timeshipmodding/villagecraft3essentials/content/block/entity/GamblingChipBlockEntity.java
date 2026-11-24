package com.timeshipmodding.villagecraft3essentials.content.block.entity;

import blue.endless.jankson.annotation.Nullable;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class GamblingChipBlockEntity extends BlockEntity {
    public static final ModelProperty<int[]> CHIP_COUNTS = new ModelProperty<>();
    public final int[] stacks = new int[] {0, 0, 0, 0};

    public GamblingChipBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OASIS_GAMBLING_CHIP_BLOCKENTITY.get(), pos, state);
    }

    public void setStackCount(int stackIndex, int count) {
        this.stacks[stackIndex] = Mth.clamp(count, 0, 8);
        setChanged();

        if (level != null) {
            if (!level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);

            } else {
                requestModelDataUpdate();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    @Override
    public ModelData getModelData() {
        return ModelData.builder()
                .with(CHIP_COUNTS, stacks.clone())
                .build();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putIntArray("stacks", stacks);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        int[] saved = tag.getIntArray("stacks");

        if (saved.length == 4) {
            System.arraycopy(saved, 0, stacks, 0, 4);
        }

        if (level != null && level.isClientSide) {
            requestModelDataUpdate();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        requestModelDataUpdate();

        if (level != null && level.isClientSide) {
            level.setBlocksDirty(worldPosition, getBlockState(), getBlockState());
        }
    }
}