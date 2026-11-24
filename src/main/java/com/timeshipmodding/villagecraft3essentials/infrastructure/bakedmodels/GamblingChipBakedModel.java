package com.timeshipmodding.villagecraft3essentials.infrastructure.bakedmodels;

import blue.endless.jankson.annotation.Nullable;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.GamblingChipBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.CompositeModel;
import net.neoforged.neoforge.client.model.data.ModelData;

import java.util.ArrayList;
import java.util.List;

public class GamblingChipBakedModel extends BakedModelWrapper<CompositeModel.Baked> {
    public GamblingChipBakedModel(CompositeModel.Baked originalModel) {
        super(originalModel);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand, ModelData data, @Nullable RenderType renderType) {
        List<BakedQuad> quads = new ArrayList<>();
        int[] stacks = data.get(GamblingChipBlockEntity.CHIP_COUNTS);

        if (stacks != null && stacks.length == 4) {
            for (int stackIndex = 0; stackIndex < 4; stackIndex++) {
                int chipCount = stacks[stackIndex];

                for (int chipHeight = 1; chipHeight <= chipCount; chipHeight++) {
                    String partName = "stack_" + stackIndex + "_chip_" + chipHeight;
                    BakedModel chipPart = originalModel.getPart(partName);

                    if (chipPart != null) {
                        quads.addAll(chipPart.getQuads(state, side, rand, data, renderType));
                    }
                }
            }
        }

        return quads;
    }
}