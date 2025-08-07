package net.herodegon.boomerangmod.entity.custom;

import net.herodegon.boomerangmod.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;

public class BoomerangProjectileEntity extends PersistentProjectileEntity
{
    private float rotation = 0.0f;
    public Vec2f groundedOffset = Vec2f.ZERO;
    private static final float RANGE = 10.0f;
    private static final float DAMAGE = 3.0f;

    public BoomerangProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BoomerangProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, LivingEntity owner, World world)
    {
        super(entityType, owner, world);
    }

    @Override
    public void tick()
    {
        super.tick();
        float distance = (float) this.getPos().distanceTo(this.getOwner().getPos());
        if (distance > RANGE)
        {
            setVelocity(getVelocity().multiply(-1.0, -1.0, -1.0));
        }
    }

    @Override
    protected ItemStack asItemStack()
    {
        return new ItemStack(ModItems.BOOMERANG_ITEM);
    }

    public float getRenderingRotation()
    {
        rotation += 0.5f;
        if (rotation >= 360.0f)
        {
            rotation -= 360.0f;
        }
        return rotation;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity();
        if (!this.getWorld().isClient())
        {
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()), DAMAGE);
        }
	}

    @Override
    protected void onBlockHit(BlockHitResult result)
    {
        super.onBlockHit(result);
        if (result.getSide() == Direction.NORTH)
        {
            this.groundedOffset = new Vec2f(215.0f, 0.0f);
        }
        if (result.getSide() == Direction.EAST)
        {
            this.groundedOffset = new Vec2f(215.0f, 90.0f);
        }
        if (result.getSide() == Direction.SOUTH)
        {
            this.groundedOffset = new Vec2f(215.0f, 180.0f);
        }
        if (result.getSide() == Direction.WEST)
        {
            this.groundedOffset = new Vec2f(215.0f, 270.0f);
        }

        if(result.getSide() == Direction.UP)
        {
            this.groundedOffset = new Vec2f(285.0f, 0.0f);
        }
        if(result.getSide() == Direction.DOWN)
        {
            this.groundedOffset = new Vec2f(115.0f, 0.0f);
        }
    }
}
