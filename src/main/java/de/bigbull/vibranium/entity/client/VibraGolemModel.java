package de.bigbull.vibranium.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bigbull.vibranium.entity.animations.ModAnimationDefinitions;
import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class VibraGolemModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart vibra;
	private final ModelPart head;

	public VibraGolemModel(ModelPart root) {
		this.vibra = root.getChild("vibra");
		this.head = vibra.getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Vibra = partdefinition.addOrReplaceChild("vibra", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition body = Vibra.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-8.0F, -12.0F, -6.0F, 16.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition R_Arm = body.addOrReplaceChild("R_Arm", CubeListBuilder.create().texOffs(50, 36).addBox(-9.0F, -4.0F, -6.0F, 9.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, -24.0F, 1.0F, 0.0873F, 0.0F, 0.0436F));

		PartDefinition cube_r1 = R_Arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 27).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -5.0F, 3.0F, -0.0326F, 0.1199F, -0.0351F));

		PartDefinition cube_r2 = R_Arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -5.0F, -3.0F, 0.0054F, -0.0061F, -0.0432F));

		PartDefinition cube_r3 = R_Arm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(46, 29).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -3.0F, 3.0F, -0.0869F, -0.0076F, -0.0869F));

		PartDefinition cube_r4 = R_Arm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 65).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -3.0F, -3.0F, 0.0432F, 0.0282F, -0.0837F));

		PartDefinition cube_r5 = R_Arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 93).addBox(-7.0F, -5.0F, -3.0F, 7.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 10.0F, -1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition R_LowerArm = R_Arm.addOrReplaceChild("R_LowerArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r6 = R_LowerArm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(66, 12).addBox(-8.0F, -6.0F, -4.0F, 9.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 6.0F, -1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition L_Arm = body.addOrReplaceChild("L_Arm", CubeListBuilder.create().texOffs(48, 57).addBox(0.0F, -4.0F, -6.0F, 9.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -24.0F, 1.0F, 0.0873F, 0.0F, -0.0436F));

		PartDefinition cube_r7 = L_Arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, 3.0F, -0.0326F, -0.1199F, 0.0351F));

		PartDefinition cube_r8 = L_Arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -3.0F, 0.0054F, 0.0061F, 0.0432F));

		PartDefinition cube_r9 = L_Arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(104, 24).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -3.0F, 3.0F, -0.0869F, 0.0076F, 0.0869F));

		PartDefinition cube_r10 = L_Arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(104, 82).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -3.0F, -3.0F, 0.0432F, -0.0282F, 0.0837F));

		PartDefinition cube_r11 = L_Arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, -5.0F, -3.0F, 7.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 10.0F, -1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition L_LowerArm = L_Arm.addOrReplaceChild("L_LowerArm", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 14.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r12 = L_LowerArm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 65).addBox(-1.0F, -6.0F, -4.0F, 9.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 6.0F, -1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition F_Body = body.addOrReplaceChild("F_Body", CubeListBuilder.create().texOffs(64, 0).addBox(-5.0F, -29.0F, -3.0F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-13.0F, -27.0F, -5.0F, 26.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-10.0F, -17.0F, -4.0F, 20.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(80, 34).addBox(-5.0F, -10.0F, -3.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(28, 78).addBox(-6.0F, -5.0F, -4.0F, 12.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r13 = F_Body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(104, 15).addBox(-2.9526F, 1.1735F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -30.0F, 1.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r14 = F_Body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 65).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -30.0F, 1.0F, -0.029F, -0.034F, -0.0113F));

		PartDefinition cube_r15 = F_Body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(62, 78).addBox(-1.9406F, -1.0009F, -1.989F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -30.0F, 1.0F, -0.0078F, -0.0765F, 0.0298F));

		PartDefinition cube_r16 = F_Body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -30.0F, 1.0F, -0.029F, 0.034F, 0.0113F));

		PartDefinition cube_r17 = F_Body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(46, 22).addBox(-2.0594F, -1.0009F, -1.989F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -30.0F, 1.0F, -0.0078F, 0.0765F, -0.0298F));

		PartDefinition cube_r18 = F_Body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(102, 92).addBox(-3.0474F, 1.1735F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -30.0F, 1.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition L_Leg = Vibra.addOrReplaceChild("L_Leg", CubeListBuilder.create().texOffs(96, 69).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(84, 98).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -14.0F, 1.0F));

		PartDefinition L_LowerLeg = L_Leg.addOrReplaceChild("L_LowerLeg", CubeListBuilder.create().texOffs(84, 49).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition R_Leg = Vibra.addOrReplaceChild("R_Leg", CubeListBuilder.create().texOffs(94, 2).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(60, 98).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -14.0F, 1.0F));

		PartDefinition R_LowerLeg = R_Leg.addOrReplaceChild("R_LowerLeg", CubeListBuilder.create().texOffs(72, 78).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.VibraGolemAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((VibraGolemEntity) entity).idleAnimationState, ModAnimationDefinitions.VibraGolemAnimation.idle, ageInTicks, 1f);
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch, float ageInTicks) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer, int p_103113_, int p_103114_, int p_350308_) {
		vibra.render(poseStack, consumer, p_103113_, p_103114_, p_350308_);
	}

	@Override
	public ModelPart root() {
		return vibra;
	}
}