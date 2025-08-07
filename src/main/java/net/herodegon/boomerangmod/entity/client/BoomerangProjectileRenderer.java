package net.herodegon.boomerangmod.entity.client;

import net.herodegon.boomerangmod.BoomerangMod;
import net.herodegon.boomerangmod.entity.custom.BoomerangProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class BoomerangProjectileRenderer extends EntityRenderer<BoomerangProjectileEntity> 
{
    protected BoomerangProjectileModel model;

    public BoomerangProjectileRenderer(EntityRendererFactory.Context ctx)
    {
        super(ctx);
        this.model = new BoomerangProjectileModel(ctx.getPart(BoomerangProjectileModel.BOOMERANG_ENTITY));
    }

    @Override
    public void render(BoomerangProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
        matrices.push();
        if (!entity.isOnGround())
        {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRenderingRotation() * 5.0f));
        }
        else
        {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.groundedOffset.y));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.groundedOffset.x));
            matrices.translate(0.0, -0.5f, 0.0);
        }

        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, 
            this.model.getLayer(Identifier.of(BoomerangMod.MOD_ID, "textures/entity/boomerang_entity/boomerang_entity.png")), false, false);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public Identifier getTexture(BoomerangProjectileEntity entity)
    {
        return Identifier.of(BoomerangMod.MOD_ID, "textures/entity/boomerang_entity/boomerang_entity.png");
    }
}
