package net.herodegon.tutorialmod.entity.client;

import net.herodegon.tutorialmod.TutorialMod;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BoomerangProjectileModel extends EntityModel<BoomerangProjectileRenderState> {
	public static final EntityModelLayer BOOMERANG_ENTITY = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "boomerang_entity"), "main");
	private final ModelPart elbow;
	private final ModelPart arm1;
	private final ModelPart arm2;
	private final ModelPart ornament;
	public BoomerangProjectileModel(ModelPart root) {
		super(root);
		this.elbow = root.getChild("elbow");
		this.arm1 = root.getChild("arm1");
		this.arm2 = root.getChild("arm2");
		this.ornament = root.getChild("ornament");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData elbow = modelPartData.addChild("elbow", ModelPartBuilder.create().uv(0, 12).cuboid(-5.0F, -1.0F, 3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-5.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.0F, -1.0F, 2.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData arm1 = modelPartData.addChild("arm1", ModelPartBuilder.create().uv(0, 15).cuboid(3.0F, -1.0F, 3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(16, 15).cuboid(4.0F, -1.0F, 4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 8).cuboid(0.0F, -1.0F, 3.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData arm2 = modelPartData.addChild("arm2", ModelPartBuilder.create().uv(0, 8).cuboid(-5.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(12, 4).cuboid(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(10, 15).cuboid(-5.0F, -1.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData ornament = modelPartData.addChild("ornament", ModelPartBuilder.create(), ModelTransform.of(0.0F, 26.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r1 = ornament.addChild("cube_r1", ModelPartBuilder.create().uv(10, 11).cuboid(0.0F, -3.0F, -1.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -1.0F, 3.0F, -3.1416F, 0.0F, 3.1416F));

		ModelPartData cube_r2 = ornament.addChild("cube_r2", ModelPartBuilder.create().uv(6, 15).cuboid(1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -1.0F, 1.0F, -3.1416F, 0.0F, 3.1416F));

		ModelPartData cube_r3 = ornament.addChild("cube_r3", ModelPartBuilder.create().uv(12, 0).cuboid(-1.0F, -3.0F, -2.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, 1.0F, -3.1416F, 0.0F, 3.1416F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	
	@Override
	public void setAngles(BoomerangProjectileRenderState renderState) {
		// You can use renderState.rotation here to rotate parts of the model
		// For example: this.elbow.yaw = renderState.rotation * 0.017453292F; // Convert degrees to radians
	}

	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		int color = ColorHelper.getArgb((int) red, (int) green, (int) blue, (int) alpha);
		elbow.render(matrices, vertexConsumer, light, overlay, color);
		arm1.render(matrices, vertexConsumer, light, overlay, color);
		arm2.render(matrices, vertexConsumer, light, overlay, color);
		ornament.render(matrices, vertexConsumer, light, overlay, color);
	}
}