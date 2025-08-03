package net.herodegon.tutorialmod.entity.client;

import net.herodegon.tutorialmod.TutorialMod;
import net.herodegon.tutorialmod.entity.custom.BoomerangProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec2f;

public class BoomerangProjectileRenderer extends EntityRenderer<BoomerangProjectileEntity, BoomerangProjectileRenderState> 
{
    protected BoomerangProjectileModel model;

    public BoomerangProjectileRenderer(EntityRendererFactory.Context ctx)
    {
        super(ctx);
        this.model = new BoomerangProjectileModel(ctx.getPart(BoomerangProjectileModel.BOOMERANG_ENTITY));
    }

    @Override
    public void render(BoomerangProjectileRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
        matrices.push();
        if (state.isInAir)
        {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.rotation * 5.0f));
        }
        else
        {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.groundedOffset.y));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.groundedOffset.x));
            matrices.translate(0.0, -0.5f, 0.0);
        }

        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, 
            this.model.getLayer(Identifier.of(TutorialMod.MOD_ID, "textures/entity/boomerang_entity/boomerang_entity.png")), false, false);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }

    public void updateRenderState(BoomerangProjectileRenderState state, BoomerangProjectileEntity entity, float tickDelta)
    {
        state.isInAir = !entity.isOnGround();
        if (state.isInAir)
        {
            state.rotation = entity.getRenderingRotation();
        }
        else if (state.groundedOffset.equals(Vec2f.ZERO))
        {
            {
                state.groundedOffset = entity.groundedOffset;
            }
        }
    }

    @Override
    public BoomerangProjectileRenderState createRenderState()
    {
        return new BoomerangProjectileRenderState();
    }
}
