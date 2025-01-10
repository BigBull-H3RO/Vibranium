package de.bigbull.vibranium.entity.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class VibraGolemModel extends EntityModel<VibraGolemRenderState> {
	private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

	public VibraGolemModel(ModelPart root) {
        super(root);
		this.head = root.getChild("head");
        this.rightArm = root.getChild("R_Arm");
        this.leftArm = root.getChild("L_Arm");
        this.rightLeg = root.getChild("R_Leg");
        this.leftLeg = root.getChild("L_Leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-8.0F, -12.0F, -6.0F, 16.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("R_Arm", CubeListBuilder.create().texOffs(50, 36).addBox(-9.0F, -4.0F, -6.0F, 9.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, -24.0F, 1.0F, 0.0873F, 0.0F, 0.0436F));

		partdefinition.addOrReplaceChild("R_LowerArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(66, 12).addBox(-8.0F, -6.0F, -4.0F, 9.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 6.0F, -1.0F, 0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("L_Arm", CubeListBuilder.create().texOffs(48, 57).addBox(0.0F, -4.0F, -6.0F, 9.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -24.0F, 1.0F, 0.0873F, 0.0F, -0.0436F));

		partdefinition.addOrReplaceChild("L_LowerArm", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("L_Leg", CubeListBuilder.create().texOffs(96, 69).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(84, 98).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -14.0F, 1.0F));

		partdefinition.addOrReplaceChild("L_LowerLeg", CubeListBuilder.create().texOffs(84, 49).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		partdefinition.addOrReplaceChild("R_Leg", CubeListBuilder.create().texOffs(94, 2).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(60, 98).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -14.0F, 1.0F));

		partdefinition.addOrReplaceChild("R_LowerLeg", CubeListBuilder.create().texOffs(72, 78).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

    public void setupAnim(VibraGolemRenderState state) {
        super.setupAnim(state);
        float f = state.attackTicksRemaining;
        float f1 = state.walkAnimationSpeed;
        float f2 = state.walkAnimationPos;
        if (f > 0.0F) {
            this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave(f, 10.0F);
            this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave(f, 10.0F);
        } else {
            this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(f2, 13.0F)) * f1;
            this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(f2, 13.0F)) * f1;
        }

        this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
        this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
        this.rightLeg.xRot = -1.5F * Mth.triangleWave(f2, 13.0F) * f1;
        this.leftLeg.xRot = 1.5F * Mth.triangleWave(f2, 13.0F) * f1;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
    }

	private void applyHeadRotation(float netHeadYaw, float headPitch, float ageInTicks) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
	}
}