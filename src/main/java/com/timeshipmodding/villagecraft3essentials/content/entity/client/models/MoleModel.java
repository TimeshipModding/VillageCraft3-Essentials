package com.timeshipmodding.villagecraft3essentials.content.entity.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.timeshipmodding.villagecraft3essentials.content.entity.MoleEntity;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.animations.MoleAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MoleModel extends HierarchicalModel<MoleEntity> {
    private final ModelPart mole;

    public MoleModel(ModelPart root) {
        this.mole = root.getChild("mole");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mole = partdefinition.addOrReplaceChild("mole", CubeListBuilder.create(), PartPose.offset(3.0F, 24.0F, -5.0F));

        PartDefinition body = mole.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -4.5F, 6.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0436F, -2.4467F, 4.5F));

        PartDefinition tail = mole.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(14, 14).addBox(-1.0F, 0.5F, 1.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, 8.0F));

        PartDefinition nose = mole.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(12, 21).addBox(-4.5F, -2.5F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition arm1 = mole.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(0, 14).addBox(3.75F, -2.0F, -1.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(8, 22).addBox(5.75F, -2.0F, 3.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 22).addBox(6.75F, -2.0F, 1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 22).addBox(6.75F, -2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(5.75F, -2.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 1.0F, 1.0F, 0.4363F, 0.2618F, 0.0F));

        PartDefinition arm2 = mole.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(5.75F, -2.0F, -1.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 22).addBox(4.75F, -2.0F, 3.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 22).addBox(3.75F, -2.0F, 1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 22).addBox(3.75F, -2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(4.75F, -2.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 1.0F, -2.0F, 0.4363F, -0.2618F, 0.0F));

        PartDefinition arm4 = mole.addOrReplaceChild("arm4", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, -0.75F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -0.5F, 7.0F, 0.0F, 1.309F, 0.0F));

        PartDefinition arm3 = mole.addOrReplaceChild("arm3", CubeListBuilder.create().texOffs(14, 18).addBox(-2.0F, 0.0F, -0.75F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, 7.0F, 0.0F, 1.309F, -3.1416F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public static LayerDefinition createSaddleLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mole = partdefinition.addOrReplaceChild("mole", CubeListBuilder.create(), PartPose.offset(3.0F, 24.0F, -5.0F));

        PartDefinition body = mole.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -4.5F, 6.0F, 5.0F, 9.0F, new CubeDeformation(0.25F)), PartPose.offset(-3.0436F, -2.4467F, 4.5F));

        PartDefinition tail = mole.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(14, 14).addBox(-1.0F, 0.5F, 1.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-3.0F, -1.0F, 8.0F));

        PartDefinition nose = mole.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(12, 21).addBox(-4.5F, -2.5F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition arm1 = mole.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(0, 14).addBox(3.75F, -2.0F, -1.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.25F))
                .texOffs(8, 22).addBox(5.75F, -2.0F, 3.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(8, 22).addBox(6.75F, -2.0F, 1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(8, 22).addBox(6.75F, -2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(0, 22).addBox(5.75F, -2.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-4.0F, 1.0F, 1.0F, 0.4363F, 0.2618F, 0.0F));

        PartDefinition arm2 = mole.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(5.75F, -2.0F, -1.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(8, 22).addBox(4.75F, -2.0F, 3.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(8, 22).addBox(3.75F, -2.0F, 1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(8, 22).addBox(3.75F, -2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(0, 22).addBox(4.75F, -2.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-13.0F, 1.0F, -2.0F, 0.4363F, -0.2618F, 0.0F));

        PartDefinition arm4 = mole.addOrReplaceChild("arm4", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, -0.75F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-8.0F, -0.5F, 7.0F, 0.0F, 1.309F, 0.0F));

        PartDefinition arm3 = mole.addOrReplaceChild("arm3", CubeListBuilder.create().texOffs(14, 18).addBox(-2.0F, 0.0F, -0.75F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(2.0F, -0.5F, 7.0F, 0.0F, 1.309F, -3.1416F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(MoleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animateWalk(MoleAnimations.ANIMATION_MOLE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        mole.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return mole;
    }
}
