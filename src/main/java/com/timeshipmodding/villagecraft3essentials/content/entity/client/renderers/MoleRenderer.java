package com.timeshipmodding.villagecraft3essentials.content.entity.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.entity.MoleEntity;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.models.MoleModel;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;

public class MoleRenderer extends MobRenderer<MoleEntity, MoleModel> {
    public MoleRenderer(EntityRendererProvider.Context context) {
        super(context, new MoleModel(context.bakeLayer(ModModelLayers.MOLE)), 0.25f);
        this.addLayer(new SaddleLayer(
                        this, new MoleModel(context.bakeLayer(ModModelLayers.MOLE_SADDLE)), ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/entity/mole/mole_saddle.png"))
        );
    }

    @Override
    public ResourceLocation getTextureLocation(MoleEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/entity/mole/mole.png");
    }

    @Override
    public void render(MoleEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.scale(1.75f, 1.75f, 1.75f);
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}