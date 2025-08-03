package net.herodegon.tutorialmod.entity.client;

import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.math.Vec2f;

public class BoomerangProjectileRenderState extends EntityRenderState {
    public float rotation = 0.0f;
    public Vec2f groundedOffset = Vec2f.ZERO;
    public boolean isInAir = true;
}
