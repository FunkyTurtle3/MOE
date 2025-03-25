package de.funkyturtle.moreofeverything.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.entity.anim.KiwiAnimations;
import de.funkyturtle.moreofeverything.entity.custom.KiwiEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class KiwiModel<T extends KiwiEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "kiwi"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart beak;
	private final ModelPart legs;
	private final ModelPart leg_left;
	private final ModelPart toes_left;
	private final ModelPart leg_right;
	private final ModelPart toes_right;
	private final ModelPart torso;
	private final ModelPart wings;
	private final ModelPart wing_left;
	private final ModelPart wing_right;

	public KiwiModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.beak = this.head.getChild("beak");
		this.legs = this.body.getChild("legs");
		this.leg_left = this.legs.getChild("leg_left");
		this.toes_left = this.leg_left.getChild("toes_left");
		this.leg_right = this.legs.getChild("leg_right");
		this.toes_right = this.leg_right.getChild("toes_right");
		this.torso = this.body.getChild("torso");
		this.wings = this.torso.getChild("wings");
		this.wing_left = this.wings.getChild("wing_left");
		this.wing_right = this.wings.getChild("wing_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 23.9F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -4.0F));

		PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 24).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -4.0F));

		PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(28, 5).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition toes_left = leg_left.addOrReplaceChild("toes_left", CubeListBuilder.create().texOffs(26, 26).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(28, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition toes_right = leg_right.addOrReplaceChild("toes_right", CubeListBuilder.create().texOffs(26, 24).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -5.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wings = torso.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition wing_left = wings.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(18, 14).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition wing_right = wings.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void setupAnim(KiwiEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(KiwiAnimations.WALK, limbSwing, limbSwingAmount, 3.5F, 2.5F);
		this.animate(entity.idleAnimationState ,KiwiAnimations.IDLE, ageInTicks , 1F);

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
		root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
	}

	@Override
	public @NotNull ModelPart root() {
		return body;
	}
}